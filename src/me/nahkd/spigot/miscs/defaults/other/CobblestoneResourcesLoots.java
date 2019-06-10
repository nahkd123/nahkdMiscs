package me.nahkd.spigot.miscs.defaults.other;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import me.nahkd.spigot.miscs.api.looting.LootEntry;
import me.nahkd.spigot.miscs.api.looting.LootTable;
import me.nahkd.spigot.miscs.defaults.DefaultItems;

public class CobblestoneResourcesLoots {

	public static LootTable sieve_gravel = new LootTable(
			new LootEntry(0.007, new ItemStack(Material.DIAMOND)),
			new LootEntry(0.02, new ItemStack(Material.CLAY)),
			new LootEntry(0.03, new ItemStack(Material.FLINT)),
			new LootEntry(0.08, DefaultItems.COBBLERES_DUSTS_ALUMINIUM.item),
			new LootEntry(0.07, DefaultItems.COBBLERES_DUSTS_COPPER.item),
			new LootEntry(0.07, DefaultItems.COBBLERES_DUSTS_IRON.item),
			new LootEntry(0.04, DefaultItems.COBBLERES_DUSTS_GOLD.item),
			new LootEntry(0.04, DefaultItems.COBBLERES_DUSTS_SLIVER.item),
			new LootEntry(0.10, DefaultItems.COBBLERES_DUSTS_COAL.item),
			new LootEntry(0.09, DefaultItems.COBBLERES_DUSTS_LEAD.item),
			new LootEntry(0.05, DefaultItems.COBBLERES_DUSTS_NICKEL.item),
			new LootEntry(0.05, DefaultItems.COBBLERES_DUSTS_COBALT.item),
			new LootEntry(0.06, DefaultItems.COBBLERES_DUSTS_OSMIUM.item),
			new LootEntry(0.03, DefaultItems.COBBLERES_DUSTS_PLATINUM.item),
			new LootEntry(0.08, DefaultItems.COBBLERES_DUSTS_TIN.item)
	);
	public static LootTable sieve_sand = new LootTable(
			new LootEntry(0.02, new ItemStack(Material.REDSTONE)),
			new LootEntry(0.02, new ItemStack(Material.GLOWSTONE)),
			new LootEntry(0.05, DefaultItems.COBBLERES_DUSTS_ALUMINIUM.item),
			new LootEntry(0.04, DefaultItems.COBBLERES_DUSTS_COPPER.item),
			new LootEntry(0.03, DefaultItems.COBBLERES_DUSTS_IRON.item),
			new LootEntry(0.005, DefaultItems.COBBLERES_DUSTS_GOLD.item),
			new LootEntry(0.025, DefaultItems.COBBLERES_DUSTS_SLIVER.item),
			new LootEntry(0.03, DefaultItems.COBBLERES_DUSTS_COAL.item),
			new LootEntry(0.02, DefaultItems.COBBLERES_DUSTS_LEAD.item),
			new LootEntry(0.03, DefaultItems.COBBLERES_DUSTS_NICKEL.item),
			new LootEntry(0.01, DefaultItems.COBBLERES_DUSTS_COBALT.item),
			new LootEntry(0.02, DefaultItems.COBBLERES_DUSTS_OSMIUM.item),
			new LootEntry(0.005, DefaultItems.COBBLERES_DUSTS_PLATINUM.item),
			new LootEntry(0.02, DefaultItems.COBBLERES_DUSTS_TIN.item)
	);
	
	public static LootTable sieve_dirt = new LootTable(
			new LootEntry(0.05, new ItemStack(Material.SEEDS)),
			new LootEntry(0.05, new ItemStack(Material.PUMPKIN_SEEDS)),
			new LootEntry(0.05, new ItemStack(Material.MELON_SEEDS)),
			new LootEntry(0.05, new ItemStack(Material.BEETROOT_SEEDS)),
			new LootEntry(0.05, new ItemStack(Material.COCOA)),
			new LootEntry(0.075, new ItemStack(Material.INK_SACK, 0, (short) 15))
	);
}
