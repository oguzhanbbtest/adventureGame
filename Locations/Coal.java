package Locations;

import Enemy.Snake;
import Game.Player;
public class Coal extends BattleArea{


    public Coal(Player player) {
        super(player, "Maden",new Snake(),"Ganimet",5);
    }


}
