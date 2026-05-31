package Scripts;

import java.awt.*;

public class Grid {

    private int[][] _cells;

    public Grid() {
        _cells = new int[GameConfig.ROWS][GameConfig.COLS];
    }

    public int get(int row, int col) {
        return _cells[row][col];
    }

    public void set(int row, int col, int value) {
        _cells[row][col] = value;
    }

    public boolean isEmpty(int row, int col) {
        return _cells[row][col] == 0;
    }

    public int getRows() {
        return GameConfig.ROWS;
    }

    public int getCols() {
        return GameConfig.COLS;
    }

    public void clear() {
        _cells = new int[GameConfig.ROWS][GameConfig.COLS];
    }

    public int clearLines() {
        int linesCleared = 0;

        for (int row = getRows() - 1; row >= 0; row--) {
            if (isLineFull(row)) {
                removeLine(row);
                linesCleared++;
                row++; // проверяем эту же строку снова после сдвига
            }
        }
        return linesCleared;
    }

    private boolean isLineFull(int row) {
        for (int col = 0; col < getCols(); col++) {
            if (isEmpty(row, col)) return false;
        }
        return true;
    }

    private void removeLine(int clearedRow) {
        // сдвигаем все строки выше вниз
        for (int row = clearedRow; row > 0; row--) {
            _cells[row] = _cells[row - 1].clone();
        }
        // верхняя строка становится пустой
        _cells[0] = new int[getCols()];
    }

    public void render(Graphics g) {
        for (int row = 0; row < getRows(); row++) {
            for (int col = 0; col < getCols(); col++) {
                if (!isEmpty(row, col)) {
                    int x = col * GameConfig.CELL_SIZE;
                    int y = row * GameConfig.CELL_SIZE;

                    g.setColor(new Color(_cells[row][col]));
                    g.fillRect(x + 1, y + 1, GameConfig.CELL_SIZE - 2, GameConfig.CELL_SIZE - 2);

                    g.setColor(new Color(_cells[row][col]).brighter());
                    g.drawRect(x + 1, y + 1, GameConfig.CELL_SIZE - 2, GameConfig.CELL_SIZE - 2);
                }
            }
        }
    }
}