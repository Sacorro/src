package net.minecraft.vanity.gui;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.Color;

import net.minecraft.client.Minecraft;
import net.minecraft.src.GuiScreen;
import net.minecraft.vanity.main.Vanity;
import net.minecraft.vanity.main.VanityFileWriter;

public class ReliantGui extends GuiScreen
{
    public static ArrayList windows;

    public ReliantGui()
    {
    }

    /**
     * Draws the screen and all the components in it.
     */
    public void drawScreen(int i, int j, float f)
    {
        drawDefaultBackground();

        for (int k = windows.size(); k > 0; k--)
        {
            if (((ReliantGuiWindow) windows.get(k - 1)).isOpen())
            {
                ((ReliantGuiWindow)windows.get(k - 1)).drawScreen(i, j, f);
            }
        }
        ReliantArt.drawRect(2, 2, 200, 200, 0xffffff);
        super.drawScreen(i, j, f);
    }

    /**
     * Called when the mouse is clicked.
     */
    protected void mouseClicked(int i, int j, int k)
    {
        label0:
        {
            super.mouseClicked(i, j, k);

            if (k != 0)
            {
                break label0;
            }

            Iterator iterator = windows.iterator();
            ReliantGuiWindow reliantguiwindow;

            do
            {
                if (!iterator.hasNext())
                {
                    break label0;
                }

                reliantguiwindow = (ReliantGuiWindow)iterator.next();
            }
            while (!reliantguiwindow.mouseClick(i, j, k));

            windows.remove(reliantguiwindow);
            windows.add(0, reliantguiwindow);
        }
    }

    /**
     * Called from the main game loop to update the screen.
     */
    public void updateScreen()
    {
        ReliantGuiWindow reliantguiwindow;

        for (Iterator iterator = windows.iterator(); iterator.hasNext(); reliantguiwindow.updateScreen())
        {
            reliantguiwindow = (ReliantGuiWindow)iterator.next();
        }
    }

    /**
     * Called when the mouse is moved or a mouse button is released.  Signature: (mouseX, mouseY, which) which==-1 is
     * mouseMove, which==0 or which==1 is mouseUp
     */
    public void initGui()
    {
    }

    public void mouseMovedOrUp(int i, int j, int k)
    {
        super.mouseMovedOrUp(i, j, k);
        ReliantGuiWindow reliantguiwindow;

        for (Iterator iterator = windows.iterator(); iterator.hasNext(); reliantguiwindow.mouseMovedOrUp(i, j, k))
        {
            reliantguiwindow = (ReliantGuiWindow)iterator.next();
        }
    }

    /**
     * Fired when a key is typed. This is the equivalent of KeyListener.keyTyped(KeyEvent e).
     */
    protected void keyTyped(char c, int i)
    {
        ReliantGuiWindow reliantguiwindow;

        for (Iterator iterator = windows.iterator(); iterator.hasNext(); reliantguiwindow.keyTyped(c, i))
        {
            reliantguiwindow = (ReliantGuiWindow)iterator.next();
        }

        if (i == 1)
        {
            mc.displayGuiScreen(null);
        }
    }

    static
    {
        windows = new ArrayList();
        windows.add(new WindowModes(GuiLocations.modesx, GuiLocations.modesy));
        windows.add(new WindowWallhack(GuiLocations.wallhackx, GuiLocations.wallhacky));
        windows.add(new WindowKillAura(GuiLocations.killaurax, GuiLocations.killauray));
        windows.add(new WindowESP(GuiLocations.espx, GuiLocations.espy));
        windows.add(new WindowValues(209, 13));
        windows.add(new WindowRetard(200, 28));
    }
}
