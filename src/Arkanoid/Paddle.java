package Arkanoid;

import java.awt.*;

public class Paddle {
    private Rectangle hitBox;
    public static int standardPlayerWidth = 120;
    public static int standardPlayerHeight = 20;


    Paddle(int x, int y, int width, int height) {
        hitBox = new Rectangle(x, y, width, height);
    }

    public void moveOnX(int speed) {
        hitBox.x += speed;
    }

    public int getX() {
        return hitBox.x;
    }

    public int getY() { return hitBox.y; }

    public void setX(int x) {
        hitBox.x = x;
    }

    public int getWidth() {
        return hitBox.width;
    }

    public int getHeight() {
        return hitBox.height;
    }

    public int getCenter() { return hitBox.x+hitBox.width/2;}

    public Rectangle getHitBox() {
        return hitBox;
    }

    public void setWidth(int width) {
        hitBox.width = width;
    }

    public boolean collidesWith(Rectangle object){
        return hitBox.intersects(object);
    }

    public void render(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(hitBox.x, hitBox.y, hitBox.width, hitBox.height);
    }

}
