package Scripts.Bag;

import Scripts.Figures.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FigureBag extends ObjectPool<Figure> {

    private static final int PREVIEW_COUNT = 3;

    private List<Figure> _currentBag;
    private List<Figure> _nextBag;
    private int _bagIndex;

    public FigureBag() {
        super();
        _currentBag = new ArrayList<>();
        _nextBag = new ArrayList<>();
        roll(_currentBag);
        roll(_nextBag);
        _bagIndex = 0;
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

    private void roll(List<Figure> bag) {
        bag.clear();
        bag.addAll(_pool);
        Collections.shuffle(bag);
    }

    public void restart() {
        returnAll();
        roll(_currentBag);
        roll(_nextBag);
        _bagIndex = 0;
    }

    public Figure get() {
        if (_bagIndex >= _currentBag.size()) {
            _currentBag.clear();
            _currentBag.addAll(_nextBag);
            roll(_nextBag);
            _bagIndex = 0;
        }
        Figure figure = _currentBag.get(_bagIndex++);
        figure.reset();
        figure.setActive(true);
        return figure;
    }

    public List<Figure> getPreview() {
        List<Figure> preview = new ArrayList<>();
        for (int i = _bagIndex; i < _bagIndex + PREVIEW_COUNT; i++) {
            if (i < _currentBag.size()) {
                preview.add(_currentBag.get(i).copy());
            } else {
                preview.add(_nextBag.get(i - _currentBag.size()).copy());
            }
        }
        return preview;
    }
}