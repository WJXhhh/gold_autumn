package com.wjx.the_golden_autumn.entity.render;

import com.wjx.the_golden_autumn.TheGoldenAutumnMod;
import com.wjx.the_golden_autumn.entity.EntityModel.Modelsuda;
import com.wjx.the_golden_autumn.entity.EntitySuda;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerHeldItem;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;

public class RenderSuda extends RenderLiving<EntitySuda> {
    public static final ResourceLocation TEXTURE = new ResourceLocation(TheGoldenAutumnMod.MODID + ":textures/entity/suda.png");

    public RenderSuda(RenderManager manager){
        super(manager,new Modelsuda(),0.5f);
        this.addLayer(new LayerHeldItem(this));
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(EntitySuda entity) {
        return TEXTURE;
    }

    @Override
    protected void applyRotations(EntitySuda entityLiving, float p_77043_2_, float rotationYaw, float partialTicks) {
        super.applyRotations(entityLiving, p_77043_2_, rotationYaw, partialTicks);
    }
}
