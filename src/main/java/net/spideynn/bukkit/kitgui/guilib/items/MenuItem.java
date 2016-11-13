package net.spideynn.bukkit.kitgui.guilib.items;

import net.spideynn.bukkit.kitgui.guilib.events.ItemClickEvent;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.List;

/**
 * An Item inside an {@link net.spideynn.bukkit.kitgui.guilib.menus.ItemMenu}.
 */
public class MenuItem {
    private final String displayName;
    private ItemStack icon;
    private final List<String> lore;

    public MenuItem(String displayName, ItemStack icon, String... lore) {
        this.displayName = displayName;
        this.icon = icon;
        this.lore = Arrays.asList(lore);
    }

    /**
     * Gets the display name of the MenuItem.
     *
     * @return The display name.
     */
    String getDisplayName() {
        return displayName;
    }

    /**
     * Gets the icon of the MenuItem.
     *
     * @return The icon.
     */
    protected ItemStack getIcon() {
        return icon;
    }

    /**
     * Sets the icon of the MenuItem.
     *
     * @return The icon.
     */
    protected void setIcon(ItemStack itemStack) {
        icon = itemStack;
    }

    /**
     * Gets the lore of the MenuItem.
     *
     * @return The lore.
     */
    List<String> getLore() {
        return lore;
    }

    /**
     * Gets the ItemStack to be shown to the player.
     *
     * @param player The player.
     * @return The final icon.
     */
    public ItemStack getFinalIcon(Player player) {
        return setNameAndLore(getIcon().clone(), getDisplayName(), getLore());
    }

    /**
     * Called when the MenuItem is clicked.
     *
     * @param event The {@link net.spideynn.bukkit.kitgui.guilib.events.ItemClickEvent}.
     */
    public void onItemClick(ItemClickEvent event) {
        // Do nothing by default
    }

    /**
     * Sets the display name and lore of an ItemStack.
     *
     * @param itemStack   The ItemStack.
     * @param displayName The display name.
     * @param lore        The lore.
     * @return The ItemStack.
     */
    static ItemStack setNameAndLore(ItemStack itemStack, String displayName, List<String> lore) {
        ItemMeta meta = itemStack.getItemMeta();
        meta.setDisplayName(displayName);
        if (lore != null) meta.setLore(lore);
        itemStack.setItemMeta(meta);
        return itemStack;
    }
}
