package seatox.minecraft.uwrunes;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;

public class ContainerRunebag extends Container {

	public ContainerRunebag(InventoryPlayer player, InventoryRunebag runebag) {
		
		// Active rune slots
		
		addSlotToContainer(new SlotRune(runebag, 0, 62, 109));
		addSlotToContainer(new SlotRune(runebag, 1, 80, 109));
		addSlotToContainer(new SlotRune(runebag, 2, 99, 109));
		
		for (int i = 0; i < 9; i++)
		{
			for (int j = 0; j < 3; j++)
				addSlotToContainer(new SlotRune(runebag, (i+(j*9) + 3), (i * 18) + 8, (j * 18) + 33));
		}
		
		bindPlayerInventory(player);
	}
	
	
	protected void bindPlayerInventory(InventoryPlayer player)
	{
		
		
		for (int i = 0; i < 3; i++)
		{
			for (int j = 0; j < 9; j++)
			{
				addSlotToContainer(new Slot(player, j+(i * 9) + 9, (j * 18) + 8, i * 18 + 140));
			}
		}
		
		for (int i = 0; i < 9; i++)
		{
			addSlotToContainer(new Slot(player, i, 8 + i * 18, 198));
		}
			
	}
	
	
	@Override
	public boolean canInteractWith(EntityPlayer player) {
		return true;
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int slot) {
		

		ItemStack stack = null;
		Slot slotObject = (Slot) inventorySlots.get(slot);

		if (slotObject != null && slotObject.getHasStack())
		{
			ItemStack stackInSlot = slotObject.getStack();
			stack = stackInSlot.copy();

			if (slot > 2 && slot < 30)
			{
				if (!this.mergeItemStack(stackInSlot, 0, 3, false))
				{
					return null;
				}
			}
			else if (slot <3) {
				if (!this.mergeItemStack(stackInSlot, 3, 30, true))
				{
					return null;
				}
			}
			else if (slot > 29)
			{
				if (!this.mergeItemStack(stackInSlot, 3, 30, true))
				{
					return null;
				}
			}

			if (stackInSlot.stackSize <= 0) {
				slotObject.putStack( (ItemStack)null );
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
	
}
