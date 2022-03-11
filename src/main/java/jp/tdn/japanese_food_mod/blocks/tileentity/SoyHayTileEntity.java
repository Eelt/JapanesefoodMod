package jp.tdn.japanese_food_mod.blocks.tileentity;

import jp.tdn.japanese_food_mod.blocks.SoyHayBlock;
import jp.tdn.japanese_food_mod.init.JPTileEntities;
import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;

import javax.annotation.Nonnull;

public class SoyHayTileEntity extends TileEntity implements ITickableTileEntity {
    public static final String FERMENT_LEFT_TIME_TAG = "fermentLeftTime";
    private short fermentLeftTime = 6000;

    public SoyHayTileEntity(){
        super(JPTileEntities.SOY_HAY);
    }

    public short getFermentLeftTime(){
        return this.fermentLeftTime;
    }

    @Override
    public void tick() {
        if(level == null || level.isClientSide())return;

        if(getFermentLeftTime() > 0){
            --fermentLeftTime;
        }else{
            level.setBlockAndUpdate(getBlockPos(), level.getBlockState(worldPosition).setValue(SoyHayBlock.COMPLETION, true));
        }
    }

    @Override
    public void load(BlockState state, CompoundNBT compound) {
        super.load(state, compound);
        this.fermentLeftTime = compound.getShort("fermentLeftTime");
    }

    @Override
    @Nonnull
    public CompoundNBT save(CompoundNBT compound) {
        compound.putShort(FERMENT_LEFT_TIME_TAG, fermentLeftTime);
        return super.save(compound);
    }

    @Nonnull
    public CompoundNBT getUpdateTag(){
        return this.save(new CompoundNBT());
    }

}
