package seatox.minecraft.uwrunes;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.item.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.*;
import net.minecraft.world.World;

public class ItemRunebag extends Item {

	public ItemRunebag(int id) {
		super(id);
	}

	public String getTextureFile() {
		return CommonProxy.ITEMS_PNG;
	}
	
	
	
	@Override
	public void onUpdate(ItemStack itemStack, World world,
			Entity holder, int par4, boolean held) {
		
		if ((holder instanceof EntityPlayer) && held && world.isRemote)
		{
			EntityPlayer player = (EntityPlayer)holder;
			
			
		}
	
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack item, World world,
			EntityPlayer player) {
		
		PlayerMagicStats casterState = Uwrunes.tracker.getStatsForPlayer(player);
		InventoryRunebag bag = casterState.getRunebag();
		if (player.isSneaking())
		{
			player.openGui(Uwrunes.instance, 1, world, player.chunkCoordX, player.chunkCoordY, player.chunkCoordZ);			
		}
		else
		{
			StringBuilder phrase = new StringBuilder();
			for (int i = 0; i < 3; i++)
			{
			ItemStack runeStack = bag.getStackInSlot(i);
			if (runeStack != null)
			{
				
				Item rune = runeStack.getItem();
				if (rune.itemID == Uwrunes.attuned_rune_item.itemID)
				{					
					phrase.append(Uwrunes.Rune.values()[runeStack.getItemDamage()].toString());
					phrase.append(" ");
				}
				
			
			}
			}
			Uwrunes.spellcaster.cast(world, player, phrase.toString().trim());
		}

		return item;
	}	

		
	public boolean shouldPassSneakingClickToBlock(World world, int par4,
			int par5, int par6) {
		
		return false;
	}
	
	
	
}
