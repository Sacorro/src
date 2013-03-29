package net.minecraft.vanity.main;

import java.io.*;
import java.util.Scanner;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.Minecraft;
import net.minecraft.vanity.gui.GuiLocations;
import net.minecraft.vanity.keybinding.Keybinds;

public class VanityFileWriter {

	private static Minecraft mc;
	private static Keybinds keybinds;
	static File f = new File(mc.getMinecraftDir(),  "VantiyKeys.txt");
	public static File NameProtectFile = new File(mc.getMinecraftDir(), "NameProtect.txt");
	static File loc = new File(mc.getMinecraftDir(), "VanityGuiLocations.txt");
	
	
	public VanityFileWriter(File f)
	{
	}
	
    public static void load() throws IOException
    {
    	
    	BufferedReader bufferedreader = new BufferedReader(new FileReader(NameProtectFile));
    	 for (String s = ""; (s = bufferedreader.readLine()) != null;)
         {
             try
             {
                 String as[] = s.split(":");
                 Vanity.protectHashMap.put(as[0], as[1]);
             }
             catch (Exception exception1)
             {
                 System.out.println((new StringBuilder()).append("Skipping bad option: ").append(s).toString());
             }
         }
    }
	
	public static void save() throws IOException
    {
		NameProtectFile.createNewFile();
        try
        {
            PrintWriter printwriter = new PrintWriter(new FileWriter(NameProtectFile)); //creates the print function with the specified file. File = File NameProtectFile = new File();

            for (Object obj : Vanity.protectHashMap.keySet()) //loops through the nameprotect hashmap, same as chat
            {
                String name = obj.toString(); //sets it to a string
                String total = new StringBuilder().append(name).append(":").append(Vanity.protectHashMap.get(name)).toString(); //puts all together, in format account:nickname
                printwriter.println(total); //prints it to the text file
            }
            printwriter.close(); //closes it after performing action
        }
        catch (Exception exception)
        {
            System.out.println("Failed to save NameProtect");
            exception.printStackTrace(); 
        }
    }
	
	
	public static void loadWallhack() throws IOException
	{
		loc.createNewFile();
		Scanner reader = new Scanner(loc);
		
	}
		
	public static void writeKeys() throws IOException
	{
		f.createNewFile();
		Scanner reader = new Scanner(f);
		PrintWriter print = new PrintWriter(f);
		print.println(keybinds.keybindFly);
		print.println(keybinds.keybindWallhack);
		print.println(keybinds.keybindSneak);
		print.println(keybinds.keybindNofall);
		print.println(keybinds.keybindTracer);
		print.println(keybinds.keybindKillaura);
		print.println(keybinds.keybindBright);
		print.println(keybinds.keybindSpeedmine);
		print.println(keybinds.keybindSprint);
		print.println(keybinds.keybindGUI);
		print.close();
	}
	
	public static void readKeys() throws IOException
	{
try{
		f.createNewFile();
		Scanner reader = new Scanner(f);
		int[] keys = new int[256];
		keys[0] = Integer.parseInt(reader.nextLine());
		keys[1] = Integer.parseInt(reader.nextLine());
		keys[2] = Integer.parseInt(reader.nextLine());
		keys[3] = Integer.parseInt(reader.nextLine());
		keys[4] = Integer.parseInt(reader.nextLine());
		keys[5] = Integer.parseInt(reader.nextLine());
		keys[6] = Integer.parseInt(reader.nextLine());
		keys[7] = Integer.parseInt(reader.nextLine());
		keys[8] = Integer.parseInt(reader.nextLine());
		keys[9] = Integer.parseInt(reader.nextLine());
		Keybinds.keybindFly = keys[0];
		Keybinds.keybindWallhack = keys[1];
		Keybinds.keybindSneak = keys[2];
		Keybinds.keybindNofall = keys[3];
		Keybinds.keybindTracer = keys[4];
		Keybinds.keybindKillaura = keys[5];
		Keybinds.keybindBright = keys[6];
		Keybinds.keybindSpeedmine = keys[7];
		Keybinds.keybindSprint = keys[8];
		Keybinds.keybindGUI = keys[9];
		reader.close();
}catch(Exception e) { }
	}
}

