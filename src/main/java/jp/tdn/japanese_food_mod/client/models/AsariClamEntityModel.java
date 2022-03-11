package jp.tdn.japanese_food_mod.client.models;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.LivingEntity;

public class AsariClamEntityModel<T extends LivingEntity> extends EntityModel<T> {
    private final ModelRenderer body;
    private final ModelRenderer bone;
    private final ModelRenderer bone2;

    public AsariClamEntityModel() {
        this.texWidth = 32;
        this.texHeight = 32;

        body = new ModelRenderer(this);
        body.setPos(0.0F, 24.0F, 0.0F);


        bone = new ModelRenderer(this);
        bone.setPos(0.0F, 0.0F, 0.0F);
        body.addChild(bone);
        bone.texOffs(0, 19).addBox(-1.7F, -1.0F, -1.0F, 3.0F, 1.0F, 3.0F, 0.0F, false);
        bone.texOffs(0, 5).addBox(-1.7F, -3.0F, -1.0F, 3.0F, 1.0F, 3.0F, 0.0F, false);
        bone.texOffs(0, 9).addBox(-0.7F, -2.0F, 2.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        bone.texOffs(0, 13).addBox(-0.7F, -1.0F, 2.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);

        bone2 = new ModelRenderer(this);
        bone2.setPos(0.0F, -1.0F, 3.0F);
        body.addChild(bone2);
        bone2.texOffs(0, 0).addBox(-2.7F, -1.0F, -5.0F, 5.0F, 1.0F, 4.0F, 0.0F, false);
    }

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
        //previously the render function, render code was moved to a method below
    }

    @Override
    public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
        body.render(matrixStack, buffer, packedLight, packedOverlay);
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.xRot = x;
        modelRenderer.yRot = y;
        modelRenderer.zRot = z;
    }
}
