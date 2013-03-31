package seatox.minecraft.uwrunes;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.src.ModLoader;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

public class SpellEscape extends GenericSpell {

	public SpellEscape(String name, int cost, int circle, boolean enabled) {
		super(name, cost, circle, enabled);
	}

	@Override
	public void doCast(World currentWorld, EntityPlayer caster) {
		if (!currentWorld.isRemote)
		{
			if (caster.dimension == 1)
			{
				// No escape from The End! NO ESCAPE!
				caster.sendChatToPlayer("Hmm, looks like the easy way out is blocked.");
			}
			else
			{
				// Well, this is going to be fun.
				if (caster instanceof EntityPlayerMP)
				{
					EntityPlayerMP coward = (EntityPlayerMP) caster;
					WorldServer server = ModLoader.getMinecraftServerInstance().worldServerForDimension(0);
					ModLoader.getMinecraftServerInstance().getConfigurationManager().transferPlayerToDimension(coward, 0, new TeleporterEscapeSpot(server));
				}
			}
			
		}
			
	}

}
