package com.wjx.the_golden_autumn.item.debug;

import com.wjx.the_golden_autumn.TheGoldenAutumnMod;
import com.wjx.the_golden_autumn.objects.item.itembase;
import com.wjx.the_golden_autumn.util.interfaces.IHasModel;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.network.NetHandlerPlayServer;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

import java.util.List;

import static com.wjx.the_golden_autumn.TheGoldenAutumnMod.TabGold;

public class ItemExit extends itembase implements IHasModel {
    public ItemExit(){
        super("debug_exit", TabGold);
    }
    public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
        super.addInformation(stack,worldIn,tooltip,flagIn);
        tooltip.add("if you right click,your game will exit");
    }

    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
    {

        super.onItemRightClick(worldIn,playerIn,handIn);
        if(playerIn instanceof EntityPlayerMP){
            ((EntityPlayerMP)playerIn).connection.disconnect(new TextComponentString(I18n.format("debug.disconnect")));
        }
        //System.exit(0);
        return new ActionResult<>(EnumActionResult.PASS, playerIn.getHeldItem(handIn));
    }

    @Override
    public void registerModels(){
        TheGoldenAutumnMod.proxy.registerItemRenderer(this,0,"inventory");

    }
}
