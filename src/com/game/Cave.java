package com.game;

public class Cave extends BattleLoc{
    public Cave(Player player) {
        super(player, "Mağara", 3, new Zombie(), "food", 3);
    }
}
