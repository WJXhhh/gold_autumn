package com.wjx.the_golden_autumn.slashblade.slashUtil;

import com.wjx.the_golden_autumn.TheGoldenAutumnMod;
import com.wjx.the_golden_autumn.proxy.CommonProxy;
import com.wjx.the_golden_autumn.slashblade.BladeLoader;
import com.wjx.the_golden_autumn.slashblade.specialattack.MeteoriteSword;
import com.wjx.the_golden_autumn.slashblade.specialattack.Test_Datacutter;
import mods.flammpfeil.slashblade.item.ItemSlashBlade;

import static mods.flammpfeil.slashblade.SlashBlade.InitEventBus;

public class commonloader {
    public commonloader(CommonProxy thiss) {
        InitEventBus.register(thiss);
        InitEventBus.register(TheGoldenAutumnMod.instance);
        boolean specialAttacksId = true;

        new BladeLoader();
        ItemSlashBlade.specialAttacks.put(257,new MeteoriteSword());
        ItemSlashBlade.specialAttacks.put(258,new Test_Datacutter());
        System.out.println("autumn:loadbladecommon");

    }
}
