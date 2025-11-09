package com.yourname.multapiary.inventory;

import com.yourname.multapiary.tileentity.TileEntityMultiApiary;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;

public class ContainerMultiApiary extends Container {
    private TileEntityMultiApiary tileEntity;
    private Slot queenSlot;
    private Slot droneSlot;
    private Slot modifierSlot;

    public ContainerMultiApiary(EntityPlayer player, TileEntityMultiApiary tile) {
        this.tileEntity = tile;

        // Apiary slots (placeholders)
        queenSlot = new Slot(tileEntity, 0, 34, 18);
        droneSlot = new Slot(tileEntity, 10, 34, 54);
        modifierSlot = new Slot(tileEntity, 20, 7, 18);
        addSlotToContainer(queenSlot);
        addSlotToContainer(droneSlot);
        addSlotToContainer(modifierSlot);

        // Output and player inventory slots... (as before)
    }

    public void setCurrentBeeSlot(int slot) {
        if(slot >= 0 && slot < 10) {
            queenSlot.slotNumber = slot;
            droneSlot.slotNumber = slot + 10;
            modifierSlot.slotNumber = slot + 20;
        }
    }

    // ... (canInteractWith, transferStackInSlot)
}
