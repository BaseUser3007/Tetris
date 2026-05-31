package Scripts.Figures;

import java.awt.*;

public class FigureZ extends Figure {

    @Override
    protected int[][] getInitialShape() {
        return new int[][]{
                {1, 1, 0},
                {0, 1, 1},
                {0, 0, 0}
        };
    }

    public FigureZ() {
        _color = Color.RED;
        _shape = getInitialShape();
    }

    @Override
    public Figure copy() {
        return new FigureZ();
    }
}