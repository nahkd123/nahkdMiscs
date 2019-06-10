package me.nahkd.spigot.miscs.api.items;

import org.bukkit.inventory.ItemStack;

import me.nahkd.spigot.miscs.api.blocks.BlockTicker;
import me.nahkd.spigot.miscs.api.blocks.CustomBlock;
import me.nahkd.spigot.miscs.api.recipes.CustomRecipe;
import me.nahkd.spigot.miscs.api.recipes.CustomRecipeType;
import me.nahkd.spigot.miscs.api.recipes.CustomRecipes;
import me.nahkd.spigot.miscs.api.utils.ItemStackUltils;
import me.nahkd.spigot.miscs.defaults.DefaultSections;
import me.nahkd.spigot.miscs.guide.GuideSection;

public class CustomItem {
	
	public String id;
	public ItemStack item;
	public CustomRecipe recipe;
	public GuideSection section;
	
	public CustomBlock block;
	
	public ItemBlockInteract onInteract;
	
	/** This will not save the block data when player place the block down **/
	public boolean bypassBlockData;
	public boolean notListenToBreakEvent;
	
	public CustomItem(String id, ItemStack item, CustomRecipeType type, ItemStack[] recipe) {
		this.id = id;
		this.item = item;
		this.recipe = new CustomRecipe(type, recipe, item);
		this.section = DefaultSections.UNCATEGORIZED;
		
		this.bypassBlockData = false;
		this.notListenToBreakEvent = false;
		
		this.onInteract = null;
		
		if (item.getType().isBlock() || ItemStackUltils.isBlockCorrectly(item.getType())) this.block = new CustomBlock(this);
		System.out.println(id + " - " + this.item.getType().isBlock());
	}
	
	public CustomItem(GuideSection section, String id, ItemStack item, CustomRecipeType type, ItemStack[] recipe) {
		this(id, item, type, recipe);
		this.section = section;
	}
	
	public boolean checkCorrectRecipe(ItemStack[] playerRecipe) {
		return this.recipe.checkCorrectRecipe(playerRecipe);
	}
	
	public void addBlockTicker(BlockTicker ticker) {
		this.block.addTicker(ticker);
	}
	
	public void register() {
		CustomItems.register(this);
		CustomRecipes.registerRecipe(recipe);
		this.section.items.add(this);
		if (this.block != null) this.block.register();
	}
	
}
