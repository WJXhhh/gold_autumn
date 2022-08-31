package com.wjx.the_golden_autumn.slashblade;

import com.wjx.the_golden_autumn.slashblade.blade.Items.Item_Star;
import com.wjx.the_golden_autumn.slashblade.blade.Star;
import com.wjx.the_golden_autumn.slashblade.blade.Youto;
import mods.flammpfeil.slashblade.SlashBlade;
import net.minecraft.item.Item;

import static com.wjx.the_golden_autumn.TheGoldenAutumnMod.TabGold;

public class BladeLoader {
    public static final Item STAR = new Item_Star(Item.ToolMaterial.IRON, 32768.0F, "STARSlashBlade").setMaxDamage(Integer.MAX_VALUE).setCreativeTab(TabGold);

    public BladeLoader() {
        loadBlade();
    }

    public void loadBlade() {
        this.loadBlade(new Star());
        this.loadBlade(new Youto());
        System.out.println("autumn:loaderblade");

    }

    public void loadBlade(Object blade) {
        SlashBlade.InitEventBus.register(blade);
    }

}
