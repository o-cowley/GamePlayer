package Handler;

import RocketGame.GameScreen;
import TicTacToe.GameBoard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RunGame extends JFrame {
    JPanel panel;
    JButton rocket;
    JButton ttt;

    public RunGame() {
        panel = new JPanel();
        panel.setLayout(new FlowLayout());
        initButtons();
        panel.setSize(panel.getPreferredSize());

        add(panel);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setVisible(true);
    }

    private void initButtons() {
        rocket = new JButton("Play Rocket Game");
        rocket.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new GameScreen();
            }
        });
        rocket.setSize(rocket.getPreferredSize());
        ttt = new JButton("Play TicTacToe...sort of");
        ttt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new GameBoard();
            }
        });
        ttt.setSize(ttt.getPreferredSize());

        panel.add(rocket);
        panel.add(ttt);
    }

    public static void main(String[] args) {
        new RunGame();
    }

}
