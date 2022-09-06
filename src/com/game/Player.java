package com.game;

import java.util.Scanner;

public class Player {
    /**
     * Oyunda name dışındaki özellikler dışarıdan alınacağı için
     * constructor metotta sadece name özelliği olacak.
     */

    private int damage;
    private int health;
    private int originalHealth;
    private int money;
    private String charName;//character name
    private String name;
    private Scanner scanner = new Scanner(System.in);
    private Inventory inventory;

    public Player(String name) {
        this.name = name;
        this.inventory = new Inventory();
    }

    public void selectChar() {
        GameChar[] charList = {new Samurai(), new Archer(), new Knight()};
        System.out.println("------------------------------------------------");
        System.out.println("Karakterler...");
        for (GameChar gameChar : charList) {
            System.out.println(
                    "ID: " + gameChar.getId() +
                    " \t İsim: " + gameChar.getName() +
                    " \t Hasar: " + gameChar.getDamage() +
                    " \t Sağlık: " + gameChar.getHealth() +
                    " \t Para: " + gameChar.getMoney()
            );
        }
        System.out.println("-------------------------------------------------");
        System.out.println("Lütfen bir karakter seçiniz (ID): ");
        int selectChar = scanner.nextInt();
        switch (selectChar) {
            case 1:
                initPlayer(new Samurai());
                break;
            case 2:
                initPlayer(new Archer());
                break;
            case 3:
                initPlayer(new Knight());
                break;
            default:
                initPlayer(new Samurai());
        }
    }

    public void initPlayer(GameChar gameChar) {
        this.setCharName(gameChar.getName());
        this.setDamage(gameChar.getDamage());
        this.setHealth(gameChar.getHealth());
        this.setOriginalHealth(gameChar.getHealth());
        this.setMoney(gameChar.getMoney());
    }

    public void printInfo() {
        System.out.println(this.getCharName());
        System.out.println("Silahınız: " + this.getInventory().getWeapon().getName() +
                " Zırhınız: " + this.getInventory().getArmor().getName() +
                " Kazanımlar:  " + this.getInventory().getObjects() +
                " Hasar: " + this.getTotalDamage() + " Sağlık: " + this.getHealth() + " Para:" + this.getMoney()
        );
    }

    public int getTotalDamage() {
        return damage + this.getInventory().getWeapon().getDamage();
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

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public String getCharName() {
        return charName;
    }

    public void setCharName(String charName) {
        this.charName = charName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public int getOriginalHealth() {
        return originalHealth;
    }

    public void setOriginalHealth(int originalHealth) {
        this.originalHealth = originalHealth;
    }
}
