package com.Cell_SINON.InstantHealMod.screen;

import com.Cell_SINON.InstantHealMod.block.Electrolysis.ElectrolysisMenu;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import com.Cell_SINON.InstantHealMod.main.InstantHealMod;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class ElectrolysisScreen extends AbstractContainerScreen<ElectrolysisMenu> {    private static final ResourceLocation TEXTURE =
        new ResourceLocation(InstantHealMod.MOD_id, "textures/gui/electrolysis_block_gui.png");

    public ElectrolysisScreen(ElectrolysisMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
    }

    @Override
    protected void init() {
        super.init();
        // GUIのタイトルテキストの位置などを設定する場合はここに書く
        // this.titleLabelY = ...;
    }

    /**
     * 背景を描画するメソッド
     * @param pGuiGraphics 描画用オブジェクト
     * @param pPartialTick ティック間の補間フレーム
     * @param pMouseX マウスのX座標
     * @param pMouseY マウスのY座標
     */
    @Override
    protected void renderBg(GuiGraphics pGuiGraphics, float pPartialTick, int pMouseX, int pMouseY) {
        RenderSystem.setShaderTexture(0, TEXTURE);
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;

        // 1. まず、GUIの背景全体を描画する
        // blit(テクスチャ, 描画X, 描画Y, テクスチャU, テクスチャV, 横幅, 縦幅)
        pGuiGraphics.blit(TEXTURE, x, y, 0, 0, imageWidth, imageHeight);

        // 2. 次に、進捗に応じて矢印を描画する


    }

    /**
     * 画面全体を描画するメインメソッド
     */
    @Override
    public void render(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
        // 背景を暗くする処理
        renderBackground(pGuiGraphics);
        // 背景とスロットを描画する
        super.render(pGuiGraphics, pMouseX, pMouseY, pPartialTick);
        // ツールチップ（アイテム名など）を描画する
        renderTooltip(pGuiGraphics, pMouseX, pMouseY);
    }

}
