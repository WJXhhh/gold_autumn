package com.wjx.the_golden_autumn.potion;

import com.wjx.the_golden_autumn.TheGoldenAutumnMod;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.attributes.AbstractAttributeMap;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;

import static net.minecraft.init.MobEffects.*;

public class CMoon extends Potion {
    public CMoon(){
        super(true,-3355444);
        setPotionName("effect.cmoon");
        setIconIndex(0,0);
        setRegistryName(new ResourceLocation(TheGoldenAutumnMod.MODID+":cmoon"));
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

            //EntityLivingBase thisen = (EntityLivingBase)entity;
            entity.setNoGravity(true);



        }


    }

    @Override
    public void removeAttributesModifiersFromEntity(EntityLivingBase entityLivingBaseIn, AbstractAttributeMap attributeMapIn, int amplifier) {
        super.removeAttributesModifiersFromEntity(entityLivingBaseIn, attributeMapIn, amplifier);
        entityLivingBaseIn.setNoGravity(false);
    }
}
