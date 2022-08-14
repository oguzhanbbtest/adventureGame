package Locations;

import Enemy.Bear;
import Game.Player;

public class River extends BattleArea{

    public River(Player player) {
        super(player, "Nehir",new Bear(),"water",2);
    }

}
