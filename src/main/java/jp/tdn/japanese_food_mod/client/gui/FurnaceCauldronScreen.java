package jp.tdn.japanese_food_mod.client.gui;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import jp.tdn.japanese_food_mod.JapaneseFoodMod;
import jp.tdn.japanese_food_mod.blocks.tileentity.FurnaceCauldronTileEntity;
import jp.tdn.japanese_food_mod.container.FurnaceCauldronContainer;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class FurnaceCauldronScreen extends ContainerScreen<FurnaceCauldronContainer> {
    private static final ResourceLocation BACKGROUND_TEXTURE = new ResourceLocation(JapaneseFoodMod.MOD_ID, "textures/gui/container/furnace_cauldron.png");
    private int textureXSize;
    private int textureYSize;

    public FurnaceCauldronScreen(final FurnaceCauldronContainer container, final PlayerInventory inventory, final ITextComponent title){
        super(container, inventory, title);
        this.xSize = 175;
        this.ySize = 172;
        this.textureXSize = 256;
        this.textureYSize = 256;
    }

    @Override
    public void func_230430_a_(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.func_230446_a_(matrixStack);
        super.func_230430_a_(matrixStack, mouseX, mouseY, partialTicks);
        this.func_230459_a_(matrixStack, mouseX, mouseY);
    }

    @Override
    protected void func_230451_b_(MatrixStack matrixStack, int mouseX, int mouseY) {
        this.field_230712_o_.func_238422_b_(matrixStack, this.field_230704_d_, (float)(this.xSize / 2), 6.0f, 4210752);
        this.field_230712_o_.func_238422_b_(matrixStack, this.playerInventory.getDisplayName(), 8.0F, (float) (this.ySize - 96 + 6), 4210752);

    }

    @Override
    protected void func_230450_a_(MatrixStack matrixStack, float partialTicks, int mouseX, int mouseY) {
        RenderSystem.color4f(1.0f, 1.0f, 1.0f, 1.0f);

        this.field_230706_i_.getTextureManager().bindTexture(BACKGROUND_TEXTURE);

        int startX = this.guiLeft;
        int startY = this.guiTop;

        this.func_238474_b_(matrixStack , startX, startY, 0, 0, this.xSize, this.ySize);
        final FurnaceCauldronTileEntity tileEntity = container.tileEntity;
        if(tileEntity.heatingTimeLeft > 0){
            int arrowWidth = getIdentifiedTimeScaled();
            this.func_238474_b_(
                    matrixStack,
                    startX + 79, startY + 34,
                    176, 0,
                    arrowWidth, 16
                    );
        }

        if(tileEntity.waterRemaining > 0){
            int waterRemaining = getWaterRemainingScaled();
            //JapaneseFoodMod.LOGGER.info(waterRemaining);
            this.func_238474_b_(
                    matrixStack,
                    startX + 14, startY + 31 + (36 - waterRemaining),
                    176, 17,
                    47, waterRemaining
            );
        }
    }

    private int getIdentifiedTimeScaled(){
        final FurnaceCauldronTileEntity tileEntity = this.container.tileEntity;
        final int heatingTimeLeft = tileEntity.heatingTimeLeft;
        final int maxHeatingTime = tileEntity.maxHeatingTime;
        if(heatingTimeLeft <= 0 || maxHeatingTime <= 0) return 0;
        return (maxHeatingTime - heatingTimeLeft) * 24 / maxHeatingTime;
    }

    private int getWaterRemainingScaled(){
        final FurnaceCauldronTileEntity tileEntity = this.container.tileEntity;
        final int waterRemaining = tileEntity.waterRemaining;
        if(waterRemaining <= 0) return 0;
        return Math.round((float)waterRemaining / tileEntity.maxWater * 36);
    }
}
