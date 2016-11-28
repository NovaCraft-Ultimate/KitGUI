package net.spideynn.bukkit.kitgui.gui;

import net.spideynn.bukkit.kitgui.Main;
import net.spideynn.bukkit.kitgui.guilib.events.ItemClickEvent;
import net.spideynn.bukkit.kitgui.guilib.items.BackItem;
import net.spideynn.bukkit.kitgui.guilib.items.MenuItem;
import net.spideynn.bukkit.kitgui.guilib.menus.ItemMenu;
import net.spideynn.bukkit.kitgui.utils.Kits;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

class KitsMenu extends ItemMenu {
    KitsMenu(JavaPlugin plugin, ItemMenu parent, Player opener) {
        super("Kits", Size.ONE_LINE, plugin, parent);
        setOpener(opener);
        this.setItem(0, new ArcherKit());
        this.setItem(1, new AssassinKit());
        this.setItem(2, new AxesKit());
        this.setItem(3, new CactiKit());
        this.setItem(4, new EndermanKit());
        this.setItem(5, new SniperKit());
        this.setItem(6, new TankKit());
        this.setItem(8, new BackItem());
    }
}

class ArcherKit extends MenuItem {
    ArcherKit() {
        super(ChatColor.DARK_GREEN + "Archer", new ItemStack(Material.BOW), "");
    }

    @Override
    public void onItemClick(ItemClickEvent event) {
        if (Main.choseKit.get(event.getPlayer())) {
            event.getPlayer().sendMessage(ChatColor.RED + "You already chose a kit! You can choose another one when you die.");
            event.setWillClose(true);

        } else {
            event.setWillClose(true);
            Player p = event.getPlayer();
            p.sendMessage(ChatColor.DARK_GREEN + "You have selected the kit Archer.");
            for (PotionEffect pe : p.getActivePotionEffects()) {
                p.removePotionEffect(pe.getType());
            }

            p.getInventory().clear();
            p.getInventory().setChestplate(null);
            p.getInventory().setHelmet(null);
            p.getInventory().setLeggings(null);
            p.getInventory().setBoots(null);

            ItemStack chestPlate = new ItemStack(Material.DIAMOND_CHESTPLATE);
            ItemMeta chestPlateMeta = chestPlate.getItemMeta();
            chestPlateMeta.setDisplayName(ChatColor.DARK_GREEN + "Diamond Chestplate");
            chestPlate.setItemMeta(chestPlateMeta);
            ItemStack weapon = new ItemStack(Material.WOOD_AXE);
            ItemMeta weaponMeta = weapon.getItemMeta();
            weaponMeta.setDisplayName(ChatColor.DARK_GREEN + "Wood Axe");
            weaponMeta.addEnchant(Enchantment.DURABILITY, 3, true);
            weapon.setItemMeta(weaponMeta);
            ItemStack bow = new ItemStack(Material.BOW);
            ItemMeta bowMeta = bow.getItemMeta();
            bowMeta.setDisplayName(ChatColor.DARK_GREEN + "Bow");
            bowMeta.addEnchant(Enchantment.DURABILITY, 3, true);
            bowMeta.addEnchant(Enchantment.ARROW_INFINITE, 1, true);
            bowMeta.addEnchant(Enchantment.ARROW_DAMAGE, 2, true);
            bow.setItemMeta(bowMeta);
            ItemStack arrow = new ItemStack(Material.ARROW);
            ItemMeta arrowMeta = arrow.getItemMeta();
            arrowMeta.setDisplayName(ChatColor.DARK_GREEN + "Arrow");
            arrow.setItemMeta(arrowMeta);

            p.getInventory().setItem(1, weapon);
            p.getInventory().setItem(0, bow);
            p.getInventory().setItem(8, arrow);
            p.getInventory().setChestplate(chestPlate);
            Main.choseKit.put(p, true);
        }
    }
}

class AssassinKit extends MenuItem{
    AssassinKit() {
        super(ChatColor.DARK_GREEN + "Assassin", new ItemStack(Material.STONE_SWORD), "");
    }

    @Override
    public ItemStack getFinalIcon(Player player) {
        ItemStack finalIcon = super.getFinalIcon(player);
        if (!Main.db.doesPlayerHaveKit(player, Kits.ASSASSIN)) {
            ItemMeta meta = finalIcon.getItemMeta();
            meta.setDisplayName(ChatColor.DARK_GREEN + "Assassin " + ChatColor.RED + "(LOCKED)");
            finalIcon.setItemMeta(meta);
        }
        return finalIcon;
    }

    @Override
    public void onItemClick(ItemClickEvent event) {
        if (Main.choseKit.get(event.getPlayer())) {
            event.getPlayer().sendMessage(ChatColor.RED + "You already chose a kit! You can choose another one when you die.");
            event.setWillClose(true);
        } if (!Main.db.doesPlayerHaveKit(event.getPlayer(), Kits.ASSASSIN)) {
            event.setWillClose(true);
            event.getPlayer().sendMessage(ChatColor.RED + "You must purchase this kit in the shop before you can use it.");
        } else {
            event.setWillClose(true);
            Player p = event.getPlayer();
            Main.choseKit.put(p, true);

            p.sendMessage(ChatColor.DARK_GREEN + "You have selected the kit Assassin.");

            for (PotionEffect pe : p.getActivePotionEffects()) {
                p.removePotionEffect(pe.getType());
            }

            p.getInventory().clear();
            p.getInventory().setChestplate(null);
            p.getInventory().setHelmet(null);
            p.getInventory().setLeggings(null);
            p.getInventory().setBoots(null);
            ItemStack chestplate = new ItemStack(Material.CHAINMAIL_CHESTPLATE);
            ItemMeta chestplateMeta = chestplate.getItemMeta();
            chestplateMeta.setDisplayName(ChatColor.DARK_GREEN + "Chain Chestplate");
            chestplate.setItemMeta(chestplateMeta);
            ItemStack sword = new ItemStack(Material.DIAMOND_SWORD);
            ItemMeta swordMeta = sword.getItemMeta();
            swordMeta.setDisplayName(ChatColor.DARK_GREEN + "Diamond Sword");
            sword.setItemMeta(swordMeta);
            ItemStack goldenApple = new ItemStack(Material.GOLDEN_APPLE);
            ItemMeta goldenAppleMeta = goldenApple.getItemMeta();
            goldenAppleMeta.setDisplayName(ChatColor.DARK_GREEN + "Golden Apple");
            goldenApple.setItemMeta(goldenAppleMeta);
            p.getInventory().setItem(0, sword);
            p.getInventory().setItem(1, goldenApple);
            p.getInventory().setChestplate(chestplate);
            p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 999999, 1));
            p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 999999, 1));
        }
    }
}

class AxesKit extends MenuItem{
    AxesKit() {
        super(ChatColor.DARK_GREEN + "Axes", new ItemStack(Material.WOOD_AXE), "");
    }

    @Override
    public ItemStack getFinalIcon(Player player) {
        ItemStack finalIcon = super.getFinalIcon(player);
        if (!Main.db.doesPlayerHaveKit(player, Kits.AXES)) {
            ItemMeta meta = finalIcon.getItemMeta();
            meta.setDisplayName(ChatColor.DARK_GREEN + "Axes " + ChatColor.RED + "(LOCKED)");
            finalIcon.setItemMeta(meta);
        }
        return finalIcon;
    }

    @Override
    public void onItemClick(ItemClickEvent event) {
        if (Main.choseKit.get(event.getPlayer())) {
            event.getPlayer().sendMessage(ChatColor.RED + "You already chose a kit! You can choose another one when you die.");
            event.setWillClose(true);
        } if (!Main.db.doesPlayerHaveKit(event.getPlayer(), Kits.AXES)) {
            event.setWillClose(true);
            event.getPlayer().sendMessage(ChatColor.RED + "You must purchase this kit in the shop before you can use it.");
        } else {
            event.setWillClose(true);
            Player p = event.getPlayer();
            Main.choseKit.put(p, true);

            p.sendMessage(ChatColor.DARK_GREEN + "You have selected the kit Axes.");

            for (PotionEffect i2 : p.getActivePotionEffects()) {
                p.removePotionEffect(i2.getType());
            }

            p.getInventory().clear();
            p.getInventory().setChestplate(null);
            p.getInventory().setHelmet(null);
            p.getInventory().setLeggings(null);
            p.getInventory().setBoots(null);
            ItemStack i21 = new ItemStack(Material.DIAMOND_CHESTPLATE);
            ItemMeta m21 = i21.getItemMeta();
            m21.setDisplayName(ChatColor.DARK_GREEN + "Diamond Chestplate");
            i21.setItemMeta(m21);
            ItemStack i5 = new ItemStack(Material.WOOD_AXE);
            ItemMeta m5 = i5.getItemMeta();
            m5.setDisplayName(ChatColor.DARK_GREEN + "Wood Axe");
            m5.addEnchant(Enchantment.DURABILITY, 3, true);
            m5.addEnchant(Enchantment.DAMAGE_ALL, 4, true);
            i5.setItemMeta(m5);
            ItemStack i6 = new ItemStack(Material.GOLDEN_APPLE);
            ItemMeta m6 = i6.getItemMeta();
            m6.setDisplayName(ChatColor.DARK_GREEN + "Golden Apple");
            i6.setItemMeta(m6);
            p.getInventory().setItem(0, i5);
            p.getInventory().setItem(1, i6);
            p.getInventory().setChestplate(i21);
        }
    }
}

class CactiKit extends MenuItem {
    CactiKit() {
        super(ChatColor.DARK_GREEN + "Cacti", new ItemStack(Material.CACTUS), "");
    }

    @Override
    public ItemStack getFinalIcon(Player player) {
        ItemStack finalIcon = super.getFinalIcon(player);
        if (!Main.db.doesPlayerHaveKit(player, Kits.CACTI)) {
            ItemMeta meta = finalIcon.getItemMeta();
            meta.setDisplayName(ChatColor.DARK_GREEN + "Cacti " + ChatColor.RED + "(LOCKED)");
            finalIcon.setItemMeta(meta);
        }
        return finalIcon;
    }

    @Override
    public void onItemClick(ItemClickEvent event) {
        if (Main.choseKit.get(event.getPlayer())) {
            event.getPlayer().sendMessage(ChatColor.RED + "You already chose a kit! You can choose another one when you die.");
            event.setWillClose(true);
        } if (!Main.db.doesPlayerHaveKit(event.getPlayer(), Kits.CACTI)) {
            event.setWillClose(true);
            event.getPlayer().sendMessage(ChatColor.RED + "You must purchase this kit in the shop before you can use it.");
        } else {
            event.setWillClose(true);
            Player p = event.getPlayer();
            Main.choseKit.put(p, true);

            p.sendMessage(ChatColor.DARK_GREEN + "You have selected the kit Cacti.");

            for (PotionEffect i1 : p.getActivePotionEffects()) {
                p.removePotionEffect(i1.getType());
            }

            p.getInventory().clear();
            p.getInventory().setChestplate(null);
            p.getInventory().setHelmet(null);
            p.getInventory().setLeggings(null);
            p.getInventory().setBoots(null);
            ItemStack i11 = new ItemStack(Material.LEATHER_HELMET);
            ItemMeta m11 = i11.getItemMeta();
            m11.setDisplayName(ChatColor.DARK_GREEN + "Leather Helmet");
            i11.setItemMeta(m11);
            i11.addUnsafeEnchantment(Enchantment.THORNS, 8);
            ItemStack i2 = new ItemStack(Material.CHAINMAIL_CHESTPLATE);
            ItemMeta m2 = i2.getItemMeta();
            m2.setDisplayName(ChatColor.DARK_GREEN + "Leather Chestplate");
            i2.setItemMeta(m2);
            ItemStack i3 = new ItemStack(Material.LEATHER_LEGGINGS);
            ItemMeta m3 = i3.getItemMeta();
            i2.addUnsafeEnchantment(Enchantment.THORNS, 10);
            m3.setDisplayName(ChatColor.DARK_GREEN + "Leather Leggings");
            i3.setItemMeta(m3);
            i3.addUnsafeEnchantment(Enchantment.THORNS, 8);
            ItemStack i4 = new ItemStack(Material.LEATHER_BOOTS);
            ItemMeta m4 = i4.getItemMeta();
            i4.addUnsafeEnchantment(Enchantment.THORNS, 10);
            m4.setDisplayName(ChatColor.DARK_GREEN + "Leather Boots");
            i4.setItemMeta(m4);
            ItemStack i5 = new ItemStack(Material.STONE_SWORD);
            ItemMeta m5 = i5.getItemMeta();
            m5.setDisplayName(ChatColor.DARK_GREEN + "Stone Sword");
            i5.setItemMeta(m5);
            p.getInventory().setItem(0, i5);
            p.getInventory().setHelmet(i11);
            p.getInventory().setChestplate(i2);
            p.getInventory().setLeggings(i3);
            p.getInventory().setBoots(i4);
            Main.choseKit.put(p, true);
        }
    }
}

class EndermanKit extends MenuItem {
    EndermanKit() {
        super(ChatColor.DARK_GREEN + "Enderman", new ItemStack(Material.EYE_OF_ENDER), "");
    }

    @Override
    public ItemStack getFinalIcon(Player player) {
        ItemStack finalIcon = super.getFinalIcon(player);
        if (!Main.db.doesPlayerHaveKit(player, Kits.ENDERMAN)) {
            ItemMeta meta = finalIcon.getItemMeta();
            meta.setDisplayName(ChatColor.DARK_GREEN + "Enderman " + ChatColor.RED + "(LOCKED)");
            finalIcon.setItemMeta(meta);
        }
        return finalIcon;
    }

    @Override
    public void onItemClick(ItemClickEvent event) {
        if (Main.choseKit.get(event.getPlayer())) {
            event.getPlayer().sendMessage(ChatColor.RED + "You already chose a kit! You can choose another one when you die.");
            event.setWillClose(true);
        }
        if (!Main.db.doesPlayerHaveKit(event.getPlayer(), Kits.ENDERMAN)) {
            event.setWillClose(true);
            event.getPlayer().sendMessage(ChatColor.RED + "You must purchase this kit in the shop before you can use it.");
        } else {
            event.setWillClose(true);
            Player p = event.getPlayer();
            Main.choseKit.put(p, true);

            p.sendMessage(ChatColor.DARK_GREEN + "You have selected the kit Enderman.");

            for (PotionEffect i1 : p.getActivePotionEffects()) {
                p.removePotionEffect(i1.getType());
            }

            p.getInventory().clear();
            p.getInventory().setChestplate(null);
            p.getInventory().setHelmet(null);
            p.getInventory().setLeggings(null);
            p.getInventory().setBoots(null);
            ItemStack i11 = new ItemStack(Material.LEATHER_HELMET);
            ItemMeta m11 = i11.getItemMeta();
            m11.setDisplayName(ChatColor.DARK_GREEN + "Leather Helmet");
            i11.setItemMeta(m11);
            ItemStack i2 = new ItemStack(Material.IRON_CHESTPLATE);
            ItemMeta m2 = i2.getItemMeta();
            m2.setDisplayName(ChatColor.DARK_GREEN + "Iron Chestplate");
            i2.setItemMeta(m2);
            ItemStack i3 = new ItemStack(Material.CHAINMAIL_LEGGINGS);
            ItemMeta m3 = i3.getItemMeta();
            m3.setDisplayName(ChatColor.DARK_GREEN + "Chain Leggings");
            i3.setItemMeta(m3);
            ItemStack i4 = new ItemStack(Material.DIAMOND_BOOTS);
            ItemMeta m4 = i4.getItemMeta();
            m4.setDisplayName(ChatColor.DARK_GREEN + "Diamond Boots");
            i4.setItemMeta(m4);
            ItemStack i5 = new ItemStack(Material.IRON_SWORD);
            ItemMeta m5 = i5.getItemMeta();
            m5.setDisplayName(ChatColor.DARK_GREEN + "Iron Sword");
            i5.setItemMeta(m5);
            ItemStack i6 = new ItemStack(Material.ENDER_PEARL, 16);
            ItemMeta m6 = i6.getItemMeta();
            m6.setDisplayName(ChatColor.DARK_GREEN + "Enderpearl");
            i6.setItemMeta(m6);
            p.getInventory().setItem(0, i5);
            p.getInventory().setItem(1, i6);
            p.getInventory().setHelmet(i11);
            p.getInventory().setChestplate(i2);
            p.getInventory().setLeggings(i3);
            p.getInventory().setBoots(i4);
        }
    }
}

class SniperKit extends MenuItem {
    SniperKit() {
        super(ChatColor.DARK_GREEN + "Sniper", new ItemStack(Material.AIR), "");
        ItemStack bow = new ItemStack(Material.BOW);
        bow.addUnsafeEnchantment(Enchantment.LUCK, 1);
        ItemMeta meta = bow.getItemMeta();
        meta.setDisplayName(ChatColor.DARK_GREEN + "Sniper");
        setIcon(bow);
    }

    @Override
    public ItemStack getFinalIcon(Player player) {
        ItemStack finalIcon = super.getFinalIcon(player);
        if (!Main.db.doesPlayerHaveKit(player, Kits.SNIPER)) {
            ItemMeta meta = finalIcon.getItemMeta();
            meta.setDisplayName(ChatColor.DARK_GREEN + "Sniper " + ChatColor.RED + "(LOCKED)");
            finalIcon.setItemMeta(meta);
        }
        return finalIcon;
    }

    @Override
    public void onItemClick(ItemClickEvent event) {
        if (Main.choseKit.get(event.getPlayer())) {
            event.getPlayer().sendMessage(ChatColor.RED + "You already chose a kit! You can choose another one when you die.");
            event.setWillClose(true);
        } if (!Main.db.doesPlayerHaveKit(event.getPlayer(), Kits.SNIPER)) {
            event.setWillClose(true);
            event.getPlayer().sendMessage(ChatColor.RED + "You must purchase this kit in the shop before you can use it.");
        } else {
            event.setWillClose(true);
            Player p = event.getPlayer();
            Main.choseKit.put(p, true);

            p.sendMessage(ChatColor.DARK_GREEN + "You have selected the kit Sniper.");
            for (PotionEffect i1 : p.getActivePotionEffects()) {
                p.removePotionEffect(i1.getType());
            }

            p.getInventory().clear();
            p.getInventory().setChestplate(null);
            p.getInventory().setHelmet(null);
            p.getInventory().setLeggings(null);
            p.getInventory().setBoots(null);
            ItemStack i21 = new ItemStack(Material.IRON_CHESTPLATE);
            ItemMeta m21 = i21.getItemMeta();
            m21.setDisplayName(ChatColor.DARK_GREEN + "Iron Chestplate");
            i21.setItemMeta(m21);
            ItemStack i6 = new ItemStack(Material.BOW);
            ItemMeta m6 = i6.getItemMeta();
            m6.setDisplayName(ChatColor.DARK_GREEN + "Bow");
            m6.addEnchant(Enchantment.DURABILITY, 3, true);
            m6.addEnchant(Enchantment.ARROW_INFINITE, 1, true);
            m6.addEnchant(Enchantment.ARROW_DAMAGE, 4, true);
            m6.addEnchant(Enchantment.ARROW_KNOCKBACK, 1, true);
            m6.addEnchant(Enchantment.ARROW_FIRE, 1, true);
            i6.setItemMeta(m6);
            ItemStack i7 = new ItemStack(Material.ARROW);
            ItemMeta m7 = i7.getItemMeta();
            m7.setDisplayName(ChatColor.DARK_GREEN + "Arrow");
            i7.setItemMeta(m7);
            p.getInventory().setItem(0, i6);
            p.getInventory().setItem(8, i7);
            p.getInventory().setChestplate(i21);
        }
    }
}

class TankKit extends MenuItem {
    TankKit() {
        super(ChatColor.DARK_GREEN + "Tank", new ItemStack(Material.DIAMOND_CHESTPLATE), "");
    }

    @Override
    public void onItemClick(ItemClickEvent event) {
        if (Main.choseKit.get(event.getPlayer())) {
            event.getPlayer().sendMessage(ChatColor.RED + "You already chose a kit! You can choose another one when you die.");
            event.setWillClose(true);
        } else {
            event.setWillClose(true);
            Player p = event.getPlayer();
            Main.choseKit.put(p, true);

            p.sendMessage(ChatColor.DARK_GREEN + "You have selected the kit Tank.");
            for (PotionEffect i1 : p.getActivePotionEffects()) {
                p.removePotionEffect(i1.getType());
            }

            p.getInventory().clear();
            p.getInventory().setChestplate(null);
            p.getInventory().setHelmet(null);
            p.getInventory().setLeggings(null);
            p.getInventory().setBoots(null);
            ItemStack i11 = new ItemStack(Material.IRON_HELMET);
            ItemMeta m11 = i11.getItemMeta();
            m11.setDisplayName(ChatColor.DARK_GREEN + "Iron Helmet");
            i11.setItemMeta(m11);
            ItemStack i2 = new ItemStack(Material.DIAMOND_CHESTPLATE);
            ItemMeta m2 = i2.getItemMeta();
            m2.setDisplayName(ChatColor.DARK_GREEN + "Diamond Chestplate");
            i2.setItemMeta(m2);
            ItemStack i3 = new ItemStack(Material.IRON_LEGGINGS);
            ItemMeta m3 = i3.getItemMeta();
            m3.setDisplayName(ChatColor.DARK_GREEN + "Iron Leggings");
            i3.setItemMeta(m3);
            ItemStack i4 = new ItemStack(Material.DIAMOND_BOOTS);
            ItemMeta m4 = i4.getItemMeta();
            m4.setDisplayName(ChatColor.DARK_GREEN + "Diamond Boots");
            i4.setItemMeta(m4);
            ItemStack i5 = new ItemStack(Material.STONE_SWORD);
            ItemMeta m5 = i5.getItemMeta();
            m5.setDisplayName(ChatColor.DARK_GREEN + "Stone Sword");
            i5.setItemMeta(m5);
            ItemStack i6 = new ItemStack(Material.GOLDEN_APPLE);
            ItemMeta m6 = i6.getItemMeta();
            m6.setDisplayName(ChatColor.DARK_GREEN + "Golden Apple");
            i6.setItemMeta(m6);
            p.getInventory().setItem(0, i5);
            p.getInventory().setItem(1, i6);
            p.getInventory().setHelmet(i11);
            p.getInventory().setChestplate(i2);
            p.getInventory().setLeggings(i3);
            p.getInventory().setBoots(i4);
            p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 999999, 1));
        }
    }
}