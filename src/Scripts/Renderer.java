package Scripts;

import Scripts.Interfaces.Listener.DataProvider.IDataProvider;
import Scripts.Interfaces.Listener.IRenderListener;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Renderer {

    private JFrame _window;
    private JPanel _gamePanel;
    private JPanel _infoPanel;
    private JLabel _scoreLabel;
    private JLabel _levelLabel;
    private List<IRenderListener> _gameListeners;
    private List<IRenderListener> _infoListeners;
    private IDataProvider _dataProvider;

    public Renderer(IDataProvider dataProvider) {
        _dataProvider = dataProvider;
        _gameListeners = new ArrayList<>();
        _infoListeners = new ArrayList<>();
    }

    public void addGameListener(IRenderListener listener) {
        _gameListeners.add(listener);
    }

    public void addInfoListener(IRenderListener listener) {
        _infoListeners.add(listener);
    }

    public void removeGameListener(IRenderListener listener) {
        _gameListeners.remove(listener);
    }

    public void removeInfoListener(IRenderListener listener) {
        _infoListeners.remove(listener);
    }

    public void initialize(KeyHandler keyHandler) {
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
                for (IRenderListener listener : _gameListeners) {
                    listener.onRender(g);
                }
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
                updateLabels();
                for (IRenderListener listener : _infoListeners) {
                    listener.onRender(g);
                }
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

    private void updateLabels() {
        _scoreLabel.setText("Score: " + _dataProvider.getScore());
        _levelLabel.setText("Level: " + _dataProvider.getLevel());
    }

    public void repaint() {
        _gamePanel.repaint();
        _infoPanel.repaint();
    }

    private void drawBackground(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, GameConfig.WINDOW_WIDTH, GameConfig.WINDOW_HEIGHT);
    }

    private void drawGrid(Graphics g) {
        g.setColor(new Color(40, 40, 40));
        for (int col = 0; col <= GameConfig.COLS; col++) {
            g.drawLine(col * GameConfig.CELL_SIZE, 0, col * GameConfig.CELL_SIZE, GameConfig.WINDOW_HEIGHT);
        }
        for (int row = 0; row <= GameConfig.ROWS; row++) {
            g.drawLine(0, row * GameConfig.CELL_SIZE, GameConfig.WINDOW_WIDTH, row * GameConfig.CELL_SIZE);
        }
    }

    private void drawInfoBackground(Graphics g) {
        g.setColor(new Color(20, 20, 20));
        g.fillRect(0, 0, 150, GameConfig.WINDOW_HEIGHT);
    }
}