package com.wjx.the_golden_autumn.entity.render;

import com.wjx.the_golden_autumn.entity.FruitChengZiThrown;
import com.wjx.the_golden_autumn.init.iteminit;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.item.Item;

public class RenderFruitChengZiThrown extends RenderSnowball<FruitChengZiThrown> {
    public RenderFruitChengZiThrown(RenderManager renderManagerIn) {
        super(renderManagerIn, iteminit.ORANGE_FRUIT, Minecraft.getMinecraft().getRenderItem());
    }
}
