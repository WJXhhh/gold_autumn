package com.wjx.the_golden_autumn.item;

import com.wjx.the_golden_autumn.TheGoldenAutumnMod;
import com.wjx.the_golden_autumn.event.updatecolor;
import com.wjx.the_golden_autumn.objects.item.itembase;
import com.wjx.the_golden_autumn.util.interfaces.IHasModel;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

import static com.wjx.the_golden_autumn.TheGoldenAutumnMod.TabGold;

public class ItemBottledAutumn extends itembase implements IHasModel {
    public ItemBottledAutumn() {
        super("bottled_autumn", TabGold,1);
    }


    @Override
    public String getItemStackDisplayName(ItemStack stack){
        return updatecolor.makeColour2(net.minecraft.util.text.translation.I18n.translateToLocal(getUnlocalizedName(stack)+".name"));
    }

    @SideOnly(Side.CLIENT)
    @Override
    public boolean hasEffect(ItemStack itemStack){
        return true;
    }

    public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
        super.addInformation(stack,worldIn,tooltip,flagIn);
        tooltip.add(I18n.format("item.desc.bottleautu"));
    }
    @Override
    public void registerModels(){
        TheGoldenAutumnMod.proxy.registerItemRenderer(this,0,"inventory");

    }
}
