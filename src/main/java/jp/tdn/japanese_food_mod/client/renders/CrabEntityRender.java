package jp.tdn.japanese_food_mod.client.renders;

import jp.tdn.japanese_food_mod.JapaneseFoodMod;
import jp.tdn.japanese_food_mod.client.models.CrabEntityModel;
import jp.tdn.japanese_food_mod.entities.CrabEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

@OnlyIn(Dist.CLIENT)
public class CrabEntityRender extends MobRenderer<CrabEntity, CrabEntityModel<CrabEntity>> {
    public CrabEntityRender(EntityRendererManager manager){
        super(manager, new CrabEntityModel<CrabEntity>(), 0f);
    }

    @Nullable
    @Override
    public ResourceLocation getTextureLocation(@Nonnull CrabEntity crabEntity) {
        return new ResourceLocation(JapaneseFoodMod.MOD_ID + ":textures/entities/crab.png");
    }
}
