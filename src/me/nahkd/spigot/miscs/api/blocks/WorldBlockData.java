package me.nahkd.spigot.miscs.api.blocks;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.YamlConfiguration;

import me.nahkd.spigot.miscs.api.items.CustomItem;
import me.nahkd.spigot.miscs.api.items.CustomItems;
import me.nahkd.spigot.miscs.config.ConfigFiles;

public class WorldBlockData {
	
	public World world;
	public ArrayList<BlockData> data;
	public YamlConfiguration config;
	public File configSource;
	
	public WorldBlockData(World world) {
		this.world = world;
		this.configSource = new File(ConfigFiles.blockDataFolder, world.getName() + ".yml");
		this.config = YamlConfiguration.loadConfiguration(this.configSource);
		this.data = new ArrayList<BlockData>();
		if (!this.configSource.exists()) {
			// Create new file
			System.out.println("[nahkdMiscs] Creating file " + world.getName() + ".yml");
			this.config.set("timestamp", new Date().getTime());
			saveConfig();
		} else {
			// The file is exists, load from file
			System.out.println("[nahkdMiscs] Loading file " + world.getName() + ".yml");
			for (String key : this.config.getKeys(false)) {
				if (!key.equalsIgnoreCase("timestamp")) {
					String[] sliced = key.split("\\|");
					Location location = new Location(Bukkit.getWorld(sliced[0]), Integer.parseInt(sliced[1]), Integer.parseInt(sliced[2]), Integer.parseInt(sliced[3]));
					CustomItem item = CustomItems.getMap().get(this.config.getString(key + ".id"));
					BlockData bd = new BlockData(location, CustomBlocks.getByItem(item));
					if (this.config.contains(key + ".otherData")) bd.otherData = this.config.getConfigurationSection(key + ".otherData");
					this.data.add(bd);
				}
			}
			saveConfig();
		}
	}
	
	/**
	 * Search for block data at location
	 * @param location The block location
	 * @return Block data if exists, otherwise null
	 */
	public BlockData queryData(Location location) {
		for (BlockData dat : data) {
			if (dat.location.getWorld() == location.getWorld() &&
				dat.location.getBlockX() == location.getBlockX() &&
				dat.location.getBlockY() == location.getBlockY() &&
				dat.location.getBlockZ() == location.getBlockZ()) return dat;
		}
		return null;
	}
	
	/**
	 * Save the config. After this, you can make a backup if you want tho
	 */
	public void saveConfig() {
		System.out.println("[nahkdMiscs] Saving data in " + this.world.getName());
		try {
			for (BlockData dataa : this.data) {
				dataa.saveToConfig(this.config);
			}
			this.config.save(this.configSource);
		} catch (IOException e) {
			System.out.println("[nahkdMiscs] Cannot save the data owo");
			e.printStackTrace();
		}
	}
	
}
