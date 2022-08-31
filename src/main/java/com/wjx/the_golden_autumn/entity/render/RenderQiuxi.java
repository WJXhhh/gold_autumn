package com.wjx.the_golden_autumn.entity.render;

import com.wjx.the_golden_autumn.TheGoldenAutumnMod;
import com.wjx.the_golden_autumn.entity.EntityModel.Modelqiuxi;
import com.wjx.the_golden_autumn.entity.Qiuxi;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerHeldItem;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;

public class RenderQiuxi extends RenderLiving<Qiuxi> {
    public static final ResourceLocation TEXTURE = new ResourceLocation(TheGoldenAutumnMod.MODID + ":textures/entity/qiuxiskin.png");

    public RenderQiuxi(RenderManager manager){
        super(manager,new Modelqiuxi(),0.5f);
        this.addLayer(new LayerHeldItem(this));
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(Qiuxi entity) {
        return TEXTURE;
    }

    @Override
    protected void applyRotations(Qiuxi entityLiving, float p_77043_2_, float rotationYaw, float partialTicks) {
        super.applyRotations(entityLiving, p_77043_2_, rotationYaw, partialTicks);
    }
}
