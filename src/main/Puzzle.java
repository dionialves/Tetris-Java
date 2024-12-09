package main;

import tetriminoes.Tetrimino;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Puzzle {
    private final int width = (PainelGame.WIDHT / Tetrimino.SIZE);
    private final int height = (PainelGame.HEIGHT / Tetrimino.SIZE) + 2;
    private int[][] area = new int[height][width];

    private final List<Tetrimino> shape = new ArrayList<>();


    public Puzzle() {
    }

    public void addShape(Rectangle[] block) {

        // Colocado nas coordenadas -1 pois matriz começa com 0
        for (Rectangle rectangle : block) {
            area[((rectangle.y / Tetrimino.SIZE))-1][((rectangle.x / Tetrimino.SIZE)-1)]++;
        }
    }

    // de colisão não esta funcional, precisa ser refeita do zero
    public boolean hasCollided(Tetrimino shape) {
        boolean collided = false;

        for (Rectangle rectangle : shape.getBlock()) {

            int x = (rectangle.x / Tetrimino.SIZE) -1;
            int y = (rectangle.y / Tetrimino.SIZE);

            if (x > 0 && y > 0) {

                if (y >= this.height - 1) {
                    collided = true;
                    break;
                } else {
                    if (area[y][x] == 1) {
                        collided = true;
                        break;
                    }
                }
            }
        }
        return collided;
    }

    public void draw(Graphics2D g2d) {

        for (int i = 0; i < area.length; i++) {
            for (int j = 0; j < area[i].length; j++) {

                if (this.area[i][j] != 0) {

                    // Adicionado +20 nas coordenadas, pois a grid começa em 20;
                    g2d.setColor(Color.GRAY);
                    g2d.fillRect(
                            (j*Tetrimino.SIZE) + Tetrimino.SIZE,
                            (i*Tetrimino.SIZE) + Tetrimino.SIZE,
                            Tetrimino.SIZE,
                            Tetrimino.SIZE
                    );

                    g2d.setColor(Color.black);
                    g2d.drawRect(
                            (j*Tetrimino.SIZE) + Tetrimino.SIZE,
                            (i*Tetrimino.SIZE) + Tetrimino.SIZE,
                            Tetrimino.SIZE,
                            Tetrimino.SIZE
                    );
                }
            }
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
