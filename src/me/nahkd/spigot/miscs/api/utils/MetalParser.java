package me.nahkd.spigot.miscs.api.utils;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import me.nahkd.spigot.miscs.api.recipes.CustomRecipe;
import me.nahkd.spigot.miscs.api.recipes.CustomRecipes;
import me.nahkd.spigot.miscs.defaults.DefaultItems;
import me.nahkd.spigot.miscs.defaults.RecipeTypes;

public class MetalParser {
	
	public static ItemStack fromSmeltery(ItemStack dust) {
		for (CustomRecipe o : CustomRecipes.getRecipes().getOrDefault(RecipeTypes.SMELTERY, new ArrayList<CustomRecipe>())) {
			if (o.items[0].isSimilar(dust)) return o.result;
		}
		if (dust != null) {
			if (dust.isSimilar(DefaultItems.COBBLERES_DUSTS_COAL.item)) return new ItemStack(Material.COAL);
			if (dust.isSimilar(DefaultItems.COBBLERES_DUSTS_IRON.item)) return new ItemStack(Material.IRON_INGOT);
			if (dust.isSimilar(DefaultItems.COBBLERES_DUSTS_GOLD.item)) return new ItemStack(Material.GOLD_INGOT);
		}
		System.out.println("[nahkdMiscs] Something wrong with class MetalParser: " + dust.getItemMeta().getDisplayName());
		return null;
	}
	
}
