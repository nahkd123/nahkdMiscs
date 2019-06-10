package me.nahkd.spigot.miscs.api.power;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.nahkd.spigot.miscs.api.ItemBuilder;

public class Generator {
	
	public ArrayList<GeneratorItemEntry> inputs;
	public double efficiency;
	
	public Generator(double efficiency, GeneratorItemEntry... inputs) {
		this.inputs = new ArrayList<GeneratorItemEntry>(Arrays.asList(inputs));
		this.efficiency = efficiency;
	}
	
	public ItemStack toItem() {
		ItemStack i = new ItemBuilder(Material.FURNACE).name("§bGenerator").addLore("", "§7Efficiency: §e" + (efficiency * 100) + "%", "", "§7Allowed materials:").create();
		ItemMeta im = i.getItemMeta();
		List<String> l = im.getLore();
		for (GeneratorItemEntry e : inputs) l.add("§7 - §f" + e.input.toString() + " §e" + e.powerPerTick + "/ticks §cat " + e.ticks + " ticks");
		im.setLore(l);
		i.setItemMeta(im);
		return i;
	}
	
}
