package seatox.minecraft.uwrunes;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.EnumSet;
import java.util.HashMap;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet250CustomPayload;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.IPlayerTracker;
import cpw.mods.fml.common.IScheduledTickHandler;
import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.network.Player;
import cpw.mods.fml.relauncher.Side;

public class PlayerMagicStatsTracker implements IPlayerTracker, IScheduledTickHandler {

	HashMap<EntityPlayer, PlayerMagicStats> stats; 
	
	public PlayerMagicStats getStatsForPlayer(EntityPlayer p)
	{
		if (stats.containsKey(p))
		{
			return stats.get(p);
		}
		else
		{
			return null;
		}
	}
	
	@Override
	public void tickStart(EnumSet<TickType> type, Object... tickData) {
		
	}

	@Override
	public void tickEnd(EnumSet<TickType> type, Object... tickData) {
		//Mana regeneration tick - SERVER SIDE ONLY
		for (EntityPlayer p: stats.keySet())
		{
			PlayerMagicStats pms = stats.get(p);
			pms.regenMana();
			
			transmitManaUpdate(p, pms);
						
		}
	}
	
	public void transmitManaUpdate(EntityPlayer player, PlayerMagicStats stats)
	{
		//transmit mana changes to a client - SEVER SIDE ONLY
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		DataOutputStream dos = new DataOutputStream(baos);
		try {
			dos.writeInt(stats.getMana());
			dos.writeInt(stats.getMaxMana());
			dos.writeInt(stats.getCircle());
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}

		Packet250CustomPayload packet = new Packet250CustomPayload();
		packet.channel = "UWRunePlayer";
		packet.data = baos.toByteArray();
		packet.length = baos.size();
		PacketDispatcher.sendPacketToPlayer(packet, (Player)player);
	}

	@Override
	public EnumSet<TickType> ticks() {
		return EnumSet.of(TickType.SERVER);
	}
	
	public PlayerMagicStatsTracker() {
		stats = new HashMap<EntityPlayer, PlayerMagicStats>();
	}

	@Override
	public String getLabel() {
		return "PlayerMagicHandler";
	}

	@Override
	public void onPlayerLogin(EntityPlayer player) {
			// Player logged in, read their NBT for stats.
			NBTTagCompound savedata = player.getEntityData();
			PlayerMagicStats playerstats = new PlayerMagicStats();
			playerstats.readFromNBT(savedata);
			stats.put(player, playerstats);
			transmitManaUpdate(player, stats.get(player));
	}

	@Override
	public void onPlayerLogout(EntityPlayer player) {
			// Player logged out, add their stats to NBT and remove from stats.
			
			NBTTagCompound savedata = player.getEntityData();
			PlayerMagicStats playerstats = stats.get(player);
			playerstats.writeToNBT(savedata);			
			stats.remove(player);
	}

	@Override
	public void onPlayerChangedDimension(EntityPlayer player) {
		// Nothing happening here yet.
	}

	@Override
	public void onPlayerRespawn(EntityPlayer player) {
		// You die, you lose all your magic juice.
		PlayerMagicStats playerstats = stats.get(player);
		playerstats.setMana(0);
	}

	public void updatePlayer(EntityPlayer player, int mana, int maxmana,
			int circle) {
		// Generally called on a client on receipt of an update packet.
		PlayerMagicStats playerstats = stats.get(player);
		if (stats != null)
		{
			playerstats.setMaxMana(maxmana);
			playerstats.setCircle(circle);
			playerstats.setMana(mana);
		}
		else
		{
		stats.put(player, new PlayerMagicStats(mana, maxmana, circle));
		}
	}

	@Override
	public int nextTickSpacing() {
		// Time between mana ticks
		// TODO: make this configurable via forge config
		return 20;
	}
	
}
