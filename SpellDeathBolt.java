package seatox.minecraft.uwrunes;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class SpellDeathBolt implements GenericSpell {

	public SpellDeathBolt() {
		// CORP POR - Death Bolt, Energy Bolt, whatever - this spell will do
		// massive damage to whatever it hits, enough to kill most mundane foes.
		// TODO: Make this a thing
	}

	@Override
	public void doCast(World currentWorld, EntityPlayer caster) {
		

	}

	@Override
	public int getCircle() {
		return 7;
	}

}
