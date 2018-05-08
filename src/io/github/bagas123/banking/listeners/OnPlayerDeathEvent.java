package io.github.bagas123.banking.listeners;

import java.util.Arrays;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.earth2me.essentials.api.NoLoanPermittedException;
import com.earth2me.essentials.api.UserDoesNotExistException;

import net.ess3.api.Economy;

public class OnPlayerDeathEvent implements Listener {

	@EventHandler(priority = EventPriority.HIGH)
	public void onEntityDeath(EntityDeathEvent e) throws NoLoanPermittedException, UserDoesNotExistException {

		if (e.getEntity() instanceof Player) {

			Player player = (Player) e.getEntity();
			String name = player.getName();
			Long balance = (long) Economy.getMoney(name);

			if (!player.hasPermission("banking.deathdrop")) {
				if (player.getWorld().getName().contains("WILD")) {

					if (Economy.getMoney(name) > 0) {
						// get their money amount first...
						ItemStack money = createItem(new ItemStack(Material.GOLD_NUGGET), Long.toString(balance),
								new String[] { ChatColor.translateAlternateColorCodes('&', "&8&3&9&1&4&2&9&4&1&2") });

						// then we reset it!
						Economy.resetBalance(name);

						// and drop the item in the exact death location.
						player.getLocation().getWorld().dropItemNaturally(player.getLocation(), money);
					} else {

					}
				} else {

				}
			} else {
				
			}
		}
	}

	private static ItemStack createItem(ItemStack item, String name, String[] lore) {
		ItemMeta im = item.getItemMeta();
		im.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
		im.setLore(Arrays.asList(lore));
		item.setItemMeta(im);
		return item;
	}

}
