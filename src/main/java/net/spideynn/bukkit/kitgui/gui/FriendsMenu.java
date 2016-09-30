package net.spideynn.bukkit.kitgui.gui;

import net.spideynn.bukkit.kitgui.guilib.items.BackItem;
import net.spideynn.bukkit.kitgui.guilib.menus.ItemMenu;
import org.bukkit.Material;
import org.bukkit.plugin.java.JavaPlugin;

public class FriendsMenu extends ItemMenu {
    public FriendsMenu(JavaPlugin plugin, ItemMenu parent) {
        super("Friends", Size.ONE_LINE, plugin, parent);
        this.setItem(8, new BackItem(Material.BARRIER));
    }
}
