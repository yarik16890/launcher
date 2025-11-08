package com.yourname.multapiary.proxy;

import com.yourname.multapiary.MultiApiaryMod;
import com.yourname.multapiary.blocks.BlockMultiApiary;
import com.yourname.multapiary.items.ItemApiaryModifier;
import com.yourname.multapiary.items.ItemModifierLifespan;
import com.yourname.multapiary.items.ItemModifierMultiFlower;
import com.yourname.multapiary.items.ItemModifierProduction;
import com.yourname.multapiary.tileentity.TileEntityMultiApiary;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class CommonProxy {

    public static Block blockMultiApiary;
    public static Item itemModifierProduction;
    public static Item itemModifierLifespan;
    public static Item itemModifierMultiFlower;

    public void preInit() {
        // ... (Block and Item registration)
    }

    public void init() {
        // Register GUI Handler, etc.

        // Register Recipes
        GameRegistry.addShapedRecipe(new ItemStack(blockMultiApiary),
                "WWW", "HCH", "WWW",
                'W', new ItemStack(Blocks.planks),
                'H', new ItemStack(Items.honeycomb), // Assuming Forestry adds honeycomb item
                'C', new ItemStack(Blocks.chest)
        );

        GameRegistry.addShapedRecipe(new ItemStack(itemModifierProduction),
                " D ", "DQD", " D ",
                'D', new ItemStack(Items.diamond),
                'Q', new ItemStack(Items.quartz)
        );

        GameRegistry.addShapedRecipe(new ItemStack(itemModifierLifespan),
                " E ", "EGE", " E ",
                'E', new ItemStack(Items.emerald),
                'G', new ItemStack(Items.glowstone_dust)
        );

        GameRegistry.addShapedRecipe(new ItemStack(itemModifierMultiFlower),
                " R ", "RFR", " R ",
                'R', new ItemStack(Items.redstone),
                'F', new ItemStack(Blocks.red_flower)
        );
    }
}
