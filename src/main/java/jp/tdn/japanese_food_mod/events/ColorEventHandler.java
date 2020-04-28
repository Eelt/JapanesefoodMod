package jp.tdn.japanese_food_mod.events;

import jp.tdn.japanese_food_mod.JapaneseFoodMod;
import jp.tdn.japanese_food_mod.init.JPBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IEnviromentBlockReader;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber(modid = JapaneseFoodMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ColorEventHandler{

    @SubscribeEvent
    public static void registerBlockColors(ColorHandlerEvent.Block event){
        event.getBlockColors().register(new IBlockColor() {
            @Override
            public int getColor(BlockState blockState, @Nullable IEnviromentBlockReader iEnviromentBlockReader, @Nullable BlockPos blockPos, int i) {
                return 0xffb4935d;
            }
        }, JPBlocks.PRESSER);
    }
}
