package com.wjx.the_golden_autumn.slashblade.blade;

import com.wjx.the_golden_autumn.TheGoldenAutumnMod;
import com.wjx.the_golden_autumn.slashblade.AutumnRecipeBlade;
import com.wjx.the_golden_autumn.slashblade.blade.Items.Item_Star;
import com.wjx.the_golden_autumn.slashblade.slashUtil.BladeUtils;
import mods.flammpfeil.slashblade.ItemSlashBladeNamed;
import mods.flammpfeil.slashblade.SlashBlade;
import mods.flammpfeil.slashblade.item.ItemSlashBlade;
import mods.flammpfeil.slashblade.named.Yamato;
import mods.flammpfeil.slashblade.named.event.LoadEvent;
import net.minecraft.init.Blocks;
import net.minecraft.init.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import com.wjx.the_golden_autumn.init.iteminit;

import static com.wjx.the_golden_autumn.slashblade.BladeLoader.STAR;
import static mods.flammpfeil.slashblade.SlashBlade.bladeNamed;

public class Youto {
    String name = "wjx.blade.youtou";
    String key = "wjx.blade.youtou";



    public Youto(){
        MinecraftForge.EVENT_BUS.register(this);
    }
    private ItemStack customblade = new ItemStack(bladeNamed,1,0);

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
        ItemSlashBladeNamed.CurrentItemName.set(tag, name);
        ItemSlashBladeNamed.CustomMaxDamage.set(tag, 888888888);

        ItemSlashBlade.TextureName.set(tag, "autumn/Youto/tex");
        ItemSlashBlade.ModelName.set(tag, "autumn/Youto/mdl");
        ItemSlashBlade.ProudSoul.set(tag, 1000);
        ItemSlashBlade.setBaseAttackModifier(tag,20);
        //ItemSlashBlade.KillCount.set(tag, 0);
        customblade.addEnchantment(Enchantments.UNBREAKING, 1);
        customblade.addEnchantment(Enchantments.SHARPNESS, 3);
        customblade.addEnchantment(Enchantments.POWER, 1);
        //customblade.addEnchantment(Enchantments.LOOTING,100);
        //customblade.addEnchantment(Enchantments.INFINITY,100);

        //tag.setInteger("HideFlags",1);



        customblade.getTagCompound().setBoolean("isYouto",true);
        //ItemSlashBlade.BaseAttackModifier.set(tag, 32768.0F);
        //ItemSlashBlade.setBaseAttackModifier(tag,32768.0F);
        customblade.getTagCompound().setFloat("baseAttackModifier",20.0F);
        //ItemSlashBlade.SpecialAttackType.set(tag, 257);
        ItemSlashBladeNamed.IsDefaultBewitched.set(tag, true);
        ItemSlashBlade.SummonedSwordColor.set(tag, sSC);
        BladeUtils.NamedBlades.add(this.name);
        ItemSlashBlade.StandbyRenderType.set(tag, 1);
        BladeUtils.registerCustomItemStack(this.name, customblade);
        //System.out.println("autumn:starinit");




        ItemStack sphereSoul = BladeUtils.findItemStack("flammpfeil.slashblade", "sphere_bladesoul", 1);
        ItemStack ingotSoul = BladeUtils.findItemStack("flammpfeil.slashblade", "ingot_bladesoul", 1);

        //ItemStack materialBladeA = BladeUtils.findItemStack(NegoreRouse.MODID, materialNameA, 1);
        //NBTTagCompound reqTag = ItemSlashBlade.getItemTagCompound(materialBladeA);
        //ItemSlashBlade.KillCount.set(reqTag, 1000);
       // ItemSlashBlade.ProudSoul.set(reqTag, 2000);
        //ItemStack materialBladeB = BladeUtils.findItemStack("flammpfeil.slashblade", materialNameB, 1);
        ItemStack blackblade = BladeUtils.findItemStack(TheGoldenAutumnMod.MODID, name, 1);
        ItemStack adsd = BladeUtils.findItemStack(SlashBlade.modid, "flammpfeil.slashblade.named.yamato",1);
        IRecipe recipe = new AutumnRecipeBlade(new ResourceLocation(TheGoldenAutumnMod.MODID,"youtou"),
                blackblade, adsd,
                new Object[]{
                        "ZC ",
                        "SBS",
                        " DH",
                        'Z', sphereSoul,
                        'C', new ItemStack(iteminit.KNIGHT_OF_MAPLE),
                        'S', new ItemStack(Blocks.DIAMOND_BLOCK),
                        'H', ingotSoul,
                        'B', adsd,
                        'D', new ItemStack(iteminit.PEACE_OF_MAPLE),});

        SlashBlade.addRecipe("youtou", recipe);

    }
}
