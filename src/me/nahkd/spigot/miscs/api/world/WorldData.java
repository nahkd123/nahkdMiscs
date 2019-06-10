package me.nahkd.spigot.miscs.api.world;

import org.bukkit.World;

import me.nahkd.spigot.miscs.api.blocks.WorldBlockData;

public class WorldData {
	
	public WorldBlockData blockData;
	public World world;
	
	public WorldData(World world) {
		System.out.println("[nahkdMiscs](WorldData) Loading data for world " + world.getName());
		this.world = world;
		this.blockData = new WorldBlockData(world);
	}
	
}
