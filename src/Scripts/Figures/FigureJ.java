package Scripts.Figures;

import java.awt.*;

public class FigureJ extends Figure {

    @Override
    protected int[][] getInitialShape() {
        return new int[][]{
                {1, 0, 0},
                {1, 1, 1},
                {0, 0, 0}
        };
    }

    public FigureJ() {
        _color = Color.BLUE;
        _shape = getInitialShape();
    }

    @Override
    public Figure copy() {
        return new FigureJ();
    }
}