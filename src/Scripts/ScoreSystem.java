package Scripts;

import Scripts.Interfaces.Listener.*;
import Scripts.Interfaces.Listener.DataProvider.ILevelProvider;
import Scripts.Interfaces.Listener.DataProvider.IScoreProvider;

public class ScoreSystem implements IScoreListener, ILineClearedListener, IScoreProvider {

    private static final int ONE_LINE = 1;
    private static final int TWO_LINE = 2;
    private static final int THREE_LINE = 3;
    private static final int TETRIS = 4;

    private int _score;
    private int _bestScore;
    private ILevelProvider _levelProvider;
    private ScoreStorage _storage;

    public ScoreSystem(ILevelProvider levelProvider) {
        _levelProvider = levelProvider;
        _storage = new ScoreStorage();
        _score = 0;
        _bestScore = _storage.load();
    }

    @Override
    public void onFigureDropped() {
        _score += GameConfig.SCORE_PER_DROP;
    }

    @Override
    public void onLinesCleared(int lines) {
        int level = _levelProvider.getLevel();
        switch (lines) {
            case ONE_LINE:
                _score += GameConfig.SCORE_PER_ONE_LINE * level;
                break;
            case TWO_LINE:
                _score += GameConfig.SCORE_PER_TWO_LINE * level;
                break;
            case THREE_LINE:
                _score += GameConfig.SCORE_PER_THREE_LINE * level;
                break;
            case TETRIS:
                _score += GameConfig.SCORE_PER_TETRIS * level;
                break;
        }
    }

    public void onGameOver() {
        if (_score > _bestScore) {
            _bestScore = _score;
            _storage.save(_bestScore);
        }
    }

    public void reset() {
        _score = 0;
    }

    @Override
    public int getScore() {
        return _score;
    }

    public int getBestScore() {
        return _bestScore;
    }
}