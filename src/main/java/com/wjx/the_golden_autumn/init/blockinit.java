package com.wjx.the_golden_autumn.init;

import com.wjx.the_golden_autumn.block.*;
import com.wjx.the_golden_autumn.block.machines.MachineLianQi;
import com.wjx.the_golden_autumn.block.special.Ores;
import com.wjx.the_golden_autumn.tileEntity.TileEntityMachineLianQi;
import com.wjx.the_golden_autumn.util.Reference;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

import java.util.ArrayList;
import java.util.List;

import static com.wjx.the_golden_autumn.TheGoldenAutumnMod.TabGold;

public class blockinit {
    public static final List<Block> Blocks = new ArrayList<>();

    public static final Block QIUROSE = new BlockQiu_Rose();
    public static final Block XIROSE = new BlockXi_rose();
    public static final Block QIUXIBIOREDUCER = new BlockQiuxiBiologicalActivityReducer.CustomBlock();
    public static final Block QIUXIGRAVITY = new BlockQiuxiGravityController.CustomBlock();
    public static final Block QIUXITIMEY = new QiuxiTimeyWimeyController.CustomBlock();
    public static final Block QIUXIWEATHER_RAIN = new QiuxiWeatherControllerRain.CustomBlock();
    public static final Block QIUXIWEATHER_CLEAR = new QiuxiWeatherControllerClear.CustomBlock();
    public static final Block QIUXIWEATHER_THUNDER = new QiuxiWeatherControllerThunder.CustomBlock();
    public static final Block HANDA = new TheHandaBlock.CustomBlock();
    public static final Block QIUXIDRILLING=new QiuxiWorldDrillingMachine.CustomBlock();
    public static final Block QIUXIROSE = new BlockQiuXiRose();
    public static final Block AUTUJADE = new Ores.AutumnJadeOre("autujade_ore", Material.ROCK,TabGold);
    public static final Block LIANQI = new MachineLianQi("lianqi",TabGold, Reference.GUI_ID_LIANQI,new TileEntityMachineLianQi());
}
