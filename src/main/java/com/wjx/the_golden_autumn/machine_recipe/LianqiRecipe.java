package com.wjx.the_golden_autumn.machine_recipe;

import com.wjx.the_golden_autumn.TheGoldenAutumnMod;
import com.wjx.the_golden_autumn.init.blockinit;
import com.wjx.the_golden_autumn.init.iteminit;
import com.wjx.the_golden_autumn.lib.ArraysHelper;
import com.wjx.the_golden_autumn.lib.ForgeArraysHelper;
import com.wjx.the_golden_autumn.lib.SeverSender;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.*;

public class LianqiRecipe {
    private static ArrayList<OutRecipeStruct> Recipes = new ArrayList<>();
    private static HashMap<Integer,Item> outMap = new HashMap<>();
    private static HashMap<Item,Integer> outCountMap = new HashMap<>();
    public LianqiRecipe(){
    }

    private static boolean isAdded = false;

    private static void addRecipe(){
        Recipes.add(new OutRecipeStruct(CAIQIUM, new ItemStack(iteminit.DRAWING_CUTAUTUMN), ItemStack.EMPTY, new ItemStack(iteminit.CUT_AUTUMN, 1)));
        Recipes.add(new OutRecipeStruct(JIANYE,new ItemStack(iteminit.DRAWING_CLEAVER),ItemStack.EMPTY,new ItemStack(iteminit.FIELD_CLEAVER,1)));
        Recipes.add(new OutRecipeStruct(ORANGER,new ItemStack(iteminit.DRAWING_ORANGE),ItemStack.EMPTY, new ItemStack(iteminit.SWEET_ORANGE,1)));
        for (OutRecipeStruct recipe : Recipes) {
            outCountMap.put(recipe.getOut().getItem(), recipe.getOut().getCount());
        }
        for (int i = 0;i < Recipes.size();i++){
            outMap.put(i,Recipes.get(i).getOut().getItem());
        }
    }

    public static ArrayList<OutRecipeStruct> getRecipeArray(){
        return Recipes;
    }
    public static LianqiRecipePack getRecipe(LianqiInputRecipeStruct input){
        if (!isAdded){
            isAdded = true;
            addRecipe();
        }
        ArrayList<ItemStack> material = input.getMaterials();
        ArrayList<Item> InputItems = ForgeArraysHelper.instance.StackArrayToItems(material);
       // TheGoldenAutumnMod.logger.info("the size of array :" + material);
        int index = -1;
        int RightCount = 0;
        for (int k = 0;k<Recipes.size();k++){
            OutRecipeStruct outsgsgdg = Recipes.get(k);
            if (ArraysHelper.instance.CompareElements(ForgeArraysHelper.instance.StackArrayToItems(outsgsgdg.getMaterials()),InputItems)){
                //SeverSender.sendTotalMessage("oooooooo");
                for (int i = 0;i<input.getMaterials().size();i++){
                    for (int j =0;j<outsgsgdg.getMaterials().size();j++){
                        if (input.getMaterials().get(i).getItem() == outsgsgdg.getMaterials().get(j).getItem()){
                            if (input.getMaterials().get(i).getItem() != Items.AIR){
                                if (input.getMaterials().get(i).getCount()>=outsgsgdg.getMaterials().get(j).getCount()){
                                    RightCount++;
                                }
                            }
                            else {
                                RightCount++;
                            }
                        }
                    }
                }
                //SeverSender.sendTotalMessage("RightCount:"+RightCount);
                if (RightCount == 4){
                    //SeverSender.sendTotalMessage(String.valueOf(input.getTuzhi().getItem() == outsgsgdg.getTuzhi().getItem())+ (input.getQiLing().getItem() == outsgsgdg.getQiLing().getItem()));
                    if (input.getTuzhi().getItem() == outsgsgdg.getTuzhi().getItem()&&input.getQiLing().getItem() == outsgsgdg.getQiLing().getItem()){
                        index = k;
                        return new LianqiRecipePack(index,new ItemStack(outMap.get(k),outCountMap.get(outMap.get(k))));
                    }
                }
            }
        }
        return new LianqiRecipePack.Empty(-1,ItemStack.EMPTY);
        //return new LianqiRecipePack(1,new ItemStack(iteminit.CUT_AUTUMN));
    }
    public static class OutRecipeStruct{
        private ArrayList<ItemStack> stacks;
        private ItemStack Tuzhi;
        private ItemStack QiLing;
        private final ItemStack outsgsgdg;
        public OutRecipeStruct(ItemStack[] stacks,ItemStack TuZhi,ItemStack QiLing,ItemStack out){
            List<ItemStack> idList= Arrays.asList(stacks);
            this.stacks = new ArrayList<>(idList);
            this.QiLing = QiLing;
            this.Tuzhi = TuZhi;
            this.outsgsgdg = out;
        }

        public ArrayList<ItemStack> getMaterials() {
            return stacks;
        }

        public ItemStack getQiLing() {
            return QiLing;
        }

        public ItemStack getTuzhi() {
            return Tuzhi;
        }

        public ItemStack getOut() {
            return outsgsgdg;
        }

        public String toString(){
            return ("The Material Array : " + Arrays.toString(stacks.toArray(new ItemStack[0])) + " The Out : " + getOut());
        }
    }
   public static class LianqiInputRecipeStruct{
        private ArrayList<ItemStack> stacks;
        private ItemStack Tuzhi;
        private ItemStack QiLing;
        public LianqiInputRecipeStruct(ArrayList<ItemStack> stacks,ItemStack TuZhi,ItemStack QiLing){
            this.stacks = stacks;
            this.QiLing = QiLing;
            this.Tuzhi = TuZhi;
        }

        public ArrayList<ItemStack> getMaterials() {
            return stacks;
        }

        public ItemStack getQiLing() {
            return QiLing;
        }

        public ItemStack getTuzhi() {
            return Tuzhi;
        }
    }
    public static class LianqiRecipePack{
        public boolean isEmpty = false;
        public int index;
        public ItemStack out;
        public LianqiRecipePack(int index,ItemStack out){
            this.index = index;
            this.out = out;
        }
        protected static class Empty extends LianqiRecipePack{
            {
                this.isEmpty = true;
            }

            public Empty(int index, ItemStack out) {
                super(index, out);
            }
        }
    }
    private static final ItemStack[] CAIQIUM = {new ItemStack(iteminit.AUTU_JADE,4),new ItemStack(Items.IRON_INGOT,4),new ItemStack(Items.DIAMOND,2),ItemStack.EMPTY};
    private static final ItemStack[] JIANYE = {new ItemStack(Item.getItemFromBlock(blockinit.QIUROSE),4),new ItemStack(Item.getItemFromBlock(blockinit.XIROSE),4),new ItemStack(Items.DIAMOND,2),ItemStack.EMPTY};
    private static final ItemStack[] ORANGER = {new ItemStack(iteminit.ORANGE_FRUIT,8),new ItemStack(iteminit.AUTU_JADE,4),new ItemStack(Items.DIAMOND,2),ItemStack.EMPTY};
}
