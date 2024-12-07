package tetriminoes;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public abstract class Tetrimino {
    public static final int SIZE = 20;
    Rectangle[] block = new Rectangle[4];

    public Tetrimino() {
        block[0] = new Rectangle(SIZE, SIZE);
        block[1] = new Rectangle(SIZE, SIZE);
        block[2] = new Rectangle(SIZE, SIZE);
        block[3] = new Rectangle(SIZE, SIZE);
    }

    public void setXY(int x, int y) {}
    public void draw(Graphics2D g2d) {}

    public java.util.List<Integer> getPositionBlockX() {
        List<Integer> listPositionX = new ArrayList<>();

        listPositionX.add(block[0].x);
        listPositionX.add(block[1].x);
        listPositionX.add(block[2].x);
        listPositionX.add(block[3].x);

        return listPositionX;
    }

    public java.util.List<Integer> getPositionBlockY() {
        List<Integer> listPositionY = new ArrayList<>();

        listPositionY.add(block[0].y);
        listPositionY.add(block[1].y);
        listPositionY.add(block[2].y);
        listPositionY.add(block[3].y);

        return listPositionY;
    }
}
