package com.game;

import java.util.Random;

public class Pit extends BattleLoc {
    public Pit(Player player) {
        /**
         * Kazanılan ödül belli ihtimallere göre rastgele belirleneceği için burada 0 ile 100 arası gelecek rastgele
         * sayıya göre bir ödül belirleme mekanizması yazılacak.
         */

        super(player, "Maden", 4, new Snake(), Integer.toString(new Random().nextInt(101)), 5);
    }
}
