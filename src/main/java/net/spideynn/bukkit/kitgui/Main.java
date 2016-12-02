package net.spideynn.bukkit.kitgui;

import net.spideynn.bukkit.kitgui.gui.MainGUI;
import net.spideynn.bukkit.kitgui.listeners.Listeners;
import net.spideynn.bukkit.kitgui.mongodb.DatabaseHandler;
import net.spideynn.bukkit.kitgui.utils.Kits;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Logger;

public class Main extends JavaPlugin {

    private final Logger log = getLogger();
    public static final HashMap<Player, Boolean> choseKit = new HashMap<>();
    public static final DatabaseHandler db = new DatabaseHandler(27017);
    private static JavaPlugin instance;

    @Override
    public void onDisable() {
        instance = null;
    }

    @Override
    public void onEnable() {
        instance = this;
        getServer().getPluginManager().registerEvents(new Listeners(), this);

        for (Player player : Bukkit.getOnlinePlayers()) {
            choseKit.put(player, false);
        }

        Plugin pl = Bukkit.getPluginManager().getPlugin("BattleLevels");
        if (pl == null) {
            log.severe("BattleLevels is not installed. Disabling plugin.");
            Bukkit.getPluginManager().disablePlugin(this);
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (label.equalsIgnoreCase("kitgui")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                if (args.length == 2 && args[0].equalsIgnoreCase("unlockall")) {
                    Player p = Bukkit.getPlayer(args[1]);
                    if (p.getPlayer() == null) {
                        sender.sendMessage(ChatColor.RED + "Player is not online or does not exist.");
                        return true;
                    }
                    net.spideynn.bukkit.kitgui.mongodb.Player pl = db.getUserByPlayer(p.getPlayer());
                    for (Kits kit : Kits.values()) {
                        pl.kits.add(kit.getKitNum());
                    }
                    Bukkit.getScheduler().runTaskAsynchronously(Main.getInstance(), () -> db.savePlayer(pl));
                    player.getPlayer().sendMessage("Unlocked all the kits for " + args[1] + ".");
                    return true;
                } else if (args.length == 2 && args[0].equalsIgnoreCase("lockall")) {
                    Player p = Bukkit.getPlayer(args[1]).getPlayer();
                    if (p.getPlayer() == null) {
                        sender.sendMessage(ChatColor.RED + "Player is not online or does not exist.");
                        return true;
                    }
                    net.spideynn.bukkit.kitgui.mongodb.Player pl = db.getUserByPlayer(p.getPlayer());
                    pl.kits = new ArrayList<>();
                    Bukkit.getScheduler().runTaskAsynchronously(Main.getInstance(), () -> db.savePlayer(pl));
                    p.getPlayer().sendMessage("Locked all the kits for " + args[1] + ".");
                    return true;
                } else if (args.length == 2 && args[0].equalsIgnoreCase("debug")) {
                    Player p = Bukkit.getPlayer(args[1]).getPlayer();
                    if (p.getPlayer() == null) {
                        sender.sendMessage(ChatColor.RED + "Player is not online or does not exist.");
                        return true;
                    }
                    net.spideynn.bukkit.kitgui.mongodb.Player pl = db.getUserByPlayer(p.getPlayer());
                    sender.sendMessage(pl.toString());
                    return true;
                }
                MainGUI mainGUI = new MainGUI(this, player);
                mainGUI.open(player);
                return true;
            } else {
                sender.sendMessage(ChatColor.RED + "You must use this command in-game!");
            }
        }
        return false;
    }

    public static JavaPlugin getInstance() {
        return instance;
    }
}
