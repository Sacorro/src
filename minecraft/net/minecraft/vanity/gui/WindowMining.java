package net.minecraft.vanity.gui;

import java.util.List;
import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;
import net.minecraft.vanity.*;
import net.minecraft.vanity.main.Vanity;
import net.minecraft.client.*;

public class WindowMining extends ReliantGuiWindow
{
    private static Minecraft mc;

    public WindowMining(int i, int j)
    {
        super(i, j, "Nukers");
    }

    public void drawPanel(TrueTypeFont truetypefont)
    {
        controlList.clear();
        super.drawPanel(truetypefont);
        int i = -9;
        int j = posY + 3;
        ReliantArt.drawString(truetypefont, "Creative Nuker",posX + 3, j += 12, Vanity.nukercreative ? Color.green : Color.red);
        ReliantArt.drawString(truetypefont, "Survival Nuker",posX + 3, j += 12, Vanity.nukersurvival ? Color.green : Color.red);
        ReliantArt.drawString(truetypefont, "Torch Nuker",posX + 3, j += 12, Vanity.nukertorch ? Color.green : Color.red);
        controlList.add(new ReliantButton(3, getWidth() - 42, i += 12, Vanity.nukercreative));
        controlList.add(new ReliantButton(4, getWidth() - 42, i += 12, Vanity.nukersurvival));
        controlList.add(new ReliantButton(5, getWidth() - 42, i += 12, Vanity.nukertorch));
    }

    public void actionPerformed(ReliantButton reliantbutton)
    {
        if (reliantbutton.id == 3)
        {
            Vanity.nukercreative = !Vanity.nukercreative;
            Vanity.nukersurvival = false;
            Vanity.nukertorch = false;
        }

        if (reliantbutton.id == 4)
        {
            Vanity.nukersurvival = !Vanity.nukersurvival;
            Vanity.nukercreative = false;
            Vanity.nukertorch = false;
        }

        if (reliantbutton.id == 5)
        {
            Vanity.nukertorch = !Vanity.nukertorch;
            Vanity.nukercreative = false;
            Vanity.nukersurvival = false;
        }
    }

    public int getWidth()
    {
        return 124;
    }

    public int getHeight()
    {
        return 48;
    }
}
