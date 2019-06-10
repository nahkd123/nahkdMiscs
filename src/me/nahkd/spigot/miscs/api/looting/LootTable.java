package me.nahkd.spigot.miscs.api.looting;

import java.util.ArrayList;
import java.util.Arrays;

import org.bukkit.inventory.ItemStack;

public class LootTable {
	
	public ArrayList<LootEntry> loots;
	
	public LootTable(LootEntry... entries) {
		this.loots = new ArrayList<LootEntry>(Arrays.asList(entries));
	}

	public ArrayList<ItemStack> generateOutputMorethan() {
		ArrayList<ItemStack> output = new ArrayList<ItemStack>();
		for (LootEntry entry : loots) if (entry.pickChanceMorethan()) output.add(entry.item);
		return output;
	}
	public ArrayList<ItemStack> generateOutputLessthan() {
		ArrayList<ItemStack> output = new ArrayList<ItemStack>();
		for (LootEntry entry : loots) if (entry.pickChanceLessthan()) output.add(entry.item);
		return output;
	}
	public ArrayList<ItemStack> generateOutputLessthan(double luckMultipier) {
		ArrayList<ItemStack> output = new ArrayList<ItemStack>();
		for (LootEntry entry : loots) if (entry.pickChanceLessthan(luckMultipier)) output.add(entry.item);
		return output;
	}
	
}
