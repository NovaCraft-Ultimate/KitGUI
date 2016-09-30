package net.spideynn.bukkit.kitgui.gui;

import net.spideynn.bukkit.kitgui.guilib.items.BackItem;
import net.spideynn.bukkit.kitgui.guilib.items.MenuItem;
import net.spideynn.bukkit.kitgui.guilib.menus.ItemMenu;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class KitsMenu extends ItemMenu {
    public KitsMenu(JavaPlugin plugin, ItemMenu parent) {
        super("Kits", Size.ONE_LINE, plugin, parent);
        this.setItem(0, new ArcherKit());
        this.setItem(8, new BackItem(Material.BARRIER));
    }
}

class ArcherKit extends MenuItem {
    public ArcherKit() {
        super(ChatColor.DARK_GREEN + "Archer", new ItemStack(Material.BOW), "");
    }
}

class AssassinKit extends MenuItem{
    public AssassinKit() {
        super(ChatColor.DARK_GREEN + "Assassin", new ItemStack(Material.STONE_SWORD), "");
    }
}

class AxesKit extends MenuItem{
    public AxesKit() {
        super(ChatColor.DARK_GREEN + "Axes", new ItemStack(Material.WOOD_AXE), "");
    }
}

class CactiKit extends MenuItem {
    public CactiKit() {
        super(ChatColor.DARK_GREEN + "Cacti", new ItemStack(Material.CACTUS), "");
    }
}

class EndermanKit extends MenuItem {
    public EndermanKit() {
        super(ChatColor.DARK_GREEN + "Enderman", new ItemStack(Material.EYE_OF_ENDER), "");
    }
}

class SniperKit extends MenuItem {
    public SniperKit() {
        super(ChatColor.DARK_GREEN + "Sniper", new ItemStack(Material.AIR), "");
        ItemStack bow = new ItemStack(Material.BOW);
        bow.addEnchantment(Enchantment.LUCK, 1);
        setNameAndLore(bow, ChatColor.DARK_GREEN + "Sniper", null);
    }
}

class TankKit extends MenuItem {
    public TankKit() {
        super(ChatColor.DARK_GREEN + "TankKit", new ItemStack(Material.DIAMOND_CHESTPLATE), "");
    }
}
