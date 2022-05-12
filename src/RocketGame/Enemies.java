package RocketGame;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class Enemies implements Iterable<Alien>{
    private static final int SCREEN_HEIGHT = 500;

    private Random rn;

    private ArrayList<Alien> aliens;

    public Enemies() {
        aliens = new ArrayList<>();
        rn = new Random();
    }

    public void addEnemy() {
        int max = SCREEN_HEIGHT - (Alien.HEIGHT * 3);
        int next = rn.nextInt(max) + Alien.HEIGHT;
        Alien alien = new Alien(next);

        aliens.add(alien);
    }

    public void removeHit(List<Alien> list) {
        aliens.removeAll(list);
    }

    @Override
    public Iterator<Alien> iterator() {
        return aliens.iterator();
    }
}
