package com.wjx.the_golden_autumn.init;

import com.wjx.the_golden_autumn.item.ItemBottledAutumn;
import com.wjx.the_golden_autumn.item.ItemDVABubbleGun;
import com.wjx.the_golden_autumn.item.ItemKnightOfMaple;
import com.wjx.the_golden_autumn.item.ItemPeaceOfMaple;
import com.wjx.the_golden_autumn.item.debug.ItemExit;
import com.wjx.the_golden_autumn.objects.item.itembase;
import com.wjx.the_golden_autumn.slashblade.blade.Items.Item_Star;
import com.wjx.the_golden_autumn.slashblade.blade.Star;
import javafx.scene.control.Tab;
import mods.flammpfeil.slashblade.tileentity.DummyTileEntity;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.client.model.ModelLoader;

import java.util.ArrayList;
import java.util.List;

import static com.wjx.the_golden_autumn.TheGoldenAutumnMod.TabGold;


public class iteminit {
    public static final ModelResourceLocation modelLoc = new ModelResourceLocation("flammpfeil.slashblade:model/named/blade.obj");
    public static final List<Item> ITEMS = new ArrayList<>();
    public static final Item NONE_ICON = new itembase("none_icon",TabGold);

    public static final Item PEACE_OF_MAPLE = new ItemPeaceOfMaple();
    public static final Item BOTTLED_AUTU = new ItemBottledAutumn();
    public static final Item KNIGHT_OF_MAPLE = new ItemKnightOfMaple();
    public static final Item DV_BUBBLE_GUN = new ItemDVABubbleGun.CustomItem();
    public static final Item QIUXI_BOOK = new itembase("qiuxi_book", TabGold);
    public static final Item EXITTT = new ItemExit();
    //public static final Item BLADE_STAR = new Star.UniBladeRF();




}
