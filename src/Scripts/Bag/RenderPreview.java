package Scripts.Bag;

import Scripts.Figures.Figure;
import Scripts.GameConfig;
import Scripts.Interfaces.Listener.IRenderListener;

import java.awt.*;
import java.util.List;

public class RenderPreview implements IRenderListener {

    private FigureBag _figureBag;

    public RenderPreview(FigureBag figureBag) {
        _figureBag = figureBag;
    }

    @Override
    public void onRender(Graphics g) {
        List<Figure> preview = _figureBag.getPreview();

        g.setColor(Color.WHITE);
        g.setFont(GameConfig.TETRIS_FONT);
        g.drawString("NEXT", 40, 30);

        int startY = 60;
        int blockSize = 20;

        for (Figure figure : preview) {
            int[][] shape = figure.getShape();
            Color color = figure.getColor();
            int figureWidth = shape[0].length * blockSize;
            int offsetX = (150 - figureWidth) / 2;

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