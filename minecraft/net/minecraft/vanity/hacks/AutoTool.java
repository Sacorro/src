package net.minecraft.vanity.hacks;

import net.minecraft.client.Minecraft;
import net.minecraft.vanity.main.Vanity;

public class AutoTool 
{
    public static Minecraft Minecraft;
	Minecraft mc;
	public static void BestTool (int par1, int par2, int par3, int par4){
		if(Vanity.autotool){
			try{
	    	if(Minecraft.thePlayer.inventory.hasItem(277) || Minecraft.thePlayer.inventory.hasItem(278) || Minecraft.thePlayer.inventory.hasItem(279) || Minecraft.thePlayer.inventory.hasItem(359) || Minecraft.thePlayer.inventory.hasItem(276))
	    	{
	    		for(int pa = 0; pa < pickaxeBlocks.length; pa++){
	    			if(Minecraft.theWorld.getBlockId(par1, par2, par3) == pickaxeBlocks[pa]){
	    				Minecraft.thePlayer.inventory.currentItem = Minecraft.thePlayer.inventory.getInventorySlotContainItem(278);
	    			}
	    		}
	    		for(int si = 0; si < shovelBlocks.length; si++){
	    			if(Minecraft.theWorld.getBlockId(par1, par2, par3) == shovelBlocks[si]){
	    				Minecraft.thePlayer.inventory.currentItem = Minecraft.thePlayer.inventory.getInventorySlotContainItem(277);
	    			}
	    		}
	    		for(int ai = 0; ai < axeBlocks.length; ai++){
	    			if(Minecraft.theWorld.getBlockId(par1, par2, par3) == axeBlocks[ai]){
	    				Minecraft.thePlayer.inventory.currentItem = Minecraft.thePlayer.inventory.getInventorySlotContainItem(279);
	    			}
	    		}
	    		for(int ui = 0; ui < shearBlocks.length; ui++){
	    			if(Minecraft.theWorld.getBlockId(par1, par2, par3) == shearBlocks[ui]){
	    				Minecraft.thePlayer.inventory.currentItem = Minecraft.thePlayer.inventory.getInventorySlotContainItem(359);
	    			}
	    		}
	    		for(int ni = 0; ni < swordBlocks.length; ni++){
	    			if(Minecraft.theWorld.getBlockId(par1, par2, par3) == swordBlocks[ni]){
	    				Minecraft.thePlayer.inventory.currentItem = Minecraft.thePlayer.inventory.getInventorySlotContainItem(276);
	    			}
	    		}
	    	}
			}catch(Exception shitnigger){}
		}
	}
	public static int pickaxeBlocks[] = {1, 4, 14, 15, 16, 21, 22, 23, 24, 33, 34, 41, 42, 43, 45, 48, 49, 56, 57, 61, 73, 74, 79, 89, 98, 108, 109}; 
	public static int shovelBlocks[] = {2, 3, 12, 13, 60, 80, 88, 110,};
	public static int axeBlocks[] = {5, 17, 29, 33, 34, 47, 54, 58, 85, 53};
	public static int shearBlocks[] = {35, 18};
	public static int swordBlocks[] = {20, 102};
	public static int xrayBlocks[] = {54};
}
