package net.spideynn.bukkit.kitgui.utils;

public enum Kits {

    ARCHER(0),
    ASSASSIN(1),
    AXES(2),
    CACTI(3),
    ENDERMAN(4),
    SNIPER(5),
    TANK(6);


    private final int kitNum;

    Kits(int kitNum) {
        this.kitNum = kitNum;
    }

    public int getKitNum() {
        return kitNum;
    }
}
