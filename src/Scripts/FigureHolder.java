package Scripts;

import Scripts.Figures.Figure;

public class FigureHolder {

    private Figure _heldFigure;
    private boolean _canHold;

    FigureHolder(){
        _heldFigure = null;
        _canHold = true;
    }

    public Figure hold(Figure figure) {
        if (!_canHold) return figure;

        Figure tmpFigure = _heldFigure;
        _heldFigure = figure.copy();
        _canHold = false;
        return tmpFigure;
    }

    public Figure getHeldFigure(){
        return _heldFigure;
    }

    public void release(){
        _canHold = true;
    }

    public void reset(){
        _heldFigure = null;
    }
}