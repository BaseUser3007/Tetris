package Scripts;

import Scripts.Bag.RenderPreview;
import Scripts.Figures.RenderFigure;

import javax.swing.*;
import java.awt.*;

public class GameInitializer {

    private JFrame _window;
    private JPanel _gamePanel;
    private JPanel _infoPanel;
    private JLabel _scoreLabel;
    private JLabel _levelLabel;
    private RenderFigure _renderFigure;
    private RenderPreview _renderPreview;
    private Grid _grid;
    private ScoreSystem _scoreSystem;
    private DifficultySystem _difficultySystem;

    public GameInitializer(Grid grid, ScoreSystem scoreSystem, DifficultySystem difficultySystem, RenderPreview renderPreview) {
        _grid = grid;
        _scoreSystem = scoreSystem;
        _difficultySystem = difficultySystem;
        _renderPreview = renderPreview;
    }

    private Grid getGrid() {
        return _grid;
    }

    public void initialize(KeyHandler keyHandler, RenderFigure renderFigure) {
        _renderFigure = renderFigure;

        _window = new JFrame("Тетрис");
        _window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        _window.setResizable(false);
        _window.setLayout(new BorderLayout());

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

        _infoPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                drawInfoBackground(g);
                _renderPreview.render(g, getWidth());
            }
        };

        _infoPanel.setPreferredSize(new Dimension(150, GameConfig.WINDOW_HEIGHT));
        _infoPanel.setBackground(new Color(20, 20, 20));
        _infoPanel.setLayout(new BoxLayout(_infoPanel, BoxLayout.Y_AXIS));

        _scoreLabel = new JLabel("Score: 0");
        _scoreLabel.setForeground(Color.WHITE);
        _scoreLabel.setFont(GameConfig.TETRIS_FONT);
        _scoreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        _levelLabel = new JLabel("Level: 1");
        _levelLabel.setForeground(Color.WHITE);
        _levelLabel.setFont(GameConfig.TETRIS_FONT);
        _levelLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        _infoPanel.add(Box.createVerticalStrut(400));
        _infoPanel.add(_scoreLabel);
        _infoPanel.add(Box.createVerticalStrut(20));
        _infoPanel.add(_levelLabel);

        _window.add(_gamePanel, BorderLayout.CENTER);
        _window.add(_infoPanel, BorderLayout.EAST);
        _window.pack();
        _window.setVisible(true);
        _gamePanel.requestFocusInWindow();
    }

    private void drawInfoBackground(Graphics g) {
        g.setColor(new Color(20, 20, 20));
        g.fillRect(0, 0, 150, GameConfig.WINDOW_HEIGHT);
    }

    public void updateInfo() {
        _scoreLabel.setText("Score: " + _scoreSystem.getScore());
        _levelLabel.setText("Level: " + _difficultySystem.getCurrentLevel());
        _infoPanel.repaint();
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