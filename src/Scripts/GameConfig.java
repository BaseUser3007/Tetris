package Scripts;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

public class GameConfig {
    public static final int CELL_SIZE = 30;
    public static final int COLS = 10;
    public static final int ROWS = 20;
    public static final int WINDOW_WIDTH = COLS * CELL_SIZE;
    public static final int WINDOW_HEIGHT = ROWS * CELL_SIZE;

    public static final int INITIAL_DELAY = 800;
    public static final int MIN_DELAY = 100;
    public static final int DELAY_STEP = 75;
    public static final int LINES_PER_LEVEL = 10;

    public static final int SCORE_PER_DROP = 1;
    public static final int SCORE_PER_ONE_LINE = 100;
    public static final int SCORE_PER_TWO_LINE = 250;
    public static final int SCORE_PER_THREE_LINE = 400;
    public static final int SCORE_PER_TETRIS = 600;

    // UI
    public static final int INFO_PANEL_WIDTH = 150;
    public static final int PREVIEW_TITLE_Y = 30;
    public static final int PREVIEW_OFFSET_Y = 60;
    public static final int PREVIEW_BLOCK_SIZE = 20;
    public static final int PREVIEW_GAP = 20;
    public static final int HOLDER_TITLE_Y = 320;
    public static final int HOLDER_OFFSET_Y = 340;
    public static final int SCORE_OFFSET_Y = 450;
    public static final int SCORE_GAP = 10;
    public static final int PAUSE_FONT_SIZE = 32;
    public static final int INFO_FONT_SIZE = 16;

    public static final Font TETRIS_FONT = loadFont();

    private static Font loadFont() {
        try {
            InputStream is = GameConfig.class.getResourceAsStream("/Fonts/TetrisFont.otf");
            Font font = Font.createFont(Font.TRUETYPE_FONT, is);
            return font.deriveFont(10f);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
            return new Font("Arial", Font.BOLD, INFO_FONT_SIZE);
        }
    }
}
