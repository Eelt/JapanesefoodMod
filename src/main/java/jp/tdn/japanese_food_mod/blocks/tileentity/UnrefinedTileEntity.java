package jp.tdn.japanese_food_mod.blocks.tileentity;

import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;

import javax.annotation.Nonnull;

public class UnrefinedTileEntity extends TileEntity{
    public static final String SAUCE_REMAINING_TAG = "sauceRemaining";
    private short sauceRemaining;

    public UnrefinedTileEntity(TileEntityType<?> type, short sauceRemaining){
        super(type);
        this.sauceRemaining = sauceRemaining;
    }

    public void useSauce(){
        --sauceRemaining;
    }

    public short getSauceRemaining(){
        return this.sauceRemaining;
    }

    @Override
    public void load(BlockState state, CompoundNBT compound) {
        super.load(state, compound);
        this.sauceRemaining = compound.getShort("sauceRemaining");
    }

    @Override
    @Nonnull
    public CompoundNBT save(CompoundNBT compound) {
        compound.putShort(SAUCE_REMAINING_TAG, sauceRemaining);
        return super.save(compound);
    }

    @Nonnull
    public CompoundNBT getUpdateTag(){
        return this.save(new CompoundNBT());
    }
}
