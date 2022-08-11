package com.wjx.the_golden_autumn.slashblade;

import mods.flammpfeil.slashblade.SlashBlade;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class BladeProxy {
    public void preInit(FMLPreInitializationEvent event) {
        SlashBlade.InitEventBus.register(this);

    }
    public void init(FMLInitializationEvent event) {

    }

    public void postInit(FMLPostInitializationEvent event) {
    }
}
