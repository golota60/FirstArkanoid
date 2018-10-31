package Arkanoid;

import java.awt.*;

public class SpeedingBlock extends Block {


    SpeedingBlock(int x, int y, int width, int height) {
        super(x, y, width, height);
        blockType = "speeding";
    }

    @Override
    void render(Graphics g) {
        if (!isDestroyed) {
            g.setColor(Color.RED);
            g.fillRect(hitBox.x, hitBox.y, hitBox.width, hitBox.height);
        }
    }

}
