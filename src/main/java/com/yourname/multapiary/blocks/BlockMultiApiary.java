package com.yourname.multapiary;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockMultiApiary extends BlockContainer {

    public BlockMultiApiary() {
        super(Material.wood);
        this.setBlockName("multiApiary");
        this.setBlockTextureName("multapiary:multi_apiary"); // 仮のテクスチャ名
        this.setCreativeTab(CreativeTabs.tabBlock); // 仮のクリエイティブタブ
    }

    @Override
    public TileEntity createNewTileEntity(World world, int metadata) {
        return new TileEntityMultiApiary();
    }
}
