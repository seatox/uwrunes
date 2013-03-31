package seatox.minecraft.uwrunes;

import net.minecraft.item.Item;

public class ItemBlankRune extends Item {

	public ItemBlankRune(int id) {
		super(id);		
	}

	public String getTextureFile() {
		return CommonProxy.ITEMS_PNG;
	}
}
