package me.nahkd.spigot.miscs.events;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map.Entry;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import me.nahkd.spigot.miscs.api.blocks.BlockData;
import me.nahkd.spigot.miscs.api.items.CustomItem;
import me.nahkd.spigot.miscs.api.items.CustomItems;
import me.nahkd.spigot.miscs.api.power.PowerableUltils;
import me.nahkd.spigot.miscs.api.power.PowerableUltils.Interfaces;
import me.nahkd.spigot.miscs.api.recipes.CustomRecipe;
import me.nahkd.spigot.miscs.api.recipes.CustomRecipeType;
import me.nahkd.spigot.miscs.api.recipes.CustomRecipes;
import me.nahkd.spigot.miscs.api.world.WorldsData;
import me.nahkd.spigot.miscs.guide.GuideBookData;
import me.nahkd.spigot.miscs.guide.GuideBookData.ViewMode;
import me.nahkd.spigot.miscs.guide.GuideBookManager;
import me.nahkd.spigot.miscs.guide.GuideItems;
import me.nahkd.spigot.miscs.guide.GuideSection;
import me.nahkd.spigot.miscs.guide.GuideSections;

public class GeneralItemListener implements Listener {
	
	@EventHandler
	public void onItemInteract(PlayerInteractEvent event) {
		if (event.getItem() != null && event.getItem().isSimilar(GuideItems.guide_book)) {
			GuideBookData data = GuideBookManager.getData(event.getPlayer());
			event.getPlayer().openInventory(data.getView());
		}
	}
	
	@EventHandler
	public void onInventoryInteract(InventoryClickEvent event) {
		if (event.getInventory().getTitle().equalsIgnoreCase("񖶊The Ultimate Guide for Dumbass")) {
			event.setCancelled(true);
			Player player = Bukkit.getPlayer(event.getWhoClicked().getName());
			GuideBookData data = GuideBookManager.getData(player);
			ArrayList<GuideSection> ss = new ArrayList<GuideSection>();
			if (GuideItems.next_page_avaliable.isSimilar(event.getCurrentItem())) {
				data.sectionSelectPage++;
				event.getWhoClicked().openInventory(data.getView());
			} else if (GuideItems.prev_page_avaliable.isSimilar(event.getCurrentItem())) {
				data.sectionSelectPage--;
				event.getWhoClicked().openInventory(data.getView());
			} else {
				for (Entry<String, GuideSection> entry : GuideSections.getMap().entrySet()) ss.add(entry.getValue());
				for (GuideSection section : ss) {
					if (section.display.isSimilar(event.getCurrentItem())) {
						data.mode = ViewMode.ITEMS;
						data.itemSelectPage = 0;
						data.selectedSection = section;
						event.getWhoClicked().openInventory(data.getView());
					}
				}
			}
		} else if (event.getInventory().getTitle().equalsIgnoreCase("񗉶The Ultimate Guide for Dumbass")) {
			event.setCancelled(true);
			Player player = Bukkit.getPlayer(event.getWhoClicked().getName());
			GuideBookData data = GuideBookManager.getData(player);
			if (GuideItems.back_view.isSimilar(event.getCurrentItem())) {
				data.mode = ViewMode.SECTIONS;
				event.getWhoClicked().openInventory(data.getView());
			} else if (GuideItems.next_page_avaliable.isSimilar(event.getCurrentItem())) {
				data.itemSelectPage++;
				event.getWhoClicked().openInventory(data.getView());
			} else if (GuideItems.prev_page_avaliable.isSimilar(event.getCurrentItem())) {
				data.itemSelectPage--;
				event.getWhoClicked().openInventory(data.getView());
			} else {
				for (CustomItem i : data.selectedSection.items) {
					if (i.item.isSimilar(event.getCurrentItem())) {
						data.mode = ViewMode.RECIPE;
						data.selectedItem = i;
						event.getWhoClicked().openInventory(data.getView());
					}
				}
			}
		} else if (event.getInventory().getTitle().equalsIgnoreCase("񗝟Recipe View")) {
			event.setCancelled(true);
			Player player = Bukkit.getPlayer(event.getWhoClicked().getName());
			GuideBookData data = GuideBookManager.getData(player);
			if (GuideItems.back_view.isSimilar(event.getCurrentItem())) {
				data.mode = ViewMode.ITEMS;
				event.getWhoClicked().openInventory(data.getView());
			} else if (data.selectedItem.item.isSimilar(event.getCurrentItem())) {
				if (player.getGameMode() == GameMode.CREATIVE) {
					player.getInventory().addItem(data.selectedItem.item);
				}
			} else if (event.getClickedInventory().getTitle().equals("񗝟Recipe View")) {
				for (String key : CustomItems.getMap().keySet()) {
					CustomItem i = CustomItems.getMap().get(key);
					if (i.item.isSimilar(event.getCurrentItem())) {
						data.selectedItem = i;
						event.getWhoClicked().openInventory(data.getView());
						return;
					}
				}
			}
		} else if (event.getInventory().getTitle().startsWith("")) {
			event.setCancelled(true);
			String[] rawData = event.getInventory().getItem(16).getItemMeta().getLore().get(2).substring(14).split("\\|");
			Location refLoc = new Location(Bukkit.getWorld(rawData[0]), Integer.parseInt(rawData[1]), Integer.parseInt(rawData[2]), Integer.parseInt(rawData[3]));
			BlockData data = WorldsData.getDataMap().get(refLoc.getWorld()).blockData.queryData(refLoc);
			if (data != null) {
				if (PowerableUltils.gui_interface_top_off.isSimilar(event.getCurrentItem())) {
					ArrayList<Interfaces> is = Interfaces.getFromString(data.otherData.getString("interface.power.in", "b"));
					is.add(Interfaces.Top);
					data.otherData.set("interface.power.in", Interfaces.composeToString(is.toArray(new Interfaces[0])));
				} else if (PowerableUltils.gui_interface_top_on.isSimilar(event.getCurrentItem())) {
					ArrayList<Interfaces> is = Interfaces.getFromString(data.otherData.getString("interface.power.in", "b"));
					ArrayList<Interfaces> is2 = Interfaces.getFromString(data.otherData.getString("interface.power.out", "t"));
					is2.add(Interfaces.Top);
					is.remove(Interfaces.Top);
					data.otherData.set("interface.power.in", Interfaces.composeToString(is.toArray(new Interfaces[0])));
					data.otherData.set("interface.power.out", Interfaces.composeToString(is2.toArray(new Interfaces[0])));
				} else if (PowerableUltils.gui_interface_top_on_out.isSimilar(event.getCurrentItem())) {
					ArrayList<Interfaces> is = Interfaces.getFromString(data.otherData.getString("interface.power.out", "t"));
					is.remove(Interfaces.Top);
					data.otherData.set("interface.power.out", Interfaces.composeToString(is.toArray(new Interfaces[0])));
				}
				
				if (PowerableUltils.gui_interface_bottom_off.isSimilar(event.getCurrentItem())) {
					ArrayList<Interfaces> is = Interfaces.getFromString(data.otherData.getString("interface.power.in", "b"));
					is.add(Interfaces.Bottom);
					data.otherData.set("interface.power.in", Interfaces.composeToString(is.toArray(new Interfaces[0])));
				} else if (PowerableUltils.gui_interface_bottom_on.isSimilar(event.getCurrentItem())) {
					ArrayList<Interfaces> is = Interfaces.getFromString(data.otherData.getString("interface.power.in", "b"));
					ArrayList<Interfaces> is2 = Interfaces.getFromString(data.otherData.getString("interface.power.out", "t"));
					is2.add(Interfaces.Bottom);
					is.remove(Interfaces.Bottom);
					data.otherData.set("interface.power.in", Interfaces.composeToString(is.toArray(new Interfaces[0])));
					data.otherData.set("interface.power.out", Interfaces.composeToString(is2.toArray(new Interfaces[0])));
				} else if (PowerableUltils.gui_interface_bottom_on_out.isSimilar(event.getCurrentItem())) {
					ArrayList<Interfaces> is = Interfaces.getFromString(data.otherData.getString("interface.power.out", "t"));
					is.remove(Interfaces.Bottom);
					data.otherData.set("interface.power.out", Interfaces.composeToString(is.toArray(new Interfaces[0])));
				}
				
				if (PowerableUltils.gui_interface_north_off.isSimilar(event.getCurrentItem())) {
					ArrayList<Interfaces> is = Interfaces.getFromString(data.otherData.getString("interface.power.in", "b"));
					is.add(Interfaces.North);
					data.otherData.set("interface.power.in", Interfaces.composeToString(is.toArray(new Interfaces[0])));
				} else if (PowerableUltils.gui_interface_north_on.isSimilar(event.getCurrentItem())) {
					ArrayList<Interfaces> is = Interfaces.getFromString(data.otherData.getString("interface.power.in", "b"));
					ArrayList<Interfaces> is2 = Interfaces.getFromString(data.otherData.getString("interface.power.out", "t"));
					is2.add(Interfaces.North);
					is.remove(Interfaces.North);
					data.otherData.set("interface.power.in", Interfaces.composeToString(is.toArray(new Interfaces[0])));
					data.otherData.set("interface.power.out", Interfaces.composeToString(is2.toArray(new Interfaces[0])));
				} else if (PowerableUltils.gui_interface_north_on_out.isSimilar(event.getCurrentItem())) {
					ArrayList<Interfaces> is = Interfaces.getFromString(data.otherData.getString("interface.power.out", "t"));
					is.remove(Interfaces.North);
					data.otherData.set("interface.power.out", Interfaces.composeToString(is.toArray(new Interfaces[0])));
				}
				
				if (PowerableUltils.gui_interface_south_off.isSimilar(event.getCurrentItem())) {
					ArrayList<Interfaces> is = Interfaces.getFromString(data.otherData.getString("interface.power.in", "b"));
					is.add(Interfaces.South);
					data.otherData.set("interface.power.in", Interfaces.composeToString(is.toArray(new Interfaces[0])));
				} else if (PowerableUltils.gui_interface_south_on.isSimilar(event.getCurrentItem())) {
					ArrayList<Interfaces> is = Interfaces.getFromString(data.otherData.getString("interface.power.in", "b"));
					ArrayList<Interfaces> is2 = Interfaces.getFromString(data.otherData.getString("interface.power.out", "t"));
					is2.add(Interfaces.South);
					is.remove(Interfaces.South);
					data.otherData.set("interface.power.in", Interfaces.composeToString(is.toArray(new Interfaces[0])));
					data.otherData.set("interface.power.out", Interfaces.composeToString(is2.toArray(new Interfaces[0])));
				} else if (PowerableUltils.gui_interface_south_on_out.isSimilar(event.getCurrentItem())) {
					ArrayList<Interfaces> is = Interfaces.getFromString(data.otherData.getString("interface.power.out", "t"));
					is.remove(Interfaces.South);
					data.otherData.set("interface.power.out", Interfaces.composeToString(is.toArray(new Interfaces[0])));
				}
				
				if (PowerableUltils.gui_interface_east_off.isSimilar(event.getCurrentItem())) {
					ArrayList<Interfaces> is = Interfaces.getFromString(data.otherData.getString("interface.power.in", "b"));
					is.add(Interfaces.East);
					data.otherData.set("interface.power.in", Interfaces.composeToString(is.toArray(new Interfaces[0])));
				} else if (PowerableUltils.gui_interface_east_on.isSimilar(event.getCurrentItem())) {
					ArrayList<Interfaces> is = Interfaces.getFromString(data.otherData.getString("interface.power.in", "b"));
					ArrayList<Interfaces> is2 = Interfaces.getFromString(data.otherData.getString("interface.power.out", "t"));
					is2.add(Interfaces.East);
					is.remove(Interfaces.East);
					data.otherData.set("interface.power.in", Interfaces.composeToString(is.toArray(new Interfaces[0])));
					data.otherData.set("interface.power.out", Interfaces.composeToString(is2.toArray(new Interfaces[0])));
				} else if (PowerableUltils.gui_interface_east_on_out.isSimilar(event.getCurrentItem())) {
					ArrayList<Interfaces> is = Interfaces.getFromString(data.otherData.getString("interface.power.out", "t"));
					is.remove(Interfaces.East);
					data.otherData.set("interface.power.out", Interfaces.composeToString(is.toArray(new Interfaces[0])));
				}
				
				if (PowerableUltils.gui_interface_west_off.isSimilar(event.getCurrentItem())) {
					ArrayList<Interfaces> is = Interfaces.getFromString(data.otherData.getString("interface.power.in", "b"));
					is.add(Interfaces.West);
					data.otherData.set("interface.power.in", Interfaces.composeToString(is.toArray(new Interfaces[0])));
				} else if (PowerableUltils.gui_interface_west_on.isSimilar(event.getCurrentItem())) {
					ArrayList<Interfaces> is = Interfaces.getFromString(data.otherData.getString("interface.power.in", "b"));
					ArrayList<Interfaces> is2 = Interfaces.getFromString(data.otherData.getString("interface.power.out", "t"));
					is2.add(Interfaces.West);
					is.remove(Interfaces.West);
					data.otherData.set("interface.power.in", Interfaces.composeToString(is.toArray(new Interfaces[0])));
					data.otherData.set("interface.power.out", Interfaces.composeToString(is2.toArray(new Interfaces[0])));
				} else if (PowerableUltils.gui_interface_west_on_out.isSimilar(event.getCurrentItem())) {
					ArrayList<Interfaces> is = Interfaces.getFromString(data.otherData.getString("interface.power.out", "t"));
					is.remove(Interfaces.West);
					data.otherData.set("interface.power.out", Interfaces.composeToString(is.toArray(new Interfaces[0])));
				}
			}
			event.getWhoClicked().openInventory(PowerableUltils.generateView(data));
			return;
		}
 	}
	
}
