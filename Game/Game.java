package Game;

import Locations.*;

import java.util.Scanner;

public class Game {
    private final Scanner scan = new Scanner(System.in);
    public void start(){
        System.out.println(" Macera Oyununa Hosgeldiniz");
        System.out.print(" Lutfen Bir Isim Giriniz : ");
        boolean win = false;

        String playerName = scan.nextLine();

        Player player = new Player(playerName);
        System.out.println(" Sayin "+player.getName()+" Mucadeleye Hosgeldiniz..");
        System.out.println(" Karakter Seciminizi Yapiniz..");
        System.out.println();
        System.out.println(" Karakterler");
        player.selectChar();

        Location location = null;

        while(true){
            player.printInfo();
            printMenu();
            int selectLoc = scan.nextInt();
            if (selectLoc == 3 && player.getInventory().getFood() == 1){
                selectLoc = 0;
                System.out.println("   ##### GITMEK ISTEDIGINIZ HARITAKI GOREVI TAMAMLADINIZ ##### ");
                System.out.println();
                System.out.println("          ###Safe House'a yonlendiriliyorsunuz###");
            }if (selectLoc == 4 && player.getInventory().getWood() == 1){
                selectLoc = 0;
                System.out.println("   ##### GITMEK ISTEDIGINIZ HARITAKI GOREVI TAMAMLADINIZ ##### ");
                System.out.println();
                System.out.println("          ###Safe House'a yonlendiriliyorsunuz###");
            }if (selectLoc == 5 && player.getInventory().getWater() == 1){
                selectLoc = 0;
                System.out.println("   ##### GITMEK ISTEDIGINIZ HARITAKI GOREVI TAMAMLADINIZ ##### ");
                System.out.println();
                System.out.println("          ### Safe House'a yonlendiriliyorsunuz ###");
            }

            switch (selectLoc) {
                case 0 -> location = null;
                case 1 -> {
                    if (player.getInventory().getFood() == 1 && player.getInventory().getWood() == 1 && player.getInventory().getWater() == 1) {
                        win = true;
                        break;
                    }
                    location = new SafeHouse(player);
                }
                case 2 -> location = new ToolStore(player);
                case 3 -> location = new Cave(player);
                case 4 -> location = new Forest(player);
                case 5 -> location = new River(player);
                case 6 -> location = new Coal(player);
                default -> System.out.println("Lutfen Gecerli Bir Deger Giriniz");
            }
            if(location == null){
                System.out.println("Yine Bekleriz ....");
                break;
            }
            if(win){
                System.out.println();
                System.out.println();
                System.out.println();
                System.out.println();
                System.out.println(   "   ############################## MISSION COMPLETED #########################");
                System.out.println("########################## TUM HARİTALARDAKI ÖZEL GANİMETLERİ TOPLADIN #########################");
                System.out.println("            ######################### YEMEGINI PISIRDIN ###########################");
                break;
            }
            if(!location.onLocation()){
                System.out.println();
                System.out.println();
                System.out.println("===== GAME OVER =====");
                break;
            }
        }
    }
    public void printMenu(){
        System.out.println();

        System.out.println(" ######## BOLGELER ########");
        System.out.println();

        System.out.println(" 1 - SafeHouse -->  Burasi sizin icin guvenli bir ev,dusman yoktur !");
        System.out.println(" 2 - Store     -->  Burdan Silah ve Zirh  satin alabilirsiniz");
        System.out.println(" 3 - Magara    -->  Odul <Yemek> Karanlik, Serin ve Zombie dolu bir magara");
        System.out.println(" 4 - Orman     -->  Odul <Odun> Vampirlere dikkat et");
        System.out.println(" 5 - Nehir     -->  Odul <Su>  Ayilar biraz seni zorlayabilir");
        System.out.println(" 6 - Maden     -->  Odul <Esya> Yilanlara Dikkat et");

        System.out.println(" 0 - Cikis Yap --> Oyunu Sonlandir");
        System.out.println(" Lutfen gitmek istediginiz bolgeyi seciniz : ");
    }
}
