package Scripts.Interfaces.Listener;

public interface IScoreListener extends IListener {
    void onFigureDropped();
    void onLinesCleared(int lines);
}
