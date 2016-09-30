package net.spideynn.bukkit.kitgui.gui;

import net.md_5.bungee.api.ChatColor;
import net.spideynn.bukkit.kitgui.Main;
import net.spideynn.bukkit.kitgui.guilib.items.SubMenuItem;
import net.spideynn.bukkit.kitgui.guilib.menus.ItemMenu;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class MainGUI extends ItemMenu {
    public MainGUI(JavaPlugin plugin, Player player) {
        super(ChatColor.DARK_GRAY + "KitPvP Menu", Size.THREE_LINE, plugin);
        ItemStack sword
        this.setItem(10, new SubMenuItem(Main.getInstance(), ChatColor.RED + "Kits", new ItemStack(Material.DIAMOND_SWORD), new KitsMenu(Main.getInstance(), this)));
        this.setItem(12, new SubMenuItem(Main.getInstance(), ChatColor.AQUA + "Rank", new ItemStack(Material.PAPER), new RankMenu(Main.getInstance(), this, player)));
        this.setItem(14, new SubMenuItem(Main.getInstance(), ChatColor.GREEN + "Shop", new ItemStack(Material.EMERALD), new ShopMenu(Main.getInstance(), this)));
        this.setItem(16, new SubMenuItem(Main.getInstance(), ChatColor.DARK_PURPLE + "Friends", new ItemStack(Material.ENCHANTED_BOOK), new FriendsMenu(Main.getInstance(), this)));
        this.fillEmptySlots();
    }
}