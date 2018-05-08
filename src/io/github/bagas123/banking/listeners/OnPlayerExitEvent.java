package io.github.bagas123.banking.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class OnPlayerExitEvent implements Listener {

	public void onPlayerQuit(PlayerQuitEvent event) {
		// When a player leaves a server
		Player player = event.getPlayer();
		if (OnInventoryClickEvent.depositer.contains(player.getName())) {
			OnInventoryClickEvent.depositer.remove(player.getName());
		}
		
		if (OnInventoryClickEvent.withdrawer.contains(player.getName())) {
			OnInventoryClickEvent.withdrawer.remove(player.getName());
		}

	}

}
