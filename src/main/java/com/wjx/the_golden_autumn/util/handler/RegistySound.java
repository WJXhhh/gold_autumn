package com.wjx.the_golden_autumn.util.handler;

import com.wjx.the_golden_autumn.TheGoldenAutumnMod;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RegistySound {
    private static final String modid = TheGoldenAutumnMod.MODID;
    public static final List<String> Sounds = new ArrayList<>();
    public static Map<ResourceLocation, SoundEvent> sounds = new HashMap<>();

    static void addlist(){

        Sounds.add("qiuxiattacked");
        Sounds.add("qiuxis2");
        Sounds.add("qiuxis3");
        Sounds.add("qiuxia2");
        Sounds.add("sudahurt");
        Sounds.add("sudatalk");
        Sounds.add("sudatalk2");
        Sounds.add("chengzidead");
        Sounds.add("chengziattack");
        Sounds.add("chengzihurt1");
        Sounds.add("chengzihurt2");



    }

    public static void registerSounds(RegistryEvent.Register<net.minecraft.util.SoundEvent> event) {
        addlist();
        for(String soundsl:Sounds){
            sounds.put(new ResourceLocation(modid, soundsl), new net.minecraft.util.SoundEvent(new ResourceLocation(modid, soundsl)));
        }
        for (Map.Entry<ResourceLocation, net.minecraft.util.SoundEvent> sound : sounds.entrySet())
            event.getRegistry().register(sound.getValue().setRegistryName(sound.getKey()));
    }

}
