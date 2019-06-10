package me.nahkd.spigot.miscs.multithreads;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import me.nahkd.spigot.miscs.nahkdMiscs;
import me.nahkd.spigot.miscs.api.tinkering.ModifierType;
import me.nahkd.spigot.miscs.api.tinkering.TinkerItem;
import me.nahkd.spigot.miscs.api.utils.ItemStackUltils;
import me.nahkd.spigot.miscs.defaults.DefaultItems;

public class OtherStuffsThread extends Thread {
	
	@Override
	public void run() {
		System.out.println("[nahkdMiscs](Multithreading) Misc thread started");
		try {
			while (true) {
				for (Player player : Bukkit.getOnlinePlayers()) {
					if (player.getInventory().getItemInMainHand() != null) {
						if (ItemStackUltils.compareTool(DefaultItems.NATURE_CROOK.item, player.getInventory().getItemInMainHand())) {
							if (player.getTargetBlock(null, 4).getType() == Material.LEAVES || player.getTargetBlock(null, 4).getType() == Material.LEAVES_2) {
								new BukkitRunnable() {
									@Override
									public void run() {player.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 10, 3));}
								}.runTask(nahkdMiscs.currentPlugin);
							};
						}
						if (ItemStackUltils.compareTool(DefaultItems.COBBLERES_TOOLS_STONE_HAMMER.item, player.getInventory().getItemInMainHand()) ||
							ItemStackUltils.compareTool(DefaultItems.COBBLERES_TOOLS_GOLD_HAMMER.item, player.getInventory().getItemInMainHand())) {
							if (player.getTargetBlock(null, 4).getType() == Material.STONE || player.getTargetBlock(null, 4).getType() == Material.COBBLESTONE) {
								new BukkitRunnable() {
									@Override
									public void run() {player.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 10, 12));}
								}.runTask(nahkdMiscs.currentPlugin);
							} else if (player.getTargetBlock(null, 4).getType() == Material.GRAVEL || player.getTargetBlock(null, 4).getType() == Material.SANDSTONE) {
								new BukkitRunnable() {
									@Override
									public void run() {player.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 10, 3));}
								}.runTask(nahkdMiscs.currentPlugin);
							} else if (player.getTargetBlock(null, 4).getType() == Material.WOOD || player.getTargetBlock(null, 4).getType() == Material.LOG || player.getTargetBlock(null, 4).getType() == Material.LOG_2) {
								if (player.hasPotionEffect(PotionEffectType.FAST_DIGGING)) new BukkitRunnable() {
									@Override
									public void run() {player.removePotionEffect(PotionEffectType.FAST_DIGGING);}
								}.runTask(nahkdMiscs.currentPlugin);
							}
						}
						if (ItemStackUltils.compareTool(DefaultItems.COBBLERES_TOOLS_IRON_HAMMER.item, player.getInventory().getItemInMainHand()) ||
							ItemStackUltils.compareTool(DefaultItems.COBBLERES_TOOLS_DIAMOND_HAMMER.item, player.getInventory().getItemInMainHand())) {
							if (player.getTargetBlock(null, 4).getType() == Material.STONE || player.getTargetBlock(null, 4).getType() == Material.COBBLESTONE) {
								new BukkitRunnable() {
									@Override
									public void run() {player.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 10, 34));}
								}.runTask(nahkdMiscs.currentPlugin);
							} else if (player.getTargetBlock(null, 4).getType() == Material.GRAVEL || player.getTargetBlock(null, 4).getType() == Material.SANDSTONE) {
								new BukkitRunnable() {
									@Override
									public void run() {player.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 10, 7));}
								}.runTask(nahkdMiscs.currentPlugin);
							} else if (player.getTargetBlock(null, 4).getType() == Material.WOOD || player.getTargetBlock(null, 4).getType() == Material.LOG || player.getTargetBlock(null, 4).getType() == Material.LOG_2) {
								if (player.hasPotionEffect(PotionEffectType.FAST_DIGGING)) new BukkitRunnable() {
									@Override
									public void run() {player.removePotionEffect(PotionEffectType.FAST_DIGGING);}
								}.runTask(nahkdMiscs.currentPlugin);
							}
						}
						if (TinkerItem.checkItem(player.getInventory().getItemInMainHand())) {
							TinkerItem i = TinkerItem.fromItem(player.getInventory().getItemInMainHand());
							for (ModifierType type : i.modifiers.keySet()) {
								if (type.ticker != null) type.ticker.onTick(i.modifiers.get(type), player);
							}
						}
					}
					for (ItemStack item : player.getInventory().getContents()) {
						if (DefaultItems.NATURE_LAVA_CRYSTAL_ACTIVATED.item.isSimilar(item)) new BukkitRunnable() {
							@Override
							public void run() {
								player.setFireTicks(100);
							}
						}.runTask(nahkdMiscs.currentPlugin);
					}
				}
				Thread.sleep(50);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
