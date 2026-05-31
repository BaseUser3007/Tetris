package Scripts.Figures;

import java.awt.*;

public class FigureI extends Figure {
    public FigureI() {
        _shape = new int[][]{
                {0, 0, 0, 0},
                {1, 1, 1, 1},
                {0, 0, 0, 0},
                {0, 0, 0, 0}
        };
        _color = Color.CYAN;
    }
}