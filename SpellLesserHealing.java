package seatox.minecraft.uwrunes;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class SpellLesserHealing implements GenericSpell {

	public SpellLesserHealing() {
		// IN BET MANI - Lesser healing, heals a single heart.
		// TODO: Evaluate balance between IBM and the food from IMY
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
