package Scripts.Figures;

import java.awt.*;

public class FigureS extends Figure {

    @Override
    protected int[][] getInitialShape() {
        return new int[][]{
                {0, 1, 1},
                {1, 1, 0},
                {0, 0, 0}
        };
    }

    public FigureS() {
        _color = Color.GREEN;
        _shape = getInitialShape();
    }

    @Override
    public Figure copy() {
        return new FigureS();
    }
}
