package RocketGame;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;

public abstract class Sprite {

    protected int xPos;
    protected int yPos;

    protected int width;
    protected int height;

    protected Image image;
    protected boolean visible;

    public Sprite(){
        visible = true;
    }

    public Sprite(int x, int y) {
        xPos = x;
        yPos = y;
        visible = true;
    }

    protected void loadImage(String imageName) {
        ImageIcon ii = new ImageIcon(imageName);
        image = ii.getImage();

    }

    protected void setImageSize(int width, int height) {
        this.width = width;
        this.height = height;
        image = image.getScaledInstance(width,height,Image.SCALE_AREA_AVERAGING);
    }

    public boolean collision(Sprite s) {
        Rectangle2D oneRect = new Rectangle(s.getX(), s.getY(), s.getWidth(), s.getHeight());
        Rectangle2D otherRect = new Rectangle(getX(), getY(), getWidth(), getHeight());
        return otherRect.intersects(oneRect);
    }

    public int getX() {
        return xPos;
    }

    public int getY() {
        return yPos;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Image getImage() {
        return image;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public void setSize(int width, int height) {
        this.width = width;
        this.height = height;
    }
}
