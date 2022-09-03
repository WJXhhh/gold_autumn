package com.wjx.the_golden_autumn.block.machines;

import com.wjx.the_golden_autumn.block.base.NOEMachineBase;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MachineLianQi extends NOEMachineBase {
    public MachineLianQi(String name, CreativeTabs tabs, int GUI_ID, TileEntity tileEntity) {
        super(name, tabs, GUI_ID, tileEntity);
    }
}
