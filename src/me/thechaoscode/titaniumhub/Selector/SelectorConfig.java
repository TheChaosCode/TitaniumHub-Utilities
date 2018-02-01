package me.thechaoscode.titaniumhub.Selector;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import me.thechaoscode.titaniumhub.TitaniumHub;


public class SelectorConfig {

	private static FileConfiguration config = null;
	private static File configFile = null;
	public static TitaniumHub main;
	public static void load() {
		config = getConfig();
		config.options().header("############################################################\n# +------------------------------------------------------+ #\n# |             !=Selector Config=!              | #\n# +------------------------------------------------------+ #\n##########################################################");	
		config.options().copyDefaults(true);
		save();
	}
	public static void reload() {
		if (configFile == null) {
			configFile = new File("plugins/TitaniumHub/Selector.yml");
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
		
