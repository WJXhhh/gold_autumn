package com.wjx.the_golden_autumn.init;

import com.wjx.the_golden_autumn.potion.ZhuangLie;
import net.minecraft.block.Block;
import net.minecraft.potion.Potion;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

import java.util.ArrayList;
import java.util.List;

public class PotionInit {

    public static final List<Potion> Potitions = new ArrayList<>();
    public static Potion GETOLD = new ZhuangLie();
    static {
        Potitions.add(GETOLD);
    }

    public static void registerPotion(){

        for (Potion potition : Potitions) {
            ForgeRegistries.POTIONS.registerAll(potition);
        }

    }
}
