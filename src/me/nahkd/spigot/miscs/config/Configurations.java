package me.nahkd.spigot.miscs.config;

import org.bukkit.World;
import org.bukkit.configuration.file.YamlConfiguration;

public class Configurations {
	
	public static YamlConfiguration getBlockDataFromWorld(World world) {
		return YamlConfiguration.loadConfiguration(ConfigFiles.getBlockDataConfig(world));
	}
	
}
