package Arkanoid;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class Menu {

    Menu() {
        JFrame frame = new JFrame("Arkanoid");

        frame.setSize(1280, 720);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setPreferredSize(frame.getSize());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);


        //ButtonPanel panel = new ButtonPanel(frame.getSize());

        Game game = new Game(frame.getSize());
        game.setSize(frame.getSize());
        frame.add(game);]]]

        Action action = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Game game = new Game(frame.getSize());
                game.setSize(frame.getSize());
                frame.add(game);
            }
        };


    }



}
