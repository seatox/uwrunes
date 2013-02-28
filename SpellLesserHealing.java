package seatox.minecraft.uwrunes;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class SpellLesserHealing implements GenericSpell {

	public SpellLesserHealing() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void doCast(World currentWorld, EntityPlayer caster) {
		caster.heal(2);		
	}

	public int getCircle()
	{
		return 1;
	}
}
