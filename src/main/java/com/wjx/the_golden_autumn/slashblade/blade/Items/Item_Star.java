package com.wjx.the_golden_autumn.slashblade.blade.Items;

import com.mojang.realmsclient.gui.ChatFormatting;
import com.wjx.the_golden_autumn.event.updatecolor;
import com.wjx.the_golden_autumn.slashblade.slashUtil.BladeUtils;
import com.wjx.the_golden_autumn.slashblade.slashUtil.ItemSlashUtil;
import mods.flammpfeil.slashblade.ItemSlashBladeNamed;
import mods.flammpfeil.slashblade.TagPropertyAccessor;
import mods.flammpfeil.slashblade.item.ItemSlashBlade;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Enchantments;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;

import java.util.EnumSet;
import java.util.List;

import static com.wjx.the_golden_autumn.slashblade.BladeLoader.STAR;
import static com.wjx.the_golden_autumn.slashblade.slashUtil.BladeUtils.getCustomBlade;

public class Item_Star extends ItemSlashBladeNamed{


        ToolMaterial material;
        private Entity player;
        public static boolean hp = false;

        public Item_Star(ToolMaterial par2EnumToolMaterial, float baseAttackModifiers, String name) {
            super(par2EnumToolMaterial, baseAttackModifiers);
            this.setUnlocalizedName("wjx.autumn.itemstar."+ name);
            this.setRegistryName(name);
            ForgeRegistries.ITEMS.register(this);
            ItemSlashUtil.AU_BLADE.add(this);
            System.out.println("autumn:staritem");

        }
        public String getItemStackDisplayName(ItemStack stack)
        {
            return updatecolor.makeColour4(I18n.translateToLocal("name.slashblade.star.1"))+updatecolor.makeColour5(I18n.translateToLocal("name.slashblade.star.2"));
        }
        @SideOnly(Side.CLIENT)
        public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn)
        {
            super.addInformation(stack,worldIn,tooltip,flagIn);
            //tooltip.add(String.format("SBCOLOR: %s",stack.getTagCompound().getInteger("SummonedSwordColor")));
            //tooltip.add("");
            tooltip.add(updatecolor.makeColourPur(I18n.translateToLocal("info.slashblade.star.1")));
            tooltip.add("");
            tooltip.add(updatecolor.makeColourPur(I18n.translateToLocal("info.slashblade.star.2")));
            tooltip.add("");
            tooltip.add(updatecolor.makeColourPur(I18n.translateToLocal("info.slashblade.star.3")));
            tooltip.add("");
            tooltip.add(updatecolor.makeColourPur(I18n.translateToLocal("info.slashblade.star.4")));
            tooltip.add("");
            tooltip.add(updatecolor.makeColourPur(I18n.translateToLocal("info.slashblade.star.5")));
            tooltip.add("");
            tooltip.add(new StringBuilder(I18n.translateToLocal(Enchantments.POWER.getName())).append(ChatFormatting.GRAY).toString()+" "+updatecolor.makeColourRed("C"));

            tooltip.add(new StringBuilder(I18n.translateToLocal(Enchantments.SHARPNESS.getName())).append(ChatFormatting.GRAY).toString()+" "+updatecolor.makeColourRed("C"));

            tooltip.add(new StringBuilder(I18n.translateToLocal(Enchantments.UNBREAKING.getName())).append(ChatFormatting.GRAY).toString()+" "+updatecolor.makeColourRed("C"));

        }
        @SideOnly(Side.CLIENT)
        public void addInformationSpecialAttack(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
            EnumSet<SwordType> swordType = this.getSwordType(par1ItemStack);
            if (swordType.contains(SwordType.Bewitched)) {
                Object tag = getItemTagCompound(par1ItemStack);
                String key = "flammpfeil.slashblade.specialattack." + this.getSpecialAttack(par1ItemStack).toString();
                par3List.add(String.format("SA:" + updatecolor.makeColourRed(I18n.translateToLocal(key))));
            }

        }
        @SideOnly(Side.CLIENT)
        public void addInformationKillCount(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
            this.getSwordType(par1ItemStack);
            Object tag = getItemTagCompound(par1ItemStack);
            par3List.add("KillCount : " + updatecolor.makeColour4(I18n.translateToLocal("info.infinity")));
        }

        @SideOnly(Side.CLIENT)
        public void addInformationProudSoul(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
            this.getSwordType(par1ItemStack);
            Object tag = getItemTagCompound(par1ItemStack);
            par3List.add("ProudSoul : " + updatecolor.makeColour4(I18n.translateToLocal("info.infinity")));
        }
        @SideOnly(Side.CLIENT)
        public void addInformationRepairCount(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
            Object tag = getItemTagCompound(par1ItemStack);
            par3List.add("Refine : " + updatecolor.makeColour4(I18n.translateToLocal("info.infinity")));
        }

        /*@SideOnly(Side.CLIENT)
        public void addInformationMaxAttack(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
            NBTTagCompound tag = getItemTagCompound(par1ItemStack);
            float repair = (float)RepairCount.get(tag);
            EnumSet<SwordType> swordType = this.getSwordType(par1ItemStack);
            par3List.add("");
            par3List.add("RankAttackDamage");
            String header;
            String template;
            if (swordType.contains(SwordType.FiercerEdge)) {
                header = "§6B-A§r/§4S-SSS§r/§5Limit";
                template = ChatFormatting.GOLD + "+" + 32768.0 + ChatFormatting.WHITE + "/" + ChatFormatting.DARK_RED + "+" + 32768.0 + ChatFormatting.WHITE + "/" + ChatFormatting.DARK_PURPLE + "+" + 32768.0;
            } else {
                header = "§6B-SS§r/§4SSS§r/§5Limit";
                template = ChatFormatting.GOLD + "+" + 32768.0 + ChatFormatting.WHITE + "/" + ChatFormatting.DARK_RED + "+" + 32768.0 + ChatFormatting.WHITE + "/" + ChatFormatting.DARK_PURPLE + "+" + 32768.0;
            }

            float baseModif = this.getBaseAttackModifiers(tag);
            float maxBonus = 10.0F + repair;
            float level = (float)par2EntityPlayer.experienceLevel;
            float sss = baseModif + Math.min(maxBonus, level);
            par3List.add(header);
            par3List.add(String.format(template, baseModif, sss, baseModif + maxBonus));
        }*/
        @SideOnly(Side.CLIENT)
        public void addInformationSwordClass(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
            EnumSet<ItemSlashBlade.SwordType> swordType = this.getSwordType(par1ItemStack);
            NBTTagCompound tag = getItemTagCompound(par1ItemStack);

            par3List.add(updatecolor.makeColour5(I18n.translateToLocal("info.slashblade.god")));


        }

    @Override
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> subItems) {
        if (this.isInCreativeTab(tab)) {
            for(String bladename : BladeUtils.NamedBlades){
                ItemStack blade = getCustomBlade(bladename);
                NBTTagCompound tag = getItemTagCompound(blade);
                BaseAttackModifier.set(tag,0.0F);
                if(blade.getItemDamage() == OreDictionary.WILDCARD_VALUE) {
                    blade.setItemDamage(0);
                }
                if(!blade.isEmpty()) {
                    subItems.add(blade);
                }
            }
        }
    }

}
