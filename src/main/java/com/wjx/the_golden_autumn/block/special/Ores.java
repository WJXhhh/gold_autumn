package com.wjx.the_golden_autumn.block.special;

import com.wjx.the_golden_autumn.TheGoldenAutumnMod;
import com.wjx.the_golden_autumn.init.iteminit;
import com.wjx.the_golden_autumn.objects.item.blockbase;
import com.wjx.the_golden_autumn.util.interfaces.IHasModel;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

import javax.annotation.Nonnull;
import java.util.Random;

public class Ores {
    public static class AutumnJadeOre extends blockbase implements IHasModel{

        public AutumnJadeOre(String name, Material material, CreativeTabs tabs) {
            super(name, material, tabs);
            this.setHardness(3.5F);
            this.setHarvestLevel("pickaxe", 2);
        }
        @Nonnull
        @Override
        public Item getItemDropped(IBlockState state, Random rand, int fortune) {
            return iteminit.AUTU_JADE;
        }

        // SRG func_149745_a，用于决定掉落的物品数量
        @Override
        public int quantityDropped(Random random) {
            return 1;
        }

        // SRG func_149679_a，用于决定受时运影响时掉落的物品数量
        @Override
        public int quantityDroppedWithBonus(int fortune, Random random) {
            if (fortune > 0) {
                int bonusFactor = Math.max(random.nextInt(fortune + 2) - 1, 0);
                return this.quantityDropped(random) * (bonusFactor + 1);
            } else {
                return this.quantityDropped(random);
            }
        }

        @Override
        public void registerModels(){
            TheGoldenAutumnMod.proxy.registerItemRenderer(Item.getItemFromBlock(this),0,"inventory");
        }
    }
}
