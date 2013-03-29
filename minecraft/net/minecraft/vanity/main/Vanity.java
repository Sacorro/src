package net.minecraft.vanity.main;

import java.awt.List;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

import org.lwjgl.input.Keyboard;

import net.minecraft.src.*;
import net.minecraft.client.*;
import net.minecraft.vanity.*;
import net.minecraft.vanity.gui.ReliantGui;
import net.minecraft.vanity.hacks.Derp;
import net.minecraft.vanity.hacks.KillAura;
import net.minecraft.vanity.hacks.Location;
import net.minecraft.vanity.hacks.mod_AutoSwitch;

public class Vanity
{
	public static Minecraft mc;
	public static String vanityversion = "Vanity 7.2.2";
    public static HashMap protectHashMap = new HashMap();
    public static HashMap POGMembers = new HashMap();
    public static ArrayList friends = new ArrayList();
    public static String defaultname = "Chuck_Knoblock";
    public static String lastMessage = "";
    //public static ArrayList pogmembers = new ArrayList();
    public static ArrayList vanitydevs = new ArrayList();
    public static boolean isPOGMember = false;
    public static boolean isVanityDev = false;
    public static double flySpeedXZ = 1;
    public static double flySpeedY = 0.1;
	public static boolean gui = false;
    public static boolean fly = false;
    public static boolean chestesp = true;
    public static boolean espbox = true;
    public static boolean tracer = false;
    public static boolean tracerplayer = true;
    public static boolean tracermob = false;
    public static boolean traceranimal = false;
    public static boolean radar = false;
    public static boolean protect = false;
    public static boolean compass = false;
    public static boolean nocheat = false;
    public static boolean derp = false;
    public static boolean god = false;
	public static boolean nofall = true;
	public static boolean killaura = false;
	public static boolean killauraplayer = true;
	public static boolean killauramob = true;
	public static boolean killauraanimal = false;
	public static boolean killauralock = false;
	public static boolean killauraarrow = false;
    public static boolean sneak = false;
    public static boolean bright = false;
    public static boolean speedmine = false;
    public static boolean xray = false;
    public static boolean sprint = false;
	public static boolean jesus = false;
	public static boolean nukersurvival = false;
	public static boolean nukercreative = false;
	public static boolean nukertorch = false;
	public static boolean ttfchat = true;
	public static boolean autotool = false;
	public static boolean noswing = false; // todo
	public static boolean noweather = false;
	public static boolean nameplates = false;
	public static boolean bignames = true;
	public static boolean array = true;
	public static boolean bars = false;
	public static float place = 3f;
	public static float opacity = 120f;
	public static float speed = 1f;
	public static float brightness = 12f;
	public static float step = 1f;
	public static boolean stepbool = true;
	public static boolean dangerxray = false;
	public static boolean diamondxray = true;
	public static boolean goldxray = false;
	public static boolean ironxray = false;
	public static boolean coalxray = false;
	public static boolean valuablexray = false;
	public static boolean otherxray = false;
	public static boolean legit = false;
	public static int kDelay = 0;
	public static boolean trollchat2 = false;
	public static String trollprefix = "";
    public static boolean trollchat = false;
    public static boolean invisibility = false;
	public static boolean instant = false;
	
	public static boolean retardstare = false;
	public static boolean retardstarereverse = false;
	public static boolean retardheadinbody = false;
	public static boolean retardheadinbodyrotating = false;
	public static boolean retardheadbang = false;
	public static boolean retardspin = false;
	public static boolean retardhump = false; // TODO
	public static boolean retardno = false;
	public static boolean retardyes = false;
	public static int hDelay = 2;
	public static int h2Delay = 1;
	public static float jump = 1f;
	public static boolean humpsneak = false;
	public static float flyspeed = 1f;
	
	public static boolean glide = false;
	public static boolean knockback = false;
	
	public static boolean autoblock = true;
	
	public static boolean antiknockback = false;
	public mod_AutoSwitch autoswitch;
	
    public static Entity nigga;
    private int timerdelay =4;
    public static int timer=0;
    public static boolean player;
    public static boolean mob ;
    public static boolean animal;
	public static boolean freecam;
	public static Location freecamPosition;
	
	public static void onUpdate(Minecraft Minecraft) {
		if (kDelay > 0 && killaura) {
			kDelay--;
		}
	}
}