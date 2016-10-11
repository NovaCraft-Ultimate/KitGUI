package net.spideynn.bukkit.kitgui.mongodb;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler {
    private MongoClient mc;
    private Morphia morphia;
    private Datastore datastore;
    private PlayerDAO playerDAO;

    public DatabaseHandler(int port)
    {
        ServerAddress addr = new ServerAddress("hostname", port);
        List<MongoCredential> credentials = new ArrayList<>();
        credentials.add(MongoCredential.createCredential("username", "database", "password".toCharArray()));
        mc = new MongoClient(addr, credentials);

        morphia = new Morphia();

        morphia.map(Player.class);
        datastore = morphia.createDatastore(mc, "novacraft");
        datastore.ensureIndexes();

        playerDAO = new PlayerDAO(Player.class, datastore);
    }
}
