package jp.tdn.japanese_food_mod.world.features;

import com.mojang.serialization.Codec;
import jp.tdn.japanese_food_mod.init.JPBlocks;
import net.minecraft.block.Blocks;
import net.minecraft.block.DoublePlantBlock;
import net.minecraft.state.properties.DoubleBlockHalf;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.BlockStateFeatureConfig;
import net.minecraft.world.gen.feature.Feature;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Random;

public class WakameFeature extends Feature<BlockStateFeatureConfig> {
    public static WakameFeature WAKAME_INSTANCE = new WakameFeature(BlockStateFeatureConfig.CODEC);

    public WakameFeature(Codec<BlockStateFeatureConfig> deserializer) {
        super(deserializer);
    }

    @Override
    @ParametersAreNonnullByDefault
    public boolean place(ISeedReader worldIn, ChunkGenerator generator, Random rand, BlockPos pos, BlockStateFeatureConfig config) {
        if (worldIn.getBlockState(pos).getBlock() == Blocks.WATER && worldIn.getBlockState(pos.below()).getBlock() != Blocks.WATER){
            worldIn.setBlock(pos, JPBlocks.WAKAME_BLOCK.get().defaultBlockState(),1);
            worldIn.setBlock(pos.above(), JPBlocks.WAKAME_BLOCK.get().defaultBlockState().setValue(DoublePlantBlock.HALF, DoubleBlockHalf.UPPER),1);
            return true;
        }
        return false;
    }
}
