package net.minecraft.src;

import net.minecraft.client.Minecraft;
import org.lwjgl.opengl.GL11;

public class GuiBrewingStand extends GuiContainer
{
    private TileEntityBrewingStand field_40217_h;

    public GuiBrewingStand(InventoryPlayer par1InventoryPlayer, TileEntityBrewingStand par2TileEntityBrewingStand)
    {
        super(new ContainerBrewingStand(par1InventoryPlayer, par2TileEntityBrewingStand));
        field_40217_h = par2TileEntityBrewingStand;
    }

    /**
     * Draw the foreground layer for the GuiContainer (everything in front of the items)
     */
    protected void drawGuiContainerForegroundLayer()
    {
        fontRenderer.drawString(StatCollector.translateToLocal("container.brewing"), 56, 6, 0x404040);
        fontRenderer.drawString(StatCollector.translateToLocal("container.inventory"), 8, (ySize - 96) + 2, 0x404040);
    }

    public void initGui()
    {
        super.initGui();
        guiLeft = (width - xSize) / 2;
        guiTop = (height - ySize) / 2;
        controlList.add(new GuiButton(1, width / 2 - 200, guiTop, 98, 20, "Awkward Potion"));
    }
    
    protected void actionPerformed(GuiButton par1GuiButton)
    {
        if (par1GuiButton.id == 1)
        {
        	int k = 0;
    		int i = 0;
    		int z = 0;
    		int k1 = 0;
    		int i1 = 0;
    		int z1 = 0;
    		boolean water = false;
    		ItemStack itemstack = mc.thePlayer.inventorySlots.getSlot(36).getStack();
    		for(int j1 = 9; j1 < 45; j1++)
    		{
    			itemstack = mc.thePlayer.inventorySlots.getSlot(j1).getStack();
    			if(itemstack != null && itemstack.itemID == 373 && (itemstack.getItemDamage() == 0))
    			{
    					Slot slot1 = (Slot)inventorySlots.inventorySlots.get(z);
    					if(slot1 != null && z < 3)
    					{
    						ItemStack itemstack2 = slot1.getStack();
    						if(itemstack2 != null && itemstack2.itemID == 373)
    						{
    								z++;
    							//continue;
    						}else
    						{
    								i = j1 - 5;
    								mc.playerController.windowClick(inventorySlots.windowId, i, 0, false, mc.thePlayer);
    								mc.playerController.windowClick(inventorySlots.windowId, z, 1, false, mc.thePlayer);
    								mc.playerController.windowClick(inventorySlots.windowId, i, 0, false, mc.thePlayer);
    								z++;
    								
    						}
    					}
   
    			}
    		}
    			for(int j1 = 9; j1 < 45; j1++)
        		{
        			itemstack = mc.thePlayer.inventorySlots.getSlot(j1).getStack();
        			if(itemstack != null && itemstack.itemID == 372)
        			{
        					Slot slot1 = (Slot)inventorySlots.inventorySlots.get(3);
        					if(slot1 != null)				
        					{
        							ItemStack itemstack3 = slot1.getStack();
        							if(itemstack3 == null)
        							{	
        							i1 = j1 - 5;
    									mc.playerController.windowClick(inventorySlots.windowId, i1, 0, false, mc.thePlayer);
    									mc.playerController.windowClick(inventorySlots.windowId, 3, 1, false, mc.thePlayer);
    									mc.playerController.windowClick(inventorySlots.windowId, i1, 0, false, mc.thePlayer);
        							}
        					}
       
        			}
        		}
    		
    		
        	/*mc.playerController.windowClick(inventorySlots.windowId, 4, 0, false, mc.thePlayer);
			mc.playerController.windowClick(inventorySlots.windowId, 0, 0, false, mc.thePlayer);
			mc.playerController.windowClick(inventorySlots.windowId, 5, 0, false, mc.thePlayer);
			mc.playerController.windowClick(inventorySlots.windowId, 1, 0, false, mc.thePlayer);
			mc.playerController.windowClick(inventorySlots.windowId, 6, 0, false, mc.thePlayer);
			mc.playerController.windowClick(inventorySlots.windowId, 2, 0, false, mc.thePlayer);*/
        }
    }
    /**
     * Draw the background layer for the GuiContainer (everything behind the items)
     */
    protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3)
    {
        int i = mc.renderEngine.getTexture("/gui/alchemy.png");
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        mc.renderEngine.bindTexture(i);
        int j = (width - xSize) / 2;
        int k = (height - ySize) / 2;
        drawTexturedModalRect(j, k, 0, 0, xSize, ySize);
        int l = field_40217_h.getBrewTime();

        if (l > 0)
        {
            int i1 = (int)(28F * (1.0F - (float)l / 400F));

            if (i1 > 0)
            {
                drawTexturedModalRect(j + 97, k + 16, 176, 0, 9, i1);
            }

            int j1 = (l / 2) % 7;

            switch (j1)
            {
                case 6:
                    i1 = 0;
                    break;

                case 5:
                    i1 = 6;
                    break;

                case 4:
                    i1 = 11;
                    break;

                case 3:
                    i1 = 16;
                    break;

                case 2:
                    i1 = 20;
                    break;

                case 1:
                    i1 = 24;
                    break;

                case 0:
                    i1 = 29;
                    break;
            }

            if (i1 > 0)
            {
                drawTexturedModalRect(j + 65, (k + 14 + 29) - i1, 185, 29 - i1, 12, i1);
            }
        }
    }
}
