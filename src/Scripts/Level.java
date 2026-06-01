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
    private RenderHolder _renderHolder;
    private RenderPreview _renderPreview;
    private Grid _grid;
    private DifficultySystem _difficultySystem;
    private ScoreSystem _scoreSystem;
    private GameDataProvider _dataProvider;
    private FigureHolder _figureHolder;

    public Level() {
        _grid = new Grid();
        _gameTimer = new GameTimer();
        _difficultySystem = new DifficultySystem(_gameTimer);
        _scoreSystem = new ScoreSystem(_difficultySystem);
        _dataProvider = new GameDataProvider(_scoreSystem, _difficultySystem);
        _figureBag = new FigureBag();
        _figureHolder = new FigureHolder();
        _renderFigure = new RenderFigure();
        _renderHolder = new RenderHolder(_figureHolder);
        _renderPreview = new RenderPreview(_figureBag);
        _renderer = new Renderer(_dataProvider);

        _mover = new Mover(this::spawnNextFigure, this::onGameOver, _grid, this::onHold, _figureHolder);
        _mover.setScoreListener(_scoreSystem);

        _keyHandler = new KeyHandler(_mover, this::repaint, this::togglePause);

        _grid.addListener(_difficultySystem);
        _grid.addListener(_scoreSystem);

        _gameTimer.addListener(_mover);
        _gameTimer.addListener(() -> repaint());

        _renderer.addGameListener(_grid);
        _renderer.addGameListener(_renderFigure);

        _renderer.addInfoListener(_renderPreview);
        _renderer.addInfoListener(_renderHolder);
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

    private void togglePause() {
        if (_gameTimer.isPaused()) {
            _gameTimer.resume();
            _keyHandler.enable();
            _renderer.hidePause();
        } else {
            _gameTimer.pause();
            _keyHandler.disable();
            _renderer.showPause();
        }
        repaint();
    }

    private void spawnNextFigure() {
        Figure figure = _figureBag.get();
        _renderFigure.setFigure(figure);
        _mover.attach(figure);
    }

    private void onGameOver() {
        _scoreSystem.onGameOver();
        restart();
    }

    private Figure onHold(Figure current) {
        Figure held = _figureHolder.hold(current);
        if (held == null) {
            held = _figureBag.get();
        } else {
            held.reset();
        }
        _renderFigure.setFigure(held);
        return held;
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