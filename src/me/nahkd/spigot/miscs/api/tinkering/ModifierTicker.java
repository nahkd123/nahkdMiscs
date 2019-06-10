package me.nahkd.spigot.miscs.api.tinkering;

import org.bukkit.entity.Player;

public interface ModifierTicker {
	
	public void onTick(Modifier modifier, Player player);
	
}
