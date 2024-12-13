package tetriminoes;
import java.awt.*;

public class ShapeI extends Tetrimino {

    public ShapeI() {
        super();
        this.setColor(Color.CYAN);

        int[][] shape = {
                {1, 1, 1, 1}
        };
        this.setShape(shape);
    }
}
