package com.wjx.the_golden_autumn.item;

import com.wjx.the_golden_autumn.entity.BubbleGunDAN;
import com.wjx.the_golden_autumn.objects.item.itembase;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EntityTrackerEntry;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.ProjectileImpactEvent;

import static com.wjx.the_golden_autumn.TheGoldenAutumnMod.TabGold;

public class ItemDVABubbleGun{

    public static class CustomItem extends itembase{
        public CustomItem() {
            super("dva_bubble_gun",TabGold, 1);
        }

        @Override
        public float getDestroySpeed(ItemStack stack, IBlockState state) {
            return 1.0f;
        }

        @Override
        public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
            ActionResult<ItemStack> ar = super.onItemRightClick(worldIn, playerIn, handIn);
            World world = worldIn;
            if (!world.isRemote){
                EntityArrow entityToSpawn1 = new BubbleGunDAN(world, playerIn);
                world.spawnEntity(entityToSpawn1);
            }
            return ar;
        }
    }

}
