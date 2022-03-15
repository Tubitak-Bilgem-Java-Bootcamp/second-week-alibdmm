import java.util.Scanner;

public class Game {
    private Scanner input = new Scanner(System.in);

    public void start(){
        System.out.println("Macera Oyununa Ho� Geldiniz");
        System.out.println("L�tfe Bir �sim Giriniz: ");
        //String palayerName = input.nextLine();

        Player player =new Player("AAli");
        SlowlyWrite.slowPrint("Say�n "+player.getName() + " ho�geldiniz", 10);
        player.selectChar();

        Location location = null;
        //SlowlyWrite.slowPrint("\n\nB�lgeler Getiriliyor ...", 5);

        while (true){
            if(player.getInventory().isWater() && player.getInventory().isFood() && player.getInventory().isFirewoord()){
                SlowlyWrite.slowPrint("\t\t"+SlowlyWrite.RED_BRIGHT+"T�m �d�lleri toplad�n�z g�venli eve d�n�p oyunu bitirebilirsiniz!", 2);
                location = new SafeHouse(player);
            }
            SlowlyWrite.slowPrint(player.playerInfo(), 5);
            SlowlyWrite.slowPrint("\n------------------ B�lgeler ------------------", 5);
            SlowlyWrite.slowPrint("1 - G�venli Ev --> Buras� sizin i�in g�veli bir aland�r, d��man yoktur !", 5, SlowlyWrite.PURPLE);
            SlowlyWrite.slowPrint("2 - Ma�aza --> Silav ve z�rh sat�n alabilirsiniz !", 5, SlowlyWrite.PURPLE);
            SlowlyWrite.slowPrint("3 - Ma�ara --> Canavar ile sava� yeme�i kazan", 5, SlowlyWrite.PURPLE);
            SlowlyWrite.slowPrint("4 - Orman --> Vampir ile sava� odunlar� topla", 5, SlowlyWrite.PURPLE);
            SlowlyWrite.slowPrint("5 - Nehir --> Ay� ile sava� suyu kazan", 5, SlowlyWrite.PURPLE);
            SlowlyWrite.slowPrint("6 - Maden --> Y�lan ile sava� s�rpriz hediyeyi kazan", 5, SlowlyWrite.PURPLE);
            SlowlyWrite.slowPrint("0 - ��k�� Yap�n�z !", 5, SlowlyWrite.RED);

            System.out.print("\n"+SlowlyWrite.GREEN+"L�tfen gitmek istedi�iniz b�lgeyi se�iniz: ");
            SlowlyWrite.setResetColor();
            int selectLoc = input.nextInt();
            boolean flag = true;
            switch (selectLoc){
                case 0:
                    location = null;
                    break;
                case 1:
                    location = new SafeHouse(player);
                    break;
                case 2:
                    location = new ToolStore(player);
                    break;
                case 3:
                    if(player.getInventory().isFood()) {
                        SlowlyWrite.slowPrint("\t\tBu b�l�mdeki t�m �d�lleri ald�n�z l�tfen ba�ka bir b�l�m se�iniz!", 2, SlowlyWrite.WHITE_BRIGHT);
                        flag = false;
                        break;
                    }
                    location = new Cave(player);
                    break;
                case 4:
                    if(player.getInventory().isFirewoord()){
                        SlowlyWrite.slowPrint("\t\tBu b�l�mdeki t�m �d�lleri ald�n�z l�tfen ba�ka bir b�l�m se�iniz!", 2, SlowlyWrite.WHITE_BRIGHT);
                        flag = false;
                    }
                    location = new Forest(player);
                    break;
                case 5:
                    if(player.getInventory().isWater()){
                        SlowlyWrite.slowPrint("\t\tBu b�l�mdeki t�m �d�lleri ald�n�z l�tfen ba�ka bir b�l�m se�iniz!", 2, SlowlyWrite.WHITE_BRIGHT);
                        flag = false;
                    }
                    location = new River(player);
                    break;
                case 6:
                	location = new MiningSite(player);
                	break;
                default:
                    location = new SafeHouse(player);
            }
            if(location == null){
                SlowlyWrite.slowPrint("GAME OVER !", 5, SlowlyWrite.RED_BACKGROUND);
                break;
            }
            if (flag && !location.onLocation()){
                SlowlyWrite.slowPrint("GAME OVER !", 5, SlowlyWrite.RED_BACKGROUND);
                break;
            }
        }
    }
}
