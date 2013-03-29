package net.minecraft.vanity.hacks;

import java.util.HashMap;
import net.minecraft.client.Minecraft;
import net.minecraft.src.*;

public class Teleport
{
    static Minecraft mc;

    public Teleport(Minecraft minecraft)
    {
        mc = minecraft;
    }

    public static void position(double d, double d1, double d2)
    {
        mc.thePlayer.setPosition(d, d1, d2);
        mc.getSendQueue().addToSendQueue(new Packet11PlayerPosition(mc.thePlayer.posX, mc.thePlayer.boundingBox.minY, mc.thePlayer.posY, mc.thePlayer.posZ, mc.thePlayer.onGround));
    }

    public static void flyTo(double d, double d1, double d2)
    {
        float f = 9F;
        double d3 = -6D;
        long l = 2L;

        for (int j = 0; j < 15; j++)
        {
            while (mc.thePlayer.posY < 150D)
            {
                position(mc.thePlayer.posX, mc.thePlayer.posY + (double)f, mc.thePlayer.posZ);

                try
                {
                    Thread.sleep(l);
                }
                catch (Exception exception) { }
            }

            while (mc.thePlayer.posY > 151D)
            {
                position(mc.thePlayer.posX, mc.thePlayer.posY - (double)f, mc.thePlayer.posZ);

                try
                {
                    Thread.sleep(l);
                }
                catch (Exception exception1) { }
            }
        }

        f = 7F;
        double d4 = mc.thePlayer.posX - d;

        if (d4 < 0.0D)
        {
            d4 = -d4;
        }

        for (int k = 0; (double)k < d4; k++)
        {
            while (mc.thePlayer.posX < d + d3)
            {
                position(mc.thePlayer.posX + (double)f, mc.thePlayer.posY, mc.thePlayer.posZ);

                try
                {
                    Thread.sleep(l);
                }
                catch (Exception exception2) { }
            }

            while (mc.thePlayer.posX > d - d3)
            {
                position(mc.thePlayer.posX - (double)f, mc.thePlayer.posY, mc.thePlayer.posZ);

                try
                {
                    Thread.sleep(l);
                }
                catch (Exception exception3) { }
            }
        }

        double d5 = mc.thePlayer.posZ - d2;

        if (d5 < 0.0D)
        {
            d5 = -d5;
        }

        for (int i1 = 0; (double)i1 < d5; i1++)
        {
            while (mc.thePlayer.posZ < d2 + d3)
            {
                position(mc.thePlayer.posX, mc.thePlayer.posY, mc.thePlayer.posZ + (double)f);

                try
                {
                    Thread.sleep(l);
                }
                catch (Exception exception5) { }
            }

            while (mc.thePlayer.posZ > d2 - d3)
            {
                position(mc.thePlayer.posX, mc.thePlayer.posY, mc.thePlayer.posZ - (double)f);

                try
                {
                    Thread.sleep(l);
                }
                catch (Exception exception6) { }
            }
        }

        mc.thePlayer.setPosition(d + 0.5D, mc.thePlayer.posY, d2 + 0.5D);
        f = 7F;

        for (int j1 = 0; j1 < 15 && mc.thePlayer.posY >= d1; j1++)
        {
            if (mc.thePlayer.posY < d1 + 8D)
            {
                f = 4F;
            }

            if (mc.thePlayer.posY < d1 + 4D)
            {
                f = 1.0F;
            }

            position(mc.thePlayer.posX, mc.thePlayer.posY - (double)f, mc.thePlayer.posZ);

            try
            {
                Thread.sleep(l);
            }
            catch (Exception exception7) { }
        }

        mc.thePlayer.motionY = -500D;

        if (l <= 1L)
        {
            try
            {
                Thread.sleep(75L);
            }
            catch (Exception exception4) { }
        }
    }
}
