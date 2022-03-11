package jp.tdn.japanese_food_mod.client.renders;

import jp.tdn.japanese_food_mod.JapaneseFoodMod;
import jp.tdn.japanese_food_mod.client.models.TurbanShellEntityModel;
import jp.tdn.japanese_food_mod.entities.TurbanShellEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

@OnlyIn(Dist.CLIENT)
public class TurbanShellEntityRender extends MobRenderer<TurbanShellEntity, TurbanShellEntityModel<TurbanShellEntity>> {
    public TurbanShellEntityRender(EntityRendererManager manager){
        super(manager, new TurbanShellEntityModel<>(), 0f);
    }

    @Nullable
    @Override
    public ResourceLocation getTextureLocation(@Nonnull TurbanShellEntity clamEntity) {
        return new ResourceLocation(JapaneseFoodMod.MOD_ID + ":textures/entities/turban_shell.png");
    }
}
