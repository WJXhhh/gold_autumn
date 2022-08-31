package com.wjx.the_golden_autumn.entity;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityPotion;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.*;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

import java.util.ArrayList;
import java.util.List;

public class Qiuxi extends EntityMob {
    public Qiuxi(World worldIn) {
        super(worldIn);
    }

    @Override
    protected void initEntityAI() {
        super.initEntityAI();
        this.tasks.addTask(1, new EntityAIAttackMelee(this, 0.8D, false));
        this.targetTasks.addTask(2, new EntityAIHurtByTarget(this, false));
        this.tasks.addTask(3, new EntityAIWander(this, 0.8D));
        this.tasks.addTask(4, new EntityAISwimming(this));
        this.tasks.addTask(5, new EntityAILookIdle(this));
        this.tasks.addTask(6, new EntityAIOpenDoor(this, true));
        this.tasks.addTask(7, new EntityAIOpenDoor(this, false));
    }
    public SoundEvent getHurtSound(DamageSource ds) {
        return (SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation(""));
    }

    public SoundEvent getDeathSound() {
        return (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation(""));
    }

    @Override
    public boolean attackEntityFrom(DamageSource source, float amount) {
        super.attackEntityFrom(source, amount);

        double x = this.posX;
        double y = this.posY;
        double z = this.posZ;

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

            if (!world.isRemote) {
                world.playSound((EntityPlayer) null, new BlockPos((int)x, (int)y, (int)z), (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("the_golden_autumn:qiuxiattacked")), SoundCategory.NEUTRAL, 1.0F, 1.0F);
            } else {
                world.playSound(x, y, z, (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("the_golden_autumn:qiuxiattacked")), SoundCategory.NEUTRAL, 1.0F, 1.0F, false);
            }
            EntityZombie entityZombie;
            if(!world.isRemote){
                entityZombie = new EntityZombie(world);
                entityZombie.setPosition(x,y+1,z);





                AxisAlignedBB bb = this.getEntityBoundingBox();
                bb = bb.grow(20.0D, 10.0D, 20.0D);
                bb = bb.offset(this.motionX, this.motionY, this.motionZ);
                List<Entity> list = this.world.getEntitiesInAABBexcluding(this, bb, input -> input != this && input.isEntityAlive());
                int zombNum=0;

                for(Entity cure:list){
                    if(cure instanceof EntityZombie)
                        zombNum++;
                }

                if(this.posY <= -10){
                    /*int numx =-80;
                    int numy= 0;
                    int numz= -80;
                    motionY = 4;
                    for (int a = 0; a < 160; a++) {
                        for (int b = 0; b < 500; b++) {
                            for (int c = 0; c < 160; c++) {
                                 if(world.getBlockState(new BlockPos(posX+numx,posY+numy,posZ+numz)).getBlock() == Blocks.AIR&&world.getBlockState(new BlockPos(posX+numx,0,posZ+numz)).getBlock() != Blocks.AIR){
                                     posX=posX+numx;
                                             posY=posY+numy;
                                                     posZ=posZ+numz;
                                 }

                                numz++;
                            }
                            numy++;
                        }
                        numx++;
                    }*/
                    world.removeEntity(this);
                }
                if(this.posY <= -2000){
                    world.removeEntity(this);
                }

                if(zombNum<=100 && world.getBlockState(new BlockPos(posX,posY+1,posZ)).getBlock()==Blocks.AIR&& posY>= -20)
                {
                    for (int i = 0; i < 4; i++) {
                        world.spawnEntity(entityZombie);
                    }
                }
            }
           return super.attackEntityFrom(source, amount);

        }
    }

    @Override
    public void onDeath(DamageSource cause) {
        EntityLivingBase target = this.getAttackTarget();
        this.setDead();
        double x = this.posX;
        double y = this.posY;
        double z = this.posZ;
        Qiuxi qiuxi;
        qiuxi = new Qiuxi(world);
        qiuxi.setPosition(this.posX,this.posY,this.posZ);
        qiuxi.setAttackTarget(target);
        if (!world.isRemote) {
            world.playSound((EntityPlayer) null, new BlockPos((int)x, (int)y, (int)z), (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("the_golden_autumn:qiuxia2")), SoundCategory.NEUTRAL, 1.0F, 1.0F);
        } else {
            world.playSound(x, y, z, (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("the_golden_autumn:qiuxia2")), SoundCategory.NEUTRAL, 1.0F, 1.0F, false);
        }

        this.world.spawnEntity(qiuxi);
        /*this.world.removeEntity(this);
        this.world.getChunkFromChunkCoords(this.chunkCoordX, this.chunkCoordZ).removeEntity(this);*/
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(5D);
    }

    @Override
    protected boolean processInteract(EntityPlayer player, EnumHand hand) {
        double x = this.posX;
        double y = this.posY;
        double z = this.posZ;
        if(Math.random()<0.5D){
            if (!world.isRemote) {
                world.playSound((EntityPlayer) null, new BlockPos((int)x, (int)y, (int)z), (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("the_golden_autumn:qiuxis2")), SoundCategory.NEUTRAL, 1.0F, 1.0F);
            } else {
                world.playSound(x, y, z, (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("the_golden_autumn:qiuxis2")), SoundCategory.NEUTRAL, 1.0F, 1.0F, false);
            }
        }else{
            if (!world.isRemote) {
                world.playSound((EntityPlayer) null, new BlockPos((int)x, (int)y, (int)z), (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("the_golden_autumn:qiuxis3")), SoundCategory.NEUTRAL, 1.0F, 1.0F);
            } else {
                world.playSound(x, y, z, (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("the_golden_autumn:qiuxis3")), SoundCategory.NEUTRAL, 1.0F, 1.0F, false);
            }
        }
        player.startRiding(this);
        return true;
    }
}
