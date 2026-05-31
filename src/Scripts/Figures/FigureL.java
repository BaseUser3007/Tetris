package Scripts.Figures;

import java.awt.*;

public class FigureL extends Figure {
    public FigureL() {
        _shape = new int[][]{
                {0, 0, 1},
                {1, 1, 1},
                {0, 0, 0}
        };
        _color = Color.ORANGE;
    }
}