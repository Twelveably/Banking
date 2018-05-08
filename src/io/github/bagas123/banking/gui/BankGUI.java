package io.github.bagas123.banking.gui;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class BankGUI {
	
    private static ItemStack createItem(ItemStack item, String name, String[] lore) {
	ItemMeta im = item.getItemMeta();
	im.setDisplayName(name);
	im.setLore(Arrays.asList(lore));
	item.setItemMeta(im);
	return item;
    }
    
    public static Inventory bankGUI = Bukkit.createInventory(null, 27, ChatColor.BOLD + "What would you like to do?");
 
    private final static ItemStack white = createItem(new ItemStack(Material.STAINED_GLASS_PANE), ChatColor.BOLD + "",
	    new String[] {});
    
    private final static ItemStack deposit = createItem(new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 5), ChatColor.translateAlternateColorCodes('&', "&a&lDeposit"),
    	    new String[] {});
    
    private final static ItemStack withdraw = createItem(new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 14),
    		ChatColor.translateAlternateColorCodes('&', "&c&lWithdraw"), new String[] {});
    
    private final static ItemStack check = createItem(new ItemStack(Material.SIGN),
    		ChatColor.translateAlternateColorCodes('&', "&6&lBalance"), new String[] {});
    
    static {

	while (bankGUI.firstEmpty() != -1) {
	    bankGUI.setItem(bankGUI.firstEmpty(), white);
	}
	
	bankGUI.setItem(11, deposit);
	bankGUI.setItem(13, check);
	bankGUI.setItem(15, withdraw);
    }
   
    
    
}
