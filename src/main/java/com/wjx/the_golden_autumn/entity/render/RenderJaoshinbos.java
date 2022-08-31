package com.wjx.the_golden_autumn.entity.render;

import com.wjx.the_golden_autumn.TheGoldenAutumnMod;
import com.wjx.the_golden_autumn.entity.EntityModel.Modeljaoboss;
import com.wjx.the_golden_autumn.entity.EntityModel.Modeljaozc;
import com.wjx.the_golden_autumn.entity.Jaoshingan_III;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerHeldItem;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;

public class RenderJaoshinbos extends RenderLiving<Jaoshingan_III> {
    public static final ResourceLocation TEXTURE = new ResourceLocation(TheGoldenAutumnMod.MODID + ":textures/entity/xiao_niao_you_shang_.png");

    public RenderJaoshinbos(RenderManager manager) {
        super(manager, new Modeljaoboss(), 0.5f);
        this.addLayer(new LayerHeldItem(this));
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(Jaoshingan_III entity) {
        return TEXTURE;
    }

    @Override
    protected void applyRotations(Jaoshingan_III entityLiving, float p_77043_2_, float rotationYaw, float partialTicks) {
        super.applyRotations(entityLiving, p_77043_2_, rotationYaw, partialTicks);
    }
    @Override
    protected void preRenderCallback(Jaoshingan_III entitylivingbaseIn, float partialTickTime)
    {
        GlStateManager.scale(4, 4, 4);
    }
}
