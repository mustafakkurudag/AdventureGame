package com.game;

public class Inventory {
    private Weapon weapon;
    /*private int weaponDamage;
    private String weaponName;*/
    private Armor armor;

    public Inventory() {
        this.weapon = new Weapon(-1, "Yumruk", 0,0);
        this.armor = new Armor(-1, "-",0,0);
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
}
