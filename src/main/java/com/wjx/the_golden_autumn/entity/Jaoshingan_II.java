package com.wjx.the_golden_autumn.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntityWitherSkeleton;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntityEvokerFangs;
import net.minecraft.entity.projectile.EntityPotion;
import net.minecraft.entity.projectile.EntityWitherSkull;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.BossInfo;
import net.minecraft.world.BossInfoServer;
import net.minecraft.world.World;

import java.util.List;

public class Jaoshingan_II extends EntityMob {
    public Jaoshingan_II(World worldIn) {
        super(worldIn);
        this.experienceValue = 250;

    }

    @Override
    public boolean isNonBoss() {
        return false;
    }

    private final BossInfoServer bossInfo = new BossInfoServer(this.getDisplayName(), BossInfo.Color.WHITE, BossInfo.Overlay.NOTCHED_20);

    @Override
    protected void initEntityAI() {
        super.initEntityAI();
        this.tasks.addTask(2, new EntityAIAttackMelee(this, 1.2D, false));
        this.targetTasks.addTask(3, new EntityAIHurtByTarget(this, false));
        this.targetTasks.addTask(1,new EntityAINearestAttackableTarget(this, Qiuxi.class,false,false));
        this.tasks.addTask(3, new EntityAIWander(this, 0.8D));
        this.tasks.addTask(4, new EntityAISwimming(this));
        this.tasks.addTask(5, new EntityAILookIdle(this));
        this.tasks.addTask(6, new EntityAIOpenDoor(this, true));
        this.tasks.addTask(7, new EntityAIOpenDoor(this, false));
    }



    @Override
    public boolean attackEntityFrom(DamageSource source, float amount) {
        super.attackEntityFrom(source, amount);
        double x = posX;
        double y =posY;
        double z = posZ;
        Jaoshingan_II entity = this;
        if (Math.random() < 0.01D) {
            AxisAlignedBB bb = entity.getEntityBoundingBox();
            bb = bb.grow(9.0D, 0.0D, 9.0D);
            bb = bb.offset(entity.motionX, entity.motionY, entity.motionZ);
            List<Entity> list = entity.world.getEntitiesInAABBexcluding(entity, bb, input -> input != entity && input.isEntityAlive()&&input instanceof EntityPlayer);

            for (Entity curEntity : list) {
                double curX = curEntity.posX;
                double curY = curEntity.posY;
                double curZ = curEntity.posZ;
                EntityTNTPrimed spawn = new EntityTNTPrimed(world);
                spawn.setPosition(curX,curY,curZ);
                world.spawnEntity(spawn);


            }

        }
        if (Math.random() < 0.02D && !world.isRemote) {
            AxisAlignedBB bb = entity.getEntityBoundingBox();
            bb = bb.grow(9.0D, 0.0D, 9.0D);
            bb = bb.offset(entity.motionX, entity.motionY, entity.motionZ);
            List<Entity> list = entity.world.getEntitiesInAABBexcluding(entity, bb, input -> input != entity && input.isEntityAlive() &&input instanceof EntityPlayer);

            for (Entity curEntity : list) {
                double curX = curEntity.posX;
                double curY = curEntity.posY;
                double curZ = curEntity.posZ;
                for(int i=0;i<2;i++){world.spawnEntity(new EntityLightningBolt(world, (int)curX, (int)curY, (int)curZ, false));}
            }
        }
        if (Math.random() < 0.01D) {
            if (!world.isRemote) {
                AxisAlignedBB bb = entity.getEntityBoundingBox();
                bb = bb.grow(9.0D, 0.0D, 9.0D);
                bb = bb.offset(entity.motionX, entity.motionY, entity.motionZ);
                List<Entity> list = entity.world.getEntitiesInAABBexcluding(entity, bb, input -> input != entity && input.isEntityAlive()&&input instanceof EntityPlayer);

                for (Entity curEntity : list) {
                    double curX = curEntity.posX;
                    double curY = curEntity.posY;
                    double curZ = curEntity.posZ;
                    for(int i = 0;i<2;i++){EntityWitherSkull spawn = new EntityWitherSkull(world);
                    spawn.setPosition(curX,curY,curZ);
                    world.spawnEntity(spawn);}
                }
            }
        }
        if (Math.random() < 0.01D && !world.isRemote) {

                AxisAlignedBB bb = entity.getEntityBoundingBox();
                bb = bb.grow(9.0D, 0.0D, 9.0D);
                bb = bb.offset(entity.motionX, entity.motionY, entity.motionZ);
                List<Entity> list = entity.world.getEntitiesInAABBexcluding(entity, bb, input -> input != entity && input.isEntityAlive()&&input instanceof EntityPlayer);

                for (Entity curEntity : list) {
                    double curX = curEntity.posX;
                    double curY = curEntity.posY;
                    double curZ = curEntity.posZ;
                    for(int i = 0;i<2;i++){EntityCreeper spawn = new EntityCreeper(world);
                    spawn.setPosition(curX,curY,curZ);
                    world.spawnEntity(spawn);}
                }

        }

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
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(.3D);
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(500D);
        this.getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(20D);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(50D);
        this.getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(1000D);


    }

    @Override
    public void addTrackingPlayer(EntityPlayerMP player) {
        super.addTrackingPlayer(player);
        this.bossInfo.addPlayer(player);
    }

    @Override
    public void removeTrackingPlayer(EntityPlayerMP player) {
        super.removeTrackingPlayer(player);
        this.bossInfo.removePlayer(player);
    }

    @Override
    public void onDeath(DamageSource cause) {
        EntityLivingBase target = this.getAttackTarget();
        this.setDead();
        double x = this.posX;
        double y = this.posY;
        double z = this.posZ;
        Jaoshingan_III entitySpawn;
        entitySpawn = new Jaoshingan_III(world);
        entitySpawn.setPosition(this.posX,this.posY,this.posZ);
        world.spawnEntity(entitySpawn);

    }

    @Override
    public void onUpdate() {
        super.onUpdate();
        this.bossInfo.setPercent(this.getHealth()/this.getMaxHealth());
    }
}
