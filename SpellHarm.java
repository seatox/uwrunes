package seatox.minecraft.uwrunes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.minecraft.command.IEntitySelector;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumMovingObjectType;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class SpellHarm extends GenericSpell {


	public SpellHarm(String name, int cost, int circle, boolean enabled) {
		super(name, cost, circle, enabled);
	
	}

	public EntityLiving BoundedRaytraceEntity(World world, EntityLiving source, double range)
	{
		// Section the first:  Run an AABB of size -range to +range in all axis around the source, getting all encountered living entities.
		List hitlist;
		List validTraces = new ArrayList<Entity>();
		AxisAlignedBB area = source.boundingBox.expand(range, range, range);
		hitlist = world.selectEntitiesWithinAABB(EntityLiving.class, area, IEntitySelector.field_94557_a);
		// Section the second:  Run the raytrace between source and each hit, discard non-qualifiers.
		for (Object e: hitlist)
		{


			EntityLiving target = (EntityLiving) e;
			if (source != target)
			{
				if (rayTraceEntities(world, source, target, range))
				{				
					validTraces.add(e);
				}
			}
		}
		if (validTraces.size() > 0)
			return (EntityLiving) validTraces.get(0);
		else
			return null;

	}

	public boolean rayTraceEntities(World world, EntityLiving source, EntityLiving target, double range)
	{
		Vec3 aim = source.getLookVec();
		Vec3 pos = world.getWorldVec3Pool().getVecFromPool(source.posX, source.posY, source.posZ);
		if (source instanceof EntityPlayer)
		{
			pos.yCoord = pos.yCoord + ((double)((EntityPlayer)source).getEyeHeight());
		}
		Vec3 ray = pos.addVector(aim.xCoord * range, aim.yCoord * range, aim.zCoord * range);
		AxisAlignedBB hitbox = target.boundingBox;
		MovingObjectPosition cast = hitbox.calculateIntercept(pos, ray);

		if (cast != null)
		{
			return true;
		}
		return false;
	}

	
	@Override
	public void doCast(World currentWorld, EntityPlayer caster) {

		Entity target = this.BoundedRaytraceEntity(currentWorld, caster, 10.0D);
		if (target != null && target instanceof EntityLiving)
		{
			EntityLiving victim = (EntityLiving) target;
			victim.attackEntityFrom(DamageSource.magic, 4);
			victim.setLastAttackingEntity(caster);
		}
	
	}

}
