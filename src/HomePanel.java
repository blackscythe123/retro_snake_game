import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class HomePanel extends JPanel {
    public HomePanel(CardLayout layout, JPanel parent, GamePanel gamePanel) {
        setLayout(null);
        setBackground(Color.DARK_GRAY);

        JLabel title = new JLabel("SNAKE GAME", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 36));
        title.setForeground(Color.GREEN);
        title.setBounds(150, 80, 300, 50);
        add(title);

        JButton startButton = new JButton("Start Game");
        startButton.setBounds(220, 180, 150, 40);
        add(startButton);

        JButton helpButton = new JButton("Controls");
        helpButton.setBounds(220, 240, 150, 40);
        add(helpButton);

        JTextArea helpText = new JTextArea("Controls:\nArrow Keys or W/A/S/D to move\nP to Pause/Resume");
        helpText.setBounds(150, 300, 300, 100);
        helpText.setVisible(false);
        helpText.setBackground(Color.LIGHT_GRAY);
        helpText.setEditable(false);
        add(helpText);

        startButton.addActionListener(e -> {
            gamePanel.startGame();
            layout.show(parent, "Game");
        });

        helpButton.addActionListener(e -> helpText.setVisible(!helpText.isVisible()));
    }
}