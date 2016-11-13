package net.spideynn.bukkit.kitgui.gui;

import net.spideynn.bukkit.kitgui.guilib.items.MenuItem;
import net.spideynn.bukkit.kitgui.guilib.menus.ItemMenu;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

class AdminMenu extends ItemMenu {
    public AdminMenu(JavaPlugin plugin) {
        super(ChatColor.DARK_RED + "Admin Menu", Size.ONE_LINE, plugin);
        this.setItem(0, new DisableDropsItem());
        this.setItem(4, new ClearChatItem());
    }

    class DisableDropsItem extends MenuItem {
        DisableDropsItem() {
            super("", new ItemStack(Material.AIR));
        }
    }

    class ClearChatItem extends MenuItem {
        ClearChatItem() {
            super("", new ItemStack(Material.AIR));
        }
    }
}
