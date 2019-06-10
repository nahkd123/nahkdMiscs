package me.nahkd.spigot.miscs.api.utils;

import java.util.List;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionType;

import me.nahkd.spigot.miscs.api.tinkering.TinkerItem;

public class ItemStackUltils {
	
	public static boolean compareTool(ItemStack item, ItemStack compareWith) {
		if (item == null && compareWith == null) return true;
		if (item != null && compareWith == null) return false;
		if (item == null && compareWith != null) return false;
		return (item.getType() == compareWith.getType() && 
				item.hasItemMeta() == compareWith.hasItemMeta() && 
				(item.hasItemMeta() ? (
						item.getItemMeta().getDisplayName() == compareWith.getItemMeta().getDisplayName() &&
						item.getItemMeta().getLore().equals(compareWith.getItemMeta().getLore())
						) : true));
	}
	
	public static boolean isBlockCorrectly(Material material) {
		return material.isBlock() ||
			material == Material.STRING ||
			material == Material.SUGAR_CANE ||
			material == Material.SIGN
			;
	}
	
	public static boolean isItemDamagable(Material material) {
		return (
				material == Material.WOOD_AXE ||
				material == Material.WOOD_PICKAXE ||
				material == Material.WOOD_SWORD || 
				material == Material.WOOD_SPADE || 
				
				material == Material.STONE_AXE ||
				material == Material.STONE_PICKAXE ||
				material == Material.STONE_SWORD || 
				material == Material.STONE_SPADE || 
				
				material == Material.IRON_AXE ||
				material == Material.IRON_PICKAXE ||
				material == Material.IRON_SWORD || 
				material == Material.IRON_SPADE || 
				
				material == Material.DIAMOND_AXE ||
				material == Material.DIAMOND_PICKAXE ||
				material == Material.DIAMOND_SWORD ||
				material == Material.DIAMOND_SPADE
				);
	}
	
	public static PotionMeta generateWaterBottleMeta(ItemStack potionItem) {
		PotionMeta m = (PotionMeta) potionItem.getItemMeta();
		m.setBasePotionData(new PotionData(PotionType.WATER));
		return m;
	}
	
	public static String getItemName(ItemStack anyItemYouWant) {
		if (anyItemYouWant.hasItemMeta()) {
			return (anyItemYouWant.getItemMeta().hasDisplayName()?
						anyItemYouWant.getItemMeta().getDisplayName() : 
						(anyItemYouWant.getItemMeta().hasLocalizedName()?
							anyItemYouWant.getItemMeta().getLocalizedName() : 
							getItemName(new ItemStack(anyItemYouWant.getType()))
						)
					);
		} else {
			String[] u = anyItemYouWant.getType().toString().split("_");
			String out = "";
			for (String subU : u) {
				String subU2 = "";
				boolean first = true;
				for (char c : subU.toLowerCase().toCharArray()) if (first) {
					first = false;
					subU2 += Character.toUpperCase(c);
				} else subU2 += c;
				out += " " + subU2;
			}
			return out.substring(1);
		}
	}
	
	public static ItemStack repairItem(ItemStack input, double repairFactor) {
		ItemStack newOne = new ItemStack(input);
		newOne.setDurability((short) (newOne.getDurability() - ((double) newOne.getType().getMaxDurability() * repairFactor)));
		if (newOne.getDurability() < 0) newOne.setDurability((short) 0);
		if (TinkerItem.isTinkersToolBroken(newOne)) {
			ItemMeta matt = newOne.getItemMeta();
			List<String> l = matt.getLore();
			l.set(0, "§7Tinkers's Tool");
			matt.setLore(l);
			newOne.setItemMeta(matt);
		}
		return newOne;
	}
	
	public static ItemStack getCloneSingle(ItemStack item) {
		ItemStack clone = new ItemStack(item);
		clone.setAmount(1);
		return clone;
	}
	
}
