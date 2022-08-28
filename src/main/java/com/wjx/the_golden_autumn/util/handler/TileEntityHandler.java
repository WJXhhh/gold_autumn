package com.wjx.the_golden_autumn.util.handler;

import com.wjx.the_golden_autumn.block.BlockQiuxiBiologicalActivityReducer;
import com.wjx.the_golden_autumn.block.BlockQiuxiGravityController;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class TileEntityHandler {
    public static void registerTileEntity(){
        GameRegistry.registerTileEntity(BlockQiuxiBiologicalActivityReducer.CustomTileEntity.class,"qiuxi_reducer");
        GameRegistry.registerTileEntity(BlockQiuxiGravityController.CustomTileEntity.class,"qiuxi_gravity");

    }
}
