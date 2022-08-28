package com.wjx.the_golden_autumn.proxy;

import com.wjx.the_golden_autumn.TheGoldenAutumnMod;
import com.wjx.the_golden_autumn.slashblade.slashUtil.ItemSlashUtil;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import static com.wjx.the_golden_autumn.TheGoldenAutumnMod.logger;

public class ClientProxy extends CommonProxy{
    @Override
    public void registerItemRenderer(Item item, int meta, String id) {
        ModelLoader.setCustomModelResourceLocation(item,meta,new ModelResourceLocation(item.getRegistryName(),id));
    }

    @Override
    public void registerVariantRenderer(Item item,int meta,String filename,String id){
        ModelLoader.setCustomModelResourceLocation(item,meta,new ModelResourceLocation(new ResourceLocation(TheGoldenAutumnMod.MODID,filename),id));
    }

    @Override
    public void init(FMLInitializationEvent event) {
        super.init(event);
    }
    @Override
    public void preInit(FMLPreInitializationEvent event){
        super.preInit(event);
        if(Loader.isModLoaded("flammpfeil.slashblade"))
        {
            new ItemSlashUtil();
            System.out.println("autumn:loadutil");
        }
    }
    @Override
    public void postInit(FMLPostInitializationEvent event){
        super.postInit(event);

    }
}
