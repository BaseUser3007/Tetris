package Scripts;

import Scripts.Interfaces.Listener.ILineClearedListener;
import Scripts.Interfaces.Listener.IScoreListener;

public class ScoreSystem implements IScoreListener, ILineClearedListener {

    public static final int TETRIS = 4;

    private int _score;
    private DifficultySystem _difficultySystem;

    public ScoreSystem(DifficultySystem difficultySystem) {
        _difficultySystem = difficultySystem;
        _score = 0;
    }

    @Override
    public void onFigureDropped() {
        _score += GameConfig.SCORE_PER_DROP;
    }

    @Override
    public void onLinesCleared(int lines) {
        int level = _difficultySystem.getCurrentLevel();
        if (lines == TETRIS) {
            _score += GameConfig.SCORE_PER_TETRIS * level;
        } else {
            _score += GameConfig.SCORE_PER_LINE * lines * level;
        }
    }

    public void reset() {
        _score = 0;
    }

    public int getScore() {
        return _score;
    }
}