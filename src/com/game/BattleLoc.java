package com.game;

import java.util.Random;

public class BattleLoc extends Location {
    private int id;
    private Monster monster;
    private String award;
    private int maxMonster;

    public BattleLoc(Player player, String name, int id, Monster monster, String award, int maxMonster) {
        super(player, name);
        this.id = id;
        this.monster = monster;
        this.award = award;
        this.maxMonster = maxMonster;
        this.setClean(false);
    }

    @Override
    public boolean onLocation() {
        int monsterNumber = this.randomMonsterNumber();
        if (!this.isClean()) {
            System.out.println("Şu an buradasınız: " + this.getName());
            System.out.println("Dikkatli ol! Burada " + monsterNumber + " adet " +
                    this.getMonster().getName() + " yaşıyor!");
            System.out.println("<S>avaş veya <K>aç");
            String selectCase = scanner.nextLine();
            selectCase = selectCase.toUpperCase();

            if (selectCase.equals("S") && combat(monsterNumber)) {
                System.out.println("Savaş başlıyor...");
                System.out.println(this.getName() + " Tebrikler. Tüm düşmanları yendiniz...");
                if (this.getId() == 3) {
                    this.getPlayer().getInventory().getObjects().add("Yemek");
                    System.out.println(this.isClean());
                    this.setClean(true);
                    System.out.println(this.isClean());
                }

                if (this.getId() == 4) {
                    this.getPlayer().getInventory().getObjects().add("Odun");
                    this.setClean(true);
                }

                if (this.getId() == 5) {
                    this.getPlayer().getInventory().getObjects().add("Su");
                    this.setClean(true);
                }
                return true;
            }
        } else {
            return true;
        }

        if (this.getPlayer().getHealth() <= 0) {
            System.out.println("Öldünüz...");
            return false;
        }

        return true;
    }

    public boolean combat(int monsterNumber) {
        for (int i = 1; i <= monsterNumber; i++) {
            this.getMonster().setHealth(this.getMonster().getOriginalHealth());
            playerStats();
            monsterStats(i);
            while (this.getPlayer().getHealth() > 0 && this.getMonster().getHealth() > 0) {
                System.out.println("<V>ur veya <K>aç");
                String selectCombat = scanner.nextLine().toUpperCase();
                if (selectCombat.equals("V")) {
                    System.out.println("Siz vurdunuz");
                    this.getMonster().setHealth(this.getMonster().getHealth() - this.getPlayer().getTotalDamage());
                    afterHit();
                    if (this.getMonster().getHealth() > 0) {
                        System.out.println();
                        System.out.println("Canavar size vurdu");
                        int monsterDamage = this.getMonster().getDamage() - this.getPlayer().getInventory().getArmor().getBlock();
                        if (monsterDamage < 0) {
                            monsterDamage = 0;
                        }
                        this.getPlayer().setHealth(this.getPlayer().getHealth() - monsterDamage);
                        afterHit();
                    }
                } else {
                    return false;
                }
            }

            if (this.getMonster().getHealth() < this.getPlayer().getHealth()) {
                System.out.println("Tebrikler. Düşmanı yendiniz...");
                System.out.println(this.getMonster().getWinAward() + " para kazandınız");
                this.getPlayer().setMoney(this.getPlayer().getMoney() + this.getMonster().getWinAward());
                System.out.println("Güncel paranız: " + this.getPlayer().getMoney());
            } else {
                return false;
            }
        }
        return true;
    }

    public void afterHit() {
        System.out.println("Canınız: " + this.getPlayer().getHealth());
        System.out.println(this.getMonster().getName() + " canı: " + this.getMonster().getHealth());
        System.out.println();
    }

    public void playerStats() {
        System.out.println("Oyuncu değerleri: ");
        System.out.println("****************************");
        System.out.println("Sağlık: " + this.getPlayer().getHealth());
        System.out.println("Silah: " + this.getPlayer().getInventory().getWeapon().getName());
        System.out.println("Zırh: " + this.getPlayer().getInventory().getArmor().getName());
        System.out.println("Bloklama: " + this.getPlayer().getInventory().getArmor().getBlock());
        System.out.println("Hasar: " + this.getPlayer().getTotalDamage());
        System.out.println("Para: " + this.getPlayer().getMoney());
    }

    public void monsterStats(int i) {
        System.out.println(i + ". " + this.getMonster().getName() + " değerleri: ");
        System.out.println("****************************");
        System.out.println("Sağlık: " + this.getMonster().getHealth());
        System.out.println("Hasar: " + this.getMonster().getDamage());
        System.out.println("Ödül: " + this.getMonster().getWinAward());
    }

    public int randomMonsterNumber() {
        Random r = new Random();
        return r.nextInt(this.getMaxMonster()) + 1; //(0 ile 2) + 1 arasında rastgele sayı üretir. canavar sayısını belirler.
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Monster getMonster() {
        return monster;
    }

    public void setMonster(Monster monster) {
        this.monster = monster;
    }

    public String getAward() {
        return award;
    }

    public void setAward(String award) {
        this.award = award;
    }

    public int getMaxMonster() {
        return maxMonster;
    }

    public void setMaxMonster(int maxMonster) {
        this.maxMonster = maxMonster;
    }

}
