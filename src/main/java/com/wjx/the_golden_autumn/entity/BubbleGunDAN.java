package com.wjx.the_golden_autumn.entity;

import com.wjx.the_golden_autumn.init.EntityInit;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class BubbleGunDAN extends EntityArrow {
    public BubbleGunDAN(World world){
        super(world);
    }

    public BubbleGunDAN(World world, EntityLivingBase shooter){
        super(world,shooter);
        shoot(shooter.getLookVec().x,shooter.getLookVec().y,shooter.getLookVec().z,10.0f,0f);
        setDamage(1.0d);
        setKnockbackStrength(0);
        EntityInit.Entities.add(this);
    }

    @Override
    protected ItemStack getArrowStack() {
        return new ItemStack(Items.ARROW);
    }
}
