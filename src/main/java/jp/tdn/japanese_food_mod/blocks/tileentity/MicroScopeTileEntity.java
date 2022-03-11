package jp.tdn.japanese_food_mod.blocks.tileentity;

import jp.tdn.japanese_food_mod.JapaneseFoodUtil;
import jp.tdn.japanese_food_mod.container.MicroScopeContainer;
import jp.tdn.japanese_food_mod.init.JPBlocks;
import jp.tdn.japanese_food_mod.init.JPTileEntities;
import jp.tdn.japanese_food_mod.recipes.MicroScopeRecipe;
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

public class MicroScopeTileEntity extends TileEntity implements ITickableTileEntity, INamedContainerProvider {
    public static final int INPUT_SLOT = 0;
    public static final int CONTAINER_SLOT = 2;
    public static final int OUTPUT_SLOT = 1;

    private static final String INVENTORY_TAG = "inventory";
    private static final String IDENTIFIED_TIME_LEFT_TAG = "identifiedTimeLeft";
    private static final String MAX_IDENTIFIED_TIME_TAG = "maxIdentifiedTime";

    public ItemStackHandler inventory = new ItemStackHandler(3){
        @Override
        public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
            switch (slot){
                case INPUT_SLOT:
                    return isInput(stack);
                case OUTPUT_SLOT:
                    return isOutput(stack);
                case CONTAINER_SLOT:
                    return isContainerInput(stack);
                default:
                    return false;
            }
        }

        @Override
        protected void onContentsChanged(int slot) {
            super.onContentsChanged(slot);
            MicroScopeTileEntity.this.setChanged();
        }
    };

    private final LazyOptional<ItemStackHandler> inventoryCapabilityExternal = LazyOptional.of(() -> this.inventory);

    public short identifiedTimeLeft = -1;
    public short maxIdentifiedTime = -1;

    public MicroScopeTileEntity(){
        super(JPTileEntities.MICROSCOPE);
    }

    private boolean isInput(final ItemStack stack){
        if(stack.isEmpty()) {
            return false;
        }
        return getRecipe(stack).isPresent();
    }

    private boolean isContainerInput(final ItemStack stack){
        return stack.getItem() == Items.GLASS_BOTTLE;
    }

    private boolean isOutput(final ItemStack stack){
        final Optional<ItemStack> result = getResult(inventory.getStackInSlot(INPUT_SLOT));
        return result.isPresent() && ItemStack.isSame(result.get(), stack);
    }

    private Optional<MicroScopeRecipe> getRecipe(final ItemStack input){
        return getRecipe(new Inventory(input));
    }

    private Optional<MicroScopeRecipe> getRecipe(final IInventory inventory){
        return Objects.requireNonNull(level).getRecipeManager().getRecipeFor(MicroScopeRecipe.RECIPE_TYPE, inventory, level);
    }

    private Optional<ItemStack> getResult(final ItemStack input){
        final Inventory dummyInventory = new Inventory(input);
        return getRecipe(dummyInventory).map(recipe -> recipe.assemble(dummyInventory));
    }

    public ItemStack getInventory(){
        return inventory.getStackInSlot(INPUT_SLOT);
    }

    @Override
    public void tick() {
        if(level == null || level.isClientSide()) {
            return;
        }

        final ItemStack input = inventory.getStackInSlot(INPUT_SLOT);
        final ItemStack container = inventory.getStackInSlot(CONTAINER_SLOT);
        final ItemStack result = getResult(input).orElse(ItemStack.EMPTY);

        if(!result.isEmpty() && isInput(input) && isContainerInput(container)){
            final boolean canInsertResultIntoOutPut = inventory.insertItem(OUTPUT_SLOT, result, true).isEmpty();
            if(canInsertResultIntoOutPut){
                if(identifiedTimeLeft == -1){
                    identifiedTimeLeft = maxIdentifiedTime = getIdentifiedTime(input);
                }else{
                    --identifiedTimeLeft;
                    if(identifiedTimeLeft == 0){
                        if(JapaneseFoodUtil.rand.nextInt(101) <= getRecipe(input).get().getProbability() * 100){
                            inventory.insertItem(OUTPUT_SLOT, result, false);
                            if (input.hasContainerItem()) {
                                insertOrDropContainerItem(input);
                            }
                            container.shrink(1);
                            inventory.setStackInSlot(CONTAINER_SLOT, container);
                        }
                        input.shrink(1);

                        inventory.setStackInSlot(INPUT_SLOT, input);
                        identifiedTimeLeft = -1;
                    }
                }
            }
        }else{
            identifiedTimeLeft = maxIdentifiedTime = -1;
        }
    }

    private void insertOrDropContainerItem(final ItemStack stack){
        final ItemStack containerItem = stack.getContainerItem();
        final boolean canInsertContainerItemIntoSlot = inventory.insertItem(MicroScopeTileEntity.INPUT_SLOT, containerItem, true).isEmpty();
        if(canInsertContainerItemIntoSlot){
            inventory.insertItem(MicroScopeTileEntity.INPUT_SLOT, containerItem, false);
        }else{
            InventoryHelper.dropItemStack(Objects.requireNonNull(level), worldPosition.getX(), worldPosition.getY(), worldPosition.getZ(), containerItem);
        }
    }

    private short getIdentifiedTime(final ItemStack input){
        return getRecipe(input).map(MicroScopeRecipe::getCookTime).orElse(200).shortValue();
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if(cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY){
            return inventoryCapabilityExternal.cast();
        }

        return super.getCapability(cap, side);
    }

    @Override
    public void load(BlockState state, CompoundNBT compound) {
        super.load(state, compound);
        this.inventory.deserializeNBT(compound.getCompound(INVENTORY_TAG));
        this.identifiedTimeLeft = compound.getShort(IDENTIFIED_TIME_LEFT_TAG);
        this.maxIdentifiedTime = compound.getShort(MAX_IDENTIFIED_TIME_TAG);
    }

    @Override
    @Nonnull
    public CompoundNBT save(CompoundNBT compound) {
        super.save(compound);
        compound.put(INVENTORY_TAG, this.inventory.serializeNBT());
        compound.putShort(IDENTIFIED_TIME_LEFT_TAG, this.identifiedTimeLeft);
        compound.putShort(MAX_IDENTIFIED_TIME_TAG, this.maxIdentifiedTime);
        return compound;
    }

    @Override
    @Nonnull
    public CompoundNBT getUpdateTag(){
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
        return new TranslationTextComponent(JPBlocks.MICRO_SCOPE.get().toString());
    }

    @Nonnull
    @Override
    public Container createMenu(int windowId, @Nonnull PlayerInventory inventory, @Nonnull PlayerEntity player) {
        return new MicroScopeContainer(windowId, inventory, this);
    }
}
