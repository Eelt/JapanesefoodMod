package jp.tdn.japanese_food_mod.world.features;

import com.mojang.serialization.Codec;
import jp.tdn.japanese_food_mod.JapaneseFoodUtil;
import jp.tdn.japanese_food_mod.init.JPBlocks;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.BlockStateFeatureConfig;
import net.minecraft.world.gen.feature.Feature;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Random;

public class OysterFeature extends Feature<BlockStateFeatureConfig> {
    public static OysterFeature OYSTER_INSTANCE = new OysterFeature(BlockStateFeatureConfig.CODEC);


    public OysterFeature(Codec<BlockStateFeatureConfig> deserializer) {
        super(deserializer);
    }

    @Override
    @ParametersAreNonnullByDefault
    public boolean place(ISeedReader worldIn, ChunkGenerator generator, Random rand, BlockPos pos, BlockStateFeatureConfig config) {
        if (worldIn.getBlockState(pos).getBlock() == Blocks.WATER && worldIn.getBlockState(pos.below()).getBlock() != Blocks.WATER) {
            worldIn.setBlock(pos, JPBlocks.OYSTER_SHELL.get().defaultBlockState().rotate(worldIn.getLevel(), pos, JapaneseFoodUtil.rotations.get(rand.nextInt(4))), 1);
            return true;
        }
        return false;
    }
}
