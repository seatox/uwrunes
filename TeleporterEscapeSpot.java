package seatox.minecraft.uwrunes;

import cpw.mods.fml.common.network.Player;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.Teleporter;
import net.minecraft.world.WorldServer;

public class TeleporterEscapeSpot extends Teleporter {

	WorldServer server;
	
	public TeleporterEscapeSpot(WorldServer par1WorldServer) {
		super(par1WorldServer);
		server = par1WorldServer;
	}
	
	@Override
	public void placeInPortal(Entity par1Entity, double par2, double par4,
			double par6, float par8) {
		ChunkCoordinates spawn = server.provider.getRandomizedSpawnPoint();
		if (par1Entity instanceof EntityPlayer)
		{	
			par1Entity.setPosition(spawn.posX + 0.5D, spawn.posY + 1.0D, spawn.posZ + 0.5D);
        }
		
		
	}
	

}
