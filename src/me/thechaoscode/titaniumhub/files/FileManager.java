package me.thechaoscode.titaniumhub.files;

import org.bukkit.configuration.file.FileConfiguration;

import me.thechaoscode.titaniumhub.TitaniumHub;
import me.thechaoscode.titaniumhub.Selector.SelectorConfig;

public class FileManager {
	
	
	TitaniumHub main;
	public void setup(TitaniumHub p) {
		if (!p.getDataFolder().exists()) {
			p.getDataFolder().mkdir();
		}
		
		Settings.reload();
		Settings.load();
		Settings.save();
		Settings.reload();
		
		SelectorConfig.reload();
		SelectorConfig.load();
		SelectorConfig.save();
		SelectorConfig.reload();
		
		SaveConfig.reload();
		SaveConfig.load();
		SaveConfig.save();
		SaveConfig.reload();
	}
	public FileConfiguration getSettings() {
		return Settings.getConfig();
	}
	
	public FileConfiguration getSelectorConfig() {
		return SelectorConfig.getConfig();
	}
	
	public FileConfiguration getSaveConfig() {
		return SaveConfig.getConfig();
	}
	public void reloadConfig() {
		Settings.reload();
		SelectorConfig.reload();
		SaveConfig.reload();
		Settings.getConfig().options().copyDefaults(false);
		SaveConfig.getConfig().options().copyDefaults(false);
		SelectorConfig.getConfig().options().copyDefaults(false);
	}
	public void saveConfig() {
		Settings.save();
		SelectorConfig.save();
		SaveConfig.save();
	}
	

}
