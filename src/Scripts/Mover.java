package Scripts;

import Scripts.Figures.Figure;
import Scripts.Interfaces.IMovable;

public class Mover {

    private IMovable _current;
    private Figure _currentFigure;
    private Runnable _onDetach;

    public Mover(Runnable onDetach) {
        _onDetach = onDetach;
    }

    public void attach(Figure figure) {
        _current = figure;
        _currentFigure = figure;
    }

    public void detach() {
        _current = null;
        _currentFigure = null;
        _onDetach.run();
    }

    public void tick() {
        if (_current == null) return;
        if (canMoveDown()) {
            _current.moveDown();
        } else {
            detach();
        }
    }

    public void moveDown() {
        if (_current == null) return;
        if (canMoveDown()) _current.moveDown();
        else detach();
    }

    public void moveLeft() {
        if (_current == null) return;
        if (canMoveLeft()) _current.moveLeft();
    }

    public void moveRight() {
        if (_current == null) return;
        if (canMoveRight()) _current.moveRight();
    }

    public void rotate() {
        if (_current == null) return;
        if (canRotate()) _current.rotate();
    }

    private boolean canMoveDown() {
        int[][] shape = _currentFigure.getShape();
        for (int row = 0; row < shape.length; row++) {
            for (int col = 0; col < shape[row].length; col++) {
                if (shape[row][col] == 1) {
                    if (_currentFigure.getY() + row + 1 >= GameConfig.ROWS) return false;
                }
            }
        }
        return true;
    }

    private boolean canMoveLeft() {
        int[][] shape = _currentFigure.getShape();
        for (int row = 0; row < shape.length; row++) {
            for (int col = 0; col < shape[row].length; col++) {
                if (shape[row][col] == 1) {
                    if (_currentFigure.getX() + col - 1 < 0) return false;
                }
            }
        }
        return true;
    }

    private boolean canMoveRight() {
        int[][] shape = _currentFigure.getShape();
        for (int row = 0; row < shape.length; row++) {
            for (int col = 0; col < shape[row].length; col++) {
                if (shape[row][col] == 1) {
                    if (_currentFigure.getX() + col + 1 >= GameConfig.COLS) return false;
                }
            }
        }
        return true;
    }

    private boolean canRotate() {
        int[][] rotated = _currentFigure.getRotatedShape();
        int figureX = _currentFigure.getX();
        int figureY = _currentFigure.getY();

        for (int row = 0; row < rotated.length; row++) {
            for (int col = 0; col < rotated[row].length; col++) {
                if (rotated[row][col] == 1) {
                    int newX = figureX + col;
                    int newY = figureY + row;
                    if (newX < 0 || newX >= GameConfig.COLS) return false;
                    if (newY < 0 || newY >= GameConfig.ROWS) return false;
                }
            }
        }
        return true;
    }

    public boolean hasTarget() {
        return _current != null;
    }
}