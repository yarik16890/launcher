package com.yourname.multapiary.blocks;

import com.yourname.multapiary.MultiApiaryMod;
import com.yourname.multapiary.core.GuiHandler;
import com.yourname.multapiary.tileentity.TileEntityMultiApiary;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockMultiApiary extends BlockContainer {

    public BlockMultiApiary() {
        super(Material.wood);
        this.setBlockName("multiApiary");
        this.setBlockTextureName("multapiary:multi_apiary_side"); // Assuming a side texture
        this.setCreativeTab(CreativeTabs.tabRedstone); // Placeholder tab
    }

    @Override
    public TileEntity createNewTileEntity(World world, int metadata) {
        return new TileEntityMultiApiary();
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
        if (!world.isRemote) {
            player.openGui(MultiApiaryMod.instance, GuiHandler.MULTI_APIARY_GUI, world, x, y, z);
        }
        return true;
    }
}
