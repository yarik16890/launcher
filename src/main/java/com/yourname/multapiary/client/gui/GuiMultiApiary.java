package com.yourname.multapiary.client.gui;

import com.yourname.multapiary.inventory.ContainerMultiApiary;
import com.yourname.multapiary.tileentity.TileEntityMultiApiary;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import org.lwjgl.input.Mouse;

public class GuiMultiApiary extends GuiContainer {

    private static final ResourceLocation guiTexture = new ResourceLocation("multapiary", "textures/gui/multi_apiary.png");
    private TileEntityMultiApiary tileEntity;
    private float scroll = 0.0F;
    private boolean isScrolling = false;

    public GuiMultiApiary(InventoryPlayer playerInventory, TileEntityMultiApiary tileEntity) {
        super(new ContainerMultiApiary(playerInventory, tileEntity));
        this.tileEntity = tileEntity;
        this.xSize = 195;
        this.ySize = 222;
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        String s = I18n.format("container.multiApiary");
        this.fontRendererObj.drawString(s, this.xSize / 2 - this.fontRendererObj.getStringWidth(s) / 2, 6, 4210752);
        this.fontRendererObj.drawString(I18n.format("container.inventory"), 8, this.ySize - 96 + 2, 4210752);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(guiTexture);
        int k = (this.width - this.xSize) / 2;
        int l = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);

        // Draw scrollbar
        int scrollbarY = (int) (this.scroll * 70);
        this.drawTexturedModalRect(k + 80, l + 18 + scrollbarY, 195, 0, 12, 15);
    }

    @Override
    public void handleMouseInput() {
        super.handleMouseInput();
        int dwheel = Mouse.getDWheel();
        if (dwheel != 0) {
            int beeSlots = 10;
            if (dwheel > 0) {
                this.scroll = Math.max(0, this.scroll - 1.0F / beeSlots);
            } else {
                this.scroll = Math.min(1.0F - 1.0F / beeSlots, this.scroll + 1.0F / beeSlots);
            }
            ((ContainerMultiApiary) this.inventorySlots).setCurrentBeeSlot((int) (this.scroll * beeSlots));
        }
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int button) {
        super.mouseClicked(mouseX, mouseY, button);
        int k = (this.width - this.xSize) / 2;
        int l = (this.height - this.ySize) / 2;
        if (mouseX >= k + 80 && mouseX < k + 80 + 12 && mouseY >= l + 18 && mouseY < l + 18 + 85) {
            this.isScrolling = true;
        }
    }

    @Override
    protected void mouseMovedOrUp(int mouseX, int mouseY, int button) {
        super.mouseMovedOrUp(mouseX, mouseY, button);
        if (button == 0) {
            this.isScrolling = false;
        }
    }

    @Override
    protected void mouseClickMove(int mouseX, int mouseY, int lastButtonClicked, long timeSinceMouseClick) {
        super.mouseClickMove(mouseX, mouseY, lastButtonClicked, timeSinceMouseClick);
        if(this.isScrolling) {
            int k = (this.width - this.xSize) / 2;
            int l = (this.height - this.ySize) / 2;
            this.scroll = (float)(mouseY - (l + 18)) / 70.0F;

            if (this.scroll < 0.0F) {
                this.scroll = 0.0F;
            }
            if (this.scroll > 1.0F) {
                this.scroll = 1.0F;
            }

            ((ContainerMultiApiary) this.inventorySlots).setCurrentBeeSlot((int) (this.scroll * 10));
        }
    }
}
