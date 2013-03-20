package seatox.minecraft.uwrunes;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.world.World;

public class EntityMagicArrow extends EntityArrow {

	
	public EntityMagicArrow(World par1World) {
		super(par1World);
		canBePickedUp = 0;
		// TODO Auto-generated constructor stub
	}

	public EntityMagicArrow(World par1World, double par2, double par4,
			double par6) {
		super(par1World, par2, par4, par6);
		canBePickedUp = 0;
		// TODO Auto-generated constructor stub
	}

	public EntityMagicArrow(World par1World, EntityLiving par2EntityLiving,
			EntityLiving par3EntityLiving, float par4, float par5) {
		super(par1World, par2EntityLiving, par3EntityLiving, par4, par5);
		canBePickedUp = 0;
		// TODO Auto-generated constructor stub
	}

	public EntityMagicArrow(World par1World, EntityLiving par2EntityLiving,
			float par3) {
		super(par1World, par2EntityLiving, par3);
		canBePickedUp = 0;
		// TODO Auto-generated constructor stub
	}

}
