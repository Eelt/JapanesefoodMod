package jp.tdn.japanese_food_mod.init;

import jp.tdn.japanese_food_mod.JapaneseFoodMod;
import jp.tdn.japanese_food_mod.blocks.*;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nonnull;
import java.util.function.Supplier;

public class JPBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, JapaneseFoodMod.MOD_ID);

    public static final RegistryObject<Block> SOY_PLANT = register(() -> new SoyPlantBlock(AbstractBlock.Properties.of(Material.PLANT).noCollission().randomTicks().sound(SoundType.CROP)), "soy_plant");
    public static final RegistryObject<Block> ROCK_SALT_BLOCK = register(() -> new RockSaltBlock(AbstractBlock.Properties.of(Material.STONE, MaterialColor.COLOR_PINK).harvestTool(ToolType.PICKAXE).strength(2.5f)), "rock_salt_block");
    public static final RegistryObject<Block> RICE_PLANT = register(RicePlantBlock::new, "rice_plant");
    public static final RegistryObject<Block> WOODEN_BUCKET = register(WoodenBucketBlock::new, "wooden_bucket");
    public static final RegistryObject<Block> MICRO_SCOPE = register(MicroScopeBlock::new, "microscope");
    public static final RegistryObject<Block> PRESSER = register(PresserBlock::new, "presser");
    public static final RegistryObject<Block> UNREFINED_SOY_SAUCE = register(UnrefinedSoySauceBlock::new, "unrefined_soy_sauce");
    public static final RegistryObject<Block> RADISH_SPROUT_PLANT = register(RadishSproutPlantBlock::new, "radish_sprout_plant");
    public static final RegistryObject<Block> UNREFINED_SAKE = register(UnrefinedSakeBlock::new, "unrefined_sake");
    public static final RegistryObject<Block> CROP_GRASS = register(CropGrassBlock::new, "crop_grass");
    public static final RegistryObject<Block> LEEK_PLANT = register(LeekPlantBlock::new, "leek_plant");
    public static final RegistryObject<Block> OYSTER_SHELL = register(OysterShellBlock::new, "oyster_shell");
    public static final RegistryObject<Block> UNREFINED_MIRIN = register(UnrefinedMirinBlock::new, "unrefined_mirin");
    public static final RegistryObject<Block> TRONA_ORE = register(() -> new OreBlock(Block.Properties.of(Material.STONE).strength(3.0F, 3.0F)), "trona_ore");
    public static final RegistryObject<Block> FURNACE_CAULDRON = register(FurnaceCauldronBlock::new, "furnace_cauldron");
    public static final RegistryObject<Block> SOY_HAY = register(SoyHayBlock::new, "soy_hay");
    public static final RegistryObject<Block> AZUKI_PLANT = register(() -> new SoyPlantBlock(AbstractBlock.Properties.of(Material.PLANT).noCollission().randomTicks().sound(SoundType.CROP)), "azuki_plant");
    public static final RegistryObject<Block> WAKAME_BLOCK = register(() -> new TallSeaGrassBlock(AbstractBlock.Properties.of(Material.GRASS, MaterialColor.COLOR_BROWN).strength(0).noCollission().sound(SoundType.WET_GRASS)), "wakame_block");

    public static RegistryObject<Block> register(@Nonnull Supplier<Block> block, @Nonnull String name){
        return BLOCKS.register(name, block);
    }
}
