package Arkanoid;

import java.awt.*;

public class NormalBlock extends Block {

    NormalBlock(int x, int y, int width, int height) {
        super(x, y, width, height);
        blockType = "normal";
    }

    public int getWidth() {
        return hitBox.width;
    }

    public int getHeight() {
        return hitBox.height;
    }

    public int getX() {
        return hitBox.x;
    }

    public int getY() {
        return hitBox.y;
    }

    @Override
    void render(Graphics g) {
        if (!isDestroyed) {
            g.setColor(Color.BLUE);
            g.fillRect(hitBox.x, hitBox.y, hitBox.width, hitBox.height);
        }
    }

}
