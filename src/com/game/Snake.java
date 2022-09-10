package com.game;

import java.util.Random;

public class Snake extends Monster {
    //Yılanın vuracağı darbe en az 3 en fazla 6 olacak şekilde rastgele belirlenecek.
    public Snake() {
        super(4, "Yılan", new Random().nextInt(4) + 3, 12, 0);
    }
}
