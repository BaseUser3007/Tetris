package Scripts.Bag;

import Scripts.Figures.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FigureBag extends ObjectPool<Figure> {

    private List<Figure> _bag;
    private int _bagIndex;

    public FigureBag() {
        super();
        _bag = new ArrayList<>();
        roll();
    }

    @Override
    protected void initPool() {
        _pool.add(new FigureI());
        _pool.add(new FigureO());
        _pool.add(new FigureT());
        _pool.add(new FigureS());
        _pool.add(new FigureZ());
        _pool.add(new FigureJ());
        _pool.add(new FigureL());
    }

    @Override
    protected Figure createObject() {
        return new FigureI();
    }

    public void roll() {
        _bag.clear();
        _bag.addAll(_pool);
        Collections.shuffle(_bag);
        _bagIndex = 0;
    }

    public Figure get() {
        if (_bagIndex >= _bag.size()) {
            roll();
        }
        Figure figure = _bag.get(_bagIndex++);
        figure.reset();
        figure.setActive(true);
        return figure;
    }
}