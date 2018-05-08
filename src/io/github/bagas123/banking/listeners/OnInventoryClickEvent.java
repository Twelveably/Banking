package io.github.bagas123.banking.listeners;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import io.github.bagas123.banking.Main;
import io.github.bagas123.banking.gui.BankGUI;

public class OnInventoryClickEvent implements Listener {

	private final static ItemStack deposit = createItem(new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 5),
			ChatColor.translateAlternateColorCodes('&', "&a&lDeposit"), new String[] {});

	private final static ItemStack withdraw = createItem(new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 14),
			ChatColor.translateAlternateColorCodes('&', "&c&lWithdraw"), new String[] {});

	private final static ItemStack check = createItem(new ItemStack(Material.SIGN),
			ChatColor.translateAlternateColorCodes('&', "&6&lBalance"), new String[] {});

	public static Set<String> depositer = new HashSet<String>();
	public static Set<String> withdrawer = new HashSet<String>();

	@EventHandler
	public void onInventoryClick(InventoryClickEvent event) { // inv click

		Player p = (Player) event.getWhoClicked();
		ItemStack clicked = event.getCurrentItem();
		Inventory inventory = event.getInventory();

		if (inventory.equals(BankGUI.bankGUI)) {

			event.setCancelled(true);

			if (clicked.equals(deposit)) {
				depositer.add(p.getName());
				p.sendMessage(ChatColor.translateAlternateColorCodes('&',
						"&8&l[&3Bank&8&l]&r Type your desired amount of money to deposit. Type &7cancel&f to cancel."));
				p.closeInventory();
			} else if (clicked.equals(withdraw)) {
				withdrawer.add(p.getName());
				p.sendMessage(ChatColor.translateAlternateColorCodes('&',
						"&8&l[&3Bank&8&l]&r Type your desired amount of money to withdraw. Type &7cancel&f to cancel."));
				p.closeInventory();
			} else if (clicked.equals(check)) {
				p.sendMessage(ChatColor.translateAlternateColorCodes('&',
						"&8&l[&3Bank&8&l]&r You have &a&l$" + Main.instance.getConfig().getInt("accounts." + p.getName() + ".balance") + " &fin your bank account."));
				p.closeInventory();
			}
		}

	}

	private static ItemStack createItem(ItemStack item, String name, String[] lore) {
		ItemMeta im = item.getItemMeta();
		im.setDisplayName(name);
		im.setLore(Arrays.asList(lore));
		item.setItemMeta(im);
		return item;
	}

}
