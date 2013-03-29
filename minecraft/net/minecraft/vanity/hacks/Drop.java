package net.minecraft.vanity.hacks;

import net.minecraft.client.Minecraft;

public class Drop 
{
    public static Minecraft Minecraft;
	Minecraft mc;
    public static void createSmokescreen(boolean flag)
    {
        for(int i = 9; i < 45 && (flag || i < 36); i++)
        {
            Minecraft.playerController.windowClick(0, i, 0, false, Minecraft.thePlayer);
            for(int j = 0; j < 64; j++)
            {
                Minecraft.playerController.windowClick(0, -999, 1, false, Minecraft.thePlayer);
            }

        }

    }
}
