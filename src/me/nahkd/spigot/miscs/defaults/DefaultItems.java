package me.nahkd.spigot.miscs.defaults;

import java.util.ArrayList;
import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Sign;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.MaterialData;
import org.bukkit.scheduler.BukkitRunnable;

import me.nahkd.spigot.miscs.nahkdMiscs;
import me.nahkd.spigot.miscs.api.ItemBuilder;
import me.nahkd.spigot.miscs.api.blocks.BlockData;
import me.nahkd.spigot.miscs.api.blocks.BlockTicker;
import me.nahkd.spigot.miscs.api.blocks.CustomBlock;
import me.nahkd.spigot.miscs.api.blocks.CustomBlocks;
import me.nahkd.spigot.miscs.api.blocks.DropChanger;
import me.nahkd.spigot.miscs.api.items.CustomItem;
import me.nahkd.spigot.miscs.api.items.ItemBlockInteract;
import me.nahkd.spigot.miscs.api.power.Capacitor;
import me.nahkd.spigot.miscs.api.power.Generator;
import me.nahkd.spigot.miscs.api.power.GeneratorItemEntry;
import me.nahkd.spigot.miscs.api.power.PowerableUltils;
import me.nahkd.spigot.miscs.api.tinkering.TinkerItem;
import me.nahkd.spigot.miscs.api.utils.ItemStackUltils;
import me.nahkd.spigot.miscs.api.world.WorldData;
import me.nahkd.spigot.miscs.api.world.WorldsData;
import me.nahkd.spigot.miscs.defaults.other.CobblestoneResourcesLoots;

public class DefaultItems {
	
	public static CustomItem ADVANCED_WORKBENCH = new CustomItem("ADVANCED_WORKBENCH", new ItemBuilder(Material.WORKBENCH).name("§6Advanced Workbench").addLore("", "§6Place §7to place (ofc lul)", "").create(), RecipeTypes.VANILLA_RECIPE, new ItemStack[] {
			null, new ItemStack(Material.WOOD), null,
			new ItemStack(Material.WOOD), new ItemStack(Material.WORKBENCH), new ItemStack(Material.WOOD),
			null, new ItemStack(Material.WOOD), null
	});
	
	// Nature & Gardening
	public static CustomItem NATURE_SILK_THREAD = new CustomItem(DefaultSections.NATURE_AND_GARDENING, "NATURE_SILK_THREAD", new ItemBuilder(Material.STRING).name("§fWorm's Silk Thread").addLore("", "§7Those little threads...", "§7are hard to see!").create(), RecipeTypes.CROOK_LOOTING, new ItemStack[] {new ItemStack(Material.LEAVES), new ItemStack(Material.LEAVES_2)});
	public static CustomItem NATURE_LUCKY_APPLE = new CustomItem(DefaultSections.NATURE_AND_GARDENING, "NATURE_LUCKY_APPLE", new ItemBuilder(Material.GOLDEN_APPLE).name("§6Lucky Apple").addLore("", "§7The apple from farmer's tree").create(), RecipeTypes.CROOK_LOOTING, new ItemStack[] {new ItemStack(Material.LEAVES), new ItemStack(Material.LEAVES_2)});
	public static CustomItem NATURE_WEED_DRUG = new CustomItem(DefaultSections.NATURE_AND_GARDENING, "NATURE_WEED_DRUG", new ItemBuilder(Material.SUGAR_CANE).name("§bWeed §c(Drug)").addLore("", "§7Get high by smoking it...", "§7oh no...").create(), RecipeTypes.CROOK_LOOTING, new ItemStack[] {new ItemStack(Material.LONG_GRASS)});
	
	public static CustomItem NATURE_CROOK = new CustomItem(DefaultSections.NATURE_AND_GARDENING, "NATURE_CROOK", new ItemBuilder(Material.WOOD_HOE).name("§7Crook").addLore("", "§6Destroy §7leaves faster", "", "§7This will gives you even", "§7more saplings and other", "§7things!", "").create(), RecipeTypes.WORKBENCH, new ItemStack[] {
			new ItemStack(Material.STICK), new ItemStack(Material.STICK), null,
			null, new ItemStack(Material.STICK), null,
			null, new ItemStack(Material.STICK), new ItemStack(Material.STICK)
	});
	public static CustomItem NATURE_VANILLA_STRING = new CustomItem(DefaultSections.NATURE_AND_GARDENING, "NATURE_VANILLA_STRING", new ItemStack(Material.STRING), RecipeTypes.WORKBENCH, new ItemStack[] {
			NATURE_SILK_THREAD.item, NATURE_SILK_THREAD.item, null,
			NATURE_SILK_THREAD.item, NATURE_SILK_THREAD.item, null,
			null, null, null
	});
	public static CustomItem NATURE_FERTILIZER = new CustomItem(DefaultSections.NATURE_AND_GARDENING, "NATURE_FERTILIZER", new ItemBuilder(Material.INK_SACK, 3, 15).name("§aBionic Fertilizer").addLore("", "§7Enviroment Friendly", "§7Fertilizer\u2122").create(), RecipeTypes.WORKBENCH, new ItemStack[] {
			new ItemStack(Material.SAPLING), new ItemStack(Material.STICK), new ItemStack(Material.SAPLING),
			new ItemStack(Material.STICK), new ItemStack(Material.SAPLING), new ItemStack(Material.STICK),
			new ItemStack(Material.SAPLING), new ItemStack(Material.STICK), new ItemStack(Material.SAPLING)
	});
	public static CustomItem NATURE_GRASS_SEED = new CustomItem(DefaultSections.NATURE_AND_GARDENING, "NATURE_GRASS_SEED", new ItemBuilder(Material.PUMPKIN_SEEDS, 4, 0).name("§aGrass Seed").addLore("", "§7Can we start watering now?").create(), RecipeTypes.WORKBENCH, new ItemStack[] {
			null, NATURE_FERTILIZER.item, null,
			NATURE_FERTILIZER.item, new ItemStack(Material.SAPLING), NATURE_FERTILIZER.item,
			null, NATURE_FERTILIZER.item, null
	});
	public static CustomItem NATURE_VANILLA_DIRT = new CustomItem(DefaultSections.NATURE_AND_GARDENING, "NATURE_VANILLA_DIRT", new ItemStack(Material.DIRT), RecipeTypes.WORKBENCH, new ItemStack[] {
			NATURE_FERTILIZER.item, NATURE_FERTILIZER.item, NATURE_FERTILIZER.item,
			NATURE_FERTILIZER.item, new ItemStack(Material.SAPLING), NATURE_FERTILIZER.item,
			NATURE_FERTILIZER.item, NATURE_FERTILIZER.item, NATURE_FERTILIZER.item
	});
	public static CustomItem NATURE_FIRESTARTER = new CustomItem(DefaultSections.NATURE_AND_GARDENING, "NATURE_FIRESTARTER", new ItemBuilder(Material.STICK, 4, 0).name("§7Firestarter (Unused)").addLore("", "§7Create fire from wood!").create(), RecipeTypes.WORKBENCH, new ItemStack[] {
			new ItemStack(Material.STRING), new ItemStack(Material.WOOD), new ItemStack(Material.STRING),
			new ItemStack(Material.STRING), new ItemStack(Material.WOOD), new ItemStack(Material.STRING),
			new ItemStack(Material.STRING), new ItemStack(Material.WOOD), new ItemStack(Material.STRING)
	});
	public static CustomItem NATURE_FIRESTARTER_USED = new CustomItem(DefaultSections.NATURE_AND_GARDENING, "NATURE_FIRESTARTER_USED", new ItemBuilder(Material.STICK).name("§cFirestarter (Used)").addLore("", "§7Look like coal stick...").create(), RecipeTypes.RIGHT_CLICKING, new ItemStack[] {DefaultItems.NATURE_FIRESTARTER.item, new ItemStack(Material.LOG), new ItemStack(Material.LOG_2)});

	public static CustomItem NATURE_CHARCOAL = new CustomItem(DefaultSections.NATURE_AND_GARDENING, "NATURE_CHARCOAL", new ItemBuilder(Material.COAL, 1, 1).name("§6Charcoal").addLore("", "§7Charcoal but compressed from", "§78 used firestarters").create(), RecipeTypes.WORKBENCH, new ItemStack[] {
			NATURE_FIRESTARTER_USED.item, NATURE_FIRESTARTER_USED.item, NATURE_FIRESTARTER_USED.item,
			NATURE_FIRESTARTER_USED.item, null, NATURE_FIRESTARTER_USED.item,
			NATURE_FIRESTARTER_USED.item, NATURE_FIRESTARTER_USED.item, NATURE_FIRESTARTER_USED.item
	});
	public static CustomItem NATURE_LAVA_CRYSTAL = new CustomItem(DefaultSections.NATURE_AND_GARDENING, "NATURE_LAVA_CRYSTAL", new ItemBuilder(Material.WOOL, 1, 14).name("§cUnactivated Lava Crystal").addLore("", "§7It need some fires").create(), RecipeTypes.WORKBENCH, new ItemStack[] {
			NATURE_CHARCOAL.item, NATURE_CHARCOAL.item, NATURE_CHARCOAL.item,
			NATURE_CHARCOAL.item, NATURE_CHARCOAL.item, NATURE_CHARCOAL.item,
			NATURE_CHARCOAL.item, NATURE_CHARCOAL.item, NATURE_CHARCOAL.item
	});
	public static CustomItem NATURE_LAVA_CRYSTAL_ACTIVATED = new CustomItem(DefaultSections.NATURE_AND_GARDENING, "NATURE_LAVA_CRYSTAL_ACTIVATED", new ItemBuilder(Material.WOOL, 1, 14).name("§cLava Crystal").addLore("", "§fCan §cIgnite §fyourself", "", "§7Is this hot enough?").create(), RecipeTypes.WORKBENCH, new ItemStack[] {
			NATURE_FIRESTARTER.item, NATURE_FIRESTARTER.item, NATURE_FIRESTARTER.item,
			NATURE_FIRESTARTER.item, NATURE_LAVA_CRYSTAL.item, NATURE_FIRESTARTER.item,
			NATURE_FIRESTARTER.item, NATURE_FIRESTARTER.item, NATURE_FIRESTARTER.item
	});
	public static CustomItem NATURE_LIQUID_CONTAINER = new CustomItem(DefaultSections.NATURE_AND_GARDENING, "NATURE_WOOOD_LIQUID_CONTAINER", new ItemBuilder(Material.WOOL, 1, 0).name("§fLiquid Container").addLore("", "§7I guess this is useless", "§7now, since we can get", "§7infinite water...").create(), RecipeTypes.WORKBENCH, new ItemStack[] {
			new ItemStack(Material.WOOD), null, new ItemStack(Material.WOOD),
			new ItemStack(Material.WOOD), null, new ItemStack(Material.WOOD),
			new ItemStack(Material.WOOD), new ItemStack(Material.WOOD), new ItemStack(Material.WOOD) 
	});
	public static CustomItem NATURE_WATER_CRYSTAL = new CustomItem(DefaultSections.NATURE_AND_GARDENING, "NATURE_WATER_CRYSTAL", new ItemBuilder(Material.WOOL, 1, 11).name("§9Water Crystal").addLore("", "§7One more, please.").create(), RecipeTypes.WAIT_FOR_RAIN, new ItemStack[] {});
	public static CustomItem NATURE_INFINITE_WATER_CRYSTAL = new CustomItem(DefaultSections.NATURE_AND_GARDENING, "NATURE_INFINITE_WATER_CRYSTAL", new ItemBuilder(Material.WOOL, 1, 11).name("§bInfinite Water Crystal").addLore("", "§7Portable version of 32m\u00B2", "§7infinite water pool.").create(), RecipeTypes.WORKBENCH, new ItemStack[] {
			null, NATURE_WATER_CRYSTAL.item, null,
			NATURE_WATER_CRYSTAL.item, new ItemStack(Material.WATER_BUCKET), NATURE_WATER_CRYSTAL.item,
			null, NATURE_WATER_CRYSTAL.item, null
	});
	
	public static CustomItem NATURE_WATERING_CAN = new CustomItem(DefaultSections.NATURE_AND_GARDENING, "NATURE_WATERING_CAN", new ItemBuilder(Material.GLASS_BOTTLE).name("§fWatering Can (Empty)").addLore("", "§7You need seeds?").create(), RecipeTypes.WORKBENCH, new ItemStack[] {
			new ItemStack(Material.IRON_INGOT), DefaultItems.NATURE_FERTILIZER.item, null,
			new ItemStack(Material.IRON_INGOT), new ItemStack(Material.BOWL), new ItemStack(Material.IRON_INGOT),
			null, new ItemStack(Material.IRON_INGOT), null
	});
	public static CustomItem NATURE_WATERING_CAN_FILLED_0 = new CustomItem(DefaultSections.NATURE_AND_GARDENING, "NATURE_WATERING_CAN_FILLED_0", new ItemBuilder(Material.POTION, 1, (byte) 0).name("§bWatering Can (100%)").addLore("§b100% filled", "", "§7You need seeds?").create(), RecipeTypes.UNKNOWN, new ItemStack[] {});
	public static CustomItem NATURE_WATERING_CAN_FILLED_1 = new CustomItem(DefaultSections.NATURE_AND_GARDENING, "NATURE_WATERING_CAN_FILLED_1", new ItemBuilder(Material.POTION, 1, (byte) 0).name("§bWatering Can (75%)").addLore("§b75% filled", "", "§7You need seeds?").create(), RecipeTypes.UNKNOWN, new ItemStack[] {});
	public static CustomItem NATURE_WATERING_CAN_FILLED_2 = new CustomItem(DefaultSections.NATURE_AND_GARDENING, "NATURE_WATERING_CAN_FILLED_2", new ItemBuilder(Material.POTION, 1, (byte) 0).name("§bWatering Can (50%)").addLore("§b50% filled", "", "§7You need seeds?").create(), RecipeTypes.UNKNOWN, new ItemStack[] {});
	public static CustomItem NATURE_WATERING_CAN_FILLED_3 = new CustomItem(DefaultSections.NATURE_AND_GARDENING, "NATURE_WATERING_CAN_FILLED_3", new ItemBuilder(Material.POTION, 1, (byte) 0).name("§bWatering Can (25%)").addLore("§b25% filled", "", "§7You need seeds?").create(), RecipeTypes.UNKNOWN, new ItemStack[] {});
	public static CustomItem NATURE_WATERING_CAN_INFINITE = new CustomItem(DefaultSections.NATURE_AND_GARDENING, "NATURE_WATERING_CAN_INFINITE", new ItemBuilder(Material.GLASS_BOTTLE).name("§bInfinite Watering Can (Empty)").addLore("", "§7Just add water!").create(), RecipeTypes.WORKBENCH, new ItemStack[] {
			null, NATURE_WATERING_CAN_FILLED_0.item, null,
			new ItemStack(Material.IRON_INGOT), NATURE_INFINITE_WATER_CRYSTAL.item, new ItemStack(Material.IRON_INGOT),
			null, NATURE_WATERING_CAN_FILLED_0.item, null
	});
	public static CustomItem NATURE_WATERING_CAN_INFINITE_FILLED = new CustomItem(DefaultSections.NATURE_AND_GARDENING, "NATURE_WATERING_CAN_INFINITE_FILLED", new ItemBuilder(Material.POTION, 1, (byte) 0).name("§bInfinite Watering Can").addLore("", "§7You loves gardening, right?").create(), RecipeTypes.UNKNOWN, new ItemStack[] {});
	
	// Cobblestone Resources
	public static CustomItem COBBLERES_TOOLS_STONE_HAMMER = new CustomItem(DefaultSections.COBBLESTONE_RESOURCES, "COBBLERES_TOOLS_STONE_HAMMER", new ItemBuilder(Material.STONE_AXE).name("§7Stone Hammer").addLore("", "§7Oh yea you'll need", "§7some gravels").create(), RecipeTypes.WORKBENCH, new ItemStack[] {
			null, new ItemStack(Material.COBBLESTONE), null,
			new ItemStack(Material.COBBLESTONE), new ItemStack(Material.STICK), null,
			null, null, new ItemStack(Material.STICK)
	});
	public static CustomItem COBBLERES_TOOLS_IRON_HAMMER = new CustomItem(DefaultSections.COBBLESTONE_RESOURCES, "COBBLERES_TOOLS_IRON_HAMMER", new ItemBuilder(Material.IRON_AXE).name("§fIron Hammer").addLore("", "§7Oh yea you'll need", "§7some gravels").create(), RecipeTypes.WORKBENCH, new ItemStack[] {
			null, new ItemStack(Material.IRON_INGOT), null,
			new ItemStack(Material.IRON_INGOT), new ItemStack(Material.STICK), null,
			null, null, new ItemStack(Material.STICK)
	});
	public static CustomItem COBBLERES_TOOLS_GOLD_HAMMER = new CustomItem(DefaultSections.COBBLESTONE_RESOURCES, "COBBLERES_TOOLS_GOLD_HAMMER", new ItemBuilder(Material.GOLD_AXE).name("§6Gold Hammer").addLore("", "§7Oh yea you'll need", "§7some gravels").create(), RecipeTypes.WORKBENCH, new ItemStack[] {
			null, new ItemStack(Material.GOLD_INGOT), null,
			new ItemStack(Material.GOLD_INGOT), new ItemStack(Material.STICK), null,
			null, null, new ItemStack(Material.STICK)
	});
	public static CustomItem COBBLERES_TOOLS_DIAMOND_HAMMER = new CustomItem(DefaultSections.COBBLESTONE_RESOURCES, "COBBLERES_TOOLS_DIAMOND_HAMMER", new ItemBuilder(Material.DIAMOND_AXE).name("§bDiamond Hammer").addLore("", "§7Oh yea you'll need", "§7some gravels").create(), RecipeTypes.WORKBENCH, new ItemStack[] {
			null, new ItemStack(Material.DIAMOND), null,
			new ItemStack(Material.DIAMOND), new ItemStack(Material.STICK), null,
			null, null, new ItemStack(Material.STICK)
	});
	
	public static CustomItem COBBLERES_ITEMS_SILK_MESH = new CustomItem(DefaultSections.COBBLESTONE_RESOURCES, "COBBLERES_ITEMS_SILK_MESH", new ItemBuilder(Material.WEB).name("§fSilk Mesh").addLore("", "§7Act like dust filter").create(), RecipeTypes.WORKBENCH, new ItemStack[] {
			new ItemStack(Material.STRING), new ItemStack(Material.STRING), new ItemStack(Material.STRING),
			new ItemStack(Material.STRING), new ItemStack(Material.STRING), new ItemStack(Material.STRING),
			new ItemStack(Material.STRING), new ItemStack(Material.STRING), new ItemStack(Material.STRING)
	});
	public static CustomItem COBBLERES_BLOCKS_WOOD_SIEVE = new CustomItem(DefaultSections.COBBLESTONE_RESOURCES, "COBBLERES_BLOCKS_SILK_SIEVE", new ItemBuilder(Material.WEB).name("§fSieve (Silk Mesh)").addLore("", "§7Have you played Ex Nihilo", "§7mod yet?").create(), RecipeTypes.WORKBENCH, new ItemStack[] {
			new ItemStack(Material.WOOD), COBBLERES_ITEMS_SILK_MESH.item, new ItemStack(Material.WOOD),
			new ItemStack(Material.WOOD), COBBLERES_ITEMS_SILK_MESH.item, new ItemStack(Material.WOOD),
			new ItemStack(Material.STICK), null, new ItemStack(Material.STICK)
	});
	public static CustomItem COBBLERES_BLOCKS_SMELTERY = new CustomItem(DefaultSections.COBBLESTONE_RESOURCES, "COBBLERES_BLOCKS_SMELTERY", new ItemBuilder(Material.FURNACE).name("§7Smeltery").addLore("", "§7Epic furnace that only", "§7smelt dusts").create(), RecipeTypes.WORKBENCH, new ItemStack[] {
			new ItemStack(Material.STONE), new ItemStack(Material.STONE), new ItemStack(Material.STONE),
			new ItemStack(Material.STONE), new ItemStack(Material.FURNACE), new ItemStack(Material.STONE),
			new ItemStack(Material.STONE), new ItemStack(Material.STONE), new ItemStack(Material.STONE)
	});
	
	public static CustomItem COBBLERES_DUSTS_ALUMINIUM = new CustomItem(DefaultSections.COBBLESTONE_RESOURCES, "COBBLERES_DUSTS_ALUMINIUM", new ItemBuilder(Material.SULPHUR).name("§fAluminium Powder").addLore("", "§7Resource").create(), RecipeTypes.UNKNOWN, new ItemStack[] {});
	public static CustomItem COBBLERES_DUSTS_COPPER = new CustomItem(DefaultSections.COBBLESTONE_RESOURCES, "COBBLERES_DUSTS_COPPER", new ItemBuilder(Material.GLOWSTONE_DUST).name("§6Copper Powder").addLore("", "§7Resource").create(), RecipeTypes.UNKNOWN, new ItemStack[] {});
	public static CustomItem COBBLERES_DUSTS_IRON = new CustomItem(DefaultSections.COBBLESTONE_RESOURCES, "COBBLERES_DUSTS_IRON", new ItemBuilder(Material.SULPHUR).name("§fIron Powder").addLore("", "§7Resource").create(), RecipeTypes.UNKNOWN, new ItemStack[] {});
	public static CustomItem COBBLERES_DUSTS_GOLD = new CustomItem(DefaultSections.COBBLESTONE_RESOURCES, "COBBLERES_DUSTS_GOLD", new ItemBuilder(Material.GLOWSTONE_DUST).name("§eGold Powder").addLore("", "§7Resource").create(), RecipeTypes.UNKNOWN, new ItemStack[] {});
	public static CustomItem COBBLERES_DUSTS_SLIVER = new CustomItem(DefaultSections.COBBLESTONE_RESOURCES, "COBBLERES_DUSTS_SLIVER", new ItemBuilder(Material.SUGAR).name("§fSliver Powder").addLore("", "§7Resource").create(), RecipeTypes.UNKNOWN, new ItemStack[] {});
	public static CustomItem COBBLERES_DUSTS_COAL = new CustomItem(DefaultSections.COBBLESTONE_RESOURCES, "COBBLERES_DUSTS_COAL", new ItemBuilder(Material.SULPHUR).name("§7Coal Powder").addLore("", "§7Resource").create(), RecipeTypes.UNKNOWN, new ItemStack[] {});
	public static CustomItem COBBLERES_DUSTS_LEAD = new CustomItem(DefaultSections.COBBLESTONE_RESOURCES, "COBBLERES_DUSTS_LEAD", new ItemBuilder(Material.SULPHUR).name("§7Lead Powder").addLore("", "§7Resource").create(), RecipeTypes.UNKNOWN, new ItemStack[] {});
	public static CustomItem COBBLERES_DUSTS_NICKEL = new CustomItem(DefaultSections.COBBLESTONE_RESOURCES, "COBBLERES_DUSTS_NICKEL", new ItemBuilder(Material.SUGAR).name("§fNickel Powder").addLore("", "§7Resource").create(), RecipeTypes.UNKNOWN, new ItemStack[] {});
	public static CustomItem COBBLERES_DUSTS_OSMIUM = new CustomItem(DefaultSections.COBBLESTONE_RESOURCES, "COBBLERES_DUSTS_OSMIUM", new ItemBuilder(Material.SUGAR).name("§fOsmium Powder").addLore("", "§7Resource").create(), RecipeTypes.UNKNOWN, new ItemStack[] {});
	public static CustomItem COBBLERES_DUSTS_PLATINUM = new CustomItem(DefaultSections.COBBLESTONE_RESOURCES, "COBBLERES_DUSTS_PLATINUM", new ItemBuilder(Material.SUGAR).name("§bPlatinum Powder").addLore("", "§7Resource").create(), RecipeTypes.UNKNOWN, new ItemStack[] {});
	public static CustomItem COBBLERES_DUSTS_COBALT = new CustomItem(DefaultSections.COBBLESTONE_RESOURCES, "COBBLERES_DUSTS_COBALT", new ItemBuilder(Material.SUGAR).name("§9Cobalt Powder").addLore("", "§7Resource").create(), RecipeTypes.UNKNOWN, new ItemStack[] {});
	public static CustomItem COBBLERES_DUSTS_TIN = new CustomItem(DefaultSections.COBBLESTONE_RESOURCES, "COBBLERES_DUSTS_TIN", new ItemBuilder(Material.SUGAR).name("§fTin Powder").addLore("", "§7Resource").create(), RecipeTypes.UNKNOWN, new ItemStack[] {});
	
	public static CustomItem COBBLERES_METALS_ALUMINIUM = new CustomItem(DefaultSections.COBBLESTONE_RESOURCES, "COBBLERES_METALS_ALUMINIUM", new ItemBuilder(Material.IRON_INGOT).name("§fAluminium Ingot").create(), RecipeTypes.SMELTERY, new ItemStack[] {COBBLERES_DUSTS_ALUMINIUM.item});
	public static CustomItem COBBLERES_METALS_COPPER = new CustomItem(DefaultSections.COBBLESTONE_RESOURCES, "COBBLERES_METALS_COPPER", new ItemBuilder(Material.CLAY_BRICK).name("§6Copper Ingot").create(), RecipeTypes.SMELTERY, new ItemStack[] {COBBLERES_DUSTS_COPPER.item});
	public static CustomItem COBBLERES_METALS_SLIVER = new CustomItem(DefaultSections.COBBLESTONE_RESOURCES, "COBBLERES_METALS_SLIVER", new ItemBuilder(Material.IRON_INGOT).name("§fSliver Ingot").create(), RecipeTypes.SMELTERY, new ItemStack[] {COBBLERES_DUSTS_SLIVER.item});
	public static CustomItem COBBLERES_METALS_LEAD = new CustomItem(DefaultSections.COBBLESTONE_RESOURCES, "COBBLERES_METALS_LEAD", new ItemBuilder(Material.IRON_INGOT).name("§7Lead Ingot").create(), RecipeTypes.SMELTERY, new ItemStack[] {COBBLERES_DUSTS_LEAD.item});
	public static CustomItem COBBLERES_METALS_NICKEL = new CustomItem(DefaultSections.COBBLESTONE_RESOURCES, "COBBLERES_METALS_NICKEL", new ItemBuilder(Material.IRON_INGOT).name("§fNickel Ingot").create(), RecipeTypes.SMELTERY, new ItemStack[] {COBBLERES_DUSTS_NICKEL.item});
	public static CustomItem COBBLERES_METALS_OSMIUM = new CustomItem(DefaultSections.COBBLESTONE_RESOURCES, "COBBLERES_METALS_OSMIUM", new ItemBuilder(Material.IRON_INGOT).name("§fOsmium Ingot").create(), RecipeTypes.SMELTERY, new ItemStack[] {COBBLERES_DUSTS_OSMIUM.item});
	public static CustomItem COBBLERES_METALS_PLATINUM = new CustomItem(DefaultSections.COBBLESTONE_RESOURCES, "COBBLERES_METALS_PLATINUM", new ItemBuilder(Material.IRON_INGOT).name("§bPlatinum Ingot").create(), RecipeTypes.SMELTERY, new ItemStack[] {COBBLERES_DUSTS_PLATINUM.item});
	public static CustomItem COBBLERES_METALS_COBALT = new CustomItem(DefaultSections.COBBLESTONE_RESOURCES, "COBBLERES_METALS_COBALT", new ItemBuilder(Material.IRON_INGOT).name("§9Cobalt Ingot").create(), RecipeTypes.SMELTERY, new ItemStack[] {COBBLERES_DUSTS_COBALT.item});
	public static CustomItem COBBLERES_METALS_TIN = new CustomItem(DefaultSections.COBBLESTONE_RESOURCES, "COBBLERES_METALS_TIN", new ItemBuilder(Material.IRON_INGOT).name("§fTin Ingot").create(), RecipeTypes.SMELTERY, new ItemStack[] {COBBLERES_DUSTS_TIN.item});
	
	public static CustomItem COBBLERES_ALLOYS_DUSTS_STEEL = new CustomItem(DefaultSections.COBBLESTONE_RESOURCES, "COBBLERES_ALLOYS_DUSTS_STEEL", new ItemBuilder(Material.SULPHUR).name("§7Steel Dust").addLore("", "§fAlloy").create(), RecipeTypes.WORKBENCH, new ItemStack[] {
			COBBLERES_DUSTS_IRON.item, COBBLERES_DUSTS_COAL.item, COBBLERES_DUSTS_IRON.item,
			null, null, null,
			null, null, null
	});
	public static CustomItem COBBLERES_ALLOYS_DUSTS_DAMASCUS_STEEL = new CustomItem(DefaultSections.COBBLESTONE_RESOURCES, "COBBLERES_ALLOYS_DUSTS_DAMASCUS_STEEL", new ItemBuilder(Material.SULPHUR).name("§fDamascus Steel Dust").addLore("", "§fAlloy").create(), RecipeTypes.WORKBENCH, new ItemStack[] {
			COBBLERES_DUSTS_IRON.item, COBBLERES_DUSTS_COAL.item, COBBLERES_DUSTS_IRON.item,
			null, COBBLERES_ALLOYS_DUSTS_STEEL.item, null,
			null, null, null
	});
	
	public static CustomItem COBBLERES_ALLOYS_INGOTS_STEEL = new CustomItem(DefaultSections.COBBLESTONE_RESOURCES, "COBBLERES_ALLOYS_DUSTS_STEEL", new ItemBuilder(Material.IRON_INGOT).name("§7Steel Ingot").create(), RecipeTypes.SMELTERY, new ItemStack[] {COBBLERES_ALLOYS_DUSTS_STEEL.item});
	public static CustomItem COBBLERES_ALLOYS_INGOTS_DAMASCUS_STEEL = new CustomItem(DefaultSections.COBBLESTONE_RESOURCES, "COBBLERES_ALLOYS_DUSTS_DAMASCUS_STEEL", new ItemBuilder(Material.IRON_INGOT).name("§fDamascus Steel Ingot").create(), RecipeTypes.SMELTERY, new ItemStack[] {COBBLERES_ALLOYS_DUSTS_DAMASCUS_STEEL.item});
	
	// Tinkering
	public static CustomItem TINKERING_TOOL_STATION = new CustomItem(DefaultSections.TINKERING, "TINKERING_TOOL_STATION", new ItemBuilder(Material.WORKBENCH).name("§6Tool Station").addLore("", "§7Your only powerful tool station").create(), RecipeTypes.WORKBENCH, new ItemStack[] {
			null, new ItemStack(Material.WOOD), null,
			null, new ItemStack(Material.WORKBENCH), null,
			null, new ItemStack(Material.WOOD), null
	});
	public static CustomItem TINKERING_TOOL_FORGE = new CustomItem(DefaultSections.TINKERING, "TINKERING_TOOL_FORGE", new ItemBuilder(Material.ANVIL).name("§fTool Forge").addLore("", "§7Now even stronger!").create(), RecipeTypes.WORKBENCH, new ItemStack[] {
			new ItemStack(Material.IRON_BLOCK), new ItemStack(Material.IRON_BLOCK), new ItemStack(Material.IRON_BLOCK),
			new ItemStack(Material.SMOOTH_BRICK), TINKERING_TOOL_STATION.item, new ItemStack(Material.SMOOTH_BRICK),
			new ItemStack(Material.SMOOTH_BRICK), null, new ItemStack(Material.SMOOTH_BRICK)
	});
	public static CustomItem TINKERING_STONE_ROD = new CustomItem(DefaultSections.TINKERING, "TINKERING_STONE_ROD", new ItemBuilder(Material.STICK, 2, 0).name("§7Stone Rod").addLore("", "§7Can we get Stone Torch?").create(), RecipeTypes.WORKBENCH, new ItemStack[] {
			null, new ItemStack(Material.COBBLESTONE), null,
			null, new ItemStack(Material.COBBLESTONE), null,
			null, null, null
	});
	public static CustomItem TINKERING_IRON_ROD = new CustomItem(DefaultSections.TINKERING, "TINKERING_IRON_ROD", new ItemBuilder(Material.STICK, 4, 0).name("§fIron Rod").addLore("", "§7Powerful knocking stick", "§7(there is no knockback)").create(), RecipeTypes.WORKBENCH, new ItemStack[] {
			null, new ItemStack(Material.IRON_INGOT), null,
			null, new ItemStack(Material.IRON_INGOT), null,
			null, null, null
	});
	public static CustomItem TINKERING_STONE_TORCH = new CustomItem(DefaultSections.TINKERING, "TINKERING_STONE_TORCH", new ItemBuilder(Material.TORCH, 4, 0).name("§7Stone Torch").addLore("", "§7Light up the mine").create(), RecipeTypes.WORKBENCH, new ItemStack[] {
			null, new ItemStack(Material.COAL), null,
			null, TINKERING_STONE_ROD.item, null,
			null, null, null
	});
	public static CustomItem TINKERING_DUCT_TAPE = new CustomItem(DefaultSections.TINKERING, "TINKERING_DUCT_TAPE", new ItemBuilder(Material.IRON_INGOT, 8, 0).name("§fDuct Tape").addLore("", "§7Turning \"No, no, no\"", "§7into \"mmm, mmm, mmm\"", "§7by 5%").create(), RecipeTypes.WORKBENCH, new ItemStack[] {
			COBBLERES_METALS_ALUMINIUM.item, COBBLERES_METALS_ALUMINIUM.item, COBBLERES_METALS_ALUMINIUM.item,
			COBBLERES_METALS_ALUMINIUM.item, new ItemStack(Material.WOOL), COBBLERES_METALS_ALUMINIUM.item,
			COBBLERES_METALS_ALUMINIUM.item, COBBLERES_METALS_ALUMINIUM.item, COBBLERES_METALS_ALUMINIUM.item
	});
	public static CustomItem TINKERING_FLEX_TAPE = new CustomItem(DefaultSections.TINKERING, "TINKERING_FLEX_TAPE", new ItemBuilder(Material.IRON_INGOT, 6, 0).name("§bFlex Tape\u00AE").addLore("", "§7As seen on TV, fix", "§7your item by 27%").create(), RecipeTypes.WORKBENCH, new ItemStack[] {
			TINKERING_DUCT_TAPE.item, COBBLERES_METALS_ALUMINIUM.item, TINKERING_DUCT_TAPE.item,
			COBBLERES_METALS_ALUMINIUM.item, TINKERING_DUCT_TAPE.item, COBBLERES_METALS_ALUMINIUM.item,
			TINKERING_DUCT_TAPE.item, COBBLERES_METALS_ALUMINIUM.item, TINKERING_DUCT_TAPE.item
	});
	public static CustomItem TINKERING_WOOD_PICKAXE = new CustomItem(DefaultSections.TINKERING, "TINKERING_WOOD_PICKAXE", new TinkerItem(Material.WOOD_PICKAXE, 1, 0, 3).toItemStack(), RecipeTypes.WORKBENCH, new ItemStack[] {
			new ItemStack(Material.WOOD), new ItemStack(Material.WOOD), new ItemStack(Material.WOOD),
			null, new ItemStack(Material.STICK), null,
			null, new ItemStack(Material.STICK), null
	});
	public static CustomItem TINKERING_STONE_PICKAXE = new CustomItem(DefaultSections.TINKERING, "TINKERING_STONE_PICKAXE", new TinkerItem(Material.STONE_PICKAXE, 1, 0, 3).toItemStack(), RecipeTypes.WORKBENCH, new ItemStack[] {
			new ItemStack(Material.COBBLESTONE), new ItemStack(Material.COBBLESTONE), new ItemStack(Material.COBBLESTONE),
			null, TINKERING_STONE_ROD.item, null,
			null, TINKERING_STONE_ROD.item, null
	});
	public static CustomItem TINKERING_IRON_PICKAXE = new CustomItem(DefaultSections.TINKERING, "TINKERING_IRON_PICKAXE", new TinkerItem(Material.IRON_PICKAXE, 1, 0, 3).toItemStack(), RecipeTypes.WORKBENCH, new ItemStack[] {
			new ItemStack(Material.IRON_INGOT), new ItemStack(Material.IRON_INGOT), new ItemStack(Material.IRON_INGOT),
			null, TINKERING_IRON_ROD.item, null,
			null, TINKERING_IRON_ROD.item, null
	});
	public static CustomItem TINKERING_DIAMOND_PICKAXE = new CustomItem(DefaultSections.TINKERING, "TINKERING_DIAMOND_PICKAXE", new TinkerItem(Material.DIAMOND_PICKAXE, 1, 0, 3).toItemStack(), RecipeTypes.WORKBENCH, new ItemStack[] {
			new ItemStack(Material.DIAMOND), new ItemStack(Material.DIAMOND), new ItemStack(Material.DIAMOND),
			null, TINKERING_IRON_ROD.item, null,
			null, TINKERING_IRON_ROD.item, null
	});
	public static CustomItem TINKERING_WOOD_AXE = new CustomItem(DefaultSections.TINKERING, "TINKERING_WOOD_AXE", new TinkerItem(Material.WOOD_AXE, 1, 0, 3).toItemStack(), RecipeTypes.WORKBENCH, new ItemStack[] {
			new ItemStack(Material.WOOD), new ItemStack(Material.WOOD), null,
			new ItemStack(Material.WOOD), new ItemStack(Material.STICK), null,
			null, new ItemStack(Material.STICK), null
	});
	public static CustomItem TINKERING_STONE_AXE = new CustomItem(DefaultSections.TINKERING, "TINKERING_STONE_AXE", new TinkerItem(Material.STONE_AXE, 1, 0, 3).toItemStack(), RecipeTypes.WORKBENCH, new ItemStack[] {
			new ItemStack(Material.COBBLESTONE), new ItemStack(Material.COBBLESTONE), null,
			new ItemStack(Material.COBBLESTONE), TINKERING_STONE_ROD.item, null,
			null, TINKERING_STONE_ROD.item, null
	});
	public static CustomItem TINKERING_IRON_AXE = new CustomItem(DefaultSections.TINKERING, "TINKERING_IRON_AXE", new TinkerItem(Material.IRON_AXE, 1, 0, 3).toItemStack(), RecipeTypes.WORKBENCH, new ItemStack[] {
			new ItemStack(Material.IRON_INGOT), new ItemStack(Material.IRON_INGOT), null,
			new ItemStack(Material.IRON_INGOT), TINKERING_IRON_ROD.item, null,
			null, TINKERING_IRON_ROD.item, null
	});
	public static CustomItem TINKERING_DIAMOND_AXE = new CustomItem(DefaultSections.TINKERING, "TINKERING_DIAMOND_AXE", new TinkerItem(Material.DIAMOND_AXE, 1, 0, 3).toItemStack(), RecipeTypes.WORKBENCH, new ItemStack[] {
			new ItemStack(Material.DIAMOND), new ItemStack(Material.DIAMOND), null,
			new ItemStack(Material.DIAMOND), TINKERING_IRON_ROD.item, null,
			null, TINKERING_IRON_ROD.item, null
	});
	public static CustomItem TINKERING_WOOD_SWORD = new CustomItem(DefaultSections.TINKERING, "TINKERING_WOOD_SWORD", new TinkerItem(Material.WOOD_SWORD, 1, 0, 3).toItemStack(), RecipeTypes.WORKBENCH, new ItemStack[] {
			null, new ItemStack(Material.WOOD), null,
			null, new ItemStack(Material.WOOD), null,
			null, new ItemStack(Material.STICK), null
	});
	public static CustomItem TINKERING_STONE_SWORD = new CustomItem(DefaultSections.TINKERING, "TINKERING_STONE_SWORD", new TinkerItem(Material.STONE_SWORD, 1, 0, 3).toItemStack(), RecipeTypes.WORKBENCH, new ItemStack[] {
			null, new ItemStack(Material.COBBLESTONE), null,
			null, new ItemStack(Material.COBBLESTONE), null,
			null, TINKERING_STONE_ROD.item, null
	});
	public static CustomItem TINKERING_IRON_SWORD = new CustomItem(DefaultSections.TINKERING, "TINKERING_IRON_SWORD", new TinkerItem(Material.IRON_SWORD, 1, 0, 3).toItemStack(), RecipeTypes.WORKBENCH, new ItemStack[] {
			null, new ItemStack(Material.IRON_INGOT), null,
			null, new ItemStack(Material.IRON_INGOT), null,
			null, TINKERING_IRON_ROD.item, null
	});
	public static CustomItem TINKERING_DIAMOND_SWORD = new CustomItem(DefaultSections.TINKERING, "TINKERING_DIAMOND_SWORD", new TinkerItem(Material.DIAMOND_SWORD, 1, 0, 3).toItemStack(), RecipeTypes.WORKBENCH, new ItemStack[] {
			null, new ItemStack(Material.DIAMOND), null,
			null, new ItemStack(Material.DIAMOND), null,
			null, TINKERING_IRON_ROD.item, null
	});
	public static CustomItem TINKERING_WOOD_SHOVEL = new CustomItem(DefaultSections.TINKERING, "TINKERING_WOOD_SHOVEL", new TinkerItem(Material.WOOD_SPADE, 1, 0, 3).toItemStack(), RecipeTypes.WORKBENCH, new ItemStack[] {
			null, new ItemStack(Material.WOOD), null,
			null, new ItemStack(Material.STICK), null,
			null, new ItemStack(Material.STICK), null
	});
	public static CustomItem TINKERING_STONE_SHOVEL = new CustomItem(DefaultSections.TINKERING, "TINKERING_STONE_SHOVEL", new TinkerItem(Material.STONE_SPADE, 1, 0, 3).toItemStack(), RecipeTypes.WORKBENCH, new ItemStack[] {
			null, new ItemStack(Material.COBBLESTONE), null,
			null, TINKERING_STONE_ROD.item, null,
			null, TINKERING_STONE_ROD.item, null
	});
	public static CustomItem TINKERING_IRON_SHOVEL = new CustomItem(DefaultSections.TINKERING, "TINKERING_IRON_SHOVEL", new TinkerItem(Material.IRON_SPADE, 1, 0, 3).toItemStack(), RecipeTypes.WORKBENCH, new ItemStack[] {
			null, new ItemStack(Material.IRON_INGOT), null,
			null, TINKERING_IRON_ROD.item, null,
			null, TINKERING_IRON_ROD.item, null
	});
	public static CustomItem TINKERING_DIAMOND_SHOVEL = new CustomItem(DefaultSections.TINKERING, "TINKERING_DIAMOND_SHOVEL", new TinkerItem(Material.DIAMOND_SPADE, 1, 0, 3).toItemStack(), RecipeTypes.WORKBENCH, new ItemStack[] {
			null, new ItemStack(Material.DIAMOND), null,
			null, TINKERING_IRON_ROD.item, null,
			null, TINKERING_IRON_ROD.item, null
	});
	
	// Electricity
	public static CustomItem ELECTRICITY_WIRING_WAND = new CustomItem(DefaultSections.ELECTRICITY, "ELECTRICITY_WIRING_WAND", new ItemBuilder(Material.STICK).name("§bWiring Wand").addLore("", " §eShift + Right click §7to select", " §eRight click §7selected block face to", " §7set interface").create(), RecipeTypes.WORKBENCH, new ItemStack[] {
			null, COBBLERES_ALLOYS_INGOTS_STEEL.item, null,
			null, TINKERING_IRON_ROD.item, null,
			null, TINKERING_IRON_ROD.item, null
	});
	public static CustomItem ELECTRICITY_CAPACITOR = new CustomItem(DefaultSections.ELECTRICITY, "ELECTRICITY_CAPACITOR", new Capacitor(0.0, 100.0).toItem(), RecipeTypes.WORKBENCH, new ItemStack[] {
			COBBLERES_METALS_LEAD.item, COBBLERES_METALS_COPPER.item, COBBLERES_METALS_LEAD.item,
			COBBLERES_ALLOYS_INGOTS_STEEL.item, new ItemStack(Material.REDSTONE_BLOCK), COBBLERES_ALLOYS_INGOTS_STEEL.item,
			new ItemStack(Material.IRON_INGOT), COBBLERES_METALS_ALUMINIUM.item, new ItemStack(Material.IRON_INGOT)
	});
	public static CustomItem ELECTRICITY_GENERATOR_COAL = new CustomItem(DefaultSections.ELECTRICITY, "ELECTRICITY_GENERATOR_COAL", new Generator(0.75, new GeneratorItemEntry(Material.COAL, 0.15, 40)).toItem(), RecipeTypes.WORKBENCH, new ItemStack[] {
			new ItemStack(Material.REDSTONE_BLOCK), COBBLERES_ALLOYS_INGOTS_STEEL.item, new ItemStack(Material.REDSTONE_BLOCK),
			COBBLERES_ALLOYS_INGOTS_STEEL.item, new ItemStack(Material.FURNACE), COBBLERES_ALLOYS_INGOTS_STEEL.item,
			new ItemStack(Material.REDSTONE_BLOCK), COBBLERES_ALLOYS_INGOTS_STEEL.item, new ItemStack(Material.REDSTONE_BLOCK)
	});
	public static CustomItem ELECTRICITY_GENERATOR_SOLAR = new CustomItem(DefaultSections.ELECTRICITY, "ELECTRICITY_GENERATOR_SOLAR", new ItemBuilder(Material.DAYLIGHT_DETECTOR).name("§bSolar Panel").addLore("", "§7Efficiency: §e50%", "", "§7Power per tick: §f0.05 §cNPt").create(), RecipeTypes.WORKBENCH, new ItemStack[] {
			new ItemStack(Material.DAYLIGHT_DETECTOR), new ItemStack(Material.DAYLIGHT_DETECTOR), new ItemStack(Material.DAYLIGHT_DETECTOR),
			COBBLERES_METALS_COPPER.item, ELECTRICITY_GENERATOR_COAL.item, COBBLERES_METALS_COPPER.item,
			COBBLERES_METALS_ALUMINIUM.item, COBBLERES_METALS_ALUMINIUM.item, COBBLERES_METALS_ALUMINIUM.item
	});
	public static CustomItem ELECTRICITY_TERMINAL = new CustomItem(DefaultSections.ELECTRICITY, "ELECTRICITY_TERMINAL", new ItemBuilder(Material.SIGN, 4, 0).name("§bTerminal").addLore("", "§7Watch those numbers jumping!").create(), RecipeTypes.WORKBENCH, new ItemStack[] {
			new ItemStack(Material.REDSTONE), new ItemStack(Material.REDSTONE), new ItemStack(Material.REDSTONE),
			COBBLERES_ALLOYS_INGOTS_STEEL.item, new ItemStack(Material.THIN_GLASS), COBBLERES_ALLOYS_INGOTS_STEEL.item,
			new ItemStack(Material.REDSTONE), new ItemStack(Material.REDSTONE), new ItemStack(Material.REDSTONE)
	});
	
	// --- END ---
	public static void init() {
		ADVANCED_WORKBENCH.register();
		
		// Nature & Gardening
		NATURE_CROOK.register();
		NATURE_SILK_THREAD.register();
		NATURE_LUCKY_APPLE.register();
		NATURE_WEED_DRUG.register();
		NATURE_VANILLA_STRING.register();
		NATURE_VANILLA_STRING.bypassBlockData = true;
		NATURE_FERTILIZER.register();
		NATURE_GRASS_SEED.register();
		NATURE_FIRESTARTER.register();
		NATURE_VANILLA_DIRT.register();
		NATURE_VANILLA_DIRT.bypassBlockData = true;
		NATURE_FIRESTARTER.onInteract = new ItemBlockInteract() {
			@Override
			public void onInteract(PlayerInteractEvent event) {
				event.getClickedBlock().getRelative(BlockFace.UP).setType(Material.FIRE);
			}
		};
		NATURE_FIRESTARTER_USED.register();
		NATURE_CHARCOAL.register();
		NATURE_LAVA_CRYSTAL.register();
		NATURE_LAVA_CRYSTAL_ACTIVATED.register();
		NATURE_LAVA_CRYSTAL_ACTIVATED.bypassBlockData = true;
		NATURE_LIQUID_CONTAINER.register();
		NATURE_WATER_CRYSTAL.register();
		NATURE_WATER_CRYSTAL.bypassBlockData = true;
		NATURE_INFINITE_WATER_CRYSTAL.register();
		NATURE_INFINITE_WATER_CRYSTAL.bypassBlockData = true;
		NATURE_WATERING_CAN.register();
		NATURE_WATERING_CAN_FILLED_0.register();
		NATURE_WATERING_CAN_FILLED_1.register();
		NATURE_WATERING_CAN_FILLED_2.register();
		NATURE_WATERING_CAN_FILLED_3.register();
		NATURE_WATERING_CAN_FILLED_0.item.setItemMeta(ItemStackUltils.generateWaterBottleMeta(NATURE_WATERING_CAN_FILLED_0.item));
		NATURE_WATERING_CAN_FILLED_1.item.setItemMeta(ItemStackUltils.generateWaterBottleMeta(NATURE_WATERING_CAN_FILLED_1.item));
		NATURE_WATERING_CAN_FILLED_2.item.setItemMeta(ItemStackUltils.generateWaterBottleMeta(NATURE_WATERING_CAN_FILLED_2.item));
		NATURE_WATERING_CAN_FILLED_3.item.setItemMeta(ItemStackUltils.generateWaterBottleMeta(NATURE_WATERING_CAN_FILLED_3.item));
		NATURE_WATERING_CAN_INFINITE.register();
		NATURE_WATERING_CAN_INFINITE_FILLED.register();
		NATURE_WATERING_CAN_INFINITE_FILLED.item.setItemMeta(ItemStackUltils.generateWaterBottleMeta(NATURE_WATERING_CAN_INFINITE_FILLED.item));
		
		// Cobblestone Resources
		COBBLERES_TOOLS_STONE_HAMMER.register();
		COBBLERES_TOOLS_IRON_HAMMER.register();
		COBBLERES_TOOLS_GOLD_HAMMER.register();
		COBBLERES_TOOLS_DIAMOND_HAMMER.register();
		COBBLERES_ITEMS_SILK_MESH.register();
		COBBLERES_BLOCKS_WOOD_SIEVE.register();
		COBBLERES_BLOCKS_SMELTERY.register();
		COBBLERES_DUSTS_ALUMINIUM.register();
		COBBLERES_DUSTS_COPPER.register();
		COBBLERES_DUSTS_IRON.register();
		COBBLERES_DUSTS_GOLD.register();
		COBBLERES_DUSTS_SLIVER.register();
		COBBLERES_DUSTS_COAL.register();
		COBBLERES_DUSTS_LEAD.register();
		COBBLERES_DUSTS_NICKEL.register();
		COBBLERES_DUSTS_OSMIUM.register();
		COBBLERES_DUSTS_PLATINUM.register();
		COBBLERES_DUSTS_COBALT.register();
		COBBLERES_DUSTS_TIN.register();
		COBBLERES_METALS_ALUMINIUM.register();
		COBBLERES_METALS_COPPER.register();
		COBBLERES_METALS_SLIVER.register();
		COBBLERES_METALS_LEAD.register();
		COBBLERES_METALS_NICKEL.register();
		COBBLERES_METALS_OSMIUM.register();
		COBBLERES_METALS_PLATINUM.register();
		COBBLERES_METALS_COBALT.register();
		COBBLERES_METALS_TIN.register();
		
		COBBLERES_ALLOYS_DUSTS_STEEL.register();
		COBBLERES_ALLOYS_DUSTS_DAMASCUS_STEEL.register();
		COBBLERES_ALLOYS_INGOTS_STEEL.register();
		COBBLERES_ALLOYS_INGOTS_DAMASCUS_STEEL.register();
		
		// Tinkering
		TINKERING_TOOL_STATION.register();
		TINKERING_TOOL_FORGE.register();
		TINKERING_STONE_ROD.register();
		TINKERING_IRON_ROD.register();
		TINKERING_STONE_TORCH.register();
		TINKERING_DUCT_TAPE.register();
		TINKERING_FLEX_TAPE.register();
		TINKERING_WOOD_PICKAXE.register();
		TINKERING_STONE_PICKAXE.register();
		TINKERING_IRON_PICKAXE.register();
		TINKERING_DIAMOND_PICKAXE.register();
		TINKERING_WOOD_AXE.register();
		TINKERING_STONE_AXE.register();
		TINKERING_IRON_AXE.register();
		TINKERING_DIAMOND_AXE.register();
		TINKERING_WOOD_SWORD.register();
		TINKERING_STONE_SWORD.register();
		TINKERING_IRON_SWORD.register();
		TINKERING_DIAMOND_SWORD.register();
		TINKERING_WOOD_SHOVEL.register();
		TINKERING_STONE_SHOVEL.register();
		TINKERING_IRON_SHOVEL.register();
		TINKERING_DIAMOND_SHOVEL.register();
		
		// Electricity
		ELECTRICITY_WIRING_WAND.register();
		ELECTRICITY_CAPACITOR.register();
		ELECTRICITY_CAPACITOR.bypassBlockData = true;
		//ELECTRICITY_GENERATOR_COAL.register();
		ELECTRICITY_GENERATOR_SOLAR.register();
		ELECTRICITY_TERMINAL.register();
		
		// Tickers
		NATURE_WEED_DRUG.addBlockTicker(new BlockTicker() {
			@Override
			public void onTick(Block block, BlockData data) {
				if (block.getRelative(BlockFace.UP).getType() == Material.SUGAR_CANE_BLOCK) {
					// The weed grow
					Block top = block.getRelative(BlockFace.UP);
					new BukkitRunnable() {
						@Override
						public void run() {
							top.setType(Material.AIR);
							top.getWorld().spawnParticle(Particle.BLOCK_CRACK, top.getLocation().add(0.5, 0, 0.5), 100, new MaterialData(Material.SUGAR_CANE_BLOCK));
						}
					}.runTask(nahkdMiscs.currentPlugin);
					if (data.otherData == null) data.otherData = new YamlConfiguration();
					data.otherData.set("amount", data.otherData.getInt("amount", 1) + 1);
				}
			}
		});
		NATURE_LIQUID_CONTAINER.addBlockTicker(new BlockTicker() {
			@SuppressWarnings("deprecation")
			@Override
			public void onTick(Block block, BlockData data) {
				if (block.getWorld().hasStorm()) {
					data.block = CustomBlocks.getByItem(NATURE_WATER_CRYSTAL);
					block.setData((byte) 11);
				}
			}
		});
		COBBLERES_BLOCKS_WOOD_SIEVE.addBlockTicker(new BlockTicker() {
			@Override
			public void onTick(Block block, BlockData data) {
				if (block.getRelative(BlockFace.UP).getType() == Material.GRAVEL) {
					ArrayList<ItemStack> drops = CobblestoneResourcesLoots.sieve_gravel.generateOutputLessthan();
					new BukkitRunnable() {
						@Override
						public void run() {
							block.getRelative(BlockFace.UP).setType(Material.AIR);
							for (ItemStack drop : drops) block.getWorld().dropItem(block.getRelative(BlockFace.UP).getLocation().add(0.5, 0.5, 0.5), drop);
						}
					}.runTask(nahkdMiscs.currentPlugin);
				}
				if (block.getRelative(BlockFace.UP).getType() == Material.SAND) {
					ArrayList<ItemStack> drops = CobblestoneResourcesLoots.sieve_sand.generateOutputLessthan();
					new BukkitRunnable() {
						@Override
						public void run() {
							block.getRelative(BlockFace.UP).setType(Material.AIR);
							for (ItemStack drop : drops) block.getWorld().dropItem(block.getRelative(BlockFace.UP).getLocation().add(0.5, 0.5, 0.5), drop);
						}
					}.runTask(nahkdMiscs.currentPlugin);
				}
				if (block.getRelative(BlockFace.UP).getType() == Material.DIRT) {
					ArrayList<ItemStack> drops = CobblestoneResourcesLoots.sieve_dirt.generateOutputLessthan();
					new BukkitRunnable() {
						@Override
						public void run() {
							block.getRelative(BlockFace.UP).setType(Material.AIR);
							for (ItemStack drop : drops) block.getWorld().dropItem(block.getRelative(BlockFace.UP).getLocation().add(0.5, 0.5, 0.5), drop);
						}
					}.runTask(nahkdMiscs.currentPlugin);
				}
			}
		});
		COBBLERES_BLOCKS_SMELTERY.addBlockTicker(new BlockTicker() {
			@Override
			public void onTick(Block block, BlockData data) {
				int heatTicks = 0;
				if (data.otherData != null) heatTicks = data.otherData.getInt("ticks", 0);
				if (heatTicks > 0) {
					heatTicks--;
					if (data.otherData == null) data.otherData = new YamlConfiguration();
					data.otherData.set("ticks", heatTicks);
				}
			}
		});
		ELECTRICITY_CAPACITOR.addBlockTicker(new BlockTicker() {
			@Override
			public void onTick(Block block, BlockData data) {
				PowerableUltils.tickingBlock(data);
			}
		});
		ELECTRICITY_GENERATOR_SOLAR.addBlockTicker(new BlockTicker() {
			@Override
			public void onTick(Block block, BlockData data) {
				if (data.otherData == null) {
					data.otherData = new YamlConfiguration();
					data.otherData.set("power.current", 0.0D);
					data.otherData.set("power.max", 1.0D);
					data.otherData.set("interface.power.allowed", true);
					data.otherData.set("interface.power.in", "");
					data.otherData.set("interface.power.out", "");
					data.otherData.set("interface.power.speed", 12);
					data.otherData.set("interface.item.allowed", false);
				}
				long time = block.getWorld().getTime();
				if (time < 12300 || time > 23850) data.otherData.set("power.current", Math.round((data.otherData.getDouble("power.current", 0.0D) + 0.01D) * 1000.0) / 1000.0);
				if (data.otherData.getDouble("power.current", 0.0D) > 1.0D) data.otherData.set("power.current", 1.0D);
				PowerableUltils.tickingBlock(data);
			}
		});
		ELECTRICITY_TERMINAL.addBlockTicker(new BlockTicker() {
			@Override
			public void onTick(Block block, BlockData data) {
				if (block.getType() == Material.SIGN_POST || block.getType() == Material.WALL_SIGN) {
					boolean update = false; // For slow as fuck servers
					
					Sign sign = (Sign) block.getState();
					String[] rawRefLoc = data.otherData.getString("reflocation").split("\\|");
					Location refLoc = new Location(Bukkit.getWorld(rawRefLoc[0]), Integer.parseInt(rawRefLoc[1]), Integer.parseInt(rawRefLoc[2]), Integer.parseInt(rawRefLoc[3]));
					BlockData ref = WorldsData.getDataMap().get(block.getWorld()).blockData.queryData(refLoc);
					double power = ref.otherData.getDouble("power.current", 0.0D);
					double maxPower = ref.otherData.getDouble("power.max", 16D);
					
					sign.setLine(0, ItemStackUltils.getItemName(ref.block.item.item));
					if (ref.otherData.contains("machine.status")) {
						if (ref.otherData.getBoolean("machine.status", false) && !sign.getLine(1).equals("§3Activated")) {
							sign.setLine(1, "§3Activated");
							update = true;
						} else if (!sign.getLine(1).equals("§4Off")) {
							sign.setLine(1, "§4Off");
							update = true;
						}
					} else if (!sign.getLine(1).equals("§bPower Storage")) {
						sign.setLine(1, "§bPower Storage");
						update = true;
					}
					if (!sign.getLine(3).equals("§b" + power + "§7/" + maxPower + " §cNP")) {
						sign.setLine(3, "§b" + power + "§7/" + maxPower + " §cNP");
						update = true;
					}
					if (update) sign.update(true);
				} else {
					// Self remove
					WorldData d = WorldsData.getDataMap().get(block.getWorld());
					d.blockData.data.remove(data);
				}
			}
		});
		
		// Drops
		NATURE_WEED_DRUG.block.setDropChanger(new DropChanger() {
			@Override
			public ArrayList<ItemStack> changeDrop(ArrayList<ItemStack> old, BlockData data) {
				int drops = (data.otherData != null? data.otherData.getInt("amount", 1) : 1);
				ItemStack iiii = new ItemStack(NATURE_WEED_DRUG.item);
				iiii.setAmount(drops);
				return new ArrayList<ItemStack>(Arrays.asList(iiii));
			}
		});
	}
	
}
