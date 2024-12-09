package tetriminoes;
import java.awt.*;

public class ShapeS extends Tetrimino {

    public ShapeS(Color color) {
        super();
        this.setColor(color);

        int[][] shape = {
                {0, 1, 1},
                {1, 1, 0},
        };
        this.setShape(shape);
    }
}
