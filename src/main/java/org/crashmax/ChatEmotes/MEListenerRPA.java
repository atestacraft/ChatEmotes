package org.crashmax.ChatEmotes;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.inventivetalent.rpapi.ResourcePackStatusEvent;
import org.inventivetalent.rpapi.Status;

public class MEListenerRPA implements Listener {

    @EventHandler
    public void onResourcePackStatus(ResourcePackStatusEvent e) {
        Player p = e.getPlayer();
        if (Emotes.isInstalling(p)) {
            if (e.getStatus().equals(Status.ACCEPTED)) {
                e.getPlayer().sendMessage(Emotes.config.getLangString("plugin-tag") + Emotes.config.getLangString("installing"));
                Emotes.DeclinedPlayers.remove(e.getPlayer().getName());
            }
            if (e.getStatus().equals(Status.SUCCESSFULLY_LOADED)) {
                e.getPlayer().sendMessage(Emotes.config.getLangString("plugin-tag") + Emotes.config.getLangString("installed"));
                Emotes.DeclinedPlayers.remove(e.getPlayer().getName());
                Emotes.delInstalling(p);
            }
            if (e.getStatus().equals(Status.DECLINED)) {
                e.getPlayer().sendMessage(Emotes.config.getLangString("plugin-tag") + Emotes.config.getLangString("disabling"));
                Emotes.DeclinedPlayers.add(e.getPlayer().getName());
                Emotes.delInstalling(p);
            }
            if (e.getStatus().equals(Status.FAILED_DOWNLOAD)) {
                e.getPlayer().sendMessage(Emotes.config.getLangString("plugin-tag") + Emotes.config.getLangString("error"));
                Emotes.DeclinedPlayers.add(e.getPlayer().getName());
                Emotes.delInstalling(p);
            }
        }
    }
}
