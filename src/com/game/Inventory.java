package com.game;

import java.util.ArrayList;
import java.util.List;

public class Inventory {
    private Weapon weapon;
    /*private int weaponDamage;
    private String weaponName;*/
    private Armor armor;
    private List<String> objects;

    public Inventory() {
        this.weapon = new Weapon(-1, "Yumruk", 0,0);
        this.armor = new Armor(-1, "Yok",0,0);
        this.objects = new ArrayList<>();
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public Armor getArmor() {
        return armor;
    }

    public void setArmor(Armor armor) {
        this.armor = armor;
    }

    public List<String> getObjects() {
        return objects;
    }

    public void setObjects(List<String> objects) {
        this.objects = objects;
    }
}
