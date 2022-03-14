package TicTacToe;

import TicTacToe.exceptions.PositionTakenException;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class GameSystem implements Iterable<PlayingSquares>{
    private Map<Integer, Player> playerMap;
    private Player player1;
    private Player player2;
    private Player activePlayer;

    private ArrayList<ArrayList<Integer>> winUnits;
    private ArrayList<PlayingSquares> squares;

    public GameSystem(int gameWidth, int spaceSide) {
        initPlayerMap();
        winUnits = new ArrayList<>();
        initUnits();
        initPoints(gameWidth, spaceSide);
    }

    private void initPoints(int gameWidth, int spaceSide) {
        squares = new ArrayList<>();
        int count = 1;
        for (int i = 0; i < 3; i ++) {
            int xPos = 10;
            int yPos = i * gameWidth / 3 + 10;
            PlayingSquares p = new PlayingSquares(xPos, yPos, spaceSide, spaceSide , count);
            squares.add(p);
            count++;
            xPos +=gameWidth / 3;
            p = new PlayingSquares(xPos, yPos, spaceSide, spaceSide, count);
            count++;
            squares.add(p);
            xPos += gameWidth / 3;
            p = new PlayingSquares(xPos, yPos, spaceSide, spaceSide, count);
            squares.add(p);
            count++;
        }
    }

    public void setPlayers(Color player1Colour, Color player2Colour) {
        player1 = new Player(player1Colour);
        player2 = new Player(player2Colour);
        activePlayer = player1;
    }

    private void initPlayerMap() {
        playerMap = new HashMap<>();
        for (int i = 1; i <= 9; i++) {
            playerMap.put(i, null);
        }
    }

    private void initUnits() {
        ArrayList<Integer> toAdd;
        for (int i = 1; i <= 3; i++) {
            toAdd = new ArrayList<>();
            for (int j = i; j <= (i + 6); j += 3) {
                toAdd.add(j);
            }
            winUnits.add(toAdd);

        }
        for (int i = 1; i <= 7; i += 3) {
            toAdd = new ArrayList<>();
            for (int j = i; j <= (i + 3); j++) {
                toAdd.add(j);
            }
            winUnits.add(toAdd);
        }
        addDiagonals();
    }

    private void addDiagonals() {
        ArrayList<Integer> toAdd = new ArrayList<>();
        toAdd.add(1);
        toAdd.add(5);
        toAdd.add(9);
        winUnits.add(toAdd);
        toAdd = new ArrayList<>();
        toAdd.add(3);
        toAdd.add(5);
        toAdd.add(7);
        winUnits.add(toAdd);
    }

    public void nextTurn() {
        if (activePlayer == player1) {
            activePlayer = player2;
        } else {
            activePlayer = player1;
        }
    }

    public void placeToken(int position) throws PositionTakenException {
        if (isTaken(position)) {
            throw new PositionTakenException();
        } else {
            playerMap.put(position, activePlayer);
        }
    }

    public boolean checkWin() {
        for (ArrayList<Integer> l: winUnits) {
            if ((playerMap.get(l.get(0))) != null
            && (playerMap.get(l.get(0))) == (playerMap.get(l.get(1))) &&
                    (playerMap.get(l.get(0))) == (playerMap.get(l.get(2)))
            && (playerMap.get(l.get(1))) == (playerMap.get(l.get(2)))) {
                return true;
            }
        }
        return false;
    }

    public boolean isTaken(int position) {
        return playerMap.get(position) != null;
    }

    public Color getSquareColour(int position) {
        return playerMap.get(position).getColour();
    }

    @Override
    public Iterator<PlayingSquares> iterator() {
        return squares.iterator();
    }
}
