package Scripts.Figures;

import Scripts.Interfaces.*;

import java.awt.*;

public abstract class Figure implements IPoolable, IMovable {

    protected int[][] _shape;
    protected Color _color;
    protected int _x;
    protected int _y;
    private boolean _active;

    public Figure() {
        _active = false;
    }

    @Override
    public void reset() {
        _x = 3;
        _y = 0;
    }

    @Override
    public void moveDown()  { _y++; }

    @Override
    public void moveLeft()  { _x--; }

    @Override
    public void moveRight() { _x++; }

    @Override
    public void rotate() {
        int size = _shape.length;
        int[][] rotated = new int[size][size];

        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                rotated[col][size - 1 - row] = _shape[row][col];
            }
        }
        _shape = rotated;
    }

    // возвращает форму после поворота, не применяя его
    public int[][] getRotatedShape() {
        int size = _shape.length;
        int[][] rotated = new int[size][size];

        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                rotated[col][size - 1 - row] = _shape[row][col];
            }
        }
        return rotated;
    }

    @Override
    public boolean isActive() { return _active; }

    @Override
    public void setActive(boolean active) { _active = active; }

    public int[][] getShape() { return _shape; }
    public void setShape(int[][] shape) { _shape = shape; }
    public Color getColor() { return _color; }
    public int getX() { return _x; }
    public void setX(int x) { _x = x; }
    public int getY() { return _y; }
    public void setY(int y) { _y = y; }
}