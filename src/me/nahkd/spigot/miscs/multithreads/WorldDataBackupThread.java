package me.nahkd.spigot.miscs.multithreads;

import me.nahkd.spigot.miscs.api.world.WorldsData;

public class WorldDataBackupThread extends Thread implements SafeStopable {
	
	public boolean stopped;
	
	public WorldDataBackupThread() {
		this.stopped = false;
	}
	
	@Override
	public synchronized void run() {
		while (!this.stopped) {
			try {
				System.out.println("[nahkdItems](Multithreading) Next data save: 12 mins");
				Thread.sleep(720000);
				System.out.println("[nahkdItems](Multithreading) Saving data (auto)");
				WorldsData.saveAll();
			} catch (InterruptedException e) {
				System.out.println("[nahkdItems](Multithreading) Auto saving thread stopped in non-safe way!");
				e.printStackTrace();
				System.out.println("Better use SafeStopable#stopThreadSafety() next time!");
			}
		}
	}

	@Override
	public void stopThreadSafety() {
		this.stopped = true;
	}

	@Override
	public boolean isThreadStopped() {
		return this.stopped;
	}
	
}
