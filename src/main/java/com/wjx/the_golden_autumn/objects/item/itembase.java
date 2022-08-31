package com.wjx.the_golden_autumn.objects.item;

import com.wjx.the_golden_autumn.TheGoldenAutumnMod;
import com.wjx.the_golden_autumn.init.iteminit;
import com.wjx.the_golden_autumn.util.interfaces.IHasModel;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class itembase extends Item implements IHasModel {
    public itembase(String name, CreativeTabs tabs){
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(tabs);

        iteminit.ITEMS.add(this);
    }

    public itembase(String name, CreativeTabs tabs,int stackLimit){
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(tabs);
        setMaxStackSize(stackLimit);


        iteminit.ITEMS.add(this);
    }



    @Override
    public void registerModels(){
        TheGoldenAutumnMod.proxy.registerItemRenderer(this,0,"inventory");

    }
}
