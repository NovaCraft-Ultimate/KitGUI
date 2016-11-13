package net.spideynn.bukkit.kitgui.guilib;


import net.spideynn.bukkit.kitgui.guilib.menus.MenuHolder;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.server.PluginDisableEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredListener;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Copyright (C) 2016 Spideynn
 * <p>
 * This software may be modified and distributed under the terms
 * of the MIT license.  See the LICENSE file for details.
 */

/**
 * Passes inventory click events to their menus for handling.
 */
public class MenuListener implements Listener {
    private Plugin plugin = null;
    private static final MenuListener INSTANCE = new MenuListener();

    private MenuListener() {
    }

    /**
     * Gets the {@link net.spideynn.bukkit.kitgui.guilib.MenuListener} instance.
     *
     * @return The {@link net.spideynn.bukkit.kitgui.guilib.MenuListener} instance.
     */
    public static MenuListener getInstance() {
        return INSTANCE;
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getWhoClicked() instanceof Player && event.getInventory().getHolder() instanceof MenuHolder) {
            event.setCancelled(true);
            ((MenuHolder) event.getInventory().getHolder()).getMenu().onInventoryClick(event);
        }
    }

    /**
     * Registers the events of the {@link net.spideynn.bukkit.kitgui.guilib.MenuListener} to a plugin.
     *
     * @param plugin The plugin used to register the events.
     */
    public void register(JavaPlugin plugin) {
        if (!isRegistered(plugin)) {
            plugin.getServer().getPluginManager().registerEvents(INSTANCE, plugin);
            this.plugin = plugin;
        }
    }

    /**
     * Checks if the {@link net.spideynn.bukkit.kitgui.guilib.MenuListener} is registered to a plugin.
     *
     * @param plugin The plugin.
     * @return True if the {@link net.spideynn.bukkit.kitgui.guilib.MenuListener} is registered to the plugin, else false.
     */
    public boolean isRegistered(JavaPlugin plugin) {
        if (plugin.equals(this.plugin)) {
            for (RegisteredListener listener : HandlerList.getRegisteredListeners(plugin)) {
                if (listener.getListener().equals(INSTANCE)) {
                    return true;
                }
            }
        }
        return false;
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onPluginDisable(PluginDisableEvent event) {
        if (event.getPlugin().equals(plugin)) {
            closeOpenMenus();
            plugin = null;
        }
    }

    /**
     * Closes all {@link net.spideynn.bukkit.kitgui.guilib.menus.ItemMenu}s currently open.
     */
    private static void closeOpenMenus() {
        Bukkit.getOnlinePlayers().stream().filter(player -> player.getOpenInventory() != null).forEach(player -> {
            Inventory inventory = player.getOpenInventory().getTopInventory();
            if (inventory.getHolder() instanceof MenuHolder) {
                player.closeInventory();
            }
        });
    }
}