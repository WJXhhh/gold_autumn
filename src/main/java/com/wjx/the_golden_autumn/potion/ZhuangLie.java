package com.wjx.the_golden_autumn.potion;

import com.wjx.the_golden_autumn.TheGoldenAutumnMod;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import static net.minecraft.init.MobEffects.*;

public class ZhuangLie extends Potion {

    public ZhuangLie(){
        super(true,-3355444);
        setPotionName("effect.getold");
        setIconIndex(0,0);
        setRegistryName(new ResourceLocation(TheGoldenAutumnMod.MODID+":getold"));
    }

    @Override
    public boolean hasStatusIcon() {
        return false;
    }





         public boolean isBeneficial(){
        return false;
         }
         public boolean isInstant(){
        return false;
         }
    public boolean shouldRenderInvText(PotionEffect effect) {
        return true;
    }

    public boolean shouldRender(PotionEffect effect) {
        return true;
    }

    public boolean shouldRenderHUD(PotionEffect effect) {
        return true;
    }

    @Override
    public boolean isReady(int duration, int amplifier) {
        return true;
    }

    public void performEffect(EntityLivingBase entity, int amplifier){
        super.performEffect(entity,amplifier);
        if ((entity instanceof EntityLivingBase)) {

                EntityLivingBase thisen = (EntityLivingBase)entity;
                thisen.addPotionEffect(new PotionEffect(SLOWNESS,20,4,false,false));
                thisen.addPotionEffect(new PotionEffect(MINING_FATIGUE,20,4,false,false));
                thisen.addPotionEffect(new PotionEffect(WEAKNESS,20,4,false,false));



        }


    }

}
