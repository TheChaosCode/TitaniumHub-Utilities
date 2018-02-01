package me.thechaoscode.titaniumhub;


import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import me.thechaoscode.titaniumhub.files.FileManager;
import net.minecraft.server.v1_8_R3.IChatBaseComponent.ChatSerializer;
import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;

public class TitaniumHub extends JavaPlugin implements Listener {

	Server server = getServer();
	PluginManager pm = server.getPluginManager();
	
	public static FileManager config = new FileManager();
	
	public static TitaniumHub instance = null;
	
	@Override
	public void onEnable() {
		SetupConfig();
		registerCommands();
		RegisterListeners();
	}
	@Override
	public void onDisable() {
		config.saveConfig();

	}
	
	
	public void registerCommands(){
		getCommand("hubreload").setExecutor(this);
	}
	
	public void RegisterListeners(){
		getServer().getPluginManager().registerEvents(new JoinLeaveMessages(), this);
		getServer().getPluginManager().registerEvents(new PlayerOptions(), this);
		getServer().getPluginManager().registerEvents(new DoubleJump(), this);
		getServer().getPluginManager().registerEvents(this, this);


	}
	
	public void sendMessage(Player player, String message) {
		player.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
	}
	
	public void sendSound(Player player, Sound sound) {
		player.playSound(player.getLocation(), sound, 10, 10);
	}
	
	 public static void sendAnnouncement(Player p, String msg) {
		   String s = ChatColor.translateAlternateColorCodes('&', msg);
		   IChatBaseComponent icbc = ChatSerializer.a("{\"text\": \""+s+"\"}");
		   PacketPlayOutChat bar = new PacketPlayOutChat(icbc, (byte) 2);
		   ((CraftPlayer) p).getHandle().playerConnection.sendPacket(bar);
		 }
	 
	 
	 public void SetupConfig(){
				TitaniumHub.config = new FileManager();
				TitaniumHub.config.setup(this);
				config.saveConfig();
				config.reloadConfig();
			}
			
	
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(label.equalsIgnoreCase("hubreload")) {
			if(sender instanceof Player) {
				Player player = (Player) sender;
				if(player.hasPermission("bunnyhub.admin")) {
					sendMessage(player, "&a&lReloading Hub configs...");
					try {
						config.reloadConfig();
					} catch (Exception e) {
						sendMessage(player, "&c&lHub reload failed!");
						sendSound(player, Sound.NOTE_BASS);
					}
					sendMessage(player, "&a&lDone!");
					sendSound(player, Sound.NOTE_PLING);
				} else {
					sendMessage(player, "&c&lYou do not have permission to do that!");
					sendSound(player, Sound.NOTE_BASS);
				}
			} else {
				sendMessage((Player) sender, "&a&lReloading Hub configs...");
				try {
					config.reloadConfig();
				} catch (Exception e) {
					sendMessage((Player) sender, "&c&lHub reload failed!");
				}
				sendMessage((Player) sender, "&a&lDone!");
			}
		}
		
		return false;
	}
}
