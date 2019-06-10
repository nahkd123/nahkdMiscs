package me.nahkd.spigot.miscs.defaults.events;

import java.util.ArrayList;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

import me.nahkd.spigot.miscs.api.blocks.BlockData;
import me.nahkd.spigot.miscs.api.utils.ItemStackUltils;
import me.nahkd.spigot.miscs.api.utils.PlayerInventoryUltils;
import me.nahkd.spigot.miscs.api.world.WorldData;
import me.nahkd.spigot.miscs.api.world.WorldsData;
import me.nahkd.spigot.miscs.defaults.DefaultItems;
import me.nahkd.spigot.miscs.defaults.other.NatureAndGardeningLoots;

public class NatureAndGardeningListener implements Listener {
	
	@EventHandler
	public void itemInteract(PlayerInteractEvent event) {
		if (event.getAction() == Action.RIGHT_CLICK_BLOCK || event.getAction() == Action.RIGHT_CLICK_AIR) {
			Block sight = event.getPlayer().getTargetBlock(null, 6);
			if (sight.getType() == Material.WATER ||
				sight.getType() == Material.STATIONARY_WATER
				) if (DefaultItems.NATURE_WATERING_CAN.item.isSimilar(event.getItem()) ||
					DefaultItems.NATURE_WATERING_CAN_FILLED_0.item.isSimilar(event.getItem()) ||
					DefaultItems.NATURE_WATERING_CAN_FILLED_1.item.isSimilar(event.getItem()) ||
					DefaultItems.NATURE_WATERING_CAN_FILLED_2.item.isSimilar(event.getItem()) ||
					DefaultItems.NATURE_WATERING_CAN_FILLED_3.item.isSimilar(event.getItem())) {
					if (event.getItem().getAmount() > 1) {
						event.getItem().setAmount(event.getItem().getAmount() - 1);
						if (event.getHand() == EquipmentSlot.HAND) event.getPlayer().getInventory().setItemInMainHand(event.getItem());
						if (event.getHand() == EquipmentSlot.OFF_HAND) event.getPlayer().getInventory().setItemInOffHand(event.getItem());
					} else {
						if (event.getHand() == EquipmentSlot.HAND) event.getPlayer().getInventory().setItemInMainHand(null);
						if (event.getHand() == EquipmentSlot.OFF_HAND) event.getPlayer().getInventory().setItemInOffHand(null);
					}
					event.getPlayer().getInventory().addItem(DefaultItems.NATURE_WATERING_CAN_FILLED_0.item);
					event.setCancelled(true);
				} else if (DefaultItems.NATURE_WATERING_CAN_INFINITE.item.isSimilar(event.getItem())) {
					if (event.getItem().getAmount() > 1) {
						event.getItem().setAmount(event.getItem().getAmount() - 1);
						if (event.getHand() == EquipmentSlot.HAND) event.getPlayer().getInventory().setItemInMainHand(event.getItem());
						if (event.getHand() == EquipmentSlot.OFF_HAND) event.getPlayer().getInventory().setItemInOffHand(event.getItem());
					} else {
						if (event.getHand() == EquipmentSlot.HAND) event.getPlayer().getInventory().setItemInMainHand(null);
						if (event.getHand() == EquipmentSlot.OFF_HAND) event.getPlayer().getInventory().setItemInOffHand(null);
					}
					event.getPlayer().getInventory().addItem(DefaultItems.NATURE_WATERING_CAN_INFINITE_FILLED.item);
					event.setCancelled(true);
				}
		}
		if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
			if (DefaultItems.NATURE_WATERING_CAN_FILLED_0.item.isSimilar(event.getItem()) ||
				DefaultItems.NATURE_WATERING_CAN_FILLED_1.item.isSimilar(event.getItem()) ||
				DefaultItems.NATURE_WATERING_CAN_FILLED_2.item.isSimilar(event.getItem()) ||
				DefaultItems.NATURE_WATERING_CAN_FILLED_3.item.isSimilar(event.getItem())) {
				double power = 0;
				ArrayList<Block> dirt = new ArrayList<Block>();
				for (int x = 0; x < 3; x++) {
					for (int z = 0; z < 3; z++) {
						Block b = event.getClickedBlock().getWorld().getBlockAt(event.getClickedBlock().getX() - 1 + x, event.getClickedBlock().getY(), event.getClickedBlock().getZ() - 1 + z);
						if (b.getType() == Material.GRASS) power += 0.11;
						if (b.getType() == Material.DIRT) dirt.add(b);
					}
				}
				for (Block b : dirt) if (Math.random() < power) {
					b.setType(Material.GRASS);
					b.getWorld().spawnParticle(Particle.WATER_DROP, b.getLocation().add(0.5, 1.5, 0.5), 32);
				}
				PlayerInventoryUltils.replaceHandWith(event.getPlayer(), event.getItem(), event.getHand(), DefaultItems.NATURE_WATERING_CAN_FILLED_0.item, DefaultItems.NATURE_WATERING_CAN_FILLED_1.item, DefaultItems.NATURE_WATERING_CAN_FILLED_2.item, DefaultItems.NATURE_WATERING_CAN_FILLED_3.item, DefaultItems.NATURE_WATERING_CAN.item);
				event.setCancelled(true);
			} else if (DefaultItems.NATURE_WATERING_CAN_INFINITE_FILLED.item.isSimilar(event.getItem())) {
				double power = 0;
				ArrayList<Block> dirt = new ArrayList<Block>();
				for (int x = 0; x < 5; x++) {
					for (int z = 0; z < 5; z++) {
						Block b = event.getClickedBlock().getWorld().getBlockAt(event.getClickedBlock().getX() - 2 + x, event.getClickedBlock().getY(), event.getClickedBlock().getZ() - 2 + z);
						if (b.getType() == Material.GRASS) power += 0.04;
						if (b.getType() == Material.DIRT) dirt.add(b);
					}
				}
				for (Block b : dirt) if (Math.random() < power) {
					b.setType(Material.GRASS);
					b.getWorld().spawnParticle(Particle.WATER_DROP, b.getLocation().add(0.5, 1.5, 0.5), 32);
				}
				PlayerInventoryUltils.replaceHandWith(event.getPlayer(), event.getItem(), event.getHand(), DefaultItems.NATURE_WATERING_CAN_FILLED_0.item, DefaultItems.NATURE_WATERING_CAN_FILLED_1.item, DefaultItems.NATURE_WATERING_CAN_FILLED_2.item, DefaultItems.NATURE_WATERING_CAN_FILLED_3.item, DefaultItems.NATURE_WATERING_CAN.item);
				event.setCancelled(true);
			} else if (DefaultItems.NATURE_INFINITE_WATER_CRYSTAL.item.isSimilar(event.getItem())) {
				if (event.getClickedBlock().getRelative(event.getBlockFace()).getType() == Material.AIR ||
						event.getClickedBlock().getRelative(event.getBlockFace()).getType() == Material.WATER ||
						event.getClickedBlock().getRelative(event.getBlockFace()).getType() == Material.LAVA ||
						event.getClickedBlock().getRelative(event.getBlockFace()).getType() == Material.STATIONARY_LAVA) event.getClickedBlock().getRelative(event.getBlockFace()).setType(Material.STATIONARY_WATER);
				event.setCancelled(true);
			} else if (DefaultItems.NATURE_GRASS_SEED.item.isSimilar(event.getItem())) {
				if (event.getClickedBlock().getType() == Material.DIRT) {
					event.getClickedBlock().setType(Material.GRASS);
					// Decrease item by 1
					if (event.getItem().getAmount() > 1) {
						event.getItem().setAmount(event.getItem().getAmount() - 1);
						if (event.getHand() == EquipmentSlot.HAND) event.getPlayer().getInventory().setItemInMainHand(event.getItem());
						if (event.getHand() == EquipmentSlot.OFF_HAND) event.getPlayer().getInventory().setItemInOffHand(event.getItem());
					} else {
						if (event.getHand() == EquipmentSlot.HAND) event.getPlayer().getInventory().setItemInMainHand(null);
						if (event.getHand() == EquipmentSlot.OFF_HAND) event.getPlayer().getInventory().setItemInOffHand(null);
					}
				}
				event.setCancelled(true);
			}
		}
		if (event.getAction() == Action.RIGHT_CLICK_AIR) {
			if (DefaultItems.NATURE_WATERING_CAN_FILLED_0.item.isSimilar(event.getItem()) ||
				DefaultItems.NATURE_WATERING_CAN_FILLED_1.item.isSimilar(event.getItem()) ||
				DefaultItems.NATURE_WATERING_CAN_FILLED_2.item.isSimilar(event.getItem()) ||
				DefaultItems.NATURE_WATERING_CAN_FILLED_3.item.isSimilar(event.getItem()) ||
				DefaultItems.NATURE_WATERING_CAN_INFINITE_FILLED.item.isSimilar(event.getItem())) {
				event.setCancelled(true);
			}
		}
	}
	
	@EventHandler
	public void blockPlacing(BlockPlaceEvent event) {
		if (DefaultItems.NATURE_LAVA_CRYSTAL_ACTIVATED.item.isSimilar(event.getItemInHand())) {
			event.getBlockPlaced().setType(Material.STATIONARY_LAVA);
			event.getPlayer().sendMessage("§7§oPhew, it's over now...");
		} else if (DefaultItems.NATURE_WATER_CRYSTAL.item.isSimilar(event.getItemInHand())) {
			event.getBlockPlaced().setType(Material.STATIONARY_WATER);
		}
	}
	
	@EventHandler
	public void blockDestroying(BlockBreakEvent event) {
		if (event.getPlayer().getInventory().getItemInMainHand() != null && event.getPlayer().getInventory().getItemInMainHand().getType() != Material.AIR) {
			ItemStack holdingItem = event.getPlayer().getInventory().getItemInMainHand();
			if (ItemStackUltils.compareTool(DefaultItems.NATURE_CROOK.item, holdingItem)) {
				// Destroying with Crook
				// I prefer to try switch, but seem like it doesn't work like I wanted to
				
				double luck = 1.0D;
				if (holdingItem.getItemMeta().hasEnchant(Enchantment.LOOT_BONUS_BLOCKS)) {
					luck += holdingItem.getItemMeta().getEnchantLevel(Enchantment.LOOT_BONUS_BLOCKS) * 0.1D;
				}
				
				if (event.getBlock().getType() == Material.LEAVES || event.getBlock().getType() == Material.LEAVES_2) {
					ArrayList<ItemStack> newLoot = NatureAndGardeningLoots.crook_leaves.generateOutputLessthan(luck);
					for (ItemStack i : newLoot) event.getBlock().getWorld().dropItem(event.getBlock().getLocation(), i);
					event.setCancelled(true);
					event.getBlock().setType(Material.AIR);
				} else if (event.getBlock().getType() == Material.LONG_GRASS) {
					ArrayList<ItemStack> newLoot = NatureAndGardeningLoots.crook_grass.generateOutputLessthan(luck);
					event.getPlayer().sendMessage(newLoot.size() + "");
					for (ItemStack i : newLoot) event.getBlock().getWorld().dropItem(event.getBlock().getLocation(), i);
					event.setCancelled(true);
					event.getBlock().setType(Material.AIR);
				}
				
				// Decrease durability
				if (!holdingItem.getItemMeta().isUnbreakable()) {
					if (holdingItem.getDurability() > holdingItem.getType().getMaxDurability()) event.getPlayer().getInventory().setItemInMainHand(null);
					else {
						holdingItem.setDurability((short) (holdingItem.getDurability() + 1));
						event.getPlayer().getInventory().setItemInMainHand(holdingItem);
					}
				}
			}
		}
	}
	
	@EventHandler
	public void interactToBlock(PlayerInteractEvent event) {
		if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
			Location location = event.getClickedBlock().getLocation();
			String theKey = location.getWorld().getName() + "|" + location.getBlockX() + "|" + location.getBlockY() + "|" + location.getBlockZ();
			WorldData wd = WorldsData.getDataMap().get(event.getClickedBlock().getWorld());
			if (wd.blockData.config.contains(theKey)) {
				for (int i = 0; i < wd.blockData.data.size(); i++) {
					BlockData dat = wd.blockData.data.get(i);
					if (dat.block.item.id == "NATURE_WEED_DRUG" && event.getBlockFace() == BlockFace.UP) {
						event.getPlayer().sendMessage("§cWhy you want to bug things?");
						event.setCancelled(true);
					}
				}
			}
		}
	}
	
}
