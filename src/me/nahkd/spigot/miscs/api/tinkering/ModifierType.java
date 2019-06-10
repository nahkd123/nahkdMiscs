package me.nahkd.spigot.miscs.api.tinkering;

import org.bukkit.inventory.ItemStack;

public class ModifierType {
	
	public static String lore_header = "§m§o§d";
	
	/**
	 * This also the identifier as well
	 */
	public String name;
	public ModifierEvents event;
	public ModifierTicker ticker;
	public int maxItems;
	public ItemStack item;
	
	public ModifierType(String name, int maxItems, ItemStack item) {
		this.name = name;
		this.event = null;
		this.ticker = null;
		this.maxItems = maxItems;
		this.item = item;
	}
	
	public String getModifierLore(int level, int items) {
		return lore_header + name + " " + level + " §7(" + items + "/" + maxItems + ")";
	}
	
	public void register() {
		ModifierTypesManager.register(this);
	}
	
}
