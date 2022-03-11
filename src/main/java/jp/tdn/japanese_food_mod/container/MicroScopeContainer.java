package jp.tdn.japanese_food_mod.container;

import jp.tdn.japanese_food_mod.blocks.tileentity.MicroScopeTileEntity;
import jp.tdn.japanese_food_mod.init.JPBlocks;
import jp.tdn.japanese_food_mod.init.JPContainerTypes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.util.IntReferenceHolder;
import net.minecraftforge.items.SlotItemHandler;

import javax.annotation.Nonnull;
import java.util.Objects;

public class MicroScopeContainer extends Container {
    public final MicroScopeTileEntity tileEntity;
    private final IWorldPosCallable canInteractWithCallable;

    public MicroScopeContainer(final int windowId, final PlayerInventory playerInventory, final PacketBuffer data){
        this(windowId, playerInventory, getTileEntity(playerInventory, data));
    }

    public MicroScopeContainer(final int windowId, final PlayerInventory playerInventory, final MicroScopeTileEntity tileEntity){
        super(JPContainerTypes.MICROSCOPE, windowId);
        this.tileEntity = tileEntity;
        this.canInteractWithCallable = IWorldPosCallable.create(Objects.requireNonNull(tileEntity.getLevel()), tileEntity.getBlockPos());

        // Identified time left
        addDataSlot(new IntReferenceHolder() {
            @Override
            public int get() {
                return tileEntity.identifiedTimeLeft;
            }

            @Override
            public void set(int i) {
                tileEntity.identifiedTimeLeft = (short) i;
            }
        });

        // Max identified time
        addDataSlot(new IntReferenceHolder() {
            @Override
            public int get() {
                return tileEntity.maxIdentifiedTime;
            }

            @Override
            public void set(int i) {
                tileEntity.maxIdentifiedTime = (short) i;
            }
        });

        this.addSlot(new SlotItemHandler(tileEntity.inventory, MicroScopeTileEntity.INPUT_SLOT, 42, 38));
        this.addSlot(new SlotItemHandler(tileEntity.inventory, MicroScopeTileEntity.OUTPUT_SLOT, 136, 78));
        this.addSlot(new SlotItemHandler(tileEntity.inventory, MicroScopeTileEntity.CONTAINER_SLOT, 136, 38));

        final int playerInventoryStartX = 8;
        final int playerInventoryStartY = 111;
        final int slotSizePlus2 = 18;

        for(int row = 0; row < 3; ++row){
            for(int column = 0; column < 9; ++column){
                this.addSlot(new Slot(playerInventory, 9 + (row * 9) + column, playerInventoryStartX + (column * slotSizePlus2), playerInventoryStartY + (row * slotSizePlus2)));
            }
        }

        final int playerHotbarY = playerInventoryStartY + slotSizePlus2 * 3 + 4;
        for(int column = 0;column < 9; ++column){
            this.addSlot(new Slot(playerInventory, column, playerInventoryStartX + (column * slotSizePlus2), playerHotbarY));
        }
    }

    private static MicroScopeTileEntity getTileEntity(final PlayerInventory playerInventory, final PacketBuffer data){
        Objects.requireNonNull(playerInventory, "playerInventory cannot be null");
        Objects.requireNonNull(data, "data cannot be null");
        final TileEntity tileAtPos = playerInventory.player.level.getBlockEntity(data.readBlockPos());
        if(tileAtPos instanceof MicroScopeTileEntity) return (MicroScopeTileEntity)tileAtPos;
        throw new IllegalStateException("Tile entity is not correct" + tileAtPos);
    }

    @Nonnull
    @Override
    public ItemStack quickMoveStack(PlayerEntity player, int index) {
        ItemStack returnStack = ItemStack.EMPTY;
        final Slot slot = this.slots.get(index);
        if(slot != null && slot.hasItem()){
            final ItemStack slotStack = slot.getItem();
            returnStack = slotStack.copy();

            final int containerSlots = this.slots.size() - player.inventory.items.size();
            if(index < containerSlots){
                if(!moveItemStackTo(slotStack, containerSlots, this.slots.size(), true)){
                    return ItemStack.EMPTY;
                }
            }else if(!moveItemStackTo(slotStack, 0, containerSlots, false)){

                return ItemStack.EMPTY;
            }

            if(slotStack.getCount() == 0){

                slot.set(ItemStack.EMPTY);
            }else{
                slot.setChanged();
            }

            if(slotStack.getCount() == returnStack.getCount()){
                return ItemStack.EMPTY;
            }

            slot.onTake(player, slotStack);
        }
        return returnStack;
    }

    @Override
    public boolean stillValid(@Nonnull final PlayerEntity player) {
        return stillValid(canInteractWithCallable, player, JPBlocks.MICRO_SCOPE.get());
    }
}
