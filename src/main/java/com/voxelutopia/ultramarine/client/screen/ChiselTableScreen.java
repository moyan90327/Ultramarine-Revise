package com.voxelutopia.ultramarine.client.screen;

import com.voxelutopia.ultramarine.Ultramarine;
import com.voxelutopia.ultramarine.common.menu.ChiselTableMenu;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Inventory;

public class ChiselTableScreen extends AbstractContainerScreen<ChiselTableMenu> {

    private static final Identifier BACKGROUND = Identifier.fromNamespaceAndPath(Ultramarine.MOD_ID, "textures/gui/chisel_table.png");

    public ChiselTableScreen(ChiselTableMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
    }

    @Override
    public void renderBg(GuiGraphics graphics, float partialTick, int mouseX, int mouseY) {
        
        graphics.blit(
                RenderPipelines.GUI_TEXTURED,
                BACKGROUND,
                this.leftPos,
                this.topPos,
                0.0F,
                0.0F,
                this.imageWidth,
                this.imageHeight,
                256,
                256
        );
    }
}
