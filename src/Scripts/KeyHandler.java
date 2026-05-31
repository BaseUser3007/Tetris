package Scripts;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyHandler extends KeyAdapter {

    private Mover _mover;
    private Runnable _onAction;

    public KeyHandler(Mover mover, Runnable onAction) {
        _mover = mover;
        _onAction = onAction;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_A: _mover.moveLeft();  _onAction.run(); break;
            case KeyEvent.VK_D: _mover.moveRight(); _onAction.run(); break;
            case KeyEvent.VK_S: _mover.moveDown();  _onAction.run(); break;
            case KeyEvent.VK_W: _mover.rotate();    _onAction.run(); break;
        }
    }
}
