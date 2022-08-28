package com.wjx.the_golden_autumn.init;

import com.wjx.the_golden_autumn.block.BlockQiu_Rose;
import com.wjx.the_golden_autumn.block.BlockQiuxiBiologicalActivityReducer;
import com.wjx.the_golden_autumn.block.BlockXi_rose;
import net.minecraft.block.Block;

import java.util.ArrayList;
import java.util.List;

public class blockinit {
    public static final List<Block> Blocks = new ArrayList<>();

    public static final Block QIUROSE = new BlockQiu_Rose();
    public static final Block XIROSE = new BlockXi_rose();
    public static final Block QIUXIBIOREDUCER = new BlockQiuxiBiologicalActivityReducer.CustomBlock();
}
