package seatox.minecraft.uwrunes;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public abstract class GenericSpell {
	
	
	
	int circle;
	int cost;
	boolean enabled;
	String name;
	
	GenericSpell()
	{
		this.circle = 1;
		this.cost = 1;
		this.enabled = false;
		this.name = "Generic Spell";
	}
	
	GenericSpell(String name, int cost, int circle, boolean enabled)
	{
		this.circle = circle;
		this.cost = cost;
		this.enabled = enabled;
		this.name = name;
	}
	
	public abstract void doCast(World currentWorld, EntityPlayer caster);
	
	public int getCircle()
	{
		return circle;
	}
	public int getCost()
	{
		return cost;
	}
	
	public String getName()
	{
		return name;
	}
	
}
