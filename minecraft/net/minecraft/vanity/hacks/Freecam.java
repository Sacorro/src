package net.minecraft.vanity.hacks;

import net.minecraft.client.Minecraft;
import net.minecraft.src.*;
import net.minecraft.vanity.main.Vanity;

public class Freecam
{
    static double savedSpeedXZ;
    static double savedSpeedY;

    public Freecam()
    {
    }

    public static final void method_UpdateFreecam()
    {
        Minecraft minecraft = Minecraft.theMinecraft;
        EntityPlayerSP entityplayersp = Minecraft.thePlayer;

        if (Vanity.freecam)
        {
            Vanity.fly = true;
            savedSpeedXZ = Vanity.flySpeedXZ;
            savedSpeedY = Vanity.flySpeedY;
            Vanity.flySpeedXZ = 10D;
            Vanity.flySpeedY = 3D;
            Vanity.freecamPosition = new Location(entityplayersp);

            if (Minecraft.theWorld instanceof WorldClient)
            {
                EntityOtherPlayerMP entityotherplayermp = new EntityOtherPlayerMP(Minecraft.theWorld, Minecraft.thePlayer.username);
                entityotherplayermp.setPositionAndRotation(Vanity.freecamPosition.posX, Vanity.freecamPosition.posY - (double)entityplayersp.yOffset, Vanity.freecamPosition.posZ, Vanity.freecamPosition.rotationYaw, Vanity.freecamPosition.rotationPitch);
                WorldClient worldclient1 = (WorldClient)Minecraft.theWorld;
                worldclient1.addEntityToWorld(-1, entityotherplayermp);
            }
        }
        else
        {
            Vanity.fly = false;
            entityplayersp.noClip = false;
            Vanity.flySpeedXZ = savedSpeedXZ;
            Vanity.flySpeedY = savedSpeedY;

            if (Minecraft.theWorld instanceof WorldClient)
            {
                WorldClient worldclient = (WorldClient)Minecraft.theWorld;
                worldclient.removeEntityFromWorld(-1);
            }

            entityplayersp.setPositionAndRotation(Vanity.freecamPosition.posX, Vanity.freecamPosition.posY, Vanity.freecamPosition.posZ, Vanity.freecamPosition.rotationYaw, Vanity.freecamPosition.rotationPitch);
            Vanity.freecamPosition = null;
        }
    }
}
