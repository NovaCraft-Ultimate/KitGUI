package net.spideynn.bukkit.kitgui.gui;

import net.spideynn.bukkit.kitgui.guilib.events.ItemClickEvent;
import net.spideynn.bukkit.kitgui.guilib.items.BackItem;
import net.spideynn.bukkit.kitgui.guilib.items.MenuItem;
import net.spideynn.bukkit.kitgui.guilib.menus.ItemMenu;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class ShopMenu extends ItemMenu {
    public ShopMenu(JavaPlugin plugin, ItemMenu parent) {
        super("Shop [BETA]", Size.ONE_LINE, plugin, parent);
        this.setItem(53, new BackItem(Material.BARRIER));
        //TODO: Set up shop using credits from BattleLevels.
    }
}

class ItemShopMenu extends ItemMenu {

    public ItemShopMenu(String name, Size size, JavaPlugin plugin, ItemMenu parent) {
        super(name, size, plugin, parent);
    }
}

class KitShopMenu extends ItemMenu {

    public KitShopMenu(String name, Size size, JavaPlugin plugin, ItemMenu parent) {
        super(name, size, plugin, parent);
    }
}

class ItemShopMenuItem extends MenuItem {

    private final int price;

    public ItemShopMenuItem(String displayName, ItemStack icon, int itemPrice, String... lore) {
        super(displayName, icon, lore);
        price = itemPrice;
    }

    @Override
    public void onItemClick(ItemClickEvent event) {

    }
}