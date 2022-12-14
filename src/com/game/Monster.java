package com.game;

public abstract class Monster {
    private int id;
    private String name;
    private int damage;
    private int health;
    private int winAward;
    private int originalHealth;

    public Monster(int id, String name, int damage, int health, int winAward) {
        this.id = id;
        this.name = name;
        this.damage = damage;
        this.health = health;
        this.originalHealth = health;
        this.winAward = winAward;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        if (health < 0)
            health = 0;
        this.health = health;
    }

    public int getWinAward() {
        return winAward;
    }

    public void setWinAward(int winAward) {
        this.winAward = winAward;
    }

    public int getOriginalHealth() {
        return originalHealth;
    }

    public void setOriginalHealth(int originalHealth) {
        this.originalHealth = originalHealth;
    }
}
