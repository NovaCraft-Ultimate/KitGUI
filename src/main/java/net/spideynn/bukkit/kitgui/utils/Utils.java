package net.spideynn.bukkit.kitgui.utils;

import me.robin.battlelevels.objects.BattlePlayer;
import net.spideynn.bukkit.kitgui.Main;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Utils {

    public static void buyKit(BattlePlayer battlePlayer, Player player, int cost, Kits kit) {
        if (battlePlayer.getScore() >= cost)
            battlePlayer.setScore(battlePlayer.getScore() - cost);
        player.sendMessage(ChatColor.AQUA + "You have " + ChatColor.GOLD + battlePlayer.getScore() + ChatColor.AQUA + " credits remaining.");
        net.spideynn.bukkit.kitgui.mongodb.Player dbPlayer = Main.db.getUserByPlayer(player);
        dbPlayer.kits.add(kit.getKitNum());
        Main.db.savePlayer(dbPlayer);
    }
}
