package Scripts.Interfaces.Listener.DataProvider;

import Scripts.Interfaces.Listener.IListener;

public interface IDataProvider extends IScoreProvider, ILevelProvider {
    int getBestScore();
}
