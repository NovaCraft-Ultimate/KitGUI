package net.spideynn.bukkit.kitgui.gui;

import net.spideynn.bukkit.kitgui.Main;
import net.spideynn.bukkit.kitgui.guilib.events.ItemClickEvent;
import net.spideynn.bukkit.kitgui.guilib.items.BackItem;
import net.spideynn.bukkit.kitgui.guilib.items.MenuItem;
import net.spideynn.bukkit.kitgui.guilib.menus.ItemMenu;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.UUID;

public class FriendsMenu extends ItemMenu {
    public FriendsMenu(JavaPlugin plugin, ItemMenu parent, Player opener) {
        super("Friends", Size.TWO_LINE, plugin, parent);
        this.setItem(8, new BackItem(Material.BARRIER));
        this.setItem(0, new AddFriendItem());
        net.spideynn.bukkit.kitgui.mongodb.Player playerDb = Main.db.getUserByPlayer(opener);
        int counter = 1;
        for (String uuid : playerDb.friends) {
            Player player = Bukkit.getPlayer(UUID.fromString(uuid));
            if (player.isOnline()) {
                ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1);
                skull.setDurability((short) 3);
                SkullMeta skullMeta = (SkullMeta) skull.getItemMeta(); // no arguments needed
                skullMeta.setDisplayName(player.getDisplayName()); // this line isn't needed
                skullMeta.setOwner(player.getName());
                skull.setItemMeta(skullMeta);
                this.setItem(counter, new MenuItem(Bukkit.getPlayer(UUID.fromString(uuid)).getDisplayName(), skull));
                counter++;
            }
        }
    }
}

class AddFriendItem extends MenuItem {

    public AddFriendItem() {
        super("Add a Friend", new ItemStack(Material.BOOK_AND_QUILL));
    }

    @Override
    public void onItemClick(ItemClickEvent event) {
        event.setWillClose(true);
        event.getPlayer().sendMessage(ChatColor.AQUA + "> Enter your friends Minecraft username.");
        event.getPlayer().sendMessage(ChatColor.AQUA + "> It must be an online player.");
        MainGUI.addFriendPrompt.put(event.getPlayer(), true);
    }
}
