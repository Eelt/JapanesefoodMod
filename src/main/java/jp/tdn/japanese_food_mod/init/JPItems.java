package jp.tdn.japanese_food_mod.init;

import jp.tdn.japanese_food_mod.JapaneseFoodItemGroup;
import jp.tdn.japanese_food_mod.JapaneseFoodMod;
import jp.tdn.japanese_food_mod.items.*;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.*;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nonnull;
import java.util.function.Supplier;

@Mod.EventBusSubscriber(modid = JapaneseFoodMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class JPItems {
    private static final String modId = JapaneseFoodMod.MOD_ID;
    public static final ItemGroup ItemGroup_Japanese = new JapaneseFoodItemGroup();
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, JapaneseFoodMod.MOD_ID);

    //Tool
    public static final RegistryObject<Item> POT = register(CookWareItem::new, "pot");
    public static final RegistryObject<Item> GRATER = register(CookWareItem::new, "grater");
    public static final RegistryObject<Item> JAPANESE_KNIFE = register(CookWareItem::new, "japanese_knife");
    public static final RegistryObject<Item> ROLLING_PIN = register(CookWareItem::new, "rolling_pin");
    public static final RegistryObject<Item> PESTLE = register(CookWareItem::new, "pestle");

    // Misc
    public static final RegistryObject<Item> TYAWAN = register(SimpleItem::new, "tyawan");
    public static final RegistryObject<Item> JAPANESE_BOWL = register(SimpleItem::new, "japanese_bowl");
    public static final RegistryObject<Item> CLAY_POT_BEFORE_HEATING = register(SimpleItem::new, "clay_pot_before_heating");
    public static final RegistryObject<Item> CLAY_POT = register(SimpleItem::new, "clay_pot");
    public static final RegistryObject<Item> CUP = register(CupItem::new, "cup");
    public static final RegistryObject<Item> CUP_WITH_WATER = register(() -> new DrinkItem(new Item.Properties().tab(ItemGroup_Japanese).craftRemainder(CUP.get()).food(new Food.Builder().saturationMod(0f).nutrition(0).build())), "cup_with_water");
    public static final RegistryObject<Item> CUP_WITH_MILK = register(() -> new DrinkItem(new Item.Properties().tab(ItemGroup_Japanese).craftRemainder(CUP.get()).food(new Food.Builder().saturationMod(0f).nutrition(0).build())), "cup_with_milk");
    public static final RegistryObject<Item> BREAD_CRUMBS = register(SimpleItem::new, "bread_crumbs");
    public static final RegistryObject<Item> WHEAT_FLOUR = register(SimpleItem::new, "wheat_flour");
    public static final RegistryObject<Item> POTATO_STARCH = register(SimpleItem::new, "potato_starch");
    public static final RegistryObject<Item> RICE_FLOUR = register(SimpleItem::new, "rice_flour");
    public static final RegistryObject<Item> SALT = register(SimpleItem::new, "salt");
    public static final RegistryObject<Item> ROCK_SALT = register(SimpleItem::new, "rock_salt");
    public static final RegistryObject<Item> BAKING_SODA = register(SimpleItem::new, "baking_soda");
    public static final RegistryObject<Item> COOKING_OIL = register(SimpleItem::new, "cooking_oil");
    public static final RegistryObject<Item> MISO = register(() -> new Item(new Item.Properties().tab(ItemGroup_Japanese).food(new Food.Builder().nutrition(1).saturationMod(0.5f).build())), "miso");
    public static final RegistryObject<Item> WAKAME = register(() -> new Item(new Item.Properties().tab(ItemGroup_Japanese).food(new Food.Builder().nutrition(1).saturationMod(0.5f).fast().build())), "wakame");
    public static final RegistryObject<Item> SOY_SAUCE = register(() -> new DrinkItem(new Item.Properties().tab(ItemGroup_Japanese).food(new Food.Builder().nutrition(2).saturationMod(0.0f).effect(new EffectInstance(Effects.POISON, 100, 2), 1.0f).effect(new EffectInstance(Effects.CONFUSION, 100, 1), 1.0f).build())), "soy_sauce");
    public static final RegistryObject<Item> VINEGAR = register(() -> new DrinkItem(1, 0f), "vinegar");
    public static final RegistryObject<Item> MIRIN = register(() -> new DrinkItem(1, 0f), "mirin");
    public static final RegistryObject<Item> BATTER = register(SimpleItem::new, "batter");
    public static final RegistryObject<Item> TARE_SAUCE = register(SimpleItem::new, "tare_sauce");
    public static final RegistryObject<Item> MISO_SAUCE = register(SimpleItem::new, "miso_sauce");
    public static final RegistryObject<Item> BROTH = register(SimpleItem::new, "broth");
    public static final RegistryObject<Item> YEAST_CELL = register(() -> new ContainerItem(Items.GLASS_BOTTLE), "yeast_cell");
    public static final RegistryObject<Item> ACETIC_ACID_BACTERIA = register(() -> new ContainerItem(Items.GLASS_BOTTLE), "acetic_acid_bacteria");
    public static final RegistryObject<Item> PORK_BONE = register(SimpleItem::new, "pork_bone");
    public static final RegistryObject<Item> BITTERN = register(SimpleItem::new, "bittern");
    public static final RegistryObject<Item> SOY_MILK = register(() -> new DrinkItem(new Item.Properties().tab(ItemGroup_Japanese).craftRemainder(Items.GLASS_BOTTLE).food(new Food.Builder().saturationMod(0.5f).nutrition(3).build())), "soy_milk");

    //Seed
    public static final RegistryObject<Item> SOY_BEANS = register(() -> new BlockItem(JPBlocks.SOY_PLANT.get(), new Item.Properties().tab(ItemGroup_Japanese).food(new Food.Builder().nutrition(1).saturationMod(0.5f).build())), "soy_beans");
    public static final RegistryObject<Item> AZUKI_BEANS = register(() -> new BlockItem(JPBlocks.AZUKI_PLANT.get(), new Item.Properties().tab(ItemGroup_Japanese)), "azuki_beans");
    public static final RegistryObject<Item> RICE_SEEDLING = register(() -> new RiceSeedlingItem(JPBlocks.RICE_PLANT.get(), new Item.Properties().tab(ItemGroup_Japanese)),"rice_seedling");
    public static final RegistryObject<Item> RADISH_SPROUT_SEED = register(() -> new BlockItem(JPBlocks.RADISH_SPROUT_PLANT.get(), new Item.Properties().tab(ItemGroup_Japanese)), "radish_sprout_seed");
    public static final RegistryObject<Item> LEEK_SEED = register(() -> new BlockItem(JPBlocks.LEEK_PLANT.get(), new Item.Properties().tab(ItemGroup_Japanese)),"leek_seed");

    // Food
    public static final RegistryObject<Item> EDAMAME = register(() -> new FoodItem(1, 0f), "edamame");
    public static final RegistryObject<Item> RADISH_SPROUT = register(() -> new FoodItem(1, 0.5f), "radish_sprout");
    public static final RegistryObject<Item> RICE = register(() -> new Item(new Item.Properties().tab(ItemGroup_Japanese).food(new Food.Builder().nutrition(1).saturationMod(0.5f).build())), "rice");
    public static final RegistryObject<Item> LEEK = register(() -> new FoodItem(1, 0.5f), "leek");
    public static final RegistryObject<Item> BOILED_SOY_BEANS = register(() -> new Item(new Item.Properties().tab(ItemGroup_Japanese).food(new Food.Builder().nutrition(1).saturationMod(0.5f).build())), "boiled_soy_beans");
    public static final RegistryObject<Item> BOILED_AZUKI_BEANS = register(() -> new FoodItem(1, 0.5f), "boiled_azuki_beans");
    public static final RegistryObject<Item> BOILED_WAKAME = register(() -> new FoodItem(2, 1.0f), "boiled_wakame");
    public static final RegistryObject<Item> MISO_SOUP = register(() -> new MisoSoupItem(3, 2.5f), "miso_soup");
    public static final RegistryObject<Item> OMELET = register(() -> new Item(new Item.Properties().tab(ItemGroup_Japanese).food(new Food.Builder().nutrition(4).saturationMod(2.5f).build())), "omelet");
    public static final RegistryObject<Item> ONIGIRI = register(() -> new Item(new Item.Properties().tab(ItemGroup_Japanese).food(new Food.Builder().nutrition(3).saturationMod(4.0f).build())), "onigiri");
    public static final RegistryObject<Item> SALMON_ONIGIRI  = register(() -> new Item(new Item.Properties().tab(ItemGroup_Japanese).food(new Food.Builder().nutrition(4).saturationMod(4.5f).build())), "salmon_onigiri");
    public static final RegistryObject<Item> NIKUJAGA = register(() -> new FoodItem(7, 2.5f), "nikujaga");
    public static final RegistryObject<Item> AMBERJACK_RADISHES = register(() -> new FoodItem(7, 2.5f), "amberjack_radishes");
    public static final RegistryObject<Item> PICKLED_RADISH = register(() -> new Item(new Item.Properties().tab(ItemGroup_Japanese).food(new Food.Builder().nutrition(2).saturationMod(1.5f).build())), "pickled_radish");
    public static final RegistryObject<Item> FRIED_CHICKEN = register(() -> new Item(new Item.Properties().tab(ItemGroup_Japanese).food(new Food.Builder().nutrition(5).saturationMod(4.0f).build())), "fried_chicken");
    public static final RegistryObject<Item> SKEWERED_CHICKEN = register(() -> new Item(new Item.Properties().tab(ItemGroup_Japanese).food(new Food.Builder().nutrition(1).saturationMod(0.5f).effect(new EffectInstance(Effects.SATURATION, 100, 1), 0.3f).build())), "skewered_chicken");
    public static final RegistryObject<Item> YAKITORI = register(() -> new FoodItem(3, 2.0f), "yakitori");
    public static final RegistryObject<Item> RAW_NEGIMA = register(() -> new Item(new Item.Properties().tab(ItemGroup_Japanese).food(new Food.Builder().nutrition(1).saturationMod(0.5f).effect(new EffectInstance(Effects.SATURATION, 100, 1), 0.3f).build())), "raw_negima");
    public static final RegistryObject<Item> NEGIMA = register(() -> new FoodItem(3, 2.3f), "negima");
    public static final RegistryObject<Item> SQUID_RING = register(() -> new FoodItem(3, 2.0f), "squid_ring");
    public static final RegistryObject<Item> SURUME = register(() -> new FoodItem(3, 2.0f), "surume");
    public static final RegistryObject<Item> SAKE = register(() -> new SakeItem(2, 1.0f), "sake");
    public static final RegistryObject<Item> TEMPURA_SHRIMP = register(() -> new FoodItem(3, 2.5f), "tempura_shrimp");
    public static final RegistryObject<Item> FUGU_SASHIMI = register(() -> new FoodItem(4, 4.0f), "fugu_sashimi");
    public static final RegistryObject<Item> BOILED_EGG = register(() -> new FoodItem(3, 3.0f), "boiled_egg");
    public static final RegistryObject<Item> GRATED_DAIKON = register(() -> new FoodItem(3, 1.5f),"grated_daikon");
    public static final RegistryObject<Item> NORI = register(() -> new FoodItem(1, 0f), "nori");
    public static final RegistryObject<Item> VINEGARED_RICE = register(() -> new FoodItem(3, 2.5f), "vinegared_rice");
    public static final RegistryObject<Item> TUNA_SASHIMI = register(() -> new FoodItem(4, 3.5f), "tuna_sashimi");
    public static final RegistryObject<Item> TUNA_SUSHI = register(() -> new FoodItem(5, 3.5f), "tuna_sushi");
    public static final RegistryObject<Item> SALMON_SUSHI = register(() -> new FoodItem(5, 3.5f), "salmon_sushi");
    public static final RegistryObject<Item> SALMON_ROE = register(() -> new FoodItem(5, 2.5f), "salmon_roe");
    public static final RegistryObject<Item> SHRIMP_SUSHI = register(() -> new FoodItem(4,2.0f), "shrimp_sushi");
    public static final RegistryObject<Item> SQUID_SUSHI = register(() -> new FoodItem(4, 2.0f), "squid_sushi");
    public static final RegistryObject<Item> SEA_BREAM_SUSHI = register(() -> new FoodItem(5, 2.0f), "sea_bream_sushi");
    public static final RegistryObject<Item> TONKATSU = register(() -> new FoodItem(4, 3.0f), "tonkatsu");
    public static final RegistryObject<Item> MISO_TONKATSU = register(() -> new FoodItem(5, 3.0f), "miso_tonkatsu");
    public static final RegistryObject<Item> GRILLED_TURBAN_SHELL = register(() -> new FoodItem(4, 1.5f), "grilled_turban_shell");
    public static final RegistryObject<Item> BAMBOO_SHOOT = register(() -> new FoodItem(2, 0.1f), "bamboo_shoot");
    public static final RegistryObject<Item> GLAZE_GRILLED_EEL = register(() -> new FoodItem(5, 2.5f), "glaze_grilled_eel");
    public static final RegistryObject<Item> EEL_RICE_BOX = register(() -> new FoodItem(7, 3.0f), "eel_rice_box");
    public static final RegistryObject<Item> FISH_PASTE = register(() -> new FoodItem(1, 0.5f), "fish_paste");
    public static final RegistryObject<Item> KAMABOKO = register(() -> new FoodItem(3, 1.0f), "kamaboko");
    public static final RegistryObject<Item> CHIKUWA = register(() -> new FoodItem(3, 1.0f), "chikuwa");
    public static final RegistryObject<Item> HANPEN = register(() -> new FoodItem(3, 1.0f), "hanpen");
    public static final RegistryObject<Item> TSUMIRE = register(() -> new FoodItem(2, 0.5f), "tsumire");
    public static final RegistryObject<Item> MENMA = register(() -> new FoodItem(2, 0.5f), "menma");
    public static final RegistryObject<Item> NOODLES = register(() -> new FoodItem(2, 0.5f), "noodles");
    public static final RegistryObject<Item> SOY_SAUCE_RAMEN_SOUP = register(() -> new DrinkItem(2, 0.5f), "soy_sauce_ramen_soup");
    public static final RegistryObject<Item> PORK_BONE_RAMEN_SOUP = register(() -> new DrinkItem(2, 0.5f), "pork_bone_ramen_soup");
    public static final RegistryObject<Item> SALT_RAMEN_SOUP = register(() -> new DrinkItem(2, 0.5f), "salt_ramen_soup");
    public static final RegistryObject<Item> MISO_RAMEN_SOUP = register(() -> new DrinkItem(2, 0.5f), "miso_ramen_soup");
    public static final RegistryObject<Item> SOY_SAUCE_RAMEN = register(() -> new DrinkItem(8, 5.0f), "soy_sauce_ramen");
    public static final RegistryObject<Item> PORK_BONE_RAMEN = register(() -> new DrinkItem(8, 5.0f), "pork_bone_ramen");
    public static final RegistryObject<Item> SALT_RAMEN = register(() -> new DrinkItem(8, 5.0f), "salt_ramen");
    public static final RegistryObject<Item> MISO_RAMEN = register(() -> new DrinkItem(8, 5.0f), "miso_ramen");
    public static final RegistryObject<Item> TOFU = register(() -> new FoodItem(4, 2.5f), "tofu");
    public static final RegistryObject<Item> NATTO = register(() -> new FoodItem(5, 3.0f), "natto");
    public static final RegistryObject<Item> FRIED_TOFU = register(() -> new FoodItem(2, 1.0f), "fried_tofu");
    public static final RegistryObject<Item> TSUBU_AN = register(() -> new FoodItem(3, 0.5f), "mashed_sweet_bean_paste");
    public static final RegistryObject<Item> IMAGAWA_YAKI = register(() -> new FoodItem(5, 1.0f), "imagawa_yaki");
    public static final RegistryObject<Item> RICE_CAKE = register(() -> new FoodItem(4, 2.5f), "rice_cake");
    public static final RegistryObject<Item> ROASTED_RICE_CAKE = register(() -> new FoodItem(6, 3.5f), "roasted_rice_cake");
    public static final RegistryObject<Item> MANJU = register(() -> new FoodItem(4, 1.5f), "manju");
    public static final RegistryObject<Item> OSHIRUKO = register(() -> new DrinkItem(7, 3.5f), "oshiruko");
    public static final RegistryObject<Item> RICE_CRACKER_DOUGH = register(SimpleItem::new, "rice_cracker_dough");
    public static final RegistryObject<Item> RICE_CRACKER = register(() -> new FoodItem(new Item.Properties().tab(ItemGroup_Japanese).food(new Food.Builder().nutrition(3).saturationMod(0.5f).fast().build())), "rice_cracker");
    public static final RegistryObject<Item> SOY_SAUCE_RICE_CRACKER = register(() -> new FoodItem(new Item.Properties().tab(ItemGroup_Japanese).food(new Food.Builder().nutrition(4).saturationMod(1.0f).fast().build())), "soy_sauce_rice_cracker");
    public static final RegistryObject<Item> JAPANESE_SHAVED_ICE = register(() -> new FoodItem(3, 0.2f), "japanese_shaved_ice");
    public static final RegistryObject<Item> SWEET_BEAN_BUN = register(() -> new FoodItem(6, 2.5f), "sweet_bean_bun");
    public static final RegistryObject<Item> STICKY_RICE_DUMPLING = register(() -> new FoodItem(2, 0.1f), "sticky_rice_dumpling");

    // Fish
    public static final RegistryObject<Item> SQUID = register(() -> new FoodItem(2, 1.5f), "squid");
    public static final RegistryObject<Item> SHRIMP = register(() -> new FoodItem(2, 1.5f), "shrimp");
    public static final RegistryObject<Item> JAPANESE_PUFFER_FISH = register(() -> new Item(new Item.Properties().tab(ItemGroup_Japanese).food(new Food.Builder().nutrition(2).saturationMod(1.5f).effect(new EffectInstance(Effects.POISON, 1000, 3), 1.0f).build())), "japanese_puffer_fish");
    public static final RegistryObject<Item> EEL = register(() -> new Item(new Item.Properties().tab(ItemGroup_Japanese).food(new Food.Builder().nutrition(2).saturationMod(1.5f).effect(new EffectInstance(Effects.POISON, 500, 2), 0.2f).build())), "eel");
    public static final RegistryObject<Item> CRAB = register(() -> new FoodItem(2, 1.5f), "crab");
    public static final RegistryObject<Item> TUNA = register(() -> new FoodItem(3, 2.0f), "tuna");
    public static final RegistryObject<Item> SARDINE = register(() -> new FoodItem(1, 0.5f), "sardine");
    public static final RegistryObject<Item> AMBERJACK = register(() -> new FoodItem(2, 1.5f), "amberjack");
    public static final RegistryObject<Item> ANGLERFISN = register(() -> new FoodItem(2, 1.0f), "anglerfish");
    public static final RegistryObject<Item> SEA_BREAM = register(() -> new FoodItem(2, 0.5f), "sea_bream");
    public static final RegistryObject<Item> MACKEREL = register(() -> new FoodItem(1, 0.5f), "mackerel");
    public static final RegistryObject<Item> EEL_BUCKET = register(() -> new FishBucketItem(JPEntities.EEL, Fluids.WATER, new Item.Properties().stacksTo(1).tab(ItemGroup_Japanese)), "eel_bucket");
    public static final RegistryObject<Item> TUNA_BUCKET = register(() -> new FishBucketItem(JPEntities.TUNA, Fluids.WATER, new Item.Properties().stacksTo(1).tab(ItemGroup_Japanese)), "tuna_bucket");
    public static final RegistryObject<Item> CLAM = register(() -> new FoodItem(1, 0.5f), "clam");
    public static final RegistryObject<Item> ASARI_CLAM = register(() -> new FoodItem(1, 0.1f), "asari_clam");
    public static final RegistryObject<Item> TURBAN_SHELL = register(() -> new FoodItem(2, 0.5f), "turban_shell");

    // Block Items
    public static final RegistryObject<Item> CROP_GRASS = register(() -> new BlockItem(JPBlocks.CROP_GRASS.get(), new Item.Properties().tab(ItemGroup_Japanese)), "crop_grass");
    public static final RegistryObject<Item> WAKAME_BLOCK = register(() -> new BlockItem(JPBlocks.WAKAME_BLOCK.get(), new Item.Properties().tab(ItemGroup_Japanese)), "wakame_block");
    public static final RegistryObject<Item> ROCK_SALT_BLOCK = register(() -> new BlockItem(JPBlocks.ROCK_SALT_BLOCK.get(), new Item.Properties().tab(ItemGroup_Japanese)), "rock_salt_block");
    public static final RegistryObject<Item> TRONA_ORE = register(() -> new BlockItem(JPBlocks.TRONA_ORE.get(), new Item.Properties().tab(ItemGroup_Japanese)), "trona_ore");
    public static final RegistryObject<Item> UNREFINED_SAKE = register(() -> new BlockItem(JPBlocks.UNREFINED_SAKE.get(), new Item.Properties().tab(ItemGroup_Japanese)), "unrefined_sake");
    public static final RegistryObject<Item> UNREFINED_SOY_SAUCE = register(() -> new BlockItem(JPBlocks.UNREFINED_SOY_SAUCE.get(), new Item.Properties().tab(ItemGroup_Japanese)), "unrefined_soy_sauce");
    public static final RegistryObject<Item> UNREFINED_MIRIN = register(() -> new BlockItem(JPBlocks.UNREFINED_MIRIN.get(), new Item.Properties().tab(ItemGroup_Japanese)), "unrefined_mirin");
    public static final RegistryObject<Item> WOODEN_BUCKET = register(() -> new BlockItem(JPBlocks.WOODEN_BUCKET.get(), new Item.Properties().tab(ItemGroup_Japanese)), "wooden_bucket");
    public static final RegistryObject<Item> MICROSCOPE = register(() -> new BlockItem(JPBlocks.MICRO_SCOPE.get(), new Item.Properties().tab(ItemGroup_Japanese)), "microscope");
    public static final RegistryObject<Item> PRESSER = register(() -> new BlockItem(JPBlocks.PRESSER.get(), new Item.Properties().tab(ItemGroup_Japanese)), "presser");
    public static final RegistryObject<Item> FURNACE_CAULDRON = register(() -> new BlockItem(JPBlocks.FURNACE_CAULDRON.get(), new Item.Properties().tab(ItemGroup_Japanese)), "furnace_cauldron");
    public static final RegistryObject<Item> OYSTER_SHELL = register(() -> new BlockItem(JPBlocks.OYSTER_SHELL.get(), new Item.Properties().tab(ItemGroup_Japanese)), "oyster_shell");
    public static final RegistryObject<Item> SOY_HAY = register(() -> new BlockItem(JPBlocks.SOY_HAY.get(), new Item.Properties().tab(ItemGroup_Japanese)), "soy_hay");

    // Spawn Eggs
    public static final RegistryObject<Item> EEL_ENTITY_EGG = register(() -> new SpawnEggItem(JPEntities.EEL, 0x00c3ff, 0x694b3a, new Item.Properties().tab(ItemGroup_Japanese)), "eel_spawn_egg");
    public static final RegistryObject<Item> CRAB_ENTITY_EGG = register(() -> new SpawnEggItem(JPEntities.CRAB, 0x00c3ff, 0xba2d20, new Item.Properties().tab(ItemGroup_Japanese)), "crab_spawn_egg");
    public static final RegistryObject<Item> TUNA_ENTITY_EGG = register(() -> new SpawnEggItem(JPEntities.TUNA, 0x00c3ff, 0x0000ff, new Item.Properties().tab(ItemGroup_Japanese)), "tuna_spawn_egg");
    public static final RegistryObject<Item> CLAM_ENTITY_EGG = register(() -> new SpawnEggItem(JPEntities.CLAM, 0x00c3ff, 0x996136, new Item.Properties().tab(ItemGroup_Japanese)), "clam_spawn_egg");
    public static final RegistryObject<Item> ASARI_CLAM_ENTITY_EGG = register(() -> new SpawnEggItem(JPEntities.ASARI_CLAM, 0x00c3ff, 0x827a51, new Item.Properties().tab(ItemGroup_Japanese)), "asari_clam_spawn_egg");
    public static final RegistryObject<Item> TURBAN_SHELL_ENTITY_EGG = register(() -> new SpawnEggItem(JPEntities.TURBAN_SHELL, 0x00c3ff, 0xfca503, new Item.Properties().tab(ItemGroup_Japanese)), "turban_shell_spawn_egg");
    public static final RegistryObject<Item> ANGLERFISH_ENTITY_EGG = register(() -> new SpawnEggItem(JPEntities.ANGLERFISH, 0x00c3ff, 0xfca000, new Item.Properties().tab(ItemGroup_Japanese)), "anglerfish_spawn_egg");

    public static RegistryObject<Item> register(@Nonnull Supplier<Item> item, @Nonnull String name){
        return ITEMS.register(name, item);
    }
}