public class SafeHouse extends NormalLocation{
    public SafeHouse(Player player) {
        super(player, "G�venli ev");
    }

    @Override
    public boolean onLocation(){
        SlowlyWrite.slowPrint("<-------------------> G�venli Evdesiniz <------------------->", 5);
        SlowlyWrite.slowPrint("\t\t\t\tCan�n�z yenilendi !\t\t\t\t", 5, SlowlyWrite.GREEN_BACKGROUND);
        this.getPlayer().setHealth(this.getPlayer().getOriginalHealt());
        if(this.getPlayer().getInventory().isFirewoord() && this.getPlayer().getInventory().isFood() && this.getPlayer().getInventory().isWater()) {
            SlowlyWrite.slowPrint("\t\t\t\tOyunu ba�aral� bir �ekilde tamamlad�n�z !\t\t\t\t", 5, SlowlyWrite.GREEN_BACKGROUND);
            return false;
        }
        return super.onLocation();
    }
}
