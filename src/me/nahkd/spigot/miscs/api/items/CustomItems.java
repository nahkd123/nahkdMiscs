package me.nahkd.spigot.miscs.api.items;

import java.util.HashMap;
import java.util.Map.Entry;

import org.bukkit.inventory.ItemStack;

public class CustomItems {
	
	private static HashMap<String, CustomItem> registeredItems;
	public static HashMap<String, CustomItem> getMap() {
		if (registeredItems == null) registeredItems = new HashMap<String, CustomItem>();
		return registeredItems;
	}
	
	public static void register(CustomItem item) {
		getMap().put(item.id, item);
	}
	
	public static CustomItem queryItem(ItemStack itemin) {
		for (Entry<String, CustomItem> entry : getMap().entrySet()) {
			if (entry.getValue().item.isSimilar(itemin)) return entry.getValue();
		}
		return null;
	}
	
}
