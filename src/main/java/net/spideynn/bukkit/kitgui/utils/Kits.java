package net.spideynn.bukkit.kitgui.utils;

import java.util.ArrayList;

public enum Kits {

    ARCHER(0), //default kit
    ASSASSIN(1),
    AXES(2),
    CACTI(3),
    ENDERMAN(4),
    SNIPER(5),
    TANK(6); //default kit


    private int kitNum;

    Kits(int kitNum) {
        this.kitNum = kitNum;
    }

    public int getKitNum() {
        return kitNum;
    }
}
