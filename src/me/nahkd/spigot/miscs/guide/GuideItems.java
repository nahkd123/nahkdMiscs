package me.nahkd.spigot.miscs.guide;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import me.nahkd.spigot.miscs.api.ItemBuilder;

public class GuideItems {
	
	public static final String unobtainable_tag = "§u§n§o";
	
	public static int[] border_default = new int[] {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 17, 18, 26, 27, 35, 36, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53};
	
	public static ItemStack border = new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 7).name(unobtainable_tag).create();
	public static ItemStack next_page_avaliable = new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 5).name(unobtainable_tag + "§aNext Page").create();
	public static ItemStack next_page_unavaliable = new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 14).name(unobtainable_tag + "§cNext Page").create();
	public static ItemStack prev_page_avaliable = new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 5).name(unobtainable_tag + "§aPrevious Page").create();
	public static ItemStack prev_page_unavaliable = new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 14).name(unobtainable_tag + "§cPrevious Page").create();
	public static ItemStack back_view = new ItemBuilder(Material.CHEST).name(unobtainable_tag + "§cBack").create();
	
	public static ItemStack guide_book = new ItemBuilder(Material.ENCHANTED_BOOK).name("§6The Ultimate Guide book for Dumbass").addLore("", "§7y... yes? You need some help?").create();
	
}
