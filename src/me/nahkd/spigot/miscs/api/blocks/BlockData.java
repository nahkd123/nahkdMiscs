package me.nahkd.spigot.miscs.api.blocks;

import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

public class BlockData {
	
	public Location location;
	public CustomBlock block;
	public ConfigurationSection otherData;
	
	public BlockData(Location location, CustomBlock block) {
		this.location = location;
		this.block = block;
		
		this.otherData = null;
	}
	
	/**
	 * Set to config, not really saving
	 * @param config The parent, as know as {@link FileConfiguration}
	 */
	public void saveToConfig(ConfigurationSection config) {
		String parentKey = location.getWorld().getName() + "|" + location.getBlockX() + "|" + location.getBlockY() + "|" + location.getBlockZ();
		config.set(parentKey + ".id", this.block.item.id);
		if (this.otherData != null) {
			ConfigurationSection sc = config.getConfigurationSection(parentKey).createSection("otherData");
			for (String key : this.otherData.getKeys(true)) {
				sc.set(key, this.otherData.get(key));
			}
		}
	}
	
}
