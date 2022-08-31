package com.wjx.the_golden_autumn.slashblade.specialattack;

import com.google.common.base.Predicate;
import mods.flammpfeil.slashblade.ability.StylishRankManager;
import mods.flammpfeil.slashblade.item.ItemSlashBlade;
import mods.flammpfeil.slashblade.specialattack.SpecialAttackBase;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Iterator;
import java.util.List;

public class Test_Datacutter extends SpecialAttackBase {
    @Override
    public String toString() {
        return "datacutter";
    }

    @Override
    public void doSpacialAttack(ItemStack stack, EntityPlayer entityPlayer) {
        World world = entityPlayer.world;
        if(!world.isRemote){
            AxisAlignedBB bb = entityPlayer.getEntityBoundingBox();
            bb = bb.grow(4.0D, 0.0D, 4.0D);
            bb = bb.offset(entityPlayer.motionX, entityPlayer.motionY, entityPlayer.motionZ);
            List<Entity> list = entityPlayer.world.getEntitiesInAABBexcluding(entityPlayer, bb, new Predicate<Entity>() {
                @Override
                public boolean apply(@Nullable Entity input) {
                    boolean result = false;
                    if(input != entityPlayer){
                    //result = ((Entity)input).isEntityAlive();
                    return true;
                    }else {
                        return false;
                    }
                }
            });
            StylishRankManager.setNextAttackType(entityPlayer, StylishRankManager.AttackTypes.RapidSlash);
            Iterator var14 = list.iterator();

            while(var14.hasNext()) {
                Entity curEntity = (Entity)var14.next();
                curEntity.hurtResistantTime = 0;
                if (entityPlayer instanceof EntityPlayer) {
                    ItemSlashBlade itemBlade = (ItemSlashBlade)stack.getItem();
                    itemBlade.attackTargetEntity(stack, curEntity, entityPlayer, true);
                    world.removeEntity(curEntity);
                } else {
                    DamageSource ds = new EntityDamageSource("mob", entityPlayer);
                    curEntity.attackEntityFrom(ds, 10.0F);
                    world.removeEntity(curEntity);
                    if (!stack.isEmpty() && curEntity instanceof EntityLivingBase) {
                        ((ItemSlashBlade)stack.getItem()).hitEntity(stack, (EntityLivingBase)curEntity, entityPlayer);
                    }
                }
            }
        }

    }
}
