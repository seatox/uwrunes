package seatox.minecraft.uwrunes;

import javax.swing.text.html.parser.Entity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class SpellDeathBolt extends GenericSpell {


	@Override
	@SideOnly(Side.SERVER)
	public void doCast(World currentWorld, EntityPlayer caster) {
		
		
	}

	@Override
	public int getCircle() {
		return 7;
	}
	
	public int getCost()
	{
		return 14;
	}
	
}
