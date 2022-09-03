package com.wjx.the_golden_autumn.objects.item;

import com.google.common.collect.Multimap;
import com.sun.istack.internal.NotNull;
import com.wjx.the_golden_autumn.TheGoldenAutumnMod;
import com.wjx.the_golden_autumn.init.iteminit;
import com.wjx.the_golden_autumn.util.interfaces.IHasModel;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemSword;
import net.minecraftforge.common.util.EnumHelper;

public class MeleeWeaponBase extends ToolSword{
    private final double attackSpeed;
    public MeleeWeaponBase(String name,int maxUse,float damage, CreativeTabs tabs, int enchant,double attackSpeed) {
        super(name, EnumHelper.addToolMaterial(name,3,maxUse,4,damage - 4f,enchant), tabs);
        this.attackSpeed = attackSpeed - 4;
    }




    @Override
    public Multimap<String, AttributeModifier> getItemAttributeModifiers(EntityEquipmentSlot equipmentSlot) {
        Multimap<String, AttributeModifier> multimap = super.getItemAttributeModifiers(equipmentSlot);
        if (equipmentSlot == EntityEquipmentSlot.MAINHAND) {
            multimap.remove(SharedMonsterAttributes.ATTACK_SPEED.getName(), new AttributeModifier(ATTACK_SPEED_MODIFIER, "Weapon modifier", -2.4000000953674316D, 0));
            multimap.put(SharedMonsterAttributes.ATTACK_SPEED.getName(), new AttributeModifier(ATTACK_SPEED_MODIFIER, "Weapon modifier", this.attackSpeed, 0));
        }
        return multimap;
    }}
class ToolSword extends ItemSword implements IHasModel {
    public ToolSword(String name, ToolMaterial material, CreativeTabs tabs){
        super(material);
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(tabs);

        iteminit.ITEMS.add(this);

    }
    @Override
    public void registerModels(){
        TheGoldenAutumnMod.proxy.registerItemRenderer(this,0,"inventory");

    }
}

