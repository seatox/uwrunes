package seatox.minecraft.uwrunes;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;

public class CommonProxy {
	public static String ITEMS_PNG = "/seatox/minecraft/uwrunes/itemtexture.png";
	public static String BLOCK_PNG = "/seatox/minecraft/uwrunes/blocktexture.png";
	
	public void registerRenderers()
	{

	}

	public void registerTickers()
	{
	Uwrunes.tracker = new PlayerMagicStatsTracker();
	TickRegistry.registerScheduledTickHandler(Uwrunes.tracker, Side.SERVER);
	GameRegistry.registerPlayerTracker(Uwrunes.tracker);
	}
}
