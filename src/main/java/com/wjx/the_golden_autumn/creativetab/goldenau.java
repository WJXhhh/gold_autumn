package com.wjx.the_golden_autumn.creativetab;

import com.wjx.the_golden_autumn.event.updatecolor;
import com.wjx.the_golden_autumn.init.iteminit;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.translation.I18n;

public class goldenau extends CreativeTabs {
    public goldenau(String label) {

        super("goldenau");
        setBackgroundImageName("gold.png");
        
    }

    public ItemStack getTabIconItem(){
        return new ItemStack(iteminit.NONE_ICON);
    }

    @Override
    public String getTranslatedTabLabel(){
        return updatecolor.makeColour2(I18n.translateToLocal("itemGroup.goldenau"));
    }
}
