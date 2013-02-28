package seatox.minecraft.uwrunes;

import cpw.mods.fml.common.network.NetworkMod.SidedPacketHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityLargeFireball;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class SpellFireball implements GenericSpell {

	public SpellFireball() {
	
	}

	@Override

	public void doCast(World currentWorld, EntityPlayer caster) {
		if (!currentWorld.isRemote)
		{
			EntityLargeFireball boom = new EntityLargeFireball(currentWorld, caster, 0, 0, 0);
			currentWorld.spawnEntityInWorld(boom);
		}
	}
	
	public int getCircle()
	{
		return 3;
	}

}
