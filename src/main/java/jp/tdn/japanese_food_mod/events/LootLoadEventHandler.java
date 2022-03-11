package jp.tdn.japanese_food_mod.events;

import jp.tdn.japanese_food_mod.JapaneseFoodMod;
import jp.tdn.japanese_food_mod.config.FishingConfig;
import net.minecraft.loot.LootEntry;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.TableLootEntry;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import org.codehaus.plexus.util.PropertyUtils;

import java.lang.reflect.Field;
import java.util.List;

@Mod.EventBusSubscriber(modid = JapaneseFoodMod.MOD_ID)
public class LootLoadEventHandler {
    //private static ResourceLocation grass = new ResourceLocation("minecraft", "blocks/grass");
    private static ResourceLocation sea_grass = new ResourceLocation("minecraft", "blocks/seagrass");
    private static ResourceLocation squid = new ResourceLocation("minecraft", "entities/squid");
    private static ResourceLocation pig = new ResourceLocation("minecraft", "entities/pig");
    //private static ResourceLocation bamboo = new ResourceLocation("minecraft", "blocks/bamboo_sapling");
    private static ResourceLocation fish = new ResourceLocation("minecraft", "gameplay/fishing");

    @SubscribeEvent
    public static void onLootLoad(LootTableLoadEvent event){
//        if(event.getName().equals(grass)){
//            event.getTable().addPool(LootPool.builder().addEntry(TableLootEntry.builder(new ResourceLocation(JapaneseFoodMod.MOD_ID, "blocks/grass"))).build());
//        }

        //JapaneseFoodMod.LOGGER.info(event.getName());
        if(event.getName().equals(sea_grass)){
            event.getTable().addPool(LootPool.lootPool().add(TableLootEntry.lootTableReference(new ResourceLocation(JapaneseFoodMod.MOD_ID, "blocks/seagrass"))).build());
        }

        if(event.getName().equals(squid)){
            event.getTable().addPool(LootPool.lootPool().add(TableLootEntry.lootTableReference(new ResourceLocation(JapaneseFoodMod.MOD_ID, "entities/squid"))).build());
        }

        if(event.getName().equals(pig)){
            event.getTable().addPool(LootPool.lootPool().add(TableLootEntry.lootTableReference(new ResourceLocation(JapaneseFoodMod.MOD_ID, "entities/pig"))).build());
        }

        if(FishingConfig.fishing_overworld.get() && event.getName().equals(fish)){
            //event.getTable().removePool("main");
            LootPool pool = event.getTable().getPool("main");
            addEntry(pool, getInjectEntry(new ResourceLocation(JapaneseFoodMod.MOD_ID, "gameplay/fishing/fish"), 85, -1));
            //event.getTable().addPool(LootPool.builder().addEntry(TableLootEntry.builder(new ResourceLocation(JapaneseFoodMod.MOD_ID, "gameplay/fishing"))).build());
        }
    }

    private static LootEntry getInjectEntry(ResourceLocation location, int weight, int quality) {
        return TableLootEntry.lootTableReference(location).setWeight(weight).setQuality(quality).build();
    }

    private static void addEntry(LootPool pool, LootEntry entry) {
        try {
            List<LootEntry> targets = ObfuscationReflectionHelper.getPrivateValue(LootPool.class, pool, "field_186453_a");
            if(targets != null) {
                if (targets.stream().anyMatch(e -> e == entry)) {
                    throw new RuntimeException("Attempted to add a duplicate entry to pool: " + entry);
                }
                targets.add(entry);
            }
        } catch (Exception e){
            JapaneseFoodMod.LOGGER.info("No such field.");
        }
    }
}
