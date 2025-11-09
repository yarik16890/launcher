package com.yourname.multapiary.proxy;

import com.yourname.multapiary.MultiApiaryMod;
import com.yourname.multapiary.blocks.BlockMultiApiary;
import com.yourname.multapiary.core.GuiHandler;
import com.yourname.multapiary.items.ItemModifierLifespan;
import com.yourname.multapiary.items.ItemModifierMultiFlower;
import com.yourname.multapiary.items.ItemModifierProduction;
import com.yourname.multapiary.tileentity.TileEntityMultiApiary;
import cpw.mods.fml.common.network.NetworkRegistry;
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
        // Register Blocks
        blockMultiApiary = new BlockMultiApiary();
        GameRegistry.registerBlock(blockMultiApiary, "multiApiary");

        // Register Items
        itemModifierProduction = new ItemModifierProduction();
        itemModifierLifespan = new ItemModifierLifespan();
        itemModifierMultiFlower = new ItemModifierMultiFlower();

        GameRegistry.registerItem(itemModifierProduction, "modifierProduction");
        GameRegistry.registerItem(itemModifierLifespan, "modifierLifespan");
        GameRegistry.registerItem(itemModifierMultiFlower, "modifierMultiFlower");

        // Register Tile Entities
        GameRegistry.registerTileEntity(TileEntityMultiApiary.class, MultiApiaryMod.MODID + ":tileMultiApiary");
    }

    public void init() {
        // Register GUI Handler
        NetworkRegistry.INSTANCE.registerGuiHandler(MultiApiaryMod.instance, new GuiHandler());

        // Register Recipes
        GameRegistry.addShapedRecipe(new ItemStack(blockMultiApiary),
                "WWW", "HCH", "WWW",
                'W', new ItemStack(Blocks.planks),
                'H', new ItemStack(Items.honeycomb),
                'C', new ItemStack(Blocks.chest)
        );
        // ... (other recipes)
    }
}
