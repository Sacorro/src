package net.minecraft.vanity.gui;

import java.util.List;
import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;

import net.minecraft.vanity.*;
import net.minecraft.vanity.main.Vanity;
import net.minecraft.client.*;

public class WindowValues extends ReliantGuiWindow
{
    private static Minecraft mc;

    public WindowValues(int i, int j)
    {
        super(i, j, "Values");
    }

    public void drawPanel(TrueTypeFont truetypefont)
    {
        controlList.clear();
        super.drawPanel(truetypefont);
        int i = -7;
        int j = posY + 5;
        
        ReliantArt.drawString(truetypefont, "Opacity",posX + 3, j += 13, Color.white);
        ReliantArt.drawString(truetypefont, "" + Vanity.opacity ,posX + 52, j, Color.white);
        controlList.add(new ReliantButton(1, getWidth() - 50, i += 13, 9, 9, true, "<"));
        controlList.add(new ReliantButton(2, getWidth() - 11, i, 9, 9, true, ">"));
        //
        ReliantArt.drawString(truetypefont, "Step",posX + 3, j += 13, Color.white);
        ReliantArt.drawString(truetypefont, "" + Vanity.step ,posX + 52, j, Color.white);
        controlList.add(new ReliantButton(3, getWidth() - 50, i += 13, 9, 9, true, "<"));
        controlList.add(new ReliantButton(4, getWidth() - 11, i, 9, 9, true, ">"));
        //
        ReliantArt.drawString(truetypefont, "Run",posX + 3, j += 13, Color.white);
        ReliantArt.drawString(truetypefont, "" + Vanity.speed,posX + 52, j, Color.white);
        controlList.add(new ReliantButton(5, getWidth() - 50, i += 13, 9, 9, true, "<"));
        controlList.add(new ReliantButton(6, getWidth() - 11, i, 9, 9, true, ">"));
        //
        ReliantArt.drawString(truetypefont, "Place",posX + 3, j += 13, Color.white);
        ReliantArt.drawString(truetypefont, "" + Vanity.place,posX + 52, j, Color.white);
        controlList.add(new ReliantButton(7, getWidth() - 50, i += 13, 9, 9, true, "<"));
        controlList.add(new ReliantButton(8, getWidth() - 11, i, 9, 9, true, ">"));
        //
        ReliantArt.drawString(truetypefont, "Bright",posX + 3, j += 13, Color.white);
        ReliantArt.drawString(truetypefont, "" + Vanity.brightness,posX + 52, j, Color.white);
        controlList.add(new ReliantButton(9, getWidth() - 50, i += 13, 9, 9, true, "<"));
        controlList.add(new ReliantButton(10, getWidth() - 11, i, 9, 9, true, ">"));
//        controlList2.add(new GuiCustomSlider(1, getWidth() - 50, i += 13, "test", Vanity.opacity ));
    }

    public void actionPerformed(ReliantButton reliantbutton)
    {
        if (reliantbutton.id == 1 && Vanity.opacity > 25)
        {
        	Vanity.opacity -= 1;
        	//mc.sndManager.playSoundFX("random.click", 1.0F, 1.0F);
        }

        if (reliantbutton.id == 2 && Vanity.opacity < 255)
        {
           Vanity.opacity += 1;
           //mc.sndManager.playSoundFX("random.click", 1.0F, 1.0F);
        }
        if (reliantbutton.id == 3 && Vanity.step > 0)
        {
            Vanity.step -= 0.5;
            //mc.sndManager.playSoundFX("random.click", 1.0F, 1.0F);
        }
        if (reliantbutton.id == 4)
        {
        	Vanity.step += 0.5;
        	//mc.sndManager.playSoundFX("random.click", 1.0F, 1.0F);
        }
        if (reliantbutton.id == 5 && Vanity.speed > 1)
        {
            Vanity.speed -= 0.2;
            //mc.sndManager.playSoundFX("random.click", 1.0F, 1.0F);
        }
        if (reliantbutton.id == 6)
        {
        	Vanity.speed += 0.2;
        	//mc.sndManager.playSoundFX("random.click", 1.0F, 1.0F);
        }
        if (reliantbutton.id == 7 && Vanity.place > 0)
        {
            Vanity.place -= 1;
            //mc.sndManager.playSoundFX("random.click", 1.0F, 1.0F);
        }
        if (reliantbutton.id == 8)
        {
        	Vanity.place += 1;
        	//mc.sndManager.playSoundFX("random.click", 1.0F, 1.0F);
        }
        if(reliantbutton.id == 9 && Vanity. brightness > 0)
        {
            Vanity.brightness -= 1;
            //mc.sndManager.playSoundFX("random.click", 1.0F, 1.0F);
        }
        if(reliantbutton.id == 10)
        {
        	Vanity.brightness += 1;
        	//mc.sndManager.playSoundFX("random.click", 1.0F, 1.0F);
        }
    }

    public int getWidth()
    {
        return 90;
    }

    public int getHeight()
    {
        return 79;
    }
}
