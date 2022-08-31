package com.wjx.the_golden_autumn.entity;

import com.wjx.the_golden_autumn.TheGoldenAutumnMod;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityPotion;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class EntitySuda extends EntityCreature {
    public EntitySuda(World worldIn) {
        super(worldIn);
    }
    @Override
    protected void initEntityAI() {
        super.initEntityAI();
        this.tasks.addTask(3, new EntityAIWander(this, 0.3D));
        this.tasks.addTask(4, new EntityAISwimming(this));
        this.tasks.addTask(5, new EntityAILookIdle(this));
        this.tasks.addTask(6, new EntityAIOpenDoor(this, true));
        this.tasks.addTask(7, new EntityAIOpenDoor(this, false));
    }
    public SoundEvent getHurtSound(DamageSource ds) {
        return (SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("the_golden_autumn:sudahurt"));
    }

    @Override
    public boolean attackEntityFrom(DamageSource source, float amount) {
        super.attackEntityFrom(source, amount);
        if (source.getImmediateSource() instanceof EntityPotion) {
            return false;
        } else if (source == DamageSource.FALL) {
            return false;
        } else if (source == DamageSource.CACTUS) {
            return false;
        } else if (source == DamageSource.DROWN) {
            return false;
        } else if(source==DamageSource.IN_FIRE){
            return false;
        }else{
            return super.attackEntityFrom(source, amount);
        }
    }

    @Override
    protected boolean processInteract(EntityPlayer player, EnumHand hand) {
        double x = this.posX;
        double y = this.posY;
        double z = this.posZ;
        if(Math.random()<0.5D){
            if (!world.isRemote) {
                world.playSound((EntityPlayer) null, new BlockPos((int)x, (int)y, (int)z), (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("the_golden_autumn:sudatalk")), SoundCategory.NEUTRAL, 1.0F, 1.0F);
            } else {
                world.playSound(x, y, z, (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("the_golden_autumn:sudatalk")), SoundCategory.NEUTRAL, 1.0F, 1.0F, false);
            }
        }else{
            if (!world.isRemote) {
                world.playSound((EntityPlayer) null, new BlockPos((int)x, (int)y, (int)z), (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("the_golden_autumn:sudatalk2")), SoundCategory.NEUTRAL, 1.0F, 1.0F);
            } else {
                world.playSound(x, y, z, (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("the_golden_autumn:sudatalk2")), SoundCategory.NEUTRAL, 1.0F, 1.0F, false);
            }
        }
        player.startRiding(this);
        return true;
    }
}
