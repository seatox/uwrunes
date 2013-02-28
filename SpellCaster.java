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
		spellList.put("flam por", new SpellFireball());
		spellList.put("in mani ylem", new SpellCreateFood());
		spellList.put("corp por", new SpellDeathBolt());
	}

	public void cast(World world, EntityPlayer source, String spellWords)
	{
		if (spellList.containsKey(spellWords))
		{
			GenericSpell spell = spellList.get(spellWords);
			PlayerMagicStats sourceStats = Uwrunes.tracker.getStatsForPlayer(source);
			int cost = spell.getCircle() * 2;
			if (sourceStats.hasMana(cost))
			{		
				
				if (!world.isRemote)
				{
					// SERVER SIDE
					sourceStats.subtractMana(cost);
					Uwrunes.tracker.transmitManaUpdate(source, sourceStats);
					source.addChatMessage(source.getEntityName() + "invokes " + spellWords.toUpperCase());
				}
				else
				{
					// CLIENT SIDE
					
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
