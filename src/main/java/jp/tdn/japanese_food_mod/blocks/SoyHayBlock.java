package jp.tdn.japanese_food_mod.blocks;

import jp.tdn.japanese_food_mod.init.JPTileEntities;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HayBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Random;

public class SoyHayBlock extends HayBlock {
    public static BooleanProperty COMPLETION = BooleanProperty.create("comp");

    public SoyHayBlock(){
        super(Properties.of(Material.PLANT, MaterialColor.COLOR_RED).strength(0.5F).sound(SoundType.CROP));
        this.registerDefaultState(this.defaultBlockState().setValue(COMPLETION, false).setValue(AXIS, Direction.Axis.Y));
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return JPTileEntities.SOY_HAY.create();
    }

    @Override
    public void animateTick(BlockState state, World world, BlockPos pos, Random random) {
        if(state.getValue(COMPLETION)){
            double posX = (double) pos.getX() + random.nextDouble();
            double posY = (double) pos.getY() + 1.0D;
            double posZ = (double) pos.getZ() + random.nextDouble();
            world.addParticle(ParticleTypes.SMOKE, posX, posY, posZ, 0.0D, 0.0D, 0.0D);
        }
    }

    @Override
    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(COMPLETION);
    }
}
