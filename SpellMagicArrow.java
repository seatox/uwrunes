package seatox.minecraft.uwrunes;

import cpw.mods.fml.common.network.NetworkMod.SidedPacketHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityLargeFireball;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class SpellMagicArrow implements GenericSpell {

	public SpellMagicArrow() {
		// ORT JUX - Magic arrow - slings an arrow at a target.
		// TODO: Make the arrow a not-permanent one, maybe implement a whole new projectile.
	}

	@Override

	public void doCast(World currentWorld, EntityPlayer caster) {
		if (!currentWorld.isRemote)
		{
			EntityMagicArrow arrow = new EntityMagicArrow(currentWorld, caster, 1);
			
			currentWorld.spawnEntityInWorld(arrow);
		}			
	}
	
	public int getCircle()
	{
		return 1;
	}
	
	public String toString()
	{
		return "Magic Arrow";
	}

}
