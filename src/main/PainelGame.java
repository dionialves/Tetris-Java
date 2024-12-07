package main;

import tetriminoes.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PainelGame {
    public static final int WIDHT = 360;
    public static final int HEIGHT = 600;
    private int left;
    private int right;
    private int top;
    private int bottom;
    private int timer = 0;

    public static int SHAPE_POSITION_X;
    public static int SHAPE_POSITION_Y;

    private final List<Object> shapes = new ArrayList<>();
    private final ShapeI shapeI = new ShapeI();
    private final ShapeJ shapeJ = new ShapeJ();
    private final ShapeL shapeL = new ShapeL();
    private final ShapeO shapeO = new ShapeO();
    private final ShapeS shapeS = new ShapeS();
    private final ShapeT shapeT = new ShapeT();
    private final ShapeZ shapeZ = new ShapeZ();

    Tetrimino currentShape;

    public PainelGame() {

        setLeft(20);
        setRight(getLeft() + WIDHT/2);
        setTop(50);
        setBottom(getTop() + HEIGHT);

        PainelGame.SHAPE_POSITION_X = getLeft() + WIDHT /2;
        PainelGame.SHAPE_POSITION_Y = getTop() + Tetrimino.SIZE;

        shapes.add(shapeI);
        shapes.add(shapeJ);
        shapes.add(shapeL);
        shapes.add(shapeO);
        shapes.add(shapeS);
        shapes.add(shapeT);
        shapes.add(shapeZ);

        this.currentShape = (Tetrimino) shapes.get(new Random().nextInt(shapes.size()));
        currentShape.setXY(PainelGame.SHAPE_POSITION_X, PainelGame.SHAPE_POSITION_Y);
    }

    public void update() {

        setTimer(getTimer() + 1);
        if (getTimer() % 60 == 0) {
            if (this.move("down")) {
                PainelGame.SHAPE_POSITION_Y+= Tetrimino.SIZE;
            } else {
                PainelGame.SHAPE_POSITION_X = getLeft() + WIDHT /2;
                PainelGame.SHAPE_POSITION_Y = getTop() + Tetrimino.SIZE;
                this.randomShape();
            }
        }

    }

    public void draw(Graphics2D g2d) {

        g2d.setColor(Color.WHITE);
        g2d.setStroke(new BasicStroke(4f));
        g2d.drawRect(getLeft()-4, getTop()-4, WIDHT+8, HEIGHT+8);

        currentShape.setXY(PainelGame.SHAPE_POSITION_X, PainelGame.SHAPE_POSITION_Y);
        currentShape.draw(g2d);
    }

    public void randomShape() {
        this.currentShape = (Tetrimino) shapes.get(new Random().nextInt(shapes.size()));
        currentShape.setXY(PainelGame.SHAPE_POSITION_X, PainelGame.SHAPE_POSITION_Y);
    }

    public boolean move(String direction) {
        /*
        Este método precisa ser melhorado, pois fiz para funcionar, preciso melhorar a logica para ficar algo
        minimamente aceitável
         */
        List<Integer> listPositionX = new ArrayList<>();
        List<Integer> listPositionY = new ArrayList<>();

        listPositionX = currentShape.getPositionBlockX();
        listPositionY = currentShape.getPositionBlockY();
        boolean isMoved = true;

        for (int i = 0; i<4 ; i++) {

            if (direction == "right") {
                if (listPositionX.get(i) > (WIDHT - getLeft())) {
                    isMoved = false;

                }
            }
            if (direction == "left") {
                if (listPositionX.get(i) <= this.getLeft()) {
                    isMoved = false;
                }
            }
            if (direction == "down") {
                if (listPositionY.get(i) >= (this.getBottom() - Tetrimino.SIZE - 8)) {
                    isMoved = false;
                }
            }
        }
        return isMoved;
    }

    public int getLeft() {
        return left;
    }

    public void setLeft(int left) {
        this.left = left;
    }

    public int getRight() {
        return right;
    }

    public void setRight(int right) {
        this.right = right;
    }

    public int getTop() {
        return top;
    }

    public void setTop(int top) {
        this.top = top;
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
