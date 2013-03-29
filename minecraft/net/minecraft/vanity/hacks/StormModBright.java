package net.minecraft.vanity.hacks;

import java.util.List;

import net.minecraft.vanity.main.Vanity;
import net.minecraft.vanity.main.Wrapper;

public class StormModBright {
	private static float currentBright = 0F;
	private static boolean fading = false;
	public boolean on = false;
	public static StormModBright inst = new StormModBright();
	public float getBright()
	{
		if(Vanity.bright || fading)
		{
			return currentBright;
		}
		return Wrapper.minecraft.gameSettings.gammaSetting;
	}
	public void runFade(boolean b)
	{
		if(b)
			(new FadeInBright(Wrapper.minecraft.theWorld.worldProvider.lightBrightnessTable[0], Vanity.brightness)).start();
		else
			(new FadeInBright(currentBright, Wrapper.minecraft.theWorld.worldProvider.lightBrightnessTable[0])).start();
	}
	public class FadeInBright extends Thread
	{
		public FadeInBright(float s, float f)
		{
			brightStart = s;
			brightEnd = f;
		}
		public void run(){
			fading = true;
			short fadeAt = 45, time = 1000, times = (short)(fadeAt * time / 1000);
			long wait = 1000L / fadeAt;
			currentBright = brightStart;
			float fVal = Vanity.brightness; //CHANGE THIS FUCK
			fVal = brightEnd;
			float step = (brightEnd - currentBright) / times;
			for(int r = 1; r < times; r++)
			{
				currentBright+=step;
				try
				{
					Thread.sleep(wait);}catch(Exception e){}
			}
			fading = false;
		}
		public float brightStart, brightEnd;
	}
}
