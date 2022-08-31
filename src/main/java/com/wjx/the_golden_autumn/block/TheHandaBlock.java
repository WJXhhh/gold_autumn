package com.wjx.the_golden_autumn.block;

import com.google.common.collect.UnmodifiableIterator;
import com.wjx.the_golden_autumn.TheGoldenAutumnMod;
import com.wjx.the_golden_autumn.block.base.ControlTileEntityBase;
import com.wjx.the_golden_autumn.block.base.lib;
import com.wjx.the_golden_autumn.event.updatecolor;
import com.wjx.the_golden_autumn.init.blockinit;
import com.wjx.the_golden_autumn.init.iteminit;
import com.wjx.the_golden_autumn.util.interfaces.IHasModel;
import net.minecraft.block.BlockFalling;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Map;

public class TheHandaBlock {
    public static class CustomTile extends ControlTileEntityBase{
        public CustomTile(){
            super();
            this.handlers = lib.create(this, EnumFacing.values());
        }

        @Override
        public ITextComponent getDisplayName() {
            return new TextComponentString("Qiuxi World Drilling Machine");
        }

        @Override
        public String getName() {
            return "thehanda";
        }

        @Override
        public void update() {
            super.update();
            int x = pos.getX();
            int y = pos.getY();
            int z = pos.getZ();
            BlockPos _bp;
            IBlockState _bs;
            IBlockState _bso;
            UnmodifiableIterator var11;
            Map.Entry entry;
            IProperty _property;
            World worldIn = this.getWorld();
            if (worldIn.getBlockState(new BlockPos(x,y-1,z)).getBlock() != Blocks.BEDROCK.getDefaultState().getBlock()){
                _bp = new BlockPos((int)x, (int)(y - 1.0D), (int)z);
                _bs = Blocks.AIR.getDefaultState();
                _bso = worldIn.getBlockState(_bp);
                var11 = _bso.getProperties().entrySet().iterator();
                while(var11.hasNext()) {
                    entry = (Map.Entry)var11.next();
                    _property = _bs.getBlock().getBlockState().getProperty(((IProperty)entry.getKey()).getName());
                    if (_bs.getPropertyKeys().contains(_property))
                        _bs = _bs.withProperty(_property, (Comparable) entry.getValue());

                }
                worldIn.setBlockState(_bp,_bs,3);
            }
            if (worldIn.getBlockState(new BlockPos(x,y-1,z-1)).getBlockHardness(worldIn,new BlockPos(x,y-1,z-1)) >= 0f && worldIn.getBlockState(new BlockPos(x,y-1,z-1)).getBlockHardness(worldIn,new BlockPos(x,y-1,z-1)) <= 2f){
                _bp = new BlockPos((int)x, (int)(y - 1.0D), (int)z - 1);
                _bs = Blocks.AIR.getDefaultState();
                _bso = worldIn.getBlockState(_bp);
                var11 = _bso.getProperties().entrySet().iterator();
                while(var11.hasNext()) {
                    entry = (Map.Entry)var11.next();
                    _property = _bs.getBlock().getBlockState().getProperty(((IProperty)entry.getKey()).getName());
                    if (_bs.getPropertyKeys().contains(_property))
                        _bs = _bs.withProperty(_property, (Comparable) entry.getValue());

                }
                worldIn.setBlockState(_bp,_bs,3);
            }
            if (LowHardDig(x,y-1,z+1,worldIn)){
                _bp = new BlockPos((int)x, (int)(y - 1.0D), (int)z + 1);
                a(_bp,worldIn);
            }
            if (LowHardDig(x -1,y-1,z,worldIn)){
                _bp = new BlockPos((int)x - 1, (int)(y - 1.0D), (int)z);
                a(_bp,worldIn);
            }
            if (LowHardDig(x + 1,y-1,z,worldIn)){
                _bp = new BlockPos((int)x + 1, (int)(y - 1.0D), (int)z);
                a(_bp,worldIn);
            }
            if (LowHardDig(x + 1,y-1,z + 1,worldIn)){
                _bp = new BlockPos((int)x + 1, (int)(y - 1.0D), (int)z +1);
                a(_bp,worldIn);
            }
            if (LowHardDig(x - 1,y-1,z + 1,worldIn)){
                _bp = new BlockPos((int)x - 1, (int)(y - 1.0D), (int)z + 1);
                a(_bp,worldIn);
            }
            if (LowHardDig(x + 1,y-1,z - 1,worldIn)){
                _bp = new BlockPos((int)x + 1, (int)(y - 1.0D), (int)z - 1);
                a(_bp,worldIn);
            }
            if (LowHardDig(x - 1,y-1,z - 1,worldIn)){
                _bp = new BlockPos((int)x - 1, (int)(y - 1.0D), (int)z - 1);
                a(_bp,worldIn);
            }
        }

        private static void a(BlockPos _bp,World worldIn) {
            IBlockState _bs;
            IBlockState _bso;
            UnmodifiableIterator var11;
            Map.Entry entry;
            IProperty _property;
            _bs = Blocks.AIR.getDefaultState();
            _bso = worldIn.getBlockState(_bp);
            var11 = _bso.getProperties().entrySet().iterator();
            while(var11.hasNext()) {
                entry = (Map.Entry)var11.next();
                _property = _bs.getBlock().getBlockState().getProperty(((IProperty)entry.getKey()).getName());
                if (_bs.getPropertyKeys().contains(_property))
                    _bs = _bs.withProperty(_property, (Comparable) entry.getValue());

            }
            worldIn.setBlockState(_bp,_bs,3);
            }
        }


    public static boolean LowHardDig(float x,float y,float z,World worldIn){
        return worldIn.getBlockState(new BlockPos(x,y,z)).getBlockHardness(worldIn,new BlockPos(x,y,z)) >= 0f && worldIn.getBlockState(new BlockPos(x,y,z)).getBlockHardness(worldIn,new BlockPos(x,y,z-1)) <= 2f;
    }

    public static class CustomBlock extends BlockFalling implements IHasModel, ITileEntityProvider {
        public static final PropertyDirection FACING = BlockHorizontal.FACING;
        public CustomBlock(){
            super();
            this.setUnlocalizedName("thehanda");
            this.setRegistryName("thehanda");
            setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
            iteminit.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
            blockinit.Blocks.add(this);
        }
        @Override
        public void addInformation(ItemStack stack, @Nullable World player, List<String> tooltip, ITooltipFlag advanced) {
            super.addInformation(stack, player, tooltip, advanced);
            tooltip.add(updatecolor.makeColourSanic(I18n.translateToLocal("info.drilling")));
        }
        @Override
        public void registerModels(){
            TheGoldenAutumnMod.proxy.registerItemRenderer(Item.getItemFromBlock(this),0,"inventory");
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

        @Nullable
        @Override
        public TileEntity createNewTileEntity(World worldIn, int meta) {
            return new TheHandaBlock.CustomTile();
        }
    }
}
