package seatox.minecraft.uwrunes;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;
import seatox.minecraft.uwrunes.ContainerRunebag;
import seatox.minecraft.uwrunes.client.GuiAnkh;
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
		case 2:
				TileEntity t = world.getBlockTileEntity(x, y, z);
				if (t instanceof TileEntityAnkh)
				{
					return new ContainerAnkh(player.inventory, (TileEntityAnkh)t);
				}
				else
				{
					return null;
				}
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
		case 2:
			TileEntity t = world.getBlockTileEntity(x, y, z);
			if (t instanceof TileEntityAnkh)
			{
				return new GuiAnkh(player.inventory, (TileEntityAnkh)t);
			}
			else
			{
				return null;
			}
		default:
				return null;
		}
		
	}

}
