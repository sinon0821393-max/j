package com.Cell_SINON.InstantHealMod.screen;

import com.Cell_SINON.InstantHealMod.block.Distiller.DistillerMenu;
import com.Cell_SINON.InstantHealMod.main.InstantHealMod;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class DistillerScreen extends AbstractContainerScreen<DistillerMenu> {    // GUIの背景テクスチャのパス (新しく作る必要があります！)
    private static final ResourceLocation DISTILLER_TEXTURE =
            new ResourceLocation(InstantHealMod.MOD_id, "textures/gui/distiller_block_gui.png");

    public DistillerScreen(DistillerMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
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
        RenderSystem.setShaderTexture(0, DISTILLER_TEXTURE);
        int x = (this.width - this.imageWidth) / 2;
        int y = (this.height - this.imageHeight) / 2;

        // 1. GUIの背景全体を描画する
        pGuiGraphics.blit(DISTILLER_TEXTURE, x, y, 0, 0, this.imageWidth, this.imageHeight);


    }

        /**
         * 燃焼ゲージを描画するためのヘルパーメソッド
         */
//    private void renderBurnFlame(GuiGraphics pGuiGraphics, int x, int y) {
//        if (menu.isBurning()) {
//            // 炎アイコンの高さを、残り燃焼時間に応じて計算
//            int flameHeight = menu.getScaledFuelProgress();
//
//            // blit(テクスチャ, 描画X, 描画Y, テクスチャU, テクスチャV, 横幅, 縦幅)
//            // テクスチャのUV座標 (176, 0) に、高さ14ピクセルの炎の絵を描いておく
//            pGuiGraphics.blit(TEXTURE,
//                    x + 56, y + 36 + 14 - flameHeight, // 描画Y座標を動かすことで、炎が下から燃え上がるように見せる
//                    176, 14 - flameHeight,
//                    14, flameHeight);
//        }
//    }
//
//    /**
//     * 進捗矢印を描画するためのヘルパーメソッド
//     */
//    private void renderProgressArrow(GuiGraphics pGuiGraphics, int x, int y) {
//        if (menu.isCrafting()) {
//            // テクスチャのUV座標 (176, 14) に、横幅24ピクセルの矢印の絵を描いておく
//            pGuiGraphics.blit(TEXTURE, x + 86, y + 21, 176, 14, menu.getScaledProgress(), 17);
//        }
//    }

    /**
     * 画面全体を描画するメインメソッド
     */
    @Override
    public void render(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
        super.render(pGuiGraphics, pMouseX, pMouseY, pPartialTick);
        int x = (this.width - this.imageWidth) / 2;
        int y = (this.height - this.imageHeight) / 2;

    }

}


