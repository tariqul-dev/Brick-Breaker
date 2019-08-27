//package brickbrackergame;
//
//import java.awt.Color;
//import java.awt.Font;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.ResultSet;
//import java.sql.Statement;
//import javax.swing.JButton;
//import javax.swing.JFrame;
//import javax.swing.JLabel;
//import javax.swing.JOptionPane;
//import javax.swing.JPanel;
//
//public class Launcher2  {
//
//    
//    private Game game;
//    private Display2 dis;
//    
//    private JButton newGameButton, contButton, scoreButton, exitButton;
//    private JLabel gameTitleLabel;
//    public JPanel lan;
//
//    private int width, height;
//    private int row, col, level, score;
//    private String title;
//    private boolean newGameOn, contGameOn;
//
//    public Launcher2(int width, int height, Display2 dis) {
//        this.width = width;
//        this.height = height;
//        this.dis = dis;
//        init();
//        labelManager();
//        buttonManager();
//        frameManager();
//    }
//
//    private void init() {
//        lan = new JPanel();
//        gameTitleLabel = new JLabel("Brick Breaker");
//        newGameButton = new JButton("New Game");
//        contButton = new JButton("Continue");
//        scoreButton = new JButton("Score");
//        exitButton = new JButton("Exit");
//        newGameOn = false;
//        contGameOn = false;
//        
//        try {
//
//                    Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//                    Connection connection = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=BrickBreaker;integratedSecurity=true");
//
//                    Statement statement = connection.createStatement();
//
//                    ResultSet resultSet = statement.executeQuery("select * from gameData");
//
//                    if (resultSet.next()) {
//                        score = resultSet.getInt("score");
//                        level = resultSet.getInt("level");
//                        row = resultSet.getInt("row");
//                        col = resultSet.getInt("col");
//
//                    }
//
//                    resultSet.close();
//                    statement.close();
//                    connection.close();
//
//                } catch (Exception ex) {
//                    ex.printStackTrace();
//                }
//
//    }
//
//    private void frameManager() {
//        lan.setSize(width, height);
//        lan.setLayout(null);
//        lan.setBackground(new Color(50));
//        lan.setVisible(true);
//    }
//
//    private void labelManager() {
//        gameTitleLabel.setFont(new Font("Courier New", Font.BOLD + Font.ITALIC, 157));
//        gameTitleLabel.setForeground(Color.CYAN);
//        gameTitleLabel.setBounds(15, 70, width, 110);
//        lan.add(gameTitleLabel);
//    }
//
//    private void buttonManager() {
//        newGameButton.setFont(new Font("Courier New", Font.BOLD + Font.ITALIC, 80));
//        newGameButton.setForeground(Color.black);
//        newGameButton.setBackground(new Color(0, 250, 200));
//        newGameButton.setBounds(width / 2 - 430 / 2, 350, 430, 70);
//        lan.add(newGameButton);
//
//        newGameButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                row = 5;
//                col = row * 2 + 2;
//                lan.setVisible(false);
////                     Display display = new Display(title, width, height, row, col, 10, 1289);
//                 game = new Game(row, col, width, height, lan);
//                 dis.add(game);
//            }
//        });
//
//        contButton.setFont(new Font("Courier New", Font.BOLD + Font.ITALIC, 80));
//        contButton.setForeground(Color.black);
//        contButton.setBackground(new Color(0, 250, 100));
//        contButton.setBounds(width / 2 - 430 / 2, 455, 430, 70);
//        lan.add(contButton);
//
//        contButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                
//                if (level > 1) {
//                    lan.setVisible(false);
//                    game = new Game(row, col, width, height, level, score, lan);
//                    dis.add(game);
//                }
//            }
//        });
//
//        scoreButton.setFont(new Font("Courier New", Font.BOLD + Font.ITALIC, 80));
//        scoreButton.setForeground(Color.black);
//        scoreButton.setBackground(new Color(0, 250, 150));
//        scoreButton.setBounds(width / 2 - 430 / 2, 555, 430, 70);
//        lan.add(scoreButton);
//
//        scoreButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                JOptionPane.showMessageDialog(null, "Your Highest Score: " + score, "Message", JOptionPane.INFORMATION_MESSAGE);
//            }
//        });
//
//        exitButton.setFont(new Font("Courier New", Font.BOLD + Font.ITALIC, 80));
//        exitButton.setForeground(Color.black);
//        exitButton.setBackground(new Color(200, 50, 50));
//        exitButton.setBounds(width / 2 - 430 / 2, 655, 430, 70);
//        lan.add(exitButton);
//
//        exitButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                dis.dispose();
//            }
//        });
//
//    }
//}
