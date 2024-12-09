package tetriminoes;
import main.PainelGame;

import java.awt.*;

public class ShapeL extends Tetrimino {

    public ShapeL(Color color) {
        super();
        this.setColor(color);

        int[][] shape = {
                {1, 0},
                {1, 0},
                {1, 1}
        };
        this.setShape(shape);
    }
}
