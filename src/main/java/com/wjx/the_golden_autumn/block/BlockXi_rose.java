package com.wjx.the_golden_autumn.block;

import com.wjx.the_golden_autumn.TheGoldenAutumnMod;
import com.wjx.the_golden_autumn.init.blockinit;
import com.wjx.the_golden_autumn.init.iteminit;
import com.wjx.the_golden_autumn.util.interfaces.IHasModel;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;

import static com.wjx.the_golden_autumn.TheGoldenAutumnMod.TabGold;

public class BlockXi_rose extends BlockFlower implements IHasModel, ITileEntityProvider {
    public BlockXi_rose(){
        setUnlocalizedName("xirose");
        setRegistryName("xirose");
        setCreativeTab(TabGold);
        blockinit.Blocks.add(this);
        iteminit.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
        GameRegistry.registerTileEntity(CustomTile.class,"xi_rose");
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World player, List<String> tooltip, ITooltipFlag advanced) {
        super.addInformation(stack, player, tooltip, advanced);
        tooltip.add(I18n.format("info.xirose"));
    }



    @Override
    public void getDrops(NonNullList<ItemStack> drops, IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
        drops.add(new ItemStack(this));
    }

    @Override
    public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
        return new ItemStack(Item.getItemFromBlock(this), 1, this.damageDropped(state));
    }


    @Override
    public int quantityDropped(Random random) {
        return 1;
    }

    @Override
    public EnumPlantType getPlantType(IBlockAccess world, BlockPos pos) {
        return EnumPlantType.Plains;
    }

    @Override
    public boolean hasTileEntity(IBlockState state) {
        return true;
    }

    @Override
    public boolean eventReceived(IBlockState state, World worldIn, BlockPos pos, int id, int param) {
        super.eventReceived(state, worldIn, pos, id, param);
        TileEntity tileentity = worldIn.getTileEntity(pos);
        return tileentity != null && tileentity.receiveClientEvent(id, param);
    }

    @Override
    public EnumFlowerColor getBlockType() {
        return EnumFlowerColor.YELLOW;
    }

    @Override
    public void registerModels(){
        TheGoldenAutumnMod.proxy.registerItemRenderer(Item.getItemFromBlock(this),0,"inventory");
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new CustomTile();
    }

    public static class CustomTile extends TileEntity{

        public CustomTile(){

        }

        @Override
        public SPacketUpdateTileEntity getUpdatePacket()
        {
            return new SPacketUpdateTileEntity(this.pos, 0, this.getUpdateTag());
        }

        @Override
        public NBTTagCompound getUpdateTag() {
            return this.writeToNBT(new NBTTagCompound());
        }

        @Override
        public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
            this.readFromNBT(pkt.getNbtCompound());
        }
    }
}
