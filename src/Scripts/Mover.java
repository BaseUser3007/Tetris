package Scripts;

import Scripts.Figures.Figure;
import Scripts.Interfaces.IMovable;
import Scripts.Interfaces.Listener.IScoreListener;
import Scripts.Interfaces.Listener.ITickListener;

import java.util.function.Function;

public class Mover implements ITickListener {

    private IMovable _current;
    private Figure _currentFigure;
    private Runnable _onDetach;
    private Runnable _onGameOver;
    private Grid _grid;
    private FigureHolder _figureHolder;
    private IScoreListener _scoreListener;
    private Function<Figure, Figure> _onHold;

    public Mover(Runnable onDetach, Runnable onGameOver, Grid grid, Function<Figure, Figure> onHold, FigureHolder figureHolder) {
        _onDetach = onDetach;
        _onGameOver = onGameOver;
        _grid = grid;
        _onHold = onHold;
        _figureHolder = figureHolder;
    }

    public void setScoreListener(IScoreListener scoreListener) {
        _scoreListener = scoreListener;
    }

    @Override
    public void onTick() {
        tick();
    }

    private Grid getGrid() {
        return _grid;
    }

    public void attach(Figure figure) {
        if (!canSpawn(figure)) {
            _onGameOver.run();
            return;
        }
        _current = figure;
        _currentFigure = figure;
    }

    public void detach() {
        placeFigure();
        getGrid().clearLines();
        _current = null;
        _currentFigure = null;
        _figureHolder.release();
        _onDetach.run();
    }

    public void holdFigure() {
        if (_currentFigure == null) return;
        Figure newFigure = _onHold.apply(_currentFigure);
        if (newFigure != null) {
            _current = newFigure;
            _currentFigure = newFigure;
        }
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

    private boolean canSpawn(Figure figure) {
        int[][] shape = figure.getShape();
        int figureX = figure.getX();
        int figureY = figure.getY();

        for (int row = 0; row < shape.length; row++) {
            for (int col = 0; col < shape[row].length; col++) {
                if (shape[row][col] == 1) {
                    if (!getGrid().isEmpty(figureY + row, figureX + col)) return false;
                }
            }
        }
        return true;
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
        if (canMoveDown()) {
            _current.moveDown();
            if (_scoreListener != null) _scoreListener.onFigureDropped();
        } else {
            detach();
        }
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