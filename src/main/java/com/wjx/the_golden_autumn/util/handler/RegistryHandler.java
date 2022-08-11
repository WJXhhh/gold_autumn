package com.wjx.the_golden_autumn.util.handler;

import com.wjx.the_golden_autumn.TheGoldenAutumnMod;
import com.wjx.the_golden_autumn.entity.BubbleGunDAN;
import com.wjx.the_golden_autumn.init.EntityInit;
import com.wjx.the_golden_autumn.init.blockinit;
import com.wjx.the_golden_autumn.init.iteminit;
import com.wjx.the_golden_autumn.util.interfaces.IHasModel;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.EntityEntryBuilder;
import net.minecraftforge.fml.common.registry.EntityRegistry;

@Mod.EventBusSubscriber
public class RegistryHandler {

    @SubscribeEvent
    public static void onItemRegister(RegistryEvent.Register<Item> event){
        event.getRegistry().registerAll(iteminit.ITEMS.toArray(new Item[0]));

    }

    @SubscribeEvent
    public static void onBlockRegister(RegistryEvent.Register<Block> event){
        event.getRegistry().registerAll(blockinit.Blocks.toArray(new Block[0]));

    }

    @SubscribeEvent
    public static void onEntityRegistation(RegistryEvent.Register<EntityEntry> event) {
        event.getRegistry().register(EntityEntryBuilder.create()
                .entity(BubbleGunDAN.class)
                .id(new ResourceLocation("the_golden_autumn", "bubble_gun_dan"), 233)
                .name("BubbleGunDan")
                .tracker(80, 3, false)
                .build()
        );
    }

    @SubscribeEvent
    public static void onModelRegister(ModelRegistryEvent event){
        for (Item item : iteminit.ITEMS){
            if(item instanceof IHasModel){
                ((IHasModel)item).registerModels();

            }
        }
        for (Block block : blockinit.Blocks){

            if (block instanceof IHasModel){
                ((IHasModel)block).registerModels();

            }
        }

        }
    public static void otherRegistries(){

    }
}
