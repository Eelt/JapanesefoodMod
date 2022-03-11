package jp.tdn.japanese_food_mod.client.models;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.LivingEntity;

public class TurbanShellEntityModel<T extends LivingEntity> extends EntityModel<T> {
    private final ModelRenderer bone;
    private final ModelRenderer hole;
    private final ModelRenderer back;

    public TurbanShellEntityModel() {
        this.texWidth = 32;
        this.texHeight = 32;

        bone = new ModelRenderer(this);
        bone.setPos(0.0F, 24.0F, 0.0F);


        hole = new ModelRenderer(this);
        hole.setPos(0.0F, 0.0F, 0.0F);
        bone.addChild(hole);
        hole.texOffs(0, 12).addBox(1.0F, -2.12F, -1.0F, 1.0F, 2.0F, 2.0F, 0.0F, false);
        hole.texOffs(0, 7).addBox(0.5F, -2.1F, 0.5F, 1.0F, 2.0F, 1.0F, 0.0F, false);
        hole.texOffs(0, 7).addBox(0.5F, -2.1F, -1.5F, 1.0F, 2.0F, 1.0F, 0.0F, false);
        hole.texOffs(7, 7).addBox(0.5F, -2.5F, -1.5F, 1.0F, 1.0F, 3.0F, 0.0F, false);
        hole.texOffs(0, 0).addBox(-2.0F, -3.0F, -2.0F, 3.0F, 3.0F, 4.0F, 0.0F, false);
        hole.texOffs(0, 21).addBox(-1.0F, -1.8F, -3.2F, 1.0F, 1.0F, 2.0F, 0.0F, false);
        hole.texOffs(0, 16).addBox(-1.0F, -3.6F, -3.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        hole.texOffs(4, 16).addBox(-1.0F, -4.0F, -1.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        hole.texOffs(13, 16).addBox(-1.0F, -3.6F, 2.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        hole.texOffs(0, 21).addBox(-1.0F, -1.8F, 1.2F, 1.0F, 1.0F, 2.0F, 0.0F, false);
        hole.texOffs(9, 16).addBox(-1.0F, -4.0F, 0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);

        back = new ModelRenderer(this);
        back.setPos(-2.0F, -1.0F, 0.0F);
        bone.addChild(back);
        setRotationAngle(back, 0.0F, 0.0F, 0.1745F);
        back.texOffs(9, 18).addBox(-0.8F, -2.0F, -2.0F, 1.0F, 3.0F, 4.0F, 0.0F, false);
        back.texOffs(16, 0).addBox(-2.0F, -3.0F, -1.0F, 2.0F, 3.0F, 3.0F, 0.0F, false);
        back.texOffs(16, 6).addBox(-3.0F, -2.8F, 0.0F, 1.0F, 2.0F, 2.0F, 0.0F, false);
        back.texOffs(26, 0).addBox(-4.0F, -2.7F, 1.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
    }

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
        //previously the render function, render code was moved to a method below
    }

    @Override
    public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
        bone.render(matrixStack, buffer, packedLight, packedOverlay);
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.xRot = x;
        modelRenderer.yRot = y;
        modelRenderer.zRot = z;
    }
}
