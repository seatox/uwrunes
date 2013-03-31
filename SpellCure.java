package seatox.minecraft.uwrunes;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.world.World;

public class SpellCure extends GenericSpell {

	public SpellCure(String name, int cost, int circle, boolean enabled) {
		super(name, cost, circle, enabled);
	}

	@Override
	public void doCast(World currentWorld, EntityPlayer caster) {
		
		if (!currentWorld.isRemote)
		{
			caster.removePotionEffect(Potion.poison.id);
			caster.removePotionEffect(Potion.hunger.id);
		}

	}

}
