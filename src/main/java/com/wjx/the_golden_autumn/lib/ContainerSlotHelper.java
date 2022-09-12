package com.wjx.the_golden_autumn.lib;

import net.minecraft.init.Items;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

import java.util.List;

public class ContainerSlotHelper {
    public static void shrink(List<Slot> invobj,int count,int index){
        ItemStack input = invobj.get(index).getStack();
        if (input.getItem() != Items.AIR){
            if (input.getCount() == count){
                invobj.get(index).putStack(ItemStack.EMPTY);
            }
            else invobj.get(index).putStack(new ItemStack(input.getItem(),input.getCount() - count,input.getMetadata()));
        }
    }
}
