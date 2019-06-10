package me.nahkd.spigot.miscs.api.recipes;

import java.util.ArrayList;

import org.bukkit.inventory.ItemStack;

public class CustomRecipeType {
	
	public String id;
	public ItemStack display;
	
	public CustomRecipeType(String id, ItemStack display) {
		this.id = id;
		this.display = display;
	}
	
	public void register() {
		CustomRecipes.register(this);
		CustomRecipes.getRecipes().put(this, new ArrayList<CustomRecipe>());
	}
	
}
