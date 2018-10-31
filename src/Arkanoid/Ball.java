package Arkanoid;

import java.awt.*;

public class Ball {
    public static int standardBallRadius = 3;
    private Point pos;
    private Point vector = new Point(0, 0);
    private int radius;

    public Ball(int x, int y, int radius) {
        pos = new Point(x, y);
        this.radius = radius;
    }

    public void setVector(int x, int y) {
        vector = new Point(x, y);
    }

    public void setVectorX(int x) {
        vector.x = x;
    }

    public int getVectorX() {
        return vector.x;
    }

    public void setVectorY(int y) {
        vector.y = y;
    }

    public int getVectorY() {
        return vector.y;
    }

    public int getX() {
        return pos.x;
    }

    public int getY() {
        return pos.y;
    }

    public int getRadius() {
        return radius;
    }

    public void move() {
        pos.move(pos.x + vector.x, pos.y + vector.y);
    }

    public void setPos(int x, int y) {
        pos.x = x;
        pos.y = y;
    }

    public void render(Graphics g) {
        g.setColor(Color.YELLOW);
        g.fillOval(pos.x, pos.y, radius, radius);
    }
}
