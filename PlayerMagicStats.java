package seatox.minecraft.uwrunes;

import net.minecraft.nbt.*;


public class PlayerMagicStats {

	int mana;
	int maxMana;
	int circle;
	InventoryRunebag runebag;
	
	
	public PlayerMagicStats()
	{
		initRunebag();
		setMana(0);
		setMaxMana(10);
		setCircle(1);			
	}
	
	public void writeToNBT(NBTTagCompound nbt)
	{
		int[] stats =  {mana, maxMana, circle};
		nbt.setIntArray("uwRuneStats", stats);
		nbt.setTag("Runebag", runebag.saveInventoryToNBT());		
	}
	
	public void readFromNBT(NBTTagCompound nbt)
	{
		int[] stats;
		if (nbt.hasKey("uwRuneStats"))
		{
		stats = nbt.getIntArray("uwRuneStats");
		if (stats != null)
		{
			mana = stats[0];
			maxMana = stats[1];
			circle = stats[2];
		}
		}
		runebag = new InventoryRunebag();
		if (nbt.hasKey("Runebag"))
		{
			runebag.loadInventoryFromNBT(nbt.getTagList("Runebag"));
		}
	}
	
	public PlayerMagicStats(int mana, int maxMana, int circle) {
		initRunebag();
		setCircle(circle);
		setMana(mana);
		setMaxMana(maxMana);	
	}
	public int getMana() {
		return mana;
	}
	
	public void initRunebag()
	{
		//TODO
		runebag = new InventoryRunebag();
	}
	
	public InventoryRunebag getRunebag()
	{
		return runebag;
	}
	
	
	public boolean hasMana(int amount)
	{
		return (mana >= amount);
	}
	
	public void subtractMana(int amount)
	{
		this.mana = Math.max(this.mana - amount, 0);
	}
	
	public void setMana(int mana) {
		this.mana = mana;
	}
	public int getMaxMana() {
		return maxMana;
	}
	public void setMaxMana(int maxMana) {
		this.maxMana = maxMana;
	}
	public int getCircle() {
		return circle;
	}
	public void setCircle(int circle) {
		this.circle = circle;
		recalcMaxMana();
	}	
	
	public void recalcMaxMana()
	{
		setMaxMana(circle * 10);
		setMana(Math.min(this.mana, this.maxMana));
	}
	
	public void regenMana()
	{
		this.mana = Math.min(this.mana + (this.circle), this.maxMana);
	}
		
}
