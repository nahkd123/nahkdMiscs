package me.nahkd.spigot.miscs.api.utils;

import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

public class PlayerInventoryUltils {
	
	public static boolean canStack(ItemStack source, ItemStack cursor) {
		if (source == null || source.getType() == Material.AIR) return true;
		if (!source.isSimilar(cursor)) return false;
		if (source.getAmount() + cursor.getAmount() > source.getType().getMaxStackSize()) return false;
		return true;
	}
	
	public static ItemStack stack(ItemStack source, ItemStack cursor) {
		if (canStack(source, cursor)) {
			source.setAmount(source.getAmount() + cursor.getAmount());
			return new ItemStack(source);
		} else return source;
	}

	public static void addToInventory(Player player, ItemStack ref, int newAmount) {
		ItemStack clone = new ItemStack(ref);
		clone.setAmount(1);
		for (int i = 0; i < newAmount * ref.getAmount(); i++) {
			if (hasEmptySpace(player)) {
				player.getInventory().addItem(clone);
			} else {
				player.getWorld().dropItem(player.getLocation(), clone);
			}
		}
	}
	public static void addToInventory(HumanEntity player, ItemStack ref, int newAmount) {
		ItemStack clone = new ItemStack(ref);
		clone.setAmount(1);
		for (int i = 0; i < newAmount * ref.getAmount(); i++) {
			if (hasEmptySpace(player)) {
				player.getInventory().addItem(clone);
			} else {
				player.getWorld().dropItem(player.getLocation(), clone);
			}
		}
	}

	public static boolean hasEmptySpace(Player player) {
		for (ItemStack i : player.getInventory().getStorageContents()) {
			if (i == null) return true;
		}
		return false;
	}
	public static boolean hasEmptySpace(HumanEntity player) {
		for (ItemStack i : player.getInventory().getStorageContents()) {
			if (i == null) return true;
		}
		return false;
	}
	
	public static void replaceHandWith(Player player, ItemStack ref, EquipmentSlot slot, ItemStack... items) {
		if (slot == null) slot = EquipmentSlot.HAND;
		for (int i = 0; i < items.length; i++) {
			if (i == items.length - 1) return;
			if (slot == EquipmentSlot.HAND) if (items[i].isSimilar(ref)) player.getInventory().setItemInMainHand(items[i + 1]);
			if (slot == EquipmentSlot.OFF_HAND) if (items[i].isSimilar(ref)) player.getInventory().setItemInOffHand(items[i + 1]);
		}
	}
	
}
