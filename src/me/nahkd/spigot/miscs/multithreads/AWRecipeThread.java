package me.nahkd.spigot.miscs.multithreads;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import me.nahkd.spigot.miscs.api.recipes.CustomRecipe;
import me.nahkd.spigot.miscs.api.recipes.CustomRecipes;
import me.nahkd.spigot.miscs.defaults.RecipeTypes;

public class AWRecipeThread extends Thread implements SafeStopable {

	public boolean stopped;
	
	public AWRecipeThread() {
		this.stopped = false;
	}
	
	@Override
	public void run() {
		while(!this.stopped) {
			try {
				//System.out.println("Ticking... " + new Date().getTime());
				for (Player player : Bukkit.getOnlinePlayers()) {
					boolean recipeFound = false;
					if (player.getOpenInventory().getTopInventory() != null && player.getOpenInventory().getTopInventory().getTitle() == "advanced workbench") {
						Inventory inv = player.getOpenInventory().getTopInventory();
						ArrayList<ItemStack> items = new ArrayList<ItemStack>(Arrays.asList(inv.getContents()));
						items.remove(0);
						CustomRecipe rep = CustomRecipes.queryRecipe(RecipeTypes.WORKBENCH, items.toArray(new ItemStack[0]));
						if (rep != null) {
							// Recipe found
							inv.setItem(0, rep.result);
							recipeFound = true;
						}
						if (!recipeFound && inv.getItem(0) != null) inv.setItem(0, null);
					}
				}
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("[nahkdMiscs](Multithreading) Crafting thread stopped. If you don't see this message, please restart your server");
	}
	
	@Override
	public void stopThreadSafety() {
		this.stopped = true;
	}

	@Override
	public boolean isThreadStopped() {
		return this.stopped;
	}

}
