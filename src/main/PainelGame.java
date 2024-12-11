package main;

import tetriminoes.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
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
    public static final int LEFT = 20;
    public static final int RIGHT = PainelGame.LEFT + PainelGame.WIDHT/2;
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

        setBottom(PainelGame.TOP + HEIGHT);

        PainelGame.SHAPE_POSITION_X = (PainelGame.LEFT + WIDHT /2) - Tetrimino.SIZE;
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

            PainelGame.SHAPE_POSITION_Y+= Tetrimino.SIZE;

            // Essa logica não esta legal, muitas erros, necessário refazer
            if (this.puzzle.hasCollided(this.currentShape, "normal")) {

                puzzle.mergeShapeToMatrix(this.currentShape);
                puzzle.hasCompleteRow();

                PainelGame.SHAPE_POSITION_X = PainelGame.LEFT + WIDHT /2 - Tetrimino.SIZE;
                PainelGame.SHAPE_POSITION_Y = 0;
                this.randomShape();

            }


        }
    }

    public void draw(Graphics2D g2d) {

        g2d.setColor(Color.WHITE);
        g2d.setStroke(new BasicStroke(4f));
        g2d.drawRect(PainelGame.LEFT-4, PainelGame.TOP-4, WIDHT+8, HEIGHT+8);

        currentShape.setX(PainelGame.SHAPE_POSITION_X);
        currentShape.setY(PainelGame.SHAPE_POSITION_Y);
        currentShape.draw(g2d);

        this.puzzle.clearBoard();
        this.puzzle.addTemporaryShapeToMatrix(this.currentShape);
        this.puzzle.draw(g2d);
    }

    public void randomShape() {
        this.currentShape = (Tetrimino) shapes.get(new Random().nextInt(shapes.size()));
        currentShape.setX(PainelGame.SHAPE_POSITION_X);
        currentShape.setY(PainelGame.SHAPE_POSITION_Y);
    }

    public void changeRotatedShape() {
        currentShape.changeRotated();
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
