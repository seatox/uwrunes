package seatox.minecraft.uwrunes.client;

import org.lwjgl.opengl.GL11;

import seatox.minecraft.uwrunes.ContainerRunebag;
import seatox.minecraft.uwrunes.InventoryRunebag;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.StatCollector;

public class GuiRunebag extends GuiContainer {

	
	
	protected int xSize = 176;
	protected int ySize = 222;
	
	
	public String name;
	
	public GuiRunebag(InventoryPlayer player, InventoryRunebag runebag, String name) {		
		super(new ContainerRunebag(player, runebag));
		this.name = name;
		this.allowUserInput = false;
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float var1, int uiX,
			int uiY) {
		int texture = mc.renderEngine.getTexture("/seatox/minecraft/uwrunes/client/runebag.png");
		GL11.glColor4f(1.0F,  1.0F,  1.0F, 1.0F);
		this.mc.renderEngine.bindTexture(texture);
		int x = (width - xSize) / 2;
		int y = (height - ySize) / 2;
		this.drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int x, int y) {
		fontRenderer.drawString(name, 8, -5, 4210752);
		fontRenderer.drawString(StatCollector.translateToLocal("container.inventory"), 8, 100, 4210752);
	}
	
}
