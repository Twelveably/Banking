package io.github.bagas123.banking;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import io.github.bagas123.banking.commands.BankCommand;
import io.github.bagas123.banking.listeners.OnInventoryClickEvent;
import io.github.bagas123.banking.listeners.OnPlayerChatEvent;
import io.github.bagas123.banking.listeners.OnPlayerDeathEvent;
import io.github.bagas123.banking.listeners.OnPlayerExitEvent;
import io.github.bagas123.banking.listeners.OnPlayerPickupEvent;

public class Main extends JavaPlugin {

	private File files;
	public static Main instance;

	@Override
	public void onEnable() {
		instance = this;
		saveResource("config.yml", false);
		this.files = new File(getDataFolder(), "config.yml");
		YamlConfiguration.loadConfiguration(this.files);
		
		Bukkit.getServer().getPluginManager().registerEvents(new OnPlayerDeathEvent(), this);
		Bukkit.getServer().getPluginManager().registerEvents(new OnPlayerPickupEvent(), this);
		Bukkit.getServer().getPluginManager().registerEvents(new OnInventoryClickEvent(), this);
		Bukkit.getServer().getPluginManager().registerEvents(new OnPlayerChatEvent(), this);
		Bukkit.getServer().getPluginManager().registerEvents(new OnPlayerExitEvent(), this);
		
		this.getCommand("bank").setExecutor(new BankCommand());
	}

	@Override
	public void onDisable() {

	}

	public String getConfigString(String arg) {
		return getConfig().getString(arg);
	}

	public Integer getConfigInt(String arg) {
		return getConfig().getInt(arg);
	}

}
