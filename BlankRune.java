package seatox.minecraft.uwrunes;

import net.minecraft.item.Item;

public class BlankRune extends Item {

	public BlankRune(int id) {
		super(id);		
	}

	public String getTextureFile() {
		return CommonProxy.ITEMS_PNG;
	}
}
