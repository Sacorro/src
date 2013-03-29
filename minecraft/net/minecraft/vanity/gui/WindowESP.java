package net.minecraft.vanity.gui;

import java.util.List;
import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;
import net.minecraft.vanity.*;
import net.minecraft.vanity.main.Vanity;
import net.minecraft.client.*;

public class WindowESP extends ReliantGuiWindow
{
    private static Minecraft mc;

    public WindowESP(int i, int j)
    {
        super(i, j, "ESP Options");
    }

    public void drawPanel(TrueTypeFont truetypefont)
    {
        controlList.clear();
        super.drawPanel(truetypefont);
        int i = -7;
        int j = posY + 5;
        ReliantArt.drawString(truetypefont, "Players", posX + 3, j += 12, Vanity.tracerplayer ? Color.green : Color.red);
        ReliantArt.drawString(truetypefont, "Mobs", posX + 3, j += 12, Vanity.tracermob ? Color.green : Color.red);
        ReliantArt.drawString(truetypefont, "Animals", posX + 3, j += 12, Vanity.traceranimal ? Color.green : Color.red);
        ReliantArt.drawString(truetypefont, "Nameplates",posX + 3, j += 12, Vanity.nameplates ? Color.green : Color.red);
        ReliantArt.drawString(truetypefont, "Chests",posX + 3, j += 12, Vanity.chestesp ? Color.green : Color.red);
        ReliantArt.drawString(truetypefont, "Radar",posX + 3, j += 12, Vanity.radar ? Color.green : Color.red);
        controlList.add(new ReliantButton(1, getWidth() - 39, i += 12, Vanity.tracerplayer));
        controlList.add(new ReliantButton(2, getWidth() - 39, i += 12, Vanity.tracermob));
        controlList.add(new ReliantButton(3, getWidth() - 39, i += 12, Vanity.traceranimal));
        controlList.add(new ReliantButton(7, getWidth() - 39, i += 12, Vanity.nameplates));
        controlList.add(new ReliantButton(8, getWidth() - 39, i += 12, Vanity.chestesp));
        controlList.add(new ReliantButton(9, getWidth() - 39, i += 12, Vanity.radar));
    }

    public void actionPerformed(ReliantButton reliantbutton)
    {
        if (reliantbutton.id == 1)
        {
            Vanity.tracerplayer = !Vanity.tracerplayer;
            //mc.sndManager.playSoundFX("random.click", 1.0F, 1.0F);
        }

        if (reliantbutton.id == 2)
        {
            Vanity.tracermob = !Vanity.tracermob;
            //mc.sndManager.playSoundFX("random.click", 1.0F, 1.0F);
        }

        if (reliantbutton.id == 3)
        {
            Vanity.traceranimal = !Vanity.traceranimal;
            //mc.sndManager.playSoundFX("random.click", 1.0F, 1.0F);
        }

        if (reliantbutton.id == 7)
        {
            Vanity.nameplates = !Vanity.nameplates;
            //mc.sndManager.playSoundFX("random.click", 1.0F, 1.0F);
        }

        if (reliantbutton.id == 8)
        {
            Vanity.chestesp = !Vanity.chestesp;
            //mc.sndManager.playSoundFX("random.click", 1.0F, 1.0F);
        }

        if (reliantbutton.id == 9)
        {
            Vanity.radar = !Vanity.radar;
          // mc.sndManager.playSoundFX("random.click", 1.0F, 1.0F);
        }
    }

    public int getWidth()
    {
        return 100;
    }

    public int getHeight()
    {
        return 87;
    }
}
