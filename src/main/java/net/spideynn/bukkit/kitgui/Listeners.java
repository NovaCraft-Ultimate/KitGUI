package net.spideynn.bukkit.kitgui;

import net.spideynn.bukkit.kitgui.gui.MainGUI;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class Listeners implements Listener {
    @EventHandler
    public void onPlayerQuitEvent(PlayerQuitEvent event) {
        Main.choseKit.remove(event.getPlayer());
    }

    @EventHandler
    public void onPlayerJoinEvent(PlayerJoinEvent event) {
        Main.choseKit.put(event.getPlayer(), false);
    }

    @EventHandler
    public void onPlayerDeathEvent(PlayerDeathEvent event) {
        if (Main.choseKit.get(event.getEntity())) Main.choseKit.put(event.getEntity(), false);
    }

    @EventHandler
    public void onAsyncPlayerChatEvent(AsyncPlayerChatEvent event) {
        if(MainGUI.addFriendPrompt.containsKey(event.getPlayer())) {
            // TODO: Add friend here
        }
    }
}
