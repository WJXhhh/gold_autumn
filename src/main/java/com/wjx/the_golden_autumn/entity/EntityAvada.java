package com.wjx.the_golden_autumn.entity;

public class EntityAvada {
    /*
    public EntityAvada(World par1World) {
        super(par1World);
    }
    public EntityAvada(World par1World, EntityLivingBase entityLiving, float AttackLevel) {
        super(par1World, entityLiving, AttackLevel);
    }
    public EntityAvada(World par1World, EntityLivingBase entityLiving, float AttackLevel, float roll) {
        super(par1World, entityLiving, AttackLevel, roll);
    }

    protected void entityInit() {
        super.func_70088_a();
        EntitySummonedBlade.getDataWatcher().addObject(8, (byte)0);
    }

    public boolean getBurst() {
        return this.func_70096_w().func_75683_a(8) != 0;
    }

    public void setBurst(boolean value) {
        this.func_70096_w().func_75692_b(8, (byte)(value ? 1 : 0));
    }

    public void starsAttackEntity(EntityLivingBase Entity) {
        if (!Entity.field_70128_L || Entity.func_110143_aJ() > 0.0F) {
            if (Entity instanceof EntityPlayer) {
                EntityPlayer player = (EntityPlayer)Entity;
                player.field_71071_by.func_70436_m();
                player.field_70172_ad = 0;
                player.func_70097_a(DamageSource.field_76380_i.func_82726_p().func_76348_h().func_76359_i().func_151518_m(), Float.MAX_VALUE);
                player.func_70606_j(0.0F);
                player.func_70645_a(DamageSource.field_76380_i);
                player.field_70128_L = true;
                player.func_70096_w().func_75692_b(6, MathHelper.func_76131_a(0.0F, 0.0F, Entity.func_110138_aP()));
            } else {
                Entity.field_70172_ad = 0;
                Entity.func_70097_a(DamageSource.field_76380_i.func_82726_p().func_76348_h().func_76359_i().func_151518_m(), Float.MAX_VALUE);
                Entity.func_70606_j(0.0F);
                Entity.func_70645_a(DamageSource.field_76380_i);
                Entity.field_70128_L = true;
                Entity.func_70096_w().func_75692_b(6, MathHelper.func_76131_a(0.0F, 0.0F, Entity.func_110138_aP()));
            }
        }

    }

    protected void attackEntity(Entity target) {
        if (this.getBurst()) {
            this.field_70170_p.func_72885_a(this, this.field_70165_t, this.field_70163_u, this.field_70161_v, 3.0F, false, false);
        }

        if (!this.field_70170_p.field_72995_K) {
            float magicDamage = Math.max(1.0F, this.AttackLevel);
            target.field_70172_ad = 0;
            DamageSource ds = (new EntityDamageSource("directMagic", this.getThrower())).func_76359_i().func_76348_h().func_82726_p();
            target.func_70097_a(ds, magicDamage);
            target.func_70097_a(ds, magicDamage);
            if (this.blade != null && target instanceof EntityLivingBase && this.thrower != null && this.thrower instanceof EntityLivingBase) {
                StylishRankManager.setNextAttackType(this.thrower, AttackTypes.Drive);
                ((ItemSlashBlade)this.blade.func_77973_b()).func_77644_a(this.blade, (EntityLivingBase)target, (EntityLivingBase)this.thrower);
                if (!target.func_70089_S()) {
                    ((EntityLivingBase)this.thrower).func_70691_i(1.0F);
                }

                ((EntityLivingBase)target).field_70737_aN = 1;
                ((EntityLivingBase)target).func_70606_j(0.0F);
                if (!target.func_70089_S() && !(target instanceof EntityPlayer)) {
                    ((EntityLivingBase)target).func_70645_a(DamageSource.field_76380_i);
                    ((EntityLivingBase)target).func_70606_j(0.0F);
                    ((EntityLivingBase)target).func_70097_a(DamageSource.field_76380_i, Float.MAX_VALUE);
                }

                if (!this.getBurst()) {
                    ((EntityLivingBase)target).func_70690_d(new PotionEffect(Potion.field_82731_v.func_76396_c(), 100, 1));
                }

                ((ItemSlashBlade)this.blade.func_77973_b()).setDaunting((EntityLivingBase)target);
            }
        }

        this.func_70106_y();
    }*/
}
