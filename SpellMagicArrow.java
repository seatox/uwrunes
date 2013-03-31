package seatox.minecraft.uwrunes;

import cpw.mods.fml.common.network.NetworkMod.SidedPacketHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityLargeFireball;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class SpellMagicArrow extends GenericSpell {

	public SpellMagicArrow(String string, int i, int j, boolean b) {
		super (string, i, j, b);
	}

	@Override

	public void doCast(World currentWorld, EntityPlayer caster) {
		if (!currentWorld.isRemote)
		{
			EntityMagicArrow arrow = new EntityMagicArrow(currentWorld, caster, 1);
			
			currentWorld.spawnEntityInWorld(arrow);
		}			
	}
	
}
