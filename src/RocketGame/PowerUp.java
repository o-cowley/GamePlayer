package RocketGame;

public class PowerUp extends Sprite{
    private final int LIFESPAN = 500;
    public static final int WIDTH = 10;
    public static final int HEIGHT = 10;

    private int life;


    public PowerUp(int x, int y) {
        super(x, y);
        setSize(WIDTH,HEIGHT);

        life = LIFESPAN;
    }

    public void countdownLife() {
        if (life <= 1) {
            setVisible(false);
        } else {
            life--;
        }
    }
}
