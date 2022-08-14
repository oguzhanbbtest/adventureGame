package Locations;
import Game.Player;
import Item.Armor;
import Item.Weapon;

public class ToolStore extends NormalLoc {

    public ToolStore(Player player) {
        super(player, "Magaza");
    }

    @Override
    public boolean onLocation() {
        System.out.println("Magazaya Hosgeldiniz");
        boolean showMenu = true;
        while(showMenu){
            System.out.println("1 - Silahlar");
            System.out.println("2 - Zirhlar");
            System.out.println("3 - Exit");
            System.out.print("Seciminiz : ");
            int selectCase = scan.nextInt();

            while (selectCase < 1 || selectCase > 3){
                selectCase = scan.nextInt();
            }

            switch (selectCase) {
                case 1 -> {
                    printWeapon();
                    buyWeapon();
                }
                case 2 -> {
                    printArmor();
                    buyArmor();
                }
                case 3 -> {
                    System.out.println("Bir daha bekleriz..");
                    showMenu = false;
                }
            }
        }
        return true;
    }
    public void printWeapon(){
        System.out.println("-----Silahlar-----");
        System.out.println();
        for (Weapon w : Weapon.weapons()) {
            System.out.println(w.getId() +" - " +w.getName()+" <Para : "+w.getPrice()+" , Hasar : "+w.getDamage()+" >");
        }
        System.out.println("0 - Cikis Yap");
    }
    public void printArmor(){
        System.out.println("-----Zirhlar-----");
        System.out.println();
        for (Armor a : Armor.armors()) {
            System.out.println(a.getId() + " - " + a.getName() +
                    "<Para : " + a.getPrice() + ", Zırh :" + a.getBlock() + " >");
        }
    }
    private void buyArmor() {
        System.out.println("Bir Zirh Seciniz : ");

        int selectArmorID = Location.scan.nextInt();
        while (selectArmorID < 1 || selectArmorID > Armor.armors().length){
            selectArmorID = Location.scan.nextInt();
        }
        Armor selectedArmor =  Armor.getArmorObjByID(selectArmorID);

        if(selectedArmor != null){
            if(selectedArmor.getPrice() > this.getPlayer().getMoney()){
                System.out.println("Yeterli Altininiz Bulunmamaktadır !");
            }else{
                System.out.println(selectedArmor.getName() + " zirhini satin aldiniz !");
                this.getPlayer().setMoney(this.getPlayer().getMoney() - selectedArmor.getPrice());
                this.getPlayer().getInventory().setArmor(selectedArmor);
                System.out.println("Kalan paranız : " + this.getPlayer().getMoney());

            }
        }

    }
    public void buyWeapon(){
        System.out.print("Bir Silah Seciniz : ");
        int selectWeaponID = Location.scan.nextInt();
        while (selectWeaponID < 0 || selectWeaponID > Weapon.weapons().length){
            selectWeaponID = Location.scan.nextInt();
        }

        if(selectWeaponID != 0 ){
            Weapon selectedWeapon = Weapon.getWeaponObjByID(selectWeaponID);

            if(selectedWeapon != null){
                if(selectedWeapon.getPrice() > getPlayer().getMoney()){
                    System.out.println("Yeterli Miktarda Altinin Yok...");
                }else {
                    // Alışveriş Kısmı
                    System.out.println(selectedWeapon.getName() + " silahini satin aldiniz ");
                    int balance = this.getPlayer().getMoney() - selectedWeapon.getPrice();
                    this.getPlayer().setMoney(balance);
                    System.out.println("Kalan paranız : " + this.getPlayer().getMoney());
                    // System.out.println("Onceki Silahınızı : " + this.getPlayer().getInventory().getWeapon());
                    this.getPlayer().getInventory().setWeapon(selectedWeapon);

                }
            }
        }
    }
}
