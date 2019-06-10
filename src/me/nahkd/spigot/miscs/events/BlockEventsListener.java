package me.nahkd.spigot.miscs.events;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map.Entry;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

import me.nahkd.spigot.miscs.api.blocks.BlockData;
import me.nahkd.spigot.miscs.api.items.CustomItem;
import me.nahkd.spigot.miscs.api.items.CustomItems;
import me.nahkd.spigot.miscs.api.recipes.CustomRecipe;
import me.nahkd.spigot.miscs.api.recipes.CustomRecipes;
import me.nahkd.spigot.miscs.api.utils.ItemStackUltils;
import me.nahkd.spigot.miscs.api.world.WorldData;
import me.nahkd.spigot.miscs.api.world.WorldsData;
import me.nahkd.spigot.miscs.defaults.RecipeTypes;

/**
 * The event listener
 * @author nahkd123
 *
 */
public class BlockEventsListener implements Listener {
	
	@EventHandler(ignoreCancelled=true, priority=EventPriority.HIGHEST)
	public void onBlockPlace(BlockPlaceEvent event) {
		for (Entry<String, CustomItem> it : CustomItems.getMap().entrySet()) {
			if (it.getValue().item.isSimilar(event.getItemInHand()) && !it.getValue().bypassBlockData) {
				// The custom item has been placed down
				WorldData wd = WorldsData.getDataMap().get(event.getBlock().getWorld());
				BlockData bd = new BlockData(event.getBlock().getLocation(), it.getValue().block);
				wd.blockData.data.add(bd);
				bd.saveToConfig(wd.blockData.config);
				return;
			}
		}
	}
	
	@EventHandler(ignoreCancelled=true, priority=EventPriority.HIGHEST)
	public void onBlockDestroy(BlockBreakEvent event) {
		Location location = event.getBlock().getLocation();
		String theKey = location.getWorld().getName() + "|" + location.getBlockX() + "|" + location.getBlockY() + "|" + location.getBlockZ();
		WorldData wd = WorldsData.getDataMap().get(event.getBlock().getWorld());
		if (wd.blockData.config.contains(theKey)) {
			// The custom block is broken
			String id = wd.blockData.config.getString(theKey + ".id");
			wd.blockData.config.set(theKey, null);
			for (int i = 0; i < wd.blockData.data.size(); i++) {
				BlockData dat = wd.blockData.data.get(i);
				if (!dat.block.item.notListenToBreakEvent) {
					if (dat.location.getBlockX() == event.getBlock().getLocation().getBlockX() &&
						dat.location.getBlockY() == event.getBlock().getLocation().getBlockY() &&
						dat.location.getBlockZ() == event.getBlock().getLocation().getBlockZ()) {
						if (dat.block.dropChanger != null) {
							for (ItemStack item : dat.block.dropChanger.changeDrop(new ArrayList<ItemStack>(Arrays.asList(CustomItems.getMap().get(id).item)), dat)) event.getBlock().getWorld().dropItem(event.getBlock().getLocation(), item);
						} else event.getBlock().getWorld().dropItem(event.getBlock().getLocation(), ItemStackUltils.getCloneSingle(dat.block.item.item));
						event.getBlock().setType(Material.AIR);
						wd.blockData.data.remove(i);
						break;
					}
				}
			}
		}
	}
	
	@EventHandler(ignoreCancelled=true)
	public void onBlockInteract(PlayerInteractEvent event) {
		if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
			if (event.getItem() != null) {
				for (CustomRecipe recipe : CustomRecipes.getRecipes().get(RecipeTypes.RIGHT_CLICKING)) {
					ItemStack fromItem = recipe.items[0];
					ArrayList<ItemStack> list = new ArrayList<ItemStack>(Arrays.asList(recipe.items));
					list.remove(0);
					ArrayList<Material> acceptedMaterials = new ArrayList<Material>();
					for (ItemStack iiiii : list) acceptedMaterials.add(iiiii.getType());
					if (event.getItem().isSimilar(fromItem)) {
						if (acceptedMaterials.contains(event.getClickedBlock().getType())) {
							CustomItem icti = CustomItems.queryItem(fromItem);
							if (icti.onInteract != null) icti.onInteract.onInteract(event);
							if (event.isCancelled()) return;
							// Decrease item by 1
							if (event.getItem().getAmount() > 1) {
								event.getItem().setAmount(event.getItem().getAmount() - 1);
								if (event.getHand() == EquipmentSlot.HAND) event.getPlayer().getInventory().setItemInMainHand(event.getItem());
								if (event.getHand() == EquipmentSlot.OFF_HAND) event.getPlayer().getInventory().setItemInOffHand(event.getItem());
							} else {
								if (event.getHand() == EquipmentSlot.HAND) event.getPlayer().getInventory().setItemInMainHand(null);
								if (event.getHand() == EquipmentSlot.OFF_HAND) event.getPlayer().getInventory().setItemInOffHand(null);
							}
							event.getPlayer().getInventory().addItem(recipe.result);
							return;
						}
					}
				}
			}
		}
	}
	
}
