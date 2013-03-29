package net.minecraft.vanity.gui;

import java.util.List;
import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;

import net.minecraft.vanity.*;
import net.minecraft.vanity.main.Vanity;
import net.minecraft.client.*;

public class WindowRetard extends ReliantGuiWindow
{
    private static Minecraft mc;

    public WindowRetard(int i, int j)
    {
        super(i, j, "Retard Mode");
    }

    public void drawPanel(TrueTypeFont truetypefont)
    {
        controlList.clear();
        super.drawPanel(truetypefont);
        int i = -7;
        int j = posY + 5;
        ReliantArt.drawString(truetypefont, "Retard",posX + 3, j += 12, Vanity.derp ? Color.green : Color.red);
        ReliantArt.drawString(truetypefont, "HeadInBody",posX + 3, j += 12, Vanity.retardheadinbody ? Color.green : Color.red);
        //ReliantArt.drawString(truetypefont, "HeadInBodyRotating",posX + 3, j += 12, Vanity.retardheadinbodyrotating ? Color.green : Color.red);
        ReliantArt.drawString(truetypefont, "Stare",posX + 3, j += 12, Vanity.retardstare ? Color.green : Color.red);
        ReliantArt.drawString(truetypefont, "Rev-Stare",posX + 3, j += 12, Vanity.retardstarereverse ? Color.green : Color.red);
        ReliantArt.drawString(truetypefont, "HeadBang",posX + 3, j += 12, Vanity.retardheadbang ? Color.green : Color.red);
        ReliantArt.drawString(truetypefont, "Spin",posX + 3, j += 12, Vanity.retardspin ? Color.green : Color.red);
        ReliantArt.drawString(truetypefont, "Hump",posX + 3, j += 12, Vanity.retardhump ? Color.green : Color.red);
        ReliantArt.drawString(truetypefont, "Nodding Yes",posX + 3, j += 12, Vanity.retardyes ? Color.green : Color.red);
        ReliantArt.drawString(truetypefont, "Nodding No",posX + 3, j += 12, Vanity.retardno ? Color.green : Color.red);
        controlList.add(new ReliantButton(0, getWidth() - 39, i += 12, Vanity.derp));
        controlList.add(new ReliantButton(1, getWidth() - 39, i += 12, Vanity.retardheadinbody));
        //controlList.add(new ReliantButton(2, getWidth() - 39, i += 12, Vanity.retardheadinbodyrotating));
        controlList.add(new ReliantButton(3, getWidth() - 39, i += 12, Vanity.retardstare));
        controlList.add(new ReliantButton(4, getWidth() - 39, i += 12, Vanity.retardstarereverse));
        controlList.add(new ReliantButton(5, getWidth() - 39, i += 12, Vanity.retardheadbang));
        controlList.add(new ReliantButton(6, getWidth() - 39, i += 12, Vanity.retardspin));
        controlList.add(new ReliantButton(7, getWidth() - 39, i += 12, Vanity.retardhump));
        controlList.add(new ReliantButton(8, getWidth() - 39, i += 12, Vanity.retardyes));
        controlList.add(new ReliantButton(9, getWidth() - 39, i += 12, Vanity.retardno));
    }

    public void actionPerformed(ReliantButton reliantbutton)
    {
        if (reliantbutton.id == 0)
        {
            Vanity.derp = !Vanity.derp;
            //mc.sndManager.playSoundFX("random.click", 1.0F, 1.0F);
        }
        
        if (reliantbutton.id == 1)
        {
            Vanity.retardheadinbody = !Vanity.retardheadinbody;
            //mc.sndManager.playSoundFX("random.click", 1.0F, 1.0F);
        }
        
        if (reliantbutton.id == 2)
        {
            Vanity.retardheadinbodyrotating = !Vanity.retardheadinbodyrotating;
            //mc.sndManager.playSoundFX("random.click", 1.0F, 1.0F);
        }

        if (reliantbutton.id == 3)
        {
            Vanity.retardstare = !Vanity.retardstare;
            //mc.sndManager.playSoundFX("random.click", 1.0F, 1.0F);
        }

        if (reliantbutton.id == 4)
        {
            Vanity.retardstarereverse = !Vanity.retardstarereverse;
            //mc.sndManager.playSoundFX("random.click", 1.0F, 1.0F);
        }

        if (reliantbutton.id == 5)
        {
            Vanity.retardheadbang = !Vanity.retardheadbang;
            //mc.sndManager.playSoundFX("random.click", 1.0F, 1.0F);
        }
        
        if (reliantbutton.id == 6)
        {
            Vanity.retardspin = !Vanity.retardspin;
            //mc.sndManager.playSoundFX("random.click", 1.0F, 1.0F);
        }
        
        if (reliantbutton.id == 7)
        {
            Vanity.retardhump = !Vanity.retardhump;
            //mc.sndManager.playSoundFX("random.click", 1.0F, 1.0F);
        }

        if (reliantbutton.id == 8)
        {
            Vanity.retardyes = !Vanity.retardyes;
            //mc.sndManager.playSoundFX("random.click", 1.0F, 1.0F);
        }

        if (reliantbutton.id == 9)
        {
            Vanity.retardno = !Vanity.retardno;
            //mc.sndManager.playSoundFX("random.click", 1.0F, 1.0F);
        }
    }

    public int getWidth()
    {
        return 100;
    }

    public int getHeight()
    {
        return 123;
    }
}
