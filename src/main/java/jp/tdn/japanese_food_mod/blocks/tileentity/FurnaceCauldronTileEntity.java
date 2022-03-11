package jp.tdn.japanese_food_mod.blocks.tileentity;

import jp.tdn.japanese_food_mod.blocks.FurnaceCauldronBlock;
import jp.tdn.japanese_food_mod.container.FurnaceCauldronContainer;
import jp.tdn.japanese_food_mod.init.JPBlocks;
import jp.tdn.japanese_food_mod.init.JPItems;
import jp.tdn.japanese_food_mod.init.JPTileEntities;
import jp.tdn.japanese_food_mod.recipes.FurnaceCauldronRecipe;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Objects;
import java.util.Optional;

import static jp.tdn.japanese_food_mod.init.JPItems.SALT;

public class FurnaceCauldronTileEntity extends TileEntity implements ITickableTileEntity, INamedContainerProvider {
    public static final int INPUT_SLOT = 0;
    public static final int OUTPUT_SLOT = 1;
    public static final int[] RETURN_SLOT = {2, 3, 4};

    private static final String INVENTORY_TAG = "inventory";
    private static final String HEATING_TIME_LEFT_TAG = "heatingTimeLeft";
    private static final String HEATING_MAX_TIME_TAG = "heatingMaxTime";
    private static final String WATER_REMAINING_TAG = "waterRemaining";

    public ItemStackHandler inventory = new ItemStackHandler(5){
        @Override
        public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
            switch (slot){
                case 0:
                    return isInput(stack);
                case 1:
                    return isOutPut(stack);
                case 2:
                case 3:
                case 4:
                    return isReturnOutput(stack);
                default:
                    return false;
            }
        }

        @Override
        protected void onContentsChanged(int slot) {
            super.onContentsChanged(slot);
            FurnaceCauldronTileEntity.this.setChanged();
        }
    };

    private final LazyOptional<ItemStackHandler> inventoryCapabilityExternal = LazyOptional.of(() -> inventory);

    public int heatingTimeLeft = -1;
    public int maxHeatingTime = -1;
    public int waterRemaining = 0;
    public int maxWater = 1000;
    public int needWater = 100;
    private boolean lastActive = false;

    public FurnaceCauldronTileEntity(){
        super(JPTileEntities.FURNACE_CAULDRON);
    }

    private boolean isInput(ItemStack stack){
        if(stack.isEmpty()){
            return false;
        }
        return getRecipe(stack).isPresent();
    }

    private boolean isOutPut(ItemStack stack){
        return stack.getItem() == SALT.get();
    }

    private boolean isReturnOutput(final ItemStack stack){
        return stack.getItem() == Items.GLASS_BOTTLE || stack.getItem() == JPItems.CUP.get() || stack.getItem() == Items.BUCKET || stack.getItem() == JPItems.BITTERN.get();
    }

    private Optional<FurnaceCauldronRecipe> getRecipe(final ItemStack input){
        return getRecipe(new Inventory(input));
    }

    private Optional<FurnaceCauldronRecipe> getRecipe(final IInventory inventory){
        return Objects.requireNonNull(level).getRecipeManager().getRecipeFor(FurnaceCauldronRecipe.RECIPE_TYPE, inventory, level);
    }

    private Optional<ItemStack> getResult(final ItemStack input){
        final Inventory dummyInventory = new Inventory(input);
        return getRecipe(dummyInventory).map(recipe -> recipe.assemble(dummyInventory));
    }

    @Override
    public void tick() {
        if(level == null || level.isClientSide()) {
            return;
        }
        boolean isActive = false;

        final ItemStack input = inventory.getStackInSlot(INPUT_SLOT);
        if(!input.isEmpty() && canAddWater()){
            addWater(input);
            //JapaneseFoodMod.LOGGER.info(waterRemaining);
            if (input.hasContainerItem()) {
                insertOrDropContainerItem(input, INPUT_SLOT);
                input.shrink(1);
                inventory.setStackInSlot(INPUT_SLOT, input);
            }
        }

        if(waterRemaining >= needWater){
            isActive = true;
            if(heatingTimeLeft == -1){
                heatingTimeLeft = maxHeatingTime = getHeatingTime(input);
            }else{
                --heatingTimeLeft;
                if(heatingTimeLeft <= 0){
                    waterRemaining -= needWater;
                    inventory.insertItem(OUTPUT_SLOT, new ItemStack(SALT.get()), false);
                    insertOrDropItem(new ItemStack(JPItems.BITTERN.get()));
                    heatingTimeLeft = -1;
                }
            }
        }else{
            heatingTimeLeft = maxHeatingTime = -1;
        }

        if(lastActive != isActive){
            this.setChanged();
            lastActive = isActive;
        }

        FurnaceCauldronBlock block = (FurnaceCauldronBlock) level.getBlockState(worldPosition).getBlock();
        block.setWaterLevel(level, worldPosition, level.getBlockState(worldPosition), waterRemaining, maxWater);
    }

    private void insertOrDropItem(final ItemStack stack){
        int index;
        boolean canInsertItem = false;
        for(index = 0; index < RETURN_SLOT.length && !(canInsertItem = inventory.insertItem(RETURN_SLOT[index], stack, true).isEmpty()); ++index){
            //JapaneseFoodMod.LOGGER.info(RETURN_SLOT[index]);
        }
        if(canInsertItem){
            inventory.insertItem(RETURN_SLOT[index], stack, false);
        }else{
            InventoryHelper.dropItemStack(level, worldPosition.getX(), worldPosition.getY(), worldPosition.getZ(), stack);
        }
    }

    private void insertOrDropContainerItem(final ItemStack stack, final int slot){
        int index;
        final ItemStack containerItem = stack.getContainerItem();
        boolean canInsertContainerItemIntoReturnSlot = false;
        for(index = 0; index < RETURN_SLOT.length && !(canInsertContainerItemIntoReturnSlot = inventory.insertItem(RETURN_SLOT[index], containerItem, true).isEmpty()); ++index);

        if(canInsertContainerItemIntoReturnSlot){
            inventory.insertItem(RETURN_SLOT[index], containerItem, false);
        }else {
            final boolean canInsertContainerItemIntoSlot = inventory.insertItem(slot, containerItem, true).isEmpty();
            if (canInsertContainerItemIntoSlot) {
                inventory.insertItem(slot, containerItem, false);
            } else {
                InventoryHelper.dropItemStack(Objects.requireNonNull(level), worldPosition.getX(), worldPosition.getY(), worldPosition.getZ(), containerItem);
            }
        }
    }

    private int getHeatingTime(ItemStack input){
        return getRecipe(input).map(FurnaceCauldronRecipe::getCookTime).orElse(1000);
    }

    public boolean canAddWater(){
        return waterRemaining < maxWater;
    }

    public void addWater(ItemStack interact){
        int plus = 0;
        if (Items.WATER_BUCKET.equals(interact.getItem())) {
            plus = 500;
        }else if(JPItems.CUP_WITH_WATER.equals(interact.getItem())){
            plus = 50;
        }
        this.waterRemaining = Math.min(maxWater, waterRemaining + plus);
    }

    public int getWaterRemaining() {
        return waterRemaining;
    }

    public int getMaxWater() {
        return maxWater;
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if(!remove && cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY){
            return inventoryCapabilityExternal.cast();
        }

        return super.getCapability(cap, side);
    }

    @Override
    public void load(BlockState state, CompoundNBT compound) {
        super.load(state, compound);
        this.inventory.deserializeNBT(compound.getCompound(INVENTORY_TAG));
        this.heatingTimeLeft = compound.getInt(HEATING_TIME_LEFT_TAG);
        this.maxHeatingTime = compound.getInt(HEATING_MAX_TIME_TAG);
        this.waterRemaining = compound.getInt(WATER_REMAINING_TAG);
    }

    @Override
    public CompoundNBT save(CompoundNBT compound) {
        super.save(compound);
        compound.put(INVENTORY_TAG, this.inventory.serializeNBT());
        compound.putInt(HEATING_TIME_LEFT_TAG, this.heatingTimeLeft);
        compound.putInt(HEATING_MAX_TIME_TAG, this.maxHeatingTime);
        compound.putInt(WATER_REMAINING_TAG, this.waterRemaining);
        return compound;
    }

    @Override
    public CompoundNBT getUpdateTag() {
        return this.save(new CompoundNBT());
    }

    @Override
    public void setRemoved() {
        super.setRemoved();
        inventoryCapabilityExternal.invalidate();
    }

    @Nonnull
    @Override
    public ITextComponent getDisplayName() {
        return new TranslationTextComponent(JPBlocks.FURNACE_CAULDRON.get().toString());
    }

    @Nonnull
    @Override
    public Container createMenu(int windowId, @Nonnull PlayerInventory inventory, @Nonnull PlayerEntity player) {
        return new FurnaceCauldronContainer(windowId, inventory, this);
    }
}
