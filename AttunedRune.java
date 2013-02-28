package seatox.minecraft.uwrunes;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class AttunedRune extends Item {

	public AttunedRune(int id) {
		super(id);
		this.setHasSubtypes(true);
		this.setMaxDamage(0);
		this.setMaxStackSize(64);
		this.setCreativeTab(CreativeTabs.tabMisc);
		
	}
	
	public String getTextureFile() {
		return CommonProxy.ITEMS_PNG;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public int getIconFromDamage(int subid) {
		int texture_id = this.iconIndex + subid;		
		return texture_id;
	}
	
	@Override
	public String getItemNameIS(ItemStack item) {
		int id = item.getItemDamage();
		return super.getItemName() + "." + Uwrunes.Rune.values()[id].toString();
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(int itemid, CreativeTabs tabs,
			List returnlist) {
		
	    for (int i = 0; i < Uwrunes.Rune.values().length; i++)
        {
            returnlist.add(new ItemStack(itemid, 1, i));
        }
	}
	
	
}
