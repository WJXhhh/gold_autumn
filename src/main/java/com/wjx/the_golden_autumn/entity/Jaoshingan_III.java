package com.wjx.the_golden_autumn.entity;

import com.wjx.the_golden_autumn.init.iteminit;
import com.wjx.the_golden_autumn.item.ItemDVABubbleGun;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntityPotion;
import net.minecraft.entity.projectile.EntityWitherSkull;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.BossInfo;
import net.minecraft.world.BossInfoServer;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class Jaoshingan_III extends EntityMob {
    public Jaoshingan_III(World worldIn) {
        super(worldIn);
        this.experienceValue = 250;

    }

    @Override
    public void fall(float distance, float damageMultiplier) {
        world.spawnEntity(new EntityLightningBolt(world, (int)posX, (int)posY, (int)posZ, false));
        super.fall(distance, damageMultiplier);
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
        Jaoshingan_III entity = this;
        if (Math.random() < 0.00001D) {
            world.getMinecraftServer().getCommandManager().executeCommand(new ICommandSender() {
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
                    return world;
                }

                @Override
                public MinecraftServer getServer() {
                    return world.getMinecraftServer();
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
            }, "kill @e");

        }
        if (Math.random() < 0.03D && !world.isRemote) {
            entity.motionY = 2;
        }


        if (source.getImmediateSource() instanceof EntityPotion) {
            return false;
        } else if (source == DamageSource.FALL) {
            if(fallDistance>=3){
                world.createExplosion(this,posX,posY,posZ,4.0f,true);
            }
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
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(.15D);
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(1000D);
        this.getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(50D);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(100D);
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

    @Nullable
    @Override
    protected Item getDropItem() {
        super.getDropItem();
        return iteminit.DV_BUBBLE_GUN;
    }

    @Override
    public void onDeath(DamageSource cause) {
        setDead();
        //super.onDeath(cause);
        dropItem(iteminit.DV_BUBBLE_GUN,1);

    }

    @Override
    public void onUpdate() {
        super.onUpdate();
        this.bossInfo.setPercent(this.getHealth()/this.getMaxHealth());
    }
}
