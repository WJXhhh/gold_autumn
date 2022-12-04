package com.wjx.the_golden_autumn.init;

import com.wjx.the_golden_autumn.TheGoldenAutumnMod;
import com.wjx.the_golden_autumn.entity.*;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.fml.common.registry.EntityRegistry;

import java.util.ArrayList;
import java.util.List;

public class EntityInit {
    public static final List<Entity> Entities = new ArrayList<>();

    public static void registerEntities(){
        registerEntity("qiuxi", Qiuxi.class,13500001,50,16771432,16753920);
        registerEntity("suda", EntitySuda.class,13500002,50,10269947,46331);
        registerEntity("jaoshingan_i", Jaoshingan_I.class,13500003,50,0,16777215);
        registerEntity("jaoshingan_ii", Jaoshingan_II.class,13500004,50,0,16777215);
        registerEntity("jaoshingan_iii", Jaoshingan_III.class,13500005,50,0,16777215);
        registerEntity("chengzi",ChengZi.class,13500006,50,0xFF9F7F,0xF4A460);
        registerEntity("fruit_chengzi_thrown",FruitChengZiThrown.class,13500007,50);
    }

    //range ：追踪范围
    private static void registerEntity(String name, Class<? extends Entity> entity, int id,int range,int color1,int color2){
        EntityRegistry.registerModEntity(new ResourceLocation(TheGoldenAutumnMod.MODID + ":" + name),entity,name,id, TheGoldenAutumnMod.instance,range,1,true,color1,color2);
    }

    private static void registerEntitySpawn(Class<? extends Entity> entityClass, int spawnWeight, int min, int max, EnumCreatureType typeOfCreature, Biome... biomes)
    {
        if (EntityLiving.class.isAssignableFrom(entityClass))
        {
            Class<? extends EntityLiving> entityLivingClass = entityClass.asSubclass(EntityLiving.class);
            EntityRegistry.addSpawn(entityLivingClass, spawnWeight, min, max, typeOfCreature, biomes);
        }
    }
    private static void registerEntity(String name, Class<? extends Entity> entity, int id,int range){
        EntityRegistry.registerModEntity(new ResourceLocation(TheGoldenAutumnMod.MODID + ":" + name),entity,name,id, TheGoldenAutumnMod.instance,range,1,true);
    }
}
