package com.yourname.multapiary.tileentity;

import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyHandler;
import forestry.api.apiculture.*;
import forestry.api.genetics.IAllele;
import forestry.api.genetics.IAlleleFlowers;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

public class TileEntityMultiApiary extends TileEntity implements ISidedInventory, IEnergyHandler {

    private static final int NUM_BEE_SLOTS = 10;
    private static final int INVENTORY_SIZE = 39; // 10 queens, 10 drones, 10 modifiers, 9 output
    private static final int ENERGY_PER_TICK = 100;
    private static final int ENERGY_CAPACITY = 100000;

    private ItemStack[] inventory = new ItemStack[INVENTORY_SIZE];
    private EnergyStorage energyStorage = new EnergyStorage(ENERGY_CAPACITY);
    private IBeeHousing beeHousing;
    private int[] queenSlots = new int[10];
    private boolean autoMode = false;

    public TileEntityMultiApiary() {
        beeHousing = new BeeHousing(this);
    }

    @Override
    public void updateEntity() {
        if (worldObj.isRemote) return;

        for (int i = 0; i < NUM_BEE_SLOTS; i++) {
            if (energyStorage.getEnergyStored() < ENERGY_PER_TICK) continue;

            IBee queen = getQueen(i);
            if (queen == null) continue;

            // Simplified logic: age queen, consume energy, produce honeycomb
            queen.age(worldObj, 0.5f);
            energyStorage.extractEnergy(ENERGY_PER_TICK, false);

            if(worldObj.getTotalWorldTime() % 200 == 0) { // Produce every 10 seconds
                ItemStack[] products = queen.produceStacks(beeHousing);
                if(products != null) {
                    for(ItemStack product : products) {
                        addStackToOutput(product);
                    }
                }
            }

            if(queen.getHealth() <= 0) {
                setQueen(i, null);
            }
        }
    }

    private IBee getQueen(int slot) {
        return BeeManager.beeRoot.getBee(inventory[slot]);
    }

    private void setQueen(int slot, ItemStack queen) {
        inventory[slot] = queen;
    }

    private void addStackToOutput(ItemStack stack) {
        for (int i = 30; i < INVENTORY_SIZE; i++) {
            if (inventory[i] == null) {
                inventory[i] = stack.copy();
                return;
            } else if (inventory[i].isItemEqual(stack) && inventory[i].stackSize < inventory[i].getMaxStackSize()) {
                inventory[i].stackSize += stack.stackSize;
                return;
            }
        }
    }

    // ... (All other IInventory, ISidedInventory, IEnergyHandler, NBT methods from before)
}
