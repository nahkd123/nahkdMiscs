package me.nahkd.spigot.miscs.defaults;

import org.bukkit.Material;

import me.nahkd.spigot.miscs.api.ItemBuilder;
import me.nahkd.spigot.miscs.api.recipes.CustomRecipeType;

public class RecipeTypes {
	
	// General
	public static CustomRecipeType VANILLA_RECIPE = new CustomRecipeType("VANILLA_RECIPE", new ItemBuilder(Material.WORKBENCH).name("§6Vanilla Workbench").addLore("§7Make this item using vanilla workbench").create());
	public static CustomRecipeType WORKBENCH = new CustomRecipeType("WORKBENCH", new ItemBuilder(Material.WORKBENCH).name("§6Advanced Workbench").addLore("§7An advanced workbench is needed").create());
	public static CustomRecipeType RIGHT_CLICKING = new CustomRecipeType("RIGHT_CLICKING", new ItemBuilder(Material.IRON_BLOCK).name("§aSpam click").addLore("§7Spam until you broke your mouse").create());
	public static CustomRecipeType UNKNOWN = new CustomRecipeType("UNKNOWN", new ItemBuilder(Material.PAPER).name("§c???").addLore("§7I don't know, but it might", "§7easy to figure out...").create());
	
	// Nature & Gardening
	public static CustomRecipeType CROOK_LOOTING = new CustomRecipeType("CROOK_LOOTING", new ItemBuilder(Material.WOOD_HOE).name("§6Break grass or leaves with §7Crook").addLore("§7Crook are special, right?").create());
	public static CustomRecipeType WAIT_FOR_RAIN = new CustomRecipeType("WAIT_FOR_RAIN", new ItemBuilder(Material.WATER_BUCKET).name("§9Just wait until it raining...").addLore("§7It need some water").create());
	
	// Cobblestone Resources
	public static CustomRecipeType SIEVE = new CustomRecipeType("SIEVE", new ItemBuilder(Material.WEB).name("§6Sieve").addLore("§7Just sieve these blocks").create());
	public static CustomRecipeType SMELTERY = new CustomRecipeType("SMELTERY", new ItemBuilder(Material.FURNACE).name("§cSmeltery").addLore("§7Ok, we need hot stuffs").create());
	
	public static void init() {
		VANILLA_RECIPE.register();
		WORKBENCH.register();
		RIGHT_CLICKING.register();
		UNKNOWN.register();
		
		CROOK_LOOTING.register();
		WAIT_FOR_RAIN.register();
		
		SIEVE.register();
		SMELTERY.register();
	}
	
}
