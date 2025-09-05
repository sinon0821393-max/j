package com.Cell_SINON.InstantHealMod.screen;

import com.Cell_SINON.InstantHealMod.block.ChemicalReactor.ChemicalReactorMenu;
import com.Cell_SINON.InstantHealMod.block.Distiller.DistillerMenu;
import com.Cell_SINON.InstantHealMod.main.InstantHealMod;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class ChemicalReactorScreen extends AbstractContainerScreen<ChemicalReactorMenu> {

    private static final ResourceLocation TEXTURE =
            new ResourceLocation(InstantHealMod.MOD_id, "textures/gui/chemical_block_gui.png");

    private static final Component INVENTORY_TITLE = Component
            .translatable("container." + InstantHealMod.MOD_id + ".inventory").withStyle(ChatFormatting.ITALIC);

    public ChemicalReactorScreen(ChemicalReactorMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);

    }

    @Override
    protected void renderLabels(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY) {
        pGuiGraphics.drawString(this.font, this.title, this.titleLabelX, this.titleLabelY, 4210752, false);
        pGuiGraphics.drawString(this.font, this.playerInventoryTitle, this.inventoryLabelX, this.inventoryLabelY, 4210752, false);
    }

    @Override
    protected void init() {
        super.init();
        // GUIのタイトルテキストの位置などを設定する場合はここに書く
        // 例: this.titleLabelY = 10;
    }

    /**
     * 背景を描画するメソッド
     */
    @Override
    protected void renderBg(GuiGraphics pGuiGraphics, float pPartialTick, int pMouseX, int pMouseY) {
        RenderSystem.setShaderTexture(0, TEXTURE);
        int x = (this.width - this.imageWidth)/2;
        int y = (this.height - this.imageHeight)/2;

        // 1. GUIの背景全体を描画する
        pGuiGraphics.blit(TEXTURE, x, y, 0, 0, this.imageWidth, this.imageHeight);



        // 3. 進捗矢印を描画する

        }






    /**
     * 画面全体を描画するメインメソッド
     */
    @Override
    public void render(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
        renderBackground(pGuiGraphics);
        super.render(pGuiGraphics, pMouseX, pMouseY, pPartialTick);
        renderTooltip(pGuiGraphics, pMouseX, pMouseY);
    }
}
