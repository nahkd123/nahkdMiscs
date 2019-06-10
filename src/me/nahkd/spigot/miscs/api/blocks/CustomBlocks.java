package me.nahkd.spigot.miscs.api.blocks;

import java.util.HashMap;

import me.nahkd.spigot.miscs.api.items.CustomItem;

public class CustomBlocks {
	
	private static HashMap<CustomBlock, CustomItem> registeredBlocks;
	public static HashMap<CustomBlock, CustomItem> getMap() {
		if (registeredBlocks == null) registeredBlocks = new HashMap<CustomBlock, CustomItem>();
		return registeredBlocks;
	}
	
	public static void registerBlock(CustomBlock block) {
		getMap().put(block, block.item);
	}
	
	public static CustomBlock getByItem(CustomItem item) {
		for (CustomBlock b : getMap().keySet()) {
			if (getMap().get(b) == item) return b;
		}
		return null;
	}
	
}
