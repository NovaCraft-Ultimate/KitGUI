package net.spideynn.bukkit.kitgui.utils;

import me.robin.battlelevels.api.BattleLevelsAPI;
import me.robin.battlelevels.objects.BattlePlayer;
import net.spideynn.bukkit.kitgui.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Utils {

    public static void buyKit(Player player, int cost, Kits kit) {
        BattlePlayer battlePlayer = BattleLevelsAPI.findPlayer(player.getUniqueId());
        System.out.println("Player " + player.getName()  + " trying to buy kit " + kit.getName());
        if (battlePlayer == null) {
            player.sendMessage(ChatColor.RED + "Please report the following error: NullPointerException while purchasing kit - BP = null.");
            player.sendMessage(ChatColor.RED + "You have not been charged.");
            return;
        }
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

    public static void buyItem(BattlePlayer battlePlayer, Player player, ShopItem item) {
        if (battlePlayer.getScore() >= item.getCost()) {
            battlePlayer.setScore(battlePlayer.getScore() - item.getCost());
        } else {
            player.sendMessage(ChatColor.RED + "You do not have enough credits to purchase this item.");
            player.closeInventory();
            return;
        }
        player.sendMessage(ChatColor.GREEN + "You have " + ChatColor.GOLD + battlePlayer.getScore() + ChatColor.GREEN + " credits remaining.");
        player.getInventory().addItem(item.getItem());
        player.closeInventory();
    }
}
