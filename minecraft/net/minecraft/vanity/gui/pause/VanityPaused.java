package net.minecraft.vanity.gui.pause;

import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.src.*;
import net.minecraft.vanity.*;
import net.minecraft.vanity.hacks.Drop;

public class VanityPaused extends GuiScreen
{
    /**
     * A reference to the screen object that created this. Used for navigating between screens.
     */
    private GuiScreen parentScreen;

    /** The title string that is displayed in the top-center of the screen. */
    protected String screenTitle;

    /** Reference to the GameSettings object. */
    private GameSettings options;
    private static EnumOptions relevantOptions[];

    public VanityPaused(GuiScreen par1GuiScreen, GameSettings par2GameSettings)
    {
        screenTitle = "Vanity";
        parentScreen = par1GuiScreen;
        options = par2GameSettings;
    }

    /**
     * Adds the buttons (and other controls) to the screen in question.
     */
    public void initGui()
    {
        StringTranslate stringtranslate = StringTranslate.getInstance();
        screenTitle = stringtranslate.translateKey("\2479[Vanity]");
        int i = 0;
        EnumOptions aenumoptions[] = relevantOptions;
        int j = aenumoptions.length;

        for (int k = 0; k < j; k++)
        {
//            EnumOptions enumoptions = aenumoptions[k];
//
//            if (!enumoptions.getEnumFloat())
            {
//                GuiSmallButton guismallbutton = new GuiSmallButton(enumoptions.returnEnumOrdinal(), (width / 2 - 155) + (i % 2) * 160, height / 6 + 24 * (i >> 1), enumoptions, options.getKeyBinding(enumoptions));
//
//                if (enumoptions == EnumOptions.DIFFICULTY && mc.theWorld != null && mc.theWorld.getWorldInfo().isHardcoreModeEnabled())
                {
//                    guismallbutton.enabled = false;
//                    guismallbutton.displayString = (new StringBuilder()).append(StatCollector.translateToLocal("options.difficulty")).append(": ").append(StatCollector.translateToLocal("options.difficulty.hardcore")).toString();
                }

//                controlList.add(guismallbutton);
            }
//            else
            {
//                controlList.add(new GuiSlider(enumoptions.returnEnumOrdinal(), (width / 2 - 155) + (i % 2) * 160, height / 6 + 24 * (i >> 1), enumoptions, options.getKeyBinding(enumoptions), options.getOptionFloatValue(enumoptions)));
            }

            i++;
        }

        controlList.add(new GuiButton(70, width / 2 - 100, (height / 6 + 24) - 6, stringtranslate.translateKey("\247aDrop")));
        //controlList.add(new GuiButton(71, width / 2 - 100, (height / 6 + 144) - 6, stringtranslate.translateKey("\247aDevelopers")));
        //controlList.add(new GuiButton(71, width / 2 - 100, (height / 6 + 120) - 6, stringtranslate.translateKey("assduffmoovee")));
        //controlList.add(new GuiButton(72, width / 2 - 100, (height / 6 + 144) - 6, stringtranslate.translateKey("options.language")));
        controlList.add(new GuiButton(200, width / 2 - 100, height / 6 + 168, stringtranslate.translateKey("\247fDone")));
    }

    /**
     * Fired when a control is clicked. This is the equivalent of ActionListener.actionPerformed(ActionEvent e).
     */
    protected void actionPerformed(GuiButton par1GuiButton)
    {
        if (!par1GuiButton.enabled)
        {
            return;
        }

        if (par1GuiButton.id < 100 && (par1GuiButton instanceof GuiSmallButton))
        {
            options.setOptionValue(((GuiSmallButton)par1GuiButton).returnEnumOptions(), 1);
            par1GuiButton.displayString = options.getKeyBinding(EnumOptions.getEnumOptions(par1GuiButton.id));
        }

        if (par1GuiButton.id == 70)
        {
        	Drop.createSmokescreen(true);
        }

        if (par1GuiButton.id == 71)
        {
        	mc.displayGuiScreen(new VanityPaused(this, mc.gameSettings));
        }

        if (par1GuiButton.id == 72)
        {
            
        }

        if (par1GuiButton.id == 73)
        {
            
        }
        if (par1GuiButton.id == 200)
        {
        	 mc.displayGuiScreen(null);
             mc.setIngameFocus();
        }
    }

    /**
     * Draws the screen and all the components in it.
     */
    public void drawScreen(int par1, int par2, float par3)
    {
        drawDefaultBackground();
        drawCenteredString(fontRenderer, screenTitle, width / 2, 20, 0xffffff);
        super.drawScreen(par1, par2, par3);
    }

    static
    {
        relevantOptions = (new EnumOptions[]
                {
                    EnumOptions.MUSIC, EnumOptions.SOUND, EnumOptions.INVERT_MOUSE, EnumOptions.SENSITIVITY, EnumOptions.FOV, EnumOptions.DIFFICULTY
                });
    }
}
