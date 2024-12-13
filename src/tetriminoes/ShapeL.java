package tetriminoes;
import main.PainelGame;

import java.awt.*;

public class ShapeL extends Tetrimino {

    public ShapeL() {
        super();
        this.setColor(Color.ORANGE);

        int[][] shape = {
                {1, 0},
                {1, 0},
                {1, 1}
        };
        this.setShape(shape);
    }
}
