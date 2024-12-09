package tetriminoes;
import java.awt.*;

public class ShapeJ extends Tetrimino {

    public ShapeJ(Color color) {
        super();
        this.setColor(color);

        int[][] shape = {
                {0, 1},
                {0, 1},
                {1, 1}
        };

        this.setShape(shape);
    }
}
