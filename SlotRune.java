package seatox.minecraft.uwrunes;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotRune extends Slot {

	public SlotRune(IInventory par1iInventory, int par2, int par3, int par4) {
		super(par1iInventory, par2, par3, par4);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public boolean isItemValid(ItemStack par1ItemStack) {

		if ((par1ItemStack.itemID  == Uwrunes.blank_rune_item.itemID)
			|| par1ItemStack.itemID == Uwrunes.attuned_rune_item.itemID)
			return true;
		else
			return false;
	}

}
