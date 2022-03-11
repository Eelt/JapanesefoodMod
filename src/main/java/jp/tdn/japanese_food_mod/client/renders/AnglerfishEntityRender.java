package jp.tdn.japanese_food_mod.client.renders;

import jp.tdn.japanese_food_mod.JapaneseFoodMod;
import jp.tdn.japanese_food_mod.client.models.AnglerfishEntityModel;
import jp.tdn.japanese_food_mod.entities.AnglerfishEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

@OnlyIn(Dist.CLIENT)
public class AnglerfishEntityRender extends MobRenderer<AnglerfishEntity, AnglerfishEntityModel<AnglerfishEntity>> {
    public AnglerfishEntityRender(EntityRendererManager manager){
        super(manager, new AnglerfishEntityModel<AnglerfishEntity>(), 0f);
    }

    @Nullable
    @Override
    public ResourceLocation getTextureLocation(@Nonnull AnglerfishEntity Entity) {
        return new ResourceLocation(JapaneseFoodMod.MOD_ID + ":textures/entities/anglerfish.png");
    }
}
