import java.util.Random;

public class Snake extends Obstacle{
    public Snake() {
        super(4,"Yýlan", Snake.getRandomDamage(), 12, 0);
    }

    public static  int getRandomDamage(){
        Random r = new Random();
        return r.nextInt(3)+3;
    }
}
