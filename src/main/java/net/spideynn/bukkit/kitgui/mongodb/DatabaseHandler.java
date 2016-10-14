package net.spideynn.bukkit.kitgui.mongodb;

import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;
import net.spideynn.bukkit.kitgui.Main;
import net.spideynn.bukkit.kitgui.utils.Kits;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

public class DatabaseHandler {
    private MongoClient mc;
    private Morphia morphia;
    private PlayerDAO playerDAO;

    public DatabaseHandler(int port)
    {
        ServerAddress addr = new ServerAddress("localhost", port);
        //List<MongoCredential> credentials = new ArrayList<>();
        //credentials.add(MongoCredential.createCredential("username", "database", "password".toCharArray()));
        //mc = new MongoClient(addr, credentials);
        mc = new MongoClient(addr);

        morphia = new Morphia();

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
            du.setIp("0.0.0.0");
            du.setUsername(player.getName());
            playerDAO.save(du);
        }
        return du;
    }

    public boolean doesPlayerHaveKit(org.bukkit.entity.Player player, Kits kit) {
        Player user = getUserByPlayer(player);
        return user.kits.contains(kit.getKitNum());
    }
}
