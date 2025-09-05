package com.Cell_SINON.InstantHealMod.screen;

import com.Cell_SINON.InstantHealMod.block.Distiller.DistillerMenu;
import com.Cell_SINON.InstantHealMod.block.Pyrolysis.PyrolysisMenu;
import com.Cell_SINON.InstantHealMod.main.InstantHealMod;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class PyrolysisScreen extends AbstractContainerScreen<PyrolysisMenu> {

    private static final ResourceLocation TEXTURE =
            new ResourceLocation(InstantHealMod.MOD_id, "textures/gui/pyrolysis_block_gui.png");

    public PyrolysisScreen(PyrolysisMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
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
        RenderSystem.setShaderTexture(0, TEXTURE);
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;

        // 1. GUIの背景全体を描画する
        pGuiGraphics.blit(TEXTURE, x, y, 0, 0, imageWidth, imageHeight);

        // 2. 燃焼ゲージ（炎）を描画する


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


