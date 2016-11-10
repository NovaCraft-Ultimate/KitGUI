package net.spideynn.bukkit.kitgui.mongodb;

import net.spideynn.bukkit.kitgui.utils.Kits;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.*;

import java.util.ArrayList;
import java.util.List;

@Entity(value = "Players", noClassnameStored = true)
public class Player {

    @Id
    public ObjectId id;

    @Indexed(options = @IndexOptions(unique = true))
    public String uuid;

    @Indexed
    public String username;

    public String ip;

    public long connectionTime;

    @Property("ip_history")
    public List<String> ipHistory = new ArrayList<>();

    @Property("name_history")
    public List<String> nameHistory = new ArrayList<>();

    @Property("friends")
    public List<String> friends = new ArrayList<>();

    @Property("kits")
    public List<Integer> kits = new ArrayList<>();

    public ObjectId getId() {
        return id;
    }

    public void setUUID(String uuid) {
        this.uuid = uuid;
    }

    public void setIp(String ip) {
        this.ip = ip;
        ipHistory.add(ip);
    }

    public void addFriend(org.bukkit.entity.Player friend) {
        friends.add(friend.getUniqueId().toString());
    }

    public void removeFriend(org.bukkit.entity.Player friend) {
        friends.remove(friend.getUniqueId().toString());
    }

    public void setUsername(String username) {
        this.username = username;
        nameHistory.add(username);
    }

    public void unlockKit(Kits kit) {
        kits.add(kit.getKitNum());
    }

    public String toString() {
        return String.valueOf(id) + "," + username + "," + ip + "," + connectionTime + "," + uuid;
    }
}
