package Locations;
import Game.Player;
import Enemy.Vampire;


public class Forest extends BattleArea{


    public Forest(Player player) {
        super(player, "Orman", new Vampire(), "firewood", 3);
    }
}
