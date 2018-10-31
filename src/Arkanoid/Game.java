package Arkanoid;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Game extends JPanel {

    private Dimension size;
    private Paddle player;
    private Ball ball = new Ball(0, 0, 0);
    private boolean doesBallExist = false;
    private final int nOfYBlocks = 8;
    private int nOfXBlocks = 10;
    private Block[][] blocks;
    private int random;
    private Score score = new Score();

    public Game(Dimension size) {

        blocks = new Block[nOfXBlocks][nOfYBlocks];
        //ruch paletki reagujący na ruch myszki
        for (int x = 0; x < nOfXBlocks; x++) {
            for (int y = 0; y < nOfYBlocks; y++) {
                random = (int) ((Math.random() * 10) + 1);
                if (random == 1) {
                    blocks[x][y] = new SpeedingBlock(x * Block.standardBlockWidth + x, y * Block.standardBlockHeight + y, Block.standardBlockWidth, Block.standardBlockHeight);
                }
                if (random == 2) {
                    blocks[x][y] = new SlowingBlock(x * Block.standardBlockWidth + x, y * Block.standardBlockHeight + y, Block.standardBlockWidth, Block.standardBlockHeight);
                }
                if (random > 2 && random <= 10) {
                    blocks[x][y] = new NormalBlock(x * Block.standardBlockWidth + x, y * Block.standardBlockHeight + y, Block.standardBlockWidth, Block.standardBlockHeight);
                }
            }
        }
        this.setFocusable(true);
        this.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                super.mouseMoved(e);
                //if potrzebny aby paletka nie mogla sie poruszac poza polem gry
                if (getMousePosition().getX() > ((getWidth() - size.getWidth() + player.getWidth()) / 2) && getMousePosition().getX() < (getWidth() - (getWidth() - size.getWidth() + player.getWidth()) / 2)) {
                    player.setX((int) (getMousePosition().getX() - ((getWidth() - size.getWidth()) / 2) - player.getWidth() / 2));
                    repaint();
                }
            }
        });
        //tworze kulke po kliknieciu spacji
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if (doesBallExist == false) {
                    if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                        doesBallExist = true;
                        ball = new Ball((int) (size.getWidth() - Paddle.standardPlayerWidth) / 2, (int) size.getHeight() - Paddle.standardPlayerHeight, 20);
                        ball.setPos((int) size.getWidth() / 2, (int) size.getHeight() / 2);
                        timer.start();
                        repaint();
                    }
                }
            }
        });
        //przekazuje size po to zeby miec odniesienie co do pozycji innych obiektow tworzonych w konstruktorze
        size = new Dimension(size.width, size.height);
        player = new Paddle((int) (size.getWidth() - Paddle.standardPlayerWidth) / 2, (int) (size.getHeight() - Paddle.standardPlayerHeight)-40, 120, 20);
        System.out.println(size.height);
    }

    @Override
    public void setSize(Dimension size) {
        super.setSize(size);

    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        g.setColor(Color.BLACK);
        g.fillRect(0, 0, size.width, size.height);
        g.drawRect(0, 0, size.width, size.height);

        if (doesBallExist) ball.render(g);
        player.render(g);

        for (Block[] pls : blocks) {
            for (Block p : pls) {
                p.render(g);
            }
        }
        g.setColor(Color.GREEN);
        g.drawString("Score:", 0, -20);
        g.drawString(Integer.toString(score.getScore()), 40, -20);

        //w razie jakby gra stracila focus nakladam go z kazdym repaintem()(keylistener dziala tylko kiedy jest nalozony focus)
        this.requestFocusInWindow();
    }

    private Thread timer = new Thread(new Runnable() {
        @Override
        public void run() {
            ball.setVector(5, 5);
            while (doesBallExist) {
                ball.move();
                collisionCheck();
                repaint();
                try {
                    Thread.sleep(10);
                } catch (Exception e) {
                }
            }
        }
    });

    private void collisionCheck() {
        //kolizje ze scianami
        //dolna(tymczasowo)
        if (ball.getY() + ball.getRadius()*2 >= size.getHeight()) {
            ball.setVectorY(ball.getVectorY() * (-1));
        }
        //gorna
        if (ball.getY() <= 0) {
            ball.setVectorY(ball.getVectorY() * (-1));
        }
        //lewa
        if (ball.getX() <= 0) {
            ball.setVectorX(ball.getVectorX() * (-1));
        }
        //prawa
        if (ball.getX() + ball.getRadius() >= size.getWidth()) {
            ball.setVectorX(ball.getVectorX() * (-1));
        }
        //kolizje z paletka
//        if (ball.getY() + ball.getRadius() >= size.getHeight() - player.getHeight() && ball.getX() + ball.getRadius() >= player.getX() && ball.getX() <= player.getX() + player.getWidth()) {
//            double dvx = ball.getX() + (ball.getRadius() / 2) - player.getCenter();
//            ball.setVectorX((int) (ball.getVectorX() + dvx) / 10);
//            ball.setVectorY(ball.getVectorY() * (-1));
//        }
        if(player.collidesWith(new Rectangle(ball.getX(), ball.getY(), ball.getRadius(), ball.getRadius())))
        {
            double dvx = ball.getX() + (ball.getRadius() / 2) - player.getCenter();
            ball.setVectorX((int) (ball.getVectorX() + dvx) / 10);
            ball.setVectorY(ball.getVectorY() * (-1));
        }
        //kolizje z blokami
        for (Block[] pls : blocks) {
            for (Block p : pls) {
                if (p.collidesWith(new Rectangle(ball.getX(), ball.getY(), ball.getRadius(), ball.getRadius()))) {
                    ball.setVectorY(ball.getVectorY() * (-1));
                    if (p.getBlockType().equals("normal")) score.addToScore(1);
                    if (p.getBlockType().equals("speeding")) {
                        ball.setVectorX((ball.getVectorX()+2));
                        ball.setVectorY((ball.getVectorY()+2));
                        score.addToScore(3);
                    }
                    if (p.getBlockType().equals("slowing")) {
                        ball.setVectorX((ball.getVectorX()-2));
                        ball.setVectorY((ball.getVectorY()-2));
                        score.addToScore(3);
                    }
                    p.destroy();
                }
            }
        }
    }
}
