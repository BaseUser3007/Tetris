package Scripts;

import Scripts.Bag.FigureBag;
import Scripts.Bag.RenderPreview;
import Scripts.Figures.Figure;
import Scripts.Figures.RenderFigure;

public class Level {

    private Renderer _renderer;
    private FigureBag _figureBag;
    private Mover _mover;
    private GameTimer _gameTimer;
    private KeyHandler _keyHandler;
    private RenderFigure _renderFigure;
    private RenderPreview _renderPreview;
    private Grid _grid;
    private DifficultySystem _difficultySystem;
    private ScoreSystem _scoreSystem;
    private GameDataProvider _dataProvider;

    public Level() {
        _grid = new Grid();
        _gameTimer = new GameTimer();
        _difficultySystem = new DifficultySystem(_gameTimer);
        _scoreSystem = new ScoreSystem(_difficultySystem);
        _dataProvider = new GameDataProvider(_scoreSystem, _difficultySystem);
        _figureBag = new FigureBag();
        _renderFigure = new RenderFigure();
        _renderPreview = new RenderPreview(_figureBag);
        _renderer = new Renderer(_dataProvider);
        _mover = new Mover(this::spawnNextFigure, this::onGameOver, _grid);
        _mover.setScoreListener(_scoreSystem);
        _keyHandler = new KeyHandler(_mover, this::repaint);

        _grid.addListener(_difficultySystem);
        _grid.addListener(_scoreSystem);

        _gameTimer.addListener(_mover);
        _gameTimer.addListener(() -> repaint());

        _renderer.addGameListener(_grid);
        _renderer.addGameListener(_renderFigure);
        _renderer.addInfoListener(_renderPreview);
    }

    public static void main(String[] args) {
        Level level = new Level();
        level.start();
    }

    private void start() {
        _renderer.initialize(_keyHandler);
        spawnNextFigure();
        _gameTimer.start();
    }

    private void spawnNextFigure() {
        Figure figure = _figureBag.get();
        _renderFigure.setFigure(figure);
        _mover.attach(figure);
    }

    private void onGameOver() {
        restart();
    }

    private void restart() {
        _grid.clear();
        _figureBag.restart();
        _renderFigure.setFigure(null);
        _difficultySystem.reset();
        _scoreSystem.reset();
        spawnNextFigure();
    }

    private void repaint() {
        _renderer.repaint();
    }
}