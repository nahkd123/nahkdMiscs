package me.nahkd.spigot.miscs.defaults;

import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import me.nahkd.spigot.miscs.nahkdMiscs;
import me.nahkd.spigot.miscs.api.tinkering.Modifier;
import me.nahkd.spigot.miscs.api.tinkering.ModifierEvents;
import me.nahkd.spigot.miscs.api.tinkering.ModifierTicker;
import me.nahkd.spigot.miscs.api.tinkering.ModifierType;

public class DefaultModifierTypes {
	
	public static ModifierType HASTE = new ModifierType("§eHaste", 64, new ItemStack(Material.GLOWSTONE_DUST));
	public static ModifierType WITHER = new ModifierType("§8Wither", 15, new ItemStack(Material.SKULL_ITEM, 1, (short) 1));
	
	public static void init() {
		HASTE.register();
		HASTE.ticker = new ModifierTicker() {
			@Override
			public void onTick(Modifier modifier, Player player) {
				if (modifier.level > 0) new BukkitRunnable() {
					@Override
					public void run() {player.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 40, modifier.level - 1));}
				}.runTask(nahkdMiscs.currentPlugin);
			}
		};
		WITHER.register();
		WITHER.event = new ModifierEvents() {
			
			@Override
			public void weaponDamageEntityEvent(EntityDamageByEntityEvent event, Modifier modifier) {
				if (modifier.level > 0) new BukkitRunnable() {
					@Override
					public void run() {
						if (event.getEntity() instanceof LivingEntity) {
							LivingEntity e = (LivingEntity) event.getEntity();
							e.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 20 * modifier.level, 0));
						}
					}
				}.runTask(nahkdMiscs.currentPlugin);
			}
			
			@Override
			public void toolInteractEvent(PlayerInteractEvent event, Modifier modifier) {}
			
			@Override
			public void toolInteractEntityEvent(PlayerInteractEntityEvent event, Modifier modifier) {}
			@Override
			public void toolBreakBlockEvent(BlockBreakEvent event, Modifier modifier) {}
		};
	}
	
}
