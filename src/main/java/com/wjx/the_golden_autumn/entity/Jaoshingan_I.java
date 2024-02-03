package com.wjx.the_golden_autumn.entity;

import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntityEvokerFangs;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.BossInfo;
import net.minecraft.world.BossInfoServer;
import net.minecraft.world.World;

import java.util.List;

public class Jaoshingan_I extends EntityMob {
    public Jaoshingan_I(World worldIn) {
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
        this.tasks.addTask(1, new EntityAIAttackMelee(this, 1.2D, false));
        this.targetTasks.addTask(2, new EntityAIHurtByTarget(this, false));
        this.targetTasks.addTask(3,new EntityAINearestAttackableTarget(this, EntityPlayer.class,false,false));
        this.tasks.addTask(3, new EntityAIWander(this, 0.8D));
        this.tasks.addTask(4, new EntityAISwimming(this));
        this.tasks.addTask(5, new EntityAILookIdle(this));
        this.tasks.addTask(6, new EntityAIOpenDoor(this, true));
        this.tasks.addTask(7, new EntityAIOpenDoor(this, false));
    }

    @Override
    public void onStruckByLightning(EntityLightningBolt lightningBolt) {
        super.onStruckByLightning(lightningBolt);
        if(this.getHealth()<this.getMaxHealth()-10f){
            this.setHealth(this.getHealth()+10);
        }
        //this.setHealth(this.getMaxHealth() + 10F);
    }

    @Override
    public boolean attackEntityFrom(DamageSource source, float amount) {
        super.attackEntityFrom(source, amount);
        double x = posX;
        double y =posY;
        double z = posZ;
        Jaoshingan_I entity = this;
        if (Math.random() < 0.04D) {
            world.spawnEntity(new EntityLightningBolt(world, (int)x, (int)y, (int)z, false));
        }
        if (Math.random() < 0.05D && !world.isRemote) {
            AxisAlignedBB bb = entity.getEntityBoundingBox();
            bb = bb.grow(9.0D, 0.0D, 9.0D);
            bb = bb.offset(entity.motionX, entity.motionY, entity.motionZ);
            List<Entity> list = entity.world.getEntitiesInAABBexcluding(entity, bb, input -> input != entity && input.isEntityAlive());

            for (Entity curEntity : list) {
                double curX = curEntity.posX;
                double curY = curEntity.posY;
                double curZ = curEntity.posZ;
                EntityEvokerFangs entitySpawn = new EntityEvokerFangs(world);
                entitySpawn.setPosition(curX, curY + 1, curZ);
                world.spawnEntity(entitySpawn);
            }
        }
        if (Math.random() < 0.03D) {
            if (!world.isRemote) {
                AxisAlignedBB bb = entity.getEntityBoundingBox();
                bb = bb.grow(9.0D, 0.0D, 9.0D);
                bb = bb.offset(entity.motionX, entity.motionY, entity.motionZ);
                List<Entity> list = entity.world.getEntitiesInAABBexcluding(entity, bb, input -> input != entity && input.isEntityAlive());

                for (Entity curEntity : list) {
                    if (curEntity instanceof EntityLivingBase){
                        curEntity.motionY = 3;
                    }
                }
            }
        }
        if (Math.random() < 0.01D && !world.isRemote) {
            world.createExplosion(null,x,y,z,10f,false);
        }
        return super.attackEntityFrom(source, amount);
    }

    @Override
    public void onKillEntity(EntityLivingBase entityLivingIn) {
        super.onKillEntity(entityLivingIn);
        if(entityLivingIn instanceof EntityPlayerMP){
            EntityPlayer entity = (EntityPlayer) entityLivingIn;
            Advancement _adv = ((EntityPlayerMP) entity).mcServer.getAdvancementManager().getAdvancement(new ResourceLocation("the_golden_autumn:thisis_requiem"));
            AdvancementProgress _ap = ((EntityPlayerMP) entity).getAdvancements().getProgress(_adv);
            if (!_ap.isDone()) {
                for (String _criterion : _ap.getRemaningCriteria()) {
                    ((EntityPlayerMP) entity).getAdvancements().grantCriterion(_adv, _criterion);
                }
            }
        }
    }

    @Override
    public void onDeath(DamageSource cause) {
        EntityLivingBase target = this.getAttackTarget();
        this.setDead();
        double x = this.posX;
        double y = this.posY;
        double z = this.posZ;
        Jaoshingan_II entitySpawn;
        entitySpawn = new Jaoshingan_II(world);
        entitySpawn.setPosition(this.posX,this.posY,this.posZ);
        world.spawnEntity(entitySpawn);
        
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(.3D);
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(250D);
        this.getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(10D);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(20D);
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
    public void onUpdate() {
        super.onUpdate();
        this.bossInfo.setPercent(this.getHealth()/this.getMaxHealth());
    }
}
