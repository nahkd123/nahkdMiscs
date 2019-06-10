package me.nahkd.spigot.miscs;

import java.io.File;
import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.World;
import org.bukkit.boss.BossBar;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.java.JavaPlugin;

import me.nahkd.spigot.miscs.api.TestClass;
import me.nahkd.spigot.miscs.api.blocks.BlockData;
import me.nahkd.spigot.miscs.api.blocks.WorldBlockData;
import me.nahkd.spigot.miscs.api.bossbar.BossBarManager;
import me.nahkd.spigot.miscs.api.items.CustomItems;
import me.nahkd.spigot.miscs.api.utils.PlayerInventoryUltils;
import me.nahkd.spigot.miscs.api.world.WorldData;
import me.nahkd.spigot.miscs.api.world.WorldsData;
import me.nahkd.spigot.miscs.config.ConfigFiles;
import me.nahkd.spigot.miscs.defaults.DefaultItems;
import me.nahkd.spigot.miscs.defaults.DefaultModifierTypes;
import me.nahkd.spigot.miscs.defaults.DefaultSections;
import me.nahkd.spigot.miscs.defaults.RecipeTypes;
import me.nahkd.spigot.miscs.defaults.events.AdvancedWorkbenchEventListener;
import me.nahkd.spigot.miscs.defaults.events.CobblestoneResourcesEventListener;
import me.nahkd.spigot.miscs.defaults.events.ElectricityEventListener;
import me.nahkd.spigot.miscs.defaults.events.NatureAndGardeningListener;
import me.nahkd.spigot.miscs.defaults.events.TinkeringEventListener;
import me.nahkd.spigot.miscs.events.BlockEventsListener;
import me.nahkd.spigot.miscs.events.GeneralItemListener;
import me.nahkd.spigot.miscs.guide.GuideItems;
import me.nahkd.spigot.miscs.multithreads.AWRecipeThread;
import me.nahkd.spigot.miscs.multithreads.BlockInfoThread;
import me.nahkd.spigot.miscs.multithreads.BlockTickerThread;
import me.nahkd.spigot.miscs.multithreads.OtherStuffsThread;
import me.nahkd.spigot.miscs.multithreads.SafeStopable;
import me.nahkd.spigot.miscs.multithreads.WorldDataBackupThread;

public class nahkdMiscs extends JavaPlugin {
	
	public static nahkdMiscs currentPlugin;
	
	public static File dataFolder;
	public static ArrayList<Thread> runningThreads;
	
	@Override
	public void onLoad() {
		dataFolder = getDataFolder();
		currentPlugin = this;
	}
	
	@Override
	public void onEnable() {
		// Configuration setup
		ConfigFiles.init();
		
		// Items and recipes setup
		RecipeTypes.init();
		DefaultItems.init();
		
		// Guide setup
		DefaultSections.init();
		
		// Data setup
		WorldsData.init();
		
		// Other setup (before everything)
		DefaultModifierTypes.init();
		
		// Listeners setup
		this.getServer().getPluginManager().registerEvents(new BlockEventsListener(), this);
		this.getServer().getPluginManager().registerEvents(new GeneralItemListener(), this);
		
		this.getServer().getPluginManager().registerEvents(new AdvancedWorkbenchEventListener(), this);
		
		this.getServer().getPluginManager().registerEvents(new NatureAndGardeningListener(), this);
		this.getServer().getPluginManager().registerEvents(new CobblestoneResourcesEventListener(), this);
		this.getServer().getPluginManager().registerEvents(new TinkeringEventListener(), this);
		this.getServer().getPluginManager().registerEvents(new ElectricityEventListener(), this);
		
		// Advanced Workbench recipe from Vanilla Workbench
		try {
			ShapedRecipe advanced_workbench = new ShapedRecipe(new NamespacedKey(this, "ADVANCED_WORKBENCH"), DefaultItems.ADVANCED_WORKBENCH.item);
			advanced_workbench.shape("-p-", "pwp", "-p-");
			advanced_workbench.setIngredient('p', Material.WOOD);
			advanced_workbench.setIngredient('w', Material.WORKBENCH);
			this.getServer().addRecipe(advanced_workbench);
		} catch (Exception e) {
			System.out.println("[nahkdMiscs] Unable to create vanilla recipe:");
			e.printStackTrace();
		}
		
		// Thread worker setup
		runningThreads = new ArrayList<Thread>();
		WorldDataBackupThread savingThread = new WorldDataBackupThread();
		savingThread.start();
		runningThreads.add(savingThread);
		AWRecipeThread recipeThread = new AWRecipeThread();
		recipeThread.start();
		runningThreads.add(recipeThread);
		
		// Run after all plugins loaded
		this.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
			@Override
			public void run() {
				System.out.println("[nahkdMiscs] All plugins loaded");
				System.out.println(CustomItems.getMap().size() + " items registered and enabled");
				BlockTickerThread tickerThread = new BlockTickerThread();
				tickerThread.start();
				runningThreads.add(tickerThread);
				OtherStuffsThread other = new OtherStuffsThread();
				other.start();
				runningThreads.add(other);
				BlockInfoThread info = new BlockInfoThread();
				info.start();
				runningThreads.add(info);
			}
		});
	}
	
	@Override
	public void onDisable() {
		WorldsData.saveAll();
		for (Thread thread : runningThreads) {
			System.out.println("[nahkdMiscs] Force stopping thread " + thread.getName());
			thread.stop();
		}
		
		// Remove all bossbars
		for (Player pp : BossBarManager.getMap().keySet()) {
			BossBar b = BossBarManager.getBar(pp);
			b.setVisible(false);
		}
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (command.getName().equalsIgnoreCase("nmts")) {
			if (sender instanceof Player) {
				Player player = (Player) sender;
				if (args.length <= 0) {
					player.sendMessage("§3This is test command and it haven't documented");
				} else {
					if (args[0].equalsIgnoreCase("test0")) {
						TestClass.test0(player);
					}
					if (args[0].equalsIgnoreCase("testtinker")) {
						TestClass.testTinkersTool(player, player.getInventory().getItemInMainHand());
					}
				}
			} else sender.sendMessage("§cPlease run this as a player");
		} else if (command.getName().equalsIgnoreCase("nahkdmiscs")) {
			if (sender instanceof Player) {
				Player player = (Player) sender;
				if (args.length <= 0) {
					player.sendMessage("§3nahkd§bMiscs §aby §bnahkd123");
					player.sendMessage("§6Version 1.0.0");
					player.sendMessage("§e/nahkdmiscs §6Show this useless help thing");
					player.sendMessage("§e/nahkdmiscs recipe §6You get lost? Gottem");
				} else if (args.length <= 1) {
					if (args[0].equalsIgnoreCase("recipe")) {
						player.sendMessage("§e:ok_hand::100:");
						PlayerInventoryUltils.addToInventory(player, GuideItems.guide_book, 1);
					}
				}
			} else sender.sendMessage("§cay no permission for ya, why not /nmadmin???");
		} else if (command.getName().equalsIgnoreCase("nmadmin")) {
			if (args.length <= 0) {
				sender.sendMessage("§3nahkd§bMiscs §aby §bnahkd123");
				sender.sendMessage("§4Administrator commands:");
				sender.sendMessage("§e/nmadmin save §6Save everything");
				sender.sendMessage("§e/nmadmin clearsave §6CLEAR EVERYTHING");
			} else if (args.length <= 1) {
				if (args[0].equalsIgnoreCase("save")) {
					sender.sendMessage("§cPlease wait while the plugin is saving all data...");
					WorldsData.saveAll();
					sender.sendMessage("§aSaved");
				} else if (args[0].equalsIgnoreCase("clearsave")) {
					for (World w : WorldsData.getDataMap().keySet()) {
						WorldData wd = WorldsData.getDataMap().get(w);
						wd.blockData.data = new ArrayList<BlockData>();
						wd.blockData.config = new YamlConfiguration();
					}
					for (File f : ConfigFiles.blockDataFolder.listFiles()) f.delete();
					WorldsData.saveAll();
				}
			}
		}
		return true;
	}
	
}
