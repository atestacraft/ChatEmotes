package org.crashmax.ChatEmotes;

import org.bukkit.Bukkit;

public class MELogger {
    public static void sucess(String s) {
        Bukkit.getConsoleSender().sendMessage("Twitch Emotes: [§a§l" + s + "§r]");
    }

    public static void info(String s) {
        Bukkit.getConsoleSender().sendMessage("Twitch Emotes: [" + s + "]");
    }

    public static void warning(String s) {
        Bukkit.getConsoleSender().sendMessage("Twitch Emotes: [§6" + s + "§r]");
    }

    public static void severe(String s) {
        Bukkit.getConsoleSender().sendMessage("Twitch Emotes: [§c§l" + s + "§r]");
    }

    public static void log(String s) {
        Bukkit.getConsoleSender().sendMessage("Twitch Emotes: [" + s + "]");
    }
}
