package net.spideynn.bukkit.kitgui.mongodb;

import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;
import net.spideynn.bukkit.kitgui.utils.Kits;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import java.util.Arrays;

public class DatabaseHandler {
    private final PlayerDAO playerDAO;

    public DatabaseHandler(int port)
    {
        ServerAddress addr = new ServerAddress("10.42.246.64", port);
        MongoClient mc = new MongoClient(addr);

        Morphia morphia = new Morphia();

        morphia.map(Player.class);
        Datastore datastore = morphia.createDatastore(mc, "novacraft");
        datastore.ensureIndexes();

        playerDAO = new PlayerDAO(Player.class, datastore);
    }

    public void savePlayer(Player player) {
        playerDAO.save(player);
    }

    public Player getUserByPlayer(org.bukkit.entity.Player player)
    {
        Player du = playerDAO.findOne("uuid", player.getUniqueId().toString());
        if (du == null)
        {
            du = new Player();
            du.setUUID(player.getUniqueId().toString());
            du.setIp(Arrays.toString(player.getAddress().getAddress().getAddress()));
            du.setUsername(player.getName());
            du.choseKit = false;
            playerDAO.save(du);
        }
        return du;
    }

    public boolean doesPlayerHaveKit(org.bukkit.entity.Player player, Kits kit) {
        Player user = getUserByPlayer(player);
        return user.kits.contains(kit.getKitNum());
    }
}
