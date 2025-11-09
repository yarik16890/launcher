package com.yourname.multapiary.inventory;

import com.yourname.multapiary.tileentity.TileEntityMultiApiary;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import java.util.ArrayList;

public class ContainerMultiApiary extends Container {
    private TileEntityMultiApiary tileEntity;
    private int currentBeeSlot = 0;

    public ContainerMultiApiary(InventoryPlayer playerInventory, TileEntityMultiApiary tileEntity) {
        this.tileEntity = tileEntity;

        // Add apiary and output slots
        addApiarySlots();
        addOutputSlots();

        // Player inventory
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.addSlotToContainer(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 140 + i * 18));
            }
        }

        // Player hotbar
        for (int i = 0; i < 9; ++i) {
            this.addSlotToContainer(new Slot(playerInventory, i, 8 + i * 18, 198));
        }
    }

    private void addApiarySlots() {
        // Queen, Drone, Modifier
        this.addSlotToContainer(new Slot(tileEntity, currentBeeSlot, 34, 18));
        this.addSlotToContainer(new Slot(tileEntity, currentBeeSlot + 10, 34, 54));
        this.addSlotToContainer(new Slot(tileEntity, currentBeeSlot + 20, 7, 18));
    }

    private void addOutputSlots() {
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                this.addSlotToContainer(new Slot(tileEntity, 30 + (j + i * 3), 116 + j * 18, 18 + i * 18));
            }
        }
    }

    @Override
    public boolean canInteractWith(EntityPlayer player) {
        return tileEntity.isUseableByPlayer(player);
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int index) {
        // Basic shift-click implementation
        return null;
    }

    public void setCurrentBeeSlot(int slot) {
        if (slot >= 0 && slot < 10) {
            this.currentBeeSlot = slot;
            this.inventorySlots.clear();
            this.inventoryItemStacks.clear();
            addApiarySlots();
            addOutputSlots();
        }
    }
}
