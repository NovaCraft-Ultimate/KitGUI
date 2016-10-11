package net.spideynn.bukkit.kitgui.mongodb;

import org.mongodb.morphia.annotations.*;

import java.util.ArrayList;
import java.util.List;

@Entity(value = "Players", noClassnameStored = true)
public class Player {

    @Id
    public int id;

    @Indexed(options = @IndexOptions(unique = true))
    public String uuid;

    @Indexed
    public String username;

    public int ip;

    public long connectionTime;

    @Property("ip_history")
    public List<Integer> ipHistory = new ArrayList<>();

    @Property("name_history")
    public List<String> nameHistory = new ArrayList<>();

    @Property("friends")
    public List<String> friends = new ArrayList<>();
}
