package seatox.minecraft.uwrunes;

import java.util.HashMap;
import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;

public class ItemAttunedRune extends Item {

	@SideOnly(Side.CLIENT)
	protected HashMap<Uwrunes.Rune, Icon> icons;
	
	public ItemAttunedRune(int id) {
		super(id);
		this.setHasSubtypes(true);
		this.setMaxDamage(0);
		this.setMaxStackSize(64);
		this.setCreativeTab(CreativeTabs.tabMisc);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void updateIcons(IconRegister register) {
		
		icons = new HashMap<Uwrunes.Rune, Icon>();
		for(Uwrunes.Rune r: Uwrunes.Rune.values())
		{
			Icon i = register.registerIcon("Uwrunes:" + r.toString());
			icons.put(r, i);
		}
		
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public Icon getIconFromDamage(int subid) {		
		return icons.get(Uwrunes.Rune.values()[subid]);
	}
	
	@Override
	public String getItemDisplayName(ItemStack item) {
		int id = item.getItemDamage();
		return Uwrunes.Rune.values()[id].toString();
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
