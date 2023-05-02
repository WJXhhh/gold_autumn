package com.wjx.the_golden_autumn.entity;

import com.wjx.the_golden_autumn.EntityMobInPeace;
import com.wjx.the_golden_autumn.init.iteminit;
import com.wjx.the_golden_autumn.lib.SeverSender;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityPotion;
import net.minecraft.entity.projectile.EntitySnowball;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

import java.util.List;

public class ChengZi extends EntityMobInPeace {
    public ChengZi(World worldIn) {
        super(worldIn);
    }
    private static byte HealthCD = 0;
    private static byte ThrowCD = 0;

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
        if(Math.random()>0.5){
            return (SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("the_golden_autumn:chengzihurt2"));
        }
        return (SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("the_golden_autumn:chengzihurt1"));
    }

    public SoundEvent getDeathSound() {
        return (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("the_golden_autumn:chengzidead"));
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(20D);
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(100);
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
        if (this.getAttackTarget() != null && !this.getAttackTarget().isDead && !this.getEntityData().getBoolean("battle")){
            this.getEntityData().setBoolean("battle",true);
            double x = this.posX;
            double y =this.posY;
            double z =this.posZ;
            if (!world.isRemote) {
                world.playSound((EntityPlayer) null, new BlockPos((int)x, (int)y, (int)z), (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("the_golden_autumn:chengziattack")), SoundCategory.NEUTRAL, 1.0F, 1.0F);
            } else {
                world.playSound(x, y, z, (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("the_golden_autumn:chengziattack")), SoundCategory.NEUTRAL, 1.0F, 1.0F, false);
            }
        }
        if(this.getAttackTarget() != null){
            this.getEntityData().setBoolean("battle", !this.getAttackTarget().isDead);
        }
        else {
            this.getEntityData().setBoolean("battle",false);
        }
        if (HealthCD >= 10 && !isDead && !(this.getHealth()<=0)){
            if (this.getMaxHealth() - this.getHealth() > 2){
                this.setHealth(this.getHealth() + 2);
            }
            HealthCD = 0;
        }
        else {
            HealthCD++;
        }
        if (this.getAttackTarget() != null && !this.getAttackTarget().isDead){
            ThrowCD++;
            if (ThrowCD >= 5){
                ThrowCD = 0;
                FruitChengZiThrown entity = new FruitChengZiThrown(this.world,this);
                EntityLivingBase target = this.getAttackTarget();
                double d0 = target.posY + (double)target.getEyeHeight() - 1.100000023841858D;
                double d1 = target.posX - this.posX;
                double d2 = d0 - entity.posY;
                double d3 = target.posZ - this.posZ;
                float f = MathHelper.sqrt(d1 * d1 + d3 * d3) * 0.2F;
                entity.shoot(d1, d2 + (double)f, d3, 1.6F, 12.0F);
                this.world.spawnEntity(entity);
            }
        }
        //SeverSender.sendTotalMessage(String.valueOf(ThrowCD));
    }

    @Override
    public void onDeath(DamageSource cause) {
        setDead();
        EntityItem item = new EntityItem(world,posX,posY,posZ,new ItemStack(iteminit.ORANGE_CRYSTAL,1));
        world.spawnEntity(item);
    }
}
