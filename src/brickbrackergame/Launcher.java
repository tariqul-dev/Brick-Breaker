package brickbrackergame;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Launcher {

    private Game game;
    private Display display;

    private JButton newGameButton, contButton, scoreButton, exitButton;
    private JLabel gameTitleLabel;
    public JPanel launcher;

    private int width, height;
    private int row, col, level, score;

    public Launcher(int width, int height, Display display) {
        this.width = width;
        this.height = height;
        this.display = display;
        init();
        labelManager();
        buttonManager();
        panelManager();
    }

    private void init() {
        launcher = new JPanel();
        gameTitleLabel = new JLabel("Brick Breaker");
        newGameButton = new JButton("New Game");
        contButton = new JButton("Continue");
        scoreButton = new JButton("Score");
        exitButton = new JButton("Exit");

        try {

            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection connection = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=BrickBreaker;integratedSecurity=true");

            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("select * from gameData");

            if (resultSet.next()) {
                score = resultSet.getInt("score");
                level = resultSet.getInt("level");
                row = resultSet.getInt("row");
                col = resultSet.getInt("col");
//                System.out.println(score);
//                System.out.println(level);
//                System.out.println(row);
//                System.out.println(col);

            }

            resultSet.close();
            statement.close();
            connection.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    private void panelManager() {
        launcher.setSize(width, height);
        launcher.setLayout(null);
        launcher.setBackground(new Color(50));
        launcher.setVisible(true);
    }

    private void labelManager() {
        gameTitleLabel.setFont(new Font("Courier New", Font.BOLD + Font.ITALIC, 157));
        gameTitleLabel.setForeground(Color.CYAN);
        gameTitleLabel.setBounds(15, 70, width, 110);
        launcher.add(gameTitleLabel);
    }

    private void buttonManager() {
        newGameButton.setFont(new Font("Courier New", Font.BOLD + Font.ITALIC, 80));
        newGameButton.setForeground(Color.black);
        newGameButton.setBackground(new Color(0, 250, 200));
        newGameButton.setBounds(width / 2 - 430 / 2, 350, 430, 70);
        launcher.add(newGameButton);

        newGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                row = 4;
                col = row * 2;
                launcher.setVisible(false);
                game = new Game(row, col, width, height, launcher);
                display.add(game);
            }
        });

        contButton.setFont(new Font("Courier New", Font.BOLD + Font.ITALIC, 80));
        contButton.setForeground(Color.black);
        contButton.setBackground(new Color(0, 250, 100));
        contButton.setBounds(width / 2 - 430 / 2, 455, 430, 70);
        launcher.add(contButton);

        contButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (level > 1) {
                    launcher.setVisible(false);
                    game = new Game(row, col, width, height, level, score, launcher);
                    display.add(game);
                }
            }
        });

        scoreButton.setFont(new Font("Courier New", Font.BOLD + Font.ITALIC, 80));
        scoreButton.setForeground(Color.black);
        scoreButton.setBackground(new Color(0, 250, 150));
        scoreButton.setBounds(width / 2 - 430 / 2, 555, 430, 70);
        launcher.add(scoreButton);

        scoreButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Your Highest Score: " + score, "Message", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        exitButton.setFont(new Font("Courier New", Font.BOLD + Font.ITALIC, 80));
        exitButton.setForeground(Color.black);
        exitButton.setBackground(new Color(200, 50, 50));
        exitButton.setBounds(width / 2 - 430 / 2, 655, 430, 70);
        launcher.add(exitButton);

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                display.dispose();
            }
        });

    }
}
