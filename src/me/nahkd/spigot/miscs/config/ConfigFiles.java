package me.nahkd.spigot.miscs.config;

import java.io.File;

import org.bukkit.World;

import me.nahkd.spigot.miscs.nahkdMiscs;

public class ConfigFiles {
	
	public static File blockDataFolder = new File(nahkdMiscs.dataFolder, "blocksdata");
	
	public static void init() {
		System.out.println("[nahkdMiscs] Setting up configurations...");
		if (!nahkdMiscs.dataFolder.exists()) nahkdMiscs.dataFolder.mkdir();
		if (!blockDataFolder.exists()) blockDataFolder.mkdir();
	}
	
	public static File getBlockDataConfig(World world) {
		return new File(blockDataFolder, world.getName() + ".yml");
	}
	
}
