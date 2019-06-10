package me.nahkd.spigot.miscs.api.tinkering;

import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public interface ModifierEvents {
	
	public void toolBreakBlockEvent(BlockBreakEvent event, Modifier modifier);
	public void toolInteractEvent(PlayerInteractEvent event, Modifier modifier);
	public void toolInteractEntityEvent(PlayerInteractEntityEvent event, Modifier modifier);
	public void weaponDamageEntityEvent(EntityDamageByEntityEvent event, Modifier modifier);
	
}
