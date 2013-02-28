package seatox.minecraft.uwrunes;

import java.util.Map;
import java.util.HashMap;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.*;
import net.minecraft.block.*;

@Mod(modid="Uwrunes", name="Ultima Underworld Runes", version="0.0.1")
@NetworkMod(clientSideRequired=true, serverSideRequired=false, channels=("UWRunePlayer"), packetHandler=UwrunesNetworkHandler.class)
public class Uwrunes {
	public static Configuration config;
	public static PlayerMagicStatsTracker tracker;
	public static SpellCaster spellcaster; // null if client.

	//enum to keep everything correctly ordered.
	public enum Rune { an, bet, corp, des, ex, flam, grav, hur, in, jux,
		kal, lor, mani, nox, ort, por, quas, rel, sanct, tym,
		uus, vas, wis, xen, ylem, zu }

	//Item IDs
	public static Map<Rune, Integer> runeIds = new HashMap<Rune, Integer>();
	public static Item blank_rune_item;
	public static Item attuned_rune_item;
	public static Item runebag_item;
	public static int blank_rune_item_id;
	public static int blank_rune_texture_id = 26;
	public static int attuned_rune_item_id;
	public static int attuned_rune_texture_id;
	public static int runebag_item_id;
	public static int runebag_texture_id = 27;
	//Block IDs
	public static int ankh_block_id;

	// Forge Instance Setup
	@Instance("Uwrunes")
	public static Uwrunes instance;

	@SidedProxy(clientSide="seatox.minecraft.uwrunes.client.ClientProxy", serverSide="seatox.minecraft.uwrunes.CommonProxy")
	public static CommonProxy proxy;

	@PreInit
	public void preInit(FMLPreInitializationEvent event) {

		Configuration config = new Configuration(event.getSuggestedConfigurationFile());
		config.load();

		blank_rune_item_id = config.getItem("blank rune", 4001, "Unattuned runestone").getInt();
		attuned_rune_item_id = config.getItem("attuend rune",4000).getInt();
		runebag_item_id = config.getItem("runebag", 4002, "The runebag").getInt();              
		ankh_block_id = config.getBlock("ankh block", 4000, "Ankh for attuning runestones").getInt();

		config.save();               
	}

	@Init
	public void load(FMLInitializationEvent event) {
		initItems();
		initRecipies();
		NetworkRegistry.instance().registerGuiHandler(this, new GuiHandler());
		proxy.registerTickers();
		proxy.registerRenderers();
	}

	@PostInit
	public void postInit(FMLPostInitializationEvent event) {
		// Stub Method
	}

	protected void initItems()
	{
		//Blank rune
		blank_rune_item = new BlankRune(blank_rune_item_id)
		.setMaxStackSize(64)
		.setItemName("BlankRune")
		.setIconIndex(blank_rune_texture_id)
		.setCreativeTab(CreativeTabs.tabMisc);

		GameRegistry.registerItem(blank_rune_item,"BlankRune");
		LanguageRegistry.addName(blank_rune_item, "Blank Rune");

		
		// Attuned runes
		attuned_rune_item = new AttunedRune(attuned_rune_item_id);            	   				    	   				 
		GameRegistry.registerItem(attuned_rune_item,"AttunedRune");

		// Register names for each rune
		for (int i = 0; i < Rune.values().length; i++)
		{
			ItemStack runeStack = new ItemStack(attuned_rune_item, 1, i);
			LanguageRegistry.addName(runeStack, "Rune " + Rune.values()[i].toString());       
		}

		// Runebag
		runebag_item = new Runebag(runebag_item_id)
		.setMaxStackSize(1)
		.setItemName("RuneBag")
		.setCreativeTab(CreativeTabs.tabTools)
		.setIconIndex(runebag_texture_id);               					

		GameRegistry.registerItem(runebag_item, "RuneBag");
		LanguageRegistry.addName(runebag_item, "Rune Bag");

	}
	
	protected void initRecipies()
	{
		ItemStack woolBlock = new ItemStack(Block.cloth);
		ItemStack stoneBlock = new ItemStack(Block.stone);
		ItemStack goldnuggetItem = new ItemStack(Item.goldNugget);
		ItemStack stringItem = new ItemStack(Item.silk);
		ItemStack runebagItem = new ItemStack(Uwrunes.runebag_item);
		ItemStack blankruneItem = new ItemStack(Uwrunes.blank_rune_item);
		
		GameRegistry.addRecipe(blankruneItem, " x ", "xyx", " x ", 'x', stoneBlock, 'y', goldnuggetItem);
		GameRegistry.addRecipe(runebagItem, "xxx", "yzy", "yyy", 'x', stringItem, 'y', woolBlock, 'z', blankruneItem);
	
	}
}


