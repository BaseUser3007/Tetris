package Scripts.Figures;

import Scripts.GameConfig;
import Scripts.GameInitializer;

import java.awt.*;

public class RenderFigure {

    private Figure _currentFigure;

    public void setFigure(Figure figure) {
        _currentFigure = figure;
    }

    public void render(Graphics g) {
        if (_currentFigure == null) return;

        int[][] shape = _currentFigure.getShape();
        Color color = _currentFigure.getColor();

        for (int row = 0; row < shape.length; row++) {
            for (int col = 0; col < shape[row].length; col++) {
                if (shape[row][col] == 1) {
                    int x = (_currentFigure.getX() + col) * GameConfig.CELL_SIZE;
                    int y = (_currentFigure.getY() + row) * GameConfig.CELL_SIZE;

                    g.setColor(color);
                    g.fillRect(x + 1, y + 1, GameConfig.CELL_SIZE - 2, GameConfig.CELL_SIZE - 2);

                    g.setColor(color.brighter());
                    g.drawRect(x + 1, y + 1, GameConfig.CELL_SIZE - 2, GameConfig.CELL_SIZE - 2);
                }
            }
        }
    }
}
