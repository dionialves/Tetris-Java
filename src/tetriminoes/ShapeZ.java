package tetriminoes;
import java.awt.*;

public class ShapeZ extends Tetrimino {

    public ShapeZ() {
        super();
        this.setColor(Color.RED);

        int[][] shape = {
                {1, 1, 0},
                {0, 1, 1},
        };
        this.setShape(shape);
    }
}
