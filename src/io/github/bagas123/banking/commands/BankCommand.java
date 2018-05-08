package io.github.bagas123.banking.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import io.github.bagas123.banking.gui.BankGUI;

public class BankCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

	Player p = (Player) sender;

	if (sender instanceof Player) {
		
		if (args.length == 0) {
			p.sendMessage("No portable banks for you, mate. Love, ~BagasMC.");
		}
		
		else if(args[0].equals("90saKA9jsh3b088mij2L997bus2")) {
			p.openInventory(BankGUI.bankGUI);
		} 
	}

	return true;
    }

}

