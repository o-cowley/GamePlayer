package RocketGame;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class Consumables implements Iterable<PowerUp>{
    private List<PowerUp> powerUps;

    private Random rnd;

    public Consumables() {
        rnd = new Random();
        powerUps = new ArrayList<>();
    }

    public void addPowerUp() {
        int x = rnd.nextInt(GameScreen.SCREEN_WIDTH - PowerUp.WIDTH);
        int y = rnd.nextInt(GameScreen.SCREEN_HEIGHT - PowerUp.HEIGHT);
        powerUps.add(new PowerUp(x, y));
    }

    public void removeHit(List<PowerUp> toRemove) {
        powerUps.removeAll(toRemove);
    }

    @Override
    public Iterator<PowerUp> iterator() {
        return powerUps.iterator();
    }
}
