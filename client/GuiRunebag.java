package seatox.minecraft.uwrunes.client;

import org.lwjgl.opengl.GL11;
import org.lwjgl.input.Keyboard;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import seatox.minecraft.uwrunes.ContainerRunebag;
import seatox.minecraft.uwrunes.InventoryRunebag;
import seatox.minecraft.uwrunes.PlayerMagicStats;
import seatox.minecraft.uwrunes.Uwrunes;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.StatCollector;

@SideOnly(Side.CLIENT)
public class GuiRunebag extends GuiContainer {
		

	
	private IInventory runebag;
	private IInventory player;
	private PlayerMagicStats stats;
	
	public String name;
	
	public GuiRunebag(InventoryPlayer player, InventoryRunebag runebag, String name) {		
		super(new ContainerRunebag(player, runebag));
		this.runebag = runebag;
		this.player = player;
		player.player.openContainer = this.inventorySlots;
		stats = Uwrunes.tracker.getStatsForPlayer(player.player);
		this.allowUserInput = false;
		this.name = name;
		this.xSize = 176;
		this.ySize = 222;
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float var1, int uiX,
			int uiY) {
		this.mc.renderEngine.bindTexture("/mods/UWrunes/textures/gui/GuiRunebag.png");
		GL11.glColor4f(1.0F,  1.0F,  1.0F, 1.0F);
		int x = (width - xSize) / 2;
		int y = (height - ySize) / 2;
		this.drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int x, int y) {
		fontRenderer.drawString(name, 8, 6, 4210752);
		fontRenderer.drawString("Mana:"+ stats.getMana() + "/" + stats.getMaxMana(), 8, 16, 4210752);		
		fontRenderer.drawString(StatCollector.translateToLocal("container.inventory"), 8, 130, 4210752);
	}
	
	@Override
	public boolean doesGuiPauseGame() {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	
}
