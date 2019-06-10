package me.nahkd.spigot.miscs.api.recipes;

import org.bukkit.inventory.ItemStack;

public class CustomRecipe {
	
	public CustomRecipeType type;
	public ItemStack[] items;
	public ItemStack result;
	
	public CustomRecipe(CustomRecipeType type, ItemStack[] items, ItemStack result) {
		this.type = type;
		this.items = items;
		this.result = result;
	}

	public boolean checkCorrectRecipe(ItemStack[] playerRecipe) {
		// Check 1: Array length
		if (this.items.length != playerRecipe.length) return false;
		// Check 2: Per item checking
		for (int i = 0; i < this.items.length; i++) {
			if (this.items[i] == null && playerRecipe[i] != null) return false;
			if (this.items[i] != null && playerRecipe[i] == null) return false;
			if (this.items[i] == null && playerRecipe[i] == null) continue;
			if (!this.items[i].isSimilar(playerRecipe[i])) return false;
		}
		return true;
	}
	
}
