package net.minecraft.vanity.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.src.*;
import org.lwjgl.opengl.GL11;

public class SliderBase extends GuiButton
{
    protected int width;
    protected int height;

    /** The x position of this control. */
    public int xPosition;

    /** The y position of this control. */
    public int yPosition;

    /** The string displayed on this control. */
    public String displayString;

    /** ID for this control. */
    public int id;

    /** True if this control is enabled, false to disable. */
    public boolean enabled;

    /** Hides the button completely if false. */
    public boolean drawButton;

    public SliderBase(int i, int j, int k, String s)
    {
        this(i, j, k, 200, 22, s);
    }

    public SliderBase(int i, int j, int k, int l, int i1, String s)
    {
        super(i, j, k, l, i1, s);
        width = 200;
        height = 22;
        enabled = true;
        drawButton = true;
        id = i;
        xPosition = j;
        yPosition = k;
        width = l;
        height = i1;
        displayString = s;
    }

    /**
     * Returns 0 if the button is disabled, 1 if the mouse is NOT hovering over this button and 2 if it IS hovering over
     * this button.
     */
    protected int getHoverState(boolean flag)
    {
        byte byte0 = 1;

        if (!enabled)
        {
            byte0 = 0;
        }
        else if (flag)
        {
            byte0 = 2;
        }

        return byte0;
    }

    /**
     * Draws this button to the screen.
     */
    public void drawButton(Minecraft minecraft, int i, int j)
    {
        if (!drawButton)
        {
            return;
        }
        else
        {
            FontRenderer fontrenderer = minecraft.fontRenderer;
            GL11.glBindTexture(GL11.GL_TEXTURE_2D, minecraft.renderEngine.getTexture("/gui/gui.png"));
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            boolean flag = i >= xPosition && j >= yPosition && i < xPosition + width && j < yPosition + height;
            int k = getHoverState(flag);
//            Gui.drawRoundedRect(xPosition, yPosition - 2, xPosition + width / 2 + 40, yPosition + 2, 1, 0xff000000, 0xff333333);
            ReliantArt.drawBRect(xPosition, yPosition - 2, xPosition + width / 2 + 40, yPosition + 2, 0xff000000, 0xff333333);
            mouseDragged(minecraft, i, j);
            return;
        }
    }

    /**
     * Fired when the mouse button is dragged. Equivalent of MouseListener.mouseDragged(MouseEvent e).
     */
    protected void mouseDragged(Minecraft minecraft, int i, int j)
    {
    }

    /**
     * Fired when the mouse button is released. Equivalent of MouseListener.mouseReleased(MouseEvent e).
     */
    public void mouseReleased(int i, int j)
    {
    }

    /**
     * Returns true if the mouse has been pressed on this control. Equivalent of MouseListener.mousePressed(MouseEvent
     * e).
     */
    public boolean mousePressed(Minecraft minecraft, int i, int j)
    {
        return enabled && drawButton && i >= xPosition && j >= yPosition - 4 && i < xPosition + width && j < yPosition + 4;
    }
}
