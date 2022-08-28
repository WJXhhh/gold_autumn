package com.wjx.the_golden_autumn.slashblade;

import com.wjx.the_golden_autumn.TheGoldenAutumnMod;
import com.wjx.the_golden_autumn.slashblade.blade.Items.Item_Star;
import com.wjx.the_golden_autumn.slashblade.blade.Star;
import com.wjx.the_golden_autumn.slashblade.blade.Youto;
import mods.flammpfeil.slashblade.SlashBlade;
import mods.flammpfeil.slashblade.SlashBladeTab;
import mods.flammpfeil.slashblade.client.model.BladeSpecialRender;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import static com.wjx.the_golden_autumn.TheGoldenAutumnMod.*;

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
