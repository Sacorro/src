package net.minecraft.vanity.gui;

import java.io.IOException;
import java.nio.IntBuffer;
import net.minecraft.client.Minecraft;
import net.minecraft.src.*;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.*;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.util.ResourceLoader;

import java.awt.Font;

public class ReliantArt
{
    private static Minecraft mc;
    private static UnicodeFont baseFont;
    Texture texture;

    public ReliantArt()
    {
        baseFont = new UnicodeFont(new Font("Lucida Sans Typewriter", Font.TRUETYPE_FONT, 16));
    }

    
    public static void drawBorderedRect(int i, int j, int k, int l, int i1, float f, int j1)
    {
        drawRect(i, j, k, l, i1);
        disableDefaults();
        GL11.glColor4d(getRedFromHex(j1), getGreenFromHex(j1), getBlueFromHex(j1), getAlphaFromHex(j1));
        GL11.glLineWidth(f);
        GL11.glBegin(GL11.GL_LINES);
		GL11.glVertex2d(i, j);
		GL11.glVertex2d(i, l);
		GL11.glVertex2d(k, l);
		GL11.glVertex2d(k, j);
		GL11.glVertex2d(i, j);
		GL11.glVertex2d(k, j);
		GL11.glVertex2d(i, l);
		GL11.glVertex2d(k, l);
        GL11.glEnd();
        enableDefaults();
    }

    public static void drawRect(int i, int j, int k, int l, int i1)
    {
        disableDefaults();
        GL11.glColor4d(getRedFromHex(i1), getGreenFromHex(i1), getBlueFromHex(i1), getAlphaFromHex(i1));
        GL11.glBegin(GL11.GL_QUADS);
        GL11.glVertex2i(k, j);
        GL11.glVertex2i(i, j);
        GL11.glVertex2i(i, l);
        GL11.glVertex2i(k, l);
        GL11.glEnd();
        enableDefaults();
    }

    public static void drawRoundedRect(int i, int j, int k, int l, int i1, int j1)
    {
        disableDefaults();
        int k1 = Math.abs(i + i1);
        int l1 = Math.abs(j + i1);
        int i2 = Math.abs(k - i1);
        int j2 = Math.abs(l - i1);
        double d = getRedFromHex(j1);
        double d1 = getGreenFromHex(j1);
        double d2 = getBlueFromHex(j1);
        double d3 = getAlphaFromHex(j1);
        drawRect(k1, l1, i2, j2, j1);
        drawRect(i, l1, k1, j2, j1);
        drawRect(i2, l1, k, j2, j1);
        drawRect(k1, j, i2, l1, j1);
        drawRect(k1, j2, i2, l, j1);
        drawQuarterCircle(k1, l1, i1, 0, j1);
        drawQuarterCircle(i2, l1, i1, 1, j1);
        drawQuarterCircle(k1, j2, i1, 2, j1);
        drawQuarterCircle(i2, j2, i1, 3, j1);
        enableDefaults();
    }

    public static void drawCenteredString(UnicodeFont unicodefont, float f, float f1, String s, Color color)
    {
        drawString(unicodefont, s, f - (float)(unicodefont.getWidth(s) / 2), f1, color);
    }

    public static void drawTTFString(UnicodeFont unicodefont, String s, float f, float f1, Color color)
    {
        disableDefaults();
        int i = 0;
        int j = 0;
        boolean flag = false;

        if (s.contains("\247"))
        {
            String as[] = s.split("\247");

            if (!s.startsWith("\247"))
            {
                unicodefont.drawString(f, f1, as[0], color);
                i += unicodefont.getWidth(as[0]);
                flag = true;
            }

            do
            {
                if (j == as.length)
                {
                    break;
                }

                if (j != 0 || !flag)
                {
                    String s1 = as[j];

                    if (s1.length() != 0)
                    {
                        char c = 'z';

                        try
                        {
                            c = s1.charAt(0);
                        }
                        catch (Exception exception)
                        {
                            exception.printStackTrace();
                        }

                        int k = "0123456789abcdefk".indexOf((new StringBuilder()).append("").append(c).toString().toLowerCase());

                        if (k == -1)
                        {
                            break;
                        }

                        int l = mc.fontRenderer.colorCode[k];
                        as[j] = (new StringBuilder()).append(as[j].substring(0, 0)).append(as[j].substring(1)).toString();
                        unicodefont.drawString(f + (float)i + 2.0F, f1 + 2.0F, as[j], Color.black);
                        unicodefont.drawString(f + (float)i, f1, as[j], new Color((float)getRedFromHex(l), (float)getGreenFromHex(l), (float)getBlueFromHex(l)));
                        i += unicodefont.getWidth(as[j]);
                    }
                }

                j++;
            }
            while (true);
        }
        else
        {
            unicodefont.drawString(f, f1, s, color);
        }

        enableDefaults();
    }

    public static void drawUnicodeString(String s, float f, float f1, Color color)
    {
        drawString(baseFont, s, f, f1, color);
    }

    public static void drawString(UnicodeFont unicodefont, String s, float f, float f1, Color color)
    {
        disableDefaults();
        int i = 0;
        int j = 0;
        boolean flag = false;

        if (s.contains("\247"))
        {
            String as[] = s.split("\247");

            if (!s.startsWith("\247"))
            {
                unicodefont.drawString(f, f1, as[0], color);
                i += unicodefont.getWidth(as[0]);
                flag = true;
            }

            do
            {
                if (j == as.length)
                {
                    break;
                }

                if (j != 0 || !flag)
                {
                    String s1 = as[j];

                    if (s1.length() != 0)
                    {
                        char c = 'z';

                        try
                        {
                            c = s1.charAt(0);
                        }
                        catch (Exception exception)
                        {
                            exception.printStackTrace();
                        }

                        int k = "0123456789abcdefk".indexOf((new StringBuilder()).append("").append(c).toString().toLowerCase());

                        if (k == -1)
                        {
                            break;
                        }

                        int l = mc.fontRenderer.colorCode[k];
                        as[j] = (new StringBuilder()).append(as[j].substring(0, 0)).append(as[j].substring(1)).toString();
                        unicodefont.drawString(f + (float)i + 1, f1 + 2, as[j], Color.black);
                        unicodefont.drawString(f + (float)i, f1, as[j], new Color((float)getRedFromHex(l), (float)getGreenFromHex(l), (float)getBlueFromHex(l)));
                        i += unicodefont.getWidth(as[j]);
                    }
                }

                j++;
            }
            while (true);
        }
        else
        {
            unicodefont.drawString(f + 1, f1 + 1, s, Color.black);
            unicodefont.drawString(f, f1, s, color);
        }

        enableDefaults();
    }


    public static void drawLine2D(int i, int j, int k, int l, int i1, float f)
    {
        disableDefaults();
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glEnable(GL11.GL_LINE_SMOOTH);
        GL11.glColor4d(getRedFromHex(i1), getGreenFromHex(i1), getBlueFromHex(i1), getAlphaFromHex(i1));
        GL11.glBegin(GL11.GL_LINES);
        GL11.glVertex2i(i, j);
        GL11.glVertex2i(k, l);
        GL11.glEnd();
        GL11.glDisable(GL11.GL_LINE_SMOOTH);
        enableDefaults();
    }

    public static void drawBorderedCircle(int i, int j, int k, int l, float f, int i1)
    {
        drawCircle(i, j, k, l);
        drawUnfilledCircle(i, j, k, f, i1);
    }

    public static void drawUnfilledCircle(int i, int j, int k, float f, int l)
    {
        disableDefaults();
        GL11.glColor4d(getRedFromHex(l), getGreenFromHex(l), getBlueFromHex(l), getAlphaFromHex(l));
        GL11.glLineWidth(f);
        GL11.glEnable(GL11.GL_LINE_SMOOTH);
        GL11.glBegin(GL11.GL_LINE_LOOP);

        for (int i1 = 0; i1 <= 360; i1++)
        {
            GL11.glVertex2d((double)i + Math.sin(((double)i1 * Math.PI) / 180D) * (double)k, (double)j + Math.cos(((double)i1 * Math.PI) / 180D) * (double)k);
        }

        GL11.glEnd();
        GL11.glDisable(GL11.GL_LINE_SMOOTH);
        enableDefaults();
    }

    public static void drawCircle(int i, int j, int k, int l)
    {
        disableDefaults();
        GL11.glColor4d(getRedFromHex(l), getGreenFromHex(l), getBlueFromHex(l), getAlphaFromHex(l));
        GL11.glBegin(GL11.GL_POLYGON);

        for (int i1 = 0; i1 <= 360; i1++)
        {
            GL11.glVertex2d((double)i + Math.sin(((double)i1 * Math.PI) / 180D) * (double)k, (double)j + Math.cos(((double)i1 * Math.PI) / 180D) * (double)k);
        }

        GL11.glEnd();
        enableDefaults();
    }

    public static void drawQuarterCircle(int i, int j, int k, int l, int i1)
    {
        disableDefaults();
        GL11.glColor4d(getRedFromHex(i1), getGreenFromHex(i1), getBlueFromHex(i1), getAlphaFromHex(i1));
        GL11.glBegin(GL11.GL_POLYGON);
        GL11.glVertex2d(i, j);

        if (l == 0)
        {
            for (int j1 = 0; j1 <= 90; j1++)
            {
                GL11.glVertex2d((double)i + Math.sin(((double)j1 * Math.PI) / 180D) * (double)(k * -1), (double)j + Math.cos(((double)j1 * Math.PI) / 180D) * (double)(k * -1));
            }
        }
        else if (l == 1)
        {
            for (int k1 = 90; k1 <= 180; k1++)
            {
                GL11.glVertex2d((double)i + Math.sin(((double)k1 * Math.PI) / 180D) * (double)k, (double)j + Math.cos(((double)k1 * Math.PI) / 180D) * (double)k);
            }
        }
        else if (l == 2)
        {
            for (int l1 = 90; l1 <= 180; l1++)
            {
                GL11.glVertex2d((double)i + Math.sin(((double)l1 * Math.PI) / 180D) * (double)(k * -1), (double)j + Math.cos(((double)l1 * Math.PI) / 180D) * (double)(k * -1));
            }
        }
        else if (l == 3)
        {
            for (int i2 = 0; i2 <= 90; i2++)
            {
                GL11.glVertex2d((double)i + Math.sin(((double)i2 * Math.PI) / 180D) * (double)k, (double)j + Math.cos(((double)i2 * Math.PI) / 180D) * (double)k);
            }
        }

        GL11.glEnd();
        enableDefaults();
    }

    public static double getAlphaFromHex(int i)
    {
        return (double)((float)(i >> 24 & 0xff) / 255F);
    }

    public static double getRedFromHex(int i)
    {
        return (double)((float)(i >> 16 & 0xff) / 255F);
    }

    public static double getGreenFromHex(int i)
    {
        return (double)((float)(i >> 8 & 0xff) / 255F);
    }

    public static double getBlueFromHex(int i)
    {
        return (double)((float)(i & 0xff) / 255F);
    }

    public static int getScreenWidth()
    {
        IntBuffer intbuffer = BufferUtils.createIntBuffer(16);
        GL11.glGetInteger(GL11.GL_VIEWPORT, intbuffer);
        return Math.round(intbuffer.get(2));
    }

    public static int getScreenHeight()
    {
        IntBuffer intbuffer = BufferUtils.createIntBuffer(16);
        GL11.glGetInteger(GL11.GL_VIEWPORT, intbuffer);
        return Math.round(intbuffer.get(3));
    }

    public static void setupGradient()
    {
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glDisable(GL11.GL_ALPHA_TEST);
        GL11.glShadeModel(GL11.GL_SMOOTH);
    }

    public static void unsetupGradient()
    {
        GL11.glShadeModel(GL11.GL_FLAT);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glEnable(GL11.GL_ALPHA_TEST);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
    }

    public static void setupOverlayRendering()
    {
        ScaledResolution scaledresolution = new ScaledResolution(mc.gameSettings, mc.displayWidth, mc.displayHeight);
        GL11.glClear(256);
        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glLoadIdentity();
        GL11.glOrtho(0.0D, scaledresolution.scaledWidthD, scaledresolution.scaledHeightD, 0.0D, 1000D, 3000D);
        GL11.glMatrixMode(GL11.GL_MODELVIEW);
        GL11.glLoadIdentity();
        GL11.glTranslatef(0.0F, 0.0F, -2000F);
    }

    public static void disableDefaults()
    {
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
    }

    public static void enableDefaults()
    {
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glEnable(GL11.GL_LIGHTING);
    }

    public static void disableLighting()
    {
        GL11.glDisable(GL11.GL_LIGHTING);
    }

    public static void drawBRect(int i, int j, int k, int l, int i1, int j1)
    {
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glDisable(GL11.GL_LIGHTING);
        Gui.drawRect(i, j, k, l, i1);
        GL11.glPushMatrix();
        GL11.glScalef(0.5F, 0.5F, 0.5F);
        Gui.drawRect(i * 2 - 1, j * 2, i * 2, l * 2 - 1, j1);
        Gui.drawRect(i * 2, j * 2 - 1, k * 2, j * 2, j1);
        Gui.drawRect(k * 2, j * 2, k * 2 + 1, l * 2 - 1, j1);
        Gui.drawRect(i * 2, l * 2 - 1, k * 2, l * 2, j1);
        GL11.glPopMatrix();
        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glDisable(GL11.GL_BLEND);
    }
    
    public static void drawBorederedRectB(int i, int j, int k, int l, int i1, int j1)
    {
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glDisable(GL11.GL_LIGHTING);
        Gui.drawRect(i, j, k, l, i1);
        GL11.glPushMatrix();
        GL11.glScalef(0.5F, 0.5F, 0.5F);
        Gui.drawRect(i * 2 - 1, j * 2, i * 2, l * 2 + 1, j1);
        Gui.drawRect(i * 2, j * 2 + 1, k * 2, j * 2, j1);
        Gui.drawRect(k * 2, j * 2, k * 2 + 1, l * 2 + 1, j1);
        Gui.drawRect(i * 2, l * 2 + 1, k * 2, l * 2, j1);
        GL11.glPopMatrix();
        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glDisable(GL11.GL_BLEND);
    }

    public static void drawButton(int i, int j, int k, int l, int i1, int j1, TrueTypeFont truetypefont, String s)
    {
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glDisable(GL11.GL_LIGHTING);
        drawBorederedRectB(i, j, k, l, i1, j1);
        drawCenteredString(truetypefont, s, i + k, j + l, Color.white);
        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glDisable(GL11.GL_BLEND);
    }

    public static void drawString(TrueTypeFont truetypefont, String s, int i, int j, Color color)
    {
        GL11.glPushMatrix();
        GL11.glScalef(0.5F, 0.5F, 0.5F);
        renderString(truetypefont, s, i * 2, j * 2, color);
        GL11.glScalef(2.0F, 2.0F, 2.0F);
        GL11.glPopMatrix();
    }

    public static void drawButtonString(TrueTypeFont truetypefont, String s, float f, float f1, Color color)
    {
        GL11.glPushMatrix();
        GL11.glScalef(0.5F, 0.5F, 0.5F);
        renderButtonString(truetypefont, s, f * 2.0F, f1 * 2.0F, color);
        GL11.glScalef(2.0F, 2.0F, 2.0F);
        GL11.glPopMatrix();
    }

    public static void renderButtonString(TrueTypeFont truetypefont, String s, float f, float f1, Color color)
    {
        GL11.glPushMatrix();
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glDisable(GL11.GL_LIGHTING);
        truetypefont.drawString(f, f1, s, color);
        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glPopMatrix();
    }

    public static void drawCenteredString(TrueTypeFont truetypefont, String s, int i, int j, Color color)
    {
        GL11.glPushMatrix();
        GL11.glScalef(0.5F, 0.5F, 0.5F);
        renderString(truetypefont, s, i - truetypefont.getWidth(s) / 2, j - truetypefont.getHeight(s) / 2, color);
        GL11.glScalef(2.0F, 2.0F, 2.0F);
        GL11.glPopMatrix();
    }

    public static void renderString(TrueTypeFont truetypefont, String s, int i, int j, Color color)
    {
        GL11.glPushMatrix();
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glDisable(GL11.GL_LIGHTING);
        truetypefont.drawString(i, j, s, color);
        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glPopMatrix();
    }
}
