package com.game;

import java.util.Scanner;

public class Game {
    private Scanner scanner = new Scanner(System.in);

    public void start() {
        System.out.println("Macera oyununa hoşgeldiniz...");
        System.out.println("Lütfen bir isim giriniz: ");
        /*String playerName = scanner.nextLine();
        Player player = new Player(playerName);*/
        Player player = new Player("Mustafa");

        System.out.print("Sayın " + player.getName() + " Bu karanlık ve sisli adaya hoşgeldiniz... ");
        System.out.println("Burada yaşananların hepsi gerçek!");

        System.out.println("Lütfen bir karakter seçiniz: ");
        player.selectChar();

        Location location = null;

        while (true) {
            player.printInfo();
            System.out.println("Bölgeler: ");
            System.out.println("1- Güvenli Ev");
            System.out.println("2- Mağaza");
            System.out.println("3- Mağara -->> Ödül: <Yemek>  Dikkatli ol! Canavar çıkabilir!");
            System.out.println("4- Orman -->> Ödül: <Odun>   Dikkatli ol! Canavar çıkabilir!");
            System.out.println("5- Nehir -->> Ödül: <Su>  Dikkatli ol! Canavar çıkabilir!");
            System.out.println("0- Çıkış Yap");

            System.out.print("Lütfen gitmek istediğiniz bölgeyi seçiniz:");
            int selectLoc = scanner.nextInt();

            switch (selectLoc) {
                case 0:
                    location = null;
                    break;
                case 1:
                    location = new SafeHouse(player);
                    break;
                case 2:
                    location = new ToolStore(player);
                    break;
                case 3:
                    if(location.getPlayer().getInventory().getObjects().contains("Yemek")) {
                        System.out.println("Bu bölge temizlendi. Lütfen farklı bir bölge seçiniz.");
                        location.setClean(true);
                        break;
                    }
                    else {
                        location = new Cave(player);
                        break;
                    }
                case 4:
                    if(location.getPlayer().getInventory().getObjects().contains("Odun")) {
                        System.out.println("Bu bölge temizlendi. Lütfen farklı bir bölge seçiniz.");
                        location.setClean(true);
                        break;
                    } else {
                        location = new Forest(player);
                        break;
                    }
                case 5:
                    if(location.getPlayer().getInventory().getObjects().contains("Su")) {
                        System.out.println("Bu bölge temizlendi. Lütfen farklı bir bölge seçiniz.");
                        location.setClean(true);
                        break;
                    } else {
                        location = new River(player);
                        break;
                    }
                default:
                    location = new SafeHouse(player);
            }

            if (location == null) {
                System.out.println("Bu karanlık ve sisli adadan çıkış yaptın!");
                break;
            }

            if (!location.onLocation()) {
                System.out.println("GAME OVER!");
                break;
            }
        }

    }
}
