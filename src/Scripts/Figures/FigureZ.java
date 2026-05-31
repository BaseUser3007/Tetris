package Scripts.Figures;

import java.awt.*;

public class FigureZ extends Figure {
    public FigureZ() {
        _shape = new int[][]{
                {1, 1, 0},
                {0, 1, 1},
                {0, 0, 0}
        };
        _color = Color.RED;
    }
}