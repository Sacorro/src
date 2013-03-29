package net.minecraft.vanity.gui.array;

import java.util.Iterator;

import net.minecraft.client.Minecraft;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.FontRenderer;
import net.minecraft.src.Gui;
import net.minecraft.src.MathHelper;
import net.minecraft.src.ScaledResolution;
import net.minecraft.vanity.gui.colors.Array;
import net.minecraft.vanity.gui.renderer.Gui2;
import net.minecraft.vanity.main.Vanity;

public class GUI
{
	static Minecraft mc;
public GUI()
{
}
FontRenderer fontrenderer = mc.fontRenderer;
public static void renderGui(float f,FontRenderer fontrenderer)
{
	ScaledResolution scaledresolution = new ScaledResolution(Minecraft.gameSettings,Minecraft.displayWidth,Minecraft.displayHeight);
	Gui2 gui2;
 	int line = 10;
 	int line2 = 2;
	int width=scaledresolution.getScaledWidth();
	int height=scaledresolution.getScaledHeight();
	fontrenderer.drawStringWithShadow(Vanity.vanityversion, 2, 2, 0xffffff);
	if(Vanity.array)
	{
	     	if(Vanity.invisibility)
	     	{
	     		fontrenderer.drawStringWithShadow("Invisible", scaledresolution.getScaledWidth() - fontrenderer.getStringWidth("Invisible") - 5, line2, Array.colinvisiblity);line2 += line;
	     	}
	     	if(Vanity.bright)
	     	{
	     		fontrenderer.drawStringWithShadow("Fullbright", scaledresolution.getScaledWidth() - fontrenderer.getStringWidth("Fullbright") - 5, line2, Array.colfullbright);line2 += line;
	     	}
	     	if(Vanity.killaura)
	     	{
	     		fontrenderer.drawStringWithShadow("Kill Aura", scaledresolution.getScaledWidth() - fontrenderer.getStringWidth("Kill Aura") - 5, line2, Array.colkillaura);line2 += line;
	     	}
	     	if(Vanity.fly)
	     	{
	     		fontrenderer.drawStringWithShadow("Flight", scaledresolution.getScaledWidth() - fontrenderer.getStringWidth("Flight") - 5, line2, Array.colfly);line2 += line;
	     	}
	     	if(Vanity.sprint && !Vanity.fly || Vanity.speed > 1.15f)
	     	{
	     		fontrenderer.drawStringWithShadow("Sprint (Speed)", scaledresolution.getScaledWidth() - fontrenderer.getStringWidth("Sprint (Speed)") - 5, line2, Array.colspeed);line2 += line;
	     	}
	     	if(Vanity.sneak)
	     	{
	     		fontrenderer.drawStringWithShadow("Sneak", scaledresolution.getScaledWidth() - fontrenderer.getStringWidth("Sneak") - 5, line2, Array.colsneak);line2 += line;
	     	}
	     	if(Vanity.nofall)
	     	{
	     		fontrenderer.drawStringWithShadow("NoFall", scaledresolution.getScaledWidth() - fontrenderer.getStringWidth("NoFall") - 5, line2, Array.colnofall);line2 += line;
	     	}
	     	if(Vanity.xray)
	     	{
	     		fontrenderer.drawStringWithShadow("Wallhack", scaledresolution.getScaledWidth() - fontrenderer.getStringWidth("Wallhack") - 5, line2, Array.colwallhack);line2 += line;
	     	}
	     	if(Vanity.speedmine)
	     	{
	     		fontrenderer.drawStringWithShadow("Speedy Gonzales", scaledresolution.getScaledWidth() - fontrenderer.getStringWidth("Speedy Gonzales") - 5, line2, Array.colspeedmine);line2 += line;
	     	}
	}
	}
}
