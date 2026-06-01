package Scripts;

import Scripts.Interfaces.Listener.DataProvider.IDataProvider;
import Scripts.Interfaces.Listener.DataProvider.ILevelProvider;
import Scripts.Interfaces.Listener.DataProvider.IScoreProvider;

public class GameDataProvider implements IDataProvider {

    private IScoreProvider _scoreProvider;
    private ILevelProvider _levelProvider;

    public GameDataProvider(IScoreProvider scoreProvider, ILevelProvider levelProvider) {
        _scoreProvider = scoreProvider;
        _levelProvider = levelProvider;
    }

    @Override
    public int getScore() {
        return _scoreProvider.getScore();
    }

    @Override
    public int getLevel() {
        return _levelProvider.getLevel();
    }
}
