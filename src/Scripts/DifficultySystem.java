package Scripts;

import Scripts.Interfaces.Listener.ILineClearedListener;
import Scripts.Interfaces.Listener.DataProvider.ILevelProvider;

public class DifficultySystem implements ILineClearedListener, ILevelProvider {

    private int _totalLines;
    private int _currentLevel;
    private GameTimer _gameTimer;

    public DifficultySystem(GameTimer gameTimer) {
        _gameTimer = gameTimer;
        _totalLines = 0;
        _currentLevel = 1;
    }

    @Override
    public void onLinesCleared(int lines) {
        _totalLines += lines;
        int newLevel = (_totalLines / GameConfig.LINES_PER_LEVEL) + 1;

        if (newLevel > _currentLevel) {
            _currentLevel = newLevel;
            updateSpeed();
        }
    }

    private void updateSpeed() {
        int newDelay = Math.max(GameConfig.INITIAL_DELAY - (_currentLevel - 1) * GameConfig.DELAY_STEP, GameConfig.MIN_DELAY);
        _gameTimer.setDelay(newDelay);
    }

    public void reset() {
        _totalLines = 0;
        _currentLevel = 1;
        _gameTimer.setDelay(GameConfig.INITIAL_DELAY);
    }

    @Override
    public int getLevel() {
        return _currentLevel;
    }

    public int getCurrentLevel() {
        return _currentLevel;
    }
}