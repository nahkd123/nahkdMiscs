package me.nahkd.spigot.miscs.defaults.events;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import me.nahkd.spigot.miscs.api.blocks.BlockData;
import me.nahkd.spigot.miscs.api.utils.ItemStackUltils;
import me.nahkd.spigot.miscs.api.utils.MetalParser;
import me.nahkd.spigot.miscs.api.world.WorldsData;
import me.nahkd.spigot.miscs.defaults.DefaultItems;

public class CobblestoneResourcesEventListener implements Listener {
	
	@EventHandler(ignoreCancelled=true)
	public void onInteract(PlayerInteractEvent event) {
		if (event.getHand() == EquipmentSlot.HAND && event.getAction() == Action.RIGHT_CLICK_BLOCK) {
			BlockData data = WorldsData.getDataMap().get(event.getClickedBlock().getWorld()).blockData.queryData(event.getClickedBlock().getLocation());
			if (data != null) {
				if (data.block.item.id == "COBBLERES_BLOCKS_SMELTERY") {
					// Smeltery
					int fireTicks = 0;
					if (data.otherData != null) fireTicks = data.otherData.getInt("ticks", 0);
					if (event.getItem() != null) {
						if (event.getItem().isSimilar(new ItemStack(Material.COAL))) {
							// Fire tick increases
							fireTicks += 200;
							if (event.getItem().getAmount() > 1) {
								event.getItem().setAmount(event.getItem().getAmount() - 1);
								if (event.getHand() == EquipmentSlot.HAND) event.getPlayer().getInventory().setItemInMainHand(event.getItem());
								if (event.getHand() == EquipmentSlot.OFF_HAND) event.getPlayer().getInventory().setItemInOffHand(event.getItem());
							} else {
								if (event.getHand() == EquipmentSlot.HAND) event.getPlayer().getInventory().setItemInMainHand(null);
								if (event.getHand() == EquipmentSlot.OFF_HAND) event.getPlayer().getInventory().setItemInOffHand(null);
							}
						} else if (event.getItem().isSimilar(new ItemStack(Material.COAL, 1, (short) 1))) {
							fireTicks += 100;
							if (event.getItem().getAmount() > 1) {
								event.getItem().setAmount(event.getItem().getAmount() - 1);
								if (event.getHand() == EquipmentSlot.HAND) event.getPlayer().getInventory().setItemInMainHand(event.getItem());
								if (event.getHand() == EquipmentSlot.OFF_HAND) event.getPlayer().getInventory().setItemInOffHand(event.getItem());
							} else {
								if (event.getHand() == EquipmentSlot.HAND) event.getPlayer().getInventory().setItemInMainHand(null);
								if (event.getHand() == EquipmentSlot.OFF_HAND) event.getPlayer().getInventory().setItemInOffHand(null);
							}
						} else if (event.getItem().isSimilar(DefaultItems.NATURE_CHARCOAL.item)) {
							fireTicks += 60;
							if (event.getItem().getAmount() > 1) {
								event.getItem().setAmount(event.getItem().getAmount() - 1);
								if (event.getHand() == EquipmentSlot.HAND) event.getPlayer().getInventory().setItemInMainHand(event.getItem());
								if (event.getHand() == EquipmentSlot.OFF_HAND) event.getPlayer().getInventory().setItemInOffHand(event.getItem());
							} else {
								if (event.getHand() == EquipmentSlot.HAND) event.getPlayer().getInventory().setItemInMainHand(null);
								if (event.getHand() == EquipmentSlot.OFF_HAND) event.getPlayer().getInventory().setItemInOffHand(null);
							}
						} else if (MetalParser.fromSmeltery(event.getItem()) != null && fireTicks > 20) {
							fireTicks -= 20;
							if (event.getItem().getAmount() > 1) {
								event.getItem().setAmount(event.getItem().getAmount() - 1);
								if (event.getHand() == EquipmentSlot.HAND) event.getPlayer().getInventory().setItemInMainHand(event.getItem());
								if (event.getHand() == EquipmentSlot.OFF_HAND) event.getPlayer().getInventory().setItemInOffHand(event.getItem());
							} else {
								if (event.getHand() == EquipmentSlot.HAND) event.getPlayer().getInventory().setItemInMainHand(null);
								if (event.getHand() == EquipmentSlot.OFF_HAND) event.getPlayer().getInventory().setItemInOffHand(null);
							}
							event.getPlayer().getInventory().addItem(MetalParser.fromSmeltery(event.getItem()));
						}
						if (fireTicks > 1200) fireTicks = 1200;
						if (data.otherData == null) data.otherData = new YamlConfiguration();
						data.otherData.set("ticks", fireTicks);
					}
					event.setCancelled(true);
					return;
				}
			}
		}
	}
	
	@EventHandler
	public void onBlockBreak(BlockBreakEvent event) {
		if (ItemStackUltils.compareTool(event.getPlayer().getInventory().getItemInMainHand(), DefaultItems.COBBLERES_TOOLS_STONE_HAMMER.item) ||
			ItemStackUltils.compareTool(event.getPlayer().getInventory().getItemInMainHand(), DefaultItems.COBBLERES_TOOLS_IRON_HAMMER.item) ||
			ItemStackUltils.compareTool(event.getPlayer().getInventory().getItemInMainHand(), DefaultItems.COBBLERES_TOOLS_GOLD_HAMMER.item) ||
			ItemStackUltils.compareTool(event.getPlayer().getInventory().getItemInMainHand(), DefaultItems.COBBLERES_TOOLS_DIAMOND_HAMMER.item)) {
			boolean correctBlock = false;
			if (event.getBlock().getType() == Material.STONE ||
				event.getBlock().getType() == Material.COBBLESTONE) {
				event.getBlock().getWorld().dropItem(event.getBlock().getLocation().add(0.5, 0.5, 0.5), new ItemStack(Material.GRAVEL));
				correctBlock = true;
			}
			if (event.getBlock().getType() == Material.GRAVEL ||
				event.getBlock().getType() == Material.SANDSTONE) {
				event.getBlock().getWorld().dropItem(event.getBlock().getLocation().add(0.5, 0.5, 0.5), new ItemStack(Material.SAND));
				correctBlock = true;
			}
			
			if (correctBlock) {
				event.getBlock().setType(Material.AIR);
				
				// Decrease durability
				if (!event.getPlayer().getInventory().getItemInMainHand().getItemMeta().isUnbreakable()) {
					if (event.getPlayer().getInventory().getItemInMainHand().getDurability() > event.getPlayer().getInventory().getItemInMainHand().getType().getMaxDurability()) event.getPlayer().getInventory().setItemInMainHand(null);
					else {
						event.getPlayer().getInventory().getItemInMainHand().setDurability((short) (event.getPlayer().getInventory().getItemInMainHand().getDurability() + 1));
						event.getPlayer().getInventory().setItemInMainHand(event.getPlayer().getInventory().getItemInMainHand());
					}
				}
			}
			event.setCancelled(true);
		}
	}
	
}
