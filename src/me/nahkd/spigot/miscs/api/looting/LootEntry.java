package me.nahkd.spigot.miscs.api.looting;

import org.bukkit.inventory.ItemStack;

public class LootEntry {
	
	public double chance;
	public ItemStack item;
	
	public LootEntry(double chance, ItemStack item) {
		this.chance = chance;
		this.item = item;
	}

	public boolean pickChanceMorethan() {
		return (Math.random() > this.chance);
	}
	public boolean pickChanceLessthan() {
		return (Math.random() < this.chance);
	}
	public boolean pickChanceLessthan(double luckMultiplier) {
		return (Math.random() < this.chance * luckMultiplier);
	}
	
}
