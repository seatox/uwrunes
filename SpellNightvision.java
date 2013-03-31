package seatox.minecraft.uwrunes;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import seatox.minecraft.uwrunes.GenericSpell;

public class SpellNightvision extends GenericSpell {

	
	
	public SpellNightvision(String string, int i, int j, boolean b) {
		super(string, i, j, b);
	}

	@Override
	public void doCast(World currentWorld, EntityPlayer caster) {		
		
		PotionEffect effect = new PotionEffect(Potion.nightVision.id, 1200);
		caster.addPotionEffect(effect);
	}

}
