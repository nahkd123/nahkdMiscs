package me.nahkd.spigot.miscs.api.bossbar;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;

public class BossBarManager {
	
	private static HashMap<Player, BossBar> bars;
	public static HashMap<Player, BossBar> getMap() {
		if (bars == null) bars = new HashMap<Player, BossBar>();
		return bars;
	}
	public static BossBar getBar(Player player) {
		if (!getMap().containsKey(player)) {
			BossBar o = Bukkit.createBossBar("title goes here", BarColor.BLUE, BarStyle.SOLID);
			getMap().put(player, o);
			return o;
		} else return getMap().get(player);
	}
	
}
