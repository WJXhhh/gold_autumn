package com.wjx.the_golden_autumn.block.machines;

import com.wjx.the_golden_autumn.TheGoldenAutumnMod;
import com.wjx.the_golden_autumn.block.base.NOEMachineBase;
import com.wjx.the_golden_autumn.tileEntity.TileEntityMachineDrawingMaker;
import com.wjx.the_golden_autumn.tileEntity.TileEntityMachineLianQi;
import com.wjx.the_golden_autumn.util.Reference;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import javax.annotation.Nullable;

public class MachineLianQi extends NOEMachineBase {
    public MachineLianQi(String name, CreativeTabs tabs) {
        super(name, tabs);
    }
    @Nullable
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta){
        return new TileEntityMachineLianQi();
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if(!worldIn.isRemote){
            playerIn.openGui(TheGoldenAutumnMod.instance, Reference.GUI_ID_LIANQI,worldIn,pos.getX(),pos.getY(),pos.getZ());
        }
        return true;
    }
}
