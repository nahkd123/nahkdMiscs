package me.nahkd.spigot.miscs.api.blocks;

import org.bukkit.block.Block;

public interface BlockTicker {
	
	public void onTick(Block block, BlockData data);
	
}
