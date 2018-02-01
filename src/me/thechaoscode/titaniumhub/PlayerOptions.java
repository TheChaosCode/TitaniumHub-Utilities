package me.thechaoscode.titaniumhub;

import org.bukkit.GameMode;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import me.thechaoscode.titaniumhub.files.SaveConfig;

public class PlayerOptions implements Listener {

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		final Player player = event.getPlayer();
		
		if(TitaniumHub.config.getSaveConfig().getString("playeroptions.gamemode").equals("")) {
			//Do Nothing
		} else {
			String gamemode = "";
			if(SaveConfig.getConfig().getString("playeroptions.gamemode").toLowerCase().contains("survival")) {
				gamemode = "SURVIVAL";
			}
			if(SaveConfig.getConfig().getString("playeroptions.gamemode").toLowerCase().contains("adventure")) {
				gamemode = "ADVENTURE";
			}
			if(SaveConfig.getConfig().getString("playeroptions.gamemode").toLowerCase().contains("creative")) {
				gamemode = "CREATIVE";
			}
			player.setGameMode(GameMode.valueOf(gamemode));
		}
	}
	
	@EventHandler
	public void onPlayerHurt(EntityDamageEvent event) {
		Entity e = event.getEntity();
		EntityType type = e.getType();
		if(type == EntityType.PLAYER) {
			if(SaveConfig.getConfig().getBoolean("playeroptions.health") == true) {
				//Do Nothing
			} else {
				event.setCancelled(true);
			}
		}
	}
	
	@EventHandler
	public void onFoodChange(FoodLevelChangeEvent event) {
		if(event.getEntityType() == EntityType.PLAYER) {
			if(SaveConfig.getConfig().getBoolean("playeroptions.hunger") == true) {
				//Do Nothing
			} else {
				event.setFoodLevel(event.getFoodLevel() + 1);
				event.setCancelled(true);
			}
		}
	}
}
