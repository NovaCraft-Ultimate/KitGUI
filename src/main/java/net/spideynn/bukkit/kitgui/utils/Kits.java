package net.spideynn.bukkit.kitgui.utils;

public enum Kits {

    ARCHER("Archer", 0, 0),
    ASSASSIN("Assassin", 1, 45),
    AXES("Axes", 2, 35),
    CACTI("Cacti", 3, 60),
    ENDERMAN("Enderman", 4, 40),
    SNIPER("Sniper", 5, 85),
    TANK("Tank", 6, 0);

    private final String name;
    private final int kitNum;
    private final int cost;

    Kits(String name, int kitNum, int cost) {
        this.kitNum = kitNum;
        this.name = name;
        this.cost = cost;
    }

    public int getKitNum() {
        return kitNum;
    }

    public int getCost() {
        return cost;
    }

    public String getName() {
        return name;
    }
}
