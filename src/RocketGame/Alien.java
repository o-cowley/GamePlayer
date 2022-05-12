package RocketGame;

public class Alien extends Sprite {
    private static final int SPEED = 2;
    private static final int ySPEED = 3;
    private static final int SCREEN_WIDTH = 500;
    private static final int CYCLE = 10;

    protected static final int HEIGHT = 20;
    protected static final int WIDTH = 20;

    private int yCounter;

    private boolean dy;


    public Alien(int y) {
        super(SCREEN_WIDTH - WIDTH, y);
        this.setSize(WIDTH, HEIGHT);

        yCounter = 0;
        dy = true;
    }

    public void move() {
        xPos -= SPEED;
        if (dy) {
            yPos += ySPEED;
        } else {
            yPos -= ySPEED;
        }
        tickCounter();

        if (xPos < 0) {
            setVisible(false);
        }
    }

    private void tickCounter() {
        if (yCounter == CYCLE) {
            yCounter = 0;
            dy = !dy;
        } else {
            yCounter++;
        }
    }
}
