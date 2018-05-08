package io.github.bagas123.banking.listeners;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import com.earth2me.essentials.api.NoLoanPermittedException;
import com.earth2me.essentials.api.UserDoesNotExistException;

import io.github.bagas123.banking.Main;
import io.github.bagas123.banking.gui.BankGUI;
import net.ess3.api.Economy;

public class OnPlayerChatEvent implements Listener {

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onPlayerChat(AsyncPlayerChatEvent e)
			throws NumberFormatException, NoLoanPermittedException, UserDoesNotExistException {

		// PLAYER
		Player p = e.getPlayer();

		if (OnInventoryClickEvent.depositer.contains(p.getName())) {
			if (isNumeric(e.getMessage())) {
				if (Economy.getMoney(p.getName()) > Integer.parseInt(e.getMessage()) - 1) {
					p.sendMessage(ChatColor.translateAlternateColorCodes('&',
							"&8&l[&3Bank&8&l]&r You have deposited &a&l$" + e.getMessage() + " &fto your account."));
					Main.instance.getConfig().set("accounts." + p.getName() + ".balance", Main.instance.getConfig().getInt("accounts." + p.getName() + ".balance") + Integer.parseInt(e.getMessage()));
					Main.instance.saveConfig();
					Economy.subtract(p.getName(), Integer.parseInt(e.getMessage()));
					e.setCancelled(true);
					OnInventoryClickEvent.depositer.remove(p.getName());
				} else {
					p.sendMessage(
							ChatColor.translateAlternateColorCodes('&', "&8&l[&3Bank&8&l]&r You don't have enough money!"));
					e.setCancelled(true);
				}
			} else if(e.getMessage().contains("cancel")) {
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8&l[&3Bank&8&l]&r Alright. Come back again!"));
				e.setCancelled(true);
				OnInventoryClickEvent.depositer.remove(p.getName());
			} else {
				p.sendMessage(
						ChatColor.translateAlternateColorCodes('&', "&8&l[&3Bank&8&l]&r Please, numbers only, sir!"));
				e.setCancelled(true);
			}
		}
		
		if (OnInventoryClickEvent.withdrawer.contains(p.getName())) {
			if (isNumeric(e.getMessage())) {
				if (Main.instance.getConfig().getInt("accounts." + p.getName() + ".balance") > Integer.parseInt(e.getMessage()) - 1) {
					p.sendMessage(ChatColor.translateAlternateColorCodes('&',
							"&8&l[&3Bank&8&l]&r You have taken &a&l$" + e.getMessage() + " &ffrom your account."));
					Main.instance.getConfig().set("accounts." + p.getName() + ".balance", Main.instance.getConfig().getInt("accounts." + p.getName() + ".balance") - Integer.parseInt(e.getMessage()));
					Main.instance.saveConfig();
					Economy.add(p.getName(), Integer.parseInt(e.getMessage()));
					e.setCancelled(true);
					OnInventoryClickEvent.withdrawer.remove(p.getName());
				} else {
					p.sendMessage(
							ChatColor.translateAlternateColorCodes('&', "&8&l[&3Bank&8&l]&r You don't have enough money in your bank account!"));
					e.setCancelled(true);
				}
			} else if(e.getMessage().contains("cancel")) {
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8&l[&3Bank&8&l]&r Alright. Come back again!"));
				e.setCancelled(true);
				OnInventoryClickEvent.withdrawer.remove(p.getName());
			} else {
				p.sendMessage(
						ChatColor.translateAlternateColorCodes('&', "&8&l[&3Bank&8&l]&r Please, numbers only, sir!"));
				e.setCancelled(true);
			}
		}

	}

	public boolean isNumeric(String str) {
		return str.matches("-?\\d+(\\.\\d+)?");
	}

}
