package me.nahkd.spigot.miscs.multithreads;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import me.nahkd.spigot.miscs.nahkdMiscs;
import me.nahkd.spigot.miscs.api.blocks.BlockData;
import me.nahkd.spigot.miscs.api.bossbar.BossBarManager;
import me.nahkd.spigot.miscs.api.formatters.NumberFormatters;
import me.nahkd.spigot.miscs.api.power.PowerableUltils.Interfaces;
import me.nahkd.spigot.miscs.api.tinkering.Modifier;
import me.nahkd.spigot.miscs.api.tinkering.ModifierType;
import me.nahkd.spigot.miscs.api.tinkering.ModifierTypesManager;
import me.nahkd.spigot.miscs.api.tinkering.TinkerItem;
import me.nahkd.spigot.miscs.api.utils.ItemStackUltils;
import me.nahkd.spigot.miscs.api.world.WorldsData;
import me.nahkd.spigot.miscs.defaults.DefaultItems;

public class BlockInfoThread extends Thread {
	
	@Override
	public void run() {
		System.out.println("[nahkdMiscs](Multithreading) Block info thread started");
		while (true) {
			try {
				for (Player p : Bukkit.getServer().getOnlinePlayers()) {
					Block sight = p.getTargetBlock(null, 5);
					BossBar bar = BossBarManager.getBar(p);
					BlockData dat = WorldsData.getDataMap().get(sight.getWorld()).blockData.queryData(sight.getLocation());
					if (dat != null && dat.block.item.id == "COBBLERES_BLOCKS_SMELTERY") {
						bar.setTitle("§7Smeltery");
						int fireTicks = 0;
						if (dat.otherData != null) fireTicks = dat.otherData.getInt("ticks", 0);
						double progress = Math.min(fireTicks / 1200D, 1);
						bar.setProgress(progress);
						bar.setColor(BarColor.RED);
						bar.setVisible(true);
					} else if (dat != null && (dat.block.item.id == "TINKERING_TOOL_STATION" || dat.block.item.id == "TINKERING_TOOL_FORGE")) {
						String title = "";
						if (dat.block.item.id == "TINKERING_TOOL_STATION") title = "§fTool Station";
						else title = "§bTool Forge";
						bar.setColor(BarColor.BLUE);
						if (dat.otherData != null) {
							if (dat.otherData.contains("awaitingItem")) {
								title += "§f - " + ItemStackUltils.getItemName(dat.otherData.getItemStack("awaitingItem"));
								TinkerItem i = TinkerItem.fromItem(dat.otherData.getItemStack("awaitingItem"));
								if (p.getInventory().getItemInMainHand() != null || p.getInventory().getItemInMainHand().getType() != Material.AIR) {
									for (String tn : ModifierTypesManager.getMap().keySet()) {
										ModifierType type = ModifierTypesManager.getMap().get(tn);
										if (type.item.isSimilar(p.getInventory().getItemInMainHand())) {
											title += "§f - " + type.name;
											
											Modifier m = i.modifiers.get(type);
											if (m != null) title += "§f - §7" + m.currentItems + "/" + type.maxItems;
											break;
										}
									}
								}
							}
						}
						bar.setTitle(title);
						bar.setVisible(true);
					} else if (DefaultItems.ELECTRICITY_WIRING_WAND.item.isSimilar(p.getInventory().getItemInMainHand()) || DefaultItems.ELECTRICITY_WIRING_WAND.item.isSimilar(p.getInventory().getItemInOffHand())) {
						if (dat != null && dat.otherData != null && dat.otherData.contains("power")) {
							double proc = dat.otherData.getDouble("power.current", 0.0) / dat.otherData.getDouble("power.max", 16.0);
							String title = ItemStackUltils.getItemName(dat.block.item.item) + " §7- §f"
									+ NumberFormatters.powerFormat.format(dat.otherData.getDouble("power.current", 0.0)) + "§7/"
									+ NumberFormatters.powerFormat.format(dat.otherData.getDouble("power.max", 16.0));
							bar.setTitle(title);
							bar.setProgress(proc);
							bar.setColor(BarColor.BLUE);
							bar.setVisible(true);
						} else bar.setVisible(false);
					} else {
						bar.setVisible(false);
					}
					if (!bar.getPlayers().contains(p)) bar.addPlayer(p);
				}
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
}
