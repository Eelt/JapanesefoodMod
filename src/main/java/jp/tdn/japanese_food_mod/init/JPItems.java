package jp.tdn.japanese_food_mod.init;

import com.google.common.collect.Lists;
import jp.tdn.japanese_food_mod.JapaneseFoodItemGroup;
import jp.tdn.japanese_food_mod.JapaneseFoodMod;
import jp.tdn.japanese_food_mod.items.*;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.EffectType;
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

    // Misc
    public static final Item TYAWAN = register(new SimpleItem(), "tyawan");
    public static final Item POT = register(new CockWareItem(), "pot");
    public static final Item SALT = register(new SimpleItem(), "salt");
    public static final Item ROCK_SALT = register(new SimpleItem(), "rock_salt");
    public static final Item YEAST_CELL = register(new SimpleItem(), "yeast_cell");
    public static final Item COOKING_OIL = register(new SimpleItem(), "cooking_oil");

    // Food
    public static final Item BOILED_SOY_BEANS = register(new Item(new Item.Properties().group(ItemGroup_Japanese).food(new Food.Builder().hunger(1).saturation(0.5f).build())), "boiled_soy_beans");
    public static final Item MISO_SOUP = register(new MisoSoupItem(), "miso_soup");
    public static final Item RICE = register(new Item(new Item.Properties().group(ItemGroup_Japanese).food(new Food.Builder().hunger(1).saturation(0.5f).build())), "rice");
    public static final Item MISO = register(new Item(new Item.Properties().group(ItemGroup_Japanese).food(new Food.Builder().hunger(1).saturation(0.5f).build())), "miso");
    public static final Item WAKAME = register(new Item(new Item.Properties().group(ItemGroup_Japanese).food(new Food.Builder().hunger(1).saturation(0.5f).fastToEat().build())), "wakame");
    public static final Item MISO_SOUP_OF_TOFU_AND_WAKAME = register(new MisoSoupOfTAW(), "miso_soup_of_tofu_and_wakame");
    public static final Item SOY_SAUCE = register(new Item(new Item.Properties().group(ItemGroup_Japanese).food(new Food.Builder().hunger(2).saturation(0.0f).effect(new EffectInstance(Effects.POISON, 100, 2), 1.0f).effect(new EffectInstance(Effects.NAUSEA, 100, 1), 1.0f).build())), "soy_sauce");

    // Block Items
    public static final Item SOY_BEANS = register(new BlockItem(JPBlocks.SOY_PLANT, new Item.Properties().group(ItemGroup_Japanese).food(new Food.Builder().hunger(1).saturation(0.5f).build())), "soy_beans");
    public static final Item ROCK_SALT_BLOCK = register(new BlockItem(JPBlocks.ROCK_SALT_BLOCK, new Item.Properties().group(ItemGroup_Japanese)), "rock_salt_block");
    public static final Item RICE_SEEDLING = register(new RiceSeedlingItem(JPBlocks.RICE_PLANT, new Item.Properties().group(ItemGroup_Japanese)),"rice_seedling");
    public static final Item WOODEN_BUCKET = register(new BlockItem(JPBlocks.WOODEN_BUCKET, new Item.Properties().group(ItemGroup_Japanese)), "wooden_bucket");
    public static final Item MICROSCOPE = register(new BlockItem(JPBlocks.MICRO_SCOPE, new Item.Properties().group(ItemGroup_Japanese)), "microscope");
    public static final Item PRESSER = register(new BlockItem(JPBlocks.PRESSER, new Item.Properties().group(ItemGroup_Japanese)), "presser");

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