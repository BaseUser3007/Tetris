package Scripts.Figures;

import java.awt.*;

public class FigureS extends Figure {
    public FigureS() {
        _shape = new int[][]{
                {0, 1, 1},
                {1, 1, 0},
                {0, 0, 0}
        };
        _color = Color.GREEN;
    }
}
