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
    public static final int LINES_PER_LEVEL = 40;

    public static final int SCORE_PER_DROP = 1;
    public static final int SCORE_PER_LINE = 100;
    public static final int SCORE_PER_TETRIS = 500;

    public static final Font TETRIS_FONT = loadFont();

    private static Font loadFont() {
        try {
            InputStream is = GameConfig.class.getResourceAsStream("/Fonts/TetrisFont.otf");
            Font font = Font.createFont(Font.TRUETYPE_FONT, is);
            return font.deriveFont(10f);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
            return new Font("Arial", Font.BOLD, 16);
        }
    }
}
