package net.minecraft.vanity.hacks;

import net.minecraft.src.Entity;

public class Location
{
    public String locationName;
    public double posX;
    public double posY;
    public double posZ;
    public float rotationYaw;
    public float rotationPitch;

    public Location(double d, double d1, double d2, float f, float f1)
    {
        this(d, d1, d2, f, f1, "");
    }

    public Location(double d, double d1, double d2, float f, float f1, String s)
    {
        locationName = s;
        posX = d;
        posY = d1;
        posZ = d2;
        rotationYaw = f;
        rotationPitch = f1;
    }

    public Location(Entity entity)
    {
        this(entity.posX, entity.posY, entity.posZ, entity.rotationYaw, entity.rotationPitch);
    }

    public Location(Entity entity, String s)
    {
        this(entity.posX, entity.posY, entity.posZ, entity.rotationYaw, entity.rotationPitch, s);
    }
}
