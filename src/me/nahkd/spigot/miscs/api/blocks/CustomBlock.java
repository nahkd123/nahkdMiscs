package me.nahkd.spigot.miscs.api.blocks;

import java.util.ArrayList;

import me.nahkd.spigot.miscs.api.items.CustomItem;

public class CustomBlock {
	
	public CustomItem item;
	public ArrayList<BlockTicker> tickers;
	public DropChanger dropChanger;
	
	public CustomBlock(CustomItem item) {
		this.item = item;
		this.tickers = new ArrayList<BlockTicker>();
		this.dropChanger = null;
	}
	
	public void addTicker(BlockTicker ticker) {
		tickers.add(ticker);
	}
	
	public void setDropChanger(DropChanger changer) {
		this.dropChanger = changer;
	}
	
	public void register() {
		CustomBlocks.registerBlock(this);
	}
	
}
