package TicTacToe;

import TicTacToe.exceptions.PositionTakenException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GameBoard extends JPanel {
    private static final int GAME_WIDTH = 500;
    private static final int GAME_HEIGHT = GAME_WIDTH;

    private final int spaceSide = GAME_WIDTH / 3 - 20;

    private GameSystem game;
    private JFrame frame;


    public GameBoard() {
        game = new GameSystem(GAME_WIDTH, spaceSide);
        game.setPlayers(Color.RED, Color.BLUE);

        addMouseListener(new Clicks());
        setSize(GAME_WIDTH, GAME_HEIGHT);
        setLayout(new GridLayout(3, 3));

        repaint();

        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        frame.add(this);
        frame.setSize(500,520);
        frame.setVisible(true);
    }

    public void paint(Graphics g) {
        paintBackDrop(g);
        paintXs(g);
    }

    private void paintBackDrop(Graphics g) {
        setBackground(Color.CYAN);
        g.setColor(Color.BLACK);
        g.drawLine(GAME_WIDTH / 3, 0, GAME_WIDTH / 3, GAME_HEIGHT);
        g.drawLine(2 * GAME_WIDTH / 3, 0, 2 * GAME_WIDTH / 3, GAME_HEIGHT);
        g.drawLine(0, GAME_HEIGHT / 3, GAME_WIDTH, GAME_HEIGHT / 3);
        g.drawLine(0, 2 * GAME_HEIGHT / 3, GAME_WIDTH, 2 * GAME_HEIGHT / 3);
    }

    private void paintXs(Graphics g) {
        int i = 1;
        for (PlayingSquares p: game) {
            if (game.isTaken(i)) {
                g.setColor(game.getSquareColour(i));
                g.fillOval(p.getxPos(), p.getyPos(), p.getWidth(), p.getHeight());
            }
            i++;
        }
    }

    private class Clicks extends MouseAdapter {

        public Clicks() {
        }

        @Override
        public void mousePressed(MouseEvent e) {
            int xMouse = e.getX();
            int yMouse = e.getY();
            for (PlayingSquares p: game) {
                if ((xMouse > p.getxPos() && xMouse < p.getxPos() + p.getWidth()) && (yMouse > p.getyPos()
                        && yMouse < p.getyPos() + p.getHeight())) {
                    try {

                        game.placeToken(p.getSquareLabel());
                        if (game.checkWin()) {
                            System.out.println("Yay you won!");
                            System.exit(0);
                        }
                        game.nextTurn();
                        repaint();
                        break;
                    } catch (PositionTakenException pe) {
                        System.out.println("nah bro");
                    }
                }
            }
        }
    }
}
