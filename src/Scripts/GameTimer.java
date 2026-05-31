package Scripts;

import Scripts.Interfaces.ITickListener;

import javax.swing.Timer;
import java.util.ArrayList;
import java.util.List;

public class GameTimer {

    private Timer _timer;
    private List<ITickListener> _listeners;

    public GameTimer() {
        _listeners = new ArrayList<>();
        _timer = new Timer(GameConfig.INITIAL_DELAY, e -> notifyListeners());
    }

    public void addListener(ITickListener listener) {
        _listeners.add(listener);
    }

    public void removeListener(ITickListener listener) {
        _listeners.remove(listener);
    }

    private void notifyListeners() {
        for (ITickListener listener : _listeners) {
            listener.onTick();
        }
    }

    public void start() { _timer.start(); }
    public void stop()  { _timer.stop(); }

    public void setDelay(int delay) {
        _timer.setDelay(delay);
    }
}
