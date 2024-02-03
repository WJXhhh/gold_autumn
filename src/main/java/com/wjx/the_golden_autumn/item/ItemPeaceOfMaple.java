package com.wjx.the_golden_autumn.item;

import com.wjx.the_golden_autumn.TheGoldenAutumnMod;
import com.wjx.the_golden_autumn.event.updatecolor;
import com.wjx.the_golden_autumn.objects.item.itembase;
import com.wjx.the_golden_autumn.util.interfaces.IHasModel;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

import static com.wjx.the_golden_autumn.TheGoldenAutumnMod.TabGold;

public class ItemPeaceOfMaple extends itembase implements IHasModel {

    public ItemPeaceOfMaple() {
        super("peaceofmaple", TabGold);
    }



    @Override
    public String getItemStackDisplayName(ItemStack stack){
        return updatecolor.makeColour2(net.minecraft.util.text.translation.I18n.translateToLocal(getUnlocalizedName(stack)+".name"));
    }

    /*public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
    {
        worldIn.findNearestEntityWithinAABB(,new AxisAlignedBB(new Vec3d(playerIn.posX,playerIn.posY,playerIn.posZ),new Vec3d(playerIn.posX+1,playerIn.posY+1,playerIn.posZ+1)));
        return new ActionResult<ItemStack>(EnumActionResult.PASS, playerIn.getHeldItem(handIn));
    }*/


    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
        super.addInformation(stack,worldIn,tooltip,flagIn);
        tooltip.add(I18n.format("item.desc.peacemaple"));
    }

   /* @Override
    public Multimap<String, AttributeModifier> getAttributeModifiers(EntityEquipmentSlot slot, ItemStack stack) {
        Multimap<String,AttributeModifier> multimap = super.getAttributeModifiers(slot,stack);
        if (slot == EntityEquipmentSlot.MAINHAND) {
            multimap.put(SharedMonsterAttributes.ATTACK_DAMAGE.getName(), new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "item_damage", -0.5D, 0));
            multimap.put(SharedMonsterAttributes.ATTACK_SPEED.getName(), new AttributeModifier(ATTACK_SPEED_MODIFIER, "item_attack_speed", -2.4D, 0));
        }
        return multimap;
    }*/


    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker){
        boolean thereturn = super.hitEntity(stack,target,attacker);
        if(target instanceof EntityTameable && attacker instanceof EntityPlayer){
            ((EntityTameable) target).setTamed(true);
            ((EntityTameable) target).setTamedBy((EntityPlayer) attacker);
        }
        return thereturn;
    }
    @Override
    public void registerModels(){
        TheGoldenAutumnMod.proxy.registerItemRenderer(this,0,"inventory");

    }

}
