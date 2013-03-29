package net.minecraft.vanity.hacks;

import net.minecraft.client.Minecraft;
import net.minecraft.src.Entity;
import net.minecraft.src.EntityAnimal;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.EntityMob;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.EntityPlayerSP;
import net.minecraft.src.InventoryPlayer;
import net.minecraft.src.ItemStack;
import net.minecraft.src.Packet16BlockItemSwitch;
import net.minecraft.vanity.main.Vanity;

/**
 * 
 * @author ZCSkid
 * @author pRoDiGy_4D4M (Zen's a skid)
 *
 */
public class AutoSword {
    public static Minecraft Minecraft;
	Minecraft mc;
	
	public static void pull(Entity entity) {
		Minecraft mc = Minecraft.getInstance();
		for(Object entities: Minecraft.theWorld.loadedEntityList)
    	{
        	if (entities instanceof EntityPlayer)
            	{
	                EntityPlayer Entity = (EntityPlayer)entities;
			    	if(!Vanity.protectHashMap.containsKey(Entity.username))
			    	{
		EntityPlayerSP thePlayer = mc.thePlayer;
		float highestToolDamage = 1.0F;
		int bestToolIndex = -1;
		
		for (int index = 0; index < 45; index++) 
		{
			ItemStack itemstack = thePlayer.inventorySlots.getSlot(index).getStack();
			if (itemstack == null) 
			{
				continue;
			}
			
			float itemstackDamage = itemstack.getDamageVsEntity(entity);
			if (itemstackDamage > highestToolDamage) 
			{
				bestToolIndex = index;
				highestToolDamage = itemstackDamage;
			}
		}

		if (bestToolIndex > 35 && bestToolIndex < 45) 
		{
			bestToolIndex -= 36;
			mc.thePlayer.inventory.currentItem = bestToolIndex;
			return;
		}
		
		if (bestToolIndex == 2 || bestToolIndex == -1) 
		{
			return;
		} else 
		{
			mc.playerController.windowClick(0, 38, 0, false, mc.thePlayer);
			mc.playerController.windowClick(0, bestToolIndex, 0, true, mc.thePlayer);
			mc.playerController.windowClick(0, 38, 0, false, mc.thePlayer);
			mc.thePlayer.inventory.currentItem = 2;
			return;
		}
			    	}
            	}
	}
	}
	
	//!Vanity.friends.contains(((EntityPlayer)e).username)
//	public static void pull()
//    {
//    	try
//    	{
//	    	for(int i = 0; i < Minecraft.theWorld.loadedEntityList.size(); i++)
//	        {
//	        	Entity e = (Entity) Minecraft.theWorld.getLoadedEntityList().get(i);
//	        	if(e instanceof EntityPlayer || e instanceof EntityMob || e instanceof EntityAnimal)
//	        	{
//	                if(e != Minecraft.thePlayer && !Vanity.friends.contains(((EntityPlayer)e).username) && !e.isDead && Minecraft.thePlayer.canEntityBeSeen(e) && Minecraft.thePlayer.getDistanceSqToEntity(e) < 36D && e instanceof EntityLiving)
//	                {
//				        EntityPlayerSP entityplayersp = Minecraft.thePlayer;
//				        InventoryPlayer inventoryplayer = entityplayersp.inventory;
//				        int i1 = 1;
//				        boolean flag = false;
//				        int k = -1;
//				        for(int l = 0; l < 9; l++)
//				        {
//				            ItemStack itemstack = inventoryplayer.mainInventory[l];
//				            if(itemstack == null)
//				            {
//				                continue;
//				            }
//				            int j = itemstack.getDamageVsEntity(entityplayersp);
//				            if(j > i1)
//				            {
//				                k = l;
//				                i1 = j;
//				            }
//				        }
//				
//				        if(k < 0)
//				        {
//				            return;
//				        } else
//				        {
//				         Minecraft.getSendQueue().addToSendQueue(new Packet16BlockItemSwitch(k));
//				         Minecraft.thePlayer.inventory.currentItem = k;
//				            return;
//				        }
//			        }
//		        }
//	        }
//    	}catch(Exception e){}
//    }
	}
