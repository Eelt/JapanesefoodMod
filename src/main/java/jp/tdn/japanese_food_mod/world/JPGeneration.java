package jp.tdn.japanese_food_mod.world;

import jp.tdn.japanese_food_mod.config.OystergenConfig;
import jp.tdn.japanese_food_mod.world.features.JPFeatures;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.gen.GenerationStage;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.registries.ForgeRegistries;

public class JPGeneration {

    public static void setup(BiomeLoadingEvent event){
        setupGeneration(event);
    }

    public static void setupGeneration(BiomeLoadingEvent event){
        for(Biome biome: ForgeRegistries.BIOMES){

            // WARM, LUKEWARM and REGULAR OCEANS ONLY
            if(biome.equals(Biomes.OCEAN) || biome.equals(Biomes.LUKEWARM_OCEAN) || biome.equals(Biomes.WARM_OCEAN)){ // TODO: May not work
                if(OystergenConfig.generate_overworld.get()){ // TODO: Config allow separate toggle Wakame
                    event.getGeneration().addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, JPFeatures.WAKAME_FEATURE);
                    event.getGeneration().addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, JPFeatures.OYSTER_FEATURE);
                }
            }

            if(event.getCategory().equals(Biome.Category.PLAINS)) {
                event.getGeneration().addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, JPFeatures.GRASS_CROP_FEATURE);
            }

           // Oregen
            event.getGeneration().addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, JPFeatures.ROCK_SALT_BLOCK_ORE);
            event.getGeneration().addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, JPFeatures.TRONA_ORE);

        }

    }
}
