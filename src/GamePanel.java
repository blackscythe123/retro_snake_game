import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class GamePanel extends JPanel implements ActionListener {
    static final int WIDTH = 600;
    static final int HEIGHT = 600;
    static final int UNIT_SIZE = 25;
    static final int DELAY = 100;

    Snake snake;
    Apple apple;
    Timer timer;
    boolean running = false;
    boolean paused = false;
    boolean inHomeScreen = true;
    JButton startButton;
    JButton playAgainButton;
    JButton pauseButton;
    JLabel controlsLabel;

    public GamePanel() {
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setBackground(Color.black);
        this.setFocusable(true);
        this.setLayout(null);
        this.addKeyListener(new MyKeyAdapter());

        // Initialize timer to avoid NullPointerException
        timer = new Timer(DELAY, this);

        // Start button
        startButton = new JButton("Start Game");
        startButton.setBounds(WIDTH / 2 - 80, HEIGHT / 2 - 20, 160, 40);
        startButton.addActionListener(e -> startGame());
        this.add(startButton);

        // Pause button
        pauseButton = new JButton("Pause");
        pauseButton.setBounds(WIDTH - 100, 10, 80, 30);
        pauseButton.setVisible(false);
        pauseButton.addActionListener(e -> togglePause());
        this.add(pauseButton);

        // Play again button
        playAgainButton = new JButton("Play Again");
        playAgainButton.setBounds(WIDTH / 2 - 80, HEIGHT / 2 + 50, 160, 40);
        playAgainButton.setVisible(false);
        playAgainButton.addActionListener(e -> restartGame());
        this.add(playAgainButton);

        // Controls label
        controlsLabel = new JLabel("<html>Controls:<br>WASD or Arrow Keys: Move<br>Space: Pause/Resume<br>Enter: Start/Play Again</html>");
        controlsLabel.setForeground(Color.white);
        controlsLabel.setFont(new Font("Ink Free", Font.PLAIN, 20));
        controlsLabel.setBounds(WIDTH / 2 - 100, HEIGHT / 2 + 30, 200, 100);
        this.add(controlsLabel);
    }

    public void startGame() {
        inHomeScreen = false;
        snake = new Snake();
        apple = new Apple();
        apple.spawnApple(WIDTH / UNIT_SIZE, HEIGHT / UNIT_SIZE, snake);
        if (!timer.isRunning()) {
            timer.start();
        }
        running = true;
        paused = false;
        startButton.setVisible(false);
        controlsLabel.setVisible(false);
        pauseButton.setVisible(true);
        playAgainButton.setVisible(false);
        requestFocusInWindow();
    }

    public void restartGame() {
        if (inHomeScreen || !running) {
            startGame();
        } else {
            timer.stop();
            startGame();
        }
        repaint();
        requestFocusInWindow();
    }

    public void togglePause() {
        if (running && !inHomeScreen) {
            paused = !paused;
            pauseButton.setText(paused ? "Resume" : "Pause");
            if (paused) {
                timer.stop();
            } else {
                timer.start();
            }
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {
        if (inHomeScreen) {
            g.setColor(Color.white);
            g.setFont(new Font("Ink Free", Font.BOLD, 50));
            g.drawString("Snake Game", WIDTH / 2 - 100, HEIGHT / 2 - 100);
        } else if (running && !paused) {
            g.setColor(Color.red);
            g.fillOval(apple.x * UNIT_SIZE, apple.y * UNIT_SIZE, UNIT_SIZE, UNIT_SIZE);

            for (int i = 0; i < snake.bodyParts; i++) {
                if (i == 0) {
                    g.setColor(Color.green);
                } else {
                    g.setColor(new Color(45, 180, 0));
                }
                g.fillRect(snake.x[i] * UNIT_SIZE, snake.y[i] * UNIT_SIZE, UNIT_SIZE, UNIT_SIZE);
            }

            g.setColor(Color.white);
            g.setFont(new Font("Ink Free", Font.BOLD, 30));
            g.drawString("Score: " + snake.bodyParts, 10, 30);
        } else if (paused) {
            g.setColor(Color.white);
            g.setFont(new Font("Ink Free", Font.BOLD, 50));
            g.drawString("Paused", WIDTH / 2 - 70, HEIGHT / 2);
            g.setFont(new Font("Ink Free", Font.PLAIN, 20));
            g.drawString("Press Space to resume", WIDTH / 2 - 80, HEIGHT / 2 + 50);
        } else {
            gameOver(g);
        }
    }

    public void gameOver(Graphics g) {
        g.setColor(Color.red);
        g.setFont(new Font("Ink Free", Font.BOLD, 75));
        FontMetrics fm = g.getFontMetrics();
        int gameOverWidth = fm.stringWidth("Game Over");
        g.drawString("Game Over", (WIDTH - gameOverWidth) / 2, HEIGHT / 2 - 20);

        g.setColor(Color.white);
        g.setFont(new Font("Ink Free", Font.BOLD, 30));
        fm = g.getFontMetrics();
        String scoreText = "Score: " + snake.bodyParts;
        int scoreWidth = fm.stringWidth(scoreText);
        g.drawString(scoreText, (WIDTH - scoreWidth) / 2, HEIGHT / 2 + 20);

        playAgainButton.setVisible(true);
    }

    public void checkApple() {
        if (snake.getHeadX() == apple.x && snake.getHeadY() == apple.y) {
            snake.grow();
            apple.spawnApple(WIDTH / UNIT_SIZE, HEIGHT / UNIT_SIZE, snake);
        }
    }

    public void checkCollisions() {
        for (int i = 1; i < snake.bodyParts; i++) {
            if (snake.getHeadX() == snake.x[i] && snake.getHeadY() == snake.y[i]) {
                running = false;
                break;
            }
        }

        if (snake.getHeadX() < 0 || snake.getHeadX() >= WIDTH / UNIT_SIZE ||
            snake.getHeadY() < 0 || snake.getHeadY() >= HEIGHT / UNIT_SIZE) {
            running = false;
        }

        if (!running) {
            timer.stop();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (running && !paused && !inHomeScreen) {
            snake.move();
            checkApple();
            checkCollisions();
        }
        repaint();
    }

    public class MyKeyAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_SPACE:
                    if (!inHomeScreen) {
                        togglePause();
                    }
                    break;
                case KeyEvent.VK_ENTER:
                    if (inHomeScreen || !running) {
                        restartGame();
                    }
                    break;
                case KeyEvent.VK_LEFT:
                case KeyEvent.VK_A:
                    snake.setDirection('L');
                    break;
                case KeyEvent.VK_RIGHT:
                case KeyEvent.VK_D:
                    snake.setDirection('R');
                    break;
                case KeyEvent.VK_UP:
                case KeyEvent.VK_W:
                    snake.setDirection('U');
                    break;
                case KeyEvent.VK_DOWN:
                case KeyEvent.VK_S:
                    snake.setDirection('D');
                    break;
            }
        }
    }
}