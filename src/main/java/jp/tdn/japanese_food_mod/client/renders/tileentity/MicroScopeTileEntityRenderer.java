package jp.tdn.japanese_food_mod.client.renders.tileentity;

import com.mojang.blaze3d.matrix.MatrixStack;
import jp.tdn.japanese_food_mod.blocks.MicroScopeBlock;
import jp.tdn.japanese_food_mod.blocks.tileentity.MicroScopeTileEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Direction;
import net.minecraft.util.math.vector.Vector3f;

public class MicroScopeTileEntityRenderer extends TileEntityRenderer<MicroScopeTileEntity> {
    public MicroScopeTileEntityRenderer(TileEntityRendererDispatcher dispatcher){
        super(dispatcher);
    }

 
    public void render(MicroScopeTileEntity tileEntity, float p_225616_2_, MatrixStack matrix, IRenderTypeBuffer p_225616_4_, int p_225616_5_, int p_225616_6_) {
        Direction direction = tileEntity.getBlockState().getValue(MicroScopeBlock.DIRECTION);
        ItemStack item = tileEntity.getInventory();

        if(!item.isEmpty()){
            matrix.pushPose();
            matrix.translate(0.5f, 0.5f, 0.5f);
            matrix.mulPose(Vector3f.YP.rotationDegrees( -direction.toYRot()));
            matrix.mulPose(Vector3f.XP.rotationDegrees(90.0F));
            matrix.translate(0f, 0f, 0.1f);
            matrix.scale(0.3f, 0.3f, 0.3f);
            Minecraft.getInstance().getItemRenderer().renderStatic(item, ItemCameraTransforms.TransformType.FIXED, p_225616_5_, p_225616_6_, matrix, p_225616_4_);
            matrix.popPose();
        }
    }
}
