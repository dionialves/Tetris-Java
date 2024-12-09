package main;

import tetriminoes.Tetrimino;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

public class GameEngine extends JPanel implements Runnable, KeyListener {
    public static final int WIDTH = 700;
    public static final int HEIGHT = 720;
    private final int fps = 60;

    private final PainelGame pm = new PainelGame();

    public GameEngine() {
        this.addKeyListener(this);

        this.setPreferredSize(new Dimension(GameEngine.WIDTH, GameEngine.HEIGHT));
        this.setFocusable(true);
        this.setLayout(null);
        this.requestFocusInWindow();
    }

    public void update() {
        pm.update();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponents(g);

        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.BLACK);
        g2d.fillRect(0, 0, getWidth(), getHeight());

        pm.draw(g2d);
    }

    @Override
    public void run() {

        double drawInterval = 1000000000/getFps();
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while (true) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;

            if (delta >= 1) {
                this.update();
                this.repaint();
                delta--;
            }
        }
    }

    public int getFps() {
        return fps;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {

            if (pm.defineLimitMove("right")) {
                PainelGame.SHAPE_POSITION_X+= Tetrimino.SIZE;
            }

        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            if (pm.defineLimitMove("left")) {
                PainelGame.SHAPE_POSITION_X-= Tetrimino.SIZE;
            }

        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            if (!pm.puzzle.hasCollided(pm.currentShape)) {
                PainelGame.SHAPE_POSITION_Y+= Tetrimino.SIZE;
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            // pensar em um nome melhor para o método;
            pm.changeRotatedShape();
        }



    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
