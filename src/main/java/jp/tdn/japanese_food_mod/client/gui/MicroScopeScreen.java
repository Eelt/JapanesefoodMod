package jp.tdn.japanese_food_mod.client.gui;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import jp.tdn.japanese_food_mod.JapaneseFoodMod;
import jp.tdn.japanese_food_mod.blocks.tileentity.MicroScopeTileEntity;
import jp.tdn.japanese_food_mod.container.MicroScopeContainer;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class MicroScopeScreen extends ContainerScreen<MicroScopeContainer> {
    private static final ResourceLocation BACKGROUND_TEXTURE = new ResourceLocation(JapaneseFoodMod.MOD_ID, "textures/gui/container/microscope.png");

    public MicroScopeScreen(final MicroScopeContainer container, final PlayerInventory inventory, final ITextComponent title){
        super(container, inventory, title);
        this.imageHeight = 191;
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
        this.font.drawShadow(matrixStack, this.inventory.getDisplayName(), 8.0F, (float) (this.imageHeight - 96 + 4), 4210752);

    }

    @Override
    protected void renderBg(MatrixStack matrixStack, float partialTicks, int mouseX, int mouseY) {
        RenderSystem.color4f(1.0f, 1.0f, 1.0f, 1.0f);

        this.minecraft.getTextureManager().bind(BACKGROUND_TEXTURE);

        int startX = this.width;
        int startY = this.height;

        this.blit(matrixStack , startX, startY, 0, 0, this.width, this.height);

        final MicroScopeTileEntity tileEntity = menu.tileEntity;
        if(tileEntity.identifiedTimeLeft > 0){
            int arrowHeight = getIdentifiedTimeScaled();
            this.blit(
                    matrixStack,
                    startX + 87, startY + 44,
                    176, 0,
                    27, 28 - arrowHeight
                    );
        }else{
            this.blit(
                    matrixStack,
                    startX + 87, startY + 44,
                    176, 0,
                    27, 28);
        }
    }

//    @Override
//    public void render(int mouseX, int mouseY, float partialTicks) {
//        this.renderBackground();
//        super.render(mouseX, mouseY, partialTicks);
//        this.renderHoveredToolTip(mouseX, mouseY);
//
//        int relMouseX = mouseX - this.guiLeft;
//        int relMouseY = mouseY - this.guiTop;
//        final MicroScopeTileEntity tileEntity = this.container.tileEntity;
//        boolean arrowHovered = relMouseX > 87 && relMouseX < 114 && relMouseY > 44 && relMouseY < 71;
//        if(arrowHovered && tileEntity.maxIdentifiedTime > 0){
//            String tooltip = new TranslationTextComponent(
//                    "gui." + JapaneseFoodMod.MOD_ID + ".identifiedTimeProgress",
//                    (short)(((float)(tileEntity.maxIdentifiedTime - tileEntity.identifiedTimeLeft) / tileEntity.maxIdentifiedTime) * 100),
//                    "%"
//            ).getFormattedText();
//            this.renderTooltip(tooltip, mouseX, mouseY);
//        }
//    }
//
//    @Override
//    protected void drawGuiContainerForegroundLayer(int mouseX_, int mouseY) {
//        super.drawGuiContainerForegroundLayer(mouseX_, mouseY);
//        String s = this.title.getFormattedText();
//        this.font.drawString(s, (float)(this.xSize / 1.5 - this.font.getStringWidth(s) / 2), 6.0F, 0x404040);
//        this.font.drawString(this.playerInventory.getDisplayName().getFormattedText(), 8.0F, (float)(this.ySize - 92 + 2), 0x404040);
//    }
//
//    @Override
//    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
//        GlStateManager.color4f(1.0f, 1.0f, 1.0f, 1.0f);
//        getMinecraft().getTextureManager().bindTexture(BACKGROUND_TEXTURE);
//        int startX = this.guiLeft;
//        int startY = this.guiTop;
//
//        this.blit(startX, startY, 0, 0, this.xSize, this.ySize);
//
//        final MicroScopeTileEntity tileEntity = container.tileEntity;
//        if(tileEntity.identifiedTimeLeft > 0){
//            int arrowHeight = getIdentifiedTimeScaled();
//            this.blit(
//                    startX + 87, startY + 44,
//                    176, 0,
//                    27, 28 - arrowHeight
//                    );
//        }else{
//            this.blit(startX + 87, startY + 44,
//                    176, 0,
//                    27, 28);
//        }
//    }

    private int getIdentifiedTimeScaled(){
        final MicroScopeTileEntity tileEntity = this.menu.tileEntity;
        final short identifiedTimeLeft = tileEntity.identifiedTimeLeft;
        final short maxIdentifiedTime = tileEntity.maxIdentifiedTime;
        if(identifiedTimeLeft <= 0 || maxIdentifiedTime <= 0) return 0;
        return (maxIdentifiedTime - identifiedTimeLeft) * 28 / maxIdentifiedTime;
    }
}
