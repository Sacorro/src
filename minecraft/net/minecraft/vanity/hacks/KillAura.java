package net.minecraft.vanity.hacks;

import net.minecraft.client.Minecraft;
import net.minecraft.src.Entity;
import net.minecraft.src.EntityAnimal;
import net.minecraft.src.EntityArrow;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.EntityMob;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.ItemStack;
import net.minecraft.src.MathHelper;
import net.minecraft.src.Packet12PlayerLook;
import net.minecraft.src.Packet18Animation;
import net.minecraft.vanity.main.Vanity;

public class KillAura {
	public static void KillAuraPlayer (Minecraft mc){
		if(Vanity.killauraplayer && Vanity.killaura)
        {
			try{
            for(int i = 0; i < mc.theWorld.loadedEntityList.size(); i++)
            {
            	Entity e = (Entity) mc.theWorld.getLoadedEntityList().get(i);
            	if(e instanceof EntityPlayer)
            	{
	                if(e != mc.thePlayer && !Vanity.protectHashMap.containsKey(((EntityPlayer)e).username) && !e.isDead && mc.thePlayer.canEntityBeSeen(e) && mc.thePlayer.getDistanceSqToEntity(e) < 25D && e instanceof EntityLiving)
	                {
	                	mc.playerController.attackEntity(mc.thePlayer, e);
	        		    mc.thePlayer.swingItem();
	        		   // if(Vanity.killauralock)
	        		    {
	        		        double x = e.posX - mc.thePlayer.posX;
	        		        double y = e.posY - mc.thePlayer.posY;
	        		        double z = e.posZ - mc.thePlayer.posZ;
	        		        double d1 = (mc.thePlayer.posY + (double)mc.thePlayer.getEyeHeight()) - (e.posY + (double)e.getEyeHeight());
	        		        double d3 = MathHelper.sqrt_double(x * x + z * z);
	        		        float f = (float) ((Math.atan2(z,  x) * 180D) / Math.PI) - 90F;
	        		        float f1 = (float) (((Math.atan2(d1, d3) * 180D) / Math.PI));
	        		        mc.getSendQueue().addToSendQueue(new Packet12PlayerLook(f, f1, true));
	        		    }
	                }
            	}
            }
        }catch(Exception e) { }
    }
    }
    
    
     public void angle(Entity entity){
        double Yaw = 0;
        double distancex = entity.posX - Minecraft.theMinecraft.thePlayer.posX;
        double distancez = entity.posZ - Minecraft.theMinecraft.thePlayer.posZ;
        double distancey = (entity.posY - Minecraft.theMinecraft.thePlayer.posY) + 1.2D;
        if(distancez > 0.0D && distancex > 0.0D)
        {
            Yaw = Math.toDegrees(-Math.atan(distancex / distancez));
        } else
            if(distancez > 0.0D && distancex < 0.0D)
            {
                Yaw = Math.toDegrees(-Math.atan(distancex / distancez));
            } else
                if(distancez < 0.0D && distancex > 0.0D)
                {
                    Yaw = -90D + Math.toDegrees(Math.atan(distancez / distancex));
                } else
                    if(distancez < 0.0D && distancex < 0.0D)
                    {
                        Yaw = 90D + Math.toDegrees(Math.atan(distancez / distancex));
                    }
        double d = Math.sqrt(distancez * distancez + distancex * distancex);
        double Pitch = -Math.toDegrees(Math.atan(distancey / d));
        Vanity.mc.getSendQueue().addToSendQueue( new net.minecraft.src.Packet13PlayerLookMove(Vanity.mc.thePlayer.posX , Vanity.mc.thePlayer.boundingBox.minY, Vanity.mc.thePlayer.posY, Vanity.mc.thePlayer.posZ, (int)Yaw, (int)Pitch, Vanity.mc.thePlayer.onGround));
    }
    
    public static void KillAuraMob (Minecraft mc){
		if(Vanity.killauramob && Vanity.killaura)
        {
	    	try{
            for(int i = 0; i < mc.theWorld.loadedEntityList.size(); i++)
            {
            	Entity e = (Entity) mc.theWorld.getLoadedEntityList().get(i);
            	if(e instanceof EntityMob)
            	{
	                if(e != mc.thePlayer && !e.isDead && mc.thePlayer.canEntityBeSeen(e) && mc.thePlayer.getDistanceSqToEntity(e) < 25D && e instanceof EntityLiving)
	                {
	                	mc.playerController.attackEntity(mc.thePlayer, e);
	                    mc.thePlayer.swingItem();
	        		    if(Vanity.killauralock)
	        		    {
	        		        double x = e.posX - mc.thePlayer.posX;
	        		        double y = e.posY - mc.thePlayer.posY;
	        		        double z = e.posZ - mc.thePlayer.posZ;
	        		        double d1 = (mc.thePlayer.posY + (double)mc.thePlayer.getEyeHeight()) - (e.posY + (double)e.getEyeHeight());
	        		        double d3 = MathHelper.sqrt_double(x * x + z * z);
	        		        float f = (float) ((Math.atan2(z,  x) * 180D) / Math.PI) - 90F;
	        		        float f1 = (float) (((Math.atan2(d1, d3) * 180D) / Math.PI));
	        		        mc.getSendQueue().addToSendQueue(new Packet12PlayerLook(f, f1, true));
	        		    }
	                }
            	}
            }
        }catch(Exception e) { }
    }
    }
    
    
    public static void KillAuraAnimal (Minecraft mc){
		if(Vanity.killauraanimal && Vanity.killaura)
        {
	    	try{
            for(int i = 0; i < mc.theWorld.loadedEntityList.size(); i++)
            {
            	Entity e = (Entity) mc.theWorld.getLoadedEntityList().get(i);
            	if(e instanceof EntityAnimal)
            	{
	                if(e != mc.thePlayer && !e.isDead && mc.thePlayer.canEntityBeSeen(e) && mc.thePlayer.getDistanceSqToEntity(e) < 25D && e instanceof EntityLiving)
	                {
	                	mc.playerController.attackEntity(mc.thePlayer, e);
	                    mc.thePlayer.swingItem();
	        		    if(Vanity.killauralock)
	        		    {
	        		        double x = e.posX - mc.thePlayer.posX;
	        		        double y = e.posY - mc.thePlayer.posY;
	        		        double z = e.posZ - mc.thePlayer.posZ;
	        		        double d1 = (mc.thePlayer.posY + (double)mc.thePlayer.getEyeHeight()) - (e.posY + (double)e.getEyeHeight());
	        		        double d3 = MathHelper.sqrt_double(x * x + z * z);
	        		        float f = (float) ((Math.atan2(z,  x) * 180D) / Math.PI) - 90F;
	        		        float f1 = (float) (((Math.atan2(d1, d3) * 180D) / Math.PI));
	        		        mc.getSendQueue().addToSendQueue(new Packet12PlayerLook(f, f1, true));
	        		    }
	                }
            	}
            }
        }catch(Exception e) { }
    }
    }
}

    
   
   

   