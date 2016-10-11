package net.spideynn.bukkit.kitgui;

import me.robin.battlelevels.objects.BattlePlayer;

public class Utils {

    public static void buyKit(BattlePlayer player, int cost) {
        if (player.getScore() >= cost)
            player.setScore(player.getScore() - cost);
    }
}
