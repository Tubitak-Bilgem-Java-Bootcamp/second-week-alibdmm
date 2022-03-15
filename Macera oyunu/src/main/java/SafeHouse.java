public class SafeHouse extends NormalLocation{
    public SafeHouse(Player player) {
        super(player, "Güvenli ev");
    }

    @Override
    public boolean onLocation(){
        SlowlyWrite.slowPrint("<-------------------> Güvenli Evdesiniz <------------------->", 5);
        SlowlyWrite.slowPrint("\t\t\t\tCanýnýz yenilendi !\t\t\t\t", 5, SlowlyWrite.GREEN_BACKGROUND);
        this.getPlayer().setHealth(this.getPlayer().getOriginalHealt());
        if(this.getPlayer().getInventory().isFirewoord() && this.getPlayer().getInventory().isFood() && this.getPlayer().getInventory().isWater()) {
            SlowlyWrite.slowPrint("\t\t\t\tOyunu baþaralý bir þekilde tamamladýnýz !\t\t\t\t", 5, SlowlyWrite.GREEN_BACKGROUND);
            return false;
        }
        return super.onLocation();
    }
}
