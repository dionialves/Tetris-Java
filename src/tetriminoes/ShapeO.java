package tetriminoes;
import java.awt.*;

public class ShapeO extends Tetrimino {

    public ShapeO(Color color) {
        super();
        this.setColor(color);

        int[][] shape = {
                {1, 1},
                {1, 1},
        };
        this.setShape(shape);
    }

}
