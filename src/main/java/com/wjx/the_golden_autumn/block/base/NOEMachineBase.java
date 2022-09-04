package com.wjx.the_golden_autumn.block.base;

import com.wjx.the_golden_autumn.TheGoldenAutumnMod;
import com.wjx.the_golden_autumn.objects.item.blockbase;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public abstract class NOEMachineBase extends blockbase implements ITileEntityProvider {
    protected final int GUI_ID;
    protected TileEntity tileEntity;

    public NOEMachineBase(String name, CreativeTabs tabs,int GUI_ID,TileEntity tileEntity) {
        super(name, Material.IRON, tabs);
        setSoundType(SoundType.METAL);
        this.GUI_ID = GUI_ID;
        this.tileEntity = tileEntity;
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta){
        return tileEntity;
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if(!worldIn.isRemote){
            playerIn.openGui(TheGoldenAutumnMod.instance, GUI_ID,worldIn,pos.getX(),pos.getY(),pos.getZ());
        }
        return true;
    }

    @Override
    public boolean hasTileEntity(IBlockState state)
    {
        return true;
    }
}
