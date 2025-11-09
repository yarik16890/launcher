package com.yourname.multapiary.apiculture;

import com.mojang.authlib.GameProfile;
import com.yourname.multapiary.tileentity.TileEntityMultiApiary;
import forestry.api.apiculture.*;
import forestry.api.core.EnumHumidity;
import forestry.api.core.EnumTemperature;
import forestry.api.core.IErrorLogic;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import java.util.Collections;

public class BeeHousing implements IBeeHousing {
    private final TileEntityMultiApiary tile;
    private int currentSlot = 0;

    public BeeHousing(TileEntityMultiApiary tile) {
        this.tile = tile;
    }

    public void setCurrentSlot(int slot) {
        this.currentSlot = slot;
    }

    @Override
    public Iterable<IBeeModifier> getBeeModifiers() {
        return Collections.singletonList(tile.getBeeModifier());
    }

    @Override
    public Iterable<IBeeListener> getBeeListeners() {
        return Collections.singletonList(tile.getBeeListener());
    }

    @Override
    public IBeeHousingInventory getBeeInventory() {
        return tile.getBeeInventory();
    }

    @Override
    public IBeekeepingLogic getBeekeepingLogic() {
        return BeeManager.beeRoot.createBeekeepingLogic(this);
    }

    @Override
    public EnumTemperature getTemperature() {
        return EnumTemperature.getFromValue(getBiome().temperature);
    }

    @Override
    public EnumHumidity getHumidity() {
        return EnumHumidity.getFromValue(getBiome().rainfall);
    }

    @Override
    public int getBlockLightValue() {
        return tile.getWorldObj().getBlockLightValue(tile.xCoord, tile.yCoord + 1, tile.zCoord);
    }

    @Override
    public boolean canBlockSeeTheSky() {
        return tile.getWorldObj().canBlockSeeTheSky(tile.xCoord, tile.yCoord + 1, tile.zCoord);
    }

    @Override
    public World getWorld() {
        return tile.getWorldObj();
    }

    @Override
    public BiomeGenBase getBiome() {
        return tile.getWorldObj().getBiomeGenForCoords(tile.xCoord, tile.zCoord);
    }

    @Override
    public GameProfile getOwner() {
        return tile.getOwner();
    }

    @Override
    public Vec3 getBeeFXCoordinates() {
        return Vec3.createVectorHelper(tile.xCoord + 0.5, tile.yCoord + 0.5, tile.zCoord + 0.5);
    }

    @Override
    public IErrorLogic getErrorLogic() {
        return tile.getErrorLogic();
    }

    @Override
    public ChunkCoordinates getCoordinates() {
        return new ChunkCoordinates(tile.xCoord, tile.yCoord, tile.zCoord);
    }
}
