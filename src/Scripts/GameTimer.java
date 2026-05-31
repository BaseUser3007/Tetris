package Scripts;

import javax.swing.Timer;

public class GameTimer {

    private Timer _timer;

    public GameTimer(Runnable onTick) {
        _timer = new Timer(GameConfig.INITIAL_DELAY, e -> onTick.run());
    }

    public void start() { _timer.start(); }
    public void stop()  { _timer.stop(); }

    public void setDelay(int delay) {
        _timer.setDelay(delay);
    }
}
