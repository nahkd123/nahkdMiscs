package me.nahkd.spigot.miscs.api.power;

import org.bukkit.Material;

public class GeneratorItemEntry {
	
	public Material input;
	public double powerPerTick;
	public int ticks;
	
	public GeneratorItemEntry(Material input, double powerPerTick, int ticks) {
		this.input = input;
		this.powerPerTick = powerPerTick;
		this.ticks = ticks;
	}
	
}
