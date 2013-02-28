package seatox.minecraft.uwrunes;

import java.util.Hashtable;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class SpellCaster {

	Hashtable<String, GenericSpell> spellList = new Hashtable<String, GenericSpell>();
	
	public SpellCaster() {
		spellList.put("in bet mani", new SpellLesserHealing());
		spellList.put("ort jux", new SpellMagicArrow());
		spellList.put("in mani ylem", new SpellCreateFood());
		spellList.put("corp por", new SpellDeathBolt());
	}

	public void cast(World world, EntityPlayer source, String spellWords)
	{
		System.out.println("Invocation: " + spellWords + " by " + source.toString());
		if (spellList.containsKey(spellWords))
		{
			System.out.println("Success!");
			GenericSpell spell = spellList.get(spellWords);
			PlayerMagicStats sourceStats = Uwrunes.tracker.getStatsForPlayer(source);
			System.out.println(sourceStats.getMana() + " of " + sourceStats.getMaxMana());
			int cost = spell.getCircle() * 2;
			if (sourceStats.hasMana(cost))
			{		
				
				if (!world.isRemote)
				{
					
					sourceStats.subtractMana(cost);
					Uwrunes.tracker.transmitManaUpdate(source, sourceStats);
					System.out.println("Server says they have " + sourceStats.getMana() + " left");
				}
				else
				{
					System.out.println("Client says they have " + sourceStats.getMana() + " left");
					
				}
				spell.doCast(world, source);		
			}
			
			
		}
		else
		{
			//do more failure stuff here
			return;
		}
	}
	
}
