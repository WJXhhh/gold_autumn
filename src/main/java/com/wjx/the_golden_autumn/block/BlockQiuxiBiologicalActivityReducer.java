package com.wjx.the_golden_autumn.block;

import com.sun.javafx.scene.traversal.Direction;
import com.wjx.the_golden_autumn.event.updatecolor;
import com.wjx.the_golden_autumn.init.blockinit;
import com.wjx.the_golden_autumn.lib.LazyOptional;
import javafx.beans.property.BooleanProperty;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerChest;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.server.MinecraftServer;

import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityLockable;
import net.minecraft.tileentity.TileEntityLockableLoot;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec2f;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fml.common.discovery.ContainerType;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.wrapper.SidedInvWrapper;

import javax.annotation.Nullable;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.IntStream;

import static com.wjx.the_golden_autumn.TheGoldenAutumnMod.TabGold;

public class BlockQiuxiBiologicalActivityReducer {
    @SuppressWarnings("unchecked")
    public static LazyOptional<IItemHandlerModifiable>[] create(ISidedInventory inv, EnumFacing... sides) {
        LazyOptional<IItemHandlerModifiable>[] ret = new LazyOptional[sides.length];
        for (int x = 0; x < sides.length; x++) {
            final EnumFacing side = sides[x];
            ret[x] = LazyOptional.of(() -> new SidedInvWrapper(inv, side));
        }
        return ret;
    }
    public static class CustomTileEntity extends TileEntityLockableLoot implements ISidedInventory {
        private NonNullList<ItemStack> stacks;
        private final LazyOptional<? extends IItemHandler>[] handlers;
        protected CustomTileEntity() {
            this.stacks = NonNullList.withSize(0, ItemStack.EMPTY);
            this.handlers = BlockQiuxiBiologicalActivityReducer.create(this, EnumFacing.values());
        }

        @Override
        public void readFromNBT(NBTTagCompound compound) {
            super.readFromNBT(compound);
            if (!this.checkLootAndRead(compound)){
                this.stacks = NonNullList.withSize(this.getSizeInventory(), ItemStack.EMPTY);
            }
            ItemStackHelper.loadAllItems(compound, this.stacks);
        }

        @Override
        public NBTTagCompound writeToNBT(NBTTagCompound compound) {
            super.writeToNBT(compound);
            if (!this.checkLootAndWrite(compound)) {
                ItemStackHelper.saveAllItems(compound, this.stacks);
            }

            return compound;
        }

        @Nullable
        @Override
        public SPacketUpdateTileEntity getUpdatePacket() {
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



        @Override
        public int getSizeInventory() {
            return 1;
        }

        @Override
        public int getInventoryStackLimit() {
            return this.stacks.size();
        }

        @Override
        public boolean isEmpty() {
            Iterator var1 = this.stacks.iterator();

            ItemStack itemstack;
            do {
                if (!var1.hasNext()) {
                    return true;
                }

                itemstack = (ItemStack)var1.next();
            } while(itemstack.isEmpty());

            return false;
        }

        @Override
        public ITextComponent getDisplayName() {
            return new TextComponentString("Qiuxi Biological Activity Reducer");
        }

        @Override
        public String getName() {
            return "qiuxi_biological_activity_reducer";
        }

        @Override
        protected NonNullList<ItemStack> getItems() {
            return this.stacks;
        }

        @Override
        public boolean isItemValidForSlot(int index, ItemStack stack) {
            return true;
        }

        @Override
        public int[] getSlotsForFace(EnumFacing side) {
            return IntStream.range(0, this.getSizeInventory()).toArray();
        }

        @Override
        public boolean canInsertItem(int index, ItemStack itemStackIn, EnumFacing direction) {
            return this.isItemValidForSlot(index, itemStackIn);
        }

        @Override
        public boolean canExtractItem(int index, ItemStack stack, EnumFacing direction) {
            return true;
        }

        @Override
        public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing) {
            return super.getCapability(capability, facing);
        }

        @Override
        public void invalidate() {
            super.invalidate();
            LazyOptional[] var1 = this.handlers;
            int var2 = var1.length;

            for(int var3 = 0; var3 < var2; ++var3) {
                LazyOptional<? extends IItemHandler> handler = var1[var3];
                handler.invalidate();
            }
        }

        @Override
        public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn) {
            return new ContainerChest(playerInventory,this,playerIn);
        }

        @Override
        public String getGuiID() {
            return null;
        }
    }
    public static class CustomBlock extends Block{

        public CustomBlock() {
            super(Material.IRON);
            setUnlocalizedName("qiuxi_biological_activity_reducer");
            setRegistryName("qiuxi_biological_activity_reducer");
            setCreativeTab(TabGold);
            blockinit.Blocks.add(this);

        }

        @Override
        public void addInformation(ItemStack stack, @Nullable World player, List<String> tooltip, ITooltipFlag advanced) {
            super.addInformation(stack, player, tooltip, advanced);
            tooltip.add(updatecolor.makeColour3(I18n.translateToLocal("info.reducer")));
        }

        @Override
        public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos) {
            super.neighborChanged(state, worldIn, pos, blockIn, fromPos);
            int x = pos.getX();
            int y = pos.getY();
            int z = pos.getZ();
            if (worldIn.isBlockIndirectlyGettingPowered(new BlockPos(x,y,z)) > 0){
                if (!worldIn.isRemote){
                    BlockPos _bp = new BlockPos(x, y, z);
                    TileEntity _tileEntity = worldIn.getTileEntity(_bp);
                    IBlockState _bs = worldIn.getBlockState(_bp);
                    if (_tileEntity != null){
                        _tileEntity.getTileData().setBoolean("zhuangliechengren", true);
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
                        _tileEntity.getTileData().setBoolean("zhuangliechengren", false);
                    }
                    worldIn.notifyBlockUpdate(_bp,_bs,_bs,3);
                }

            }

        }

        @Override
        public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
            super.updateTick(worldIn, pos, state, rand);
            int x = pos.getX();
            int y = pos.getY();
            int z = pos.getZ();
            if (new Object(){

                public boolean getValue(BlockPos pos, String tag) {
                    TileEntity tileEntity = worldIn.getTileEntity(pos);
                    if (tileEntity != null) {
                        return tileEntity.getTileData().getBoolean(tag);
                    }
                    return false;
                }
            }.getValue(new BlockPos((int) x, (int) y, (int) z), "zhuangliechengren")) {
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
                    }, "effect @e the_golden_autumn:getold 1 255");
                }
            }


        }
    }
}
