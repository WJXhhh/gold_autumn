package com.wjx.the_golden_autumn.world.gen;

import com.wjx.the_golden_autumn.init.blockinit;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.Random;

public class Oregen_1 implements IWorldGenerator {
    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {

        if (world.provider.getDimension() == 0){
            generateOverworld(random, chunkX, chunkZ, world, chunkGenerator, chunkProvider);
        }
    }
    //在世界生成矿物的信息
    private void generateOverworld(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator,IChunkProvider chunkProvider) {

        generateOre(blockinit.AUTUJADE.getDefaultState(), world, random, chunkX*16, chunkZ*16, 40, 80, random.nextInt(2)+3, 10);


    }
    //生成的矿物   生成矿物的世界 生成数(随机的)  生成的x ,z坐标   Y最小,Y最大坐标在(minY,maxY)高度区间中生成矿石 矿脉大小 生成概率
    private void generateOre(IBlockState ore, World world, Random random, int x, int z, int minY, int maxY, int size, int chances) {
        int deltaY = maxY - minY;
        for (int i = 0; i < chances; i++)
        {
            BlockPos pos = new BlockPos(x+random.nextInt(16), minY+random.nextInt(deltaY), z+random.nextInt(16));

            WorldGenMinable generator = new WorldGenMinable(ore, size);
            generator.generate (world, random, pos);
        }
    }
}
