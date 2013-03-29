package net.minecraft.vanity.hacks;

import java.io.*;
import java.lang.reflect.Field;
import java.util.*;
import net.minecraft.client.Minecraft;
import net.minecraft.src.Block;
import net.minecraft.src.CompressedStreamTools;
import net.minecraft.src.Enchantment;
import net.minecraft.src.EnchantmentModifierLiving;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.EntityPlayerSP;
import net.minecraft.src.EnumMovingObjectType;
import net.minecraft.src.GuiScreen;
import net.minecraft.src.Item;
import net.minecraft.src.ItemAxe;
import net.minecraft.src.ItemPickaxe;
import net.minecraft.src.ItemShears;
import net.minecraft.src.ItemSpade;
import net.minecraft.src.ItemStack;
import net.minecraft.src.ItemSword;
import net.minecraft.src.ItemTool;
import net.minecraft.src.MathHelper;
import net.minecraft.src.NBTTagCompound;
import net.minecraft.src.NBTTagList;
import net.minecraft.src.Potion;
import net.minecraft.src.World;

import net.minecraft.src.IEnchantmentModifier;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

public class mod_AutoSwitch
{
    private int configLineNum;
    public int prevtool;
    public int prevWorld;
    public int toggleKey;
    public int useWorstToggleKey;
    public int useSwordToggleKey;
    public int pulseToggleKey;
    public boolean prevmouse;
    public boolean autoSwitchEnabled = true;
    public boolean pulseOn;
    public boolean prevPulse;
    public boolean debug = false;
    public boolean ignore;
    public boolean usetoolorder;
    public boolean usepickaxeorder;
    public boolean useworst;
    public boolean usesword = false;
    public boolean bindingsSet;
    public final Set pickaxeItems = new HashSet();
    public final Set shearsItems = new HashSet();
    public final Set swordItems = new HashSet();
    public final Set shovelItems = new HashSet();
    public final Set axeItems = new HashSet();
    public final Map idealLookup = new HashMap();
    public final List axeShovelOrder = new ArrayList();
    public final List enchantmentSwordOrder = new ArrayList();
    public final List enchantmentToolOrder = new ArrayList();
    public final EnchantmentModifierLiving damageModifier = new EnchantmentModifierLiving(null);
    public final Minecraft mc;
    public File modFolder;
    public File debugFile;
    public File configFile;
    public File memoryFile;
    public PrintStream debugLogger;
    public static final float WEAK_THRESHOLD = 0.3F;

    public mod_AutoSwitch(Minecraft minecraft)
    {
        configLineNum = 0;
        prevtool = 0;
        prevWorld = 0;
        prevmouse = false;
        autoSwitchEnabled = true;
        pulseOn = false;
        prevPulse = false;
        debug = false;
        ignore = true;
        usetoolorder = false;
        usepickaxeorder = false;
        useworst = false;
        usesword = false;
        bindingsSet = false;
        mc = minecraft;
        load();
    }

    public void load()
    {
      
        debugFile = new File(modFolder, "DEBUG.txt");
        PrintStream printstream = null;

        if (debug)
        {
            try
            {
                printstream = new PrintStream(new FileOutputStream(debugFile));
            }
            catch (FileNotFoundException filenotfoundexception)
            {
                throwException("Unable to open debug output file.", filenotfoundexception);
                debug = false;
                printstream = null;
            }
        }
        else
        {
            printstream = null;
        }

        debugLogger = printstream;
        //ModLoader.setInGUIHook(this, true, false);
    }

  

    public void cleanUpAfterIgnore()
    {
        debug = false;
        pickaxeItems.clear();
        shovelItems.clear();
        axeItems.clear();
        shearsItems.clear();
        swordItems.clear();
        axeShovelOrder.clear();
        idealLookup.entrySet().clear();
    }

    public void writeToMemoryFile(File file, NBTTagCompound nbttagcompound)
    {
        try
        {
            file.delete();
            file.createNewFile();
            FileOutputStream fileoutputstream = new FileOutputStream(file);
            CompressedStreamTools.writeCompressed(nbttagcompound, fileoutputstream);
        }
        catch (IOException ioexception)
        {
            throwException("Couldn't write to memory file.", ioexception);
        }
    }

  

   
    public void setSettingsCompound(NBTTagCompound nbttagcompound)
    {
        autoSwitchEnabled = nbttagcompound.getBoolean("autoSwitchEnabled");
        useworst = nbttagcompound.getBoolean("useworst");
        usesword = nbttagcompound.getBoolean("usesword");
    }

    public void setDataCompound(NBTTagCompound nbttagcompound)
    {
        NBTTagCompound nbttagcompound1 = nbttagcompound.getCompoundTag("Settings");
        setSettingsCompound(nbttagcompound1);
    }


    public boolean onTickInGame(float f, Minecraft minecraft)
    {
        if (!bindingsSet)
        {
            //setKeyBindings();
            bindingsSet = true;
        }

        if (Keyboard.isKeyDown(pulseToggleKey))
        {
            pulseOn = true;
        }
        else
        {
            pulseOn = false;
        }
        boolean flag = Mouse.isButtonDown(minecraft.gameSettings.keyBindAttack.keyCode + 100);
        boolean flag1 = pulseOn ^ autoSwitchEnabled;

        if (!flag && prevmouse || flag && pulseOn ^ prevPulse)
        {
            switchBack();
        }

        if (flag && !prevmouse || flag && pulseOn ^ prevPulse)
        {
            prevtool = minecraft.thePlayer.inventory.currentItem;
        }

        if (flag && flag1)
        {
            clickMouse();
        }

        prevmouse = flag;
        prevPulse = pulseOn;
        prevWorld = minecraft.theWorld != null ? minecraft.theWorld.hashCode() : 0;
        return true;
    }

    public boolean onTickInGUI(float f, Minecraft minecraft, GuiScreen guiscreen)
    {
        if (!bindingsSet)
        {
            //setKeyBindings();
            bindingsSet = true;
        }

        return true;
    }

    public void clickMouse()
    {
        if (mc.currentScreen == null && mc.objectMouseOver != null && mc.objectMouseOver.typeOfHit == EnumMovingObjectType.TILE)
        {
            debug("====================================================");
            int i = mc.objectMouseOver.blockX;
            int j = mc.objectMouseOver.blockY;
            int k = mc.objectMouseOver.blockZ;
            debug("Clicked on a block, x = %d, y = %d, z = %d", new Object[]
                    {
                        Integer.valueOf(i), Integer.valueOf(j), Integer.valueOf(k)
                    });

            try
            {
                switchIfNecessary(mc.theWorld, i, j, k, mc.thePlayer);
            }
            catch (Throwable throwable1)
            {
                debugException(throwable1);
            }
        }
        else if (mc.currentScreen == null && mc.objectMouseOver != null && mc.objectMouseOver.typeOfHit == EnumMovingObjectType.ENTITY)
        {
            debug("====================================================");

            if (!(mc.objectMouseOver.entityHit instanceof EntityLiving))
            {
                return;
            }

            debug("Clicked on an entity, %s", new Object[]
                    {
                        mc.objectMouseOver.entityHit.getEntityString()
                    });

            try
            {
                switchToBestSword(mc.thePlayer, (EntityLiving)mc.objectMouseOver.entityHit);
            }
            catch (Throwable throwable)
            {
                debugException(throwable);
            }
        }
    }

    public void switchToBestSword(EntityPlayer entityplayer, EntityLiving entityliving)
    {
        ItemStack aitemstack[] = entityplayer.inventory.mainInventory;
        int i;

        for (i = 0; i < 9 && aitemstack[i] == null; i++) { }

        if (i == 9)
        {
            return;
        }

        String as[] = new String[9];

        for (int j = 0; j < 9; j++)
        {
            String s = aitemstack[j] != null ? aitemstack[j].getItem().getItemName() : "Nothing";
            as[j] = s;
            debug("Hotbar slot %d contains item %s", new Object[]
                    {
                        Integer.valueOf(j), s
                    });
        }

        debug("Current item is %d", new Object[]
                {
                    Integer.valueOf(entityplayer.inventory.currentItem)
                });
        debug("Setting possible best weapon to %d, which is %s", new Object[]
                {
                    Integer.valueOf(i), aitemstack[i].getItem().getItemName()
                });

        for (int k = i + 1; k < 9; k++)
        {
            if (aitemstack[k] == null)
            {
                debug("Not checking empty slot %d", new Object[]
                        {
                            Integer.valueOf(k)
                        });
                continue;
            }

            Item item = aitemstack[k].getItem();
            debug("Checking if weapon %d, which is %s, is better than %d, which is %s", new Object[]
                    {
                        Integer.valueOf(k), as[k], Integer.valueOf(i), as[i]
                    });

            if (isSwordBetter(aitemstack[k], aitemstack[i], entityliving))
            {
                debug("Changing possible best weapon because weapon is better.");
                i = k;
            }
        }

        switchToolsToN(i);
    }

    public boolean isSwordBetter(ItemStack itemstack, ItemStack itemstack1, EntityLiving entityliving)
    {
        boolean flag = entityliving instanceof EntityPlayer;
        int i = itemstack1.getItem().getDamageVsEntity(entityliving);
        int j = itemstack.getItem().getDamageVsEntity(entityliving);

        if (mc.thePlayer.isPotionActive(Potion.damageBoost))
        {
            i += 3 << mc.thePlayer.getActivePotionEffect(Potion.damageBoost).getAmplifier();
            j += 3 << mc.thePlayer.getActivePotionEffect(Potion.damageBoost).getAmplifier();
        }

        if (mc.thePlayer.isPotionActive(Potion.weakness))
        {
            i -= 2 << mc.thePlayer.getActivePotionEffect(Potion.weakness).getAmplifier();
            j -= 2 << mc.thePlayer.getActivePotionEffect(Potion.weakness).getAmplifier();
        }

        boolean flag1 = mc.thePlayer.fallDistance > 0.0F && !mc.thePlayer.onGround && !mc.thePlayer.isOnLadder() && !mc.thePlayer.isInWater() && !mc.thePlayer.isPotionActive(Potion.blindness) && mc.thePlayer.ridingEntity == null;

        if (flag1)
        {
            i += i / 4 + 1;
            j += j / 4 + 1;
        }

        i += getEnchantmentModifierLiving(itemstack1, entityliving);
        j += getEnchantmentModifierLiving(itemstack, entityliving);
        int k = ceil_double((double)entityliving.health / (double)i);
        int l = ceil_double((double)entityliving.health / (double)j);

        if (!flag)
        {
            if (l < k)
            {
                debug("Switching because new hits are fewer.");
                debug("New hits are %d, old hits are %d", new Object[]
                        {
                            Integer.valueOf(l), Integer.valueOf(k)
                        });
                return true;
            }

            if (l > k)
            {
                debug("Not switching because old hits are fewer.");
                debug("New hits are %d, old hits are %d", new Object[]
                        {
                            Integer.valueOf(l), Integer.valueOf(k)
                        });
                return false;
            }

            for (int i1 = 0; i1 < enchantmentSwordOrder.size(); i1++)
            {
                int k1 = ((Integer)enchantmentSwordOrder.get(i1)).intValue();
                int i2 = getEnchantmentLevel(k1, itemstack1);
                int j2 = getEnchantmentLevel(k1, itemstack);

                if (j2 > i2)
                {
                    debug("Switching because new enchantment, %s, is more than old enchantment, %s", new Object[]
                            {
                                Enchantment.enchantmentsList[k1].getTranslatedName(j2), Enchantment.enchantmentsList[k1].getTranslatedName(i2)
                            });
                    return true;
                }

                if (j2 < i2)
                {
                    debug("Not switching because new enchantment, %s, is less than old enchantment, %s", new Object[]
                            {
                                Enchantment.enchantmentsList[k1].getTranslatedName(j2), Enchantment.enchantmentsList[k1].getTranslatedName(i2)
                            });
                    return false;
                }
            }

            if (j > i)
            {
                debug("Switching because new damage is more.");
                debug("New damage is %d, old damage is %d", new Object[]
                        {
                            Integer.valueOf(j), Integer.valueOf(i)
                        });
                return true;
            }

            if (j < i)
            {
                debug("Not witching because old damage is more.");
                debug("New damage is %d, old damage is %d", new Object[]
                        {
                            Integer.valueOf(j), Integer.valueOf(i)
                        });
                return false;
            }
        }
        else
        {
            if (j > i)
            {
                debug("Switching because new damage is more.");
                debug("New damage is %d, old damage is %d", new Object[]
                        {
                            Integer.valueOf(j), Integer.valueOf(i)
                        });
                return true;
            }

            if (j < i)
            {
                debug("Not witching because old damage is more.");
                debug("New damage is %d, old damage is %d", new Object[]
                        {
                            Integer.valueOf(j), Integer.valueOf(i)
                        });
                return false;
            }

            int j1 = getEnchantmentLevel(Enchantment.fireAspect.effectId, itemstack1);
            int l1 = getEnchantmentLevel(Enchantment.fireAspect.effectId, itemstack);

            if (l1 > j1)
            {
                debug("Switching because new fire aspect level, %d, is more than old fire aspect level, %d.", new Object[]
                        {
                            Integer.valueOf(l1), Integer.valueOf(j1)
                        });
                return true;
            }

            if (l1 < j1)
            {
                debug("Not switching because new fire aspect level, %d, is less than old fire aspect level, %d.", new Object[]
                        {
                            Integer.valueOf(l1), Integer.valueOf(j1)
                        });
                return false;
            }

            j1 = getEnchantmentLevel(Enchantment.knockback.effectId, itemstack1);
            l1 = getEnchantmentLevel(Enchantment.knockback.effectId, itemstack);

            if (l1 > j1)
            {
                debug("Switching because new knockback level, %d, is more than old knockback level, %d.", new Object[]
                        {
                            Integer.valueOf(l1), Integer.valueOf(j1)
                        });
                return true;
            }

            if (l1 < j1)
            {
                debug("Not switching because new knockback level, %d, is less than old knock level, %d.", new Object[]
                        {
                            Integer.valueOf(l1), Integer.valueOf(j1)
                        });
                return false;
            }

            if (l < k)
            {
                debug("Switching because new hits are fewer.");
                debug("New hits are %d, old hits are %d", new Object[]
                        {
                            Integer.valueOf(l), Integer.valueOf(k)
                        });
                return true;
            }

            if (l > k)
            {
                debug("Not switching because old hits are fewer.");
                debug("New hits are %d, old hits are %d", new Object[]
                        {
                            Integer.valueOf(l), Integer.valueOf(k)
                        });
                return false;
            }
        }

        if ((itemstack.getItem() instanceof ItemSword) && !(itemstack1.getItem() instanceof ItemSword))
        {
            debug("Switching because new item is sword and old item is not.");
            return true;
        }
        else
        {
            debug("Not switching because weapons are equal.");
            return false;
        }
    }

    public void switchIfNecessary(World world, int i, int j, int k, EntityPlayer entityplayer)
    {
        ItemStack aitemstack[] = entityplayer.inventory.mainInventory;
        Block block = Block.blocksList[world.getBlockId(i, j, k)];

        if (block != null);

        debug("Testing vs block %s", new Object[]
                {
                    block.getBlockName()
                });
        String as[] = new String[9];

        for (int l = 0; l < 9; l++)
        {
            as[l] = aitemstack[l] != null ? aitemstack[l].getItem().getItemName() : "Nothing";
            debug("Hotbar slot %d contains item %s", new Object[]
                    {
                        Integer.valueOf(l), as[l]
                    });
        }

        int i1 = -1;
        debug("Setting possible best tool to %d, which is %s", new Object[]
                {
                    Integer.valueOf(i1), as[0]
                });

        for (int j1 = 0; j1 < 9; j1++)
        {
            debug("Checking if tool %d, which is %s, is better than %d, which is %s", new Object[]
                    {
                        Integer.valueOf(j1), as[j1], Integer.valueOf(i1), i1 != -1 ? as[i1] : "Nothing"
                    });

            if (isToolBetter(aitemstack[j1], i1 != -1 ? aitemstack[i1] : null, block, i1 == -1))
            {
                debug("Changing possible best tool because tool is better.");
                i1 = j1;
            }
        }

        if (i1 != -1)
        {
            switchToolsToN(i1);
        }
        else
        {
            debug("Not switching because nothing found.");
        }
    }

    public boolean isToolBetter(ItemStack itemstack, ItemStack itemstack1, Block block, boolean flag)
    {
        if (block.getHardness() == -1F)
        {
            debug("Not switching because block is unbreakable.");
            return false;
        }

        int i = getEnchantmentLevel(Enchantment.efficiency.effectId, itemstack);
        int j = getEnchantmentLevel(Enchantment.efficiency.effectId, itemstack1);
        float f = i <= 0 ? 0 : i * i + 1;
        float f1 = j <= 0 ? 0 : j * j + 1;
        float f2 = itemstack != null ? itemstack.getStrVsBlock(block) : -1F;
        float f3 = itemstack1 != null ? itemstack1.getStrVsBlock(block) : -1F;
        float f4 = f2 + f;
        float f5 = f3 + f1;
        List list = (List)idealLookup.get(Integer.valueOf(block.blockID));
        Item item = itemstack != null ? itemstack.getItem() : null;
        Item item1 = itemstack1 != null ? itemstack1.getItem() : null;
        debug("New efficiency is: %f, old efficiency is: %f", new Object[]
                {
                    Float.valueOf(f4), Float.valueOf(f5)
                });
        debug("New strength is: %f, old strength is %f", new Object[]
                {
                    Float.valueOf(f2), Float.valueOf(f3)
                });

        if (axeItems.contains(Integer.valueOf(block.blockID)) && (item1 instanceof ItemAxe) && !(item instanceof ItemAxe))
        {
            debug("Not switching because new item is not an axe and old item is an axe and block is in axe blocks override.");
            return false;
        }

        if (axeItems.contains(Integer.valueOf(block.blockID)) && !(item1 instanceof ItemAxe) && (item instanceof ItemAxe))
        {
            debug("Switching because new item is an axe and old item is not an axe and block is in axe blocks override.");
            return true;
        }

        if (shovelItems.contains(Integer.valueOf(block.blockID)) && (item1 instanceof ItemSpade) && !(item instanceof ItemSpade))
        {
            debug("Not switching because new item is not a shovel and old item is a shovel and block is in shovel blocks override.");
            return false;
        }

        if (shovelItems.contains(Integer.valueOf(block.blockID)) && !(item1 instanceof ItemSpade) && (item instanceof ItemSpade))
        {
            debug("Switching because new item is a shovel and old item is not a shovel and block is in shovel blocks override.");
            return true;
        }

        if (shearsItems.contains(Integer.valueOf(block.blockID)) && (item1 instanceof ItemShears) && !(item instanceof ItemShears))
        {
            debug("Not switching because new item is not a shears and old item is a shears and block is in shears blocks override.");
            return false;
        }

        if (shearsItems.contains(Integer.valueOf(block.blockID)) && !(item1 instanceof ItemShears) && (item instanceof ItemShears))
        {
            debug("Switching because new item is a shears and old item is not a shears and block is in shears blocks override.");
            return true;
        }

        if (pickaxeItems.contains(Integer.valueOf(block.blockID)) && (item1 instanceof ItemPickaxe) && !(item instanceof ItemPickaxe))
        {
            debug("Not switching because new item is not a pickaxe and old item is a pickaxe and block is in pickaxe blocks override.");
            return false;
        }

        if (pickaxeItems.contains(Integer.valueOf(block.blockID)) && !(item1 instanceof ItemPickaxe) && (item instanceof ItemPickaxe))
        {
            debug("Switching because new item is a pickaxe and old item is not a pickaxe and block is in pickaxe blocks override.");
            return true;
        }

        if (swordItems.contains(Integer.valueOf(block.blockID)) && (item1 instanceof ItemSword) && !(item instanceof ItemSword))
        {
            debug("Not switching because new item is not a sword and old item is a sword and block is in sword blocks override.");
            return false;
        }

        if (swordItems.contains(Integer.valueOf(block.blockID)) && !(item1 instanceof ItemSword) && (item instanceof ItemSword))
        {
            debug("Switching because new item is a sword and old item is not a sword and block is in sword blocks override.");
            return true;
        }

        if (itemstack != null && block.getHardness() < 0.3F && f4 <= 1.5F && item.isDamageable()) //block.hardness
        {
            debug("Not switching because block is weak and new item is damageable and weak.");
            return false;
        }

        if (itemstack1 != null && block.getHardness() < 0.3F && f5 <= 1.5F && item1.isDamageable()) //
        {
            debug("Switching because block is weak and old item is damageable and weak.");
            return true;
        }

        if (itemstack != null && f4 == 1.0F && item.isDamageable())
        {
            debug("Not switching because new item is wrong for the block and new item is damageable.");
            return false;
        }

        if (itemstack1 != null && f5 == 1.0F && item1.isDamageable())
        {
            debug("Switching because old item is wrong for the block and old item is damageable.");
            return true;
        }

        if (itemstack != null && block.getHardness() == 0.0F && item.isDamageable())
        {
            debug("Not switching because new item is damageable and block is instantmine");
            return false;
        }

        if (itemstack1 != null && block.getHardness() == 0.0F && item1.isDamageable())
        {
            debug("Switching because old item is damageable and block is instantmine");
            return true;
        }

        if (flag)
        {
            debug("Switching because first possibly viable tool.");
            return true;
        }

        if (itemstack == null)
        {
            debug("Not switching because new item is Nothing");
            return false;
        }

        if (itemstack1 == null)
        {
            debug("Switching because old item is Nothing");
            return true;
        }

        if (usepickaxeorder && list != null)
        {
            debug("Using pickaxe order.");

            if ((item instanceof ItemPickaxe) && (item1 instanceof ItemPickaxe))
            {
                int k = list.indexOf(Integer.valueOf(item.shiftedIndex));
                int l1 = list.indexOf(Integer.valueOf(item1.shiftedIndex));

                if (k < l1)
                {
                    debug("Switching because new pickaxe is overridden as better than old pickaxe.");
                    return true;
                }

                if (k > l1)
                {
                    debug("Not switching because new pickaxe is overridden as worse than old pickaxe.");
                    return false;
                }
            }
            else if ((item instanceof ItemSword) && (item1 instanceof ItemPickaxe))
            {
                int l = list.indexOf(Integer.valueOf(-1));
                int i2 = list.indexOf(Integer.valueOf(item1.shiftedIndex));

                if (l < i2)
                {
                    debug("Switching because new sword is overridden as better than old pickaxe.");
                    return true;
                }

                if (l > i2)
                {
                    debug("Not switching because new sword is overridden as worse than old pickaxe.");
                    return false;
                }
            }
            else if ((item instanceof ItemPickaxe) && (item1 instanceof ItemSword))
            {
                int i1 = list.indexOf(Integer.valueOf(item.shiftedIndex));
                int j2 = list.indexOf(Integer.valueOf(-1));

                if (i1 < j2)
                {
                    debug("Switching because new pickaxe is overridden as better than old sword.");
                    return true;
                }

                if (i1 > j2)
                {
                    debug("Not switching because new pickaxe is overridden as worse than old sword.");
                    return false;
                }
            }
        }

        if (usetoolorder)
        {
            debug("Use tool order is on.");

            if ((item instanceof ItemTool) && (item1 instanceof ItemTool) && (!(item instanceof ItemPickaxe) || !(item1 instanceof ItemPickaxe)) && areToolsSameSubclass((ItemTool)item, (ItemTool)item1))
            {
                int j1 = axeShovelOrder.indexOf(((ItemTool)item).toolMaterial);
                int k2 = axeShovelOrder.indexOf(((ItemTool)item1).toolMaterial);

                if (j1 < k2)
                {
                    debug("Switching because new tool is same tool as old tool and new tool is overridden as better.");
                    return true;
                }

                if (j1 > k2)
                {
                    debug("Not switching because new tool is same tool as old tool and new tool is overridden as worse.");
                    return false;
                }
            }
        }

        if (!block.blockMaterial.isHarvestable() && item.canHarvestBlock(block) && !item1.canHarvestBlock(block))
        {
            debug("Switching because new tool can harvest material and old tool can't.");
            return true;
        }

        if (!block.blockMaterial.isHarvestable() && !item.canHarvestBlock(block) && item1.canHarvestBlock(block))
        {
            debug("Not switching because old tool can harvest material and new tool can't.");
            return false;
        }

        if (useworst)
        {
            debug("Useworst is on.");

            if (f2 > 1.5F && f3 <= 1.5F)
            {
                debug("Switching because new item is special to block and old item isn't.");
                return true;
            }

            if (f2 <= 1.5F && f3 > 1.5F)
            {
                debug("Not switching because old item is special to block and new item isn't.");
                return false;
            }

            if (f2 > 1.5F && f3 > 1.5F)
            {
                if (f4 < f5)
                {
                    debug("Switching because new item is worse than old item and use worst is on.");
                    return true;
                }

                if (f4 > f5)
                {
                    debug("Not switching because new item is better than old item and use worst is on.");
                    return false;
                }
            }
        }

        if (!usesword)
        {
            debug("No sword is on.");

            if (f2 == 1.5F && f3 < 1.5F)
            {
                debug("Not switching because new item is sword and no sword is on.");
                return false;
            }

            if (f3 == 1.5F && f2 < 1.5F)
            {
                debug("Switching because old item is sword and no sword is on.");
                return true;
            }
        }

        if (f4 > f5)
        {
            debug("Switching because new tool is stronger.");
            return true;
        }

        if (f4 < f5)
        {
            debug("Not switching because old tool is stronger.");
            return false;
        }

        for (int k1 = 0; k1 < enchantmentToolOrder.size(); k1++)
        {
            int l2 = ((Integer)enchantmentToolOrder.get(k1)).intValue();
            int i3 = getEnchantmentLevel(l2, itemstack1);
            int j3 = getEnchantmentLevel(l2, itemstack);

            if (useworst)
            {
                if (j3 < i3)
                {
                    debug("Switching because new enchantment, %s, is less than old enchantment, %s, and use worst is on.", new Object[]
                            {
                                Enchantment.enchantmentsList[l2].getTranslatedName(j3), Enchantment.enchantmentsList[l2].getTranslatedName(i3)
                            });
                    return true;
                }

                if (j3 > i3)
                {
                    debug("Not switching because new enchantment, %s, is more than old enchantment, %s, and use worst is on.", new Object[]
                            {
                                Enchantment.enchantmentsList[l2].getTranslatedName(j3), Enchantment.enchantmentsList[l2].getTranslatedName(i3)
                            });
                    return false;
                }

                continue;
            }

            if (j3 > i3)
            {
                debug("Switching because new enchantment, %s, is more than old enchantment, %s", new Object[]
                        {
                            Enchantment.enchantmentsList[l2].getTranslatedName(j3), Enchantment.enchantmentsList[l2].getTranslatedName(i3)
                        });
                return true;
            }

            if (j3 < i3)
            {
                debug("Not switching because new enchantment, %s, is less than old enchantment, %s", new Object[]
                        {
                            Enchantment.enchantmentsList[l2].getTranslatedName(j3), Enchantment.enchantmentsList[l2].getTranslatedName(i3)
                        });
                return false;
            }
        }

        debug("Not switching because tools are equal.");
        return false;
    }

    public boolean areToolsSameSubclass(ItemTool itemtool, ItemTool itemtool1)
    {
        Class class1 = itemtool.getClass();
        Class class2 = itemtool1.getClass();

        for (; !class1.equals(net.minecraft.src.ItemTool.class) && !class1.getSuperclass().equals(net.minecraft.src.ItemTool.class); class1 = class1.getSuperclass()) { }

        for (; !class2.equals(net.minecraft.src.ItemTool.class) && !class2.getSuperclass().equals(net.minecraft.src.ItemTool.class); class2 = class2.getSuperclass()) { }

        return class1.equals(class2);
    }

    public void switchToolsToN(int i)
    {
        EntityPlayerSP entityplayersp = mc.thePlayer;
        ((EntityPlayer)(entityplayersp)).inventory.currentItem = i;
        String s = ((EntityPlayer)(entityplayersp)).inventory.mainInventory[i] != null ? ((EntityPlayer)(entityplayersp)).inventory.mainInventory[i].getItem().getItemName() : "Nothing";
        debug("Switching tools to %d, which is %s", new Object[]
                {
                    Integer.valueOf(i), s
                });
    }

    public void switchBack()
    {
        if (mc.thePlayer.inventory.currentItem != prevtool)
        {
            mc.thePlayer.inventory.currentItem = prevtool;
            debug("Switching tools back");
        }
    }

    public int getEnchantmentModifierLiving(ItemStack itemstack, EntityLiving entityliving)
    {
        damageModifier.livingModifier = 0;
        damageModifier.entityLiving = entityliving;
        applyEnchantmentModifier(damageModifier, itemstack);
        return damageModifier.livingModifier;
    }

    public void applyEnchantmentModifier(IEnchantmentModifier ienchantmentmodifier, ItemStack itemstack)
    {
        if (itemstack == null)
        {
            return;
        }

        NBTTagList nbttaglist = itemstack.getEnchantmentTagList();

        if (nbttaglist == null)
        {
            return;
        }

        for (int i = 0; i < nbttaglist.tagCount(); i++)
        {
            short word0 = ((NBTTagCompound)nbttaglist.tagAt(i)).getShort("id");
            short word1 = ((NBTTagCompound)nbttaglist.tagAt(i)).getShort("lvl");

            if (Enchantment.enchantmentsList[word0] != null)
            {
                ienchantmentmodifier.calculateModifier(Enchantment.enchantmentsList[word0], word1);
            }
        }
    }

    public int getEnchantmentLevel(int i, ItemStack itemstack)
    {
        if (itemstack == null)
        {
            return 0;
        }

        NBTTagList nbttaglist = itemstack.getEnchantmentTagList();

        if (nbttaglist == null)
        {
            return 0;
        }

        for (int j = 0; j < nbttaglist.tagCount(); j++)
        {
            short word0 = ((NBTTagCompound)nbttaglist.tagAt(j)).getShort("id");
            short word1 = ((NBTTagCompound)nbttaglist.tagAt(j)).getShort("lvl");

            if (word0 == i)
            {
                return word1;
            }
        }

        return 0;
    }

    public int getNumEnchantments(ItemStack itemstack)
    {
        if (itemstack == null)
        {
            return 0;
        }

        NBTTagList nbttaglist = itemstack.getEnchantmentTagList();

        if (nbttaglist == null)
        {
            return 0;
        }
        else
        {
            return nbttaglist.tagCount();
        }
    }

    public static int ceil_double(double d)
    {
        return -MathHelper.floor_double(-d);
    }

    public void debug(String s)
    {
        debug("%s", new Object[]
                {
                    s
                });
    }

    public void debug(String s, Object aobj[])
    {
        if (debug)
        {
            String s1 = String.format(s, aobj);
            debugLogger.println(s1);
            debugLogger.flush();
        }
    }

    public void debugException(Throwable throwable)
    {
        throwable.printStackTrace(System.err);

        if (debug)
        {
            throwable.printStackTrace(debugLogger);
            debugLogger.flush();
        }
    }

    public void throwException(String s, Throwable throwable)
    {
        //ModLoader.throwException(s, throwable);
    }

    public String getVersion()
    {
        return "AutoSwitch v1.9.0 for Minecraft 1.2.5";
    }

    protected void finalize() throws Throwable
    {
        if (debug)
        {
            debugLogger.close();
        }
    }
}