package com.wjx.the_golden_autumn.api;

import com.wjx.the_golden_autumn.TheGoldenAutumnMod;
import com.wjx.the_golden_autumn.api.recipe.ApiDT_R_Handler;
import com.wjx.the_golden_autumn.api.recipe.ApiLQ_R_Handler;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;

public class API {
    public static int APIVersion = 0;

    public static class HandlerRegister{
        public static void RegisterApiDrawingTableHandler(ApiDT_R_Handler handler){
            handler.ToAddRecipe();
            ArrayList<APIStructs.ApiDrawingTableRecipe> ApiRecipes = handler.ApiRecipes;
            TheGoldenAutumnMod.APIRecipeDrawingTable.addAll(ApiRecipes);
        }
        public static void RegisterApiLianQiHandler(ApiLQ_R_Handler handler){
            handler.ToAddRecipe();
            ArrayList<APIStructs.ApiLianQiRecipe> ApiRecipes = handler.ApiRecipes;
            TheGoldenAutumnMod.APIRecipeLianQi.addAll(ApiRecipes);
        }
    }
    public static class APIStructs{
        public static class ApiDrawingTableRecipe{
            public Item inputItem;
            public int InputItemCount;
            public Item OutputItem;
            public int OutputItemCount;
            public ApiDrawingTableRecipe(Item f1,int f2,Item f3,int f4){
                inputItem = f1;
                InputItemCount = f2;
                OutputItem = f3;
                OutputItemCount = f4;
            }
        }
        public static class ApiLianQiRecipe{
            public ItemStack[] Materials;
            public Item OutItem;
            public int OutCount;
            public ItemStack TuZhi;
            public ItemStack QiLing;
            public ApiLianQiRecipe(ItemStack[] f1,Item f2,int f3,ItemStack f4,ItemStack f5){
                Materials = f1;
                OutItem = f2;
                OutCount = f3;
                TuZhi = f4;
                QiLing = f5;
            }
        }
    }
}
