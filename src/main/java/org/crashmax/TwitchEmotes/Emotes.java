package org.crashmax.TwitchEmotes;

import org.crashmax.TwitchEmotes.Fanciful.FancyMessage;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.inventivetalent.rpapi.ResourcePackAPI;

import java.util.*;

public class Emotes extends JavaPlugin implements Listener {

    public static Server serv;
    public static PluginDescriptionFile pdf;
    public static PluginManager pm;
    public static Emotes plugin;
    public static MEConfig config;
    static HashMap<List<String>, String> emojis = new HashMap<>();
    static List<String> DeclinedPlayers = new ArrayList<>();
    private static final List<String> installing = new ArrayList<>();
    private final HashMap<String, HashMap<Integer, String>> signPlayers = new HashMap<>();

    public static boolean isInstalling(Player p) {
        return installing.contains(p.getName());
    }

    public static void delInstalling(Player p) {
        installing.remove(p.getName());
    }

    public void onEnable() {
        try {

            Plugin p = Bukkit.getPluginManager().getPlugin("ResourcePackApi");
            boolean RPAPI = p != null && p.isEnabled();
            plugin = this;
            serv = getServer();
            pdf = getDescription();
            pm = serv.getPluginManager();
            AddEmojis();
            config = new MEConfig(plugin);

            if (getBukkitVersion() >= 188) {
                if (RPAPI) {
                    MELogger.warning("ResourcePackApi detected but after version 1.8.8 is not necessary. You can remove securely!");
                }
                pm.registerEvents(new MEListener188(), this);
            } else {
                if (!RPAPI) {
                    MELogger.severe("Not found the dependency ResourcePackAPI required for version < 1.8.8!");
                    Bukkit.getPluginManager().disablePlugin(this);
                    return;
                }
                pm.registerEvents(new MEListenerRPA(), this);
            }

            MELogger.sucess(pdf.getFullName() + " enabled. (" + getBukkitVersion() + ")");

            Bukkit.getPluginManager().registerEvents(this, this);
        } catch (Exception e) {
            e.printStackTrace();
            Bukkit.getPluginManager().disablePlugin(this);
        }
    }

    private void AddEmojis() {
        emojis.put(new ArrayList<>(Collections.singletonList("4Head")), "䰀");
        emojis.put(new ArrayList<>(Collections.singletonList("ANELE")), "䰁");
        emojis.put(new ArrayList<>(Collections.singletonList("BloodTrail")), "䰂");
        emojis.put(new ArrayList<>(Collections.singletonList("BibleThump")), "䰃");
        emojis.put(new ArrayList<>(Collections.singletonList("CoolStoryBob")), "䰄");
        emojis.put(new ArrayList<>(Collections.singletonList("DansGame")), "䰅");
        emojis.put(new ArrayList<>(Collections.singletonList("FailFish")), "䰆");
        emojis.put(new ArrayList<>(Collections.singletonList("Kappa")), "䰇");
        emojis.put(new ArrayList<>(Collections.singletonList("KPride")), "䰈");
        emojis.put(new ArrayList<>(Collections.singletonList("Keepo")), "䰉");
        emojis.put(new ArrayList<>(Collections.singletonList("NotLikeThis")), "䰊");
        emojis.put(new ArrayList<>(Collections.singletonList("PogChamp")), "䰋");
        emojis.put(new ArrayList<>(Collections.singletonList("SeemsGood")), "䰌");
        emojis.put(new ArrayList<>(Collections.singletonList("SMOrc")), "䰍");
        emojis.put(new ArrayList<>(Collections.singletonList("TriHard")), "䰎");
        emojis.put(new ArrayList<>(Collections.singletonList("Kreygasm")), "䰏");

        emojis.put(new ArrayList<>(Collections.singletonList("roflanEbalo")), "䰐");
        emojis.put(new ArrayList<>(Collections.singletonList("ebaloRoflan")), "䰑");
        emojis.put(new ArrayList<>(Collections.singletonList("roflanBuldiga")), "䰒");
        emojis.put(new ArrayList<>(Collections.singletonList("roflanGorit")), "䰓");
        emojis.put(new ArrayList<>(Collections.singletonList("gachiGASM")), "䰔");
        emojis.put(new ArrayList<>(Collections.singletonList("OMEGALUL")), "䰕");
        emojis.put(new ArrayList<>(Collections.singletonList("5Head")), "䰖");
        emojis.put(new ArrayList<>(Collections.singletonList("KEKW")), "䰗");
        emojis.put(new ArrayList<>(Collections.singletonList("KWait")), "䰘");
        emojis.put(new ArrayList<>(Collections.singletonList("monkaS")), "䰙");
        emojis.put(new ArrayList<>(Collections.singletonList("FeelsTastyMan")), "䰚");
        emojis.put(new ArrayList<>(Collections.singletonList("FeelsThinkingMan")), "䰛");
        emojis.put(new ArrayList<>(Collections.singletonList("PepoG")), "䰜");
        emojis.put(new ArrayList<>(Collections.singletonList("monkaOMEGA")), "䰝");
        emojis.put(new ArrayList<>(Collections.singletonList("PepeLaugh")), "䰞");
        emojis.put(new ArrayList<>(Collections.singletonList("pepeY")), "䰟");

        emojis.put(new ArrayList<>(Collections.singletonList("lehaHaha")), "䰠");
        emojis.put(new ArrayList<>(Collections.singletonList("lehaTrail")), "䰡");
        emojis.put(new ArrayList<>(Collections.singletonList("Qq")), "䰢");
        emojis.put(new ArrayList<>(Collections.singletonList("goblinWut")), "䰣");
        emojis.put(new ArrayList<>(Collections.singletonList("goblinKavo")), "䰤");
        emojis.put(new ArrayList<>(Collections.singletonList("goblinOgo")), "䰥");
        emojis.put(new ArrayList<>(Collections.singletonList("pogW")), "䰦");
        emojis.put(new ArrayList<>(Collections.singletonList("D:")), "䰧");
        emojis.put(new ArrayList<>(Collections.singletonList("KEKL")), "䰨");
        emojis.put(new ArrayList<>(Collections.singletonList("KEKYou")), "䰩");
        emojis.put(new ArrayList<>(Collections.singletonList("FeelsRageMan")), "䰪");
        emojis.put(new ArrayList<>(Collections.singletonList("pressF")), "䰫");
        emojis.put(new ArrayList<>(Collections.singletonList("Durka")), "䰬");
        emojis.put(new ArrayList<>(Collections.singletonList("melW")), "䰭");
        emojis.put(new ArrayList<>(Collections.singletonList("melYoba")), "䰮");
        emojis.put(new ArrayList<>(Collections.singletonList("AwkwardFlushed")), "䰯");
		
		emojis.put(new ArrayList<>(Collections.singletonList("CrashMax")), "䰰");
        emojis.put(new ArrayList<>(Collections.singletonList("BarsokuN")), "䰱");
        emojis.put(new ArrayList<>(Collections.singletonList("le_xot")), "䰲");
        emojis.put(new ArrayList<>(Collections.singletonList("BAN")), "䰳");
        emojis.put(new ArrayList<>(Collections.singletonList("NED")), "䰴");
        emojis.put(new ArrayList<>(Collections.singletonList("AYAYA")), "䰵");
        emojis.put(new ArrayList<>(Collections.singletonList("LULW")), "䰶");
        emojis.put(new ArrayList<>(Collections.singletonList("Thonk")), "䰷");
        emojis.put(new ArrayList<>(Collections.singletonList("HAha")), "䰸");
        emojis.put(new ArrayList<>(Collections.singletonList("sadCat")), "䰹");
        emojis.put(new ArrayList<>(Collections.singletonList("MEGALUL")), "䰺");
        emojis.put(new ArrayList<>(Collections.singletonList("Clap")), "䰻");
        emojis.put(new ArrayList<>(Collections.singletonList("PepeHA")), "䰼");
        emojis.put(new ArrayList<>(Collections.singletonList("pogO")), "䰽");
        emojis.put(new ArrayList<>(Collections.singletonList("peepoNoob")), "䰾");
        emojis.put(new ArrayList<>(Collections.singletonList("Loading")), "䰿");
    }

    public void onDisable() {
        config.saveAll();
        MELogger.severe(pdf.getFullName() + " disabled.");
    }

    private int getBukkitVersion() {
        String name = Bukkit.getServer().getClass().getPackage().getName();
        String v = name.substring(name.lastIndexOf('.') + 1) + ".";
        String[] version = v.replace('_', '.').split("\\.");

        int lesserVersion = 0;
        try {
            lesserVersion = Integer.parseInt(version[2]);
        } catch (NumberFormatException ignored) {
        }
        return Integer.parseInt((version[0] + version[1]).substring(1) + lesserVersion);
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String alias, String[] args) {
        List<String> tab = new ArrayList<>();
        for (List<String> emojis : Emotes.emojis.keySet()) {
            for (String emoji : emojis) {
                tab.add(emoji);
            }
        }
        return tab;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String lbl, String[] args) {
        if (args.length == 0) {
            sender.sendMessage(config.getLangString("plugin-tag"));
            if (sender.hasPermission("minemojis.install")) {
                sender.sendMessage(config.getLangString("commands.install").replace("{cmd}", lbl));
            }
            if (sender.hasPermission("minemojis.enable")) {
                sender.sendMessage(config.getLangString("commands.enable").replace("{cmd}", lbl));
            }
            sender.sendMessage(ChatColor.GRAY + "" + ChatColor.ITALIC + Emotes.pdf.getFullName() + ", developed by CrashMax!");
        }
        if (args.length == 1) {
            if ((args[0].equalsIgnoreCase("on") || args[0].equalsIgnoreCase("enable")) && sender.hasPermission("minemojis.enable")) {
                DeclinedPlayers.remove(sender.getName());
                sender.sendMessage(config.getLangString("plugin-tag") + config.getLangString("emojis.enabled"));
            }
            if ((args[0].equalsIgnoreCase("off") || args[0].equalsIgnoreCase("disable")) && sender.hasPermission("minemojis.enable")) {
                if (!DeclinedPlayers.contains(sender.getName())) {
                    DeclinedPlayers.add(sender.getName());
                }
                sender.sendMessage(config.getLangString("plugin-tag") + config.getLangString("emojis.disabled"));
            }
            if (args[0].equalsIgnoreCase("reload") && sender.hasPermission("minemojis.reload")) {
                config.reload();
                sender.sendMessage(ChatColor.AQUA + Emotes.pdf.getFullName() + " reloaded!");
            }
            if (args[0].equalsIgnoreCase("download") && sender instanceof Player && sender.hasPermission("minemojis.download")) {
                Player p = (Player) sender;
                if (getBukkitVersion() >= 188) {
                    p.setResourcePack("https://atestacraft.ru/download/resourcepacks/TwitchEmotes.zip");
                } else {
                    ResourcePackAPI.setResourcepack(p, "https://atestacraft.ru/download/resourcepacks/TwitchEmotes.zip");
                }
                installing.add(p.getName());
            }
            if (args[0].equalsIgnoreCase("install") && sender instanceof Player && sender.hasPermission("minemojis.install")) {
                Player p = (Player) sender;
                if (config.getList("download-help-lines") != null && config.getList("download-help-lines").size() > 0) {
                    for (String line : config.getList("download-help-lines")) {
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', line));
                    }
                }
            }
            if (args[0].equalsIgnoreCase("list") && sender instanceof Player && sender.hasPermission("minemojis.list")) {
                Iterator<List<String>> emojits = Emotes.emojis.keySet().iterator();
                sender.sendMessage(config.getLangString("plugin-tag") + config.getLangString("default-color") + " Emojis:");
                try {
                    Class.forName("com.google.gson.JsonParser");
                    FancyMessage message = new FancyMessage();

                    while (emojits.hasNext()) {
                        List<String> shortcuts = emojits.next();
                        String[] emoji = shortcuts.toArray(new String[0]);
                        StringBuilder shortcut = new StringBuilder();
                        for (String shortc : emoji) {
                            shortcut.append(" ").append(config.getLangString("default-color")).append("|§r ").append(shortc);
                        }
                        shortcut = new StringBuilder(shortcut.substring(4));
                        message.text(config.getLangString("default-color") + "|§r" + Emotes.emojis.get(shortcuts) + config.getLangString("default-color") + "|§r")
                                .tooltip(Emotes.config.getLangString("shortcut") + shortcut)
                                .then(" ");
                    }
                    message.send(sender);
                } catch (ClassNotFoundException e) {
                    StringBuilder send = new StringBuilder();
                    while (emojits.hasNext()) {
                        List<String> shortcuts = emojits.next();
                        String[] emoji = shortcuts.toArray(new String[0]);
                        send.append("|").append(Emotes.emojis.get(shortcuts)).append(" = ").append(Arrays.toString(emoji)).append("|");
                    }
                    sender.sendMessage(send.toString());
                }
            }
            for (List<String> emojis : Emotes.emojis.keySet()) {
                if (emojis.contains(args[0]) && (sender.hasPermission("minemojis." + args[0]) || sender.hasPermission("minemojis.all"))) {
                    sender.sendMessage(config.getLangString("plugin-tag") + " " + args[0] + "§r = " + Emotes.emojis.get(emojis));
                }
            }
        }

        if (args.length >= 3) {
            //emoji setsign <line> <text>
            if (args[0].equalsIgnoreCase("setsign") && sender instanceof Player && sender.hasPermission("minemojis.setsign")) {
                Player p = (Player) sender;
                int line;
                try {
                    line = Integer.parseInt(args[1]);
                    if (line < 1 || line > 4) {
                        sender.sendMessage(config.getLangString("plugin-tag") + config.getLangString("setsign.nolines"));
                        return true;
                    }
                } catch (NumberFormatException e) {
                    sender.sendMessage(config.getLangString("plugin-tag") + config.getLangString("setsign.usage").replace("{cmd}", lbl));
                    return true;
                }
                StringBuilder message = new StringBuilder();
                for (String arg : args) {
                    if (arg.equals(args[0]) || arg.equals(args[1])) {
                        continue;
                    }
                    message.append(arg).append(" ");
                }
                String msg = formatEmoji(p, message.toString().substring(0, message.toString().length() - 1), true);
                HashMap<Integer, String> pmsg = new HashMap<>();
                if (signPlayers.containsKey(p.getName())) {
                    pmsg = signPlayers.get(p.getName());
                }
                pmsg.put(line, msg);
                signPlayers.put(p.getName(), pmsg);
                sender.sendMessage(config.getLangString("plugin-tag") + config.getLangString("setsign.setline-to").replace("{line}", "" + line).replace("{text}", ChatColor.translateAlternateColorCodes('&', msg)));
            }
        }
        return true;
    }

    private String formatEmoji(Player p, String msg, boolean sign) {
        for (List<String> emojis : Emotes.emojis.keySet()) {
            for (String emoji : emojis) {
                if (msg.contains(emoji) && (p.hasPermission("minemojis.emoji.all"))) {
                    String emof = Emotes.emojis.get(emojis);
                    msg = msg.replace(emoji, sign ? "&f" + emof + "&r" : emof);
                }
            }
        }
        return msg;
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGHEST)
    public void onPlayerJoin(PlayerJoinEvent event){
        Player p = event.getPlayer();
        if (config.getBool("config.resourcepack-onplayerjoin")) {
            if (getBukkitVersion() >= 188) {
                p.setResourcePack("https://atestacraft.ru/download/resourcepacks/TwitchEmotes.zip");
            } else {
                ResourcePackAPI.setResourcepack(p, "https://atestacraft.ru/download/resourcepacks/TwitchEmotes.zip");
            }
        }
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGHEST)
    public void onInteractSign(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        Block b = e.getClickedBlock();
        if (signPlayers.containsKey(p.getName())) {
            if (b != null && b.getState() instanceof Sign) {
                Sign s = (Sign) b.getState();
                for (Integer line : signPlayers.get(p.getName()).keySet()) {
                    s.setLine(line - 1, ChatColor.translateAlternateColorCodes('&', signPlayers.get(p.getName()).get(line)));
                }
                s.update();
            } else {
                p.sendMessage(config.getLangString("plugin-tag") + config.getLangString("setsign.no-sign"));
            }
            signPlayers.remove(p.getName());
        }
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGHEST)
    public void onSignChange(SignChangeEvent e) {
        Player p = e.getPlayer();
        if (p.hasPermission("minemojis.placesign") && !Emotes.DeclinedPlayers.contains(p.getName())) {
            for (int i = 0; i < e.getLines().length; i++) {
                e.setLine(i, ChatColor.translateAlternateColorCodes('&', formatEmoji(p, e.getLine(i), true)));
            }
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerChat(AsyncPlayerChatEvent e) {
        Player p = e.getPlayer();
        String chat = e.getMessage();

        if (Emotes.DeclinedPlayers.contains(p.getName())) {
            return;
        }
        e.setMessage(formatEmoji(p, chat, false));
    }
}
