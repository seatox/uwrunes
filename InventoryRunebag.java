package seatox.minecraft.uwrunes;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.*;

public class InventoryRunebag extends InventoryBasic {

	// 1 slot for each rune
	private ItemStack[] runes;

	public InventoryRunebag() {
		super("Runes", 30);
	
	}

    public void loadInventoryFromNBT(NBTTagList tags)
    {
        

        for (int i = 0; i < this.getSizeInventory(); i++)
        {
            this.setInventorySlotContents(i, (ItemStack)null);
        }

        for (int i = 0; i < tags.tagCount(); ++i)
        {
            NBTTagCompound tag = (NBTTagCompound)tags.tagAt(i);
            int slot = tag.getByte("Slot") & 255;

            if (slot >= 0 && slot < this.getSizeInventory())
            {
                this.setInventorySlotContents(slot, ItemStack.loadItemStackFromNBT(tag));
            }
        }
    }
	
    public NBTTagList saveInventoryToNBT()
    {
        NBTTagList tags = new NBTTagList("Runebag");

        for (int i = 0; i < this.getSizeInventory(); i++)
        {
            ItemStack stack = this.getStackInSlot(i);

            if (stack != null)
            {
                NBTTagCompound tag = new NBTTagCompound();
                tag.setByte("Slot", (byte)i);
                stack.writeToNBT(tag);
                tags.appendTag(tag);
            }
        }
        return tags;
    }
    	
}