package com.wjx.the_golden_autumn.block;

import com.wjx.the_golden_autumn.TheGoldenAutumnMod;
import com.wjx.the_golden_autumn.event.updatecolor;
import com.wjx.the_golden_autumn.init.blockinit;
import com.wjx.the_golden_autumn.init.iteminit;
import com.wjx.the_golden_autumn.lib.LazyOptional;
import com.wjx.the_golden_autumn.util.interfaces.IHasModel;
import net.minecraft.block.Block;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerChest;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityLockableLoot;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.wrapper.SidedInvWrapper;

import javax.annotation.Nullable;
import java.util.Iterator;
import java.util.List;
import java.util.stream.IntStream;

import static com.wjx.the_golden_autumn.TheGoldenAutumnMod.TabGold;

public class BlockQiuxiGravityController {
    @SuppressWarnings("unchecked")
    public static LazyOptional<IItemHandlerModifiable>[] create(ISidedInventory inv, EnumFacing... sides) {
        LazyOptional<IItemHandlerModifiable>[] ret = new LazyOptional[sides.length];
        for (int x = 0; x < sides.length; x++) {
            final EnumFacing side = sides[x];
            ret[x] = LazyOptional.of(() -> new SidedInvWrapper(inv, side));
        }
        return ret;
    }

    public static class CustomTileEntity extends TileEntityLockableLoot implements ISidedInventory, ITickable {
        private NonNullList<ItemStack> stacks;
        private final LazyOptional<? extends IItemHandler>[] handlers;
        protected CustomTileEntity() {
            this.stacks = NonNullList.withSize(0, ItemStack.EMPTY);
            this.handlers = BlockQiuxiGravityController.create(this, EnumFacing.values());
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
            return new TextComponentString("Qiuxi Gravity Controller");
        }

        @Override
        public String getName() {
            return "qiuxi_gravity_controller";
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

        public boolean getValuebool(World worldIn, BlockPos pos, String tag) {
            TileEntity tileEntity = worldIn.getTileEntity(pos);
            if (tileEntity != null) {
                return tileEntity.getTileData().getBoolean(tag);
            }
            return false;
        }
        @Override
        public void update() {
            int x = pos.getX();
            World worldIn = this.getWorld();
            int y = pos.getY();
            int z = pos.getZ();
            if (getValuebool(worldIn,new BlockPos((int) x, (int) y, (int) z), "xinyue")) {
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
                    }, "effect @e[type=!player] the_golden_autumn:cmoon 3 255 true");
                }
            }else{
                //System.out.println("zhaungliechengren isth false or cannot get: "+getValuebool(worldIn,new BlockPos((int) x, (int) y, (int) z), "zhuangliechengren"));
            }

        }
    }
    public static class CustomBlock extends Block implements ITileEntityProvider, IHasModel {

        public static final PropertyDirection FACING = BlockHorizontal.FACING;

        public CustomBlock() {
            super(Material.IRON);
            setUnlocalizedName("qiuxi_gravity_controller");
            setRegistryName("qiuxi_gravity_controller");
            setCreativeTab(TabGold);
            iteminit.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
            blockinit.Blocks.add(this);
            setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
        }
        @Override
        public void registerModels(){
            TheGoldenAutumnMod.proxy.registerItemRenderer(Item.getItemFromBlock(this),0,"inventory");
        }

        @Override
        public void addInformation(ItemStack stack, @Nullable World player, List<String> tooltip, ITooltipFlag advanced) {
            super.addInformation(stack, player, tooltip, advanced);
            tooltip.add(updatecolor.makeColourSanic(I18n.translateToLocal("info.gravity")));
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
                        _tileEntity.getTileData().setBoolean("xinyue", true);
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
                        _tileEntity.getTileData().setBoolean("xinyue", false);
                        //System.out.println("zhaungliechengren false");
                    }
                    worldIn.notifyBlockUpdate(_bp,_bs,_bs,3);
                }

            }

        }


        public boolean getValuebool(World worldIn,BlockPos pos, String tag) {
            TileEntity tileEntity = worldIn.getTileEntity(pos);
            if (tileEntity != null) {
                return tileEntity.getTileData().getBoolean(tag);
            }
            return false;
        }



        @Nullable
        @Override
        public TileEntity createNewTileEntity(World worldIn, int meta) {
            return new BlockQiuxiGravityController.CustomTileEntity();
        }

        @Override
        public boolean hasTileEntity(IBlockState state) {
            return true;
        }

        @Override
        public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
            IBlockState north = worldIn.getBlockState(pos.north());
            IBlockState south = worldIn.getBlockState(pos.south());
            IBlockState west = worldIn.getBlockState(pos.west());
            IBlockState east = worldIn.getBlockState(pos.east());
            EnumFacing face = state.getValue(FACING);
            if (face == EnumFacing.NORTH && north.isFullBlock() && !south.isFullBlock()) face = EnumFacing.SOUTH;
            else if(face ==EnumFacing.SOUTH &&south.isFullBlock()&&!north.isFullBlock()) face = EnumFacing.NORTH;
            else if(face ==EnumFacing.WEST &&west.isFullBlock()&&!east.isFullBlock()) face = EnumFacing.EAST;
            else if(face ==EnumFacing.EAST &&east.isFullBlock()&&!west.isFullBlock()) face = EnumFacing.WEST;

            worldIn.setBlockState(pos,state.withProperty(FACING,face),2);
        }

        @Override
        public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer, EnumHand hand) {
            return this.getDefaultState().withProperty(FACING,placer.getHorizontalFacing().getOpposite());
        }

        @Override
        public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack)
        {
            worldIn.setBlockState(pos, this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite()), 2);
        }

        @Override
        public EnumBlockRenderType getRenderType(IBlockState state)
        {
            return EnumBlockRenderType.MODEL;
        }

        @Override
        public IBlockState withRotation(IBlockState state, Rotation rot)
        {
            return state.withProperty(FACING, rot.rotate((EnumFacing)state.getValue(FACING)));
        }

        @Override
        public IBlockState withMirror(IBlockState state, Mirror mirrorIn)
        {
            return state.withRotation(mirrorIn.toRotation((EnumFacing)state.getValue(FACING)));
        }



        @Override
        protected BlockStateContainer createBlockState()
        {
            return new BlockStateContainer(this, new IProperty[] {FACING});
        }

        @Override
        public IBlockState getStateFromMeta(int meta)
        {
            EnumFacing facing = EnumFacing.getFront(meta);
            if(facing.getAxis() == EnumFacing.Axis.Y) facing = EnumFacing.NORTH;
            return this.getDefaultState().withProperty(FACING, facing);
        }

        @Override
        public int getMetaFromState(IBlockState state)
        {
            return state.getValue(FACING).getIndex();
        }
    }
}
