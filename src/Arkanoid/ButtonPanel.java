package Arkanoid;

import javax.swing.*;
import java.awt.*;

public class ButtonPanel extends JPanel {

    private Dimension size;

    ButtonPanel(Dimension size){
        size = new Dimension(size.width - 100, size.height - 100);
    }


    @Override
    public void paint(Graphics g) {
        super.paint(g);

    }

}
