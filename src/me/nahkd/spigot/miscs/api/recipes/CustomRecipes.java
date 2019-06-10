package me.nahkd.spigot.miscs.api.recipes;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.inventory.ItemStack;

public class CustomRecipes {

	private static HashMap<String, CustomRecipeType> registeredRecipeTypes;
	public static HashMap<String, CustomRecipeType> getMap() {
		if (registeredRecipeTypes == null) registeredRecipeTypes = new HashMap<String, CustomRecipeType>();
		return registeredRecipeTypes;
	}
	public static void register(CustomRecipeType recipeType) {
		getMap().put(recipeType.id, recipeType);
	}
	
	private static HashMap<CustomRecipeType, ArrayList<CustomRecipe>> registeredRecipes;
	public static HashMap<CustomRecipeType, ArrayList<CustomRecipe>> getRecipes() {
		if (registeredRecipes == null) registeredRecipes = new HashMap<CustomRecipeType, ArrayList<CustomRecipe>>();
		return registeredRecipes;
	}
	public static void registerRecipe(CustomRecipe recipe) {
		if (getRecipes().get(recipe.type) == null) getRecipes().put(recipe.type, new ArrayList<CustomRecipe>());
		getRecipes().get(recipe.type).add(recipe);
	}
	public static CustomRecipe queryRecipe(CustomRecipeType type, ItemStack[] playerRecipe) {
		for (CustomRecipe rep : registeredRecipes.get(type)) {
			if (rep.checkCorrectRecipe(playerRecipe)) return rep;
		}
		return null;
	}
	
}
