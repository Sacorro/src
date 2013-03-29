package net.minecraft.vanity.gui;

import java.util.List;
import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;

import net.minecraft.vanity.*;
import net.minecraft.vanity.main.Vanity;
import net.minecraft.client.*;

public class WindowModes extends ReliantGuiWindow
{
    private static Minecraft mc;

    public WindowModes(int i, int j)
    {
        super(i, j, "Modes");
    }

    public void drawPanel(TrueTypeFont truetypefont)
    {
        controlList.clear();
        super.drawPanel(truetypefont);
        int i = -7;
        int j = posY + 5;
        // jesus, chat, nameprotect, nocheat, autoblock, noweather, autotool, nofall, glide
        ReliantArt.drawString(truetypefont, "Jesus Mode",posX + 3, j += 12, Vanity.jesus ? Color.green : Color.red);
        ReliantArt.drawString(truetypefont, "ReliantChat",posX + 3, j += 12, Vanity.ttfchat ? Color.green : Color.red);
        ReliantArt.drawString(truetypefont, "Fly",posX + 3, j += 12, Vanity.fly ? Color.green : Color.red);
        ReliantArt.drawString(truetypefont, "Speed Hack",posX + 3, j += 12, Vanity.sprint ? Color.green : Color.red);
        ReliantArt.drawString(truetypefont, "NameProtect",posX + 3, j += 12, Vanity.protect ? Color.green : Color.red);
        ReliantArt.drawString(truetypefont, "NoCheat",posX + 3, j += 12, Vanity.nocheat ? Color.green : Color.red);
        ReliantArt.drawString(truetypefont, "NoWeather",posX + 3, j += 12, Vanity.noweather ? Color.green : Color.red);
        ReliantArt.drawString(truetypefont, "Sneak",posX + 3, j += 12, Vanity.sneak ? Color.green : Color.red);
        ReliantArt.drawString(truetypefont, "Auto Tool",posX + 3, j += 12, Vanity.autotool ? Color.green : Color.red);
        ReliantArt.drawString(truetypefont, "NoFall",posX + 3, j += 12, Vanity.nofall ? Color.green : Color.red);
       
//        ReliantArt.drawString(truetypefont, "Glide",posX + 3, j += 12, Vanity.glide ? Color.green : Color.red);
        
        controlList.add(new ReliantButton(0, getWidth() - 39, i += 12, Vanity.jesus)); //
        controlList.add(new ReliantButton(1, getWidth() - 39, i += 12, Vanity.ttfchat));
        controlList.add(new ReliantButton(2, getWidth() - 39, i += 12, Vanity.fly));
        controlList.add(new ReliantButton(3, getWidth() - 39, i += 12, Vanity.sprint));
        controlList.add(new ReliantButton(4, getWidth() - 39, i += 12, Vanity.protect));
        controlList.add(new ReliantButton(5, getWidth() - 39, i += 12, Vanity.nocheat));
        controlList.add(new ReliantButton(6, getWidth() - 39, i += 12, Vanity.noweather));
        controlList.add(new ReliantButton(7, getWidth() - 39, i += 12, Vanity.sneak));
        controlList.add(new ReliantButton(8, getWidth() - 39, i += 12, Vanity.autotool));
        controlList.add(new ReliantButton(9, getWidth() - 39, i += 12, Vanity.nofall));
        
//        controlList.add(new ReliantButton(10, getWidth() - 39, i += 12, Vanity.glide));
    }

    public void actionPerformed(ReliantButton reliantbutton)
    {
        if (reliantbutton.id == 0)
        {
            Vanity.jesus = !Vanity.jesus;
            //mc.sndManager.playSoundFX("random.click", 1.0F, 1.0F);
        }

        if (reliantbutton.id == 1)
        {
            Vanity.ttfchat = !Vanity.ttfchat;
            //mc.sndManager.playSoundFX("random.click", 1.0F, 1.0F);
        }
        if(reliantbutton.id == 2)
        {
        	Vanity.fly = !Vanity.fly;
        	//mc.sndManager.playSoundFX("random.click", 1.0F, 1.0F);
        }
        if(reliantbutton.id == 3)
        {
        	Vanity.sprint = !Vanity.sprint;
        	//mc.sndManager.playSoundFX("random.click", 1.0F, 1.0F);
        }
        if (reliantbutton.id == 4)
        {
            Vanity.protect = !Vanity.protect;
            //mc.sndManager.playSoundFX("random.click", 1.0F, 1.0F);
        }
        if (reliantbutton.id == 5)
        {
            Vanity.nocheat = !Vanity.nocheat;
            //mc.sndManager.playSoundFX("random.click", 1.0F, 1.0F);
        }
        if (reliantbutton.id == 5)
        {
            Vanity.autoblock = !Vanity.autoblock;
            //mc.sndManager.playSoundFX("random.click", 1.0F, 1.0F);
        }
        if (reliantbutton.id == 6)
        {
            Vanity.noweather = !Vanity.noweather;
            //mc.sndManager.playSoundFX("random.click", 1.0F, 1.0F);
        }
        if (reliantbutton.id == 7)
        {
            Vanity.sneak = !Vanity.sneak;
            //mc.sndManager.playSoundFX("random.click", 1.0F, 1.0F);
        }
        if (reliantbutton.id == 8)
        {
            Vanity.autotool = !Vanity.autotool;
            //mc.sndManager.playSoundFX("random.click", 1.0F, 1.0F);
        }
        if (reliantbutton.id == 9)
        {
            Vanity.nofall = !Vanity.nofall;
            //mc.sndManager.playSoundFX("random.click", 1.0F, 1.0F);
        }
        
        if (reliantbutton.id == 10)
        {
            Vanity.glide = !Vanity.glide;
            //mc.sndManager.playSoundFX("random.click", 1.0F, 1.0F);
        }
    }

    public int getWidth()
    {
        return 100;
    }

    public int getHeight()
    {
        return 134;
    }
}
