package jp.tdn.japanese_food_mod.init;

import com.google.common.collect.Lists;
import jp.tdn.japanese_food_mod.JapaneseFoodItemGroup;
import jp.tdn.japanese_food_mod.JapaneseFoodMod;
import jp.tdn.japanese_food_mod.items.*;
import net.minecraft.data.loot.FishingLootTables;
import net.minecraft.entity.EntityType;
import net.minecraft.item.*;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import javax.annotation.Nonnull;
import java.util.List;

@Mod.EventBusSubscriber(modid = JapaneseFoodMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class JPItems {
    private static String modId = JapaneseFoodMod.MOD_ID;
    public static final ItemGroup ItemGroup_Japanese = new JapaneseFoodItemGroup();
    public static final List<Item> itemlist = Lists.newArrayList();

    //Tool
    public static final Item POT = register(new CookWareItem(), "pot");
    public static final Item GRATER = register(new CookWareItem(), "grater");
    public static final Item CLAY_POT = register(new CookWareItem(), "clay_pot");
    public static final Item JAPANESE_KNIFE = register(new CookWareItem(), "japanese_knife");

    // Misc
    public static final Item TYAWAN = register(new SimpleItem(), "tyawan");
    public static final Item COOKING_OIL = register(new SimpleItem(), "cooking_oil");
    public static final Item BREAD_CRUMBS = register(new SimpleItem(), "bread_crumbs");
    public static final Item WHEAT_FLOUR = register(new SimpleItem(), "wheat_flour");
    public static final Item POTATO_STARCH = register(new SimpleItem(), "potato_starch");
    public static final Item SALT = register(new SimpleItem(), "salt");
    public static final Item ROCK_SALT = register(new SimpleItem(), "rock_salt");
    public static final Item YEAST_CELL = register(new SimpleItem(), "yeast_cell");
    public static final Item MISO = register(new Item(new Item.Properties().group(ItemGroup_Japanese).food(new Food.Builder().hunger(1).saturation(0.5f).build())), "miso");
    public static final Item WAKAME = register(new Item(new Item.Properties().group(ItemGroup_Japanese).food(new Food.Builder().hunger(1).saturation(0.5f).fastToEat().build())), "wakame");
    public static final Item SOY_SAUCE = register(new Item(new Item.Properties().group(ItemGroup_Japanese).food(new Food.Builder().hunger(2).saturation(0.0f).effect(new EffectInstance(Effects.POISON, 100, 2), 1.0f).effect(new EffectInstance(Effects.NAUSEA, 100, 1), 1.0f).build())), "soy_sauce");
    public static final Item BATTER = register(new SimpleItem(), "batter");
    public static final Item CLAY_POT_BEFORE_HEATING = register(new SimpleItem(), "clay_pot_before_heating");

    //Seed
    public static final Item SOY_BEANS = register(new BlockItem(JPBlocks.SOY_PLANT, new Item.Properties().group(ItemGroup_Japanese).food(new Food.Builder().hunger(1).saturation(0.5f).build())), "soy_beans");
    public static final Item RICE_SEEDLING = register(new RiceSeedlingItem(JPBlocks.RICE_PLANT, new Item.Properties().group(ItemGroup_Japanese)),"rice_seedling");

    // Food
    public static final Item RADISH_SPROUT = register(new BlockItem(JPBlocks.RADISH_SPROUT_PLANT, new Item.Properties().group(ItemGroup_Japanese).food(new Food.Builder().hunger(1).saturation(0.5f).build())), "radish_sprout");
    public static final Item RICE = register(new Item(new Item.Properties().group(ItemGroup_Japanese).food(new Food.Builder().hunger(1).saturation(0.5f).build())), "rice");
    public static final Item BOILED_SOY_BEANS = register(new Item(new Item.Properties().group(ItemGroup_Japanese).food(new Food.Builder().hunger(1).saturation(0.5f).build())), "boiled_soy_beans");
    public static final Item MISO_SOUP = register(new MisoSoupItem(), "miso_soup");
    public static final Item OMELET = register(new Item(new Item.Properties().group(ItemGroup_Japanese).food(new Food.Builder().hunger(4).saturation(2.5f).build())), "omelet");
    public static final Item ONIGIRI = register(new Item(new Item.Properties().group(ItemGroup_Japanese).food(new Food.Builder().hunger(3).saturation(4.0f).build())), "onigiri");
    public static final Item SALMON_ONIGIRI  = register(new Item(new Item.Properties().group(ItemGroup_Japanese).food(new Food.Builder().hunger(4).saturation(4.5f).build())), "salmon_onigiri");
    public static final Item PICKLED_RADISH = register(new Item(new Item.Properties().group(ItemGroup_Japanese).food(new Food.Builder().hunger(2).saturation(1.5f).build())), "pickled_radish");
    public static final Item FRIED_CHICKEN = register(new Item(new Item.Properties().group(ItemGroup_Japanese).food(new Food.Builder().hunger(5).saturation(4.0f).build())), "fried_chicken");
    public static final Item SKEWERED_CHICKEN = register(new Item(new Item.Properties().group(ItemGroup_Japanese).food(new Food.Builder().hunger(1).saturation(0.5f).effect(new EffectInstance(Effects.HUNGER, 100, 1), 0.3f).build())), "skewered_chicken");
    public static final Item YAKITORI = register(new FoodItem(3, 2.0f), "yakitori");
    public static final Item SQUID_RING = register(new FoodItem(3, 2.0f), "squid_ring");
    public static final Item SAKE = register(new Item(new Item.Properties().group(ItemGroup_Japanese).food(new Food.Builder().hunger(2).saturation(1.0f).effect(new EffectInstance(Effects.NAUSEA, 200, 1), 0.75f).build())), "sake");
    public static final Item TEMPURA_SHRIMP = register(new FoodItem(3, 2.5f), "tempura_shrimp");
    public static final Item FUGU_SASHIMI = register(new FoodItem(4, 4.0f), "fugu_sashimi");
    public static final Item BOILED_EGG = register(new FoodItem(3, 3.0f), "boiled_egg");

    // Fish
    public static final Item SQUID = register(new FoodItem(2, 1.5f), "squid");
    public static final Item SHRIMP = register(new FoodItem(2, 1.5f), "shrimp");
    public static final Item JAPANESE_PUFFER_FISH = register(new Item(new Item.Properties().group(ItemGroup_Japanese).food(new Food.Builder().hunger(2).saturation(1.5f).effect(new EffectInstance(Effects.POISON, 1000, 3), 1.0f).build())), "japanese_puffer_fish");
    public static final Item EEL = register(new Item(new Item.Properties().group(ItemGroup_Japanese).food(new Food.Builder().hunger(2).saturation(1.5f).effect(new EffectInstance(Effects.POISON, 500, 2), 0.2f).build())), "eel");
    public static final Item CRAB = register(new FoodItem(2, 1.5f), "crab");

    // Block Items
    public static final Item ROCK_SALT_BLOCK = register(new BlockItem(JPBlocks.ROCK_SALT_BLOCK, new Item.Properties().group(ItemGroup_Japanese)), "rock_salt_block");
    public static final Item UNREFINED_SAKE = register(new BlockItem(JPBlocks.UNREFINED_SAKE, new Item.Properties().group(ItemGroup_Japanese)), "unrefined_sake");
    public static final Item UNREFINED_SOY_SAUCE = register(new BlockItem(JPBlocks.UNREFINED_SOY_SAUCE, new Item.Properties().group(ItemGroup_Japanese)), "unrefined_soy_sauce");
    public static final Item WOODEN_BUCKET = register(new BlockItem(JPBlocks.WOODEN_BUCKET, new Item.Properties().group(ItemGroup_Japanese)), "wooden_bucket");
    public static final Item MICROSCOPE = register(new BlockItem(JPBlocks.MICRO_SCOPE, new Item.Properties().group(ItemGroup_Japanese)), "microscope");
    public static final Item PRESSER = register(new BlockItem(JPBlocks.PRESSER, new Item.Properties().group(ItemGroup_Japanese)), "presser");

    // Spawn Eggs
    public static final Item EEL_ENTITY_EGG = register(new SpawnEggItem(JPEntities.EEL, 0x00c3ff, 0x694b3a, new Item.Properties().group(ItemGroup_Japanese)), "eel_spawn_egg");
    public static final Item CRAB_ENTITY_EGG = register(new SpawnEggItem(JPEntities.CRAB, 0x00c3ff, 0xba2d20, new Item.Properties().group(ItemGroup_Japanese)), "crab_spawn_egg");

    public static Item register(@Nonnull Item item, @Nonnull String name){
        item.setRegistryName(modId, name);
        itemlist.add(item);
        return item;
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event){
        JapaneseFoodMod.LOGGER.info("Register items...");
        for(Item item : itemlist){
            JapaneseFoodMod.LOGGER.info(item.getRegistryName());
            event.getRegistry().register(item);
        }
    }
}