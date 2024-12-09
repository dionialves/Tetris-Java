package tetriminoes;
import java.awt.*;

public class ShapeZ extends Tetrimino {

    public ShapeZ(Color color) {
        super();
        this.setColor(color);

        int[][] shape = {
                {1, 1, 0},
                {0, 1, 1},
        };
        this.setShape(shape);
    }
}
