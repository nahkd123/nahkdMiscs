package me.nahkd.spigot.miscs.defaults.other;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import me.nahkd.spigot.miscs.api.ItemBuilder;
import me.nahkd.spigot.miscs.api.looting.LootEntry;
import me.nahkd.spigot.miscs.api.looting.LootTable;
import me.nahkd.spigot.miscs.defaults.DefaultItems;

public class NatureAndGardeningLoots {

	public static LootTable crook_leaves = new LootTable(
			new LootEntry(0.2, new ItemStack(Material.SAPLING)),
			new LootEntry(0.15, new ItemStack(Material.APPLE)),
			new LootEntry(0.2, new ItemStack(Material.STICK)),
			new LootEntry(0.01, DefaultItems.NATURE_LUCKY_APPLE.item),
			new LootEntry(0.05, DefaultItems.NATURE_SILK_THREAD.item)
	);
	public static LootTable crook_grass = new LootTable(
			new LootEntry(0.1, new ItemStack(Material.SAPLING)),
			new LootEntry(0.25, new ItemStack(Material.SEEDS)),
			new LootEntry(0.05, new ItemStack(Material.MELON_SEEDS)),
			new LootEntry(0.05, new ItemStack(Material.PUMPKIN_SEEDS)),
			new LootEntry(0.05, new ItemStack(Material.BEETROOT_SEEDS)),
			new LootEntry(0.05, new ItemStack(Material.COCOA)),
			new LootEntry(0.075, new ItemStack(Material.SUGAR_CANE)),
			new LootEntry(0.01, DefaultItems.NATURE_WEED_DRUG.item)
	);
	
}
