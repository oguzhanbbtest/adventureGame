package Locations;
import Game.Player;

public class SafeHouse extends NormalLoc {
    public SafeHouse(Player player) {
        super(player, "Guvenli Ev");
    }

    @Override
    public boolean onLocation() {
        System.out.println();
        System.out.println();
        System.out.println("Guvenli Evdesiniz !");
        System.out.println("Caniniz Yenilendi !");
        System.out.println();
        this.getPlayer().setHealth(this.getPlayer().getOrgHealth());
        getPlayer().printInventory();
        System.out.println();
        return true;
    }
}
