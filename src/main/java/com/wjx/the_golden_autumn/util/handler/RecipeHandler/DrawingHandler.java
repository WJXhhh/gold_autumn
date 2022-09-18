package com.wjx.the_golden_autumn.util.handler.RecipeHandler;

import com.wjx.the_golden_autumn.api.API;
import com.wjx.the_golden_autumn.api.recipe.ApiDT_R_Handler;
import com.wjx.the_golden_autumn.init.iteminit;

public class DrawingHandler extends ApiDT_R_Handler {
    @Override
    public int getApiVersion() {
        return 0;
    }

    @Override
    public void ToAddRecipe() {
        this.ApiRecipes.add(new API.APIStructs.ApiDrawingTableRecipe(iteminit.AUTU_JADE,2,iteminit.DRAWING_KATANA,1));
    }
}
