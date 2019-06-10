package me.nahkd.spigot.miscs.api;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

/**
 * You can create item much faster by using this item builder, simply by creating object and then end it with .create()
 * @author nahkd123
 *
 */
public class ItemBuilder {

	public Material material;
	public int count;
	public short damage;
	
	public List<String> lores;
	public boolean glowing;
	public String name;
	
	/**
	 * A basic item builder with only material
	 * @param material
	 */
	public ItemBuilder(Material material) {
		this(material, 1, 0);
	}
	
	/**
	 * A basic item builder with only material and count and damage data
	 * @param material
	 * @param count
	 */
	public ItemBuilder(Material material, int count, int damage) {
		this.material = material;
		this.count = count;
		
		this.lores = new ArrayList<String>();
		this.glowing = false;
		this.name = null;
		this.damage = (short) damage;
	}
	
	public ItemBuilder addLore(String... lores) {
		this.lores.addAll(Arrays.asList(lores));
		return this;
	}
	public ItemBuilder glow() {
		this.glowing = true;
		return this;
	}
	public ItemBuilder name(String name) {
		this.name = name;
		return this;
	}
	
	public ItemStack create() {
		// Create ItemStack
		ItemStack output = new ItemStack(material, count, damage);
		// Get ItemMeta
		ItemMeta outputMeta = output.getItemMeta();
		// Lore
		outputMeta.setLore(lores);
		// Glowing
		if (this.glowing) {
			outputMeta.addEnchant(Enchantment.ARROW_INFINITE, 0, true);
			outputMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		}
		// Name
		if (this.name != null) {
			outputMeta.setDisplayName(name);
		}
		
		// Change ItemStack meta
		output.setItemMeta(outputMeta);
		return output;
	}
	
}
