package seatox.minecraft.uwrunes;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;
import cpw.mods.fml.relauncher.Side;

public class UwrunesNetworkHandler implements IPacketHandler {

	public UwrunesNetworkHandler() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void onPacketData(INetworkManager manager,
			Packet250CustomPayload packet, Player player) {
		
		if (packet.channel.equals("UWRunePlayer"))
		{
			if (FMLCommonHandler.instance().getSide() == Side.CLIENT)
			{
				int mana = 0;
				int maxmana = 0;
				int circle = 0;
				DataInputStream dis = new DataInputStream(new ByteArrayInputStream(packet.data));
				try {
					mana = dis.readInt();
					maxmana = dis.readInt();
					circle = dis.readInt();
				}
				catch (IOException e)
				{
					e.printStackTrace();
					return;
				}
				Uwrunes.tracker.updatePlayer((EntityPlayer)player, mana, maxmana, circle);			
			
			}
		}
		if (packet.channel.equals("UWRuneSpell"))
		{
			if (FMLCommonHandler.instance().getSide() == Side.SERVER)
			{
				
			}
		}
	}

	
	
}
