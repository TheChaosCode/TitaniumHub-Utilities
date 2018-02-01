package me.thechaoscode.titaniumhub;

import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import me.thechaoscode.titaniumhub.files.Settings;

public class JoinLeaveMessages implements Listener {

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		
		if(Settings.getConfig().getBoolean("joinmessage.enable") == true) {
			player.sendMessage(ChatColor.translateAlternateColorCodes('&', Settings.getConfig().getString("joinmessage.message")));
			if(!Settings.getConfig().getString("joinmessage.sound").equals("")) {
				try {
					String sound = Settings.getConfig().getString("joinmessage.sound").toUpperCase();
					player.playSound(player.getLocation(), Sound.valueOf(sound), 10, 10);
				} catch (Exception e) {
					//Do Nothing
				}
			}
		}
		
		if(Settings.getConfig().getBoolean("defaultmessages.joinmessage") == true) {
			//Do Nothing
		} else {
			event.setJoinMessage("");
		}
	}
	
	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent event) {
		
		if(Settings.getConfig().getBoolean("defaultmessages.leavemessage") == true) {
			//Do Nothing
		} else {
			event.setQuitMessage("");
		}
	}
}
