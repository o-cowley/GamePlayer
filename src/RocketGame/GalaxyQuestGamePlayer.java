package RocketGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GalaxyQuestGamePlayer extends JPanel implements ActionListener {
    public static final int SCREEN_WIDTH = 500;
    public static final int SCREEN_HEIGHT = 500;
    private JFrame mainFrame;

    private RocketShip ship;
    private int level;

    private Timer timer;
    private Timer alienTimer;
    private Timer powerUpTimer;

    public GalaxyQuestGamePlayer() {
        mainFrame = new JFrame();
        ship = new RocketShip(100,20);
        level = ship.getEnemyLevel();


        setFocusable(true);
        setSize(new Dimension(SCREEN_WIDTH,SCREEN_HEIGHT));
        addKey();
        initTimer();

        mainFrame.add(this);
        mainFrame.setResizable(false);


        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mainFrame.setSize(getSize());
        timer.start();
        alienTimer.start();
        repaint();
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);

    }

    private void addKey() {
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                ship.keyPressed(e);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                ship.keyReleased(e);
            }
        });
    }


    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBackground(Color.CYAN);
        //g.fillOval(ship.getX(), ship.getY(), 50, 20);
        g.drawImage(ship.getImage(), ship.getX(), ship.getY(),null);
        paintMissiles(g);
        paintEnemies(g);
        paintPowerUps(g);
        paintScore(g);
    }

    private void paintMissiles(Graphics g) {
        g.setColor(ship.getLevelColour());

        for (Missile m: ship.getMissiles()) {
            g.fillRect(m.getX(), m.getY(), Missile.WIDTH, Missile.HEIGHT);
        }
    }

    private void paintEnemies(Graphics g) {
        int level = ship.getEnemyLevel();
        switch (level) {
            case 1:
                g.setColor(Color.BLACK);
                break;
            case 2:
                g.setColor(Color.YELLOW);
                break;
            case 3:
                g.setColor(Color.RED);
                break;
        }

        for (Alien a: ship.getAliens()){
            g.fillRect(a.getX(), a.getY(), Alien.WIDTH, Alien.HEIGHT);
        }
    }

    private void paintPowerUps(Graphics g) {
        g.setColor(Color.YELLOW);
        for (PowerUp p: ship.getPowerUps()) {
            g.fillOval(p.getX(), p.getY(), p.getWidth(), p.getHeight());
        }
    }

    private void paintScore(Graphics g) {
        g.setColor(Color.BLACK);
        g.drawString("Score: " + ship.getScore(), SCREEN_WIDTH / 2 - 20, 30);
    }

    public void actionPerformed(ActionEvent e) {
        ship.move();
        ship.updateOthers();
        checkLevelProgression();
        repaint();
    }

    public void initTimer() {
        timer = new Timer(10, this);
        timer.setRepeats(true);

        setAlienTimer(2000);
        setPowerUpTimer(10000);
    }

    private void setAlienTimer(int delay) {
        alienTimer = new Timer(delay, e -> ship.addAlien());
        alienTimer.setRepeats(true);
        alienTimer.start();
    }

    private void setPowerUpTimer(int delay) {
        powerUpTimer = new Timer(delay, e -> ship.addPowerUp());
        powerUpTimer.setRepeats(true);
        powerUpTimer.start();
    }

    private void checkLevelProgression() {
        int newLevel = ship.getEnemyLevel();
        if (newLevel != this.level) {
            switch (newLevel) {
                case 2:
                    setAlienTimer(1000);
                    this.level = newLevel;
                    break;
                case 3:
                    setAlienTimer(750);
                    this.level = newLevel;
                    break;
            }
        }
        if(ship.getScore() > 5) {
            stopAllTimers();
            JOptionPane.showMessageDialog(mainFrame, "Congrats, You won!");
            mainFrame.dispose();
        }
    }

    private void stopAllTimers() {
        timer.stop();
        alienTimer.stop();
        powerUpTimer.stop();
    }
}
