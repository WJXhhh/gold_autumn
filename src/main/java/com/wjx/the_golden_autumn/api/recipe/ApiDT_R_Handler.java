package com.wjx.the_golden_autumn.api.recipe;

import com.wjx.the_golden_autumn.api.API;

import java.util.ArrayList;
import java.util.Arrays;

public abstract class ApiDT_R_Handler {
    public ArrayList<API.APIStructs.ApiDrawingTableRecipe> ApiRecipes = new ArrayList<>();

    public abstract int getApiVersion();

    public void addRecipeInArray(API.APIStructs.ApiDrawingTableRecipe... recipes){
        if (recipes != null){
            ApiRecipes.clear();
            ApiRecipes.addAll(Arrays.asList(recipes));
        }
    };

    public abstract void ToAddRecipe();
}
