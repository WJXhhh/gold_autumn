package com.wjx.the_golden_autumn.init;

import com.wjx.the_golden_autumn.item.ItemBottledAutumn;
import com.wjx.the_golden_autumn.item.ItemDVABubbleGun;
import com.wjx.the_golden_autumn.item.ItemKnightOfMaple;
import com.wjx.the_golden_autumn.item.ItemPeaceOfMaple;
import com.wjx.the_golden_autumn.item.debug.ItemExit;
import com.wjx.the_golden_autumn.objects.FoodBase;
import com.wjx.the_golden_autumn.objects.item.MeleeWeaponBase;
import com.wjx.the_golden_autumn.objects.item.itembase;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;

import java.util.ArrayList;
import java.util.List;

import static com.wjx.the_golden_autumn.TheGoldenAutumnMod.TabGold;


public class iteminit {
    //public static final ModelResourceLocation modelLoc = new ModelResourceLocation("flammpfeil.slashblade:model/named/blade.obj");
    public static final List<Item> ITEMS = new ArrayList<>();
    public static final Item NONE_ICON = new itembase("none_icon",TabGold);

    //ITEM
    public static final Item PEACE_OF_MAPLE = new ItemPeaceOfMaple();
    public static final Item BOTTLED_AUTU = new ItemBottledAutumn();
    public static final Item KNIGHT_OF_MAPLE = new ItemKnightOfMaple();
    public static final Item DV_BUBBLE_GUN = new ItemDVABubbleGun.CustomItem();
    public static final Item AUTU_JADE = new itembase("autumn_jade",TabGold);
    public static final Item ORANGE_CRYSTAL = new itembase("orange_crystal",TabGold);
    public static final ItemFood ORANGE_FRUIT = new FoodBase("orange_fruit",TabGold,4,4,false).setAlwaysEdible();


    //DRAWING
    public static final Item DRAWING_CUTAUTUMN = new itembase("drawing_1",TabGold);
    public static final Item DRAWING_CLEAVER = new itembase("drawing_2",TabGold);
    public static final Item DRAWING_ORANGE = new itembase("drawing_3",TabGold);

    public static final Item DRAWING_KATANA = new itembase("drawing_4",TabGold);

    //SWORD
    public static final Item CUT_AUTUMN = new MeleeWeaponBase("cut_autumn",512,12,TabGold,14,2.4F);
    public static final Item FIELD_CLEAVER = new MeleeWeaponBase("field_cleaver",512,14,TabGold,14,1.6F);
    public static final Item SWEET_ORANGE = new MeleeWeaponBase("sweet_orange",512,11,TabGold,14,2.8F);
    public static final Item AUTUMN_KATANA = new MeleeWeaponBase("autu_katana",1024,18,TabGold,14,2.8F);
    //public static final Item EXITTT = new ItemExit();
    //public static final Item BLADE_STAR = new Star.UniBladeRF();
}
