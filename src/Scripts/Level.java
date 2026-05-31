package Scripts;

import Scripts.Bag.FigureBag;
import Scripts.Figures.Figure;
import Scripts.Figures.RenderFigure;

public class Level {

    private GameInitializer _gameInitializer;
    private FigureBag _figureBag;
    private Mover _mover;
    private GameTimer _gameTimer;
    private KeyHandler _keyHandler;
    private RenderFigure _renderFigure;

    public Level() {
        _gameInitializer = new GameInitializer();
        _mover = new Mover(this::spawnNextFigure);
        _figureBag = new FigureBag();
        _renderFigure = new RenderFigure();
        _keyHandler = new KeyHandler(_mover, this::repaint);
        _gameTimer = new GameTimer(this::tick);
    }

    public static void main(String[] args) {
        Level level = new Level();
        level.start();
    }

    private void start() {
        _gameInitializer.initialize(_keyHandler, _renderFigure);
        spawnNextFigure();
        _gameTimer.start();
    }

    private void tick() {
        _mover.tick();
        repaint();
    }

    private void repaint() {
        _gameInitializer.getGamePanel().repaint();
    }

    private void spawnNextFigure() {
        Figure figure = _figureBag.get();
        _renderFigure.setFigure(figure);
        _mover.attach(figure);
    }

    public void setDifficulty(int delay) {
        _gameTimer.setDelay(delay);
    }
}