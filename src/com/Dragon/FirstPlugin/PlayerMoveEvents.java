/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Dragon.FirstPlugin;

import java.util.ArrayList;
import java.util.logging.Logger;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class PlayerMoveEvents implements Listener
{	
	private FirstPlugin plugin;
	
	private Logger logger;
	
	public PlayerMoveEvents(FirstPlugin fp, Logger logger)
	{
		this.plugin = fp;
		this.logger = logger;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
		System.out.println("Registriert Move Event");
	}
	
	ItemStack is = new ItemStack(Material.ARROW);
	
	@EventHandler
	public void onMove(org.bukkit.event.player.PlayerMoveEvent e)
	{
		Player player = e.getPlayer();
		if(player.getLocation().getBlock().getType() == Material.STONE_PLATE)
		{
			if(!player.getInventory().contains(is))
			{
				ItemMeta im = is.getItemMeta();
				im.setDisplayName("§2Vergiftungspfeil");
				ArrayList<String> desc = new ArrayList();
				desc.add("§3Dieser Pfeil wird einen Gegner vergiften.");
				im.setLore(desc);
				
				is.setItemMeta(im);
				
				player.getInventory().addItem(is);
			}
			
			if(!player.getInventory().contains(new ItemStack(Material.BOW)))
				player.getInventory().addItem(new ItemStack(Material.BOW, 1));
		}
	}
}
