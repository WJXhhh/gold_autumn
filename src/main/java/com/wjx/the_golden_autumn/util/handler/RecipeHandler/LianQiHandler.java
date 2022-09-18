package com.wjx.the_golden_autumn.util.handler.RecipeHandler;

import com.wjx.the_golden_autumn.api.API;
import com.wjx.the_golden_autumn.api.recipe.ApiLQ_R_Handler;
import com.wjx.the_golden_autumn.init.iteminit;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class LianQiHandler extends ApiLQ_R_Handler {
    @Override
    public int getApiVersion() {
        return 0;
    }

    @Override
    public void ToAddRecipe() {
       this.ApiRecipes.add(new API.APIStructs.ApiLianQiRecipe(new ItemStack[]{new ItemStack(Items.IRON_SWORD),new ItemStack(iteminit.AUTU_JADE,8),new ItemStack(Items.DIAMOND,4),new ItemStack(iteminit.KNIGHT_OF_MAPLE)}, iteminit.AUTUMN_KATANA,1,new ItemStack(iteminit.DRAWING_KATANA),ItemStack.EMPTY));
    }
}
