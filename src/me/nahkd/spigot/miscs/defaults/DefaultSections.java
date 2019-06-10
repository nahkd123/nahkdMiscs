package me.nahkd.spigot.miscs.defaults;

import org.bukkit.Material;

import me.nahkd.spigot.miscs.api.ItemBuilder;
import me.nahkd.spigot.miscs.guide.GuideSection;

public class DefaultSections {
	
	public static GuideSection UNCATEGORIZED = new GuideSection(new ItemBuilder(Material.BARRIER).name("§7< Uncategorized >").addLore("", "§7uh oh, look like the developer", "§7need to update more").glow().create(), "UNCATEGORIZED");
	public static GuideSection NATURE_AND_GARDENING = new GuideSection(new ItemBuilder(Material.SAPLING).name("§aNature and Gardening").glow().create(), "NATURE_AND_GARDENING");
	public static GuideSection COBBLESTONE_RESOURCES = new GuideSection(new ItemBuilder(Material.COBBLESTONE).name("§7Cobblestone Resources").glow().create(), "COBBLESTONE_RESOURCES");
	public static GuideSection TINKERING = new GuideSection(new ItemBuilder(Material.IRON_PICKAXE).name("§bTinkering").addLore("§7Tinkers's Construct related").glow().create(), "TINKERING");
	public static GuideSection ELECTRICITY = new GuideSection(new ItemBuilder(Material.STAINED_CLAY, 1, 1).name("§bElectricity").glow().create(), "ELECTRICITY");
	
	public static void init() {
		UNCATEGORIZED.register();
		NATURE_AND_GARDENING.register();
		COBBLESTONE_RESOURCES.register();
		TINKERING.register();
		ELECTRICITY.register();
	}
	
}
