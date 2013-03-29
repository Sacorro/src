package net.minecraft.vanity.gui;

import java.util.List;
import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;

import net.minecraft.vanity.*;
import net.minecraft.vanity.main.Vanity;
import net.minecraft.vanity.main.VanityXrayManager;
import net.minecraft.client.*;

public class WindowWallhack extends ReliantGuiWindow
{
    private static Minecraft mc;

    public WindowWallhack(int i, int j)
    {
        super(i, j, "Wallhack");
    }

    public void drawPanel(TrueTypeFont truetypefont)
    {
        controlList.clear();
        super.drawPanel(truetypefont);
        int i = -7;
        int j = posY + 5;
        ReliantArt.drawString(truetypefont, "Valuable",posX + 3, j += 12, VanityXrayManager.xrayBlocks[22] ? Color.green : Color.red);
        ReliantArt.drawString(truetypefont, "Fuel",posX + 3, j += 12, VanityXrayManager.xrayBlocks[16] ? Color.green : Color.red);
        ReliantArt.drawString(truetypefont, "Iron",posX + 3, j += 12, VanityXrayManager.xrayBlocks[15] ? Color.green : Color.red);
        ReliantArt.drawString(truetypefont, "Diamond",posX + 3, j += 12, VanityXrayManager.xrayBlocks[56] ? Color.green : Color.red);
        ReliantArt.drawString(truetypefont, "Gold",posX + 3, j += 12, VanityXrayManager.xrayBlocks[14] ? Color.green : Color.red);
        ReliantArt.drawString(truetypefont, "Redstone",posX + 3, j += 12, VanityXrayManager.xrayBlocks[21] ? Color.green : Color.red);
        ReliantArt.drawString(truetypefont, "Danger",posX + 3, j += 12, VanityXrayManager.xrayBlocks[30] ? Color.green : Color.red);
        ReliantArt.drawString(truetypefont, "Manmade",posX + 3, j += 12, VanityXrayManager.xrayBlocks[5] ? Color.green : Color.red);
        ReliantArt.drawString(truetypefont, "Dungeon",posX + 3, j += 12, VanityXrayManager.xrayBlocks[112] ? Color.green : Color.red);
        controlList.add(new ReliantButton(0, getWidth() - 39, i += 12, VanityXrayManager.xrayBlocks[22]));
        controlList.add(new ReliantButton(1, getWidth() - 39, i += 12, VanityXrayManager.xrayBlocks[16]));
        controlList.add(new ReliantButton(2, getWidth() - 39, i += 12, VanityXrayManager.xrayBlocks[15]));
        controlList.add(new ReliantButton(4, getWidth() - 39, i += 12, VanityXrayManager.xrayBlocks[56]));
        controlList.add(new ReliantButton(3, getWidth() - 39, i += 12, VanityXrayManager.xrayBlocks[14]));
        controlList.add(new ReliantButton(6, getWidth() - 39, i += 12, VanityXrayManager.xrayBlocks[21]));
        controlList.add(new ReliantButton(5, getWidth() - 39, i += 12, VanityXrayManager.xrayBlocks[30]));
        controlList.add(new ReliantButton(8, getWidth() - 39, i += 12, VanityXrayManager.xrayBlocks[5]));
        controlList.add(new ReliantButton(7, getWidth() - 39, i += 12, VanityXrayManager.xrayBlocks[112]));
    }

    public void actionPerformed(ReliantButton reliantbutton)
    {
        if (reliantbutton.id == 7)
        {
        	//mc.sndManager.playSoundFX("random.click", 1.0F, 1.0F);
            VanityXrayManager.xrayBlocks[112] = !VanityXrayManager.xrayBlocks[112]; //Nether Brick
            VanityXrayManager.xrayBlocks[113] = !VanityXrayManager.xrayBlocks[113]; //Nether Brick fence
            VanityXrayManager.xrayBlocks[114] = !VanityXrayManager.xrayBlocks[114]; //Nether Brick Stairs
            VanityXrayManager.xrayBlocks[109] = !VanityXrayManager.xrayBlocks[109]; //Stone brick stairs
            VanityXrayManager.xrayBlocks[98] = !VanityXrayManager.xrayBlocks[98]; //Stone bricks
            VanityXrayManager.xrayBlocks[97] = !VanityXrayManager.xrayBlocks[97]; //Silverfish block
            if (Vanity.xray);

            mc.renderGlobal.loadRenderers();
        }

        if (reliantbutton.id == 8)
        {
        	//mc.sndManager.playSoundFX("random.click", 1.0F, 1.0F);
            VanityXrayManager.xrayBlocks[5] = !VanityXrayManager.xrayBlocks[5]; //Planks
            VanityXrayManager.xrayBlocks[4] = !VanityXrayManager.xrayBlocks[4]; //Cobble
            VanityXrayManager.xrayBlocks[61] = !VanityXrayManager.xrayBlocks[61]; //Furnace
            VanityXrayManager.xrayBlocks[58] = !VanityXrayManager.xrayBlocks[58]; //Crafting bench
            VanityXrayManager.xrayBlocks[45] = !VanityXrayManager.xrayBlocks[45]; //Bricks
            VanityXrayManager.xrayBlocks[44] = !VanityXrayManager.xrayBlocks[44]; //Slabs
            VanityXrayManager.xrayBlocks[43] = !VanityXrayManager.xrayBlocks[43]; //Double slab
            VanityXrayManager.xrayBlocks[35] = !VanityXrayManager.xrayBlocks[35]; //Wool
            VanityXrayManager.xrayBlocks[20] = !VanityXrayManager.xrayBlocks[20]; //Glass

            if (Vanity.xray);

            mc.renderGlobal.loadRenderers();
        }

        if (reliantbutton.id == 5)
        {
        	//mc.sndManager.playSoundFX("random.click", 1.0F, 1.0F);
            VanityXrayManager.xrayBlocks[48] = !VanityXrayManager.xrayBlocks[48]; //Mossy cobble
            VanityXrayManager.xrayBlocks[46] = !VanityXrayManager.xrayBlocks[46]; //TNT
            VanityXrayManager.xrayBlocks[52] = !VanityXrayManager.xrayBlocks[52]; //Mob spawner
            VanityXrayManager.xrayBlocks[30] = !VanityXrayManager.xrayBlocks[30]; //Cobwebs

            if (Vanity.xray);

            mc.renderGlobal.loadRenderers();
        }

        if (reliantbutton.id == 4)
        {
        	//mc.sndManager.playSoundFX("random.click", 1.0F, 1.0F);
            VanityXrayManager.xrayBlocks[56] = !VanityXrayManager.xrayBlocks[56]; //Diamond ore

            if (Vanity.xray);

            mc.renderGlobal.loadRenderers();
        }

        if (reliantbutton.id == 3)
        {
        	//mc.sndManager.playSoundFX("random.click", 1.0F, 1.0F);
            VanityXrayManager.xrayBlocks[14] = !VanityXrayManager.xrayBlocks[14]; //Gold ore

            if (Vanity.xray);

            mc.renderGlobal.loadRenderers();
        }

        if (reliantbutton.id == 2)
        {
        	//mc.sndManager.playSoundFX("random.click", 1.0F, 1.0F);
            VanityXrayManager.xrayBlocks[15] = !VanityXrayManager.xrayBlocks[15]; //Iron ore

            if (Vanity.xray);

            mc.renderGlobal.loadRenderers();
        }

        if (reliantbutton.id == 1)
        {
        	//mc.sndManager.playSoundFX("random.click", 1.0F, 1.0F);
            VanityXrayManager.xrayBlocks[16] = !VanityXrayManager.xrayBlocks[16]; //Coal ore

            if (Vanity.xray);

            mc.renderGlobal.loadRenderers();
        }

        if (reliantbutton.id == 0)
        {
        	//mc.sndManager.playSoundFX("random.click", 1.0F, 1.0F);
            VanityXrayManager.xrayBlocks[22] = !VanityXrayManager.xrayBlocks[22]; //Lapis block
            VanityXrayManager.xrayBlocks[49] = !VanityXrayManager.xrayBlocks[49]; //Obsidian
            VanityXrayManager.xrayBlocks[57] = !VanityXrayManager.xrayBlocks[57]; //Dblock
            VanityXrayManager.xrayBlocks[41] = !VanityXrayManager.xrayBlocks[41]; //Gold block
            VanityXrayManager.xrayBlocks[42] = !VanityXrayManager.xrayBlocks[42]; //Iron block
            VanityXrayManager.xrayBlocks[89] = !VanityXrayManager.xrayBlocks[89]; //Glowstone
            VanityXrayManager.xrayBlocks[47] = !VanityXrayManager.xrayBlocks[47]; //Bookshelf
            VanityXrayManager.xrayBlocks[54] = !VanityXrayManager.xrayBlocks[54]; //Chest

            if (Vanity.xray);

            mc.renderGlobal.loadRenderers();
        }

        if (reliantbutton.id == 6)
        {
        	//mc.sndManager.playSoundFX("random.click", 1.0F, 1.0F);
            VanityXrayManager.xrayBlocks[21] = !VanityXrayManager.xrayBlocks[21]; //Lapis ore
            VanityXrayManager.xrayBlocks[73] = !VanityXrayManager.xrayBlocks[73]; //Redstone ore (not glowing)
            VanityXrayManager.xrayBlocks[74] = !VanityXrayManager.xrayBlocks[74]; //Redstone ore (glowing)

            if (Vanity.xray);

            mc.renderGlobal.loadRenderers();
        }
    }

    public int getWidth()
    {
        return 83;
    }

    public int getHeight()
    {
        return 123;
    }
}
