package Game;
import java.util.Scanner;
import Character.GameChar;
import Character.Samurai;
import Character.Archer;
import Character.Knight;

public class Player{

    protected int damage;
    protected int health;
    private int orgHealth;
    protected int money;
    protected String name;
    protected String charName;
    protected Scanner scan = new Scanner(System.in);
    protected Inventory inventory;


    public Player(String name) {
        this.name = name;
        this.inventory = new Inventory();
    }
    public void selectChar(){
        GameChar[] charlist = {new Samurai(), new Archer(), new Knight()};
        System.out.println("--------------------------------------------");
        for (GameChar gameChar : charlist){
            System.out.println("ID : " + gameChar.getId()+
                    "\t Karakter : "+ gameChar.getName() +
                    "\t Damage : "+gameChar.getDamage()+
                    "\t healthy : " + gameChar.getHealthy()+
                    "\t Money : "+gameChar.getMoney());
        }System.out.println("--------------------------------------------");
        System.out.println("Lutfen Bir Karakter Seciniz !");
        int selectChar = scan.nextInt();

        switch (selectChar){
            case 1:
                inItPlayer(new Samurai());
                break;
            case 2:
                inItPlayer(new Knight());
                break;
            case 3:
                inItPlayer(new Archer());
                break;
            default:
                inItPlayer(new Samurai());
                break;
        }
        System.out.println("Karakter : "+ this.getCharName() +
                "\t Damage : "+this.getDamage()+
                "\t healthy : " + this.getHealth()+
                "\t Money : "+this.getMoney());
    }

    public void inItPlayer(GameChar gameChar){
        this.setDamage(gameChar.getDamage());
        this.setHealth(gameChar.getHealthy());
        this.setOrgHealth(gameChar.getHealthy());
        this.setMoney(gameChar.getMoney());
        this.setCharName(gameChar.getName());
    }
    public void printInfo(){
        System.out.println("Weapon : "+ this.getInventory().getWeapon().getName() + "  |" +
                "\t Armor : " + this.getInventory().getArmor().getName() + "  |" +
                "\t Block : " + this.getInventory().getArmor().getBlock() + "  |" +
                "\t Damage : "+ this.getDamage() + "  |" +
                "\t healthy : " + this.getHealth() + "  |" +
                "\t Money : "+this.getMoney());

    }
    public void printInventory(){
        System.out.println();
        System.out.println("Yemek : " + this.getInventory().getFood());
        System.out.println("Odun : " + this.getInventory().getWood());
        System.out.println("Su : " + this.getInventory().getWater());
        System.out.println();
    }
    public int getTotalDamage(){
        return damage + this.getInventory().getWeapon().getDamage();
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        if(health < 0){
            health = 0;
        }
        this.health = health;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCharName() {
        return charName;
    }

    public void setCharName(String charName) {
        this.charName = charName;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public int getOrgHealth() {
        return orgHealth;
    }

    public void setOrgHealth(int orgHealth) {
        this.orgHealth = orgHealth;
    }


}
