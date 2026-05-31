package Scripts.Figures;

import java.awt.*;

public class FigureL extends Figure {

    @Override
    protected int[][] getInitialShape() {
        return new int[][]{
                {0, 0, 1},
                {1, 1, 1},
                {0, 0, 0}
        };
    }

    public FigureL() {
        _color = Color.ORANGE;
        _shape = getInitialShape();
    }

    @Override
    public Figure copy() {
        return new FigureL();
    }
}