package com.wjx.the_golden_autumn.lib;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;

public class ForgeArraysHelper {
    public static ForgeArraysHelper instance;

    public ArrayList<Item> StackArrayToItems(ArrayList<ItemStack> array1){
        ArrayList<Item> items = new ArrayList<>();
        for (ItemStack stack : array1){
            items.add(stack.getItem());
        }
        return items;
    }
}
