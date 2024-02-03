package com.wjx.the_golden_autumn.block;

import com.wjx.the_golden_autumn.block.base.ControlBlockBase;
import com.wjx.the_golden_autumn.block.base.ControlTileEntityBase;
import com.wjx.the_golden_autumn.block.base.lib;
import com.wjx.the_golden_autumn.event.updatecolor;
import com.wjx.the_golden_autumn.init.blockinit;
import com.wjx.the_golden_autumn.init.iteminit;
import net.minecraft.block.Block;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class QiuxiWorldDrillingMachine {
    public static class CustomTile extends ControlTileEntityBase {
        public CustomTile(){
            super();
            this.handlers = lib.create(this, EnumFacing.values());
        }

        @Override
        public ITextComponent getDisplayName() {
            return new TextComponentString("QiuXi World Drilling Machine");
        }

        @Override
        public String getName() {
            return "qiuxi_world_drilling_machine";
        }

        @Override
        public void update() {
            super.update();
            int x = pos.getX();
            World worldIn = this.getWorld();
            int y = pos.getY();
            int z = pos.getZ();
            if (getValuebool(worldIn,new BlockPos((int) x, (int) y, (int) z), "tianqikongzhi_thunder")) {
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
                    }, "weather thunder");
                }
            }else{
                //System.out.println("zhaungliechengren isth false or cannot get: "+getValuebool(worldIn,new BlockPos((int) x, (int) y, (int) z), "zhuangliechengren"));
            }

        }
    }

    public static class CustomBlock extends ControlBlockBase {
        public CustomBlock(){
            super();
            setUnlocalizedName("qiuxi_world_drilling_machine");
            setRegistryName("qiuxi_world_drilling_machine");
            iteminit.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
            blockinit.Blocks.add(this);
        }

        @Nullable
        @Override
        public TileEntity createNewTileEntity(World worldIn, int meta) {
            return new QiuxiWorldDrillingMachine.CustomTile();
        }

        @Override
        @SideOnly(Side.CLIENT)
        public void addInformation(ItemStack stack, @Nullable World player, List<String> tooltip, ITooltipFlag advanced) {
            super.addInformation(stack, player, tooltip, advanced);
            tooltip.add(updatecolor.makeColourSanic(I18n.translateToLocal("info.drilling")));
        }

        @Override
        public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
            super.onBlockActivated(worldIn,pos,state,playerIn,hand,facing,hitX,hitY,hitZ);
            int x = pos.getX();
            int y = pos.getY();
            int z = pos.getZ();
            if (playerIn.getHeldItemMainhand().getItem() == Items.CAKE){
                playerIn.getHeldItemMainhand().shrink(1);
                final TileEntity tileEntity = worldIn.getTileEntity(pos);
                if (tileEntity != null){
                    if (!worldIn.isRemote){
                        BlockPos _bp = new BlockPos(x, y, z);
                        TileEntity _tileEntity = worldIn.getTileEntity(_bp);
                        IBlockState _bs = worldIn.getBlockState(_bp);
                        if (_tileEntity != null){
                            _tileEntity.getTileData().setBoolean("huongzha", true);
                            //System.out.println("zhaungliechengren true");
                        }
                        worldIn.notifyBlockUpdate(_bp,_bs,_bs,3);
                    }
                    worldIn.playSound(null,new BlockPos(x,y,z), Objects.requireNonNull(ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("block.anvil.destroy"))), SoundCategory.NEUTRAL, 1.0f, 1.0f);
                    BlockPos _bp = new BlockPos(x, y, z);
                    IBlockState _bs2 = blockinit.HANDA.getDefaultState();
                    final IBlockState _bso = worldIn.getBlockState(_bp);
                    for (final Map.Entry<IProperty<?>, Comparable<?>> entry : _bso.getProperties().entrySet()) {
                        final IProperty _property = _bs2.getBlock().getBlockState().getProperty(entry.getKey().getName());
                        if (_bs2.getPropertyKeys().contains(_property))
                            _bs2 = _bs2.withProperty(_property, (Comparable) entry.getValue());

                    }
                    TileEntity _te = worldIn.getTileEntity(_bp);
                    NBTTagCompound _bnbt = null;
                    if (_te != null) {
                        _bnbt = _te.writeToNBT(new NBTTagCompound());
                        _te.markDirty();
                    }
                    worldIn.setBlockState(_bp,_bs2,3);
                    if (_bnbt != null) {
                        _te = worldIn.getTileEntity(_bp);
                        if (_te != null) {
                            try {
                                _te.writeToNBT(_bnbt);
                            }
                            catch (Exception ex) {}
                        }
                    }


                }
            }

            return true;
        }

        @Override
        public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos) {



        }
    }
}
