package Scripts.Figures;

import java.awt.*;

public class FigureI extends Figure {

    @Override
    protected int[][] getInitialShape() {
        return new int[][]{
                {0, 0, 0, 0},
                {1, 1, 1, 1},
                {0, 0, 0, 0},
                {0, 0, 0, 0}
        };
    }

    public FigureI() {
        _color = Color.CYAN;
        _shape = getInitialShape();
    }

    @Override
    public void reset() {
        _x = 3;
        _y = -1;
        _shape = getInitialShape();
    }

    @Override
    public Figure copy() {
        return new FigureI();
    }
}