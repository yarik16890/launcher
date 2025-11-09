package com.yourname.multapiary.core;

import com.yourname.multapiary.client.gui.GuiMultiApiary;
import com.yourname.multapiary.inventory.ContainerMultiApiary;
import com.yourname.multapiary.tileentity.TileEntityMultiApiary;
import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class GuiHandler implements IGuiHandler {

    public static final int MULTI_APIARY_GUI = 0;

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        TileEntity tileEntity = world.getTileEntity(x, y, z);
        if (tileEntity instanceof TileEntityMultiApiary) {
            return new ContainerMultiApiary(player.inventory, (TileEntityMultiApiary) tileEntity);
        }
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        TileEntity tileEntity = world.getTileEntity(x, y, z);
        if (tileEntity instanceof TileEntityMultiApiary) {
            return new GuiMultiApiary(player.inventory, (TileEntityMultiApiary) tileEntity);
        }
        return null;
    }
}
