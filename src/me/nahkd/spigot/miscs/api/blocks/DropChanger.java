package me.nahkd.spigot.miscs.api.blocks;

import java.util.ArrayList;

import org.bukkit.inventory.ItemStack;

public interface DropChanger {
	
	public ArrayList<ItemStack> changeDrop(ArrayList<ItemStack> old, BlockData data);
	
}
