package net.minecraft.vanity.hacks;

import net.minecraft.client.Minecraft;
import net.minecraft.src.AxisAlignedBB;
import net.minecraft.src.Entity;
import net.minecraft.src.EntityAnimal;
import net.minecraft.src.EntityMob;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.RenderGlobal;
import net.minecraft.src.RenderManager;
import net.minecraft.src.Tessellator;
import net.minecraft.vanity.main.Vanity;

import org.lwjgl.opengl.GL11;

public class ESP {
    public static Minecraft Minecraft;
	Minecraft mc;
	public static void Tracer(float f)
	{
		try{
		GL11.glPushMatrix();
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glDepthMask(false);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glEnable(GL11.GL_LINE_SMOOTH);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glLineWidth(1.8F);
    	int i = 0;
    	Entity e = (Entity) Minecraft.theWorld.getLoadedEntityList().get(i);
        for (Object entities: Minecraft.theWorld.loadedEntityList)
        {
            if (entities != Minecraft.thePlayer && entities != null)
            {
                if (entities instanceof EntityPlayer && Vanity.tracerplayer && Vanity.tracer)
                {
                    EntityPlayer entity = (EntityPlayer)entities;
                    float distance = Minecraft.thePlayer.getDistanceToEntity(entity);
                    double posX = ((entity.lastTickPosX + (entity.posX - entity.lastTickPosX) * (double)f) - RenderManager.instance.renderPosX);
                    double posY = ((entity.lastTickPosY + (entity.posY - entity.lastTickPosY) * (double)f) - RenderManager.instance.renderPosY);
                    double posZ = ((entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) * (double)f) - RenderManager.instance.renderPosZ);

                    if (Vanity.protectHashMap.containsKey(entity.username))
                    {
                        GL11.glColor3f(0.27F, 0.70F, 0.92F);
                    }
                    else
                    {
                        if (distance <= 6F)
                        {
                            GL11.glColor3f(1.0F, 0.0F, 0.0F);
                        }
                        else if (distance <= 96F)
                        {
                            GL11.glColor3f(1.0F, (distance / 100F), 0.0F);
                        }
                        else if (distance > 96F)
                        {
                            GL11.glColor3f(0.0F, 0.9F, 0.0F);
                        }
                    }

                    GL11.glBegin(GL11.GL_LINE_LOOP);
                    //GL11.glVertex2d(0, 0);
                    GL11.glVertex3d(0, 0, 0);
                    GL11.glVertex3d(posX, posY, posZ);
                    GL11.glEnd();
                    if(Vanity.tracerplayer && Vanity.espbox)
                    {
                    	drawPlayerESP(posX, posY, posZ, entity.username, distance, entity.height, entity.width - 0.25);
                        //RenderGlobal.drawOutlinedBoundingBoxdday(new AxisAlignedBB(d - width, d1 + 0.1, d2 - width, d + width, d1 + height + 0.25, d2 + width));
                    }
                }
                else if ((entities instanceof EntityMob) &&  Vanity.tracermob && Vanity.tracer)
                {
                    EntityMob entity = (EntityMob)entities;
                    float distance = Minecraft.thePlayer.getDistanceToEntity(entity);
                    double posX = ((entity.lastTickPosX + (entity.posX - entity.lastTickPosX) * (double)f) - RenderManager.instance.renderPosX);
                    double posY = ((entity.lastTickPosY + (entity.posY - entity.lastTickPosY) * (double)f) - RenderManager.instance.renderPosY);
                    double posZ = ((entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) * (double)f) - RenderManager.instance.renderPosZ);

                    if (distance <= 6F)
                    {
                        GL11.glColor3f(1.0F, 0.0F, 0.0F);
                    }
                    else if (distance <= 96F)
                    {
                        GL11.glColor3f(1.0F, (distance / 100F), 0.0F);
                    }
                    else if (distance > 96F)
                    {
                        GL11.glColor3f(0.0F, 0.9F, 0.0F);
                    }

                    GL11.glBegin(GL11.GL_LINE_LOOP);
                    //GL11.glVertex2d(0, 0);
                    GL11.glVertex3d(0, 0, 0);
                    GL11.glVertex3d(posX, posY, posZ);
                    GL11.glEnd();
                    if(Vanity.tracermob && Vanity.espbox)
                    {
                    	drawPlayerESP(posX, posY, posZ, null, distance, entity.height, entity.width - 0.25);
                    }
                }
                else if ((entities instanceof EntityAnimal)  &&  Vanity.traceranimal && Vanity.tracer)
                {
                    EntityAnimal entity = (EntityAnimal)entities;
                    float distance = Minecraft.thePlayer.getDistanceToEntity(entity);
                    double posX = ((entity.lastTickPosX + (entity.posX - entity.lastTickPosX) * (double)f) - RenderManager.instance.renderPosX);
                    double posY = ((entity.lastTickPosY + (entity.posY - entity.lastTickPosY) * (double)f) - RenderManager.instance.renderPosY);
                    double posZ = ((entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) * (double)f) - RenderManager.instance.renderPosZ);

                    if (distance <= 6F)
                    {
                        GL11.glColor3f(1.0F, 0.0F, 0.0F);
                    }
                    else if (distance <= 96F)
                    {
                        GL11.glColor3f(1.0F, (distance / 100F), 0.0F);
                    }
                    else if (distance > 96F)
                    {
                        GL11.glColor3f(0.0F, 0.9F, 0.0F);
                    }

                    GL11.glBegin(GL11.GL_LINE_LOOP);
                    //GL11.glVertex2d(0, 0);
                    GL11.glVertex3d(0, 0, 0);
                    GL11.glVertex3d(posX, posY, posZ);
                    GL11.glEnd();
                    if(Vanity.traceranimal && Vanity.espbox)
                    {
                    	drawPlayerESP(posX, posY, posZ, null, distance, entity.height, entity.width);
                    }
                }
            }
        }
        GL11.glDisable(GL11.GL_LINE_SMOOTH);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glDepthMask(true);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glPopMatrix();
	}catch (Exception e) { }
	}
    public static void chestESP(double d, double d1, double d2, float f) {
        GL11.glEnable(3042 /*GL_BLEND*/);
        GL11.glBlendFunc(770, 771);
        GL11.glLineWidth(0.1F);
        GL11.glColor4d(255, 255, 0, 0.15);
        GL11.glDisable(GL11.GL_TEXTURE_2D);  
        GL11.glEnable(GL11.GL_LINE_SMOOTH);
        GL11.glDisable(2929 /*GL_DEPTH_TEST*/);
        GL11.glDepthMask(false);
        drawBoundingBox(new AxisAlignedBB(d, d1, d2, d + 1.0, d1 + 1.0, d2 + 1.0));
        GL11.glColor4d(255, 255, 0, 1);
        RenderGlobal.drawOutlinedBoundingBox(new AxisAlignedBB(d, d1, d2, d + 1.0, d1 + 1.0, d2 + 1.0));
        GL11.glLineWidth(0.3F);
        chestLines(new AxisAlignedBB(d, d1, d2, d + 1.0, d1 + 1.0, d2 + 1.0));
        GL11.glEnable(GL11.GL_TEXTURE_2D); 
        GL11.glEnable(2929 /*GL_DEPTH_TEST*/);
        GL11.glDepthMask(true);
        GL11.glDisable(3042 /*GL_BLEND*/);
    }
	
    public static void chestLines(AxisAlignedBB ax) {
        GL11.glLineWidth(0.1F);
        GL11.glBegin(GL11.GL_LINES);
        GL11.glVertex3d(ax.minX, ax.maxY, ax.minZ);
        GL11.glVertex3d(ax.minX, ax.minY, ax.maxZ);
        GL11.glEnd();
        GL11.glBegin(GL11.GL_LINES);
        GL11.glVertex3d(ax.maxX, ax.maxY, ax.minZ);
        GL11.glVertex3d(ax.maxX, ax.minY, ax.maxZ);
        GL11.glEnd();
        GL11.glBegin(GL11.GL_LINES);
        GL11.glVertex3d(ax.minX, ax.maxY, ax.minZ);
        GL11.glVertex3d(ax.maxX, ax.maxY, ax.maxZ);
        GL11.glEnd();
    }
	
	private static void drawBoundingBox(AxisAlignedBB axisalignedbb)
    {
        Tessellator tessellator = Tessellator.instance;
        tessellator.startDrawingQuads();
        tessellator.addVertex(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.minZ); 
        tessellator.addVertex(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.minZ);
        tessellator.addVertex(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.minZ);
        tessellator.addVertex(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.minZ);
        tessellator.addVertex(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.maxZ);
        tessellator.addVertex(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.maxZ);
        tessellator.addVertex(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.maxZ);
        tessellator.addVertex(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.maxZ);
        tessellator.draw();
        tessellator.startDrawingQuads();
        tessellator.addVertex(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.minZ);
        tessellator.addVertex(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.minZ);
        tessellator.addVertex(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.minZ);
        tessellator.addVertex(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.minZ);
        tessellator.addVertex(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.maxZ);
        tessellator.addVertex(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.maxZ);
        tessellator.addVertex(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.maxZ);
        tessellator.addVertex(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.maxZ);
        tessellator.draw();
        tessellator.startDrawingQuads();      
        tessellator.addVertex(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.minZ);        
        tessellator.addVertex(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.minZ);
        tessellator.addVertex(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.maxZ);
        tessellator.addVertex(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.maxZ);
        tessellator.addVertex(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.minZ);
        tessellator.addVertex(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.maxZ);
        tessellator.addVertex(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.maxZ);
        tessellator.addVertex(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.minZ);
        tessellator.draw();
        tessellator.startDrawingQuads();       
        tessellator.addVertex(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.minZ);        
        tessellator.addVertex(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.minZ);
        tessellator.addVertex(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.maxZ);
        tessellator.addVertex(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.maxZ);
        tessellator.addVertex(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.minZ);
        tessellator.addVertex(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.maxZ);
        tessellator.addVertex(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.maxZ);
        tessellator.addVertex(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.minZ);
        tessellator.draw();
        tessellator.startDrawingQuads();
        tessellator.addVertex(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.minZ); 
        tessellator.addVertex(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.minZ);
        tessellator.addVertex(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.maxZ);
        tessellator.addVertex(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.maxZ);
        tessellator.addVertex(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.maxZ);
        tessellator.addVertex(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.maxZ);
        tessellator.addVertex(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.minZ);
        tessellator.addVertex(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.minZ);
        tessellator.draw();
        tessellator.startDrawingQuads();
        tessellator.addVertex(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.maxZ);
        tessellator.addVertex(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.maxZ);
        tessellator.addVertex(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.minZ);
        tessellator.addVertex(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.minZ);
        tessellator.addVertex(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.minZ);
        tessellator.addVertex(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.minZ);
        tessellator.addVertex(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.maxZ);
        tessellator.addVertex(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.maxZ);
        tessellator.draw();
    } 
	public static void drawPlayerESP(double d, double d1, double d2, String username, float distance, double e, double f)
    {
        //GL11.glPushMatrix();
        if (Vanity.protectHashMap.containsKey(username) && username != null)
        {
            GL11.glColor3f(0.27F, 0.70F, 0.92F);
        }
        else
        {
            if (distance <= 6F)
            {
                GL11.glColor3f(1.0F, 0.0F, 0.0F);
            }
            else
            {
                GL11.glColor3f(0.0F, 0.9F, 0.0F);
            }
        }

        //GL11.glDisable(GL11.GL_TEXTURE_2D);
        //GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glDisable(GL11.GL_DEPTH_TEST);
        GL11.glDepthMask(false);
        //GL11.glLineWidth(1F);
        //GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        //GL11.glEnable(GL11.GL_LINE_SMOOTH);
        RenderGlobal.drawOutlinedBoundingBox(new AxisAlignedBB(d - f, d1 + 0.1, d2 - f, d + f, d1 + e + 0.25, d2 + f));
        //drawOutlinedBoundingBoxdday(new AxisAlignedBB(d - width, d1 + 0.1, d2 - width, d + width, d1 + height + 0.25, d2 + width));
        //GL11.glPushMatrix();
        //GL11.glEnd();
        //GL11.glDisable(GL11.GL_LINE_SMOOTH);
        GL11.glDepthMask(true);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        //GL11.glEnable(GL11.GL_TEXTURE_2D);
        //GL11.glEnable(GL11.GL_LIGHTING);
        //GL11.glPopMatrix();
        //GL11.glEnd();
    }
	public static void drawOutlinedBoundingBoxdday(AxisAlignedBB ax)//AxisAlignedBB ax)
    {
        GL11.glLineWidth(1F);
        GL11.glBegin(GL11.GL_LINES);
        GL11.glVertex3d(ax.minX, ax.minY, ax.minZ);
        GL11.glVertex3d(ax.maxX, ax.minY, ax.maxZ);
        GL11.glEnd();
        GL11.glBegin(GL11.GL_LINES);
        GL11.glVertex3d(ax.maxX, ax.minY, ax.maxZ);
        GL11.glVertex3d(ax.maxX, ax.maxY, ax.minZ);
        GL11.glEnd();
        GL11.glBegin(GL11.GL_LINES);
        GL11.glVertex3d(ax.minX, ax.maxY, ax.minZ);
        GL11.glVertex3d(ax.maxX, ax.maxY, ax.maxZ);
        GL11.glEnd();
        GL11.glBegin(GL11.GL_LINES);
        GL11.glVertex3d(ax.minX, ax.minY, ax.maxZ);
        GL11.glVertex3d(ax.minX, ax.maxY, ax.minZ);
        GL11.glEnd();
        GL11.glBegin(GL11.GL_LINES);
        GL11.glVertex3d(ax.minX, ax.maxY, ax.maxZ);
        GL11.glVertex3d(ax.maxX, ax.minY, ax.maxZ);
        GL11.glEnd();
        GL11.glBegin(GL11.GL_LINES);
        GL11.glVertex3d(ax.minX, ax.maxY, ax.minZ);
        GL11.glVertex3d(ax.maxX, ax.minY, ax.minZ);
        GL11.glEnd();
        RenderGlobal.drawOutlinedBoundingBox(ax);
    }
}
