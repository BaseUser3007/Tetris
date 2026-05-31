package Scripts.Figures;

import java.awt.*;

public class FigureT extends Figure {
    public FigureT() {
        _shape = new int[][]{
                {0, 1, 0},
                {1, 1, 1},
                {0, 0, 0}
        };
        _color = Color.MAGENTA;
    }
}
