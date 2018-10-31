package Arkanoid;

import java.awt.*;

public class SlowingBlock extends Block {

    SlowingBlock(int x, int y, int width, int height) {
        super(x, y, width, height);
        blockType = "slowing";
    }

    @Override
    void render(Graphics g) {
        if (!isDestroyed) {
            g.setColor(Color.GREEN);
            g.fillRect(hitBox.x, hitBox.y, hitBox.width, hitBox.height);
        }
    }

}
