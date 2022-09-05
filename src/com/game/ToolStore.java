package com.game;

public class ToolStore extends NormalLoc{
    public ToolStore(Player player) {
        super(player, "Mağaza");
    }

    @Override
    public boolean onLocation() {
        System.out.println("-*-*-*-*-*-*-*-Mağazaya hoşgeldiniz-*-*-*-*-*-*-*-");
        boolean showMenu = true;
        while (showMenu) {
            System.out.println("1- Silahlar");
            System.out.println("2- Zırhlar");
            System.out.println("3- Çıkış yap");

            System.out.print("Seçiminiz: ");
            int selectCase = scanner.nextInt();
            while (selectCase < 1 || selectCase > 3) {
                System.out.println("Geçersiz değer. Tekrar deneyiniz:");
                selectCase = scanner.nextInt();
            }
            switch (selectCase) {
                case 1:
                    printWeapons();
                    buyWeapon();
                    break;
                case 2:
                    printArmors();
                    buyArmor();
                    break;
                case 3:
                    System.out.println("Bir daha bekleriz");
                    showMenu = false;
                    break;
            }
        }
        return true;
    }

    public void printWeapons() {
        System.out.println("Silahlar");
        System.out.println();

        for (Weapon weapon: Weapon.weapons()) {
            System.out.println(weapon.getId() + " - " +  weapon.getName()
                    + " <Para: " + weapon.getPrice()
                    + ", Hasar: " + weapon.getDamage() + ">");
        }

        System.out.println("0- Çıkış Yap");
    }

    public void buyWeapon() {
        System.out.println("Bir silah seçiniz: ");
        int selectWeaponId = scanner.nextInt();
        while (selectWeaponId < 0 || selectWeaponId > Weapon.weapons().length ) {
            System.out.println("Geçersiz değer. Tekrar deneyiniz:");
            selectWeaponId = scanner.nextInt();
        }

        if (selectWeaponId != 0) {
            Weapon selectedWeapon = Weapon.getWeaponById(selectWeaponId);

            if (selectedWeapon != null) {
                if (selectedWeapon.getPrice() > this.getPlayer().getMoney()) {
                    System.out.println("yeterli paranız bulunmamaktadır...");
                } else {
                    System.out.println(selectedWeapon.getName() + " silahı alındı.");
                    this.getPlayer().setMoney(this.getPlayer().getMoney() - selectedWeapon.getPrice());
                    System.out.println("Kalan paranız: " + this.getPlayer().getMoney());
                    this.getPlayer().getInventory().setWeapon(selectedWeapon);
                }
            }
        }
    }

    public void printArmors() {
        System.out.println("Zırhlar");
        System.out.println();

        for (Armor armor: Armor.armors()) {
            System.out.println(armor.getId() + " - " +  armor.getName()
                    + " <Para: " + armor.getPrice()
                    + ", Zırh: " + armor.getBlock() + ">");
        }
        System.out.println("0- Çıkış Yap");

    }

    public void buyArmor() {
        System.out.println("Bir zırh seçiniz: ");
        int selectArmorId = scanner.nextInt();
        while (selectArmorId < 0 || selectArmorId > Armor.armors().length ) {
            System.out.println("Geçersiz değer. Tekrar deneyiniz:");
            selectArmorId = scanner.nextInt();
        }

        if (selectArmorId != 0) {
            Armor selectedArmor = Armor.getArmorById(selectArmorId);

            if (selectedArmor != null) {
                if (selectedArmor.getPrice() > this.getPlayer().getMoney()) {
                    System.out.println("Yeterli paranız bulunmamaktadır.");
                } else {
                    System.out.println(selectedArmor.getName() + " zırhını satın aldınız...");
                    this.getPlayer().setMoney(this.getPlayer().getMoney() - selectedArmor.getPrice());
                    System.out.println("Kalan paranız: " + this.getPlayer().getMoney());
                    this.getPlayer().getInventory().setArmor(selectedArmor);
                }
            }
        }
    }
}
