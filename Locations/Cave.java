package Locations;
import Enemy.Zombie;
import Game.Inventory;
import Game.Player;

public class Cave extends BattleArea {
    public Cave(Player player) {
        super(player, "Magara", new Zombie(), "food",3);
    }
}
