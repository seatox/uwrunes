package seatox.minecraft.uwrunes;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class SpellLesserHealing extends GenericSpell {

	public SpellLesserHealing(String string, int i, int j, boolean b) {
		super(string, i, j, b);
	}

	public void doCast(World currentWorld, EntityPlayer caster) {
		caster.heal(2);		
	}
	
}
