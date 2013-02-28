package seatox.minecraft.uwrunes;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public interface GenericSpell {
	
	public void doCast(World currentWorld, EntityPlayer caster);
	public int getCircle();
}
