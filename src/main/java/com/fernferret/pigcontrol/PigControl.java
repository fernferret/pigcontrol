package com.fernferret.pigcontrol;

import org.bukkit.ChatColor;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

public class PigControl extends JavaPlugin {

    public static final Logger log = Logger.getLogger("Minecraft");
    private static final String logPrefix = "[PigControl]";
    private static final String PIG_CONTROL_CONFIG = "PigControl.yml";

    private FileConfiguration pigControlConfig;

    @Override
    public void onDisable() {
        this.log.info(logPrefix + " - Disabled");
    }

    @Override
    public void onEnable() {
        this.loadConfiguration();
        PluginManager pm = this.getServer().getPluginManager();

        PigControlEntityListener entityListener = new PigControlEntityListener(this);
        pm.registerEvents(entityListener, this);
        this.log.info(logPrefix + " - Version " + this.getDescription().getVersion() + " Enabled");
    }

    private void loadConfiguration() {
        this.getDataFolder().mkdirs();
        this.pigControlConfig = YamlConfiguration.loadConfiguration(new File(this.getDataFolder(), PIG_CONTROL_CONFIG));
        List<String> items = (List<String>) this.pigControlConfig.getStringList("items");
        this.saveConfig();
    }

    public void saveConfig() {
        try {
            this.pigControlConfig.save(new File(this.getDataFolder(), PIG_CONTROL_CONFIG));
        } catch (IOException e) {
            this.log.warning("Could not save config!");
        }
    }
}
