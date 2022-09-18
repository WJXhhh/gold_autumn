package com.wjx.the_golden_autumn.machine_recipe;

import com.wjx.the_golden_autumn.TheGoldenAutumnMod;
import com.wjx.the_golden_autumn.api.API;
import com.wjx.the_golden_autumn.init.iteminit;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;

public class DrawingTableRecipeHandler {
    public static HashMap<Item, DrawingTableOutStruct> RecipeForOutItemStack= new HashMap<>();
    public static HashMap<Item,Integer> RecipeForNeedCount = new HashMap<>();
    static boolean isAdded = false;

    public static void addRecipe(){
        RecipeForOutItemStack.put(iteminit.PEACE_OF_MAPLE,new DrawingTableOutStruct(iteminit.DRAWING_CUTAUTUMN,1));
        RecipeForOutItemStack.put(Item.getItemFromBlock(Blocks.TALLGRASS),new DrawingTableOutStruct(iteminit.DRAWING_CLEAVER,1));
        RecipeForOutItemStack.put(iteminit.ORANGE_FRUIT,new DrawingTableOutStruct(iteminit.DRAWING_ORANGE,1));
        RecipeForNeedCount.put(iteminit.PEACE_OF_MAPLE,1);
        RecipeForNeedCount.put(Item.getItemFromBlock(Blocks.TALLGRASS),2);
        RecipeForNeedCount.put(iteminit.ORANGE_FRUIT,2);
        if (TheGoldenAutumnMod.APIRecipeDrawingTable.size() > 0){
            ArrayList<API.APIStructs.ApiDrawingTableRecipe> array = TheGoldenAutumnMod.APIRecipeDrawingTable;
            for (API.APIStructs.ApiDrawingTableRecipe recipe : array){
                RecipeForOutItemStack.put(recipe.inputItem,new DrawingTableOutStruct(recipe.OutputItem,recipe.OutputItemCount));
                RecipeForNeedCount.put(recipe.inputItem,recipe.InputItemCount);
            }
        }
    }

    public static DrawingTableOutStruct getRecipe(DrawingTableRecipeStruct input){
        if (RecipeForOutItemStack.containsKey(input.input)){
            int needCount = RecipeForNeedCount.get(input.input);
            if (input.Count >= needCount){
                return new DrawingTableOutStruct(RecipeForOutItemStack.get(input.input).out,RecipeForOutItemStack.get(input.input).outCount);
            }
        }
        return new DrawingTableOutStruct(Items.AIR,0);
    }

    public static class DrawingTableRecipeStruct{
        private final Item input;
        private final int Count;
        public DrawingTableRecipeStruct(ItemStack stack){
            this.input = stack.getItem();
            this.Count = stack.getCount();
        }
    }

    public static class DrawingTableOutStruct{
        public final Item out;
        public final int outCount;
        public DrawingTableOutStruct(Item outItem,int count){
            this.out = outItem;
            this.outCount = count;
        }
    }
}
