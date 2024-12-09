package tetriminoes;
import java.awt.*;

public class ShapeT extends Tetrimino {

    public ShapeT(Color color) {
        super();
        this.setColor(color);

        int[][] shape = {
                {1, 1, 1},
                {0, 1, 0},
        };
        this.setShape(shape);
    }
}
