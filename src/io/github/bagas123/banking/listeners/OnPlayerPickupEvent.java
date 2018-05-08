package io.github.bagas123.banking.listeners;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPickupItemEvent;

import com.earth2me.essentials.api.NoLoanPermittedException;
import com.earth2me.essentials.api.UserDoesNotExistException;

import net.ess3.api.Economy;

public class OnPlayerPickupEvent implements Listener {
	
	@SuppressWarnings({ "deprecation" })
	@EventHandler(priority = EventPriority.NORMAL)
	public void OnPickup(PlayerPickupItemEvent event) throws NumberFormatException, NoLoanPermittedException, UserDoesNotExistException {
		
		String name = event.getPlayer().getName();
		
		if (event.getItem().getItemStack().getItemMeta().getLore()
				.contains(ChatColor.translateAlternateColorCodes('&', "&8&3&9&1&4&2&9&4&1&2"))) {
	
			event.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', "You picked up &a&l$" + event.getItem().getItemStack().getItemMeta().getDisplayName()));
			
			Economy.add(name, Long.parseLong(event.getItem().getItemStack().getItemMeta().getDisplayName()));
			event.getItem().remove();
			event.setCancelled(true);
		}
	}
	
}
