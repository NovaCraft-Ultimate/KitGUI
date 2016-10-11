package net.spideynn.bukkit.kitgui.mysql;

//import org.apache.commons.codec.binary.Base64;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class MySQL extends Database {
    private final String user;
    private final String database;
    private final String password;
    private final String port;
    private final String hostname;
    private Connection connection;

    private static final String TABLE_NAME = "player_friends";

    /**
     * Creates a new MySQL instance
     *
     * @param plugin   Plugin instance
     * @param hostname Name of the host
     * @param port     Port number
     * @param database Database name
     * @param username Username
     * @param password Password
     */
    public MySQL(Plugin plugin, String hostname, String port, String database, String username, String password) {
        super(plugin);
        this.hostname = hostname;
        this.port = port;
        this.database = database;
        this.user = username;
        this.password = password;
        this.connection = null;
    }

    @Override
    public Connection openConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://" + this.hostname + ":" + this.port + "/" + this.database, this.user, this.password);
        } catch (SQLException e) {
            plugin.getLogger().log(Level.SEVERE, "Could not connect to MySQL server because: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            plugin.getLogger().log(Level.SEVERE, "JDBC Driver not found!");
        }
        return connection;
    }

    @Override
    public boolean checkConnection() {
        return connection != null;
    }

    @Override
    public Connection getConnection() {
        return connection;
    }

    @Override
    public void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                plugin.getLogger().log(Level.SEVERE, "Error closing the MySQL Connection!");
                e.printStackTrace();
            }
        }
    }

    public void addFriend(Player player, Player newFriend) {
        List<Player> friendsArray = getPlayersFriends(player);
        friendsArray.add(newFriend);
        String QUERY = "UPDATE " + TABLE_NAME + "SET friends=" + serialize(friendsArray) + " WHERE playeruuid=" + player.getUniqueId() + " LIMIT 1";
        updateSql(QUERY);
    }

    public List<Player> getPlayersFriends(Player player) {
        List<String> frienduuids;
        String QUERY = "SELECT friends FROM " + TABLE_NAME + "WHERE `playeruuid`='" + player.getUniqueId() + "'";
        ResultSet resultSet = querySql(QUERY);
        try {
            if (!resultSet.isBeforeFirst()) {
                System.out.println("No friend data for player " + player.getName());
                return null;
            }
            frienduuids = (ArrayList<String>) resultSet.getArray("friends").getArray();
            List<Player> friends = new ArrayList<Player>();
            for (String friend : frienduuids) {
                friends.add(Bukkit.getPlayer(friend));
            }
            return friends;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String serialize(List<Player> players) {
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            new ObjectOutputStream(out).writeObject(players);
            //return new String(Base64.encodeBase64(out.toByteArray()));
        } catch (IOException e) {
            Bukkit.getLogger().severe("Could not serialize an array of players");
            e.printStackTrace();
        }
        return null;
    }

    public String deserialize(String serializedText) {
        //ByteArrayInputStream in = new ByteArrayInputStream(Base64.decodeBase64(serializedText.getBytes()));
        //return in.toString();
        return "";
    }

    public ResultSet querySql(String query) {
        Connection c;

        if (checkConnection()) {
            c = getConnection();
        } else {
            c = openConnection();
        }

        Statement s = null;

        try {
            s = c.createStatement();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }

        ResultSet ret = null;

        try {
            ret = s.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        closeConnection();

        return ret;
    }

    public void updateSql(String update) {

        Connection c;

        if (checkConnection()) {
            c = getConnection();
        } else {
            c = openConnection();
        }

        Statement s;

        try {
            s = c.createStatement();
            s.executeUpdate(update);
        } catch (SQLException e1) {
            e1.printStackTrace();
        }

        closeConnection();
    }
}