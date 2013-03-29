package net.minecraft.vanity.hacks;

import net.minecraft.client.Minecraft;
import net.minecraft.src.MovingObjectPosition;
import net.minecraft.src.NetClientHandler;
import net.minecraft.src.Packet14BlockDig;
import net.minecraft.src.PlayerCapabilities;
import net.minecraft.src.PlayerControllerMP;
import net.minecraft.src.Vec3D;
import net.minecraft.vanity.main.Vanity;

public class Nuker 
{
    public NetClientHandler sendQueue;
    private static double posX;
    private static double posY;
    private static double posZ;
    private float oldRotationYaw;
    private float oldRotationPitch;
	public static void StartBotter(int X, int Y, int Z, int BlockHit)
	{
		//HitPos = objectMouseOver.hitVec;
		int HitX = (int) Math.round(X);
		int HitY = (int) Math.round(Y);
		int HitZ = (int) Math.round(Z);
		
		HitPos = Vec3D.createVectorHelper(HitX, HitY, HitZ);
		BlockHitID = BlockHit;
		if(!PlayerControllerMP.creativeMode)
		{
			return;
		}
		run();
	}
	
	public static void run()
	{		
		int MaxHeight = 5;
		int MinHeight = -5;

		int MaxWidth = 5;
		int MinWidth = -5;
		
		for(int Y = MaxHeight; Y >= MinHeight; Y--)
		{
			for(int Z = MinWidth; Z <= MaxWidth; Z++)
			{
				for(int X = MinWidth; X <= MaxWidth; X++)
				{
					int PosX = (int) Math.round(HitPos.xCoord + X);
					int PosY = (int) Math.round(HitPos.yCoord + Y);
					int PosZ = (int) Math.round(HitPos.zCoord + Z);
					
					int BlockID = mc.theWorld.getBlockId(PosX, PosY, PosZ);
					
					if(BlockID <= 0)
					{
						continue;
					} 
					mc.getSendQueue().addToSendQueue(new Packet14BlockDig(0, PosX, PosY, PosZ, 1));
					mc.getSendQueue().addToSendQueue(new Packet14BlockDig(2, PosX, PosY, PosZ, 1));
				}
			}
		}
	}
	public PlayerCapabilities PC;
	public static Minecraft mc = Minecraft.theMinecraft;
	public static Vec3D HitPos;
	public static int BlockHitID = 0;
    public static MovingObjectPosition objectMouseOver;
}
