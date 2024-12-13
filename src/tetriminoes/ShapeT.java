package tetriminoes;
import java.awt.*;

public class ShapeT extends Tetrimino {

    public ShapeT() {
        super();
        this.setColor(Color.MAGENTA);

        int[][] shape = {
                {1, 1, 1},
                {0, 1, 0},
        };
        this.setShape(shape);
    }
}
