package com.wjx.the_golden_autumn.machine_recipe;

import com.wjx.the_golden_autumn.init.iteminit;
import com.wjx.the_golden_autumn.lib.ArraysHelper;
import com.wjx.the_golden_autumn.lib.ForgeArraysHelper;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;

public class LianqiRecipe {
    private static LianqiRecipe instance;
    private static ArrayList<OutRecipeStruct> Recipes = new ArrayList<>();
    public static LianqiRecipe getInstance(){
        return instance;
    }
    public LianqiRecipe(){
        Recipes.add(new OutRecipeStruct(CAIQIUM, ItemStack.EMPTY, ItemStack.EMPTY, new ItemStack(iteminit.CUT_AUTUMN, 1)));
    }
    public ArrayList<OutRecipeStruct> getRecipeArray(){
        return Recipes;
    }
    public LianqiRecipePack getRecipe(LianqiInputRecipeStruct input){
        ArrayList<Item> InputItems = ForgeArraysHelper.instance.StackArrayToItems(input.getMaterials());
        int index = -1;
        int RightCount = 0;
        for (int k = 0;k<Recipes.size();k++){
            OutRecipeStruct out = Recipes.get(k);
            if (ArraysHelper.instance.CompareElements(ForgeArraysHelper.instance.StackArrayToItems(out.getMaterials()),InputItems)){
                for (int i = 0;i<input.getMaterials().size();i++){
                    for (int j =0;j<out.getMaterials().size();j++){
                        if (input.getMaterials().get(i).getItem() == out.getMaterials().get(j).getItem()){
                            if (input.getMaterials().get(i).getItem() != Items.AIR){
                                if (input.getMaterials().get(i).getCount()>=out.getMaterials().get(j).getCount()){
                                    RightCount++;
                                }
                            }
                            else {
                                RightCount++;
                            }
                        }
                    }
                }
                if (RightCount == InputItems.size()){
                    if (input.getTuzhi().getItem() == out.getTuzhi().getItem()&&input.getQiLing().getItem() == out.getQiLing().getItem()){
                        index = k;

                        return new LianqiRecipePack(index,out.out);
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
        private ItemStack out;
        public OutRecipeStruct(ItemStack[] stacks,ItemStack TuZhi,ItemStack QiLing,ItemStack out){
            this.stacks = (ArrayList<ItemStack>) Arrays.asList(stacks);
            this.QiLing = QiLing;
            this.Tuzhi = TuZhi;
            this.out = out;
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
            return out;
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
    private static ItemStack[] CAIQIUM = {new ItemStack(iteminit.AUTU_JADE,4),new ItemStack(Items.IRON_INGOT,4),new ItemStack(Items.DIAMOND,2)};


}
