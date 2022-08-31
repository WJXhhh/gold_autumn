package com.wjx.the_golden_autumn.entity.render;

import com.wjx.the_golden_autumn.TheGoldenAutumnMod;
import com.wjx.the_golden_autumn.entity.EntityModel.Modeljaozc;
import com.wjx.the_golden_autumn.entity.EntityModel.Modelqiuxi;
import com.wjx.the_golden_autumn.entity.Qiuxi;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerHeldItem;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;

public class RenderJaoshingan_I<T extends EntityLiving> extends RenderLiving<T> {
    public static final ResourceLocation TEXTURE = new ResourceLocation(TheGoldenAutumnMod.MODID + ":textures/entity/xiao_niao_you_shang_.png");

    public RenderJaoshingan_I(RenderManager manager){
        super(manager,new Modeljaozc(),0.5f);
        this.addLayer(new LayerHeldItem(this));
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(T entity) {
        return TEXTURE;
    }

    @Override
    protected void applyRotations(T entityLiving, float p_77043_2_, float rotationYaw, float partialTicks) {
        super.applyRotations(entityLiving, p_77043_2_, rotationYaw, partialTicks);
    }
}
