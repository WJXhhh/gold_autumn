package com.wjx.the_golden_autumn.machine_recipe;

import com.wjx.the_golden_autumn.init.iteminit;
import com.wjx.the_golden_autumn.lib.ArraysHelper;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;

public class LianqiRecipe {
    private static LianqiRecipe instance;
    private static ArrayList<OutRecipeStruct> Recipes = new ArrayList<>();
    public LianqiRecipe getInstance(){
        return instance;
    }
    public LianqiRecipe(){
        Recipes.add(new OutRecipeStruct(CAIQIUM,ItemStack.EMPTY,ItemStack.EMPTY,new ItemStack(iteminit.CUT_AUTUMN,1)));
    }
    public ItemStack getRecipe(LianqiInputRecipeStruct input){
        for (OutRecipeStruct recipe : Recipes) {
            if (ArraysHelper.instance.CompareElements(input.getMaterials(), recipe.getMaterials())) {
                if (input.Tuzhi == recipe.Tuzhi && input.QiLing == recipe.QiLing) {
                    return recipe.out;
                }
            }
        }
        return ItemStack.EMPTY;
    }
    private static class OutRecipeStruct{
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
        public LianqiInputRecipeStruct(ItemStack[] stacks,ItemStack TuZhi,ItemStack QiLing){
            this.stacks = (ArrayList<ItemStack>) Arrays.asList(stacks);
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
    private static ItemStack[] CAIQIUM = {new ItemStack(iteminit.AUTU_JADE,4),new ItemStack(Items.IRON_INGOT,4),new ItemStack(Items.DIAMOND,2)};


}
