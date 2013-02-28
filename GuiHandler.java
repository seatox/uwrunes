package seatox.minecraft.uwrunes;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;
import seatox.minecraft.uwrunes.ContainerRunebag;
import seatox.minecraft.uwrunes.client.GuiRunebag;

public class GuiHandler implements IGuiHandler {

	public GuiHandler() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z) {
		
		PlayerMagicStats stats = Uwrunes.tracker.getStatsForPlayer(player);
		
		switch (ID) {
		case 1:
				return new ContainerRunebag(player.inventory, stats.getRunebag());
		default:
				return null;
		}
		
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z) {		
		PlayerMagicStats stats = Uwrunes.tracker.getStatsForPlayer(player);
		
		switch (ID) {
		case 1:
				return new GuiRunebag(player.inventory, new InventoryRunebag(), "Runebag");
		default:
				return null;
		}
		
	}

}
