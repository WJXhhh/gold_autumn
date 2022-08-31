package com.wjx.the_golden_autumn.block.base;

import com.wjx.the_golden_autumn.lib.LazyOptional;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.wrapper.SidedInvWrapper;

public class lib {
    @SuppressWarnings("unchecked")
    public static LazyOptional<IItemHandlerModifiable>[] create(ISidedInventory inv, EnumFacing... sides) {
        LazyOptional<IItemHandlerModifiable>[] ret = new LazyOptional[sides.length];
        for (int x = 0; x < sides.length; x++) {
            final EnumFacing side = sides[x];
            ret[x] = LazyOptional.of(() -> new SidedInvWrapper(inv, side));
        }
        return ret;
    }
}
