package seatox.minecraft.uwrunes;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockAnkh extends BlockContainer {

	public BlockAnkh(int id) {
		super(id, Material.rock);
		this.setCreativeTab(CreativeTabs.tabDecorations);
	}

	@Override
	public TileEntity createNewTileEntity(World var1) {
		return new TileEntityAnkh();
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y,
			int z, EntityPlayer player, int side, float vecX,
			float vecY, float vecZ) {
		TileEntity te = world.getBlockTileEntity(x, y, z);
		if (te == null || player.isSneaking())
		{
			return false;
		}
		else
		{		
			player.openGui(Uwrunes.instance, 2, world, x, y, z);
			return true;
		}		

	}
}
