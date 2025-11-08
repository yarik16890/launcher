package com.yourname.multapiary;

import com.yourname.multapiary.proxy.CommonProxy;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = "multapiary", name = "Multi-Apiary", version = "1.0.0", dependencies = "required-after:Forestry")
public class MultiApiaryMod {

    public static final String MODID = "multapiary";
    public static final String NAME = "Multi-Apiary";
    public static final String VERSION = "1.0.0";

    @Mod.Instance(MODID)
    public static MultiApiaryMod instance;

    @SidedProxy(clientSide = "com.yourname.multapiary.proxy.ClientProxy", serverSide = "com.yourname.multapiary.proxy.CommonProxy")
    public static CommonProxy proxy;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        proxy.preInit();
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init();
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        // Cross-mod compatibility, etc.
    }
}
