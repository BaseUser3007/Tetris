package Scripts;

import Scripts.Interfaces.Listener.ITickListener;

import javax.swing.Timer;
import java.util.ArrayList;
import java.util.List;

public class GameTimer {

    private Timer _timer;
    private boolean _paused;
    private List<ITickListener> _listeners;

    public GameTimer() {
        _listeners = new ArrayList<>();
        _timer = new Timer(GameConfig.INITIAL_DELAY, e -> notifyListeners());
        _paused = false;
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

    public void pause() {
        _timer.stop();
        _paused = true;
    }

    public void resume() {
        _timer.start();
        _paused = false;
    }

    public boolean isPaused() {
        return _paused;
    }

    public void start() { _timer.start(); }
    public void stop()  { _timer.stop(); }

    public void setDelay(int delay) {
        _timer.setDelay(delay);
    }
}
