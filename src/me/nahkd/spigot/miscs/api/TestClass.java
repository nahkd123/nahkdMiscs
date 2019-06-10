package me.nahkd.spigot.miscs.api;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;

import me.nahkd.spigot.miscs.api.tinkering.TinkerItem;

public class TestClass {
	
	/**
	 * Open a workbench interface without any recipe avaliable
	 * @param player The player to open hah
	 */
	public static void test0(Player player) {
		player.openInventory(Bukkit.createInventory(null, InventoryType.WORKBENCH, ""));
	}
	
	public static void testTinkersTool(Player player, ItemStack item) {
		if (TinkerItem.checkItem(item)) {
			TinkerItem i = TinkerItem.fromItem(item);
			player.sendMessage("§6Tool level: " + i.toolLevel);
			player.sendMessage("§6EXP: " + i.currentExp);
			player.sendMessage("§eModifier slots (empty): " + i.emptyModifierSlots);
		} else {
			player.sendMessage("§aResult: §cItem hold in hand isn't tinkers's tool");
		}
	}
	
}
