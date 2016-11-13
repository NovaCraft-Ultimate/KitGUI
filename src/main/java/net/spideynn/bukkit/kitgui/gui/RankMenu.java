package net.spideynn.bukkit.kitgui.gui;

import me.robin.battlelevels.api.BattleLevelsAPI;
import me.robin.battlelevels.objects.BattlePlayer;
import net.spideynn.bukkit.kitgui.guilib.items.BackItem;
import net.spideynn.bukkit.kitgui.guilib.items.StaticMenuItem;
import net.spideynn.bukkit.kitgui.guilib.menus.ItemMenu;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

class RankMenu extends ItemMenu {
    public RankMenu(JavaPlugin plugin, ItemMenu parent, Player opener) {
        super("Rank", Size.ONE_LINE, plugin, parent);
        setOpener(opener);
        this.setItem(8, new BackItem());
        BattlePlayer player = BattleLevelsAPI.findPlayer(this.getOpener().getUniqueId());
        this.setItem(0, new StaticMenuItem(ChatColor.GRAY + "Level:", new ItemStack(Material.EXP_BOTTLE),
                ChatColor.RESET + "" + ChatColor.GOLD + "" + player.getLevel() +
                        "\n" + ChatColor.GOLD + "Progress: " + player.getProgress()+
                        "\n" + player.getBar()));

        this.setItem(2, new StaticMenuItem(ChatColor.YELLOW + "Statistics",
                new ItemStack(Material.DIAMOND_SWORD),
                ChatColor.GOLD + "Kills: " + player.getKills(),
                ChatColor.GOLD +  "Deaths: " + player.getDeaths(),
                ChatColor.GOLD + "KDR: " + player.getKdr()));

        this.setItem(4, new StaticMenuItem(ChatColor.GREEN + "Highest Killstreak: ",
                new ItemStack(Material.SKULL_ITEM),
                ChatColor.GOLD + "" + player.getTopStreak()));
        this.setItem(6, new StaticMenuItem(ChatColor.AQUA + "Credits",
                new ItemStack(Material.PAPER), player.getBar(), ChatColor.GOLD + "" + player.getScore()));
    }
}
