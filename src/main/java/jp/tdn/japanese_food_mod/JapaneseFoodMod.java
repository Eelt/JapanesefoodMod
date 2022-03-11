package jp.tdn.japanese_food_mod;

import jp.tdn.japanese_food_mod.client.renders.JPRenderRegistry;
import jp.tdn.japanese_food_mod.config.Config;
import jp.tdn.japanese_food_mod.init.JPBlocks;
import jp.tdn.japanese_food_mod.init.JPEntities;
import jp.tdn.japanese_food_mod.init.JPItems;
import jp.tdn.japanese_food_mod.world.JPGeneration;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(JapaneseFoodMod.MOD_ID)
public class JapaneseFoodMod {
    public static final String MOD_ID = "japanese_food_mod";

     // Directly reference a log4j logger.
    public static final Logger LOGGER = LogManager.getLogger();


    public JapaneseFoodMod() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        // Register the setup method for mod-loading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        // Register the doClientStuff method for mod-loading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);

        JPBlocks.BLOCKS.register(modEventBus);
        JPItems.ITEMS.register(modEventBus);
        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);

        MinecraftForge.EVENT_BUS.addListener(EventPriority.HIGH, JPGeneration::setup);
        Config.loadConfig();
    }

    private void setup(final FMLCommonSetupEvent event){
        //JPGeneration.setup();
        JPEntities.registerEntityWorldSpawns();
    }

    private void doClientStuff(final FMLClientSetupEvent event){
        // do something that can only be done on the client
        JPRenderRegistry.registryEntityRenders();
        JPRenderRegistry.registryTileEntityRenders();
        JPRenderRegistry.registryRenderLayer();
        LOGGER.info("Client Setup");
    }
}
