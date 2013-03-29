package net.minecraft.vanity.commands;

import java.io.IOException;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.Minecraft;
import net.minecraft.src.*;
import net.minecraft.vanity.gui.ReliantGui;
import net.minecraft.vanity.gui.WindowKillAura;
import net.minecraft.vanity.hacks.Drop;
import net.minecraft.vanity.hacks.Teleport;
import net.minecraft.vanity.keybinding.KeyChange;
import net.minecraft.vanity.keybinding.Keybinds;
import net.minecraft.vanity.main.Vanity;
import net.minecraft.vanity.main.VanityFileWriter;
import net.minecraft.vanity.main.VanityXrayManager;
import net.minecraft.vanity.main.Wrapper;


public class Manager {

	private static Minecraft mc;
	private static VanityXrayManager xray;
	private static Teleport tele = new Teleport(mc);
	
	public Manager()
	{
		
	}
	
	public static void doCommand(String s)
	{
    	if(s.startsWith(".retard"))
    	{
    		Vanity.derp = !Vanity.derp;
			mc.thePlayer.addChatMessage(Vanity.derp ? "§9[Vanity]§f Retard Mode enabled." : "§9[Vanity]§f Retard Mode disabled.");
    		return;
    	}
    	if(s.startsWith(".quotebanana"))
    	{
    		String[] niggerfaggot = s.split(" ");
    		mc.thePlayer.sendChatMessage("You're an idiot - " + niggerfaggot[1]);
    	}
    	if(s.startsWith(".vanitycrash"))
    	{
    		mc.thePlayer.sendChatMessage("54r74y84ae484dsf7456a48793448qa");
    	}
    	if(s.startsWith(".derp"))
    	{
    		Vanity.derp = !Vanity.derp;
			mc.thePlayer.addChatMessage(Vanity.derp ? "§9[Vanity]§f Retard Mode enabled." : "§9[Vanity]§f Retard Mode disabled.");
    		return;
    	}
    	if(s.startsWith(".legit"))
    	{
    		Vanity.legit = !Vanity.legit;
    		mc.thePlayer.addChatMessage(Vanity.legit ? "§9[Vanity]§f Legit Mode enabled." : "§9[Vanity]§f Legit Mode disabled.");
    	}
    	if (s.startsWith(".reverse"))
    	{
    		Vanity.trollchat = !Vanity.trollchat;
    		mc.thePlayer.addChatMessage("§9[Vanity]§f Reverse chat " + Vanity.trollchat);
    	}
    	
    	else if (s.startsWith(".troll "))
    	{
    		String[] niggaz = s.split(".troll ");
    		if(niggaz[1].equals("off"))
    		{
    			Vanity.trollchat2 = false;
        		mc.thePlayer.addChatMessage(Vanity.trollchat2 ? "§9[Vanity]§f Now trolling chat!" : "§9[Vanity]§f No longer trolling chat!");
    			return;
    		}
    		Vanity.trollprefix = niggaz[1];
    		Vanity.trollchat2 = true;
    		mc.thePlayer.addChatMessage(Vanity.trollchat2 ? "§9[Vanity]§f Now trolling chat!" : "§9[Vanity]§f No longer trolling chat!");
    	}
    	if (s.startsWith(".tp "))
    	{
    		try{
    			String[] nig = s.split(" ");
    			int x = Integer.parseInt(nig[1]);
    			int y = Integer.parseInt(nig[2]);
    			int z = Integer.parseInt(nig[3]);
    			tele.flyTo(x, y, z);
    			mc.thePlayer.addChatMessage("Successfully teleported to: x: " + x + " y: " + y + " z: " + z );
    		}catch(Exception e){mc.thePlayer.addChatMessage("Teleport failed!");}
    	}
    	if(s.startsWith(".loc"))
    	{
    		try{
    			String[] nig = s.split(" ");
    			String name = nig[1]; 
    			EntityPlayer e = mc.theWorld.getPlayerEntityByName(name);
    			int x = (int) e.posX;
    			int y = (int) e.posY;
    			int z = (int) e.posZ;
    			mc.thePlayer.addChatMessage("Location of player is: x: " + x + " y: " + y + " z:" + z);
    		}catch(Exception e){mc.thePlayer.addChatMessage("Could not get location");}	
    	}
    	
    	if(s.startsWith(".coords"))
    	{
    		try{
    			String[] nig = s.split(" ");
    			String name = nig[1]; 
    			EntityPlayer e = mc.theWorld.getPlayerEntityByName(name);
    			int x = (int) e.posX;
    			int y = (int) e.posY;
    			int z = (int) e.posZ;
    			if(name.startsWith(mc.thePlayer.username))
    				mc.thePlayer.sendChatMessage("My coords are: x: " + x + " y: " + y + " z: " + z);
    			else
    			mc.thePlayer.sendChatMessage(name + "'s coords are: x:" + x + " y: " + y + " z:" + z + ". Problem?");
    		}catch(Exception e){mc.thePlayer.addChatMessage("Could not get location");}	
    	}
    	
    	if(s.startsWith(".tpp"))
    	{
    		try{
    			String[] nig = s.split(" ");
    			String name = nig[1]; 
    			if(name.startsWith(mc.thePlayer.username))
    				return;
    			EntityPlayer e = mc.theWorld.getPlayerEntityByName(name);
    			int x = (int) e.posX;
    			int y = (int) e.posY;
    			int z = (int) e.posZ;
    			tele.flyTo(x, y, z);
    		}catch(Exception e){mc.thePlayer.addChatMessage("Teleportation failed");}
    	}
    	/*
    	 * To use god, type .god, wait until it says in chat 'your username' has hit the ground
    	 * too hard, relog and it should look like you're taking damage, but you're not. #zenisretarded
    	 */
    	if(s.equals(".drop"))
    	{
    		Drop.createSmokescreen(true);
    	}
    	if (s.equals(".god"))
        {
    		Vanity.god = !Vanity.god;
			mc.thePlayer.addChatMessage(Vanity.god ? "§9[Vanity]§f God enabled." : "§9[Vanity]§f God disabled.");
			if(Vanity.god)
			{
	            EntityPlayer p = mc.thePlayer;
	            for (int i = 0; i < 30000; i++) {
	                mc.getSendQueue().addToSendQueue(new Packet11PlayerPosition(p.posX, p.boundingBox.minY + 8, p.posY + 8, p.posZ, false));
	                mc.getSendQueue().addToSendQueue(new Packet11PlayerPosition(p.posX, p.boundingBox.minY, p.posY, p.posZ, false));
	            }
	                mc.getSendQueue().addToSendQueue(new Packet10Flying(true));
	                return;
			}
        }
    	if(s.startsWith(".up"))
        {
            try{
                String nig[] = s.split(" ");
                String niga2 = nig[1];
                double derp = Double.parseDouble(niga2);

                for (int k1 = 1; k1 < derp; k1++){
                    double d = mc.thePlayer.posY + 8D;
                    for (int l2 = 0; l2 < 1; l2++){
                        mc.thePlayer.setLocationAndAngles(mc.thePlayer.posX, d, mc.thePlayer.posZ, mc.thePlayer.rotationYaw, mc.thePlayer.rotationPitch);
                        mc.getSendQueue().addToSendQueue(new Packet11PlayerPosition(mc.thePlayer.posX, d - 1.0D, d, mc.thePlayer.posZ, true));
                        mc.thePlayer.addChatMessage("§9[Vanity]§f Teleported " + niga2 + " blocks.");
                    }
                }
                return;
            }
            catch (Exception e){}
        }
        if(s.startsWith(".down"))
        {
            try{
                String nig[] = s.split(" ");
                String niga2 = nig[1];
                double derp = Double.parseDouble(niga2);

                for (int k1 = 1; k1 < derp; k1++){
                    double d = mc.thePlayer.posY + -8D;
                    for (int l2 = 0; l2 < 1; l2++){
                        mc.thePlayer.setLocationAndAngles(mc.thePlayer.posX, d, mc.thePlayer.posZ, mc.thePlayer.rotationYaw, mc.thePlayer.rotationPitch);
                        mc.getSendQueue().addToSendQueue(new Packet11PlayerPosition(mc.thePlayer.posX, d - 1.0D, d, mc.thePlayer.posZ, true));
                        mc.thePlayer.addChatMessage("§9[Vanity]§f Teleported " + niga2 + " blocks.");
                    }
                }
                return;
            }
            catch (Exception e){}
        }
        if(s.startsWith(".chestesp"))
        {
        	Vanity.chestesp = !Vanity.chestesp;
			mc.thePlayer.addChatMessage(Vanity.chestesp ? "§9[Vanity]§f Chest-ESP enabled." : "§9[Vanity]§f Chest-ESP disabled.");
        }
        if(s.startsWith(".fly"))
        {
        	Vanity.fly = !Vanity.fly;
			mc.thePlayer.addChatMessage(Vanity.fly ? "§9[Vanity]§f Fly enabled." : "§9[Vanity]§f Fly disabled.");
        }
        if(s.equals(".xray"))
        {
        	Vanity.xray = !Vanity.xray;
			mc.thePlayer.addChatMessage(Vanity.xray ? "§9[Vanity]§f Wallhack enabled." : "§9[Vanity]§f Wallhack disabled.");
        }
        if(s.startsWith(".sneak"))
        {
        	Vanity.sneak = !Vanity.sneak;
			mc.thePlayer.addChatMessage(Vanity.sneak ? "§9[Vanity]§f Sneak enabled." : "§9[Vanity]§f Sneak disabled.");
        }
        if(s.startsWith(".nofall"))
        {
        	Vanity.nofall = !Vanity.nofall;
			mc.thePlayer.addChatMessage(Vanity.nofall ? "§9[Vanity]§f NoFall enabled." : "§9[Vanity]§f NoFall disabled.");
        }
        if(s.startsWith(".killaura"))
        {
        	Vanity.killaura = !Vanity.killaura;
			mc.thePlayer.addChatMessage(Vanity.killaura ? "§9[Vanity]§f Kill Aura enabled." : "§9[Vanity]§f Kill Aura disabled.");
        }
        if(s.startsWith(".fullbright"))
        {
        	Vanity.bright = !Vanity.bright;
			mc.thePlayer.addChatMessage(Vanity.bright ? "§9[Vanity]§f Fullbright enabled." : "§9[Vanity]§f Fullbright disabled.");
        }
        if(s.startsWith(".mine"))
        {
        	Vanity.speedmine = !Vanity.speedmine;
			mc.thePlayer.addChatMessage(Vanity.speedmine ? "§9[Vanity]§f Speed Mine enabled." : "§9[Vanity]§f Speed Mine disabled.");
        }
        if(s.startsWith(".sprint"))
        {
        	Vanity.sprint = !Vanity.sprint;
			mc.thePlayer.addChatMessage(Vanity.sprint ? "§9[Vanity]§f Speed enabled." : "§9[Vanity]§f Speed disabled.");
        }
        if(s.startsWith(".jesus"))
        {
        	Vanity.jesus = !Vanity.jesus;
			mc.thePlayer.addChatMessage(Vanity.jesus ? "§9[Vanity]§f Jesus enabled." : "§9[Vanity]§f Jesus disabled.");
        }
        if(s.startsWith(".compass"))
        {
        	Vanity.compass = !Vanity.compass;
			mc.thePlayer.addChatMessage(Vanity.compass ? "§9[Vanity]§f Compass enabled." : "§9[Vanity]§f Compass disabled.");
        }
        if(s.startsWith(".tnuker"))
        {
        	Vanity.nukertorch = !Vanity.nukertorch;
        	mc.thePlayer.addChatMessage(Vanity.nukertorch ? "§9[Vanity]§f Torch Nuker enabled." : "§9[Vanity]§f Torch Nuker disabled.");
        }
        if(s.startsWith(".cnuker"))
        {
        	Vanity.nukercreative = !Vanity.nukercreative;
        	mc.thePlayer.addChatMessage(Vanity.nukercreative ? "§9[Vanity]§f Creative Nuker enabled." : "§9[Vanity]§f Creative Nuker disabled.");
        }
        if(s.startsWith(".snuker"))
        {
        	Vanity.nukersurvival = !Vanity.nukersurvival;
        	mc.thePlayer.addChatMessage(Vanity.nukersurvival ? "§9[Vanity]§f Survival Nuker enabled." : "§9[Vanity]§f Survival Nuker disabled.");
        }
        if(s.startsWith(".autotool"))
        {
        	Vanity.autotool = !Vanity.autotool;
        	mc.thePlayer.addChatMessage(Vanity.autotool ? "§9[Vanity]§f Auto Tool enabled." : "§9[Vanity]§f Auto Tool disabled.");
        }
        if(s.startsWith(".chat"))
        {
        	Vanity.ttfchat = !Vanity.ttfchat;
        	mc.thePlayer.addChatMessage(Vanity.ttfchat ? "§9[Vanity]§f TTF Chat enabled." : "§9[Vanity]§f TTF Chat disabled.");
        }
        if(s.startsWith(".nocheat"))
        {
        	Vanity.nocheat = !Vanity.nocheat;
        	mc.thePlayer.addChatMessage(Vanity.nocheat ? "§9[Vanity]§f NoCheat Mode enabled." : "§9[Vanity]§f NoCheat Mode disabled.");
        }
		if(s.startsWith(".protect"))
		{
			Vanity.protect = !Vanity.protect;
			mc.thePlayer.addChatMessage(Vanity.protect ? "§9[Vanity]§f Name Protect enabled." : "§9[Vanity]§f Name Protect disabled.");
		}	
		if(s.startsWith(".radar"))
		{
			Vanity.radar = !Vanity.radar;
			mc.thePlayer.addChatMessage(Vanity.radar ? "§9[Vanity]§f Radar enabled." : "§9[Vanity]§f Radar disabled.");
		}
		if(s.startsWith(".noswing"))
		{
			Vanity.noswing = !Vanity.noswing;
			mc.thePlayer.addChatMessage(Vanity.noswing ? "§9[Vanity]§f No-Swing enabled." : "§9[Vanity]§f No-Swing disabled.");
		}
		if(s.startsWith(".tracers"))
		{
			Vanity.tracer = !Vanity.tracer;
			mc.thePlayer.addChatMessage(Vanity.tracer ? "§9[Vanity]§f Tracers enabled." : "§9[Vanity]§f Tracers disabled.");
		}
		if(s.startsWith(".box"))
		{
			Vanity.espbox = !Vanity.espbox;
			mc.thePlayer.addChatMessage(Vanity.espbox ? "§9[Vanity]§f ESP Boxes enabled." : "§9[Vanity]§f ESP Boxes disabled.");
		}
		if(s.startsWith(".lockview"))
		{
			Vanity.killauralock = !Vanity.killauralock;
			mc.thePlayer.addChatMessage(Vanity.killauralock ? "§9[Vanity]§f Lock View enabled." : "§9[Vanity]§f Lock View disabled.");
		}
		if(s.startsWith(".gui"))
		{
        	mc.displayGuiScreen(new ReliantGui());
        	Vanity.gui = !Vanity.gui;
		}
		if(s.startsWith(".tracer player"))
		{
			Vanity.tracerplayer = !Vanity.tracerplayer;
			mc.thePlayer.addChatMessage(Vanity.tracerplayer ? "§9[Vanity]§f Player Tracers enabled." : "§9[Vanity]§f Player Tracers disabled.");
		}
		if(s.startsWith(".tracer mob"))
		{
			Vanity.tracermob = !Vanity.tracermob;
			mc.thePlayer.addChatMessage(Vanity.tracermob ? "§9[Vanity]§f Mob Tracers enabled." : "§9[Vanity]§f Mob Tracers disabled.");
		}
		if(s.startsWith(".tracer animal"))
		{
			Vanity.traceranimal = !Vanity.traceranimal;
			mc.thePlayer.addChatMessage(Vanity.traceranimal ? "§9[Vanity]§f Animal Tracers enabled." : "§9[Vanity]§f Animal Tracers disabled.");
		}
		if(s.startsWith(".killaura player"))
		{
			Vanity.killauraplayer = !Vanity.killauraplayer;
			mc.thePlayer.addChatMessage(Vanity.killauraplayer ? "§9[Vanity]§f Player Kill Aura enabled." : "§9[Vanity]§f Player Kill Aura disabled.");
		}
		if(s.startsWith(".killaura mob"))
		{
			Vanity.killauramob = !Vanity.killauramob;
			mc.thePlayer.addChatMessage(Vanity.killauramob ? "§9[Vanity]§f Mob Kill Aura enabled." : "§9[Vanity]§f Mob Kill Aura disabled.");
		}
		if(s.startsWith(".killaura animal"))
		{
			Vanity.killauraanimal = !Vanity.killauraanimal;
			mc.thePlayer.addChatMessage(Vanity.killauraanimal ? "§9[Vanity]§f Animal Kill Aura enabled." : "§9[Vanity]§f Animal Kill Aura disabled.");
		}
		if(s.startsWith(".bignames"))
		{
			Vanity.bignames = !Vanity.bignames;
			mc.thePlayer.addChatMessage(Vanity.bignames ? "§9[Vanity]§f Big Names enabled." : "§9[Vanity]§f Big Names disabled.");
		}
		if(s.startsWith(".nameplates"))
		{
			Vanity.nameplates = !Vanity.nameplates;
			mc.thePlayer.addChatMessage(Vanity.nameplates ? "§9[Vanity]§f Name Plates enabled." : "§9[Vanity]§f Name Plates disabled.");
		}
		if(s.startsWith(".noweather"))
		{
			Vanity.noweather = !Vanity.noweather;
			mc.thePlayer.addChatMessage(Vanity.noweather ? "§9[Vanity]§f No Weather enabled." : "§9[Vanity]§f No Weather disabled.");
		}
		if(s.startsWith(".step"))
		{
			Vanity.stepbool = !Vanity.stepbool;
			mc.thePlayer.addChatMessage(Vanity.stepbool ? "§9[Vanity]§f Step enabled." : "§9[Vanity]§f Step disabled.");
		}
		if(s.startsWith(".load"))
		{
			try {
				VanityFileWriter.readKeys();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try{
				VanityFileWriter.load();
			}catch (IOException e) {
				e.printStackTrace();
			}
		}
		if(s.startsWith(".bind add fly"))
		{
            String nig[] = s.split(" ");
            String niga2 = nig[3];
            String niga3 = niga2.toUpperCase();
			Keybinds.keybindFly = Keyboard.getKeyIndex(niga3);
			mc.thePlayer.addChatMessage("§9[Vanity]§f Fly bound to key " + nig[3] + ".");
			try {
				VanityFileWriter.writeKeys();
				VanityFileWriter.readKeys();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if(s.startsWith(".bind add xray"))
		{
            String nig[] = s.split(" ");
            String niga2 = nig[3];
            String niga3 = niga2.toUpperCase();
            Keybinds.keybindWallhack = Keyboard.getKeyIndex(niga3);
			mc.thePlayer.addChatMessage("§9[Vanity]§f Wallhack bound to key " + nig[3] + ".");
			try {
				VanityFileWriter.writeKeys();
				VanityFileWriter.readKeys();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if(s.startsWith(".bind add sneak"))
		{
            String nig[] = s.split(" ");
            String niga2 = nig[3];
            String niga3 = niga2.toUpperCase();
            Keybinds.keybindSneak = Keyboard.getKeyIndex(niga3);
			mc.thePlayer.addChatMessage("§9[Vanity]§f Sneak bound to key " + nig[3] + ".");
			try {
				VanityFileWriter.writeKeys();
				VanityFileWriter.readKeys();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if(s.startsWith(".bind add nofall"))
		{
            String nig[] = s.split(" ");
            String niga2 = nig[3];
            String niga3 = niga2.toUpperCase();
            Keybinds.keybindNofall = Keyboard.getKeyIndex(niga3);
			mc.thePlayer.addChatMessage("§9[Vanity]§f NoFall bound to key " + nig[3] + ".");
			try {
				VanityFileWriter.writeKeys();
				VanityFileWriter.readKeys();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if(s.startsWith(".bind add killaura"))
		{
            String nig[] = s.split(" ");
            String niga2 = nig[3];
            String niga3 = niga2.toUpperCase();
            Keybinds.keybindKillaura = Keyboard.getKeyIndex(niga3);
			mc.thePlayer.addChatMessage("§9[Vanity]§f Kill Aura bound to key " + nig[3] + ".");
			try {
				VanityFileWriter.writeKeys();
				VanityFileWriter.readKeys();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if(s.startsWith(".bind add fullbright"))
		{
            String nig[] = s.split(" ");
            String niga2 = nig[3];
            String niga3 = niga2.toUpperCase();
            Keybinds.keybindBright = Keyboard.getKeyIndex(niga3);
			mc.thePlayer.addChatMessage("§9[Vanity]§f Fullbright bound to key " + nig[3] + ".");
			try {
				VanityFileWriter.writeKeys();
				VanityFileWriter.readKeys();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if(s.startsWith(".bind add mine"))
		{
            String nig[] = s.split(" ");
            String niga2 = nig[3];
            String niga3 = niga2.toUpperCase();
            Keybinds.keybindSpeedmine = Keyboard.getKeyIndex(niga3);
			mc.thePlayer.addChatMessage("§9[Vanity]§f Speed Mine bound to key " + nig[3] + ".");
			try {
				VanityFileWriter.writeKeys();
				VanityFileWriter.readKeys();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if(s.startsWith(".bind add speed"))
		{
            String nig[] = s.split(" ");
            String niga2 = nig[3];
            String niga3 = niga2.toUpperCase();
            Keybinds.keybindSprint = Keyboard.getKeyIndex(niga3);
			mc.thePlayer.addChatMessage("§9[Vanity]§f Speed bound to key " + nig[3] + ".");
			try {
				VanityFileWriter.writeKeys();
				VanityFileWriter.readKeys();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if(s.startsWith(".bind add gui"))
		{
            String nig[] = s.split(" ");
            String niga2 = nig[3];
            String niga3 = niga2.toUpperCase();
            Keybinds.keybindGUI = Keyboard.getKeyIndex(niga3);
			mc.thePlayer.addChatMessage("§9[Vanity]§f GUI bound to key " + nig[3] + ".");
			try {
				VanityFileWriter.writeKeys();
				VanityFileWriter.readKeys();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	if(s.startsWith(".msg"))
	{
		try{
		String[] msg = s.split(":");
		String message = msg[1];
		mc.thePlayer.sendChatMessage(message);
		}catch(Exception e)
		{
			mc.thePlayer.addChatMessage("\2479[Vanity]\247f Proper format: .msg: -Message-");
		}
	}
	if(s.startsWith(".add"))
	{
		 String[] names = s.split(" ");
		 if(names.length <= 2) 
		 {
			 return;
		 }
		 Vanity.protectHashMap.put(names[1], names[2]);
		 Vanity.friends.add(names[1]);
		 Vanity.friends.add(names[2]);
		 mc.thePlayer.addChatMessage("§9[Vanity]§f " + names[1] + " Added.");
		 try {
			VanityFileWriter.save();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		if(s.startsWith(".friend"))
	    {
	     try
	     {
	      String as22[] = s.split(" ");
	      String s9 = as22[1];
	      String s14 = as22[2];
	      if(s9.equals("add"))
	      {
	       if(!Vanity.friends.contains(s14))
	       {
	    	Vanity.friends.add(s14);
	        
	        mc.thePlayer.addChatMessage("§9[Vanity]§f Friend " + s14 +" added.");
	       } else
	       {
	        mc.thePlayer.addChatMessage("§9[Vanity]§f Username is already in array list.");
	       }
	      }
	     } catch(Exception exception)
	     {
	      mc.thePlayer.addChatMessage("§9[Vanity]§f Correct: friend add <ALIAS>");
	     }
	    }
		if (s.startsWith(".step"))
        {
        	try{
        	String[] nig = s.split(" ");
        	String penis = nig[1];
        	float height = Float.parseFloat(penis);
        	Vanity.step = height;
        	mc.thePlayer.stepHeight = height;
        	mc.thePlayer.addChatMessage("§9[Vanity]§f Step height set to " + height);
        	}catch(Exception e){}
        }
		if (s.startsWith(".speed"))
        {
        	try{
        	String[] nig = s.split(" ");
        	String penis = nig[1];
        	float speed = Float.parseFloat(penis);
        	Vanity.speed = speed;
        	mc.thePlayer.addChatMessage("§9[Vanity]§f Speed set to " + speed);
        	}catch(Exception e){Vanity.speed = 1.2F;}
        }
		if (s.startsWith(".opacity"))
        {
        	try{
        	String[] nig = s.split(" ");
        	String penis = nig[1];
        	float opac = Float.parseFloat(penis);
        	if(opac > 255)
        	{
        		opac = 255F;
        	}
        	else if(opac < 26)
        	{
        		opac = 26F;
        	}
        	Vanity.opacity = opac;
        	mc.renderGlobal.loadRenderers();
        	mc.thePlayer.addChatMessage("§9[Vanity]§f Wallhack opacity set to " + opac);
        	}catch(Exception e){ Vanity.opacity = 111F; mc.renderGlobal.loadRenderers();}
        }
		if (s.startsWith(".del"))
        {
            String[] names = s.split(" ");
            Vanity.protectHashMap.remove(names[1]);
            Vanity.friends.remove(names[1]);
            mc.thePlayer.addChatMessage("§9[Vanity]§f " + names[1] + " Deleted.");
            try{
                VanityFileWriter.save();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}
