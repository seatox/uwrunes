package seatox.minecraft.uwrunes;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class SpellProtectionFromFire extends GenericSpell {

	public SpellProtectionFromFire(String name, int cost, int circle,
			boolean enabled) {
		super(name, cost, circle, enabled);
	}

	@Override
	public void doCast(World currentWorld, EntityPlayer caster) {
		
		PotionEffect effect = new PotionEffect(Potion.fireResistance.id, 4800);
		caster.addPotionEffect(effect);

	}

}
