package com.wjx.the_golden_autumn.init;

import com.wjx.the_golden_autumn.potion.ZhuangLie;
import net.minecraft.potion.Potion;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class PotionInit {

    public static Potion GETOLD = new ZhuangLie();

    public static void registerPotion(){
        ForgeRegistries.POTIONS.register(GETOLD);
    }
}
