package me.nahkd.spigot.miscs.api.utils;

import java.util.Random;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

import me.nahkd.spigot.miscs.api.tinkering.TinkerItem;
import me.nahkd.spigot.miscs.api.tinkering.TinkerItem.MiningLevel;

public class BlockUltils {
	
	public static Random randomizer;
	
	public static Random getRandomizerSafe() {
		if (randomizer == null) randomizer = new Random();
		return randomizer;
	}
	
	public static void breakBlock(Location loc, ItemStack item) {
		Material block = loc.getBlock().getType();
		MiningLevel l = MiningLevel.getLevelFromMaterial(item.getType());
		if (TinkerItem.checkItem(item)) {
			l = TinkerItem.fromItem(item).level;
		}
		if (block == Material.CHEST || block == Material.TRAPPED_CHEST) {
			loc.getBlock().breakNaturally();
			return;
		}
		if (item.containsEnchantment(Enchantment.SILK_TOUCH)) {
			loc.getWorld().dropItem(loc, new ItemStack(block));
			loc.getBlock().setType(Material.AIR);
			return;
		}
		if (item.containsEnchantment(Enchantment.LOOT_BONUS_BLOCKS)) {
			if (block == Material.COBBLESTONE || block == Material.STONE) loc.getWorld().dropItem(loc, new ItemStack(Material.COBBLESTONE));
			if (block == Material.LOG || 
					block == Material.LOG_2 ||
					block == Material.IRON_ORE || 
					block == Material.GOLD_ORE) loc.getWorld().dropItem(loc, new ItemStack(block));
			if (block == Material.COAL_ORE) loc.getWorld().dropItem(loc, new ItemStack(Material.COAL, 1 + getRandomizerSafe().nextInt(item.getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS))));
			if (block == Material.LAPIS_ORE && (l == MiningLevel.STONE || l == MiningLevel.IRON || l == MiningLevel.DIAMOND)) loc.getWorld().dropItem(loc, new ItemStack(Material.INK_SACK, 8 + getRandomizerSafe().nextInt(item.getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS)) * 3, (short) 4));
			if (block == Material.REDSTONE_ORE && (l == MiningLevel.IRON || l == MiningLevel.DIAMOND)) loc.getWorld().dropItem(loc, new ItemStack(Material.REDSTONE, 5 + (getRandomizerSafe().nextInt(item.getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS)) * 3)));
			if (block == Material.DIAMOND_ORE && (l == MiningLevel.IRON || l == MiningLevel.DIAMOND)) loc.getWorld().dropItem(loc, new ItemStack(Material.DIAMOND, 1 + getRandomizerSafe().nextInt(item.getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS))));
			if (block == Material.EMERALD_ORE && (l == MiningLevel.IRON || l == MiningLevel.DIAMOND)) loc.getWorld().dropItem(loc, new ItemStack(Material.EMERALD, 1 + getRandomizerSafe().nextInt(item.getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS))));
			loc.getBlock().setType(Material.AIR);
			return;
		}
		loc.getBlock().breakNaturally(new ItemStack(MiningLevel.getMaterialByMiningLevel(item.getType(), l)));
		return;
	}
	
}
