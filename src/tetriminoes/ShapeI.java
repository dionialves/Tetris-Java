package tetriminoes;
import java.awt.*;

public class ShapeI extends Tetrimino {

    public ShapeI(Color color) {
        super();
        this.setColor(color);

        int[][] shape = {
                {1, 1, 1, 1}
        };
        this.setShape(shape);
    }
}
