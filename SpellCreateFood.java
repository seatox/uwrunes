package seatox.minecraft.uwrunes;

import java.util.Random;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class SpellCreateFood extends GenericSpell {

	
	
	Item[] foods = {Item.appleRed, Item.potato, Item.fishRaw, 
					Item.bread, Item.carrot, Item.beefRaw, Item.chickenRaw};

	public SpellCreateFood(String string, int i, int j, boolean b) {
		super (string, i, j, b);
	}

	public void doCast(World currentWorld, EntityPlayer caster) {
		Item pick = foods[new Random().nextInt(foods.length)];
		if (!currentWorld.isRemote)
		{
			caster.dropItem(pick.itemID, 1);
		}
	}

}
