package net.spideynn.bukkit.kitgui.listeners;

import net.spideynn.bukkit.kitgui.Main;
import net.spideynn.bukkit.kitgui.gui.MainGUI;
import net.spideynn.bukkit.kitgui.mongodb.Player;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class Listeners implements Listener {
    @EventHandler
    public void onPlayerQuitEvent(PlayerQuitEvent event) {
        try {
            Player player = Main.db.getUserByPlayer(event.getPlayer());
            player.choseKit = Main.choseKit.get(event.getPlayer());
            Main.db.savePlayer(player);
        } catch (NullPointerException e) {
            // ignored
        }
    }

    @EventHandler
    public void onPlayerLoginEvent(PlayerLoginEvent event) {
        Player player = Main.db.getUserByPlayer(event.getPlayer());
        Main.choseKit.put(event.getPlayer(), player.choseKit);
        event.getPlayer().teleport(event.getPlayer().getWorld().getSpawnLocation());
        event.getPlayer().setGameMode(GameMode.ADVENTURE);
        Main.db.savePlayer(player);
    }

    @EventHandler
    public void onPlayerDeathEvent(PlayerDeathEvent event) {
        if (Main.choseKit.get(event.getEntity()))
            Main.choseKit.put(event.getEntity(), false);
    }

    @EventHandler
    public void onAsyncPlayerChatEvent(AsyncPlayerChatEvent event) {
        if(MainGUI.addFriendPrompt.containsKey(event.getPlayer())) {
            if (Main.db.getUserByPlayer(event.getPlayer()).friends.size() >= 16) {
                event.getPlayer().sendMessage(ChatColor.RED + "You have hit the limit of 16 friends. (for now there is a limit)");
            }
            org.bukkit.entity.Player friend = Bukkit.getPlayer(event.getMessage());
            Player playerDb = Main.db.getUserByPlayer(event.getPlayer());
            playerDb.addFriend(friend);
            Main.db.savePlayer(playerDb);
            MainGUI.addFriendPrompt.remove(event.getPlayer());
        }
    }

    @EventHandler
    public void onPlayerHunger(FoodLevelChangeEvent event) {
        event.setCancelled(true);
    }
}
