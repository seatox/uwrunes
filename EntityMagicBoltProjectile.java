package seatox.minecraft.uwrunes;

import net.minecraft.entity.Entity;
import net.minecraft.entity.IProjectile;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class EntityMagicBoltProjectile extends Entity implements IProjectile {

	int tileX;
	int tileY;
	int tileZ;
	
	
	
	public EntityMagicBoltProjectile(World world) {
		super(world);
	}

	@Override
	public void setThrowableHeading(double var1, double var3, double var5,
			float var7, float var8) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void entityInit() {
		// TODO Auto-generated method stub

	}

	
	
	@Override
	public void onUpdate() {
		super.onUpdate();
	}
	
	@Override
	protected void readEntityFromNBT(NBTTagCompound var1) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound var1) {
	

	}

}
