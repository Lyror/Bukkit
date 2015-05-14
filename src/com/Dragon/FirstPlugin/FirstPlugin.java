/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Dragon.FirstPlugin;

import java.util.logging.Logger;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class FirstPlugin extends JavaPlugin
{    
	Logger log = getLogger();

	@Override
	public void onEnable() 
	{
		System.out.println("Registriere Move Event");
		registerEvents();
	}
	
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
    {
		try
		{	
			if(cmd.getName().equalsIgnoreCase("poison"))
			{
				Player pl = null;
				Player player = null;
				if(sender instanceof Player)
				{
					log.info(sender.getName());
					pl = Bukkit.getServer().getPlayer(sender.getName());
				}
				else
				{
					if(args.length < 1)
					{
						log.info("Bitte gebe einen Spielernamen an!");
						return true;
					}
					else
					{
						if((player = Bukkit.getServer().getPlayer(args[0])) != null)
						{
							player.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 100, 1));
							pl.sendMessage("§2" + args[0] + "wurde vergiftet!");
							Bukkit.getServer().getPlayer(args[0]).sendMessage("§2Du wurdest vergiftet");
						}
						else
						{
							pl.sendMessage("§4Der Spieler konnte nicht gefunden werden!");
						}
						return true;
					}
				}
				if(args.length > 0)
				{
					if((player = Bukkit.getServer().getPlayer(args[0])) != null)
					{					
						player.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 100, 1));
						pl.sendMessage("§2" + args[0] + "wurde vergiftet!");
						Bukkit.getServer().getPlayer(args[0]).sendMessage("§2Du wurdest vergiftet");
					}
					else
					{
						pl.sendMessage("§4Der Spieler konnte nicht gefunden werden!");
					}
					return true;
				}
				else
				{
					pl.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 100, 1));
					pl.sendMessage("§2Du wurde vergiftet!");
					return true;
				}
			}
			return false;
		}
		catch(Exception ex)
		{
			log.info(ex.getMessage());
			return false;
		}
    }

	private void registerEvents()
	{
		new PlayerMoveEvents(this,log);
		new ArrowEffects(this,log);
	}
}
