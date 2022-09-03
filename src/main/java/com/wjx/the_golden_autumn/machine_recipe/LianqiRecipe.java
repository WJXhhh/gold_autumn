package com.wjx.the_golden_autumn.machine_recipe;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class LianqiRecipe {
    private static LianqiRecipe instance;
    private Map<ItemStack[],ItemStack> RecipeMap = new HashMap<>();
    public LianqiRecipe getInstance(){
        return instance;
    }
    public ItemStack getRecipe(ItemStack[] stacks){
        if (RecipeMap.containsKey(stacks)){
            return RecipeMap.get(stacks);
        }
        else return ItemStack.EMPTY;
    }
}
