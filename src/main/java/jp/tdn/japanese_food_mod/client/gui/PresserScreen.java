package jp.tdn.japanese_food_mod.client.gui;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import jp.tdn.japanese_food_mod.JapaneseFoodMod;
import jp.tdn.japanese_food_mod.blocks.tileentity.PresserTileEntity;
import jp.tdn.japanese_food_mod.container.PresserContainer;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class PresserScreen extends ContainerScreen<PresserContainer> {
    private static final ResourceLocation BACKGROUND_TEXTURE = new ResourceLocation(JapaneseFoodMod.MOD_ID, "textures/gui/container/presser.png");

    public PresserScreen(final PresserContainer container, final PlayerInventory inventory, final ITextComponent title){
        super(container, inventory, title);
        this.imageWidth = 175;
        this.imageHeight = 168;
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
        this.font.drawShadow(matrixStack, this.inventory.getDisplayName(), 8.0F, (float) (this.imageHeight - 96 + 5), 4210752);

    }

    @Override
    protected void renderBg(MatrixStack matrixStack, float partialTicks, int mouseX, int mouseY) {
        RenderSystem.color4f(1.0f, 1.0f, 1.0f, 1.0f);

        this.minecraft.getTextureManager().bind(BACKGROUND_TEXTURE);

        int startX = this.width;
        int startY = this.height;

        this.blit(matrixStack , startX, startY, 0, 0, this.width, this.height);

        final PresserTileEntity tileEntity = menu.tileEntity;
        if(tileEntity.pressedTimeLeft > 0){
            int arrowHeight = getPressedTimeScaled();
            this.blit(
                    matrixStack,
                    startX + 34, startY + 32,
                    176, 0,
                    16, arrowHeight
                    );
        }

        if(tileEntity.oilRemaining > 0){
            int oilRemaining = getOilRemainingScaled();
            this.blit(
                    matrixStack,
                    startX + 22, startY + 59 + 13 - oilRemaining,
                    176, 22,
                    40, oilRemaining
            );
        }
    }

    private int getPressedTimeScaled(){
        final PresserTileEntity tileEntity = this.menu.tileEntity;
        final short pressedTimeLeft = tileEntity.pressedTimeLeft;
        final short maxPressedTime = tileEntity.maxPressedTime;
        if(pressedTimeLeft <= 0 || maxPressedTime <= 0) return 0;
        return (maxPressedTime - pressedTimeLeft) * 20 / maxPressedTime;
    }

    private int getOilRemainingScaled(){
        final PresserTileEntity tileEntity = this.menu.tileEntity;
        final short oilRemaining = tileEntity.oilRemaining;
        if(oilRemaining <= 0) return 0;
        return Math.round((float)oilRemaining / tileEntity.maxOilRemaining * 13);
    }
}
