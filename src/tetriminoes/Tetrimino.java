package tetriminoes;

import main.PainelGame;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Tetrimino {
    public static final int SIZE = 20;

    private int x;
    private int y;
    private int[][] shape;

    private final Rectangle[] block = new Rectangle[4];
    private Color color;

    public Tetrimino() {
        block[0] = new Rectangle(SIZE, SIZE);
        block[1] = new Rectangle(SIZE, SIZE);
        block[2] = new Rectangle(SIZE, SIZE);
        block[3] = new Rectangle(SIZE, SIZE);
    }

    public void draw(Graphics2D g2d) {

        // numBlock será responsável por interar entre o for e definir o número do atributo block dessa mesma classe.
        int numBlock = 0;
        int x;
        int y;

        for (int i = 0; i < shape.length; i++) {
            for (int j = 0; j < shape[i].length; j++) {
                if (this.shape[i][j] != 0) {

                    x = this.getX() + (j * Tetrimino.SIZE);
                    y = this.getY() + (i * Tetrimino.SIZE);

                    if (y >= PainelGame.TOP) {

                        g2d.setColor(this.getColor());
                        g2d.fillRect(x, y, Tetrimino.SIZE, Tetrimino.SIZE);

                        g2d.setColor(Color.black);
                        g2d.drawRect(x, y, Tetrimino.SIZE, Tetrimino.SIZE);
                    }

                    // aqui setamos a posição x e y do bloco, usando a variável numBlock mpara fazer essa interação.
                    this.setPositionBlock(numBlock, x, y);
                    numBlock++;

                }
            }
        }
    }

    public List<Integer> getPositionBlockX() {
        List<Integer> listPositionX = new ArrayList<>();

        listPositionX.add(block[0].x);
        listPositionX.add(block[1].x);
        listPositionX.add(block[2].x);
        listPositionX.add(block[3].x);

        return listPositionX;
    }

    public void setPositionBlock(int numberBlock, int x, int y) {
        this.block[numberBlock].x = x;
        this.block[numberBlock].y = y;
    }

    public List<Integer> getPositionBlockY() {
        List<Integer> listPositionY = new ArrayList<>();

        listPositionY.add(block[0].y);
        listPositionY.add(block[1].y);
        listPositionY.add(block[2].y);
        listPositionY.add(block[3].y);

        return listPositionY;
    }

    public void changeRotated() {
        int rows = this.shape.length;
        int cols = this.shape[0].length;

        int[][] rotated = new int[cols][rows];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                rotated[j][rows - 1 - i] = this.shape[i][j];
            }
        }
        this.shape = rotated;
    }

    public Color getColor() {
        return this.color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int[][] getShape() {
        return shape;
    }

    public void setShape(int[][] shape) {
        this.shape = shape;
    }

    public Rectangle[] getBlock() {
        return block;
    }

    public void setBlock(Rectangle[] block) {
        this.block[0] = block[0];
        this.block[1] = block[1];
        this.block[2] = block[2];
        this.block[3] = block[3];
    }
}
