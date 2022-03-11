package jp.tdn.japanese_food_mod.client.renders;

import jp.tdn.japanese_food_mod.client.renders.tileentity.MicroScopeTileEntityRenderer;
import jp.tdn.japanese_food_mod.init.JPBlocks;
import jp.tdn.japanese_food_mod.init.JPEntities;
import jp.tdn.japanese_food_mod.init.JPTileEntities;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

@OnlyIn(Dist.CLIENT)
public class JPRenderRegistry {
    public static void registryEntityRenders(){
        RenderingRegistry.registerEntityRenderingHandler(JPEntities.EEL, EelEntityRender::new);
        RenderingRegistry.registerEntityRenderingHandler(JPEntities.CRAB, CrabEntityRender::new);
        RenderingRegistry.registerEntityRenderingHandler(JPEntities.TUNA, TunaEntityRender::new);
        RenderingRegistry.registerEntityRenderingHandler(JPEntities.CLAM, ClamEntityRender::new);
        RenderingRegistry.registerEntityRenderingHandler(JPEntities.ASARI_CLAM, AsariClamEntityRender::new);
        RenderingRegistry.registerEntityRenderingHandler(JPEntities.TURBAN_SHELL, TurbanShellEntityRender::new);
        RenderingRegistry.registerEntityRenderingHandler(JPEntities.ANGLERFISH, AnglerfishEntityRender::new);
    }

    public static void registryTileEntityRenders(){
        ClientRegistry.bindTileEntityRenderer(JPTileEntities.MICROSCOPE, MicroScopeTileEntityRenderer::new);
    }

    public static void registryRenderLayer(){
        RenderTypeLookup.setRenderLayer(JPBlocks.CROP_GRASS.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(JPBlocks.LEEK_PLANT.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(JPBlocks.RADISH_SPROUT_PLANT.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(JPBlocks.RICE_PLANT.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(JPBlocks.SOY_PLANT.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(JPBlocks.FURNACE_CAULDRON.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(JPBlocks.AZUKI_PLANT.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(JPBlocks.WAKAME_BLOCK.get(), RenderType.cutout());
    }
}
