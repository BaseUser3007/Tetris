package Scripts;

import Scripts.Figures.Figure;
import Scripts.Interfaces.IMovable;
import Scripts.Interfaces.ITickListener;

public class Mover implements ITickListener {

    private IMovable _current;
    private Figure _currentFigure;
    private Runnable _onDetach;
    private Grid _grid;

    public Mover(Runnable onDetach, Grid grid) {
        _onDetach = onDetach;
        _grid = grid;
    }

    @Override
    public void onTick() {
        tick();
    }

    private Grid getGrid() {
        return _grid;
    }

    public void attach(Figure figure) {
        _current = figure;
        _currentFigure = figure;
    }

    public void detach() {
        placeFigure();
        getGrid().clearLines();
        _current = null;
        _currentFigure = null;
        _onDetach.run();
    }

    private void placeFigure() {
        int[][] shape = _currentFigure.getShape();
        int figureX = _currentFigure.getX();
        int figureY = _currentFigure.getY();

        for (int row = 0; row < shape.length; row++) {
            for (int col = 0; col < shape[row].length; col++) {
                if (shape[row][col] == 1) {
                    getGrid().set(figureY + row, figureX + col, _currentFigure.getColor().getRGB());
                }
            }
        }
    }

    private void tick() {
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
                    int newY = _currentFigure.getY() + row + 1;
                    int newX = _currentFigure.getX() + col;
                    if (newY >= getGrid().getRows()) return false;
                    if (!getGrid().isEmpty(newY, newX)) return false;
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
                    int newX = _currentFigure.getX() + col - 1;
                    int newY = _currentFigure.getY() + row;
                    if (newX < 0) return false;
                    if (!getGrid().isEmpty(newY, newX)) return false;
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
                    int newX = _currentFigure.getX() + col + 1;
                    int newY = _currentFigure.getY() + row;
                    if (newX >= getGrid().getCols()) return false;
                    if (!getGrid().isEmpty(newY, newX)) return false;
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
                    if (newX < 0 || newX >= getGrid().getCols()) return false;
                    if (newY < 0 || newY >= getGrid().getRows()) return false;
                    if (!getGrid().isEmpty(newY, newX)) return false;
                }
            }
        }
        return true;
    }

    public boolean hasTarget() {
        return _current != null;
    }
}