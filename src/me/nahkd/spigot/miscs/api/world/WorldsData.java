package me.nahkd.spigot.miscs.api.world;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.World;

public class WorldsData {
	
	private static HashMap<World, WorldData> data;
	public static HashMap<World, WorldData> getDataMap() {
		if (data == null) data = new HashMap<World, WorldData>();
		return data;
	}
	public static void init() {
		for (World world : Bukkit.getWorlds()) {
			getDataMap().put(world, new WorldData(world));
		}
	}
	
	public static void saveAll() {
		for (World world : data.keySet()) {
			data.get(world).blockData.saveConfig();
		}
	}
	
}
