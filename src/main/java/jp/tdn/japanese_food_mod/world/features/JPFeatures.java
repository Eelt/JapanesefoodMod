package jp.tdn.japanese_food_mod.world.features;

import jp.tdn.japanese_food_mod.init.JPBlocks;
import net.minecraft.world.gen.blockplacer.SimpleBlockPlacer;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.placement.ChanceConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.placement.TopSolidRangeConfig;

public class JPFeatures {
    //public static final Feature<NoFeatureConfig> OYSTER = new OysterFeature(NoFeatureConfig.field_236558_a_);

    public static final ConfiguredFeature<?, ?> OYSTER_FEATURE = OysterFeature.OYSTER_INSTANCE
            .configured(new BlockStateFeatureConfig(JPBlocks.OYSTER_SHELL.get().defaultBlockState()))
            .decorated(Placement.CHANCE.configured(new ChanceConfig(15))); // TODO: Config for Oyster Chance

    public static final ConfiguredFeature<?, ?> WAKAME_FEATURE = WakameFeature.WAKAME_INSTANCE
            .configured(new BlockStateFeatureConfig(JPBlocks.WAKAME_BLOCK.get().defaultBlockState()))
            .decorated(Placement.CHANCE.configured(new ChanceConfig(15))); // TODO: Config for Oyster Chance

    public static final ConfiguredFeature<?, ?> GRASS_CROP_FEATURE = Feature.RANDOM_PATCH
            .configured((new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(JPBlocks.CROP_GRASS.get().defaultBlockState()), SimpleBlockPlacer.INSTANCE)).tries(32).build())
            .decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE)
            .count(5); // TODO: Config for GRASS_CROP

    // ORES
    public static final ConfiguredFeature<?, ?> ROCK_SALT_BLOCK_ORE = Feature.ORE
            .configured(new OreFeatureConfig(
                    OreFeatureConfig.FillerBlockType.NATURAL_STONE,
                    JPBlocks.ROCK_SALT_BLOCK.get().defaultBlockState(),
                    8))
            .decorated(Placement.RANGE.configured(new TopSolidRangeConfig(14,0,64)))
            .squared()
            .count(8);

    public static final ConfiguredFeature<?, ?> TRONA_ORE = Feature.ORE
            .configured(new OreFeatureConfig(
                    OreFeatureConfig.FillerBlockType.NATURAL_STONE,
                    JPBlocks.TRONA_ORE.get().defaultBlockState(),
                    8))
            .decorated(Placement.RANGE.configured(new TopSolidRangeConfig(4,0,32)))
            .squared()
            .count(8);

}
