package me.nahkd.spigot.miscs.api.tinkering;

import java.util.HashMap;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.nahkd.spigot.miscs.api.ItemBuilder;

public class TinkerItem {
	
	public static boolean checkItem(ItemStack item) {
		if (item == null) return false;
		if (!item.hasItemMeta()) return false;
		if (!item.getItemMeta().hasLore()) return false;
		if (!item.getItemMeta().getLore().get(0).equals("§7Tinkers's Tool")) return false;
		return true;
	}
	public static boolean isTinkersToolBroken(ItemStack item) {
		if (!checkItem(item)) {
			if (item.getItemMeta().getLore().get(0).equals("§4Broken Tinkers's tool")) return true;
			return false;
		} else return false;
	}
	
	public static int getRequiredExpForNextLevel(int currentLevel) {
		return 21 + (currentLevel * 45) / 3 + (currentLevel * 15) / 9;
	}
	
	public static TinkerItem fromItem(ItemStack item) {
		if (!checkItem(item)) return null;
		int toolLevel = Integer.parseInt(item.getItemMeta().getLore().get(2).substring(10));
		int toolExp = Integer.parseInt(item.getItemMeta().getLore().get(3).substring(2).split("\\/")[0]);
		MiningLevel toolMiningLevel = MiningLevel.fromStringLore(item.getItemMeta().getLore().get(4));
		int emptyModifiers = Integer.parseInt(item.getItemMeta().getLore().get(6).substring(2).split(" ")[0]);
		HashMap<ModifierType, Modifier> mods = ModifierTypesManager.getModifiersFormLore(item.getItemMeta().getLore());
		TinkerItem i2 = new TinkerItem(item.getType(), toolLevel, toolExp, emptyModifiers);
		i2.modifiers = mods;
		i2.level = toolMiningLevel;
		return i2;
	}
	
	public int toolLevel;
	public int currentExp;
	public int emptyModifierSlots;
	public HashMap<ModifierType, Modifier> modifiers;
	public Material itemMeterial;
	public MiningLevel level;
	
	public TinkerItem(Material itemMaterial, int toolLevel, int currentExp, int emptyModifierSlots) {
		this.toolLevel = toolLevel;
		this.currentExp = currentExp;
		this.emptyModifierSlots = emptyModifierSlots;
		this.modifiers = new HashMap<ModifierType, Modifier>();
		this.itemMeterial = itemMaterial;
		this.level = MiningLevel.getLevelFromMaterial(itemMaterial);
	}
	
	public ItemStack toItemStack() {
		ItemStack out = new ItemBuilder(itemMeterial).addLore("§7Tinkers's Tool", "", "§7Level §f" + toolLevel, "§7" + currentExp + "/" + getRequiredExpForNextLevel(toolLevel) + " EXP", level.getMiningLevelLore(), "", "§7" + emptyModifierSlots + " modifiers left").create();
		
		ItemMeta meta = out.getItemMeta();
		List<String> l = meta.getLore();
		for (ModifierType type : modifiers.keySet()) l.add(type.getModifierLore(modifiers.get(type).level, modifiers.get(type).currentItems));
		meta.setLore(l);
		
		out.setItemMeta(meta);
		return out;
	}
	
	public enum MiningLevel {
		WOOD("§6Wood"),
		STONE("§7Stone"),
		IRON("§fIron"),
		DIAMOND("§bDiamond"),
		OBSIDIAN("§5Obsidian");
		
		public String display;
		private MiningLevel(String display) {
			this.display = display;
		}
		
		public String getMiningLevelLore() {
			return "§7Mining level: " + display;
		}
		public static MiningLevel fromStringLore(String lore) {
			for (MiningLevel l : MiningLevel.values()) if (lore.startsWith("§7Mining level: " + l.display)) return l;
			return null;
		}
		public static MiningLevel getLevelFromMaterial(Material mat) {
			if (mat.toString().startsWith("WOOD_")) return WOOD;
			if (mat.toString().startsWith("STONE_")) return STONE;
			if (mat.toString().startsWith("IRON_")) return IRON;
			if (mat.toString().startsWith("DIAMOND_")) return DIAMOND;
			return null;
		}
		public static Material getMaterialByMiningLevel(Material source, MiningLevel level) {
			String ii = source.toString().split("_")[1];
			switch (level) {
			case WOOD: return Material.getMaterial("WOOD_" + ii);
			case STONE: return Material.getMaterial("STONE_" + ii);
			case IRON: return Material.getMaterial("IRON_" + ii);
			case DIAMOND: return Material.getMaterial("DIAMOND_" + ii);
			
			default: return Material.WOOD_AXE;
			}
		}
	}
	
}
