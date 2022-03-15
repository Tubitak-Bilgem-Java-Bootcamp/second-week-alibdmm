
import java.util.Random;

public class BattleLocation extends Location{
    private Obstacle obstacle;
    private String award;
    private int maxObstacle;

    public BattleLocation(Player player, String name,Obstacle obstacle, String award, int maxObstacle) {
        super(player, name);
        this.obstacle = obstacle;
        this.award = award;
        this.maxObstacle = maxObstacle;
    }

    @Override
    public boolean onLocation() {
        int obsCaount = this.randomObstacleCount();
        SlowlyWrite.slowPrint("\t\tÞuannki konumunuz: "+ this.getName(), 5);
        SlowlyWrite.slowPrint("\t\tDikkatli Ol! Burada "+obsCaount+" tane "+this.getObstacle().getName()+ " Yaþýyor !",5, SlowlyWrite.RED_BRIGHT);
        System.out.print("\t\t\t"+SlowlyWrite.BLUE_BOLD+" <S>avaþ yada <K>aç    ");
        SlowlyWrite.setResetColor();
        String selectCase = input.nextLine().toUpperCase();

        if(selectCase.equals("S")){
            System.out.print("\t\t\t"+SlowlyWrite.RED_UNDERLINED+"Savaþ Baþlýyor");
            SlowlyWrite.slowPrint(" ...",200,SlowlyWrite.RED_BRIGHT);
            if(this.combat(obsCaount)){
                System.out.print("\t\t\t\t");
                SlowlyWrite.slowPrint("Tüm Düþmaný Yendiniz", 0, SlowlyWrite.GREEN_BACKGROUND);
                if(this.getObstacle().getId() == 4) {
                	this.afterW();
                }else {
                	this.setPlayerAward();
                }
                return  true;
            }
        }
        if(this.getPlayer().getHealth() <= 0){
            System.out.print("\t\t\t\t");
            SlowlyWrite.slowPrint("Öldünüz", 0, SlowlyWrite.RED_BACKGROUND);
            return false;
        }
        return true;
    }

    public boolean combat(int obsCount){
        for (int i= 0; i < obsCount; i++){
            this.getObstacle().setHealth(this.getObstacle().getOriginalHealth());
            playerStatus();
            this.obstacleStatus(i+1);
            int item = randomPlayerOrObstacle(); //ilk önce kimin vuracagýný random olarak alýr 2 deðer alýr %50
            while (this.getPlayer().getHealth() > 0 && this.getObstacle().getHealth() > 0){
                System.out.print("\n\t\t\t\t"+SlowlyWrite.BLUE_BRIGHT+"<V>ur yada <K>aç>:  ");
                String selectCombat = input.nextLine().toUpperCase();
                if(selectCombat.equals("V")){
                    if(item == 1){
                        SlowlyWrite.slowPrint("\n\t\t\t\t\tSiz Vurdunuz",0,SlowlyWrite.GREEN_BRIGHT);
                        obstacle.setHealth(this.obstacle.getHealth() - this.getPlayer().getTotalDamage());
                        afterHit();
                    }
                    if(this.getObstacle().getHealth() > 0){
                        SlowlyWrite.slowPrint("\n\t\t\t\t\tCanavar Size vurdu",0,SlowlyWrite.RED_BRIGHT);
                        int obstacleDamage = this.getObstacle().getDamage() - this.getPlayer().getInventory().getArmor().getBlock();
                        if(obstacleDamage < 0)
                            obstacleDamage = 0;
                        this.getPlayer().setHealth(this.getPlayer().getHealth() - obstacleDamage);
                        afterHit();
                    }
                    item = 1;
                }else{
                    return false;
                }
            }
            if(this.getObstacle().getHealth() < this.getPlayer().getHealth()){
                System.out.print("\t\t\t\t");
                SlowlyWrite.slowPrint("Düþmaný Yendiniz", 0, SlowlyWrite.GREEN_BACKGROUND);
                System.out.print("\t\t\t\t");
                if(this.getObstacle().getId() == 4) {
                	System.out.println("burada özel ödül bulunmaktadýr bölüm sonunda þansýnýza ne çýkarsa");
                }else {
	                SlowlyWrite.slowPrint(this.getObstacle().getAward()+ " para kazandýnýz !");
	                this.getPlayer().setMoney(this.getPlayer().getMoney() + this.getObstacle().getAward());
                }
                SlowlyWrite.slowPrint("\t\t\t\tGüncel paranýz: "+this.getPlayer().getMoney(), 0, SlowlyWrite.GREEN_BRIGHT);
            }
        }
        if(!(this.getPlayer().getHealth() <= 0))
            return true;
        return false;
    }

    private void afterHit() {
        SlowlyWrite.slowPrint("\t\t\t\t\tCanýnýz: "+this.getPlayer().getHealth(),2);
        SlowlyWrite.slowPrint("\t\t\t\t\t"+this.getObstacle().getName()+" Caný: "+this.getObstacle().getHealth(),2);

    }

    public void playerStatus() {
        SlowlyWrite.slowPrint("\n\t\t\t\tOyuncu Deðerleri",2, SlowlyWrite.PURPLE_BRIGHT);
        SlowlyWrite.slowPrint("\t\t\t------------------------------------------",15,1.6);
        SlowlyWrite.slowPrint("\t\t\t\t\tSaðlýk: "+this.getPlayer().getHealth(),5);
        SlowlyWrite.slowPrint("\t\t\t\t\tSilah: "+this.getPlayer().getInventory().getWeapon().getName(),5);
        SlowlyWrite.slowPrint("\t\t\t\t\tHasar: "+this.getPlayer().getTotalDamage(),5);
        SlowlyWrite.slowPrint("\t\t\t\t\tZýrh: "+this.getPlayer().getInventory().getArmor().getName(),5);
        SlowlyWrite.slowPrint("\t\t\t\t\tBloklama: "+this.getPlayer().getInventory().getArmor().getBlock(),5);
        SlowlyWrite.slowPrint("\t\t\t\t\tPara: "+this.getPlayer().getMoney(),5);
    }

    public void obstacleStatus(int i){
        SlowlyWrite.slowPrint("\n\t\t\t\t"+i+". "+this.getObstacle().getName()+" Deðerleri",2, SlowlyWrite.PURPLE_BRIGHT);
        SlowlyWrite.slowPrint("\t\t\t------------------------------------------",15,1.6);
        SlowlyWrite.slowPrint("\t\t\t\t\tSaðlýk: "+this.getObstacle().getHealth(),5);
        SlowlyWrite.slowPrint("\t\t\t\t\tHasar: "+this.getObstacle().getDamage(),5);
        SlowlyWrite.slowPrint("\t\t\t\t\tÖdül: "+this.getObstacle().getAward(),5);

    }
    
    public void afterW() {
    	if(this.obstacle.getId() == 4) {
    		int surpriseVal = this.randomSurpriseValue();
    		if(surpriseVal>0 && surpriseVal <= 15) {
    			System.out.println("silah kazandýnýz"+surpriseVal);
    			Weapon w;
    			int surpriseWeapon =this.randomSurpriseValue();
    			if(surpriseWeapon >= 1 && surpriseWeapon <= 20) {
    				System.out.println("Tüfek kazandýnýz");
    				w = Weapon.getWeaponObjById(2);
    			}else if (surpriseWeapon >= 21 && surpriseWeapon <= 50) {
    				System.out.println("Kýlýç kazandýnýz");
    				w = Weapon.getWeaponObjById(1);
    			}else {
    				System.out.println("Tabanca kazandýnýz");
    				w = Weapon.getWeaponObjById(0);
    			}
    			this.getPlayer().getInventory().setWeapon(w);
    		}else if(surpriseVal >=16 && surpriseVal <=30) {
    			System.out.println("Zýrh kazandýnýz" + surpriseVal);
    			Armor a;
    			int surpriseArmor =this.randomSurpriseValue();
    			if(surpriseArmor >= 1 && surpriseArmor <= 20) {
    				System.out.println("Aðýr zýrh kazandýnýz");
    				a = Armor.getArmorObjById(2);
    			}else if (surpriseArmor >= 21 && surpriseArmor <= 50) {
    				System.out.println("Orta Zýrh kazandýnýz");
    				a = Armor.getArmorObjById(1);
    			}else {
    				System.out.println("Hafif zýrh kazandýnýz");
    				a = Armor.getArmorObjById(0);
    			}
    			this.getPlayer().getInventory().setArmor(a);
    		}else if(surpriseVal >=16 && surpriseVal <=30) {
    			System.out.println("Para kazandýnýz"+surpriseVal);
    			int surprizeMoney = this.randomSurpriseValue();
    			int moneyVal;
    			if(surprizeMoney >= 1 && surprizeMoney <= 20) {
    				System.out.println("10 para kazandýnýz");
    				moneyVal = 10;
    			}else if (surprizeMoney >= 21 && surprizeMoney <= 50) {
    				System.out.println("5 para kazandýnýz");
    				moneyVal = 5;
    			}else {
    				System.out.println("1 para kazandýnýz");
    				moneyVal = 1;
    			}
    			this.getPlayer().setMoney(moneyVal + this.getPlayer().getMoney());
    		}else {
    			System.out.println("Hiç bir ödül kazanamadýnýz"+surpriseVal);
    		}
    	}
    }
    
    public int randomSurpriseValue() {
    	Random r = new Random();
    	return r.nextInt(100)+1;
    }
    
    public int randomObstacleCount(){
        Random r = new Random();
        return r.nextInt(this.getMaxObstacle())+1;
    }

    public int randomPlayerOrObstacle(){
        Random r = new Random();
        return r.nextInt(2)+1;
    }

    public void setPlayerAward(){
        String message;
        if(this.getAward().equals("food")) {
            this.getPlayer().getInventory().setFood(true);
            message = "Yemek";
        }
        else if(this.getAward().equals("water")) {
            this.getPlayer().getInventory().setWater(true);
            message ="Su";
        }
        else {
            this.getPlayer().getInventory().setFirewoord(true);
            message = "Odun";
        }
        SlowlyWrite.slowPrint("\t\t\t"+message+" ödülünü aldýnýz bir sonraki bölüme geçebilirsiniz", 2, SlowlyWrite.PURPLE_BRIGHT);

    }

    public Obstacle getObstacle() {
        return obstacle;
    }

    public void setObstacle(Obstacle obstacle) {
        this.obstacle = obstacle;
    }

    public String getAward() {
        return award;
    }

    public void setAward(String award) {
        this.award = award;
    }

    public int getMaxObstacle() {
        return maxObstacle;
    }

    public void setMaxObstacle(int maxObstacle) {
        this.maxObstacle = maxObstacle;
    }
}
