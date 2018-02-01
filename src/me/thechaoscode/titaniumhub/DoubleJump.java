package me.thechaoscode.titaniumhub;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import me.thechaoscode.titaniumhub.files.Settings;


public class DoubleJump implements Listener {

	List<Player> flying = new ArrayList<Player>();

	@EventHandler
    public void setFlyOnJoin(PlayerJoinEvent event)
    {
        Player p = event.getPlayer();
        if(Settings.getConfig().getBoolean("playeroptions.doublejump") == true)
        {
        p.setAllowFlight(true);
        p.setFlying(true);
        }
    }

    @SuppressWarnings("resource")
	@EventHandler
    public void onFlightAttempt(PlayerToggleFlightEvent event) {
    Scanner x = new Scanner(TitaniumHub.config.getSettings().getString("DoubleJump.MotionX"));
    Scanner y = new Scanner(TitaniumHub.config.getSettings().getString("DoubleJump.MotionY"));
        final Player p = event.getPlayer();
        if(p.getGameMode() != GameMode.CREATIVE && !flying.contains(p))
        {
        	p.setFlying(false);
        	flying.add(p);
            p.playSound(p.getLocation(), Sound.IRONGOLEM_THROW, 10, -10);
            event.setCancelled(true);
            Vector v = p.getLocation().getDirection().multiply(x.nextInt()).setY(y.nextInt());
            p.setVelocity(v);
            
            new BukkitRunnable() {
            	@SuppressWarnings("deprecation")
				public void run() {
            		if(p.isOnGround()) {
            			flying.remove(p);
            	        p.setAllowFlight(true);
            	        p.setFlying(false);
            			this.cancel();
            		} else {
            			p.setAllowFlight(false);
            			p.setFlying(false);
            		}
            	}
            }.runTaskTimer(TitaniumHub.instance, 0, 1L);
        }
 
    }
}
