package net.spideynn.bukkit.kitgui.utils;

import me.robin.battlelevels.objects.BattlePlayer;
import net.spideynn.bukkit.kitgui.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Utils {

    public static void buyKit(BattlePlayer battlePlayer, Player player, int cost, Kits kit) {
        if (battlePlayer.getScore() >= cost) {
            battlePlayer.setScore(battlePlayer.getScore() - cost);
        } else {
            player.sendMessage(ChatColor.RED + "You do not have enough credits to purchase this kit.");
            player.closeInventory();
            return;
        }
        player.sendMessage(ChatColor.GREEN + "You have " + ChatColor.GOLD + battlePlayer.getScore() + ChatColor.GREEN + " credits remaining.");
        net.spideynn.bukkit.kitgui.mongodb.Player dbPlayer = Main.db.getUserByPlayer(player);
        dbPlayer.kits.add(kit.getKitNum());
        Bukkit.getScheduler().runTaskAsynchronously(Main.getInstance(), () -> Main.db.savePlayer(dbPlayer));
    }
}
