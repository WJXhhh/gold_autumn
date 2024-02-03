package com.wjx.the_golden_autumn.item;

import com.wjx.the_golden_autumn.TheGoldenAutumnMod;
import com.wjx.the_golden_autumn.event.updatecolor;
import com.wjx.the_golden_autumn.objects.item.itembase;
import com.wjx.the_golden_autumn.util.interfaces.IHasModel;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

import static com.wjx.the_golden_autumn.TheGoldenAutumnMod.TabGold;

public class ItemKnightOfMaple extends itembase implements IHasModel {
    public ItemKnightOfMaple() {
        super("knightofmaple", TabGold);
    }

    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
        super.addInformation(stack,worldIn,tooltip,flagIn);
        tooltip.add(I18n.format("item.desc.knight"));
    }

    public String getItemStackDisplayName(ItemStack stack){
        return updatecolor.makeColour2(net.minecraft.util.text.translation.I18n.translateToLocal(getUnlocalizedName(stack)+".name"));
    }

    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker){
        boolean thereturn = super.hitEntity(stack,target,attacker);
        attacker.startRiding(target);
        return thereturn;
    }

    @Override
    public void registerModels(){
        TheGoldenAutumnMod.proxy.registerItemRenderer(this,0,"inventory");

    }
}
