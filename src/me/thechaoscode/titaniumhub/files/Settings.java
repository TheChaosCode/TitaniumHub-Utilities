package me.thechaoscode.titaniumhub.files;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import me.thechaoscode.titaniumhub.TitaniumHub;

public class Settings {
	

	private static FileConfiguration config = null;
	private static File configFile = null;
	public static TitaniumHub main;
	public static void load() {
		config = getConfig();
		config.options().header("############################################################\n# +------------------------------------------------------+ #\n# |             !=Settings Config=!              | #\n# +------------------------------------------------------+ #\n##########################################################");
		config.addDefault("DoubleJump.MotionY", "");
		config.addDefault("DoubleJump.MotionX", "");
		config.addDefault("playeroptions.doublejump", false);
		config.options().pathSeparator();
		config.addDefault("joinmessage.message", "");
		config.addDefault("joinmessage.sound", "");
		config.addDefault("joinmessage.enable", false);
		config.addDefault("defaultmessages.joinmessage", false);
		config.addDefault("defaultmessages.leavemessage", false);
		config.options().copyDefaults(true);
		save();
	}
	public static void reload() {
		if (configFile == null) {
			configFile = new File("plugins/TitaniumHub/Settings.yml");
		}
		
		config = YamlConfiguration.loadConfiguration(configFile);

	}

	public static FileConfiguration getConfig() {
		if (config == null) {
			reload();
		}
		return config;
	}

	public static void save() {
		if ((config == null) || (configFile == null)) {
			return;
		}

		try {
			config.save(configFile);
		} catch (IOException ex) {
			Logger.getLogger(JavaPlugin.class.getName()).log(Level.SEVERE, "Could not save configFile to " + configFile, ex);

		}

	}
	}
		

