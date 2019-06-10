package me.nahkd.spigot.miscs.api;

import java.util.ArrayList;

import org.bukkit.inventory.ItemStack;

public class ItemStorage {
	
	public ArrayList<ItemStack> items;
	public String id;
	
	/**
	 * Create empty item storage
	 */
	public ItemStorage() {
		this.items = new ArrayList<ItemStack>();
		this.id = IDGenerator.generateID(16);
	}
	
}
