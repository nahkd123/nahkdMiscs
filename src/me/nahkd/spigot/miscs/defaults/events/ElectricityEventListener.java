package me.nahkd.spigot.miscs.defaults.events;

import java.util.ArrayList;
import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

import me.nahkd.spigot.miscs.api.blocks.BlockData;
import me.nahkd.spigot.miscs.api.items.CustomItems;
import me.nahkd.spigot.miscs.api.power.Capacitor;
import me.nahkd.spigot.miscs.api.power.PowerableUltils;
import me.nahkd.spigot.miscs.api.world.WorldData;
import me.nahkd.spigot.miscs.api.world.WorldsData;
import me.nahkd.spigot.miscs.defaults.DefaultItems;

public class ElectricityEventListener implements Listener {
	
	@EventHandler(ignoreCancelled=true, priority=EventPriority.LOWEST)
	public void onBlockPlace(BlockPlaceEvent event) {
		if (Capacitor.isCapacitor(event.getItemInHand())) {
			// Capacitor placed
			event.setCancelled(true);
			event.getBlockReplacedState().setType(Material.STAINED_CLAY);
			event.getBlockReplacedState().setRawData((byte) 3);
			WorldData wd = WorldsData.getDataMap().get(event.getBlock().getWorld());
			BlockData bd = new BlockData(event.getBlock().getLocation(), DefaultItems.ELECTRICITY_CAPACITOR.block);
			//Bukkit.getPlayer("nahkd123").sendMessage("Block placed at " + bd.location.getBlockX() + " " + bd.location.getBlockY() + " " + bd.location.getBlockZ());
			bd.otherData = new YamlConfiguration();
			Capacitor b = Capacitor.fromItem(event.getItemInHand());
			bd.otherData.set("power.current", b.currentPower);
			bd.otherData.set("power.max", b.maxPower);
			bd.otherData.set("interface.power.allowed", true);
			bd.otherData.set("interface.power.speed", 5);
			bd.otherData.set("interface.power.in", "");
			bd.otherData.set("interface.power.out", "");
			bd.otherData.set("interface.item.allowed", false);
			wd.blockData.data.add(bd);
			bd.saveToConfig(wd.blockData.config);
			// Decrease item by 1
			if (event.getItemInHand().getAmount() > 1) {
				event.getItemInHand().setAmount(event.getItemInHand().getAmount() - 1);
				if (event.getHand() == EquipmentSlot.HAND) event.getPlayer().getInventory().setItemInMainHand(event.getItemInHand());
				if (event.getHand() == EquipmentSlot.OFF_HAND) event.getPlayer().getInventory().setItemInOffHand(event.getItemInHand());
			} else {
				if (event.getHand() == EquipmentSlot.HAND) event.getPlayer().getInventory().setItemInMainHand(null);
				if (event.getHand() == EquipmentSlot.OFF_HAND) event.getPlayer().getInventory().setItemInOffHand(null);
			}
			return;
		}
	}
	
	@EventHandler(ignoreCancelled=true, priority=EventPriority.LOWEST)
	public void onBlockDestroy(BlockBreakEvent event) {
		Location location = event.getBlock().getLocation();
		String theKey = location.getWorld().getName() + "|" + location.getBlockX() + "|" + location.getBlockY() + "|" + location.getBlockZ();
		WorldData wd = WorldsData.getDataMap().get(event.getBlock().getWorld());
		if (wd.blockData.config.contains(theKey)) {
			// The custom block is broken
			String id = wd.blockData.config.getString(theKey + ".id");
			for (int i = 0; i < wd.blockData.data.size(); i++) {
				BlockData dat = wd.blockData.data.get(i);
				if (id == DefaultItems.ELECTRICITY_CAPACITOR.id) {
					if (dat.location.getBlockX() == event.getBlock().getLocation().getBlockX() &&
							dat.location.getBlockY() == event.getBlock().getLocation().getBlockY() &&
							dat.location.getBlockZ() == event.getBlock().getLocation().getBlockZ()) {
						// Capacitor is broken
						event.setCancelled(true);
						event.getBlock().setType(Material.AIR);
						event.getBlock().getWorld().dropItem(event.getBlock().getLocation(), new Capacitor(dat.otherData.getDouble("power.current", 0.0), dat.otherData.getDouble("power.max", 0.0)).toItem());
						
						wd.blockData.data.remove(i);
						wd.blockData.config.set(theKey, null);
						return;
					}
				}
			}
		}
	}
	
	@EventHandler(ignoreCancelled=true, priority=EventPriority.LOWEST)
	public void onBlockInteract(PlayerInteractEvent event) {
		if (event.getHand() == EquipmentSlot.HAND && event.getAction() == Action.RIGHT_CLICK_BLOCK) {
			Location location = event.getClickedBlock().getLocation();
			String theKey = location.getWorld().getName() + "|" + location.getBlockX() + "|" + location.getBlockY() + "|" + location.getBlockZ();
			WorldData wd = WorldsData.getDataMap().get(event.getClickedBlock().getWorld());
			if (wd.blockData.config.contains(theKey)) {
				// Clicked the custom block
				String id = wd.blockData.config.getString(theKey + ".id");
				for (int i = 0; i < wd.blockData.data.size(); i++) {
					BlockData dat = wd.blockData.data.get(i);
					if (dat.location.getBlockX() == event.getClickedBlock().getLocation().getBlockX() &&
							dat.location.getBlockY() == event.getClickedBlock().getLocation().getBlockY() &&
							dat.location.getBlockZ() == event.getClickedBlock().getLocation().getBlockZ()) {
						if (DefaultItems.ELECTRICITY_TERMINAL.item.isSimilar(event.getItem())) {
							// Terminal placed. Set data to terminal
							event.setCancelled(true);
							Block b = event.getClickedBlock().getRelative(event.getBlockFace());
							if (event.getBlockFace() == BlockFace.EAST ||
									event.getBlockFace() == BlockFace.NORTH ||
									event.getBlockFace() == BlockFace.SOUTH ||
									event.getBlockFace() == BlockFace.WEST) {
								b.setType(Material.WALL_SIGN);
								if (event.getBlockFace() == BlockFace.NORTH) b.setData((byte) 2);
								if (event.getBlockFace() == BlockFace.WEST) b.setData((byte) 4);
								if (event.getBlockFace() == BlockFace.SOUTH) b.setData((byte) 3);
								if (event.getBlockFace() == BlockFace.EAST) b.setData((byte) 5);
							} else event.getClickedBlock().getRelative(event.getBlockFace()).setType(Material.SIGN_POST);

							BlockData bd = new BlockData(event.getClickedBlock().getRelative(event.getBlockFace()).getLocation(), DefaultItems.ELECTRICITY_TERMINAL.block);
							bd.otherData = new YamlConfiguration();
							bd.otherData.set("reflocation", location.getWorld().getName() + "|" + location.getBlockX() + "|" + location.getBlockY() + "|" + location.getBlockZ());
							wd.blockData.data.add(bd);
							bd.saveToConfig(wd.blockData.config);
							
							// Decrease item by 1
							if (event.getItem().getAmount() > 1) {
								event.getItem().setAmount(event.getItem().getAmount() - 1);
								if (event.getHand() == EquipmentSlot.HAND) event.getPlayer().getInventory().setItemInMainHand(event.getItem());
								if (event.getHand() == EquipmentSlot.OFF_HAND) event.getPlayer().getInventory().setItemInOffHand(event.getItem());
							} else {
								if (event.getHand() == EquipmentSlot.HAND) event.getPlayer().getInventory().setItemInMainHand(null);
								if (event.getHand() == EquipmentSlot.OFF_HAND) event.getPlayer().getInventory().setItemInOffHand(null);
							}
						} else if (dat != null && dat.otherData != null && dat.otherData.contains("power") && !event.getPlayer().isSneaking()) {
							// Powerable block
							event.getPlayer().openInventory(PowerableUltils.generateView(dat));
							event.setCancelled(true);
							return;
						}
					}
				}
			}
		}
	}
	
}
