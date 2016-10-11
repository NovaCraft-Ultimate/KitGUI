package net.spideynn.bukkit.kitgui.gui;

import net.spideynn.bukkit.kitgui.guilib.events.ItemClickEvent;
import net.spideynn.bukkit.kitgui.guilib.items.BackItem;
import net.spideynn.bukkit.kitgui.guilib.items.MenuItem;
import net.spideynn.bukkit.kitgui.guilib.menus.ItemMenu;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class FriendsMenu extends ItemMenu {
    public FriendsMenu(JavaPlugin plugin, ItemMenu parent) {
        super("Friends", Size.ONE_LINE, plugin, parent);
        this.setItem(8, new BackItem(Material.BARRIER));
        this.setItem(0, new AddFriendItem());
    }
}

class AddFriendItem extends MenuItem {

    public AddFriendItem() {
        super("Add a Friend", new ItemStack(Material.BOOK_AND_QUILL));
    }

    @Override
    public void onItemClick(ItemClickEvent event) {
        event.setWillClose(true);
        event.getPlayer().sendMessage(ChatColor.AQUA + "Enter your friends Minecraft username.");
        event.getPlayer().sendMessage(ChatColor.AQUA + "Must be an online player.");
        MainGUI.addFriendPrompt.put(event.getPlayer(), true);
    }
}
