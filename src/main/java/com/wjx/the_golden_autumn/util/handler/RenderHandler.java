package com.wjx.the_golden_autumn.util.handler;

import com.wjx.the_golden_autumn.entity.*;
import com.wjx.the_golden_autumn.entity.render.*;

import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class RenderHandler {
    public static void registerEntityRenders(){
        RenderingRegistry.registerEntityRenderingHandler(Qiuxi.class, RenderQiuxi::new);
        RenderingRegistry.registerEntityRenderingHandler(EntitySuda.class, RenderSuda::new);
        RenderingRegistry.registerEntityRenderingHandler(Jaoshingan_I.class, RenderJaoshingan_I::new);
        RenderingRegistry.registerEntityRenderingHandler(Jaoshingan_II.class, RenderJaoshingan_I::new);
        RenderingRegistry.registerEntityRenderingHandler(Jaoshingan_III.class,RenderJaoshinbos::new);
        RenderingRegistry.registerEntityRenderingHandler(ChengZi.class,RenderChengZi::new);
        RenderingRegistry.registerEntityRenderingHandler(FruitChengZiThrown.class,RenderFruitChengZiThrown::new);
    }
}
