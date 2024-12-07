package tetriminoes;
import java.awt.*;

public class ShapeJ extends Tetrimino {

    public final Color color;

    public ShapeJ() {
        super();
        this.color = Color.blue;
    }

    public void setXY(int x, int y) {
        //      O
        // ->   O
        //   O  O

        block[0].x = x;
        block[0].y = y;
        block[1].x = x;
        block[1].y = y - Tetrimino.SIZE;
        block[2].x = x;
        block[2].y = y + Tetrimino.SIZE;
        block[3].x = x - Tetrimino.SIZE;
        block[3].y = y + Tetrimino.SIZE;
    }

    public void draw(Graphics2D g2d) {
        g2d.setColor(this.color);
        g2d.fillRect(block[0].x, block[0].y, Tetrimino.SIZE, Tetrimino.SIZE);
        g2d.fillRect(block[1].x, block[1].y, Tetrimino.SIZE, Tetrimino.SIZE);
        g2d.fillRect(block[2].x, block[2].y, Tetrimino.SIZE, Tetrimino.SIZE);
        g2d.fillRect(block[3].x, block[3].y, Tetrimino.SIZE, Tetrimino.SIZE);

        g2d.setColor(Color.black);
        g2d.drawRect(block[0].x, block[0].y, Tetrimino.SIZE, Tetrimino.SIZE);
        g2d.drawRect(block[1].x, block[1].y, Tetrimino.SIZE, Tetrimino.SIZE);
        g2d.drawRect(block[2].x, block[2].y, Tetrimino.SIZE, Tetrimino.SIZE);
        g2d.drawRect(block[3].x, block[3].y, Tetrimino.SIZE, Tetrimino.SIZE);
    }
}