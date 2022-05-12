package Handler;

import ReflexGames.Catch;
import ReflexGames.Reflex;
import RocketGame.GalaxyQuestGamePlayer;
import TicTacToe.TicTacToeGamePlayer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RunGame extends JFrame {
    JPanel panel;
    JButton rocket;
    JButton ttt;
    JButton CatchGame;
    JButton ReflexGame;

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
                new GalaxyQuestGamePlayer();
            }
        });
        rocket.setSize(rocket.getPreferredSize());
        ttt = new JButton("Play TicTacToe...sort of");
        ttt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TicTacToeGamePlayer();
            }
        });
        ttt.setSize(ttt.getPreferredSize());

        CatchGame = new JButton("See if you can catch the ball!");
        CatchGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Catch();
            }
        });
        CatchGame.setSize(CatchGame.getPreferredSize());

        ReflexGame = new JButton("How fast are your clicks? (Probably need a mouse)");
        ReflexGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Reflex();
            }
        });
        ReflexGame.setSize(ReflexGame.getPreferredSize());

        panel.add(rocket);
        panel.add(ttt);
        panel.add(CatchGame);
        panel.add(ReflexGame);
    }

    public static void main(String[] args) {
        new RunGame();
    }

}
