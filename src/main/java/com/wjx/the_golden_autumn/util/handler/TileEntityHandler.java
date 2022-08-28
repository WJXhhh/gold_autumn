package com.wjx.the_golden_autumn.util.handler;

import com.wjx.the_golden_autumn.block.BlockQiuxiBiologicalActivityReducer;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class TileEntityHandler {
    public static void registerTileEntity(){
        GameRegistry.registerTileEntity(BlockQiuxiBiologicalActivityReducer.CustomTileEntity.class,"qiuxi_reducer");

    }
}
