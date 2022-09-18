package com.wjx.the_golden_autumn.api.recipe;

import com.wjx.the_golden_autumn.api.API;

import java.util.ArrayList;
import java.util.Arrays;

public abstract class ApiLQ_R_Handler {
    public ArrayList<API.APIStructs.ApiLianQiRecipe> ApiRecipes = new ArrayList<>();

    public abstract int getApiVersion();

    public void addRecipeInArray(API.APIStructs.ApiLianQiRecipe... recipes){
        if (recipes != null){
            ApiRecipes.clear();
            ApiRecipes.addAll(Arrays.asList(recipes));
        }
    };

    public abstract void ToAddRecipe();
}
