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
        g.drawString("NEXT", GameConfig.INFO_PANEL_WIDTH / 2 - 20, GameConfig.PREVIEW_TITLE_Y);

        int startY = GameConfig.PREVIEW_OFFSET_Y;

        for (Figure figure : preview) {
            int[][] shape = figure.getShape();
            Color color = figure.getColor();
            int figureWidth = shape[0].length * GameConfig.PREVIEW_BLOCK_SIZE;
            int offsetX = (GameConfig.INFO_PANEL_WIDTH - figureWidth) / 2;

            for (int row = 0; row < shape.length; row++) {
                for (int col = 0; col < shape[row].length; col++) {
                    if (shape[row][col] == 1) {
                        int x = offsetX + col * GameConfig.PREVIEW_BLOCK_SIZE;
                        int y = startY + row * GameConfig.PREVIEW_BLOCK_SIZE;
                        g.setColor(color);
                        g.fillRect(x + 1, y + 1, GameConfig.PREVIEW_BLOCK_SIZE - 2, GameConfig.PREVIEW_BLOCK_SIZE - 2);
                        g.setColor(color.brighter());
                        g.drawRect(x + 1, y + 1, GameConfig.PREVIEW_BLOCK_SIZE - 2, GameConfig.PREVIEW_BLOCK_SIZE - 2);
                    }
                }
            }
            startY += shape.length * GameConfig.PREVIEW_BLOCK_SIZE + GameConfig.PREVIEW_GAP;
        }
    }
}