package com.wjx.the_golden_autumn.util.handler;

import com.wjx.the_golden_autumn.gui_container.GuiMachineDraMak;
import com.wjx.the_golden_autumn.gui_container.GuiMachineLianQi;
import com.wjx.the_golden_autumn.tileEntity.TileEntityMachineDrawingMaker;
import com.wjx.the_golden_autumn.tileEntity.TileEntityMachineLianQi;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

import com.wjx.the_golden_autumn.util.Reference;

import javax.annotation.Nullable;

public class GuiHandler implements IGuiHandler {
    @Nullable
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if (ID== Reference.GUI_ID_LIANQI) return new GuiMachineLianQi.CustomContainer(player.inventory,(TileEntityMachineLianQi) world.getTileEntity(new BlockPos(x,y,z)));
        if (ID== Reference.GUI_ID_DRAWING_MAKER) return new GuiMachineDraMak.CustomContainer(player.inventory,(TileEntityMachineDrawingMaker) world.getTileEntity(new BlockPos(x,y,z)));
        return null;
    }

    @Nullable
    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if (ID== Reference.GUI_ID_LIANQI) return new GuiMachineLianQi.CustomGui(player.inventory,(TileEntityMachineLianQi) world.getTileEntity(new BlockPos(x,y,z)));
        if (ID== Reference.GUI_ID_DRAWING_MAKER) return new GuiMachineDraMak.CustomGui(player.inventory,(TileEntityMachineDrawingMaker) world.getTileEntity(new BlockPos(x,y,z)));
        return null;
    }
}
