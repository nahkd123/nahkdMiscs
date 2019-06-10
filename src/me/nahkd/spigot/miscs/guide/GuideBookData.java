package me.nahkd.spigot.miscs.guide;

import java.util.ArrayList;
import java.util.Map.Entry;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import me.nahkd.spigot.miscs.api.items.CustomItem;

public class GuideBookData {

	public static int[] mappedMatrix = new int[] {12, 13, 14, 21, 22, 23, 30, 31, 32};
	
	public int sectionSelectPage;
	public int itemSelectPage;
	public CustomItem selectedItem;
	public GuideSection selectedSection;
	public ViewMode mode;
	public Player player;
	
	public GuideBookData(Player player) {
		this.player = player;
		this.sectionSelectPage = 0;
		this.itemSelectPage = 0;
		this.selectedItem = null;
		this.mode = ViewMode.SECTIONS;
	}
	
	public Inventory getView() {
		if (mode == ViewMode.SECTIONS) {
			// Sections view mode
			Inventory inv = Bukkit.createInventory(null, 54, "񖶊The Ultimate Guide for Dumbass");
			for (int slot : GuideItems.border_default) inv.setItem(slot, GuideItems.border);
			
			// Put all sections in a page (heh)
			ArrayList<GuideSection> ss = new ArrayList<GuideSection>();
			for (Entry<String, GuideSection> entry : GuideSections.getMap().entrySet()) ss.add(entry.getValue());
			boolean nextPageAvaliable = true;
			for (int i = 0; i < 28; i++) {
				int arrayIndex = this.sectionSelectPage * 28 + i;
				if (arrayIndex >= ss.size()) {
					nextPageAvaliable = false;
					System.out.println(!nextPageAvaliable);
					break;
				}
				inv.addItem(ss.get(arrayIndex).display);
			}
			if (this.sectionSelectPage > 0) inv.setItem(47, GuideItems.prev_page_avaliable);
			if (nextPageAvaliable) inv.setItem(51, GuideItems.next_page_avaliable);
			return inv;
		} else if (mode == ViewMode.ITEMS) {
			// Sections view mode
			Inventory inv = Bukkit.createInventory(null, 54, "񗉶The Ultimate Guide for Dumbass");
			for (int slot : GuideItems.border_default) inv.setItem(slot, GuideItems.border);
			
			// Put all items in a page
			ArrayList<CustomItem> is = selectedSection.items;
			boolean nextPageAvaliable = true;
			for (int i = 0; i < 28; i++) {
				int arrayIndex = this.itemSelectPage * 28 + i;
				if (arrayIndex >= is.size()) {
					nextPageAvaliable = false;
					break;
				}
				inv.addItem(is.get(arrayIndex).item);
			}
			if (this.itemSelectPage > 0) inv.setItem(47, GuideItems.prev_page_avaliable);
			if (nextPageAvaliable) inv.setItem(51, GuideItems.next_page_avaliable);
			inv.setItem(49, GuideItems.back_view);
			return inv;
		} else if (mode == ViewMode.RECIPE) {
			Inventory inv = Bukkit.createInventory(null, 54, "񗝟Recipe View");
			
			// Show in 9x9 matrix (mapping)
			/*inv.setItem(12, this.selectedItem.recipe.items[0]);
			inv.setItem(13, this.selectedItem.recipe.items[1]);
			inv.setItem(14, this.selectedItem.recipe.items[2]);
			inv.setItem(21, this.selectedItem.recipe.items[3]);
			inv.setItem(22, this.selectedItem.recipe.items[4]);
			inv.setItem(23, this.selectedItem.recipe.items[5]);
			inv.setItem(30, this.selectedItem.recipe.items[6]);
			inv.setItem(31, this.selectedItem.recipe.items[7]);
			inv.setItem(32, this.selectedItem.recipe.items[8]);*/
			for (int i = 0; i < this.selectedItem.recipe.items.length; i++) {
				if (i >= 9) break;
				ItemStack i2 = null;
				if (this.selectedItem.recipe.items[i] != null) {
					i2 = new ItemStack(this.selectedItem.recipe.items[i]);
					i2.setAmount(1);
				}
				inv.setItem(mappedMatrix[i], i2);
			}
			
			inv.setItem(0, this.selectedItem.item);
			inv.setItem(8, this.selectedItem.recipe.type.display);
			for (int i = 45; i < 54; i++) inv.setItem(i, GuideItems.border);
			inv.setItem(49, GuideItems.back_view);
			return inv;
		}
		return null;
	}
	
	public enum ViewMode {
		SECTIONS,
		ITEMS,
		RECIPE
	}
	
}
