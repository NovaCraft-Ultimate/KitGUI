package net.spideynn.bukkit.kitgui.gui;

import net.spideynn.bukkit.kitgui.Main;
import net.spideynn.bukkit.kitgui.guilib.events.ItemClickEvent;
import net.spideynn.bukkit.kitgui.guilib.items.BackItem;
import net.spideynn.bukkit.kitgui.guilib.items.MenuItem;
import net.spideynn.bukkit.kitgui.guilib.items.SubMenuItem;
import net.spideynn.bukkit.kitgui.guilib.menus.ItemMenu;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class ShopMenu extends ItemMenu {
    public ShopMenu(JavaPlugin plugin, ItemMenu parent) {
        super("Shop [BETA]", Size.ONE_LINE, plugin, parent);
        this.setItem(53, new BackItem(Material.BARRIER));
        this.setItem(3, new ItemShopItem());
        this.setItem(6, new KitShopItem());
    }
}

class KitShopItem extends MenuItem {
    public KitShopItem() {
        super(ChatColor.GOLD + "Kit Shop", new ItemStack(Material.DIAMOND_AXE));
    }
}

class ItemShopItem extends SubMenuItem {

    public ItemShopItem() {
        super(Main.getInstance(), ChatColor.GREEN + "Item Shop", new ItemStack(Material.EMERALD), new ShopMenu(Main.getInstance(), new MainGUI(Main.getInstance())));
    }

    @Override
    public void onItemClick(ItemClickEvent event) {

    }
}

class ItemShopMenu extends ItemMenu {

    public ItemShopMenu(String name, Size size, JavaPlugin plugin, ItemMenu parent) {
        super(name, size, plugin, parent);
        //TODO: Set up shop using credits from BattleLevels.
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