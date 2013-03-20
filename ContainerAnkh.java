package seatox.minecraft.uwrunes;

import java.util.EnumSet;

import seatox.minecraft.uwrunes.Uwrunes.Rune;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;



public class ContainerAnkh extends Container {

	protected TileEntityAnkh tileEntity;
	protected InventoryPlayer playerInventory;
	
	public ContainerAnkh(InventoryPlayer player, TileEntityAnkh ankh) {
		tileEntity = ankh;
		playerInventory = player;
		addSlotToContainer(new Slot(tileEntity, 0, 37, 35));
		addSlotToContainer(new Slot(tileEntity, 1, 116, 35));
		
		bindPlayerInventory(player);
	}

	public Boolean tryInvoke(String mantra)
	{
		if (!tileEntity.worldObj.isRemote)
		{					
			Slot destSlot = (Slot) inventorySlots.get(1);
			Slot sourceSlot = (Slot) inventorySlots.get(0);
			if (destSlot != null && sourceSlot != null)
			{
				if (!destSlot.getHasStack())
					if (sourceSlot.getHasStack())
					{
						ItemStack rune = sourceSlot.getStack();
						if (rune.getItem().itemID == Uwrunes.blank_rune_item.itemID)
						{
							Rune[] runeArray = Uwrunes.Rune.values();
							for (int i = 0; i < runeArray.length; i++)
							{
								if (runeArray[i].toString().equals(mantra))
								{
									if (!tileEntity.worldObj.isRemote)
									{
										rune.stackSize--;
										if (rune.stackSize < 1)
										{
											sourceSlot.putStack(null);
										}
										ItemStack newAttunedRune = new ItemStack(Uwrunes.attuned_rune_item, 1, i);
										destSlot.putStack(newAttunedRune);
										destSlot.onSlotChanged();
										sourceSlot.onSlotChanged();
										return true;
									}
									else {
										playerInventory.player.sendChatToPlayer(mantra);
										return true;
									}
								}

							}

						}
					}
			}
		}


		return false;
	}
	
	protected void bindPlayerInventory(InventoryPlayer player)
	{
		for (int i = 0; i < 3; i++)
		{
			for (int j = 0; j < 9; j++)
			{
				addSlotToContainer(new Slot(player, j+(i * 9) + 9, (j * 18) + 8,  84 + (i * 18)));
			}
		}
		for (int i = 0; i < 9; i++)
		{
			addSlotToContainer(new Slot(player, i, 8 + i * 18, 142));
		}
	}
	
	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int slot) {
		
		int playerInv = 36;
		int thingInv = 2;
		int totalSlots = playerInv+thingInv;
		
		ItemStack stack = null;
		Slot slotObject = (Slot) inventorySlots.get(slot);
		
		if (slotObject != null && slotObject.getHasStack())
		{
			ItemStack stackInSlot = slotObject.getStack();
			stack = stackInSlot.copy();
			
			if (slot < thingInv)
			{
				if (!this.mergeItemStack(stackInSlot, thingInv, totalSlots, true))
				{
					return null;
				}
			}
			else if (!this.mergeItemStack(stackInSlot, 0, thingInv, false))
			{
				return null;
			}
			if (stackInSlot.stackSize == 0) {
				slotObject.putStack(null);
			}
			else
			{
				slotObject.onSlotChanged();
			}
			
			if (stackInSlot.stackSize == stack.stackSize)
			{
				return null;
			}
			slotObject.onPickupFromSlot(player, stackInSlot);
			}
			return stack;
			
		}
	
	
	@Override
	public boolean canInteractWith(EntityPlayer var1) {
		// TODO Auto-generated method stub
		return true;
	}

}
