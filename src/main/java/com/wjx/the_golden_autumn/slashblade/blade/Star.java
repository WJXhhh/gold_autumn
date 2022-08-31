package com.wjx.the_golden_autumn.slashblade.blade;

import com.wjx.the_golden_autumn.slashblade.blade.Items.Item_Star;
import com.wjx.the_golden_autumn.slashblade.slashUtil.BladeUtils;
import mods.flammpfeil.slashblade.item.ItemSlashBlade;
import mods.flammpfeil.slashblade.named.event.LoadEvent;
import net.minecraft.init.Enchantments;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import static com.wjx.the_golden_autumn.slashblade.BladeLoader.STAR;

public class Star{

    String name = "wjx.blade.stredgeuniverse";
    String key = "wjx.blade.stredgeuniverse";



    public Star(){
        MinecraftForge.EVENT_BUS.register(this);
    }
    private ItemStack customblade = new ItemStack(STAR,1,0);

    private NBTTagCompound tag = new NBTTagCompound();
    {
        customblade.setTagCompound(tag);
    }
    static int sSC = 16718929;

   @SubscribeEvent
    public void init(LoadEvent.InitEvent event) {


        //ItemStack customblade = new ItemStack(STAR,1,0);
        //NBTTagCompound tag = new NBTTagCompound();
        //customblade.setTagCompound(tag);

            tag.setBoolean("Unbreakable",true);
            Item_Star.CurrentItemName.set(tag, name);
            Item_Star.CustomMaxDamage.set(tag, 888888888);

            ItemSlashBlade.TextureName.set(tag, "autumn/Universe/tex");
            ItemSlashBlade.ModelName.set(tag, "autumn/Universe/mdl");
            ItemSlashBlade.ProudSoul.set(tag, 888888888);
            ItemSlashBlade.KillCount.set(tag, 888888);
       customblade.addEnchantment(Enchantments.UNBREAKING, 100);
       customblade.addEnchantment(Enchantments.SHARPNESS, 100);
       customblade.addEnchantment(Enchantments.POWER, 100);
       customblade.addEnchantment(Enchantments.LOOTING,100);
       customblade.addEnchantment(Enchantments.INFINITY,100);

       tag.setInteger("HideFlags",1);



       customblade.getTagCompound().setBoolean("isUniverseBlade",true);
       //ItemSlashBlade.BaseAttackModifier.set(tag, 32768.0F);
       //ItemSlashBlade.setBaseAttackModifier(tag,32768.0F);
       customblade.getTagCompound().setFloat("baseAttackModifier",32768.0F);
            ItemSlashBlade.SpecialAttackType.set(tag, 257);
            Item_Star.IsDefaultBewitched.set(tag, true);
            ItemSlashBlade.SummonedSwordColor.set(tag, sSC);
            BladeUtils.NamedBlades.add(this.name);
            ItemSlashBlade.StandbyRenderType.set(tag, 1);
            BladeUtils.registerCustomItemStack(this.name, customblade);
            System.out.println("autumn:starinit");
        }


}
