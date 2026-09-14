package com.voxelutopia.ultramarine.client.screen;

import com.voxelutopia.ultramarine.common.menu.ContainerDecorativeBlockMenu;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Inventory;

public class ContainerDecorativeBlockScreen extends AbstractContainerScreen<ContainerDecorativeBlockMenu> {

    private static final Identifier CONTAINER_BACKGROUND = Identifier.withDefaultNamespace("textures/gui/container/generic_54.png");
    private final int containerRows;

    public ContainerDecorativeBlockScreen(ContainerDecorativeBlockMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
        this.imageWidth = 176;
        this.imageHeight = 114 + pMenu.getRowCount() * 18;
        this.containerRows = pMenu.getRowCount();
        this.inventoryLabelY = this.imageHeight - 94;
    }

    @Override
    public void renderBg(GuiGraphics graphics, float partialTick, int mouseX, int mouseY) {
        
        int xo = (this.width - this.imageWidth) / 2;
        int yo = (this.height - this.imageHeight) / 2;
        graphics.blit(
                RenderPipelines.GUI_TEXTURED,
                CONTAINER_BACKGROUND,
                xo,
                yo,
                0.0F,
                0.0F,
                this.imageWidth,
                this.containerRows * 18 + 17,
                256,
                256
        );
        graphics.blit(
                RenderPipelines.GUI_TEXTURED,
                CONTAINER_BACKGROUND,
                xo,
                yo + this.containerRows * 18 + 17,
                0.0F,
                126.0F,
                this.imageWidth,
                96,
                256,
                256
        );
    }
}
