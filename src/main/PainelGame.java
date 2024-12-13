package main;

import tetriminoes.*;

import java.awt.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PainelGame {
    public static final int WIDHT = 360;
    public static final int HEIGHT = 600;
    public static final int LEFT = 20;
    public static final int RIGHT = PainelGame.LEFT + PainelGame.WIDHT/2;
    public static final int TOP = 40;
    private int bottom;
    private int timer = 0;

    Class<?>[] tetriminoClass = {
            ShapeI.class,
            ShapeJ.class,
            ShapeL.class,
            ShapeO.class,
            ShapeS.class,
            ShapeT.class,
            ShapeZ.class
    };

    TetriminoManager tetriminoManager = new TetriminoManager();
    public static final Score score = new Score(40);

    public static int SHAPE_POSITION_X;
    public static int SHAPE_POSITION_Y;

    public final Puzzle puzzle = new Puzzle();

    public PainelGame() {

        setBottom(PainelGame.TOP + HEIGHT);

        PainelGame.SHAPE_POSITION_X = (PainelGame.LEFT + WIDHT /2) - Tetrimino.SIZE;
        PainelGame.SHAPE_POSITION_Y = 20;

        //currentShape = tetriminoManager.getCurrentShape();
        //nextShape = tetriminoManager.getCurrentShape();

        tetriminoManager.getNextShape().setX(500);
        tetriminoManager.getNextShape().setY(550);
        tetriminoManager.getCurrentShape().setX(PainelGame.SHAPE_POSITION_X);
        tetriminoManager.getCurrentShape().setY(PainelGame.SHAPE_POSITION_Y);

    }

    public void update() {
        setTimer(getTimer() + 1);

        if (getTimer() % (int) score.getSpeed() == 0) {

            PainelGame.SHAPE_POSITION_Y+= Tetrimino.SIZE;

            if (this.puzzle.hasCollided(tetriminoManager.getCurrentShape(), "normal")) {

                puzzle.mergeShapeToMatrix(tetriminoManager.getCurrentShape());
                puzzle.hasCompleteRow();

                PainelGame.SHAPE_POSITION_X = PainelGame.LEFT + WIDHT /2 - Tetrimino.SIZE;
                PainelGame.SHAPE_POSITION_Y = 20;
                this.randomShape();

            }


        }
    }

    public void draw(Graphics2D g2d) {

        g2d.setColor(Color.WHITE);
        g2d.setStroke(new BasicStroke(4f));
        g2d.drawRect(PainelGame.LEFT-4, PainelGame.TOP-4, WIDHT+8, HEIGHT+8);

        g2d.setColor(Color.WHITE);
        g2d.setStroke(new BasicStroke(4f));
        g2d.drawRect(420, 494, 200, 150);

        g2d.setFont(new Font("Arial", Font.BOLD, 24));
        g2d.drawString("NEXT", 490, 530);

        g2d.setFont(new Font("Arial", Font.BOLD, 24));
        g2d.drawString("Tetris Game in Java", 420, 50);

        g2d.setFont(new Font("Arial", Font.PLAIN, 22));
        g2d.drawString("SCORE", 420, 120);

        g2d.setFont(new Font("Arial", Font.PLAIN, 16));
        g2d.drawString(Integer.toString(score.getScore()), 420, 145);

        g2d.setFont(new Font("Arial", Font.PLAIN, 22));
        g2d.drawString("LINES", 420, 190);

        g2d.setFont(new Font("Arial", Font.PLAIN, 16));
        g2d.drawString(Integer.toString(score.getTotalLines()), 420, 215);

        g2d.setFont(new Font("Arial", Font.PLAIN, 22));
        g2d.drawString("LEVEL", 420, 260);

        g2d.setFont(new Font("Arial", Font.PLAIN, 16));
        g2d.drawString(Integer.toString(score.getLevel()), 420, 285);

        tetriminoManager.getNextShape().setX(500);
        tetriminoManager.getNextShape().setY(550);
        tetriminoManager.getCurrentShape().setX(PainelGame.SHAPE_POSITION_X);
        tetriminoManager.getCurrentShape().setY(PainelGame.SHAPE_POSITION_Y);

        tetriminoManager.getCurrentShape().buildShape();
        tetriminoManager.getNextShape().buildShape();

        tetriminoManager.getNextShape().draw(g2d);

        this.puzzle.clearBoard();
        this.puzzle.addTemporaryShapeToMatrix(tetriminoManager.getCurrentShape());
        this.puzzle.draw(g2d);
    }

    public void randomShape() {

        tetriminoManager.updateShapes();

        tetriminoManager.getCurrentShape().setX(PainelGame.SHAPE_POSITION_X);
        tetriminoManager.getCurrentShape().setY(PainelGame.SHAPE_POSITION_Y);
        tetriminoManager.getCurrentShape().buildShape();

    }

    public void changeRotatedShape() {
        tetriminoManager.getCurrentShape().changeRotated();
    }

    public int getBottom() {
        return bottom;
    }

    public void setBottom(int bottom) {
        this.bottom = bottom;
    }

    public int getTimer() {
        return timer;
    }

    public void setTimer(int timer) {
        this.timer = timer;
    }
}
