package net.minecraft.vanity.main;


import org.lwjgl.input.Keyboard;

import net.minecraft.src.*;
import net.minecraft.vanity.gui.ReliantGui;
import net.minecraft.vanity.gui.WindowKillAura;
import net.minecraft.vanity.gui.array.GUI;
import net.minecraft.vanity.hacks.Derp;
import net.minecraft.vanity.hacks.KillAura;
import net.minecraft.vanity.hacks.StormModBright;
import net.minecraft.vanity.hacks.Teleport;
import net.minecraft.vanity.keybinding.Keybinds;
import net.minecraft.client.*;

public class Wrapper {
    static int j = ScaledResolution.getScaledHeight();
	public static Teleport rtele;
	public static Minecraft mc;
	public static WindowKillAura killaura;
	public static VanityXrayManager xray;
    float modBrightnessEnabled;
    static final Wrapper ref = new Wrapper();
    private float gameBrightness[];
    private float gameBrightnessBackup[];
    public static Minecraft minecraft;
	static int i = 0;
	private static boolean keyStates[];
	
	public Wrapper(Minecraft minecraft, EntityPlayerSP entityplayersp, NetClientHandler netclienthandler)
	{
		rtele = new Teleport(mc);
        modBrightnessEnabled = 0.3F;
	}

	public Wrapper() 
	{
        modBrightnessEnabled = 0.3F;
    	keyStates = new boolean [256];
	}
	public static void shit(){
	}
}
