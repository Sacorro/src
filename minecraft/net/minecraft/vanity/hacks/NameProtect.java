package net.minecraft.vanity.hacks;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import net.minecraft.client.Minecraft;
import net.minecraft.vanity.main.Vanity;

public class NameProtect {
	public static File configdir =  new File(Minecraft.getMinecraftDir(), "Vanity");
	public static File NameProtectFile = new File(configdir, "NameProtect.txt");
	public static void save()
    {
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
    public static void load() throws IOException
    {
    	
    	Scanner reader = new Scanner(NameProtectFile);
    	 for (String s = ""; (s = reader.nextLine()) != null;)
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
}
