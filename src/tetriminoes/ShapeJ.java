package tetriminoes;
import java.awt.*;

public class ShapeJ extends Tetrimino {

    public ShapeJ() {
        super();
        this.setColor(Color.BLUE);

        int[][] shape = {
                {0, 1},
                {0, 1},
                {1, 1}
        };
        this.setShape(shape);
    }
}
