package tetriminoes;
import java.awt.*;

public class ShapeS extends Tetrimino {

    public ShapeS() {
        super();
        this.setColor(Color.GREEN);

        int[][] shape = {
                {0, 1, 1},
                {1, 1, 0},
        };
        this.setShape(shape);
    }
}
