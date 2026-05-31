package Scripts.Figures;

import java.awt.*;

public class FigureO extends Figure {
    public FigureO() {
        _shape = new int[][]{
                {1, 1},
                {1, 1}
        };
        _color = Color.YELLOW;
    }

    @Override
    public void rotate() {
        // O не поворачивается
    }

    @Override
    public int[][] getRotatedShape() {
        return _shape;
    }
}