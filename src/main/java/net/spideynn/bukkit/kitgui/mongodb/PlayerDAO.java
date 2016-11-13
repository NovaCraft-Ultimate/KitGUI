package net.spideynn.bukkit.kitgui.mongodb;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;

class PlayerDAO extends BasicDAO<Player, String> {

    public PlayerDAO(Class<Player> entityClass, Datastore ds) {
        super(entityClass, ds);
    }
}
