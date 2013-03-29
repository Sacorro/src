package net.minecraft.vanity.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.src.Gui;
import org.lwjgl.opengl.GL11;

public class GuiCustomSlider extends SliderBase
{
    public float sliderValue;
    public boolean dragging;

    public GuiCustomSlider(int i, int j, int k, String s, float f)
    {
        super(i, j, k, 80, 20, s);
        sliderValue = 1.0F;
        dragging = false;
        sliderValue = f;
        displayString = s;
    }

    /**
     * Returns 0 if the button is disabled, 1 if the mouse is NOT hovering over this button and 2 if it IS hovering over
     * this button.
     */
    protected int getHoverState(boolean flag)
    {
        return 0;
    }

    /**
     * Fired when the mouse button is dragged. Equivalent of MouseListener.mouseDragged(MouseEvent e).
     */
    protected void mouseDragged(Minecraft minecraft, int i, int j)
    {
        if (!drawButton)
        {
            return;
        }

        if (dragging)
        {
            sliderValue = (float)(i - (xPosition + 4)) / (float)(width - 8);

            if (sliderValue < 0.0F)
            {
                sliderValue = 0.0F;
            }

            if (sliderValue > 1.0F)
            {
                sliderValue = 1.0F;
            }
        }

        	ReliantArt.drawRect(xPosition, yPosition - 2, xPosition + (int)(sliderValue * (float)(width - 8)) + 4, yPosition + 2, 0xff000000);
        	ReliantArt.drawRect(xPosition + (int)(sliderValue * (float)(width - 8)) + 4, yPosition, xPosition + 2, yPosition + 3, 0xff00ff00);
//        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
//        Gui.drawRoundedRect(xPosition, yPosition - 2, xPosition + (int)(sliderValue * (float)(width - 8)) + 4, yPosition + 2, 1, 0xff000000, 0xff3300ff);
//        Gui.drawFilledCircle(xPosition + (int)(sliderValue * (float)(width - 8)) + 4, yPosition, 3D, 0xff111111);
//        Gui.drawFilledCircle(xPosition + (int)(sliderValue * (float)(width - 8)) + 4, yPosition, 2D, 0xff3300ff);
    }

    /**
     * Returns true if the mouse has been pressed on this control. Equivalent of MouseListener.mousePressed(MouseEvent
     * e).
     */
    public boolean mousePressed(Minecraft minecraft, int i, int j)
    {
        if (super.mousePressed(minecraft, i, j))
        {
            sliderValue = (float)(i - (xPosition + 4)) / (float)(width - 8);

            if (sliderValue < 0.0F)
            {
                sliderValue = 0.0F;
            }

            if (sliderValue > 1.0F)
            {
                sliderValue = 1.0F;
            }

            dragging = true;
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * Fired when the mouse button is released. Equivalent of MouseListener.mouseReleased(MouseEvent e).
     */
    public void mouseReleased(int i, int j)
    {
        dragging = false;
    }
}
