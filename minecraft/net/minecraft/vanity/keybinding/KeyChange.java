package net.minecraft.vanity.keybinding;

import org.lwjgl.input.Keyboard;

public class KeyChange {
    public static int changeKey(String s)
    {
		String[] args = s.split(" ");
		String a = args[1].toUpperCase().toString();
		int i1 = Keyboard.getKeyIndex(a);
		return Integer.valueOf(i1);
    }
}
