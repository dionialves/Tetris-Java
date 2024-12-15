package tetriminoes;

import java.awt.*;
import main.PainelGame;

// Essa classe é responsável por definir atributos gerais para as formas do tetris,
// portanto cada forma irá herdar essa classe
public class Tetrimino implements Cloneable {
    // Tamanho e coordenadas do tetris, essas coordenadas serão usadas para desenhar cada retângulo.
    public static final int SIZE = 20;
    private int x;
    private int y;
    // Esse atributo será diferente em cada tetris
    private int[][] shape;
    private Color color;
    // Esses Atributo que define 4 retângulos que formaram o tetris
    public final Rectangle[] block = new Rectangle[4];

    public Tetrimino() {
        block[0] = new Rectangle(SIZE, SIZE);
        block[1] = new Rectangle(SIZE, SIZE);
        block[2] = new Rectangle(SIZE, SIZE);
        block[3] = new Rectangle(SIZE, SIZE);
    }

    // Desenha o tetris, esse método apenas é usado para desenhar o nextShape na moldura, informando qual será o
    // o próximo tetris a ser utilizado
    public void draw(Graphics2D g2d) {
        for (Rectangle rectangle: this.block) {
            g2d.setColor(this.getColor());
            g2d.fillRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);

            g2d.setColor(Color.black);
            g2d.drawRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
        }
    }

    // Método principal da classe, onde ele pega as coordenadas do tetris e popula com coordenadas cada 1 dos quatro
    // blocos do tetris
    public void buildShape() {
        int numberBlock = 0;
        int x;
        int y;

        for (int i = 0; i < shape.length; i++) {
            for (int j = 0; j < shape[i].length; j++) {
                if (this.shape[i][j] != 0) {
                    x = this.getX() + (j * Tetrimino.SIZE);
                    y = this.getY() + (i * Tetrimino.SIZE);

                    // Essa condição foi necessária para que o tetris não seja desenhado em cima da linha do quadro,
                    // onde as formas aparecem
                    if (y >= PainelGame.TOP) {

                        // Atualiza as coordenadas de cada bloco
                        this.block[numberBlock].x = x;
                        this.block[numberBlock].y = y;
                        numberBlock++;
                    }
                }
            }
        }
    }

    // Método para rotacionar no sentido horário o tetris
    public void changeRotated() {
        int rows = this.shape.length;
        int cols = this.shape[0].length;

        // cria uma nova matrix com as dimensões da forma atual
        int[][] rotated = new int[cols][rows];

        // Nesse bloco de código, é que a rotação ocorre, trocando linhas por colunas
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                rotated[j][rows - 1 - i] = this.shape[i][j];
            }
        }
        this.shape = rotated;
    }

    public Rectangle[] cloneRectangle() {
        Rectangle[] cloneRectangle = new Rectangle[4];

        cloneRectangle[0] = new Rectangle();
        cloneRectangle[1] = new Rectangle();
        cloneRectangle[2] = new Rectangle();
        cloneRectangle[3] = new Rectangle();



        for (int i = 0; i < this.block.length; i++) {
            cloneRectangle[i].y = this.block[i].y;
            cloneRectangle[i].x = this.block[i].x;
            cloneRectangle[i].width = this.block[i].width;
            cloneRectangle[i].height = this.block[i].height;
        }

        return cloneRectangle;
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
