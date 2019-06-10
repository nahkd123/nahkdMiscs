package me.nahkd.spigot.miscs.multithreads;

import org.bukkit.World;
import org.bukkit.scheduler.BukkitRunnable;

import me.nahkd.spigot.miscs.nahkdMiscs;
import me.nahkd.spigot.miscs.api.blocks.BlockData;
import me.nahkd.spigot.miscs.api.blocks.BlockTicker;
import me.nahkd.spigot.miscs.api.world.WorldData;
import me.nahkd.spigot.miscs.api.world.WorldsData;

public class BlockTickerThread extends Thread implements SafeStopable {

	public boolean stopped;
	
	public BlockTickerThread() {
		this.stopped = false;
	}
	
	@Override
	public void run() {
		System.out.println("[nahkdMiscs](Multithreading) Block ticker thread started");
		while (true) {
			try {
				for (World world : WorldsData.getDataMap().keySet()) {
					WorldData worldd = WorldsData.getDataMap().get(world);
					for (BlockData block : worldd.blockData.data) {
						for (BlockTicker ticker : block.block.tickers) {
							if (nahkdMiscs.currentPlugin.isEnabled()) new BukkitRunnable() {
								@Override
								public void run() {
									ticker.onTick(block.location.getBlock(), block);
								}
							}.runTask(nahkdMiscs.currentPlugin);
						}
					}
				}
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public void stopThreadSafety() {
		this.stopped = true;
	}

	@Override
	public boolean isThreadStopped() {
		return false;
	}

}
