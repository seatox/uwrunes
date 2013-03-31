package seatox.minecraft.uwrunes.client;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.network.Player;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import seatox.minecraft.uwrunes.ContainerAnkh;
import seatox.minecraft.uwrunes.TileEntityAnkh;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.util.ChatAllowedCharacters;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.util.StatCollector;

@SideOnly(Side.CLIENT)
public class GuiAnkh extends GuiContainer {

	protected String mantra = "";
	protected int xSize = 176;
	protected int ySize = 166;
	
	public GuiAnkh(InventoryPlayer player, TileEntityAnkh ankh) {
		super(new ContainerAnkh(player, ankh));
	}

	@Override
	public void updateScreen() {
		super.updateScreen();
	}
	
	public void sendMantra(String mantra)
	{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		DataOutputStream dos = new DataOutputStream(baos);
		try {
			dos.writeUTF(mantra);
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}

		Packet250CustomPayload packet = new Packet250CustomPayload();
		packet.channel = "UWRuneCrafting";
		packet.data = baos.toByteArray();
		packet.length = baos.size();
		PacketDispatcher.sendPacketToServer(packet);
	}
	
	
	@Override
	protected void keyTyped(char keychar, int keycode) {
		//super.keyTyped(keychar, keycode);
		if (keycode == 1)
			this.mc.thePlayer.closeScreen();
		
		if (keycode == 28)
		{
			if (this.inventorySlots instanceof ContainerAnkh)
			{
				sendMantra(mantra);
				if (((ContainerAnkh) this.inventorySlots).tryInvoke(mantra));
					mantra = "";
			}
		}
		
		if (keycode == 14)
			mantra = "";
			
		if (ChatAllowedCharacters.isAllowedCharacter(keychar))
			mantra = mantra + keychar;
		
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {
		this.mc.renderEngine.bindTexture("/mods/UWrunes/textures/gui/GuiAnkh.png");
		GL11.glColor4f(1.0F,  1.0F,  1.0F, 1.0F);
		int x = (width - xSize) / 2;
		int y = (height - ySize) / 2;
		this.drawTexturedModalRect(x, y, 0, 0, xSize, ySize);		
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int x, int y) {
		fontRenderer.drawString(mantra, 8, 32, 4210752);
		fontRenderer.drawString(StatCollector.translateToLocal("container.inventory"), 8, this.ySize - 96, 4210752);
	}
	
}
