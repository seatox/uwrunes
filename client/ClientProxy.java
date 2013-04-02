package seatox.minecraft.uwrunes.client;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;
import net.minecraftforge.client.MinecraftForgeClient;
import seatox.minecraft.uwrunes.CommonProxy;
import seatox.minecraft.uwrunes.PlayerMagicStatsTracker;
import seatox.minecraft.uwrunes.Uwrunes;

public class ClientProxy extends CommonProxy{

	public void registerTickers()
	{
	Uwrunes.tracker = new PlayerMagicStatsTracker();
	Uwrunes.spellcaster = new ClientSpellcaster();
	TickRegistry.registerScheduledTickHandler(Uwrunes.tracker, Side.SERVER);
	TickRegistry.registerTickHandler(new HudRunebag(), Side.CLIENT);
	GameRegistry.registerPlayerTracker(Uwrunes.tracker);
	}
	
}
