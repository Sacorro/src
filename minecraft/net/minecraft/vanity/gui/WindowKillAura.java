package net.minecraft.vanity.gui;

import java.util.List;
import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;

import net.minecraft.vanity.*;
import net.minecraft.vanity.main.Vanity;
import net.minecraft.client.*;

public class WindowKillAura extends ReliantGuiWindow
{
    private static Minecraft mc;

    public WindowKillAura(int i, int j)
    {
        super(i, j, "Kill Aura");
    }

    public void drawPanel(TrueTypeFont truetypefont)
    {
        controlList.clear();
        super.drawPanel(truetypefont);
        int i = -7;
        int j = posY + 5;
        ReliantArt.drawString(truetypefont, "Players",posX + 3, j += 12, Vanity.killauraplayer ? Color.green : Color.red);
        ReliantArt.drawString(truetypefont, "Animals",posX + 3, j += 12, Vanity.killauraanimal ? Color.green : Color.red);
        ReliantArt.drawString(truetypefont, "Mobs",posX + 3, j += 12, Vanity.killauramob ? Color.green : Color.red);
        
        ReliantArt.drawString(truetypefont, "Knockback",posX + 3, j += 12, Vanity.knockback ? Color.green : Color.red);
        
        ReliantArt.drawString(truetypefont, "Anti-Knockback",posX + 3, j += 12, Vanity.antiknockback ? Color.green : Color.red);
        
        controlList.add(new ReliantButton(4, getWidth() - 39, i += 12, Vanity.killauraplayer));
        controlList.add(new ReliantButton(6, getWidth() - 39, i += 12, Vanity.killauraanimal));
        controlList.add(new ReliantButton(5, getWidth() - 39, i += 12, Vanity.killauramob));
        
        controlList.add(new ReliantButton(7, getWidth() - 39, i += 12, Vanity.knockback));
        controlList.add(new ReliantButton(8, getWidth() - 39, i += 12, Vanity.antiknockback));
    }

    public void actionPerformed(ReliantButton reliantbutton)
    {
        if (reliantbutton.id == 4)
        {
            Vanity.killauraplayer = !Vanity.killauraplayer;
            //mc.sndManager.playSoundFX("random.click", 1.0F, 1.0F);
        }

        if (reliantbutton.id == 5)
        {
            Vanity.killauramob = !Vanity.killauramob;
            //mc.sndManager.playSoundFX("random.click", 1.0F, 1.0F);
        }

        if (reliantbutton.id == 6)
        {
            Vanity.killauraanimal = !Vanity.killauraanimal;
            //mc.sndManager.playSoundFX("random.click", 1.0F, 1.0F);
        }
        
        if (reliantbutton.id == 7)
        {
            Vanity.knockback = !Vanity.knockback;
            //mc.sndManager.playSoundFX("random.click", 1.0F, 1.0F);
        }
        
        if (reliantbutton.id == 8)
        {
            Vanity.antiknockback = !Vanity.antiknockback;
            //mc.sndManager.playSoundFX("random.click", 1.0F, 1.0F);
        }
    }

    public int getWidth()
    {
        return 115;
    }

    public int getHeight()
    {
        return 75;
    }
}
