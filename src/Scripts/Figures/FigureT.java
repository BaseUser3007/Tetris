package Scripts.Figures;

import java.awt.*;

public class FigureT extends Figure {

    @Override
    protected int[][] getInitialShape() {
        return new int[][]{
                {0, 1, 0},
                {1, 1, 1},
                {0, 0, 0}
        };
    }

    public FigureT() {
        _color = Color.MAGENTA;
        _shape = getInitialShape();
    }

    @Override
    public Figure copy() {
        return new FigureT();
    }
}
