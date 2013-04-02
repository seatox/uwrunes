package seatox.minecraft.uwrunes;

import java.util.Hashtable;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class SpellCaster {

	Hashtable<String, GenericSpell> spellList = new Hashtable<String, GenericSpell>();
	
	public SpellCaster() {		
		//TODO: Make this pull from a config file.
		spellList.put("in bet mani", new SpellLesserHealing("Lesser Healing", 2, 1, true));
		spellList.put("ort jux", new SpellMagicArrow("Magic Arrow", 2, 1, true));
		spellList.put("flam por", new SpellFireball());
		spellList.put("in mani ylem", new SpellCreateFood("Create Food", 2, 1, true));
		spellList.put("corp por", new SpellDeathBolt());
		spellList.put("an nox", new SpellCure("Cure Poison", 4, 2, true));
		spellList.put("kal lor", new SpellEscape("HELP", 2, 1, true));
		spellList.put("wis lor", new SpellNightvision("Nightvision", 2, 1, true));
		spellList.put("an mani", new SpellHarm("Harm", 2, 1, true));
		spellList.put("an xen corp", new SpellSmiteUndead("Smite Undead", 2, 1, true));
		spellList.put("flam sanct", new SpellProtectionFromFire("Protection from Fire", 2, 1, true));
	}

	public void cast(World world, EntityPlayer source, String spellWords)
	{
		if (spellList.containsKey(spellWords))
		{
			GenericSpell spell = spellList.get(spellWords);
			PlayerMagicStats sourceStats = Uwrunes.tracker.getStatsForPlayer(source);
			if (sourceStats != null)
			{
				int cost = spell.getCost();
				int circle = spell.getCircle();			
				if (sourceStats.hasMana(cost) && sourceStats.getCircle() >= circle)
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
						source.addChatMessage("You invoke " + spell.getName());
					}
					spell.doCast(world, source);		
				}
				else
				{
					// Not enough mana, or not high enough circle.
					return;
				}
			
			}
		else
		{
			// Something has gone wrong, there is no stats data, so bail.
			return;
		}
	}
	
	}
}
