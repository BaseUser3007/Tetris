package Scripts;

import Scripts.Interfaces.Listener.DataProvider.IDataProvider;
import Scripts.Interfaces.Listener.DataProvider.ILevelProvider;
import Scripts.Interfaces.Listener.DataProvider.IScoreProvider;

public class GameDataProvider implements IDataProvider {

    private IScoreProvider _scoreProvider;
    private ILevelProvider _levelProvider;
    private ScoreSystem _scoreSystem;

    public GameDataProvider(ScoreSystem scoreSystem, ILevelProvider levelProvider) {
        _scoreProvider = scoreSystem;
        _levelProvider = levelProvider;
        _scoreSystem = scoreSystem;
    }

    @Override
    public int getScore() {
        return _scoreProvider.getScore();
    }

    @Override
    public int getLevel() {
        return _levelProvider.getLevel();
    }

    @Override
    public int getBestScore() {
        return _scoreSystem.getBestScore();
    }
}
