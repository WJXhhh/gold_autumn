package com.wjx.the_golden_autumn.block.machines;

import com.wjx.the_golden_autumn.block.base.NOEMachineBase;
import com.wjx.the_golden_autumn.tileEntity.TileEntityMachineDrawingMaker;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.tileentity.TileEntity;

public class MachineDrawingMaker extends NOEMachineBase {
    public MachineDrawingMaker(String name, CreativeTabs tabs, int GUI_ID, TileEntity tileEntity) {
        super(name, tabs, GUI_ID, new TileEntityMachineDrawingMaker());
    }
}
