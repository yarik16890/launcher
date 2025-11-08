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
    private ArrayList<Slot> apiarySlots = new ArrayList<>();

    public ContainerMultiApiary(InventoryPlayer playerInventory, TileEntityMultiApiary tileEntity) {
        this.tileEntity = tileEntity;

        // Add apiary slots first, so they have predictable indices
        addApiarySlots();

        // Output slots (9 slots)
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                this.addSlotToContainer(new Slot(tileEntity, 30 + (j + i * 3), 116 + j * 18, 18 + i * 18));
            }
        }

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
        // Remove old apiary slots
        for(Slot slot : apiarySlots) {
            this.inventorySlots.remove(slot);
            this.inventoryItemStacks.remove(slot.slotNumber);
        }
        apiarySlots.clear();

        // Add new apiary slots
        Slot queenSlot = new Slot(tileEntity, currentBeeSlot, 34, 18);
        Slot droneSlot = new Slot(tileEntity, 10 + currentBeeSlot, 34, 54);
        Slot modifierSlot = new Slot(tileEntity, 20 + currentBeeSlot, 7, 18);

        apiarySlots.add(queenSlot);
        apiarySlots.add(droneSlot);
        apiarySlots.add(modifierSlot);

        this.addSlotToContainer(queenSlot);
        this.addSlotToContainer(droneSlot);
        this.addSlotToContainer(modifierSlot);
    }

    // ... (canInteractWith, transferStackInSlot, setCurrentBeeSlot)
}
