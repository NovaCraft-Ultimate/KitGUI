package net.spideynn.bukkit.kitgui.gui;

import net.spideynn.bukkit.kitgui.Main;
import net.spideynn.bukkit.kitgui.guilib.events.ItemClickEvent;
import net.spideynn.bukkit.kitgui.guilib.items.BackItem;
import net.spideynn.bukkit.kitgui.guilib.items.MenuItem;
import net.spideynn.bukkit.kitgui.guilib.items.SubMenuItem;
import net.spideynn.bukkit.kitgui.guilib.menus.ItemMenu;
import net.spideynn.bukkit.kitgui.utils.Kits;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

public class ShopMenu extends ItemMenu {
    public ShopMenu(JavaPlugin plugin, ItemMenu parent, Player opener) {
        super("Shop [BETA]", Size.ONE_LINE, plugin, parent);
        setOpener(opener);
        this.setItem(8, new BackItem(Material.BARRIER));
        this.setItem(3, new ItemShopItem());
        this.setItem(6, new KitShopItem(opener));
    }
}

class KitShopItem extends MenuItem {
    public KitShopItem(Player opener) {
        super(ChatColor.GOLD + "Kit Shop", new ItemStack(Material.DIAMOND_AXE));
    }
}

class ItemShopItem extends MenuItem {

    //TODO: Fix loop thing on back menu system.

    public ItemShopItem() {
        super(ChatColor.GREEN + "Item Shop", new ItemStack(Material.EMERALD));
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

    public KitShopMenu(String name, Size size, JavaPlugin plugin, ItemMenu parent, Player opener) {
        super(name, size, plugin, parent);
        net.spideynn.bukkit.kitgui.mongodb.Player user = Main.db.getUserByPlayer(opener);
        //for (Kits kit : Kits.getKitsbyArray(Main.db.getUserByPlayer(opener).kits.toArray(new Integer[user.kits.size()])))
        this.setItem(0, new KitShopMenuItem("Assassin", new ItemStack(Material.STONE_SWORD), user.kits.contains(Kits.ASSASSIN.getKitNum()), 100));
    }
}

class KitShopMenuItem extends MenuItem {
    boolean hasBought;
    int cost;

    public KitShopMenuItem(String displayName, ItemStack icon, boolean hasBought, int cost, String... lore) {
        super(displayName, icon, lore);
    }

    @Override
    public ItemStack getFinalIcon(Player player) {
        ItemStack icon = getIcon();
        ItemMeta iconMeta = icon.getItemMeta();
        if (hasBought) {
            iconMeta.setDisplayName(ChatColor.GREEN + getDisplayName());
            ArrayList<String> list = new ArrayList<>();
            list.add(ChatColor.GREEN + "[PURCHASED]");
            iconMeta.setLore(list);
        } else {
            iconMeta.setDisplayName(ChatColor.RED + getDisplayName());
            ArrayList<String> list = new ArrayList<>();
            list.add(ChatColor.RED + "[NOT PURCHASED]");
            iconMeta.setLore(list);
        }
        return icon;
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