package jp.tdn.japanese_food_mod.blocks.tileentity;

import jp.tdn.japanese_food_mod.init.JPTileEntities;
import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tags.FluidTags;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;

import javax.annotation.Nonnull;

public class OysterShellTileEntity extends TileEntity implements ITickableTileEntity {
    public static final String NORI_REMAINING_TAG = "noriRemaining";
    public static final String ELAPSED_TIME_TAG = "elapsedTime";
    private short noriRemaining = -1;
    private short elapsedTime = -1;
    private short maxNori = 10;
    private short timeToGrow = 1000;

    public OysterShellTileEntity(){
        super(JPTileEntities.OYSTER_SHELL);
    }

    public void useNori(){
        if(getnoriRemaining() >= 0)
            --noriRemaining;
    }

    private void growNori(){
        ++noriRemaining;
    }

    public short getnoriRemaining(){
        return this.noriRemaining;
    }

    public boolean isEmpty(){
        return noriRemaining < 0;
    }

    @Override
    public void tick() {
        if(level == null || level.isClientSide()) return;
        if(level.getFluidState(worldPosition).is(FluidTags.WATER) && getnoriRemaining() < maxNori){
            if(++elapsedTime > timeToGrow){
                elapsedTime = -1;
                growNori();
            }
        }
    }

    @Override
    public void load(BlockState state, CompoundNBT compound) {
        super.load(state, compound);
        this.noriRemaining = compound.getShort("noriRemaining");
        this.elapsedTime = compound.getShort("elapsedTime");
    }

    @Override
    @Nonnull
    public CompoundNBT save(CompoundNBT compound) {
        compound.putShort(NORI_REMAINING_TAG, noriRemaining);
        compound.putShort(ELAPSED_TIME_TAG, elapsedTime);
        return super.save(compound);
    }

    @Nonnull
    public CompoundNBT getUpdateTag(){
        return this.save(new CompoundNBT());
    }
}
