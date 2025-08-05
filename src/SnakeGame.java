import javax.swing.JFrame;

public class SnakeGame {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Snake Game");
        GamePanel panel = new GamePanel();

        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.pack(); // fit to preferred size of GamePanel
        frame.setLocationRelativeTo(null); // center on screen
        frame.setVisible(true);
    }
}
