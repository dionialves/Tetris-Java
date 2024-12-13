package tetriminoes;
import java.awt.*;

public class ShapeO extends Tetrimino {

    public ShapeO() {
        super();
        this.setColor(Color.YELLOW);

        int[][] shape = {
                {1, 1},
                {1, 1},
        };
        this.setShape(shape);
    }
}
