package com.wjx.the_golden_autumn.proxy;

import com.wjx.the_golden_autumn.TheGoldenAutumnMod;
import com.wjx.the_golden_autumn.WorldEvent;
import com.wjx.the_golden_autumn.slashblade.BladeLoader;
import com.wjx.the_golden_autumn.slashblade.blade.Star;
import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import static com.wjx.the_golden_autumn.TheGoldenAutumnMod.logger;
import static mods.flammpfeil.slashblade.SlashBlade.InitEventBus;

public class CommonProxy {
    public void registerItemRenderer(Item item, int meta, String id){

    }
    public void registerVariantRenderer(Item item,int meta,String filename,String id){}

    public void init(FMLInitializationEvent event){
        new WorldEvent();
        new Star();
    }


    public void preInit(FMLPreInitializationEvent event) {
        if(Loader.isModLoaded("flammpfeil.slashblade")){
            //SlashEvent(this);


        }
        InitEventBus.register(this);
        MinecraftForge.EVENT_BUS.register(this);

        InitEventBus.register(TheGoldenAutumnMod.instance);
        boolean specialAttacksId = true;

        new BladeLoader();
        System.out.println("autumn:loadbladecommon");
    }
/*
    @Optional.Method(modid = "flammpfeil.slashblade")
    public void SlashEvent(Object event){


    }*/


    public void postInit(FMLPostInitializationEvent event) {
    }
}
