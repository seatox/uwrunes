package seatox.minecraft.uwrunes;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;

public class TileEntityAnkh extends TileEntity implements IInventory {

	private ItemStack ankhItemStacks[];
	
	public TileEntityAnkh() {
		ankhItemStacks = new ItemStack[2];
	}

	@Override
	public void readFromNBT(NBTTagCompound tagCompound) {
		super.readFromNBT(tagCompound);
		
		NBTTagList tagList = tagCompound.getTagList("Inventory");
		for (int i = 0; i < tagList.tagCount(); i++)
		{
			NBTTagCompound tag = (NBTTagCompound) tagList.tagAt(i);
			byte slot = tag.getByte("Slot");
			if (slot >=0 && slot < ankhItemStacks.length)
			{
				ankhItemStacks[slot] = ItemStack.loadItemStackFromNBT(tag);
			}
		}
		
	}
	
	@Override
	public void writeToNBT(NBTTagCompound tagCompound) {
		super.writeToNBT(tagCompound);
		
		NBTTagList itemList = new NBTTagList();
		for (int i = 0; i < ankhItemStacks.length; i++)
		{
			ItemStack stack = ankhItemStacks[i];
			if (stack != null)
			{
				NBTTagCompound tag = new NBTTagCompound();
				tag.setByte("Slot", (byte) i);
				stack.writeToNBT(tag);
				itemList.appendTag(tag);
			}
		}
		tagCompound.setTag("Inventory", itemList);
		
	}

	@Override
	public int getSizeInventory() {

		return ankhItemStacks.length;
	}

	@Override
	public ItemStack getStackInSlot(int slot) {
		if (slot < 2)
		{
			return ankhItemStacks[slot];
		}
		else
		{
			return null;
		}
	}

	@Override
	public ItemStack decrStackSize(int slot, int amount) {
		
		ItemStack stack = getStackInSlot(slot);
		if (stack != null)
		{
			if (stack.stackSize < amount)
			{
				setInventorySlotContents(slot, null);
				this.onInventoryChanged();
				return stack;
			}
			else
			{
				ItemStack newstack = stack.splitStack(amount);
				if (stack.stackSize == 0)
				{
					setInventorySlotContents(slot, null);
					this.onInventoryChanged();
				}
				return newstack;
			}		
		}
		return null;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int slot) {
		return null;
	}

	@Override
	public void setInventorySlotContents(int slot, ItemStack item) {
		ankhItemStacks[slot] = item;
	}

	@Override
	public String getInvName() {
		// TODO Auto-generated method stub
		return "container.ankh";
	}

	@Override
	public int getInventoryStackLimit() {
		// TODO Auto-generated method stub
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer var1) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void openChest() {		
	}

	@Override
	public void closeChest() {		
	}

	
}
