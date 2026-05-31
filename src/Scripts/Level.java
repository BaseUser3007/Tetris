package Scripts;

import Scripts.Bag.FigureBag;
import Scripts.Bag.RenderPreview;
import Scripts.Figures.Figure;
import Scripts.Figures.RenderFigure;

public class Level {

    private GameInitializer _gameInitializer;
    private FigureBag _figureBag;
    private Mover _mover;
    private GameTimer _gameTimer;
    private KeyHandler _keyHandler;
    private RenderFigure _renderFigure;
    private RenderPreview _renderPreview;
    private Grid _grid;
    private DifficultySystem _difficultySystem;
    private ScoreSystem _scoreSystem;

    public Level() {
        _grid = new Grid();
        _gameTimer = new GameTimer();
        _difficultySystem = new DifficultySystem(_gameTimer);
        _scoreSystem = new ScoreSystem(_difficultySystem);
        _figureBag = new FigureBag();
        _renderFigure = new RenderFigure();
        _renderPreview = new RenderPreview(_figureBag);
        _gameInitializer = new GameInitializer(_grid, _scoreSystem, _difficultySystem, _renderPreview);
        _mover = new Mover(this::spawnNextFigure, this::onGameOver, _grid);
        _mover.setScoreListener(_scoreSystem);
        _keyHandler = new KeyHandler(_mover, this::repaint);
        _grid.addListener(_difficultySystem);
        _grid.addListener(_scoreSystem);
        _gameTimer.addListener(_mover);
        _gameTimer.addListener(() -> repaint());
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

    private void spawnNextFigure() {
        Figure figure = _figureBag.get();
        _renderFigure.setFigure(figure);
        _mover.attach(figure);
    }

    private void onGameOver() {
        System.out.println("Game Over! Restarting...");
        restart();
    }

    private void restart() {
        _grid.clear();
        _figureBag.returnAll();
        _figureBag.roll(_figureBag.getCurrentBag());
        _figureBag.roll(_figureBag.getNextBag());
        _renderFigure.setFigure(null);
        _difficultySystem.reset();
        _scoreSystem.reset();
        spawnNextFigure();
    }

    private void repaint() {
        _gameInitializer.updateInfo();
        _gameInitializer.getGamePanel().repaint();
    }
}