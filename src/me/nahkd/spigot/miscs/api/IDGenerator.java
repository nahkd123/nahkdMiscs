package me.nahkd.spigot.miscs.api;

public class IDGenerator {
	
	public static String generateID(int length) {
		String ua = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ-";
		String out = "";
		for (int i = 0; i < length; i++) {
			out += ua.toCharArray()[(int) Math.round(Math.random() * 63)];
		}
		return out;
	}
	
}
