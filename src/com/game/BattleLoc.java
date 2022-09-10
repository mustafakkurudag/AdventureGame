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
            if (this.getName().equals("Maden")) {
                while (monsterNumber < 3) {
                    monsterNumber = new Random().nextInt(4) + 3;
                }
            }
            System.out.println("Dikkatli ol! Burada " + monsterNumber + " adet " +
                    this.getMonster().getName() + " yaşıyor!");
            System.out.println("<S>avaş veya <K>aç");
            String selectCase = scanner.nextLine();
            selectCase = selectCase.toUpperCase();

            if (selectCase.equals("S") && combat(monsterNumber)) {
                System.out.println("Savaş başlıyor...");
                System.out.println(this.getName() + " Tebrikler. Tüm düşmanları yendiniz...");
                winAwardByLocName(this.getName());
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

    public void winAwardByLocName(String name) {
        if (name.equals("Mağara")) {
            this.getPlayer().getInventory().getObjects().add("Yemek");
        } else if (name.equals("Orman")) {
            this.getPlayer().getInventory().getObjects().add("Odun");
        } else if (name.equals("Nehir")) {
            this.getPlayer().getInventory().getObjects().add("Su");
        } else if (name.equals("Maden")) {
            getRandomAward();
        }
    }

    public void getRandomAward(){
        //madendeki savaş kazanıldıktan sonra elde edilecek ödül
        int awardPossibility = Integer.parseInt(this.getAward());
        System.out.println("Ödül kazanma ihtimali " + awardPossibility);
        if (awardPossibility <= 15) {
            //silah kazanma ihtimalleri
            int weaponPossibility = new Random().nextInt(101);
            if (weaponPossibility <= 20) {
                //tüfek
                System.out.println("Tebrikler " + Weapon.getWeaponById(3).getName() + " ödülünü kazandınız");
                this.getPlayer().getInventory().setWeapon(Weapon.getWeaponById(3));
            } else if (weaponPossibility <= 50) {
                //kılıç
                this.getPlayer().getInventory().setWeapon(Weapon.getWeaponById(1));
                System.out.println("Tebrikler " + Weapon.getWeaponById(1).getName() + " ödülünü kazandınız");
            } else {
                //tabanca
                System.out.println("Tebrikler " + Weapon.getWeaponById(2).getName() + " ödülünü kazandınız");
                this.getPlayer().getInventory().setWeapon(Weapon.getWeaponById(2));
            }
        } else if (awardPossibility <= 30) {
            //zırh kazanma ihtimalleri
            int armorPossibility = new Random().nextInt(101);
            if (armorPossibility <= 20) {
                //ağır
                System.out.println("Tebrikler " + Armor.getArmorById(3).getName() + " ödülünü kazandınız");
                this.getPlayer().getInventory().setArmor(Armor.getArmorById(3));
            } else if (armorPossibility <= 50) {
                //orta
                System.out.println("Tebrikler " + Armor.getArmorById(2).getName() + " ödülünü kazandınız");
                this.getPlayer().getInventory().setArmor(Armor.getArmorById(2));
            } else {
                //hafif
                System.out.println("Tebrikler " + Armor.getArmorById(1).getName() + " ödülünü kazandınız");
                this.getPlayer().getInventory().setArmor(Armor.getArmorById(1));
            }
        } else if (awardPossibility <= 55) {
            //para kazanma ihtimalleri
            int moneyPossibility = new Random().nextInt(101);
            if (moneyPossibility <= 20) {
                //10 para kazanacak
                System.out.println("Tebrikler 10 para ödülünü kazandınız");
                this.getPlayer().setMoney(this.getPlayer().getMoney() + 10);
            } else if (moneyPossibility <= 50) {
                //5 para kazanacak
                System.out.println("Tebrikler 5 para ödülünü kazandınız");
                this.getPlayer().setMoney(this.getPlayer().getMoney() + 5);
            } else {
                //1 para kazanacak
                System.out.println("Tebrikler 1 para ödülünü kazandınız");
                this.getPlayer().setMoney(this.getPlayer().getMoney() + 1);
            }
        } else {
            System.out.println("Ödül kazanamadınız...");
        }
    }

    public boolean combat(int monsterNumber) {
        boolean isRan = false;
        for (int i = 1; i <= monsterNumber; i++) {
            this.getMonster().setHealth(this.getMonster().getOriginalHealth());
            playerStats();
            monsterStats(i);
            while (this.getPlayer().getHealth() > 0 && this.getMonster().getHealth() > 0) {
                if (!isRan && new Random().nextInt(2) == 0) {
                    try {
                        System.out.println("ilk darbeyi " + this.getMonster().getName() + " vuracak");
                        Thread.sleep(3000);
                        decideWhoHitFirst();
                        isRan = true;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
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

    public void decideWhoHitFirst() {
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
