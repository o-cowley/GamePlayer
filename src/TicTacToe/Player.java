package TicTacToe;

import java.awt.*;
import java.util.Objects;

public class Player {
    private Color colour;

    public Player(Color colour) {
        this.colour = colour;
    }

    public Color getColour() {
        return colour;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(colour, player.colour);
    }

    @Override
    public int hashCode() {
        return Objects.hash(colour);
    }
}
