package main;

import tetriminoes.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

/*
Implementações:
-> Cria classe para comportar os blocos que serão construídos e definir na mesma quando a linha será apagada.
-> Sistema de pontuação
-> Sistema de leveis aumentando a velocidade de queda do tetris
-> Criar painel contendo o próximo tetris a aparecer no game

Correção de bugs
-> Rotação dos tetris esta estranha, seria bom definir um tetris central e a partir dele rotacionar

 */
public class PainelGame {
    public static final int WIDHT = 360;
    public static final int HEIGHT = 600;
    private int left;
    private int right;
    public static final int TOP = 40;
    private int bottom;
    private int timer = 0;

    public static int SHAPE_POSITION_X;
    public static int SHAPE_POSITION_Y;

    public final Puzzle puzzle = new Puzzle();

    private final List<Object> shapes = new ArrayList<>();
    private final ShapeI shapeI = new ShapeI(Color.CYAN);
    private final ShapeJ shapeJ = new ShapeJ(Color.BLUE);
    private final ShapeL shapeL = new ShapeL(Color.ORANGE);
    private final ShapeO shapeO = new ShapeO(Color.YELLOW);
    private final ShapeS shapeS = new ShapeS(Color.GREEN);
    private final ShapeT shapeT = new ShapeT(Color.MAGENTA);
    private final ShapeZ shapeZ = new ShapeZ(Color.RED);

    Tetrimino currentShape;

    public PainelGame() {

        setLeft(20);
        setRight(getLeft() + WIDHT/2);
        setBottom(PainelGame.TOP + HEIGHT);

        PainelGame.SHAPE_POSITION_X = (getLeft() + WIDHT /2) - Tetrimino.SIZE;
        PainelGame.SHAPE_POSITION_Y = 0;

        shapes.add(shapeI);
        shapes.add(shapeJ);
        shapes.add(shapeL);
        shapes.add(shapeO);
        shapes.add(shapeS);
        shapes.add(shapeT);
        shapes.add(shapeZ);

        this.currentShape = (Tetrimino) shapes.get(new Random().nextInt(shapes.size()));
        //this.currentShape = shapeJ;
        currentShape.setX(PainelGame.SHAPE_POSITION_X);
        currentShape.setY(PainelGame.SHAPE_POSITION_Y);
    }

    public void update() {

        setTimer(getTimer() + 1);
        if (getTimer() % 60 == 0) {


            // Essa logica não esta legal, muitas erros, necessário refazer
            if (this.puzzle.hasCollided(this.currentShape)) {

                puzzle.addShape(this.currentShape.getBlock());

                PainelGame.SHAPE_POSITION_X = getLeft() + WIDHT /2 - Tetrimino.SIZE;
                PainelGame.SHAPE_POSITION_Y = 0;
                this.randomShape();

            }
            PainelGame.SHAPE_POSITION_Y+= Tetrimino.SIZE;
            /*
            else if (this.defineLimitMove("down")) {



            }


             */
        }
    }

    public void draw(Graphics2D g2d) {

        g2d.setColor(Color.WHITE);
        g2d.setStroke(new BasicStroke(4f));
        g2d.drawRect(getLeft()-4, PainelGame.TOP-4, WIDHT+8, HEIGHT+8);

        currentShape.setX(PainelGame.SHAPE_POSITION_X);
        currentShape.setY(PainelGame.SHAPE_POSITION_Y);
        currentShape.draw(g2d);

        this.puzzle.draw(g2d);
    }

    public void randomShape() {
        this.currentShape = (Tetrimino) shapes.get(new Random().nextInt(shapes.size()));
        currentShape.setX(PainelGame.SHAPE_POSITION_X);
        currentShape.setY(PainelGame.SHAPE_POSITION_Y);
    }

    public boolean defineLimitMove(String direction) {
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

            if (Objects.equals(direction, "right")) {
                if (listPositionX.get(i) > (WIDHT - getLeft())) {
                    isMoved = false;

                }
            }
            if (Objects.equals(direction, "left")) {
                if (listPositionX.get(i) <= this.getLeft()) {
                    isMoved = false;
                }
            }
            if (Objects.equals(direction, "down")) {
                if (listPositionY.get(i) >= (this.getBottom() - Tetrimino.SIZE - 8)) {
                    isMoved = false;
                }
            }
        }
        return isMoved;
    }

    public void changeRotatedShape() {
        currentShape.changeRotated();
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
