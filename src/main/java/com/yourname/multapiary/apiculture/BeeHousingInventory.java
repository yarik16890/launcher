package com.yourname.multapiary.apiculture;

import com.yourname.multapiary.tileentity.TileEntityMultiApiary;
import forestry.api.apiculture.IBeeHousingInventory;
import net.minecraft.item.ItemStack;

public class BeeHousingInventory implements IBeeHousingInventory {
    private final TileEntityMultiApiary tile;
    private int currentSlot = 0;

    public BeeHousingInventory(TileEntityMultiApiary tile) {
        this.tile = tile;
    }

    public void setCurrentSlot(int slot) {
        this.currentSlot = slot;
    }

    @Override
    public ItemStack getQueen() {
        return tile.getStackInSlot(currentSlot);
    }

    @Override
    public ItemStack getDrone() {
        return tile.getStackInSlot(currentSlot + 10);
    }

    @Override
    public void setQueen(ItemStack itemstack) {
        tile.setInventorySlotContents(currentSlot, itemstack);
    }

    @Override
    public void setDrone(ItemStack itemstack) {
        tile.setInventorySlotContents(currentSlot + 10, itemstack);
    }

    @Override
    public boolean addProduct(ItemStack product, boolean all) {
        return tile.addStackToOutput(product, all);
    }
}
