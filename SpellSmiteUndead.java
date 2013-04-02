package seatox.minecraft.uwrunes;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class SpellSmiteUndead extends SpellHarm {

	public SpellSmiteUndead(String name, int cost, int circle, boolean enabled) {
		super(name, cost, circle, enabled);
	}
	
	@Override
	public void doCast(World currentWorld, EntityPlayer caster) {
	
		Entity target = this.BoundedRaytraceEntity(currentWorld, caster, 10.0D);
		if (target != null && target instanceof EntityLiving)
		{
			EntityLiving victim = (EntityLiving) target;
			if (victim.isEntityUndead())
			{
				victim.attackEntityFrom(DamageSource.magic, 100);
				victim.setLastAttackingEntity(caster);
			}
		}
	
	}

}
