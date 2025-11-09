package com.yourname.multapiary.apiculture;

import com.yourname.multapiary.items.ItemModifierLifespan;
import com.yourname.multapiary.items.ItemModifierMultiFlower;
import com.yourname.multapiary.items.ItemModifierProduction;
import com.yourname.multapiary.tileentity.TileEntityMultiApiary;
import forestry.api.apiculture.IBeeGenome;
import forestry.api.apiculture.IBeeHousing;
import forestry.api.apiculture.IBeeModifier;
import forestry.api.genetics.IAllele;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class BeeHousingModifier implements IBeeModifier {

    private final TileEntityMultiApiary tile;
    private int currentSlot = 0;

    public BeeHousingModifier(TileEntityMultiApiary tile) {
        this.tile = tile;
    }

    public void setCurrentSlot(int slot) {
        this.currentSlot = slot;
    }

    private boolean hasModifier(Class<? extends Item> modifierClass) {
        ItemStack modifierStack = tile.getStackInSlot(20 + currentSlot);
        return modifierStack != null && modifierClass.isInstance(modifierStack.getItem());
    }

    @Override
    public float getMutationModifier(IBeeGenome genome, IAllele allele, float current) {
        return 1.0f; // No mutation bonus
    }

    @Override
    public float getLifespanModifier(IBeeGenome genome, IBeeGenome mate, float current) {
        if (hasModifier(ItemModifierLifespan.class)) {
            return 1.2f; // +20%
        }
        return 1.0f;
    }

    @Override
    public float getProductionModifier(IBeeGenome genome, float current) {
        if (hasModifier(ItemModifierProduction.class)) {
            return 2.0f; // x2
        }
        return 1.0f;
    }

    @Override
    public float getFloweringModifier(IBeeGenome genome, float current) {
        return 1.0f;
    }

    @Override
    public boolean isSealed() {
        return false;
    }

    @Override
    public boolean isSelfLighted() {
        return false;
    }

    @Override
    public boolean isSunlightSimulated() {
        return false;
    }

    @Override
    public boolean isHellish() {
        return false;
    }
}
