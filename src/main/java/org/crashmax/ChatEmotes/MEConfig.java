package org.crashmax.ChatEmotes;

import org.bukkit.ChatColor;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class MEConfig {
  private static Emotes plugin;
  private MEYaml config;
  private MEYaml emotes;

  public MEConfig(Emotes pl) {
    plugin = pl;
    config = new MEYaml();
    emotes = new MEYaml();
    loadConfigs();
  }

  private static MEYaml inputLoader(InputStream inp) {
    MEYaml file = new MEYaml();
    try {
      file.load(new InputStreamReader(inp, StandardCharsets.UTF_8));
      inp.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return file;
  }

  public void reload() {
    loadConfigs();
    saveAll();
  }

  private void loadConfigEmotes() {
    File file = new File(plugin.getDataFolder(), "emotes.yml");
    if (!file.exists()) {
      MELogger.info("Creating new emotes.yml ...");
      try {
        plugin.saveResource("emotes.yml", false);
        emotes = inputLoader(plugin.getResource("emotes.yml"));
      } catch (Exception e) {
        MELogger.severe("Error on creating emotes.yml file!");
        e.printStackTrace();
      }
    } else {
      try {
        emotes.load(file);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }

  public void saveAll() {
    try {
      config.save(new File(plugin.getDataFolder(), "config.yml"));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private void loadConfigs() {
    File configs = new File(plugin.getDataFolder(), "config.yml");
    if (!configs.exists()) {
      plugin.saveResource("config.yml", false);
    }

    FileConfiguration temp = new MEYaml();
    try {
      temp.load(configs);
    } catch (Exception e) {
      e.printStackTrace();
    }

    try {
      plugin.getConfig().load(configs);
    } catch (IOException | InvalidConfigurationException e) {
      e.printStackTrace();
    }

    config = inputLoader(plugin.getResource("config.yml"));
    for (String key : config.getKeys(true)) {
      config.set(key, plugin.getConfig().get(key));
    }
    for (String key : config.getKeys(false)) {
      plugin.getConfig().set(key, config.get(key));
    }

    loadConfigEmotes();
    plugin.saveConfig();
  }

  public String getLangString(String key) {
    return ChatColor.translateAlternateColorCodes('&',
        config.getString("strings." + key, "&cNot found string on config.yml for &4" + key + "&r"));

  }
}
