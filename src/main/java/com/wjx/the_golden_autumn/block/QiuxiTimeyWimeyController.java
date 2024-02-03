package com.wjx.the_golden_autumn.block;

import com.wjx.the_golden_autumn.block.base.ControlBlockBase;
import com.wjx.the_golden_autumn.block.base.ControlTileEntityBase;
import com.wjx.the_golden_autumn.block.base.lib;
import com.wjx.the_golden_autumn.event.updatecolor;
import com.wjx.the_golden_autumn.init.blockinit;
import com.wjx.the_golden_autumn.init.iteminit;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.command.ICommandSender;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;

public class QiuxiTimeyWimeyController {
    public static class CustomTile extends ControlTileEntityBase{
        public CustomTile(){
            super();
            this.handlers = lib.create(this,EnumFacing.values());
        }

        @Override
        public ITextComponent getDisplayName() {
            return new TextComponentString("QiuXi Timey Wimey Controller");
        }

        @Override
        public String getName() {
            return "qiuxi_timey_wimey_controller";
        }

        @Override
        public void update() {
            super.update();
            int x = pos.getX();
            World worldIn = this.getWorld();
            int y = pos.getY();
            int z = pos.getZ();
            if (getValuebool(worldIn,new BlockPos((int) x, (int) y, (int) z), "tiantang")) {
                //System.out.println("zhaungliechengren isth true");
                if (!worldIn.isRemote && worldIn.getMinecraftServer() != null) {
                    worldIn.getMinecraftServer().getCommandManager().executeCommand(new ICommandSender() {
                        @Override
                        public String getName() {
                            return "";
                        }

                        @Override
                        public boolean canUseCommand(int permission, String command) {
                            return true;
                        }

                        @Override
                        public World getEntityWorld() {
                            return worldIn;
                        }

                        @Override
                        public MinecraftServer getServer() {
                            return worldIn.getMinecraftServer();
                        }

                        @Override
                        public boolean sendCommandFeedback() {
                            return false;
                        }

                        @Override
                        public BlockPos getPosition() {
                            return new BlockPos((int) x, (int) y, (int) z);
                        }

                        @Override
                        public Vec3d getPositionVector() {
                            return new Vec3d(x, y, z);
                        }
                    }, "time add 1000");
                }
            }else{
                //System.out.println("zhaungliechengren isth false or cannot get: "+getValuebool(worldIn,new BlockPos((int) x, (int) y, (int) z), "zhuangliechengren"));
            }

        }
        }


    public static class CustomBlock extends ControlBlockBase{
        public CustomBlock(){
            super();
            setUnlocalizedName("qiuxi_timey_wimey_controller");
            setRegistryName("qiuxi_timey_wimey_controller");
            iteminit.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
            blockinit.Blocks.add(this);
        }

        @Nullable
        @Override
        public TileEntity createNewTileEntity(World worldIn, int meta) {
            return new QiuxiTimeyWimeyController.CustomTile();
        }

        @Override
        @SideOnly(Side.CLIENT)
        public void addInformation(ItemStack stack, @Nullable World player, List<String> tooltip, ITooltipFlag advanced) {
            super.addInformation(stack, player, tooltip, advanced);
            tooltip.add(updatecolor.makeColourSanic(I18n.translateToLocal("info.timey")));
        }

        @Override
        public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos) {
            super.neighborChanged(state, worldIn, pos, blockIn, fromPos);
            int x = pos.getX();
            int y = pos.getY();
            int z = pos.getZ();

            //System.out.println("neiboyrchan");
            if (worldIn.isBlockIndirectlyGettingPowered(new BlockPos(x,y,z)) > 0){
                //System.out.println("getpower!");
                if (!worldIn.isRemote){
                    BlockPos _bp = new BlockPos(x, y, z);
                    TileEntity _tileEntity = worldIn.getTileEntity(_bp);
                    IBlockState _bs = worldIn.getBlockState(_bp);
                    if (_tileEntity != null){
                        _tileEntity.getTileData().setBoolean("tiantang", true);
                        //System.out.println("zhaungliechengren true");
                    }
                    worldIn.notifyBlockUpdate(_bp,_bs,_bs,3);
                }
            }
            else {
                if (!worldIn.isRemote){
                    BlockPos _bp = new BlockPos(x, y, z);
                    TileEntity _tileEntity = worldIn.getTileEntity(_bp);
                    IBlockState _bs = worldIn.getBlockState(_bp);
                    if (_tileEntity != null){
                        _tileEntity.getTileData().setBoolean("tiantang", false);
                        //System.out.println("zhaungliechengren false");
                    }
                    worldIn.notifyBlockUpdate(_bp,_bs,_bs,3);
                }

            }

        }
    }

}
