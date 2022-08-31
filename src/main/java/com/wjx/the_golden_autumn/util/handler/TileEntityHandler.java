package com.wjx.the_golden_autumn.util.handler;

import com.wjx.the_golden_autumn.block.*;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class TileEntityHandler {
    public static void registerTileEntity(){
        GameRegistry.registerTileEntity(BlockQiuxiBiologicalActivityReducer.CustomTileEntity.class,"qiuxi_reducer");
        GameRegistry.registerTileEntity(BlockQiuxiGravityController.CustomTileEntity.class,"qiuxi_gravity");
        GameRegistry.registerTileEntity(QiuxiTimeyWimeyController.CustomTile.class,"qiuxi_timey");
        GameRegistry.registerTileEntity(QiuxiWeatherControllerRain.CustomTile.class,"qiuxi_weather_rain");
        GameRegistry.registerTileEntity(QiuxiWeatherControllerClear.CustomTile.class,"qiuxi_weather_clear");
        GameRegistry.registerTileEntity(QiuxiWeatherControllerThunder.CustomTile.class,"qiuxi_weather_thunder");
        GameRegistry.registerTileEntity(TheHandaBlock.CustomTile.class,"qiuxi_world_drilling_machine_module");
        GameRegistry.registerTileEntity(QiuxiWorldDrillingMachine.CustomTile.class,"qiuxi_world_drilling_machine");

    }
}
