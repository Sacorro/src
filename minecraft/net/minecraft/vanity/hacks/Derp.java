package net.minecraft.vanity.hacks;

import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.src.Packet10Flying;
import net.minecraft.src.Packet12PlayerLook;
import net.minecraft.src.Packet18Animation;
import net.minecraft.vanity.main.Vanity;

public class Derp
{
    public static Minecraft Minecraft;
	Minecraft mc;
    public static void HandleDerp()
    {
    	if(Vanity.derp)
    	{
	        Random random = new Random();
	        Random random1 = new Random();
	        Minecraft.getSendQueue().addToSendQueue(new Packet12PlayerLook(random.nextInt(360) + 1, random.nextInt(180) + 1, Packet10Flying.onGround));
	        Minecraft.getSendQueue().addToSendQueue(new Packet18Animation(Minecraft.thePlayer, 1));
    	}
    }
}
