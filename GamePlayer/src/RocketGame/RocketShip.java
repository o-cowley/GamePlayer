package RocketGame;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class RocketShip extends Sprite {
    private final int speed = 2;
    private final int LEVEL_UP = 10;

    private boolean left;
    private boolean right;
    private boolean up;
    private boolean down;

    private List<Missile> missiles;
    private Enemies enemies;
    private Consumables powerUps;

    private int enemyLevel;
    private int gunLevel;
    private int score;


    public RocketShip(int x, int y) {
        super(x, y);

        left = false;
        right = false;
        up = false;
        down = false;

        enemyLevel = 1;
        gunLevel = 1;
        score = 0;

        missiles = new ArrayList<>();
        powerUps = new Consumables();
        enemies = new Enemies();
        initImage();
    }

    public void initImage() {
        this.loadImage("src/RocketGame/Picture/nightraiderfixed.png");
        setImageSize(50,30);
    }

    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_SPACE) {
            fireMissile();
        }
        if (keyCode == KeyEvent.VK_LEFT) {
            left = true;
        }
        if (keyCode == KeyEvent.VK_RIGHT) {
            right = true;
        }
        if (keyCode == KeyEvent.VK_UP) {
            up = true;
        }
        if (keyCode == KeyEvent.VK_DOWN) {
            down = true;
        }
    }

    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();

        if (keyCode == KeyEvent.VK_LEFT) {
            left = false;
        }
        if (keyCode == KeyEvent.VK_RIGHT) {
            right = false;
        }
        if (keyCode == KeyEvent.VK_UP) {
            up = false;
        }
        if (keyCode == KeyEvent.VK_DOWN) {
            down = false;
        }
    }

    public void move() {
        if (left && canMoveLeft()) {
            if (!right) {
                xPos -= speed;
            }
        }
        if (right && canMoveRight()) {
            if (!left) {
                xPos += speed;
            }
        }
        if (up && canMoveUp()) {
            if (!down) {
                yPos -= speed;
            }
        }
        if (down && canMoveDown()) {
            if (!up) {
                yPos += speed;
            }
        }
    }

    private boolean canMoveLeft() {
        return !(xPos <= 0);
    }

    private boolean canMoveRight() {
        return !(xPos + width >= GameScreen.SCREEN_WIDTH);
    }

    private boolean canMoveUp() {
        return !(yPos <= 0);
    }

    private boolean canMoveDown() {
        return !(yPos + height >= GameScreen.SCREEN_HEIGHT - 20);
    }

    private void fireMissile() {
        switch (gunLevel) {
            case 1:
                fireMissileLevelOne();
                break;
            case 2:
                fireMissileLevelTwo();
                break;
            case 3:
                fireMissileLevelThree();
                break;
        }
    }

    private void fireMissileLevelOne() {
        Missile missile = new Missile((xPos + width), (yPos + (height / 2) - (Missile.HEIGHT / 2)));
        missiles.add(missile);
    }

    private void fireMissileLevelTwo() {
        Missile missile1 = new Missile((xPos + width), (yPos + (height / 3) - (Missile.HEIGHT / 2)));
        Missile missile2 = new Missile((xPos + width), (yPos + 2 * (height / 3) - (Missile.HEIGHT / 2)));
        missiles.add(missile1);
        missiles.add(missile2);
    }

    private void fireMissileLevelThree() {
        Missile missile1 = new Missile((xPos + width), (yPos - (Missile.HEIGHT / 2)));
        Missile missile2 = new Missile((xPos + width), (yPos + (height / 2) - (Missile.HEIGHT / 2)));
        Missile missile3 = new Missile((xPos + width), (yPos + height - (Missile.HEIGHT / 2)));
        missiles.add(missile1);
        missiles.add(missile2);
        missiles.add(missile3);
    }

    public Color getLevelColour() {
        switch (gunLevel) {
            case 1:
                return Color.RED;
            case 2:
                return Color.GREEN;
            case 3:
                return Color.BLACK;
        }
        return null;
    }

    public void addAlien() {
        enemies.addEnemy();
    }

    public void addPowerUp() {
        powerUps.addPowerUp();
    }

    public void updateOthers() {
        updateMissiles();
        updateEnemies();
        updatePowerUps();
        checkCollisions();
    }

    public void updateMissiles() {
        Iterator<Missile> itr = missiles.iterator();
        while (itr.hasNext()) {
            Missile m = itr.next();
            m.move();
            if (!m.isVisible()) {
                itr.remove();
            }
        }
    }

    public void updateEnemies() {
        Iterator<Alien> itr = enemies.iterator();
        while (itr.hasNext()) {
            Alien a = itr.next();
            a.move();
            if (!a.isVisible()) {
                itr.remove();
            }
        }
    }

    public void updatePowerUps() {
        Iterator<PowerUp> itr = powerUps.iterator();
        while (itr.hasNext()) {
            PowerUp p = itr.next();
            p.countdownLife();
            if (!p.isVisible()) {
                itr.remove();
            }
        }
    }

    private void checkCollisions() {
        ArrayList<Alien> aToRemove = new ArrayList<>();
        ArrayList<Missile> mToRemove = new ArrayList<>();
        ArrayList<PowerUp> pToRemove = new ArrayList<>();
        for (Alien a: enemies) {

            for (Missile m: missiles) {


                if (m.collision(a)){
                    aToRemove.add(a);
                    mToRemove.add(m);

                    score++;
                    if (score > (LEVEL_UP * enemyLevel) && enemyLevel < 3) {
                        enemyLevel++;
                    }
                }
            }
        }

        for (PowerUp p: powerUps) {
            if (p.collision(this)) {
                pToRemove.add(p);
                if (gunLevel < 3) {
                    gunLevel++;
                }
            }
        }

        powerUps.removeHit(pToRemove);
        missiles.removeAll(mToRemove);
        enemies.removeHit(aToRemove);
    }

    public Enemies getAliens() {
        return enemies;
    }

    public List<Missile> getMissiles() {
        return missiles;
    }

    public Consumables getPowerUps() {
        return powerUps;
    }

    public int getScore() {
        return score;
    }

    public int getEnemyLevel() {
        return enemyLevel;
    }
}
