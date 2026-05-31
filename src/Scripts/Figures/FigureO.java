package Scripts.Figures;

import java.awt.*;

public class FigureO extends Figure {

    @Override
    protected int[][] getInitialShape() {
        return new int[][]{{1, 1}, {1, 1}};
    }

    public FigureO() {
        _color = Color.YELLOW;
        _shape = getInitialShape();
    }

    @Override
    public void rotate() {}

    @Override
    public int[][] getRotatedShape() { return _shape; }

    @Override
    public Figure copy() {
        return new FigureO();
    }
}
