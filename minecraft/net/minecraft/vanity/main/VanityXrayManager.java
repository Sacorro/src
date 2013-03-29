package net.minecraft.vanity.main;

public class VanityXrayManager {
	
	public static boolean[] xrayBlocks;
	
	static {
	xrayBlocks = new boolean[500];
	}
	
	static {
	xrayBlocks[16] = false; //coal
	xrayBlocks[56] = true; //diamond
	}
}
