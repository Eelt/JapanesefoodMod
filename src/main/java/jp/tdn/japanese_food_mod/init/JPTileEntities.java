package jp.tdn.japanese_food_mod.init;

import com.google.common.base.Preconditions;
import jp.tdn.japanese_food_mod.JapaneseFoodMod;
import jp.tdn.japanese_food_mod.JapaneseFoodUtil;
import jp.tdn.japanese_food_mod.blocks.tileentity.*;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistryEntry;
import net.minecraftforge.registries.ObjectHolder;

import javax.annotation.Nonnull;

@ObjectHolder(JapaneseFoodMod.MOD_ID)
@Mod.EventBusSubscriber(modid = JapaneseFoodMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class JPTileEntities {
     public static final TileEntityType<MicroScopeTileEntity> MICROSCOPE = JapaneseFoodUtil._null();
     public static final TileEntityType<WoodenBucketTileEntity> WOODEN_BUCKET = JapaneseFoodUtil._null();
     public static final TileEntityType<PresserTileEntity> PRESSER = JapaneseFoodUtil._null();
     public static final TileEntityType<UnrefinedSoySauceTileEntity> UNREFINED_SOY_SAUCE = JapaneseFoodUtil._null();
     public static final TileEntityType<UnrefinedSakeTileEntity> UNREFINED_SAKE = JapaneseFoodUtil._null();
     public static final TileEntityType<OysterShellTileEntity> OYSTER_SHELL = JapaneseFoodUtil._null();
     public static final TileEntityType<UnrefinedMirinTileEntity> UNREFINED_MIRIN = JapaneseFoodUtil._null();
     public static final TileEntityType<FurnaceCauldronTileEntity> FURNACE_CAULDRON = JapaneseFoodUtil._null();
     public static final TileEntityType<SoyHayTileEntity> SOY_HAY = JapaneseFoodUtil._null();

     @SubscribeEvent
     public static void registerTileEntityTypes(@Nonnull final RegistryEvent.Register<TileEntityType<?>> event){
          event.getRegistry().registerAll(
                  setup(TileEntityType.Builder.of(MicroScopeTileEntity::new, JPBlocks.MICRO_SCOPE.get()).build(null), "microscope"),
                  setup(TileEntityType.Builder.of(WoodenBucketTileEntity::new, JPBlocks.WOODEN_BUCKET.get()).build(null), "wooden_bucket"),
                  setup(TileEntityType.Builder.of(PresserTileEntity::new, JPBlocks.PRESSER.get()).build(null), "presser"),
                  setup(TileEntityType.Builder.of(UnrefinedSoySauceTileEntity::new, JPBlocks.UNREFINED_SOY_SAUCE.get()).build(null), "unrefined_soy_sauce"),
                  setup(TileEntityType.Builder.of(UnrefinedSakeTileEntity::new, JPBlocks.UNREFINED_SAKE.get()).build(null), "unrefined_sake"),
                  setup(TileEntityType.Builder.of(OysterShellTileEntity::new, JPBlocks.OYSTER_SHELL.get()).build(null), "oyster_shell"),
                  setup(TileEntityType.Builder.of(UnrefinedMirinTileEntity::new, JPBlocks.UNREFINED_MIRIN.get()).build(null), "unrefined_mirin"),
                  setup(TileEntityType.Builder.of(FurnaceCauldronTileEntity::new, JPBlocks.FURNACE_CAULDRON.get()).build(null), "furnace_cauldron"),
                  setup(TileEntityType.Builder.of(SoyHayTileEntity::new, JPBlocks.SOY_HAY.get()).build(null), "soy_hay")
          );
     }

     @Nonnull
     private static <T extends IForgeRegistryEntry<T>> T setup(@Nonnull final T entry, @Nonnull final String name){
          Preconditions.checkNotNull(name, "Name to assign to entry cannot be null");
          return setup(entry, new ResourceLocation(JapaneseFoodMod.MOD_ID, name));
     }

     @Nonnull
     private static <T extends IForgeRegistryEntry<T>> T setup(@Nonnull final T entry, @Nonnull final ResourceLocation registryName) {
          Preconditions.checkNotNull(entry, "Entry cannot be null!");
          Preconditions.checkNotNull(registryName, "Registry name to assign to entry cannot be null!");
          entry.setRegistryName(registryName);
          return entry;
     }
}
