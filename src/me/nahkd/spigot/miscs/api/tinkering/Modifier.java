package me.nahkd.spigot.miscs.api.tinkering;

public class Modifier {
	
	public ModifierType type;
	public int level;
	public int currentItems;
	
	public Modifier(ModifierType type, int level, int currentItems) {
		this.type = type;
		this.level = level;
		this.currentItems = currentItems;
	}
	
}
