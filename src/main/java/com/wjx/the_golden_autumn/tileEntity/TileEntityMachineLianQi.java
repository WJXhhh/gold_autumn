package com.wjx.the_golden_autumn.tileEntity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;

public class TileEntityMachineLianQi extends TileEntity implements IInventory, ITickable {
    private NonNullList<ItemStack> inventory = NonNullList.withSize(7,ItemStack.EMPTY);
    private String customName;

    @Override
    public int getSizeInventory() {
        return this.inventory.size();
    }

    @Override
    public boolean isEmpty() {
        for (ItemStack stack:this.inventory){
            if(stack.isEmpty()) return false;
        }
        return true;
    }

    @Override
    public ItemStack getStackInSlot(int index) {
        return this.inventory.get(index);
    }

    @Override
    public ItemStack decrStackSize(int index, int count) {
        return ItemStackHelper.getAndRemove(this.inventory,index);
    }

    @Override
    public ItemStack removeStackFromSlot(int index) {
        return ItemStackHelper.getAndRemove(this.inventory,index);
    }

    @Override
    public void setInventorySlotContents(int index, ItemStack stack) {
        ItemStack itemStack = this.inventory.get(index);
        boolean flag = !stack.isEmpty()&&stack.isItemEqual(itemStack)&&ItemStack.areItemsEqualIgnoreDurability(stack,itemStack);
        this.inventory.set(index,stack);

        if (stack.getCount() > this.getInventoryStackLimit())
            stack.setCount(this.getInventoryStackLimit());
        if(index == 0 && index +1 ==1&&!flag ){
            ItemStack stack1 = (ItemStack) this.inventory.get(index+1);
            this.markDirty();
        }

    }

    @Override
    public void readFromNBT(NBTTagCompound compound){
        super.readFromNBT(compound);
        this.inventory = NonNullList.withSize(this.getSizeInventory(),ItemStack.EMPTY);
        ItemStackHelper.loadAllItems(compound,this.inventory);

        if(compound.hasKey("CustomName",8))this.setCustomName(compound.getString("CustomName"));
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);

        ItemStackHelper.saveAllItems(compound,this.inventory);

        if (this.hasCustomName()) compound.setString("CustomName",this.customName);
        return compound;

    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    @Override
    public boolean isUsableByPlayer(EntityPlayer player) {
        return this.world.getTileEntity(this.pos) == this && player.getDistanceSq((double) this.pos.getX() + 0.5D, (double) this.pos.getY() + 0.5D, (double) this.pos.getZ() + 0.5D) <= 64.0D;
    }


    @Override
    public void openInventory(EntityPlayer player) {

    }

    @Override
    public void closeInventory(EntityPlayer player) {

    }

    @Override
    public boolean isItemValidForSlot(int index, ItemStack stack) {
        return false;
    }

    @Override
    public int getField(int id) {
        return 0;
    }

    @Override
    public void setField(int id, int value) {

    }

    @Override
    public int getFieldCount() {
        return 0;
    }

    @Override
    public void clear() {
        this.inventory.clear();

    }

    @Override
    public void update() {

    }

    public String getGuiID(){
        return "the_gold_autumn:lianqi";
    }

    @Override
    public String getName() {
        return this.hasCustomName() ? this.customName : "container.lianqi";
    }

    @Override
    public boolean hasCustomName() {
        return this.customName !=null &&!this.customName.isEmpty();
    }

    public void setCustomName(String customName){
        this.customName = customName;
    }

    @Override
    public ITextComponent getDisplayName() {
        return this.hasCustomName() ? new TextComponentString(this.getName()) : new TextComponentTranslation(this.getName());
    }
}
