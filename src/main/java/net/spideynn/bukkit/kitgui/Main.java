package net.spideynn.bukkit.kitgui;

import net.spideynn.bukkit.kitgui.gui.MainGUI;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public class Main extends JavaPlugin {

    public Logger log = getServer().getLogger();
    private static JavaPlugin instance;
    /// TODO: BattleLevels API https://github.com/RobiRami/BattleLevels/wiki

    @Override
    public void onDisable() {
        instance = null;
    }

    @Override
    public void onEnable() {
        instance = this;
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
                MainGUI mainGUI = new MainGUI(this, player);
                mainGUI.open(player);
            }
        }
        return false;
    }

    public static JavaPlugin getInstance() {
        return instance;
    }
}
