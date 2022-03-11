package jp.tdn.japanese_food_mod.client.gui;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import jp.tdn.japanese_food_mod.JapaneseFoodMod;
import jp.tdn.japanese_food_mod.blocks.tileentity.WoodenBucketTileEntity;
import jp.tdn.japanese_food_mod.container.WoodenBucketContainer;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class WoodenBucketScreen extends ContainerScreen<WoodenBucketContainer> {
    private static final ResourceLocation BACKGROUND_TEXTURE = new ResourceLocation(JapaneseFoodMod.MOD_ID, "textures/gui/container/wooden_bucket.png");
    private int textureXSize;
    private int textureYSize;

    public WoodenBucketScreen(final WoodenBucketContainer container, final PlayerInventory inventory, final ITextComponent title){
        super(container, inventory, title);
        this.imageWidth = 175;
        this.imageHeight = 172;
        this.textureXSize = 256;
        this.textureYSize = 256;
    }

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
        this.renderTooltip(matrixStack, mouseX, mouseY);
    }

    @Override
    protected void renderLabels(MatrixStack matrixStack, int mouseX, int mouseY) {
        this.font.drawShadow(matrixStack, this.title, (float)(this.imageWidth / 2), 6.0f, 4210752);
        this.font.drawShadow(matrixStack, this.inventory.getDisplayName(), 8.0F, (float) (this.imageHeight - 96 + 6), 4210752);

    }

    @Override
    protected void renderBg(MatrixStack matrixStack, float partialTicks, int mouseX, int mouseY) {
        RenderSystem.color4f(1.0f, 1.0f, 1.0f, 1.0f);

        this.minecraft.getTextureManager().bind(BACKGROUND_TEXTURE);

        int startX = this.width;
        int startY = this.height;

        this.blit(matrixStack , startX, startY, 0, 0, this.width, this.height);

        final WoodenBucketTileEntity tileEntity = menu.tileEntity;
        if(tileEntity.fermentationTimeLeft > 0){
            int arrowWidth = getIdentifiedTimeScaled();
            this.blit(
                    matrixStack,
                    startX + 79, startY + 34,
                    176, 0,
                    arrowWidth, 16
                    );
        }
    }

    private int getIdentifiedTimeScaled(){
        final WoodenBucketTileEntity tileEntity = this.menu.tileEntity;
        final short fermentationTimeLeft = tileEntity.fermentationTimeLeft;
        final short maxFermentationTime = tileEntity.maxFermentationTime;
        if(fermentationTimeLeft <= 0 || maxFermentationTime <= 0) return 0;
        return (maxFermentationTime - fermentationTimeLeft) * 24 / maxFermentationTime;
    }
}
