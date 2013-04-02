package seatox.minecraft.uwrunes.client;

import java.util.EnumSet;

import org.lwjgl.opengl.GL11;

import seatox.minecraft.uwrunes.ItemRunebag;
import seatox.minecraft.uwrunes.PlayerMagicStats;
import seatox.minecraft.uwrunes.Uwrunes;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;

public class HudRunebag implements ITickHandler {

	public HudRunebag() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void tickStart(EnumSet<TickType> type, Object... tickData) {
		// TODO Auto-generated method stub

	}

	@Override
	public void tickEnd(EnumSet<TickType> type, Object... tickData) {
		
		Minecraft minecraft = FMLClientHandler.instance().getClient();
		EntityPlayer player = minecraft.thePlayer;
		ItemStack currentItemStack = null;
		if (type.contains(TickType.RENDER))
		{
			if (player != null)
				currentItemStack = player.getCurrentEquippedItem();
				if (currentItemStack != null && currentItemStack.getItem() instanceof ItemRunebag)
				{
					renderRunebagHud(minecraft, player, currentItemStack);
				}
		}
			
	}
	
	public void renderRunebagHud(Minecraft minecraft, EntityPlayer player, ItemStack bag)
	{
		PlayerMagicStats stats = Uwrunes.tracker.getStatsForPlayer(player);
		int mana = stats.getMana();
		int maxmana = stats.getMaxMana();
		int circle = stats.getCircle();
		GL11.glPushMatrix();
		minecraft.fontRenderer.drawString("Mana:" + mana + "/" + maxmana + ", "+circle+"Circle", 0, 0, 65535);
		GL11.glPopMatrix();
	}
	

	@Override
	public EnumSet<TickType> ticks() {
		// TODO Auto-generated method stub
		return EnumSet.of(TickType.CLIENT, TickType.RENDER);
	}

	@Override
	public String getLabel() {
		// TODO Auto-generated method stub
		return "hudrunebag";
	}

}
