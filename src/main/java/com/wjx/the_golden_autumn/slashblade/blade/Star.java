package com.wjx.the_golden_autumn.slashblade.blade;

import com.mojang.realmsclient.gui.ChatFormatting;
import com.wjx.the_golden_autumn.event.updatecolor;
import com.wjx.the_golden_autumn.init.iteminit;
import com.wjx.the_golden_autumn.slashblade.BladeLoader;
import com.wjx.the_golden_autumn.slashblade.blade.Items.Item_Star;
import com.wjx.the_golden_autumn.slashblade.slashUtil.BladeUtils;
import com.wjx.the_golden_autumn.slashblade.specialattack.MeteoriteSword;
import mods.flammpfeil.slashblade.ItemSlashBladeNamed;
import mods.flammpfeil.slashblade.SlashBlade;
import mods.flammpfeil.slashblade.item.ItemSlashBlade;
import mods.flammpfeil.slashblade.named.event.LoadEvent;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Enchantments;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ITickable;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.ForgeRegistry;

import java.util.EnumSet;
import java.util.List;

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
    static int sSC = 14623772;

   @SubscribeEvent
    public void init(LoadEvent.InitEvent event) {


        //ItemStack customblade = new ItemStack(STAR,1,0);
        //NBTTagCompound tag = new NBTTagCompound();
        //customblade.setTagCompound(tag);

            tag.setBoolean("Unbreakable",true);
            Item_Star.CurrentItemName.set(tag, name);
            Item_Star.CustomMaxDamage.set(tag, Integer.MAX_VALUE);

            ItemSlashBlade.TextureName.set(tag, "autumn/Universe/tex");
            ItemSlashBlade.ModelName.set(tag, "autumn/Universe/mdl");
            ItemSlashBlade.ProudSoul.set(tag, 10000);
            ItemSlashBlade.KillCount.set(tag, 10000);
       customblade.addEnchantment(Enchantments.UNBREAKING, 100);
       customblade.addEnchantment(Enchantments.SHARPNESS, 100);
       customblade.addEnchantment(Enchantments.POWER, 100);

       tag.setInteger("HideFlags",1);
       tag.setFloat("baseAttackModifier",32768.0F);
       ItemSlashBlade.BaseAttackModifier.set(tag, 32768.0F);
       ItemSlashBlade.setBaseAttackModifier(tag,32768.0F);
            ItemSlashBlade.SpecialAttackType.set(tag, 257);
            Item_Star.IsDefaultBewitched.set(tag, true);
            ItemSlashBlade.SummonedSwordColor.set(tag, sSC);
            BladeUtils.NamedBlades.add(this.name);
            ItemSlashBlade.StandbyRenderType.set(tag, 1);
            BladeUtils.registerCustomItemStack(this.name, customblade);
            System.out.println("autumn:starinit");
        }

    @SubscribeEvent
    public void update(TickEvent.PlayerTickEvent event) {
        if (sSC == 14623772){
            sSC = 10532312;
        }
        else if (sSC == 10532312){
            sSC = 14623772;
        }

        tag.setInteger("SummonedSwordColor",sSC);
    }
}









