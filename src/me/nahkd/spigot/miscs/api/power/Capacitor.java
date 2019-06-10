package me.nahkd.spigot.miscs.api.power;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import me.nahkd.spigot.miscs.api.ItemBuilder;

public class Capacitor {
	
	public double currentPower;
	public double maxPower;
	
	public Capacitor(double current, double max) {
		this.currentPower = current;
		this.maxPower = max;
	}
	
	public ItemStack toItem() {
		ItemStack out = new ItemBuilder(Material.STAINED_CLAY, 1, 3).name("§bCapacitor").addLore("", "§7Capacity: §f" + currentPower + "/" + maxPower + " §cNP", "", "§7This block can store", "§cNature Power").create();
		return out;
	}
	
	public static boolean isCapacitor(ItemStack item) {
		return item.hasItemMeta()? (item.getItemMeta().hasDisplayName()? item.getItemMeta().getDisplayName().equals("§bCapacitor") : false) : false;
	}
	public static Capacitor fromItem(ItemStack item) {
		if (isCapacitor(item)) {
			double current = Double.parseDouble(item.getItemMeta().getLore().get(1).split("\\/")[0].substring(14));
			double max = Double.parseDouble(item.getItemMeta().getLore().get(1).split("\\/")[1].split(" ")[0]);
			return new Capacitor(current, max);
		} else return null;
	}
	
}
