package me.nahkd.spigot.miscs.multithreads;

public interface SafeStopable {
	
	/**
	 * Safety stop the thread. This can reduce error while processing
	 */
	public void stopThreadSafety();
	public boolean isThreadStopped();
	
}
