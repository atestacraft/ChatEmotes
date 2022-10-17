package org.crashmax.ChatEmotes;

import org.crashmax.ChatEmotes.Fanciful.FancyMessage;
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
  static List<String> DeclinedPlayers = new ArrayList<>();
  static String[][] Unicodes = {
      { "䰀", "䰁", "䰂", "䰃", "䰄", "䰅", "䰆", "䰇", "䰈", "䰉", "䰊", "䰋", "䰌", "䰍", "䰎", "䰏" }, // 1
      { "䰐", "䰑", "䰒", "䰓", "䰔", "䰕", "䰖", "䰗", "䰘", "䰙", "䰚", "䰛", "䰜", "䰝", "䰞", "䰟" }, // 2
      { "䰠", "䰡", "䰢", "䰣", "䰤", "䰥", "䰦", "䰧", "䰨", "䰩", "䰪", "䰫", "䰬", "䰭", "䰮", "䰯" }, // 3
      { "䰰", "䰱", "䰲", "䰳", "䰴", "䰵", "䰶", "䰷", "䰸", "䰹", "䰺", "䰻", "䰼", "䰽", "䰾", "䰿" }, // 4
      { "䱀", "䱁", "䱂", "䱃", "䱄", "䱅", "䱆", "䱇", "䱈", "䱉", "䱊", "䱋", "䱌", "䱍", "䱎", "䱏" }, // 5
      { "䱐", "䱑", "䱒", "䱓", "䱔", "䱕", "䱖", "䱗", "䱘", "䱙", "䱚", "䱛", "䱜", "䱝", "䱞", "䱟" }, // 6
      { "䱠", "䱡", "䱢", "䱣", "䱤", "䱥", "䱦", "䱧", "䱨", "䱩", "䱪", "䱫", "䱬", "䱭", "䱮", "䱯" }, // 7
      { "䱰", "䱱", "䱲", "䱳", "䱴", "䱵", "䱶", "䱷", "䱸", "䱹", "䱺", "䱻", "䱼", "䱽", "䱾", "䱿" }, // 8
      { "䲀", "䲁", "䲂", "䲃", "䲄", "䲅", "䲆", "䲇", "䲈", "䲉", "䲊", "䲋", "䲌", "䲍", "䲎", "䲏" }, // 9
      { "䲐", "䲑", "䲒", "䲓", "䲔", "䲕", "䲖", "䲗", "䲘", "䲙", "䲚", "䲛", "䲜", "䲝", "䲞", "䲟" }, // 10
      { "䲠", "䲡", "䲢", "䲣", "䲤", "䲥", "䲦", "䲧", "䲨", "䲩", "䲪", "䲫", "䲬", "䲭", "䲮", "䲯" }, // 11
      { "䲰", "䲱", "䲲", "䲳", "䲴", "䲵", "䲶", "䲷", "䲸", "䲹", "䲺", "䲻", "䲼", "䲽", "䲾", "䲿" }, // 12
      { "䳀", "䳁", "䳂", "䳃", "䳄", "䳅", "䳆", "䳇", "䳈", "䳉", "䳊", "䳋", "䳌", "䳍", "䳎", "䳏" }, // 13
      { "䳐", "䳑", "䳒", "䳓", "䳔", "䳕", "䳖", "䳗", "䳘", "䳙", "䳚", "䳛", "䳜", "䳝", "䳞", "䳟" }, // 14
      { "䳠", "䳡", "䳢", "䳣", "䳤", "䳥", "䳦", "䳧", "䳨", "䳩", "䳪", "䳫", "䳬", "䳭", "䳮", "䳯" }, // 15
      { "䳰", "䳱", "䳲", "䳳", "䳴", "䳵", "䳶", "䳷", "䳸", "䳹", "䳺", "䳻", "䳼", "䳽", "䳾", "䳿" }, // 16
  };

  public static Server serv;
  public static PluginDescriptionFile pdf;
  public static PluginManager pm;
  public static Emotes plugin;
  public static MEConfig config;
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
      plugin = this;
      serv = getServer();
      pdf = getDescription();
      pm = serv.getPluginManager();
      config = new MEConfig(plugin);
      pm.registerEvents(new MEListener188(), this);
      MELogger.success(pdf.getFullName() + " enabled.");
      Bukkit.getPluginManager().registerEvents(this, this);
    } catch (Exception e) {
      e.printStackTrace();
      Bukkit.getPluginManager().disablePlugin(this);
    }
  }

  public void onDisable() {
    config.saveAll();
    MELogger.severe(pdf.getFullName() + " disabled.");
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
    if (args.length == 1) {
      if (args[0].equalsIgnoreCase("reload") && sender.hasPermission("ChatEmotes.reload")) {
        config.reload();
        sender.sendMessage(ChatColor.AQUA + Emotes.pdf.getFullName() + " reloaded!");
      }
    }

    if (args.length >= 3) {
      // emoji setsign <line> <text>
      if (args[0].equalsIgnoreCase("setsign") && sender instanceof Player
          && sender.hasPermission("chatemotes.setsign")) {
        Player p = (Player) sender;
        int line;
        try {
          line = Integer.parseInt(args[1]);
          if (line < 1 || line > 4) {
            sender.sendMessage(config.getLangString("plugin-tag") + config.getLangString("setsign.nolines"));
            return true;
          }
        } catch (NumberFormatException e) {
          sender.sendMessage(
              config.getLangString("plugin-tag") + config.getLangString("setsign.usage").replace("{cmd}", lbl));
          return true;
        }
        StringBuilder message = new StringBuilder();
        for (String arg : args) {
          if (arg.equals(args[0]) || arg.equals(args[1])) {
            continue;
          }
          message.append(arg).append(" ");
        }
        String msg = formatEmotes(p, message.toString().substring(0, message.toString().length() - 1), true);
        HashMap<Integer, String> pmsg = new HashMap<>();
        if (signPlayers.containsKey(p.getName())) {
          pmsg = signPlayers.get(p.getName());
        }
        pmsg.put(line, msg);
        signPlayers.put(p.getName(), pmsg);
        sender.sendMessage(config.getLangString("plugin-tag") + config.getLangString("setsign.setline-to")
            .replace("{line}", "" + line).replace("{text}", ChatColor.translateAlternateColorCodes('&', msg)));
      }
    }
    return true;
  }

  private String formatEmotes(Player p, String msg, boolean sign) {
    for (List<String> emojis : Emotes.emojis.keySet()) {
      for (String emoji : emojis) {
        if (msg.contains(emoji) && (p.hasPermission("chatemotes.emotes.all"))) {
          String emof = Emotes.emojis.get(emojis);
          msg = msg.replace(emoji, sign ? "&f" + emof + "&r" : emof);
        }
      }
    }
    return msg;
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
    if (p.hasPermission("chatemotes.placesign") && !Emotes.DeclinedPlayers.contains(p.getName())) {
      for (int i = 0; i < e.getLines().length; i++) {
        e.setLine(i, ChatColor.translateAlternateColorCodes('&', formatEmotes(p, e.getLine(i), true)));
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
    e.setMessage(formatEmotes(p, chat, false));
  }
}
