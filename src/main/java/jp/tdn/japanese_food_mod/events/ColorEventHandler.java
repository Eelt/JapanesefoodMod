package jp.tdn.japanese_food_mod.events;

import jp.tdn.japanese_food_mod.JapaneseFoodMod;
import jp.tdn.japanese_food_mod.init.JPBlocks;
import jp.tdn.japanese_food_mod.init.JPItems;
import net.minecraft.world.biome.BiomeColors;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = JapaneseFoodMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ColorEventHandler{

    @SubscribeEvent
    public static void registerBlockColors(ColorHandlerEvent.Block event){
        event.getBlockColors().register((blockState, iEnviromentBlockReader, blockPos, i) -> 0xffb4935d, JPBlocks.PRESSER.get());
        event.getBlockColors().register((blockState, iEnviromentBlockReader, blockPos, i) -> 0xff2D8C00, JPBlocks.CROP_GRASS.get());
        event.getBlockColors().register((blockState, iLightReader, blockPos, i) -> BiomeColors.getAverageWaterColor(iLightReader, blockPos), JPBlocks.FURNACE_CAULDRON.get());
    }

    @SubscribeEvent
    public static void registerItemColors(ColorHandlerEvent.Item event){
        event.getItemColors().register((itemStack, i) -> 0xff2D8C00, JPItems.CROP_GRASS.get());
    }
}
