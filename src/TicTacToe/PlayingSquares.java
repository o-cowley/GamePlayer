package TicTacToe;

public class PlayingSquares {
    private int xPos;
    private int yPos;
    private int width;
    private int height;
    private int squareLabel;



    public PlayingSquares(int x, int y, int w, int h, int label) {
        xPos = x;
        yPos = y;
        width = w;
        height = h;
        squareLabel = label;
    }
    
    public int getxPos() {
        return xPos;
    }

    public int getyPos() {
        return yPos;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getSquareLabel() {
        return squareLabel;
    }
}
