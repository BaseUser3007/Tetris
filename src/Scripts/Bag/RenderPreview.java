package Scripts.Bag;

import Scripts.Figures.Figure;
import Scripts.GameConfig;

import java.awt.*;
import java.util.List;

public class RenderPreview {

    private FigureBag _figureBag;

    public RenderPreview(FigureBag figureBag) {
        _figureBag = figureBag;
    }

    public void render(Graphics g, int panelWidth) {
        List<Figure> preview = _figureBag.getPreview();

        g.setColor(Color.WHITE);
        g.setFont(GameConfig.TETRIS_FONT);
        g.drawString("NEXT", panelWidth / 2 - 20, 30);

        int startY = 60;

        for (Figure figure : preview) {
            int[][] shape = figure.getShape();
            Color color = figure.getColor();

            int blockSize = 20;
            int figureWidth = shape[0].length * blockSize;
            int offsetX = (panelWidth - figureWidth) / 2;

            for (int row = 0; row < shape.length; row++) {
                for (int col = 0; col < shape[row].length; col++) {
                    if (shape[row][col] == 1) {
                        int x = offsetX + col * blockSize;
                        int y = startY + row * blockSize;

                        g.setColor(color);
                        g.fillRect(x + 1, y + 1, blockSize - 2, blockSize - 2);

                        g.setColor(color.brighter());
                        g.drawRect(x + 1, y + 1, blockSize - 2, blockSize - 2);
                    }
                }
            }
            startY += shape.length * blockSize + 20;
        }
    }
}
