package Scripts;

import Scripts.Figures.RenderFigure;

import javax.swing.*;
import java.awt.*;

import javax.swing.*;
import java.awt.*;

public class GameInitializer {

    private JFrame _window;
    private JPanel _gamePanel;
    private RenderFigure _renderFigure;
    private Grid _grid;

    public GameInitializer(Grid grid) {
        _grid = grid;
    }

    private Grid getGrid() {
        return _grid;
    }

    public void initialize(KeyHandler keyHandler, RenderFigure renderFigure) {
        _renderFigure = renderFigure;

        _window = new JFrame("Тетрис");
        _window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        _window.setResizable(false);

        _gamePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                drawBackground(g);
                drawGrid(g);
                getGrid().render(g);
                _renderFigure.render(g);
            }
        };

        _gamePanel.setPreferredSize(new Dimension(GameConfig.WINDOW_WIDTH, GameConfig.WINDOW_HEIGHT));
        _gamePanel.setBackground(Color.BLACK);
        _gamePanel.setFocusable(true);
        _gamePanel.addKeyListener(keyHandler);

        _window.add(_gamePanel);
        _window.pack();
        _window.setVisible(true);
        _gamePanel.requestFocusInWindow();
    }

    private void drawBackground(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, GameConfig.WINDOW_WIDTH, GameConfig.WINDOW_HEIGHT);
    }

    private void drawGrid(Graphics g) {
        g.setColor(new Color(40, 40, 40));
        for (int col = 0; col <= getGrid().getCols(); col++) {
            g.drawLine(col * GameConfig.CELL_SIZE, 0, col * GameConfig.CELL_SIZE, GameConfig.WINDOW_HEIGHT);
        }
        for (int row = 0; row <= getGrid().getRows(); row++) {
            g.drawLine(0, row * GameConfig.CELL_SIZE, GameConfig.WINDOW_WIDTH, row * GameConfig.CELL_SIZE);
        }
    }

    public JPanel getGamePanel() {
        return _gamePanel;
    }
}