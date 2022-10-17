package org.crashmax.ChatEmotes;

import org.bukkit.Bukkit;

public class MELogger {
  public static void success(String s) {
    Bukkit.getConsoleSender().sendMessage("ChatEmotes: [§a§l" + s + "§r]");
  }

  public static void info(String s) {
    Bukkit.getConsoleSender().sendMessage("ChatEmotes: [" + s + "]");
  }

  public static void warning(String s) {
    Bukkit.getConsoleSender().sendMessage("ChatEmotes: [§6" + s + "§r]");
  }

  public static void severe(String s) {
    Bukkit.getConsoleSender().sendMessage("ChatEmotes: [§c§l" + s + "§r]");
  }

  public static void log(String s) {
    Bukkit.getConsoleSender().sendMessage("ChatEmotes: [" + s + "]");
  }
}
