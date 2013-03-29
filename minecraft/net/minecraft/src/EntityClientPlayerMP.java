package net.minecraft.src;

import java.util.List;
import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.vanity.commands.Manager;
import net.minecraft.vanity.hacks.Derp;
import net.minecraft.vanity.hacks.KillAura;
import net.minecraft.vanity.main.Vanity;
import net.minecraft.vanity.main.Wrapper;

public class EntityClientPlayerMP extends EntityPlayerSP
{
	public NetClientHandler sendQueue;

	/**
	 * Tick counter that resets every 20 ticks, used for sending inventory updates
	 */
	private int inventoryUpdateTickCounter;
	private double oldPosX;

	/** Old Minimum Y of the bounding box */
	private double oldMinY;
	private double oldPosY;
	private double oldPosZ;
	private float oldRotationYaw;
	private float oldRotationPitch;

	/** Check if was on ground last update */
	private boolean wasOnGround;

	/** should the player stop sneaking? */
	private boolean shouldStopSneaking;
	private boolean wasSneaking;

	/** The time since the client player moved */
	public static int timeSinceMoved;

	/** has the client player's health been set? */
	private boolean hasSetHealth;

	/** VANITY **/
	int i = 0;
	int asdf = 0;
	public double lastX;
	public double lastY;
	public double lastZ;
	public double savedY;
	public double resetDistance = 2.5;
	private double maxDistance = 0.75;
	private double maxVerticalDistance = 0.75;
	private double savedposx;
	private double savedposy;
	private double savedposz;
	private Random rng;

	public EntityClientPlayerMP(Minecraft par1Minecraft, World par2World, Session par3Session, NetClientHandler par4NetClientHandler)
	{
		super(par1Minecraft, par2World, par3Session, 0);
		inventoryUpdateTickCounter = 0;
		wasOnGround = false;
		shouldStopSneaking = false;
		wasSneaking = false;
		timeSinceMoved = 0;
		hasSetHealth = false;
		sendQueue = par4NetClientHandler;
		rng = new Random();
	}

	/**
	 * Called when the entity is attacked.
	 */
	public boolean attackEntityFrom(DamageSource par1DamageSource, int par2)
	{
		return false;
	}

	/**
	 * Heal living entity (param: amount of half-hearts)
	 */
	public void heal(int i)
	{
	}

	/**
	 * Called to update the entity's position/logic.
	 */
	public void onUpdate()
	{
		if (!worldObj.blockExists(MathHelper.floor_double(posX), 0, MathHelper.floor_double(posZ)))
		{
			return;
		}
		else
		{
			super.onUpdate();
			if(!Vanity.freecam)
			sendMotionUpdates();
			return;
		}
	}

	/**
	 * Send updated motion and position information to the server
	 */
	public void sendMotionUpdates()
	{
		if(Vanity.knockback)
		{
			 sendQueue.addToSendQueue(new Packet19EntityAction(this, 4)); //sprinting herp derp
		}

		/** VANITY **/
		if(Vanity.retardhump)
		{
			asdf++;
			if(asdf <= 1) 
			{
				Vanity.humpsneak = true;
			}
			if(asdf >= 2)
			{
				Vanity.humpsneak = false;
				asdf = 0;
			}
		} 
		i++;
		if(Vanity.fly && Vanity.nocheat)
		{
			Vanity.flySpeedY = 0.3f;
			Vanity.flySpeedXZ = 1.2f;
		}
		if(Vanity.fly && !Vanity.nocheat)
		{
			Vanity.flySpeedY *= Vanity.flyspeed;
			Vanity.flySpeedXZ *= Vanity.flyspeed;
		}
		
		if(!Vanity.fly && Vanity.nocheat)
		{
			Vanity.glide = false;
		}
		
		if(Vanity.sneak && Vanity.nocheat)
		{
			mc.thePlayer.motionX /= 1.8;
			mc.thePlayer.motionZ /= 1.8;
			Vanity.sprint = false;
			mc.thePlayer.setSprinting(false);
		}

		int aDelay = 6; // The aura delay (6 gameticks)
		int dTime = Vanity.kDelay;
		try
		{
			if (dTime <= 0) {
				KillAura.KillAuraPlayer(mc);
				Vanity.kDelay = aDelay;
			}
		} 
		catch(Exception fuckplayers)
		{
			fuckplayers.printStackTrace();
		}
		try
		{
			if (dTime <= 0) {
				KillAura.KillAuraMob(mc);
				Vanity.kDelay = aDelay;
			}
		}
		catch(Exception fuckmobs)
		{
			fuckmobs.printStackTrace();
		}
		try
		{
			if (dTime <= 0) {
				KillAura.KillAuraAnimal(mc);
				Vanity.kDelay = aDelay;
			}
		}
		catch(Exception fuckanimals)
		{
			fuckanimals.printStackTrace();
		}
		if(Vanity.nukersurvival)
		{
			int size = 3;
			for(int x = -size; x < size + 1; x++)
			{
				for(int z = -size; z < size + 1; z++)
				{
					for(int y = -size; y < size + 1; y++)
					{
						int i = (int) posX + x;
						int j = (int) posY + y;
						int k = (int) posZ + z;
						if(mc.theWorld.getBlockId(i, j, k) != 0){
							mc.getSendQueue().addToSendQueue(new Packet14BlockDig(0, i, j, k, 0));
							mc.getSendQueue().addToSendQueue(new Packet14BlockDig(2, i, j, k, 0));
						}
					}
				}
			}
		}
		if(Vanity.nukertorch)
		{
			int size = 5;
			for(int x = -size; x < size + 1; x++)
			{
				for(int z = -size; z < size + 1; z++)
				{
					for(int y = -size; y < size + 1; y++)
					{
						int i = (int) posX + x;
						int j = (int) posY + y;
						int k = (int) posZ + z;
						if(mc.theWorld.getBlockId(i, j, k) == 50)
						{
							mc.getSendQueue().addToSendQueue(new Packet14BlockDig(0, i, j, k, 0));
							mc.getSendQueue().addToSendQueue(new Packet14BlockDig(2, i, j, k, 0));
						}
					}
				}
			}
		}
		if (inventoryUpdateTickCounter++ == 20)
		{
			inventoryUpdateTickCounter = 0;
		}

		boolean flag = isSprinting();

		if (flag != wasSneaking)
		{
			if (flag)
			{
				sendQueue.addToSendQueue(new Packet19EntityAction(this, 4));
			}
			else
			{
				sendQueue.addToSendQueue(new Packet19EntityAction(this, 5));
			}

			wasSneaking = flag;
		}
		/** VANITY **/
		boolean flag1 = isSneaking() || Vanity.sneak || Vanity.humpsneak;

		if (flag1 != shouldStopSneaking)
		{
			if (flag1)
			{
				sendQueue.addToSendQueue(new Packet19EntityAction(this, 1));
			}
			else
			{
				sendQueue.addToSendQueue(new Packet19EntityAction(this, 2));
			}

			shouldStopSneaking = flag1;
		}

		double d = posX - oldPosX;
		double d1 = boundingBox.minY - oldMinY;
		double d2 = posY - oldPosY;
		double d3 = posZ - oldPosZ;
		double d4 = rotationYaw - oldRotationYaw;
		double d5 = rotationPitch - oldRotationPitch;
		boolean flag2 = d1 != 0.0D || d2 != 0.0D || d != 0.0D || d3 != 0.0D;
		boolean flag3 = d4 != 0.0D || d5 != 0.0D;
		float savedYaw = rotationYaw;
		float savedPitch = rotationPitch;
		if(Vanity.derp)
		{
			rotationYaw = rng.nextInt(360) + 1;
			rotationPitch = rng.nextInt(180) + 1;
		}
		if(Vanity.retardheadinbodyrotating)
		{
			rotationYaw = rng.nextInt(360) + 1;
			rotationPitch = 180;
		}
		if(Vanity.retardheadinbody)
		{
			rotationYaw = 0;
			rotationPitch = 180;
		}
		if(Vanity.retardstarereverse)
		{
			rotationYaw = rotationYaw -180;
		}
		if(Vanity.retardstare)
		{
			rotationYaw = 180;
		}
		if(Vanity.retardheadbang)
		{
			rotationPitch = rng.nextInt(360) + 1;
			rotationYaw = 180;
		}
		if(Vanity.retardno)
		{
			rotationYaw = rng.nextInt(90) + 1;
			//rotationPitch = 180;
		}
		if(Vanity.retardspin)
		{
			rotationYaw = rng.nextInt(999999999) + 1;
		}
		if(Vanity.retardyes)
		{
			rotationPitch = rng.nextInt(90) + 1;
			//rotationPitch = 180;
		}
		if (ridingEntity != null)
		{
			if (flag3)
			{
				sendQueue.addToSendQueue(new Packet11PlayerPosition(motionX, -999D, -999D, motionZ, onGround));
			}
			else
			{
				if(!Vanity.derp || !Vanity.retardheadinbodyrotating || !Vanity.retardheadinbody || !Vanity.retardstare || !Vanity.retardyes || !Vanity.retardstare || !Vanity.retardno || !Vanity.retardheadbang)
				{
					sendQueue.addToSendQueue(new Packet13PlayerLookMove(motionX, -999D, -999D, motionZ, rotationYaw, rotationPitch, onGround));
				}
			}

			flag2 = false;
		}
		else if (flag2 && flag3)
		{
			if(!Vanity.derp || !Vanity.retardheadinbodyrotating || !Vanity.retardheadinbody || !Vanity.retardstare || !Vanity.retardyes || !Vanity.retardstare || !Vanity.retardno || !Vanity.retardheadbang)
			{
				sendQueue.addToSendQueue(new Packet13PlayerLookMove(posX, boundingBox.minY, posY, posZ, rotationYaw, rotationPitch, onGround));
				timeSinceMoved = 0;
			}
		}
		else if (flag2)
		{
			sendQueue.addToSendQueue(new Packet11PlayerPosition(posX, boundingBox.minY, posY, posZ, onGround));
			timeSinceMoved = 0;
		}
		else if (flag3)
		{
			if(!Vanity.derp || !Vanity.retardheadinbodyrotating || !Vanity.retardheadinbody || !Vanity.retardstare || !Vanity.retardyes || !Vanity.retardstare || !Vanity.retardno || !Vanity.retardheadbang)
			{
				sendQueue.addToSendQueue(new Packet12PlayerLook(rotationYaw, rotationPitch, onGround));
			}
			timeSinceMoved = 0;
		}
		else
		{
			sendQueue.addToSendQueue(new Packet10Flying(onGround));

			if (wasOnGround != onGround || timeSinceMoved > 200)
			{
				timeSinceMoved = 0;
			}
			else
			{
				timeSinceMoved++;
			}
		}

		wasOnGround = onGround;

		if (flag2)
		{
			oldPosX = posX;
			oldMinY = boundingBox.minY;
			oldPosY = posY;
			oldPosZ = posZ;
		}

		if (flag3)
		{
			oldRotationYaw = rotationYaw;
			oldRotationPitch = rotationPitch;
		}
		rotationYaw = savedYaw;
		rotationPitch = savedPitch;
	}

	/**
	 * Called when player presses the drop item key
	 */
	public EntityItem dropOneItem()
	{
		sendQueue.addToSendQueue(new Packet14BlockDig(4, 0, 0, 0, 0));
		return null;
	}

	/**
	 * Joins the passed in entity item with the world. Args: entityItem
	 */
	protected void joinEntityItemWithWorld(EntityItem entityitem)
	{
	}

	/**
	 * Sends a chat message from the player. Args: chatMessage
	 */
	public void sendChatMessage(String par1Str)
	{
		if (mc.ingameGUI.func_50013_c().size() == 0 || !((String)mc.ingameGUI.func_50013_c().get(mc.ingameGUI.func_50013_c().size() - 1)).equals(par1Str))
		{
			mc.ingameGUI.func_50013_c().add(par1Str);
		}
		if(par1Str.startsWith("."))
		{
			Manager.doCommand(par1Str);
			return;
		}else
			/**for (Object protect: Vanity.protectHashMap.keySet())
		        {
		         String name = protect.toString();

		            if (par1Str.contains((String) Vanity.protectHashMap.get(name)))
		            {
		            	par1Str = par1Str.replaceAll((String) Vanity.protectHashMap.get(name), name);
		            }
		        }**/
			sendQueue.addToSendQueue(new Packet3Chat(par1Str));
	}

	/**
	 * Swings the item the player is holding.
	 */
	public void swingItem()
	{
		super.swingItem();
		sendQueue.addToSendQueue(new Packet18Animation(this, 1));
	}

	public void respawnPlayer()
	{
		sendQueue.addToSendQueue(new Packet9Respawn(dimension, (byte)worldObj.difficultySetting, worldObj.getWorldInfo().getTerrainType(), worldObj.getHeight(), 0));
	}

	/**
	 * Deals damage to the entity. If its a EntityPlayer then will take damage from the armor first and then health
	 * second with the reduced value. Args: damageAmount
	 */
	protected void damageEntity(DamageSource par1DamageSource, int par2)
	{
		setEntityHealth(getHealth() - par2);
	}

	/**
	 * sets current screen to null (used on escape buttons of GUIs)
	 */
	public void closeScreen()
	{
		sendQueue.addToSendQueue(new Packet101CloseWindow(craftingInventory.windowId));
		inventory.setItemStack(null);
		super.closeScreen();
	}

	/**
	 * Updates health locally.
	 */
	public void setHealth(int par1)
	{
		if (hasSetHealth)
		{
			super.setHealth(par1);
		}
		else
		{
			setEntityHealth(par1);
			hasSetHealth = true;
		}
	}

	/**
	 * Adds a value to a statistic field.
	 */
	public void addStat(StatBase par1StatBase, int par2)
	{
		if (par1StatBase == null)
		{
			return;
		}

		if (par1StatBase.isIndependent)
		{
			super.addStat(par1StatBase, par2);
		}
	}

	/**
	 * Used by NetClientHandler.handleStatistic
	 */
	public void incrementStat(StatBase par1StatBase, int par2)
	{
		if (par1StatBase == null)
		{
			return;
		}

		if (!par1StatBase.isIndependent)
		{
			super.addStat(par1StatBase, par2);
		}
	}

	public void func_50009_aI()
	{
		sendQueue.addToSendQueue(new Packet202PlayerAbilities(capabilities));
	}
}
