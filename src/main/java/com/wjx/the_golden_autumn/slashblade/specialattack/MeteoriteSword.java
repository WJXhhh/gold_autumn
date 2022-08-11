package com.wjx.the_golden_autumn.slashblade.specialattack;

import com.wjx.the_golden_autumn.proxy.CommonProxy;
import mods.flammpfeil.slashblade.ability.StylishRankManager;
import mods.flammpfeil.slashblade.entity.EntityDrive;
import mods.flammpfeil.slashblade.entity.selector.EntitySelectorAttackable;
import mods.flammpfeil.slashblade.item.ItemSlashBlade;
import mods.flammpfeil.slashblade.specialattack.ISuperSpecialAttack;
import mods.flammpfeil.slashblade.specialattack.SpecialAttackBase;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Enchantments;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.Iterator;
import java.util.List;

import static mods.flammpfeil.slashblade.specialattack.CircleSlash.AttackType;
import static net.minecraft.init.MobEffects.*;


public class MeteoriteSword extends SpecialAttackBase {
    @Override
    public String toString() {
        return "fallensun";
    }

    private Entity getEntityToWatch(EntityPlayer player) {
        World world = player.world;
        Entity target = null;

        for(int dist = 2; dist < 20; dist += 2) {
            AxisAlignedBB bb = player.getCollisionBoundingBox();
            Vec3d vec = player.getLookVec();
            vec = vec.normalize();
            bb = bb.grow(2.0D, 0.25D, 2.0D);
            bb = bb.offset(vec.x * (double)dist, vec.y * (double)dist, vec.z * (double)dist);
            List<Entity> list = world.getEntitiesInAABBexcluding(player, bb, EntitySelectorAttackable.getInstance());
            float distance = 30.0F;
            Iterator var9 = list.iterator();

            while(var9.hasNext()) {
                Entity curEntity = (Entity)var9.next();
                float curDist = curEntity.getDistance(player);
                if (curDist < distance) {
                    target = curEntity;
                    distance = curDist;
                }
            }

            if (target != null) {
                break;
            }
        }

        return target;
    }

    @Override
    public void doSpacialAttack(ItemStack stack, EntityPlayer player) {
        World world = player.world;
        NBTTagCompound tag = ItemSlashBlade.getItemTagCompound(stack);
        boolean cost;
        ItemSlashBlade blade;
        float baseModif;
        int level;
        float magicDamage;
        int rank;
        if (!world.isRemote) {

            blade = (ItemSlashBlade) stack.getItem();
            player.setHealth(player.getMaxHealth());
            //player.world.getGameRules().setOrCreateGameRule("keepInventory", "true");
            player.addPotionEffect(new PotionEffect(NIGHT_VISION, 5, 1, true, true));
            player.addPotionEffect(new PotionEffect(WATER_BREATHING, 5, 1, true, true));
            player.addPotionEffect(new PotionEffect(RESISTANCE, 5, 5, true, true));
            player.addPotionEffect(new PotionEffect(FIRE_RESISTANCE, 5, 0, true, true));
            player.addPotionEffect(new PotionEffect(INSTANT_HEALTH, 5, 240, true, true));
            baseModif = blade.getBaseAttackModifiers(tag);
            level = Math.max(1, EnchantmentHelper.getEnchantmentLevel(Enchantments.POWER, stack));
            magicDamage = baseModif;
            rank = StylishRankManager.getStylishRank(player);
            if (5 <= rank) {
                magicDamage = baseModif + ItemSlashBlade.AttackAmplifier.get(tag) * (5.0F + (float) level / 5.0F);
            }

            float[] speeds = new float[]{2.3F, 2.1F, 2.2F};

            for (int i = 0; i < speeds.length; ++i) {
                EntityDrive entityDrive = new EntityDrive(world, player, magicDamage, true, 1000000.0F);
                entityDrive.setInitialSpeed(speeds[i]);
                entityDrive.setLifeTime(100);
                if (entityDrive != null) {
                    world.spawnEntity(entityDrive);
                }
            }
        }
        if (!world.isRemote) {
            blade = (ItemSlashBlade) stack.getItem();
            Entity target = null;
            int entityId = ItemSlashBlade.TargetEntityId.get(tag);
            if (entityId != 0) {
                Entity tmp = world.getEntityByID(entityId);
                if (tmp != null && tmp.getDistance(player) < 100.0F) {
                    target = tmp;
                }
            }

            if (target == null) {
                target = this.getEntityToWatch(player);
            }
            blade.attackTargetEntity(stack, target, player, true);

            /*if (target != null && player.func_70694_bm().func_77973_b() == CommonProxy.bladefuck) {
                int cost = true;
                if (!ItemSlashBlade.ProudSoul.tryAdd(tag, -10, false)) {
                    stack.func_77972_a(50, player);
                }
            }*/


        }

 /*   @Override
    public String toString() {
        return "meteoritesword_wjx";
    }

    @Override
    public void doSpacialAttack(ItemStack stack, EntityPlayer player) {
        World world = player.world;
        NBTTagCompound tag = ItemSlashBlade.getItemTagCompound(stack);
        boolean cost;
        ItemSlashBlade blade;
        float baseModif;
        int level;
        float magicDamage;
        int rank;
        if (!world.isRemote) {
            cost = true;
            if (!ItemSlashBlade.ProudSoul.tryAdd(tag, -1, false)) {
                ItemSlashBlade.damageItem(stack, 50, player);
            }

            blade = (ItemSlashBlade)stack.getItem();
            player.setHealth(player.getMaxHealth());
            player.world.getGameRules().setOrCreateGameRule("keepInventory", "true");
            player.addPotionEffect(new PotionEffect(NIGHT_VISION, 5, 1, true,true));
            player.addPotionEffect(new PotionEffect(WATER_BREATHING, 5, 1, true,true));
            player.addPotionEffect(new PotionEffect(RESISTANCE, 5, 5, true,true));
            player.addPotionEffect(new PotionEffect(FIRE_RESISTANCE, 5, 0, true,true));
            player.addPotionEffect(new PotionEffect(INSTANT_HEALTH, 5, 240, true,true));
            baseModif = blade.getBaseAttackModifiers(tag);
            level = Math.max(1, EnchantmentHelper.getEnchantmentLevel(Enchantments.POWER,stack));
            magicDamage = baseModif;
            rank = StylishRankManager.getStylishRank(player);
            if (5 <= rank) {
                magicDamage = baseModif + ItemSlashBlade.AttackAmplifier.get(tag) * (5.0F + (float)level / 5.0F);
            }

            float[] speeds = new float[]{2.3F, 2.1F, 2.2F};

            for(int i = 0; i < speeds.length; ++i) {
                EntityDrive entityDrive = new EntityDrive(world, player, magicDamage, true, 1000000.0F);
                entityDrive.setInitialSpeed(speeds[i]);
                entityDrive.setLifeTime(100);
                if (entityDrive != null) {
                    world.spawnEntity(entityDrive);
                }
            }
        }

        ItemSlashBlade.setComboSequence(tag, ItemSlashBlade.ComboSequence.Kiriage);
        if (!world.isRemote) {
            cost = true;
            if (!ItemSlashBlade.ProudSoul.tryAdd(tag, -10, false)) {
                ItemSlashBlade.damageItem(stack, 50, player);
            }

            blade = (ItemSlashBlade)stack.getItem();
            baseModif = blade.getBaseAttackModifiers(tag);
            level = Math.max(1, EnchantmentHelper.getEnchantmentLevel(Enchantments.POWER, stack));
            magicDamage = baseModif;
            rank = StylishRankManager.getStylishRank(player);
            if (5 <= rank) {
                magicDamage = baseModif + ItemSlashBlade.AttackAmplifier.get(tag) * (5.0F + (float)level);
            }

            EntityAvada entityDA = new EntityAvada(world, player, magicDamage, 90.0F);
            if (entityDA != null) {
                world.func_72838_d(entityDA);
            }
        }

        if (!world.field_72995_K) {
            ItemSlashBlade blade = (ItemSlashBlade)stack.func_77973_b();
            Entity target = null;
            int entityId = ItemSlashBlade.TargetEntityId.get(tag);
            if (entityId != 0) {
                Entity tmp = world.func_73045_a(entityId);
                if (tmp != null && tmp.func_70032_d(player) < 100.0F) {
                    target = tmp;
                }
            }

            if (target == null) {
                target = this.getEntityToWatch(player);
            }

            if (target != null && player.func_70694_bm().func_77973_b() == CommonProxy.bladefuck) {
                int cost = true;
                if (!ItemSlashBlade.ProudSoul.tryAdd(tag, -10, false)) {
                    stack.func_77972_a(50, player);
                }

                StylishRankManager.setNextAttackType(player, AttackType);
                blade.attackTargetEntity(stack, target, player, true);
                player.func_71009_b(target);
                target.field_70159_w = 0.0D;
                target.field_70181_x = 0.0D;
                target.field_70179_y = 0.0D;
                target.func_70024_g(0.0D, 1.05D, 0.0D);
                if (target instanceof EntityLivingBase) {
                    blade.setDaunting((EntityLivingBase)target);
                    if (target instanceof EntityPlayer) {
                        EntityPlayer victim = (EntityPlayer)target;
                        victim.func_70691_i(Float.MAX_VALUE);
                        ((EntityPlayer)target).func_70690_d(new PotionEffect(Potion.field_76434_w.func_76396_c(), 100, -120));
                        if (!((EntityPlayer)target).field_71071_by.func_146028_b(CommonProxy.bladefuck)) {
                            Itemkiller.KillAllPlayer(victim);
                        }

                        if (((EntityPlayer)target).field_71071_by.func_146028_b(CommonProxy.bladefuck)) {
                            victim.func_70606_j(victim.func_110143_aJ() - 1.0F);
                        }
                    }
                }

                int level = EnchantmentHelper.func_77506_a(Enchantment.field_77345_t.field_77352_x, stack);
                float magicDamage = 25.0F + ItemSlashBlade.AttackAmplifier.get(tag) * ((float)level / 5.0F);

                for(int i = 0; i < 7; ++i) {
                    if (!world.field_72995_K) {
                        EntityPhantomSwordBase entityDrive = new EntityPhantomSwordBase(world, player, magicDamage, 180000.0F);
                        EntityPhantomSwordBase entityDrive1 = new EntityPhantomSwordBase(world, player, magicDamage, 180000.0F);
                        EntityPhantomSwordBase entityDrive2 = new EntityPhantomSwordBase(world, player, magicDamage, 180000.0F);
                        EntityPhantomSwordBase entityDrive3 = new EntityPhantomSwordBase(world, player, magicDamage, 180000.0F);
                        EntityPhantomSwordBase entityDrive4 = new EntityPhantomSwordBase(world, player, magicDamage, 180000.0F);
                        EntityPhantomSwordBase entityDrive5 = new EntityPhantomSwordBase(world, player, magicDamage, 180000.0F);
                        EntityPhantomSwordBase entityDrive6 = new EntityPhantomSwordBase(world, player, magicDamage, 180000.0F);
                        if (entityDrive != null) {
                            entityDrive.func_70012_b(target.field_70165_t - 3.0D + 1.0D, target.field_70163_u + 2.0D, target.field_70161_v - 3.0D + 1.0D, player.field_70177_z, player.field_70125_A);
                            entityDrive1.func_70012_b(target.field_70165_t - 3.0D + 2.0D, target.field_70163_u + 2.0D, target.field_70161_v - 3.0D + 2.0D, player.field_70177_z, player.field_70125_A);
                            entityDrive2.func_70012_b(target.field_70165_t - 3.0D + 3.0D, target.field_70163_u + 2.0D, target.field_70161_v - 3.0D + 3.0D, player.field_70177_z, player.field_70125_A);
                            entityDrive3.func_70012_b(target.field_70165_t - 3.0D + 4.0D, target.field_70163_u + 2.0D, target.field_70161_v - 3.0D + 4.0D, player.field_70177_z, player.field_70125_A);
                            entityDrive4.func_70012_b(target.field_70165_t - 3.0D + 5.0D, target.field_70163_u + 2.0D, target.field_70161_v - 3.0D + 5.0D, player.field_70177_z, player.field_70125_A);
                            entityDrive5.func_70012_b(target.field_70165_t - 3.0D + 6.0D, target.field_70163_u + 2.0D, target.field_70161_v - 3.0D + 6.0D, player.field_70177_z, player.field_70125_A);
                            entityDrive6.func_70012_b(target.field_70165_t - 3.0D + 7.0D, target.field_70163_u + 2.0D, target.field_70161_v - 3.0D + 7.0D, player.field_70177_z, player.field_70125_A);
                            entityDrive.setColor(16711680);
                            entityDrive1.setColor(14972979);
                            entityDrive2.setColor(16776960);
                            entityDrive3.setColor(65280);
                            entityDrive4.setColor(65535);
                            entityDrive5.setColor(7377883);
                            entityDrive6.setColor(10040013);
                            entityDrive.setTargetEntityId(target.func_145782_y());
                            world.func_72838_d(entityDrive);
                            entityDrive1.setTargetEntityId(target.func_145782_y());
                            world.func_72838_d(entityDrive1);
                            entityDrive2.setTargetEntityId(target.func_145782_y());
                            world.func_72838_d(entityDrive2);
                            entityDrive3.setTargetEntityId(target.func_145782_y());
                            world.func_72838_d(entityDrive3);
                            entityDrive4.setTargetEntityId(target.func_145782_y());
                            world.func_72838_d(entityDrive4);
                            entityDrive5.setTargetEntityId(target.func_145782_y());
                            world.func_72838_d(entityDrive5);
                            entityDrive6.setTargetEntityId(target.func_145782_y());
                            world.func_72838_d(entityDrive6);
                        }
                    }
                }
            }
        }
    }*/
    }
}