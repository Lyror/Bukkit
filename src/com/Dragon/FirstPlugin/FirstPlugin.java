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

public class FirstPlugin extends JavaPlugin
{
    Logger log = getLogger();
    
    @Override
    public void onDisable()
    {
        log.info("FirstPlugin onDisable");
    }
    
    @Override
    public void onEnable()
    {
        log.info("FirstPlugin onEnable");
    }
    
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
    {
        if(cmd.getName().equalsIgnoreCase("heal"))
        {
			Player pl = null;
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
					if(Bukkit.getServer().getPlayer(args[0]) != null)
					{
						Bukkit.getServer().getPlayer(args[0]).setHealth(20);
						pl.sendMessage("§2" + args[0] + "wurde geheilt!");
						Bukkit.getServer().getPlayer(args[0]).sendMessage("§2Du wurdest geheilt");
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
				if(Bukkit.getServer().getPlayer(args[0]) != null)
				{
					Bukkit.getServer().getPlayer(args[0]).setHealth(20);
					pl.sendMessage("§2" + args[0] + "wurde geheilt!");
					Bukkit.getServer().getPlayer(args[0]).sendMessage("§2Du wurdest geheilt");
				}
				else
				{
					pl.sendMessage("§4Der Spieler konnte nicht gefunden werden!");
				}
				return true;
			}
			else
			{
				pl.setHealth(20);
				pl.sendMessage("§2Du wurde geheilt!");
				return true;
			}
        }
		return false;
    }
}
