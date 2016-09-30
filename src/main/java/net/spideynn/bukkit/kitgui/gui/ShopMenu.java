package net.spideynn.bukkit.kitgui.gui;

import net.spideynn.bukkit.kitgui.guilib.items.BackItem;
import net.spideynn.bukkit.kitgui.guilib.menus.ItemMenu;
import org.bukkit.Material;
import org.bukkit.plugin.java.JavaPlugin;

public class ShopMenu extends ItemMenu {
    public ShopMenu(JavaPlugin plugin, ItemMenu parent) {
        super("Shop", Size.SIX_LINE, plugin, parent);
        this.setItem(53, new BackItem(Material.BARRIER));
    }
}
