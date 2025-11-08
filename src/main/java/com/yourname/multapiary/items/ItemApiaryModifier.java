package com.yourname.multapiary.items;

import net.minecraft.item.Item;

public class ItemApiaryModifier extends Item {

    public ItemApiaryModifier(String name) {
        this.setUnlocalizedName(name);
        this.setTextureName("multapiary:" + name);
        // this.setCreativeTab(MultiApiaryMod.creativeTab); // Add creative tab later
    }
}
