package com.wjx.the_golden_autumn.entity.render;

import com.wjx.the_golden_autumn.TheGoldenAutumnMod;
import com.wjx.the_golden_autumn.entity.ChengZi;
import com.wjx.the_golden_autumn.entity.EntityModel.Modelqiuxi;
import com.wjx.the_golden_autumn.entity.Qiuxi;
import net.minecraft.client.model.ModelPlayer;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerHeldItem;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;

public class RenderChengZi extends RenderLiving<ChengZi> {
    public static final ResourceLocation TEXTURE = new ResourceLocation(TheGoldenAutumnMod.MODID + ":textures/entity/chengzi.png");

    public RenderChengZi(RenderManager manager){
        super(manager,new ModelPlayer(0,true),0.5f);
        this.addLayer(new LayerHeldItem(this));
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(ChengZi entity) {
        return TEXTURE;
    }

    @Override
    protected void applyRotations(ChengZi entityLiving, float p_77043_2_, float rotationYaw, float partialTicks) {
        super.applyRotations(entityLiving, p_77043_2_, rotationYaw, partialTicks);
    }
}
