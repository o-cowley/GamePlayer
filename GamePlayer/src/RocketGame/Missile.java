package RocketGame;

public class Missile extends Sprite{
    private final int missileSpeed = 3;
    private final int SCREEN_WIDTH = 500;

    protected static final int HEIGHT = 5;
    protected static final int WIDTH = 20;


    public Missile(int x, int y) {
        super(x, y);
        initImage();
    }

    public void initImage() {
        loadImage("");
        this.setImageSize(WIDTH,HEIGHT);
    }


    public void move() {
        this.xPos = xPos + missileSpeed;

        if (xPos > SCREEN_WIDTH) {
            setVisible(false);
        }
    }



}
