package net.spideynn.bukkit.kitgui.gui;

import me.robin.battlelevels.api.BattleLevelsAPI;
import net.spideynn.bukkit.kitgui.Main;
import net.spideynn.bukkit.kitgui.guilib.events.ItemClickEvent;
import net.spideynn.bukkit.kitgui.guilib.items.BackItem;
import net.spideynn.bukkit.kitgui.guilib.items.MenuItem;
import net.spideynn.bukkit.kitgui.guilib.items.StaticMenuItem;
import net.spideynn.bukkit.kitgui.guilib.items.SubMenuItem;
import net.spideynn.bukkit.kitgui.guilib.menus.ItemMenu;
import net.spideynn.bukkit.kitgui.utils.Kits;
import net.spideynn.bukkit.kitgui.utils.Utils;
import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
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

    public KitShopMenu(JavaPlugin plugin, ItemMenu parent, Player opener) {
        super(ChatColor.BLUE + "Kit Shop", Size.ONE_LINE, plugin, parent);
        net.spideynn.bukkit.kitgui.mongodb.Player user = Main.db.getUserByPlayer(opener);
        this.setItem(0, new KitShopMenuItem("Assassin", new ItemStack(Material.STONE_SWORD), user.kits.contains(Kits.ASSASSIN.getKitNum()), 100, Kits.ASSASSIN));
    }
}


class KitShopMenuItem extends MenuItem {
    final Kits kit;
    final boolean hasBought;
    final int cost;
    final String displayName;

    public KitShopMenuItem(String displayName, ItemStack icon, boolean hasBought, int cost, Kits kit, String... lore) {
        super(displayName, icon, lore);
        this.hasBought = hasBought;
        this.cost = cost;
        this.kit = kit;
        this.displayName = displayName;
    }

    @Override
    public ItemStack getFinalIcon(Player player) {
        ItemStack icon = getIcon();
        ItemMeta iconMeta = icon.getItemMeta();
        if (hasBought) {
            iconMeta.setDisplayName(ChatColor.GREEN + displayName);
            ArrayList<String> list = new ArrayList<>();
            list.add(ChatColor.GREEN + "[PURCHASED]");
            iconMeta.setLore(list);
        } else {
            iconMeta.setDisplayName(ChatColor.RED + displayName);
            ArrayList<String> list = new ArrayList<>();
            list.add(ChatColor.BLUE + "COST: " + ChatColor.GOLD + cost);
            iconMeta.setLore(list);
        }
        return icon;
    }

    @Override
    public void onItemClick(ItemClickEvent event) {
        if (hasBought) {
            event.setWillClose(true);
            return;
        }
        new KitShopConfirmMenu(Main.getInstance(), cost, kit, getIcon()).open(event.getPlayer());
    }
}

class KitShopConfirmMenu extends ItemMenu {

    public KitShopConfirmMenu(JavaPlugin plugin, int cost, Kits kit, ItemStack itemToBuy) {
        super("Confirm Purchase", Size.ONE_LINE, plugin);
        this.setItem(0, new KitShopConfirmItem(kit, cost));
        this.setItem(3, new StaticMenuItem(itemToBuy.getItemMeta().getDisplayName(), itemToBuy));
        this.setItem(7, new KitShopCancelItem());
    }

    class KitShopConfirmItem extends MenuItem {
        final Kits kit;
        final int cost;

        public KitShopConfirmItem(Kits kit, int cost) {
            super(ChatColor.GREEN + "CONFIRM", new ItemStack(Material.WOOL, 1, DyeColor.LIME.getData()));
            this.kit = kit;
            this.cost = cost;
        }

        @Override
        public void onItemClick(ItemClickEvent event) {
            Utils.buyKit(BattleLevelsAPI.findPlayer(event.getPlayer().getUniqueId().toString()), event.getPlayer(), cost, kit);
        }
    }

    class KitShopCancelItem extends MenuItem {

        public KitShopCancelItem() {
            super(ChatColor.RED + "CANCEL", new ItemStack(Material.WOOL, 1, DyeColor.RED.getData()));
        }

        @Override
        public void onItemClick(ItemClickEvent event) {
            event.setWillClose(true);
        }
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