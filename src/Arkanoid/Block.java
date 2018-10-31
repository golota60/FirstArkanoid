package Arkanoid;

import java.awt.*;

public abstract class Block {

    protected static int standardBlockWidth = 125;
    protected static int standardBlockHeight = 30;

    protected Rectangle hitBox;
    protected boolean isDestroyed = false;
    String blockType;

    public String getBlockType(){
        return blockType;
    }

    abstract void render(Graphics g);

    void destroy() {
        isDestroyed = true;
    }

    public boolean collidesWith(Rectangle object) {
        if (isDestroyed == false) {
            return hitBox.intersects(object);
        }
        return false;
    }

    Block(int x, int y, int width, int height) {
        hitBox = new Rectangle(x, y, width, height);
    }
}
