package me.nahkd.spigot.miscs.defaults.events;

import java.util.List;
import java.util.Map;
import java.util.Random;

import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.nahkd.spigot.miscs.api.blocks.BlockData;
import me.nahkd.spigot.miscs.api.tinkering.Modifier;
import me.nahkd.spigot.miscs.api.tinkering.ModifierType;
import me.nahkd.spigot.miscs.api.tinkering.ModifierTypesManager;
import me.nahkd.spigot.miscs.api.tinkering.TinkerItem;
import me.nahkd.spigot.miscs.api.tinkering.TinkerItem.MiningLevel;
import me.nahkd.spigot.miscs.api.utils.BlockUltils;
import me.nahkd.spigot.miscs.api.utils.ItemStackUltils;
import me.nahkd.spigot.miscs.api.world.WorldsData;
import me.nahkd.spigot.miscs.defaults.DefaultItems;

public class TinkeringEventListener implements Listener {
	
	public Random randomizer;
	
	public TinkeringEventListener() {
		randomizer = new Random();
	}
	
	@EventHandler(ignoreCancelled=true)
	public void onBreakBlock(BlockBreakEvent event) {
		if (TinkerItem.checkItem(event.getPlayer().getInventory().getItemInMainHand())) {
			short oldDamage = event.getPlayer().getInventory().getItemInMainHand().getDurability();
			Map<Enchantment, Integer> enchs = event.getPlayer().getInventory().getItemInMainHand().getEnchantments();
			
			TinkerItem item = TinkerItem.fromItem(event.getPlayer().getInventory().getItemInMainHand());
			for (ModifierType modType : item.modifiers.keySet()) {
				Modifier mod = item.modifiers.get(modType);
				if (modType.event != null) modType.event.toolBreakBlockEvent(event, mod);
			}
			if (event.isCancelled()) return;
			item.currentExp++;
			if (item.currentExp >= TinkerItem.getRequiredExpForNextLevel(item.toolLevel)) {
				item.currentExp = 0;
				item.toolLevel += 1;
				event.getPlayer().sendMessage("§aYour tool has been leveled up!");
				if (item.toolLevel == 10) {
					item.emptyModifierSlots++;
					event.getPlayer().sendMessage("§3Your tool's empty modifiers slot increases by 1!");
					if (item.level == MiningLevel.WOOD) {
						event.getPlayer().sendMessage("§3Your tool's mining level is now §7Stone§3!");
						item.level = MiningLevel.STONE;
					} else if (item.level == MiningLevel.STONE) {
						event.getPlayer().sendMessage("§3Your tool's mining level is now §fIron§3!");
						item.level = MiningLevel.IRON;
					} else if (item.level == MiningLevel.IRON) {
						event.getPlayer().sendMessage("§3Your tool's mining level is now §bDiamond§3!");
						item.level = MiningLevel.DIAMOND;
					}
				}
			}
			
			ItemStack ui = item.toItemStack();
			double decreaseDurabilityPower = 1.0D;
			if (enchs.containsKey(Enchantment.DURABILITY)) {
				decreaseDurabilityPower -= ((double) enchs.get(Enchantment.DURABILITY)) * 0.1D;
			}
			if (ui.getItemMeta().isUnbreakable()) decreaseDurabilityPower = -1.0D;
			if (Math.random() <= decreaseDurabilityPower) ui.setDurability((short) (oldDamage + 1));
			else ui.setDurability((short) oldDamage);
			if ((oldDamage + 1) >= ui.getType().getMaxDurability()) {
				ui.setDurability(ui.getType().getMaxDurability());
				ItemMeta met = ui.getItemMeta();
				List<String> lore = met.getLore();
				lore.set(0, "§4Broken Tinkers's tool");
				met.setLore(lore);
				ui.setItemMeta(met);
				event.getPlayer().sendMessage("§cOh no, your tool is now broken.");
				event.getPlayer().sendMessage("§7§oTip: Use §fDuct Tape\u2122");
			}
			
			// Readd enchantment from previous ItemStack
			ui.addEnchantments(enchs);
			
			// Set new item
			event.getPlayer().getInventory().setItemInMainHand(ui);
			
			// Mine block with custom item
			event.setCancelled(true);
			/*ItemStack tool = new ItemStack(MiningLevel.getMaterialByMiningLevel(ui.getType(), item.level));
			event.getBlock().breakNaturally(tool);*/ // doesn't work
			BlockUltils.breakBlock(event.getBlock().getLocation(), ui);
			
		}
	}
	
	@EventHandler
	public void onInteract(PlayerInteractEvent event) {
		// Duct Tape
		if (DefaultItems.TINKERING_DUCT_TAPE.item.isSimilar(event.getItem()) && event.getHand() == EquipmentSlot.HAND) {
			if (event.getPlayer().getInventory().getItemInOffHand() != null && ItemStackUltils.isItemDamagable(event.getPlayer().getInventory().getItemInOffHand().getType())) {
				event.getPlayer().getInventory().setItemInOffHand(ItemStackUltils.repairItem(event.getPlayer().getInventory().getItemInOffHand(), 0.05D));
				if (event.getPlayer().getInventory().getItemInMainHand().getAmount() > 1) {
					event.getItem().setAmount(event.getItem().getAmount() - 1);
					event.getPlayer().getInventory().setItemInMainHand(event.getItem());
				} else event.getPlayer().getInventory().setItemInMainHand(null);
			}
		}
		// Flex Tape
		if (DefaultItems.TINKERING_FLEX_TAPE.item.isSimilar(event.getItem()) && event.getHand() == EquipmentSlot.HAND) {
			if (event.getPlayer().getInventory().getItemInOffHand() != null && ItemStackUltils.isItemDamagable(event.getPlayer().getInventory().getItemInOffHand().getType())) {
				event.getPlayer().getInventory().setItemInOffHand(ItemStackUltils.repairItem(event.getPlayer().getInventory().getItemInOffHand(), 0.27D));
				if (event.getPlayer().getInventory().getItemInMainHand().getAmount() > 1) {
					event.getItem().setAmount(event.getItem().getAmount() - 1);
					event.getPlayer().getInventory().setItemInMainHand(event.getItem());
				} else event.getPlayer().getInventory().setItemInMainHand(null);
			}
		}
		
		// Tool Station and Tool Forge
		if (event.getHand() == EquipmentSlot.HAND && event.getAction() == Action.RIGHT_CLICK_BLOCK) {
			BlockData data = WorldsData.getDataMap().get(event.getClickedBlock().getWorld()).blockData.queryData(event.getClickedBlock().getLocation());
			if (data != null) {
				if (data.block.item.id == "TINKERING_TOOL_STATION" || data.block.item.id == "TINKERING_TOOL_FORGE") {
					event.setCancelled(true);
					ConfigurationSection otherData = null;
					if (data.otherData == null) otherData = new YamlConfiguration();
					else otherData = data.otherData;
					
					if (otherData.contains("awaitingItem")) {
						// Contains item in the block
						if (event.getItem() == null || event.getItem().getType() == Material.AIR) {
							// Take item out
							event.getPlayer().getInventory().addItem(otherData.getItemStack("awaitingItem"));
							otherData.set("awaitingItem", null);
						} else {
							Map<Enchantment, Integer> enchants = otherData.getItemStack("awaitingItem").getEnchantments();
							TinkerItem i = TinkerItem.fromItem(otherData.getItemStack("awaitingItem"));
							for (String typename : ModifierTypesManager.getMap().keySet()) {
								ModifierType type = ModifierTypesManager.getMap().get(typename);
								if (type.item.isSimilar(event.getItem())) {
									if (i.modifiers.containsKey(type)) {
										// Modifier exists
										Modifier mod = i.modifiers.get(type);
										if (mod.currentItems <= 0) {
											if (i.emptyModifierSlots > 0) {
												i.emptyModifierSlots--;
											} else {
												event.getPlayer().sendMessage("§cYour item need more empty modifier slot");
												return;
											}
										}
										mod.currentItems++;
										if (event.getItem().getAmount() > 1) {
											ItemStack u = new ItemStack(event.getItem());
											u.setAmount(u.getAmount() - 1);
											event.getPlayer().getInventory().setItemInMainHand(u);
										} else event.getPlayer().getInventory().setItemInMainHand(null);
										if (mod.currentItems >= type.maxItems) {
											mod.level++;
											mod.currentItems = 0;
											event.getPlayer().sendMessage("§3Modifier " + type.name + " §3increased to level " + mod.level);
										}
									} else {
										// Modifier not found
										if (i.emptyModifierSlots > 0) {
											// Empty Modifier exists
											i.emptyModifierSlots--;
											i.modifiers.put(type, new Modifier(type, 0, 1));
										} else {
											event.getPlayer().sendMessage("§cYour item need more empty modifier slot");
											return;
										}
									}
								}
							}
							ItemStack newI = i.toItemStack();
							newI.addEnchantments(enchants);
							otherData.set("awaitingItem", newI);
						}
						data.otherData = otherData;
					} else {
						// Put item to block
						if (TinkerItem.checkItem(event.getItem())) {
							TinkerItem i = TinkerItem.fromItem(event.getItem());
							if (data.block.item.id == "TINKERING_TOOL_STATION") {
								if (MiningLevel.getLevelFromMaterial(i.itemMeterial) == MiningLevel.WOOD || MiningLevel.getLevelFromMaterial(i.itemMeterial) == MiningLevel.STONE) {
									otherData.set("awaitingItem", event.getItem());
									event.getPlayer().getInventory().setItemInMainHand(null);
								} else {
									event.getPlayer().sendMessage("§cThe item you're trying to put is not supported.");
									event.getPlayer().sendMessage("§7§oTip: Use §bTool Station");
								}
							} else {
								otherData.set("awaitingItem", event.getItem());
								event.getPlayer().getInventory().setItemInMainHand(null);
							}
							data.otherData = otherData;
						} else event.getPlayer().sendMessage("§cPlease put tinkers's tool first!");
					}
				}
			}
		}
	}
	
	@EventHandler(ignoreCancelled=true)
	public void onEntityInteract(PlayerInteractEntityEvent event) {
		if (event.getHand() == EquipmentSlot.HAND && event.getPlayer().getInventory().getItemInMainHand() != null && event.getPlayer().getInventory().getItemInMainHand().getType() != Material.AIR) {
			if (TinkerItem.checkItem(event.getPlayer().getInventory().getItemInMainHand())) {
				TinkerItem item = TinkerItem.fromItem(event.getPlayer().getInventory().getItemInMainHand());
				for (ModifierType modType : item.modifiers.keySet()) {
					Modifier mod = item.modifiers.get(modType);
					if (modType.event != null) modType.event.toolInteractEntityEvent(event, mod);
				}
			}
		}
	}
	
	@EventHandler(ignoreCancelled=true)
	public void onEntityDamage(EntityDamageByEntityEvent event) {
		if (event.getDamager() instanceof Player) {
			Player player = (Player) event.getDamager();
			if (TinkerItem.checkItem(player.getInventory().getItemInMainHand())) {
				Map<Enchantment, Integer> enchs = player.getInventory().getItemInMainHand().getEnchantments();
				short oldDamage = player.getInventory().getItemInMainHand().getDurability();
				TinkerItem item = TinkerItem.fromItem(player.getInventory().getItemInMainHand());
				for (ModifierType modType : item.modifiers.keySet()) {
					Modifier mod = item.modifiers.get(modType);
					if (modType.event != null) modType.event.weaponDamageEntityEvent(event, mod);
				}
				if (event.isCancelled()) return;
				item.currentExp++;
				if (item.currentExp >= TinkerItem.getRequiredExpForNextLevel(item.toolLevel)) {
					item.currentExp = 0;
					item.toolLevel += 1;
					player.sendMessage("§aYour tool has been leveled up!");
					if (item.toolLevel == 10) {
						item.emptyModifierSlots++;
						player.sendMessage("§3Your tool's empty modifiers slot increases by 1!");
						if (item.level == MiningLevel.WOOD) {
							player.sendMessage("§3Your tool's mining level is now §7Stone§3!");
							item.level = MiningLevel.STONE;
						} else if (item.level == MiningLevel.STONE) {
							player.sendMessage("§3Your tool's mining level is now §fIron§3!");
							item.level = MiningLevel.IRON;
						} else if (item.level == MiningLevel.IRON) {
							player.sendMessage("§3Your tool's mining level is now §bDiamond§3!");
							item.level = MiningLevel.DIAMOND;
						}
					}
				}
				
				ItemStack ui = item.toItemStack();
				double decreaseDurabilityPower = 1.0D;
				if (enchs.containsKey(Enchantment.DURABILITY)) {
					decreaseDurabilityPower -= ((double) enchs.get(Enchantment.DURABILITY)) * 0.1D;
				}
				if (ui.getItemMeta().isUnbreakable()) decreaseDurabilityPower = -1.0D;
				if (Math.random() <= decreaseDurabilityPower) ui.setDurability((short) (oldDamage + 1));
				else ui.setDurability((short) oldDamage);
				if ((oldDamage + 1) >= ui.getType().getMaxDurability()) {
					ui.setDurability(ui.getType().getMaxDurability());
					ItemMeta met = ui.getItemMeta();
					List<String> lore = met.getLore();
					lore.set(0, "§4Broken Tinkers's tool");
					met.setLore(lore);
					ui.setItemMeta(met);
					player.sendMessage("§cOh no, your tool is now broken.");
					player.sendMessage("§7§oTip: Use §fDuct Tape\u2122");
				}
				
				// Readd enchantment from previous ItemStack
				ui.addEnchantments(enchs);
				
				// Set new item
				player.getInventory().setItemInMainHand(ui);
			}
		}
	}
	
}
