package jp.tdn.japanese_food_mod.client.renders;

import jp.tdn.japanese_food_mod.JapaneseFoodMod;
import jp.tdn.japanese_food_mod.client.models.AsariClamEntityModel;
import jp.tdn.japanese_food_mod.entities.AsariClamEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

@OnlyIn(Dist.CLIENT)
public class AsariClamEntityRender extends MobRenderer<AsariClamEntity, AsariClamEntityModel<AsariClamEntity>> {
    public AsariClamEntityRender(EntityRendererManager manager){
        super(manager, new AsariClamEntityModel<AsariClamEntity>(), 0f);
    }

    @Nullable
    @Override
    public ResourceLocation getTextureLocation(@Nonnull AsariClamEntity clamEntity) {
        return new ResourceLocation(JapaneseFoodMod.MOD_ID + ":textures/entities/asari_clam.png");
    }
}
