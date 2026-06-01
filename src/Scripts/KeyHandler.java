package Scripts;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyHandler extends KeyAdapter {

    private Mover _mover;
    private Runnable _onAction;
    private Runnable _onPause;
    private boolean _blocked;

    public KeyHandler(Mover mover, Runnable onAction, Runnable onPause) {
        _mover = mover;
        _onAction = onAction;
        _onPause = onPause;
        _blocked = false;
    }

    public void setBlocked(boolean blocked) {
        _blocked = blocked;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_Q) {
            _onPause.run();
            return;
        }

        if (e.getKeyCode() == KeyEvent.VK_E) {
            _mover.holdFigure();
            return;
        }

        if (_blocked) return;

        switch (e.getKeyCode()) {
            case KeyEvent.VK_A: _mover.moveLeft();  _onAction.run(); break;
            case KeyEvent.VK_D: _mover.moveRight(); _onAction.run(); break;
            case KeyEvent.VK_S: _mover.moveDown();  _onAction.run(); break;
            case KeyEvent.VK_W: _mover.rotate();    _onAction.run(); break;
        }
    }

    public void enable() {
        _blocked = false;
    }

    public void disable() {
        _blocked = true;
    }
}
