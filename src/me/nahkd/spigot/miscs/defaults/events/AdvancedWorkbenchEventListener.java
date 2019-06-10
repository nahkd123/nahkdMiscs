package me.nahkd.spigot.miscs.defaults.events;

import java.util.ArrayList;
import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import me.nahkd.spigot.miscs.api.blocks.BlockData;
import me.nahkd.spigot.miscs.api.items.CustomItem;
import me.nahkd.spigot.miscs.api.items.CustomItems;
import me.nahkd.spigot.miscs.api.recipes.CustomRecipe;
import me.nahkd.spigot.miscs.api.recipes.CustomRecipes;
import me.nahkd.spigot.miscs.api.utils.PlayerInventoryUltils;
import me.nahkd.spigot.miscs.api.world.WorldsData;
import me.nahkd.spigot.miscs.defaults.RecipeTypes;

public class AdvancedWorkbenchEventListener implements Listener {
	
	@EventHandler
	public void onInteract(PlayerInteractEvent event) {
		if (event.getHand() == EquipmentSlot.HAND && event.getAction() == Action.RIGHT_CLICK_BLOCK) {
			BlockData data = WorldsData.getDataMap().get(event.getClickedBlock().getWorld()).blockData.queryData(event.getClickedBlock().getLocation());
			if (data != null) {
				if (data.block.item.id == "ADVANCED_WORKBENCH") {
					// Open the workbench GUI, but can't craft any vanilla items
					Inventory inv = Bukkit.createInventory(null, InventoryType.WORKBENCH, "advanced workbench");
					event.getPlayer().openInventory(inv);
					event.setCancelled(true);
					event.getPlayer().sendMessage("§6You've opened advanced crafting table!");
				}
			}
		}
	}
	
	@EventHandler
	public void onInventoryInteract(InventoryClickEvent event) {
		if (event.getClickedInventory() != null && event.getClickedInventory().getTitle() == "advanced workbench") {
			Inventory inv = event.getClickedInventory();
			
			boolean recipeFound = false;
			ArrayList<ItemStack> playerRecipe = new ArrayList<ItemStack>(Arrays.asList(inv.getContents()));
			playerRecipe.remove(0);
			CustomRecipe rep = CustomRecipes.queryRecipe(RecipeTypes.WORKBENCH, playerRecipe.toArray(new ItemStack[0]));
			if (rep != null) {
				// Recipe found
				inv.setItem(0, rep.result);
				recipeFound = true;
			}
			if (!recipeFound && inv.getItem(0) != null) inv.setItem(0, null);
			
			if (event.getSlot() == 0 && inv.getItem(0) != null) {
				if (event.getClick() == ClickType.LEFT || event.getClick() == ClickType.RIGHT) {
					CustomItem i = CustomItems.queryItem(inv.getItem(0));
					if (i != null) {
						// Item is actually exists
						if (event.getCursor() == null || event.getCursor().getType() == Material.AIR) {
							// No item in cursor, set it instead
							event.setCursor(new ItemStack(i.item));
						} else {
							// Item in cursor
							event.getWhoClicked().sendMessage("" + event.getCursor().getType().getMaxStackSize());
							if (PlayerInventoryUltils.canStack(event.getCursor(), i.item)) event.setCursor(new ItemStack(PlayerInventoryUltils.stack(event.getCursor(), i.item)));
							else {
								event.setCancelled(true);
								return;
							}
						}
						for (int i2 = 1; i2 < inv.getContents().length; i2++) {
							ItemStack ii = inv.getContents()[i2];
							if (ii != null) {
								if (ii.getAmount() > 1) ii.setAmount(ii.getAmount() - 1);
								else inv.setItem(i2, null);
							}
						}
						playerRecipe = new ArrayList<ItemStack>(Arrays.asList(inv.getContents()));
						playerRecipe.remove(0);
						recipeFound = false;
						rep = CustomRecipes.queryRecipe(RecipeTypes.WORKBENCH, playerRecipe.toArray(new ItemStack[0]));
						if (rep != null) {
							// Recipe found
							event.getWhoClicked().sendMessage("Recipe found");
							inv.setItem(0, rep.result);
							recipeFound = true;
						}
						if (!recipeFound && inv.getItem(0) != null) inv.setItem(0, null);
					} else {
						System.out.println("[nahkdItems] Something unexpected. Please report this to dev!");
						System.out.println("---------------------------------------------------------------------");
						System.out.println("type: AW_CRAFT_NOITEM");
						System.out.println("---------------------------------------------------------------------");
					}
				} else if (event.getClick() == ClickType.SHIFT_LEFT || event.getClick() == ClickType.SHIFT_RIGHT) {
					event.getWhoClicked().sendMessage("Craft all the item and add to inventory");
					CustomItem i = CustomItems.queryItem(inv.getItem(0));
					if (i != null) {
						// Again, item exists
						int minAmount = 65; // 1 stack never get over 64
						for (int i2 = 1; i2 < inv.getContents().length; i2++) {
							ItemStack ii = inv.getContents()[i2];
							// Instead of subtract item amount, we'll need min amount first
							if (ii != null) {
								if (ii.getAmount() < minAmount) minAmount = ii.getAmount();
							}
						}
						// Now, we'll need to subtract with min amount
						for (int i2 = 1; i2 < inv.getContents().length; i2++) {
							ItemStack ii = inv.getContents()[i2];
							if (ii != null) {
								if (ii.getAmount() > minAmount) ii.setAmount(ii.getAmount() - minAmount);
								else inv.setItem(i2, null);
							}
						}
						// The last thing is add to inventory yay
						PlayerInventoryUltils.addToInventory(event.getWhoClicked(), i.item, minAmount);
						event.setCancelled(true);
						return;
					} else {
						System.out.println("[nahkdItems] Something unexpected. Please report this to dev!");
						System.out.println("---------------------------------------------------------------------");
						System.out.println("type: AW_CRAFT_NOITEM");
						System.out.println("---------------------------------------------------------------------");
					}
				}
				event.setCancelled(true);
			} else if (event.getClick() == ClickType.SHIFT_LEFT || event.getClick() == ClickType.SHIFT_RIGHT) {
				event.setCancelled(true);
				return;
			}
				
		}
	}
	
	@EventHandler
	public void inventoryClose(InventoryCloseEvent event) {
		if (event.getInventory() != null && event.getInventory().getTitle() == "advanced workbench") {
			ArrayList<ItemStack> playerRecipe = new ArrayList<ItemStack>(Arrays.asList(event.getInventory().getContents()));
			playerRecipe.remove(0);
			for (ItemStack i : playerRecipe) if (i != null) event.getPlayer().getWorld().dropItem(event.getPlayer().getLocation(), i);
		}
	}
	
}
