package net.minecraft.vanity.gui;

import java.awt.Font;
import java.util.ArrayList;
import net.minecraft.client.Minecraft;
import net.minecraft.src.Gui;
import net.minecraft.src.GuiButton;
import net.minecraft.src.GuiScreen;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;

public abstract class ReliantGuiWindow extends GuiScreen
{
    public String title;
    public boolean enabled;
    public boolean open;
    public boolean focused;
    public boolean dragging;
    public int posX;
    public int posY;
    public int pointX;
    public int pointY;
    protected java.util.List<ReliantButton> controlList;
    protected java.util.List<GuiCustomSlider> controlList2;
    private ReliantButton selectedButton;
    public TrueTypeFont font3;
    public TrueTypeFont font1;
    public static TrueTypeFont font4 = new TrueTypeFont(new Font("Lucida Console", Font.TRUETYPE_FONT, 18), true); // - and +
    public static TrueTypeFont font2 = new TrueTypeFont(new Font("Lucida Console", Font.PLAIN, 19), true); // title
    public static TrueTypeFont font5 = new TrueTypeFont(new Font("Lucida Sans Typewriter", Font.PLAIN, 16), true); // buttons
    private Minecraft mc;

    public ReliantGuiWindow(int i, int j, String s)
    {
        font3 = new TrueTypeFont(new Font("Lucida Console", Font.PLAIN, 20), true);
        font1 = new TrueTypeFont(new Font("Lucida Sans Typewriter", Font.PLAIN, 16), true);// hacks
        posX = i;
        posY = j;
        title = s;
        controlList = new ArrayList<ReliantButton>();
        controlList2 = new ArrayList<GuiCustomSlider>();
        open = true;
    }

    public static void start()
    {
    }

    public void drawScreen(int i, int j, float f)
    {
        if (open)
        {
            draw(font2);

            if (enabled)
            {
                for (int k = 0; k < controlList.size(); k++)
                {
                    ReliantButton reliantbutton = controlList.get(k);
                    reliantbutton.drawButton(mc, posX + i, posY + j, posX, posY + 12);
                }
                for (int l = 0; l < controlList2.size(); l++){
                	GuiCustomSlider slider = controlList2.get(l);
                	slider.mouseDragged(mc, i, j);
                }
            }
        }
    }

    public void keyTyped(char c, int i)
    {
    }

    public void draw(TrueTypeFont ttf)
    {
//        GL11.glTranslatef(posX, posY, 0.0F);
        drawHeader(ttf);

        if (enabled)
        {
//            GL11.glTranslatef(0.0F, 15F, 0.0F);
            drawPanel(font1);
//            GL11.glTranslatef(0.0F, -15F, 0.0F);
        }

//        GL11.glTranslatef(-posX, -posY, 0.0F);
    }

    public void drawHeader(TrueTypeFont truetypefont)
    {
        ReliantArt.drawString(font3, "", posX + 1, posY + 1, Color.white);
        ReliantArt.drawBRect(posX, posY, posX + getWidth(), posY + 13, 0xbb363636, 0xff5e5e5e);
        ReliantArt.drawString(truetypefont, title, posX + 3, posY + 2, Color.white);
        ReliantArt.drawBRect(posX + getWidth() - 11, posY + 2, posX + getWidth() - 2, posY + 11, 0xbb585858, 0xff5e5e5e);
        ReliantArt.drawButtonString(font3, enabled ? "-" : "+", (float)posX + getWidth() - 9.65F, posY + 1.25F, Color.white);
    }

    public void drawPanel(TrueTypeFont truetypefont)
    {
        ReliantArt.drawBRect(posX, posY + 15, posX + getWidth(), posY + 15 + getHeight() - 12, 0xcc363636, 0xff5e5e5e);
    }

    public boolean mouseClick(int i, int j, int k)
    {
        if (checkBounds(posX + i, posY + j, posX + getWidth() - 12, posY + 1, posX + getWidth() - 2, posY + 10))
        {
            enabled = !enabled;
            return true;
        }

        if (checkBounds(posX + i, posY + j, posX, posY, posX + getWidth(), posY + 11))
        {
            dragging = true;
            pointX = i;
            pointY = j;
        }

        for (int l = 0; l < controlList.size(); l++)
        {
            ReliantButton reliantbutton = (ReliantButton)controlList.get(l);

            if (reliantbutton.mousePressed(mc, i, j, posX, posY + 12))
            {
                selectedButton = reliantbutton;
                actionPerformed(reliantbutton);
            }
        }

        buttonClick(posX + i, posY + j, k);
        return checkBounds(posX + i, posY + j, posX, posY, posX + getWidth(), enabled ? posY + getHeight() : posY + 11);
    }

    public void updateScreen()
    {
    }

    public void buttonClick(int i, int j, int k)
    {
    }

    public void mouseMovedOrUp(int i, int j, int k)
    {
        if (dragging)
        {
            if (k < 0)
            {
                posX += i - pointX;
                posY += j - pointY;
                pointX = i;
                pointY = j;
            }
            else if (k == 0)
            {
                dragging = false;
            }
        }

        if (selectedButton != null && k == 0)
        {
            selectedButton.mouseReleased(i + posX, j + posY);
            selectedButton = null;
        }
    }

    public boolean isOpen()
    {
        return open;
    }

    public void toggle()
    {
        open = !open;
    }

    protected void actionPerformed(ReliantButton reliantbutton)
    {
    }

    public boolean checkBounds(int i, int j, int k, int l, int i1, int j1)
    {
        return i >= posX + k && i <= posX + i1 && j >= posY + l && j <= posY + j1;
    }

    public abstract int getWidth();

    public abstract int getHeight();
}
