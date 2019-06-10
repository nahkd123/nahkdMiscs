package me.nahkd.spigot.miscs.api.power;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import me.nahkd.spigot.miscs.api.ItemBuilder;
import me.nahkd.spigot.miscs.api.blocks.BlockData;
import me.nahkd.spigot.miscs.api.world.WorldsData;

public class PowerableUltils {
	
	public static ItemStack gui_interface_power = new ItemBuilder(Material.REDSTONE).name("§3Power Interfaces").addLore("§aGreen§7: Input", "§cRed§7: Output", "§7Grey: None").create();
	public static ItemStack gui_interface_item = new ItemBuilder(Material.DIAMOND).name("§3Item Interfaces").addLore("§aGreen§7: Input", "§cRed§7: Output", "§7Grey: None").create();
	
	public static ItemStack gui_interface_top_on = new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 5).name("§aTop").addLore("§7Click to change").create();
	public static ItemStack gui_interface_bottom_on = new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 5).name("§aBottom").addLore("§7Click to change").create();
	public static ItemStack gui_interface_north_on = new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 5).name("§aNorth").addLore("§7Click to change").create();
	public static ItemStack gui_interface_south_on = new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 5).name("§aSouth").addLore("§7Click to change").create();
	public static ItemStack gui_interface_west_on = new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 5).name("§aWest").addLore("§7Click to change").create();
	public static ItemStack gui_interface_east_on = new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 5).name("§aEast").addLore("§7Click to change").create();
	
	public static ItemStack gui_interface_top_off = new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 7).name("§7Top").addLore("§7Click to change").create();
	public static ItemStack gui_interface_bottom_off = new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 7).name("§7Bottom").addLore("§7Click to change").create();
	public static ItemStack gui_interface_north_off = new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 7).name("§7North").addLore("§7Click to change").create();
	public static ItemStack gui_interface_south_off = new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 7).name("§7South").addLore("§7Click to change").create();
	public static ItemStack gui_interface_west_off = new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 7).name("§7West").addLore("§7Click to change").create();
	public static ItemStack gui_interface_east_off = new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 7).name("§7East").addLore("§7Click to change").create();
	
	public static ItemStack gui_interface_top_on_out = new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 14).name("§cTop").addLore("§7Click to change").create();
	public static ItemStack gui_interface_bottom_on_out = new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 14).name("§cBottom").addLore("§7Click to change").create();
	public static ItemStack gui_interface_north_on_out = new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 14).name("§cNorth").addLore("§7Click to change").create();
	public static ItemStack gui_interface_south_on_out = new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 14).name("§cSouth").addLore("§7Click to change").create();
	public static ItemStack gui_interface_west_on_out = new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 14).name("§cWest").addLore("§7Click to change").create();
	public static ItemStack gui_interface_east_on_out = new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 14).name("§cEast").addLore("§7Click to change").create();
	
	public static boolean isBlockPowerable(ConfigurationSection blockOtherData) {
		if (blockOtherData == null) return false;
		if (blockOtherData.contains("power")) return true;
		return false;
	}
	public static Inventory generateView(BlockData data) {
		if (data.otherData != null && data.otherData.contains("power")) {
			// Init
			if (!data.otherData.contains("_invdat.blockView")) {
				data.otherData.set("_invdat.blockView.facesMode", 0);
			}
			
			Inventory inv = Bukkit.createInventory(null, 54, "§b§p§o§w§e§r§g§u§i" + data.block.item.item.getItemMeta().getDisplayName());
			inv.setItem(0, new ItemBuilder(Material.STAINED_GLASS).name("§3Status").addLore("§7Power: §f" + data.otherData.getDouble("power.current", 0.0) + "/" + data.otherData.getDouble("power.max", 16.0) + " §cNP").create());
			inv.setItem(8, gui_interface_power);
			
			String rawCoor = data.location.getWorld().getName() + "|" + data.location.getBlockX() + "|" + data.location.getBlockY() + "|" + data.location.getBlockZ();
			inv.setItem(16, new ItemBuilder(Material.CHEST).name("§3Block data:").addLore("§7World: §f" + data.location.getWorld().getName(), "§7Coor: §f" + data.location.getBlockX() + "/" + data.location.getBlockY() + "/" + data.location.getBlockZ(), "§7Raw data: §f" + rawCoor).create());
			
			int view_faceMode = data.otherData.getInt("_invdat.blockView.facesMode", 0);
			inv.setItem(6,  gui_interface_top_off);
			inv.setItem(7,  gui_interface_north_off);
			inv.setItem(15, gui_interface_west_off);
			inv.setItem(17, gui_interface_east_off);
			inv.setItem(24, gui_interface_bottom_off);
			inv.setItem(25, gui_interface_south_off);
			if (view_faceMode == 0) {
				for (Interfaces i : Interfaces.getFromString(data.otherData.getString("interface.power.in", "b"))) switch (i) {
				case Top:    inv.setItem(6,  gui_interface_top_on); break;
				case Bottom: inv.setItem(24, gui_interface_bottom_on); break;
				case North:  inv.setItem(7,  gui_interface_north_on); break;
				case East:   inv.setItem(17, gui_interface_east_on); break;
				case South:  inv.setItem(25, gui_interface_south_on); break;
				case West:   inv.setItem(15, gui_interface_west_on); break;
				default: // No
				}
				for (Interfaces i : Interfaces.getFromString(data.otherData.getString("interface.power.out", "t"))) switch (i) {
				case Top:    inv.setItem(6,  gui_interface_top_on_out); break;
				case Bottom: inv.setItem(24, gui_interface_bottom_on_out); break;
				case North : inv.setItem(7,  gui_interface_north_on_out); break;
				case East:   inv.setItem(17, gui_interface_east_on_out); break;
				case South:  inv.setItem(25, gui_interface_south_on_out); break;
				case West:   inv.setItem(15, gui_interface_west_on_out); break;
				default: // No
				}
			}
			return inv;
		}
		return null;
	}
	
	public static void tickingBlock(BlockData data) {
		if (data.otherData != null && data.otherData.contains("interface")) {
			if (data.otherData.getBoolean("interface.power.allowed", false)) {
				// Buffer: Store input power
				double power = data.otherData.getDouble("power.current", 0.0D);
				double maxPower = data.otherData.getDouble("power.max", 0.5D);
				// Transfer power (output)
				for (Interfaces i : Interfaces.getFromString(data.otherData.getString("interface.power.out", "s"))) {
					BlockData data2 = null;
					Location newloc = new Location(data.location.getWorld(), data.location.getX(), data.location.getY(), data.location.getZ());
					if (i == Interfaces.Top) data2 = WorldsData.getDataMap().get(data.location.getWorld()).blockData.queryData(newloc.add(0, 1, 0));
					if (i == Interfaces.Bottom) data2 = WorldsData.getDataMap().get(data.location.getWorld()).blockData.queryData(newloc.add(0, -1, 0));
					if (i == Interfaces.South) data2 = WorldsData.getDataMap().get(data.location.getWorld()).blockData.queryData(newloc.add(0, 0, 1));
					if (i == Interfaces.North) data2 = WorldsData.getDataMap().get(data.location.getWorld()).blockData.queryData(newloc.add(0, 0, -1));
					if (i == Interfaces.East) data2 = WorldsData.getDataMap().get(data.location.getWorld()).blockData.queryData(newloc.add(1, 0, 0));
					if (i == Interfaces.West) data2 = WorldsData.getDataMap().get(data.location.getWorld()).blockData.queryData(newloc.add(-1, 0, 0));
					if (data2 != null) {
						if (data2.otherData != null && data2.otherData.contains("interface.power.in") && Interfaces.getFromString(data2.otherData.getString("interface.power.in", "n")).contains(i.getRelative())) {
							// Top block accepted input from bottom
							double inputSpeed = data2.otherData.getDouble("interface.power.speed", 0.25);
							double power2 = data2.otherData.getDouble("power.current", 0.0D);
							double maxpower2 = data2.otherData.getDouble("power.max", 16.0D);
							
							// Calculate power to transfer to destination
							double powerUse = ((maxpower2 - power2) > power)? power : (maxpower2 - power2);
							powerUse = Math.min(inputSpeed, powerUse);
							
							power -= powerUse;
							power2 += powerUse;
							data2.otherData.set("power.current", Math.round(power2 * 1000.0) / 1000.0);
						}
					}
				}

				data.otherData.set("power.current", Math.round(power * 1000.0) / 1000.0);
			}
		}
	}
	
	public enum FacingDirection {
		NORTH(0, 22.5),
		NORTH_EAST(22.5, 67.5),
		EAST(67.5, 112.5),
		SOUTH_EAST(112.5, 157.5),
		SOUTH(157.5, 202.5),
		SOUTH_WEST(202.5, 247.5),
		WEST(247.5, 292.5),
		NORTH_WEST(292.5, 337.5);
		
		public double from;
		public double to;
		private FacingDirection(double from, double to) {
			this.from = from;
			this.to = to;
		}
		
		public static FacingDirection getByRotation(double rot) {
			if (0 <= rot && rot < 22.5) {
	            return NORTH;
	        } else if (22.5 <= rot && rot < 67.5) {
	            return NORTH_EAST;
	        } else if (67.5 <= rot && rot < 112.5) {
	            return EAST;
	        } else if (112.5 <= rot && rot < 157.5) {
	            return SOUTH_EAST;
	        } else if (157.5 <= rot && rot < 202.5) {
	            return SOUTH;
	        } else if (202.5 <= rot && rot < 247.5) {
	            return SOUTH_WEST;
	        } else if (247.5 <= rot && rot < 292.5) {
	            return WEST;
	        } else if (292.5 <= rot && rot < 337.5) {
	            return NORTH_WEST;
	        } else if (337.5 <= rot && rot < 360.0) {
	            return NORTH;
	        } else {
	            return null;
	        }
		}
	}

	public enum Interfaces {
		Top,
		Bottom,
		North,
		East,
		South,
		West;
		
		public Interfaces getRelative() {
			if (this == Top) return Bottom;
			if (this == Bottom) return Top;
			if (this == North) return South;
			if (this == South) return North;
			if (this == East) return West;
			if (this == West) return East;
			return null;
		}
		public static ArrayList<Interfaces> getFromString(String input) {
			ArrayList<Interfaces> inters = new ArrayList<PowerableUltils.Interfaces>();
			for (char c : input.toCharArray()) switch (c) {
			case 't': inters.add(Interfaces.Top); break;
			case 'b': inters.add(Interfaces.Bottom); break;
			case 'n': inters.add(Interfaces.North); break;
			case 's': inters.add(Interfaces.South); break;
			case 'w': inters.add(Interfaces.West); break;
			case 'e': inters.add(Interfaces.East); break;
			default: // Do nothing
			}
			return inters;
		}
		public static String composeToString(Interfaces... inters) {
			String out = "";
			for (Interfaces i : inters) switch (i) {
			case Bottom: out += "b"; break;
			case Top: out += "t"; break;
			case North: out += "n"; break;
			case South: out += "s"; break;
			case East: out += "e"; break;
			case West: out += "w"; break;
			default: // Do nothing
			}
			return out;
		}
	}
	
}
