package com.wjx.the_golden_autumn.api;

import com.wjx.the_golden_autumn.api.recipe.ApiDT_R_Handler;
import net.minecraft.item.Item;

import java.util.ArrayList;

public class API {
    public static class HandlerRegister{
        public void RegisterApiDrawingTableHandler(ApiDT_R_Handler handler){
            handler.ToAddRecipe();
            ArrayList<APIStructs.ApiDrawingTableRecipe> ApiRecipes = handler.ApiRecipes;
        }
    }
    public static class APIStructs{
        public static class ApiDrawingTableRecipe{
            Item inputItem;
            int InputItemCount;
            Item OutputItem;
            int OutputItemCount;
            public ApiDrawingTableRecipe(Item f1,int f2,Item f3,int f4){
                inputItem = f1;
                InputItemCount = f2;
                OutputItem = f3;
                OutputItemCount = f4;
            }
        }
    }
}
