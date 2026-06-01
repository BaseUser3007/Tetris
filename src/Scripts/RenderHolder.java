package Scripts;

import Scripts.Figures.Figure;
import Scripts.Interfaces.Listener.IRenderListener;

import java.awt.*;

public class RenderHolder implements IRenderListener {

    private FigureHolder _figureHolder;

    public RenderHolder(FigureHolder figureHolder) {
        _figureHolder = figureHolder;
    }

    @Override
    public void onRender(Graphics g) {
        g.setColor(Color.WHITE);
        g.setFont(GameConfig.TETRIS_FONT);
        g.drawString("HOLD", GameConfig.INFO_PANEL_WIDTH / 2 - 20, GameConfig.HOLDER_TITLE_Y);

        Figure figure = _figureHolder.getHeldFigure();
        if (figure == null) return;

        int[][] shape = figure.getShape();
        Color color = figure.getColor();
        int figureWidth = shape[0].length * GameConfig.PREVIEW_BLOCK_SIZE;
        int offsetX = (GameConfig.INFO_PANEL_WIDTH - figureWidth) / 2;

        for (int row = 0; row < shape.length; row++) {
            for (int col = 0; col < shape[row].length; col++) {
                if (shape[row][col] == 1) {
                    int x = offsetX + col * GameConfig.PREVIEW_BLOCK_SIZE;
                    int y = GameConfig.HOLDER_OFFSET_Y + row * GameConfig.PREVIEW_BLOCK_SIZE;
                    g.setColor(color);
                    g.fillRect(x + 1, y + 1, GameConfig.PREVIEW_BLOCK_SIZE - 2, GameConfig.PREVIEW_BLOCK_SIZE - 2);
                    g.setColor(color.brighter());
                    g.drawRect(x + 1, y + 1, GameConfig.PREVIEW_BLOCK_SIZE - 2, GameConfig.PREVIEW_BLOCK_SIZE - 2);
                }
            }
        }
    }
}