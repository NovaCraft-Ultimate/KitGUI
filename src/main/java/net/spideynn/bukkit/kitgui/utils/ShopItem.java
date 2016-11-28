package net.spideynn.bukkit.kitgui.utils;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionType;

public enum ShopItem {
    SWIFTNESS("Swiftness Potion", new Potion(PotionType.SPEED).toItemStack(1), 8),
    STRENGTH("Strength Potion", new Potion(PotionType.STRENGTH).toItemStack(1), 20),
    INSTANT_HEALTH("Instant Health Potion", new Potion(PotionType.INSTANT_HEAL).toItemStack(1), 15),
    FIRE_RESISTANCE("Fire Resistance Potion", new Potion(PotionType.FIRE_RESISTANCE).toItemStack(1), 12),
    GOLDEN_APPLE(ChatColor.AQUA + "Golden Apple", new ItemStack(Material.GOLDEN_APPLE), 15),
    OP_GOLDEN_APPLE(ChatColor.LIGHT_PURPLE + "Golden Apple", new ItemStack(Material.GOLDEN_APPLE, 1, (short) 1), 40),
    FISHING_ROD("Fishing Rod", new ItemStack(Material.FISHING_ROD), 6),
    MILK("Milk", new ItemStack(Material.MILK_BUCKET), 8),
    XP_BOTTLE("Bottle o' Enchanting", new ItemStack(Material.EXP_BOTTLE), 50),
    DIAMOND_HELM("Diamond Helmet", new ItemStack(Material.DIAMOND_HELMET), 55),
    DIAMOND_CHEST("Diamond Chestplate", new ItemStack(Material.DIAMOND_CHESTPLATE), 75),
    DIAMOND_LEGGINGS("Diamond Leggings", new ItemStack(Material.DIAMOND_LEGGINGS), 65),
    DIAMOND_BOOTS("Diamond Boots", new ItemStack(Material.DIAMOND_BOOTS), 45),
    DIAMOND_SWORD("Diamond Sword", new ItemStack(Material.DIAMOND_SWORD), 60),
    ENDER_PEARLS("Ender Pearl", new ItemStack(Material.ENDER_PEARL, 16), 20),
    FLINT_STEEL("Flint and Steel", new ItemStack(Material.FLINT_AND_STEEL), 15);

    private final String itemName;
    private final ItemStack item;
    private final int cost;

    ShopItem(String itemName, ItemStack item, int cost) {
        this.itemName = itemName;
        this.item = item;
        this.cost = cost;
    }

    public String getItemName() {
        return itemName;
    }

    public int getCost() {
        return cost;
    }

    public ItemStack getItem() {
        return item;
    }
}
