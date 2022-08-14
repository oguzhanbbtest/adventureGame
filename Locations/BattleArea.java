package Locations;
import Enemy.Enemy;
import Game.Player;
import Item.Armor;
import Item.Weapon;

import java.util.Random;

public class BattleArea extends Location {
    private Enemy enemy;
    private String award;
    private int maxEnemy;


    public BattleArea(Player player, String name, Enemy enemy, String award,int maxEnemy) {
        super(player, name);
        this.enemy = enemy;
        this.award = award;
        this.maxEnemy = maxEnemy;
    }
    @Override
    public boolean onLocation() {
        int enemyNumber = this.randomEnemy();
        System.out.println("Suan Buradasiniz : " + this.getName());
        System.out.println("Dikkatli Ol Burada " + enemyNumber + " adet " + getEnemy().getName() + " var..");
        System.out.println("<F>ight or <E>scape");
        String selectOps = scan.nextLine();
        selectOps = selectOps.toUpperCase();

        if (selectOps.equals("F")){
            System.out.println("Mucadeleye Baslandi...");
            if (combat(enemyNumber)){
                System.out.println(this.getName() + " tüm düşmanları hakladınız ! ");
                return true;
            }
        }
        if (this.getPlayer().getHealth() <= 0 ){
            System.out.println("Kaybettiniz  ! ");
            return false;
        }
        return true;
    }

    public boolean combat(int maxEnemy){
        if(this.getEnemy().getName().equals("Snake")){
            this.getEnemy().setDamage(snakeDamage());
        }
        for(int i = 1; i <= maxEnemy ; i++){
            this.getEnemy().setHealth(this.getEnemy().getOrgHealth());
            playerStats();
            enemyStats(i);
            int battlechance = chance();
            while (this.getPlayer().getHealth() > 0 && this.getEnemy().getHealth() > 0 ){
                System.out.print("<V>ur veya <K>ac : ");
                String selectCombat = scan.nextLine().toUpperCase();

                if(selectCombat.equals("V")){
                    if (battlechance < 50) {
                        System.out.println("Siz vurdunuz !");
                        this.getEnemy().setHealth(this.getEnemy().getHealth() - this.getPlayer().getTotalDamage());
                        afterHit();
                        if (this.getEnemy().getHealth() > 0 ){
                            System.out.println();
                            System.out.println(getEnemy().getName() + "Size vurdu !");
                            int enemyDamage  = this.getEnemy().getDamage() - this.getPlayer().getInventory().getArmor().getBlock();
                            if(enemyDamage < 0 ){
                                enemyDamage = 0;
                            }
                            this.getPlayer().setHealth(this.getPlayer().getHealth() - enemyDamage);
                            afterHit();
                        }
                    }else{
                        if (this.getEnemy().getHealth() > 0 ){
                            System.out.println();
                            System.out.println(getEnemy().getName() + "Size vurdu !");
                            int enemyDamage  = this.getEnemy().getDamage() - this.getPlayer().getInventory().getArmor().getBlock();
                            if(enemyDamage < 0 ){
                                enemyDamage = 0;
                            }
                            this.getPlayer().setHealth(this.getPlayer().getHealth() - enemyDamage);
                            afterHit();
                        }
                        System.out.println("Siz vurdunuz !");
                        this.getEnemy().setHealth(this.getEnemy().getHealth() - this.getPlayer().getTotalDamage());
                        afterHit();
                    }
                }else {
                    return false;
                }
            }
            if(getEnemy().getHealth() < this.getPlayer().getHealth()){
                System.out.println("Düsmani Yendiniz !");

                if(getEnemy().getName().equals("Snake")){
                    drop();
                }else {
                    System.out.println(this.getEnemy().getAward() + " para kazandınız !");
                    this.getPlayer().setMoney(this.getPlayer().getMoney() + this.getEnemy().getAward());
                    if(this.getEnemy().getName().equals("Zombie")){
                        getPlayer().getInventory().setFood(1);
                        System.out.println("Yemek Kazandiniz");
                    }if(this.getEnemy().getName().equals("Vampire")){
                        getPlayer().getInventory().setWood(1);
                        System.out.println("Odun Kazandiniz");
                    }if(this.getEnemy().getName().equals("Bear")){
                        getPlayer().getInventory().setWater(1);
                        System.out.println("Su Kazandiniz");
                    }if(this.getEnemy().getName().equals("Snake")){
                    }
                }
                System.out.println("Guncel Paraniz " + this.getPlayer().getMoney());
            }
        }
        return false;
    }

    public void afterHit(){
        System.out.println(" Caniniz : " + this.getPlayer().getHealth());
        System.out.println(this.getEnemy().getName() + " Cani : " + this.getEnemy().getHealth());
        System.out.println("----------");
    }
    public void playerStats(){
        System.out.println("Player Details");
        System.out.println("==============");
        System.out.println("Health : " + this.getPlayer().getHealth());
        System.out.println("Weapon : " + this.getPlayer().getInventory().getWeapon().getName());
        System.out.println("Armor : " + this.getPlayer().getInventory().getArmor().getName());
        System.out.println("Block : " + this.getPlayer().getInventory().getArmor().getBlock());
        System.out.println("Damage : " + this.getPlayer().getTotalDamage());
        System.out.println("Money : " + this.getPlayer().getMoney());
        System.out.println();
    }

    public void enemyStats(int i){
        System.out.println(i + ". " +this.getEnemy().getName() + " Degerleri");
        System.out.println("======================================");
        System.out.println("Health : "+ this.getEnemy().getHealth());
        System.out.println("Damage : "+ this.getEnemy().getDamage());
        System.out.println("Money : " + this.getEnemy().getAward());
        System.out.println();
    }
    public int randomEnemy(){
        Random r = new Random();
        return r.nextInt(this.getMaxEnemy()) + 1 ;
    }

    public int chance(){
        Random r = new Random();
        return r.nextInt()*100;
    }

    public int getMaxEnemy() {
        return maxEnemy;
    }

    public void setMaxEnemy(int maxEnemy) {
        this.maxEnemy = maxEnemy;
    }

    public Enemy getEnemy() {
        return enemy;
    }

    public void setEnemy(Enemy enemy) {
        this.enemy = enemy;
    }

    public String getAward() {
        return award;
    }

    public void setAward(String award) {
        this.award = award;
    }

    public int snakeDamage(){
        Random random = new Random();
        return random.nextInt(3) + 3;
    }

    public void drop(){
        Random random = new Random();
        int chance = random.nextInt()*100;

        if(chance < 55){
            Random r = new Random();
            int itemchance = r.nextInt()*100;
            if(itemchance <= 30){
                Random rw = new Random();
                int weaponeChance = rw.nextInt()*100;
                if(weaponeChance <= 20){
                    getPlayer().getInventory().setWeapon(Weapon.getWeaponObjByID(3));
                    System.out.println(getPlayer().getInventory().getWeapon().getName()+ " Kazandiniz ..");
                }if(20 < weaponeChance && weaponeChance <= 50){
                    getPlayer().getInventory().setWeapon(Weapon.getWeaponObjByID(2));
                    System.out.println(getPlayer().getInventory().getWeapon().getName()+ " Kazandiniz ..");
                }if( 50 < weaponeChance){
                    getPlayer().getInventory().setWeapon(Weapon.getWeaponObjByID(1));
                    System.out.println(getPlayer().getInventory().getWeapon().getName()+ " Kazandiniz ..");
                }

            }if(30 < itemchance && itemchance <= 60){
                Random ra = new Random();
                int armorChance = ra.nextInt()*100;
                if(armorChance <= 20 ){
                    getPlayer().getInventory().setArmor(Armor.getArmorObjByID(3));
                    System.out.println(getPlayer().getInventory().getArmor().getName()+" Kazandiniz");
                }if(20 < armorChance && armorChance <= 50 ){
                    getPlayer().getInventory().setArmor(Armor.getArmorObjByID(2));
                    System.out.println(getPlayer().getInventory().getArmor().getName()+" Kazandiniz");
                }if(50 < armorChance){
                    getPlayer().getInventory().setArmor(Armor.getArmorObjByID(1));
                    System.out.println(getPlayer().getInventory().getArmor().getName()+" Kazandiniz");
                }
            }if (60 < itemchance){
                Random rg = new Random();
                int goldChance = rg.nextInt()*100;
                if(goldChance <= 20){
                    getPlayer().setMoney(getPlayer().getMoney() + 10);
                    System.out.println("10 Gold Kazandiniz");
                }if(20 < goldChance && goldChance <= 50){
                    getPlayer().setMoney(getPlayer().getMoney() + 5);
                    System.out.println("5 Gold Kazandiniz");
                }if(50 < goldChance){
                    getPlayer().setMoney(getPlayer().getMoney() + 1);
                    System.out.println("1 Gold Kazandiniz");
                }
            }
        }else{
            System.out.println("Hic bir sey kazanamadiniz..");
        }
    }
}
