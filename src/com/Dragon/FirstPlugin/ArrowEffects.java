/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Dragon.FirstPlugin;

import java.util.logging.Logger;
import org.bukkit.EntityEffect;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

/**
 *
 * @author Sebastian
 */
class ArrowEffects implements Listener
{
	private FirstPlugin plugin;
	private Logger log;
	
	public ArrowEffects(FirstPlugin fp, Logger log)
	{
		this.plugin = fp;
		this.log = log;
		this.plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler
	public void onEntityDamageByEntity(EntityDamageByEntityEvent event)
	{
		try
		{
			if (event.getDamager() instanceof Arrow && event.getEntity() instanceof LivingEntity)
			{
				Projectile projectile = (Projectile) event.getDamager();
				System.out.println("ACTIVE onEntityDamageByEntity");
				if (projectile.hasMetadata("Arrow"))
				{
					System.err.println("POISON ENTITY");
					LivingEntity entity = (LivingEntity) event.getEntity();
					entity.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 20*5, 1, true));
					entity.playEffect(EntityEffect.HURT);
				}
			}
		}
		catch(Exception ex)
		{
			log.info(ex.getMessage());
		}
	}
	
	@EventHandler
	public void onProjectileLaunch(ProjectileLaunchEvent e)
	{
		try
		{
			if(e.getEntity() instanceof Arrow)
			{
				Arrow arrow = (Arrow) e.getEntity();
				arrow.setMetadata("Arrow", new FixedMetadataValue(plugin, "PoisonArrow"));
			}
		}
		catch(Exception ex)
		{
			log.info(ex.getMessage());
		}
	}
}
