package jp.tdn.japanese_food_mod.client.models;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.MathHelper;

public class EelEntityModel<T extends LivingEntity> extends EntityModel<T> {
    private final ModelRenderer tail;
    private final ModelRenderer body;
    private final ModelRenderer head;
    private final ModelRenderer body2;
    private final ModelRenderer hire;
    private final ModelRenderer hire2;

    public EelEntityModel() {
        this.texWidth = 32;
        this.texHeight = 32;

        tail = new ModelRenderer(this);
        tail.setPos(0.0F, 23.0F, 12.0F);
        tail.texOffs(17, 11).addBox(-0.5F, -1.5F, 0.0F, 1.0F, 2.0F, 3.0F, 0.0F, false);
        tail.texOffs(19, 6).addBox(-0.5F, -1.5F, 3.0F, 1.0F, 2.0F, 3.0F, 0.0F, false);

        body = new ModelRenderer(this);
        body.setPos(0.0F, 24.0F, 0.0F);
        body.texOffs(0, 10).addBox(-1.0F, -3.0F, -4.0F, 2.0F, 3.0F, 3.0F, 0.0F, false);
        body.texOffs(0, 10).addBox(-1.0F, -3.0F, -1.0F, 2.0F, 3.0F, 3.0F, 0.0F, false);
        body.texOffs(0, 10).addBox(-1.0F, -3.0F, 2.0F, 2.0F, 3.0F, 3.0F, 0.0F, false);

        head = new ModelRenderer(this);
        head.setPos(0.0F, 24.0F, 0.0F);
        head.texOffs(0, 10).addBox(-1.0F, -3.0F, -7.0F, 2.0F, 3.0F, 3.0F, 0.0F, false);
        head.texOffs(0, 2).addBox(-1.0F, -2.5F, -9.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);
        head.texOffs(0, 0).addBox(-1.0F, -2.5F, -10.0F, 2.0F, 1.0F, 1.0F, 0.0F, false);

        body2 = new ModelRenderer(this);
        body2.setPos(0.0F, 23.0F, 5.0F);
        body2.texOffs(8, 0).addBox(-1.0F, -1.5F, 0.0F, 2.0F, 2.0F, 3.0F, 0.0F, false);
        body2.texOffs(7, 5).addBox(-1.0F, -1.5F, 3.0F, 2.0F, 2.0F, 4.0F, 0.0F, false);

        hire = new ModelRenderer(this);
        hire.setPos(1.0F, 23.0F, -7.0F);
        setRotationAngle(hire, 0.0F, 0.2618F, 0.0F);
        hire.texOffs(0, 3).addBox(0.0F, -0.5F, 0.0F, 0.0F, 1.0F, 4.0F, 0.0F, false);

        hire2 = new ModelRenderer(this);
        hire2.setPos(-1.0F, 23.0F, -7.0F);
        setRotationAngle(hire2, 0.0F, -0.2618F, 0.0F);
        hire2.texOffs(0, 3).addBox(0.0F, -0.5F, 0.0F, 0.0F, 1.0F, 4.0F, 0.0F, true);
    }

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
        float move = 1.0f;
        if(!entity.isInWater()){
            move = 1.5f;
        }

        tail.x = MathHelper.sin(move * 0.1f * ageInTicks) * 0.2f;
        body2.y = -(MathHelper.sin(move * 0.1f * ageInTicks) * 0.2f);
    }

    @Override
    public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
        tail.render(matrixStack, buffer, packedLight, packedOverlay);
        body.render(matrixStack, buffer, packedLight, packedOverlay);
        head.render(matrixStack, buffer, packedLight, packedOverlay);
        body2.render(matrixStack, buffer, packedLight, packedOverlay);
        hire.render(matrixStack, buffer, packedLight, packedOverlay);
        hire2.render(matrixStack, buffer, packedLight, packedOverlay);
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.xRot = x;
        modelRenderer.yRot = y;
        modelRenderer.zRot = z;
    }
}

