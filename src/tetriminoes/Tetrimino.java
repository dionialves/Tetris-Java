package tetriminoes;

import main.PainelGame;

import java.awt.*;
import java.util.Random;
import java.util.ArrayList;
import java.util.List;

public class Tetrimino implements Cloneable {
    public static final int SIZE = 20;

    private int x;
    private int y;
    private int[][] shape;

    public final Rectangle[] block = new Rectangle[4];
    private Color color;

    public Tetrimino() {

        block[0] = new Rectangle(SIZE, SIZE);
        block[1] = new Rectangle(SIZE, SIZE);
        block[2] = new Rectangle(SIZE, SIZE);
        block[3] = new Rectangle(SIZE, SIZE);
    }

    public void draw(Graphics2D g2d) {

        for (Rectangle rectangle: this.block) {
            g2d.setColor(this.getColor());
            g2d.fillRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);

            g2d.setColor(Color.black);
            g2d.drawRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
        }
    }

    public void buildShape() {
        // Método responsável por popular as coordenadas de x e y de cada bloco
        int numberBlock = 0;
        int x;
        int y;

        for (int i = 0; i < shape.length; i++) {
            for (int j = 0; j < shape[i].length; j++) {
                if (this.shape[i][j] != 0) {

                    x = this.getX() + (j * Tetrimino.SIZE);
                    y = this.getY() + (i * Tetrimino.SIZE);

                    // No momento de refatorar o codigo, preciso avaliar a logica por traz dessa condição, de
                    // y >= PainelGame.TOP, pois se não colocar a mesma os blocos não são apresentados corretamente
                    // iniciando seu surgimento antes da tela reservada a ele.
                    // Esta funcionando mas acredito que esse logica de controle de apresentação de deva ser colocada
                    // aqui
                    if (y >= PainelGame.TOP) {
                        this.block[numberBlock].x = x;
                        this.block[numberBlock].y = y;

                        numberBlock++;
                    }
                }
            }
        }
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

    @Override
    public Tetrimino clone() {
        try {
            Tetrimino tetrimino = (Tetrimino) super.clone();
            tetrimino.setShape(this.getShape().clone());
            return tetrimino;
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException("Erro ao clonar", e);
        }
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
