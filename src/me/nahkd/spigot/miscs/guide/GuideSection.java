package me.nahkd.spigot.miscs.guide;

import java.util.ArrayList;

import org.bukkit.inventory.ItemStack;

import me.nahkd.spigot.miscs.api.items.CustomItem;

public class GuideSection {
	
	public ItemStack display;
	public String id;
	public ArrayList<CustomItem> items;
	
	public GuideSection(ItemStack display, String id) {
		this.display = display;
		this.id = id;
		this.items = new ArrayList<CustomItem>();
	}
	
	public void register() {
		GuideSections.register(this);
	}
	
}
