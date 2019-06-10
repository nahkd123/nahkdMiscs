package me.nahkd.spigot.miscs.guide;

import java.util.HashMap;

import org.bukkit.entity.Player;

public class GuideBookManager {
	
	private static HashMap<Player, GuideBookData> datas;
	public static HashMap<Player, GuideBookData> getMap() {
		if (datas == null) datas = new HashMap<Player, GuideBookData>();
		return datas;
	}
	public static GuideBookData getData(Player player) {
		GuideBookData dat = getMap().get(player);
		if (dat == null) {
			dat = new GuideBookData(player);
			getMap().put(player, dat);
		}
		return dat;
	}
	
}
