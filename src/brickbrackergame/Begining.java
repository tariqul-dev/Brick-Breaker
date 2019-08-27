//package brickbrackergame;
//
//import java.awt.Color;
//import java.awt.Font;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import javax.swing.JButton;
//import javax.swing.JFrame;
//import javax.swing.JLabel;
//
//public class Begining {
//
//    private JFrame launcher, display;
//
//    private JButton newGameButton, contButton, scoreButton, exitButton;
//    private JLabel gameTitleLabel;
//
//    private int width, height;
//    private int row, col, level, score;
//    private String title;
//    
//    private Game game;
//    
//    public Begining(String title, int width, int height, int row, int col, int level, int score){
//        this.title = title;
//        this.width = width;
//        this.height = height;
//        this.row = row;
//        this.col = col;
//        this.level = level;
//        this.score = score;
//        
//        init();
//        labelManager();
//        buttonManager();
//        launcherFrameManager();
////        displayFrameManager();
//        
//        
//    }
//    
//    private void init(){
//        launcher = new JFrame(title);
//        display = new JFrame(title);
//        game = new Game(row, col, width, height, level, score);
//        
//        gameTitleLabel = new JLabel("Brick Breaker");
//        newGameButton = new JButton("New Game");
//        contButton = new JButton("Continue");
//        scoreButton = new JButton("Score");
//        exitButton = new JButton("Exit");
//    }
//    
//    private void launcherFrameManager() {
//        launcher.setSize(width, height);
//        launcher.setResizable(false);
//        launcher.setLocationRelativeTo(null);
//        launcher.setLayout(null);
//        launcher.getContentPane().setBackground(new Color(50));
//        launcher.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        launcher.setVisible(true);
//
//    }
//    private void displayFrameManager() {
//        display.setSize(width, height);
//        display.setResizable(false);
//        display.setLocationRelativeTo(null);
//        display.getContentPane().setBackground(new Color(50));
//        display.add(game);
//        display.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        display.setVisible(true);
////        game.GameLoop();
//
//    }
//
//    private void labelManager() {
//        gameTitleLabel.setFont(new Font("Courier New", Font.BOLD + Font.ITALIC, 122));
//        gameTitleLabel.setForeground(Color.CYAN);
//        gameTitleLabel.setBounds(10, 50, width, 80);
//        launcher.add(gameTitleLabel);
//    }
//
//    private void buttonManager() {
//        newGameButton.setFont(new Font("Courier New", Font.BOLD + Font.ITALIC, 80));
//        newGameButton.setForeground(Color.black);
//        newGameButton.setBackground(new Color(0, 250, 200));
//        newGameButton.setBounds(width / 2 - 430 / 2, 250, 430, 60);
//        launcher.add(newGameButton);
//
//        newGameButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
////                display.setVisible(true);
//displayFrameManager();
//            }
//        });
//
//        contButton.setFont(new Font("Courier New", Font.BOLD + Font.ITALIC, 80));
//        contButton.setForeground(Color.black);
//        contButton.setBackground(new Color(0, 250, 100));
//        contButton.setBounds(width / 2 - 430 / 2, 350, 430, 60);
//        launcher.add(contButton);
//
//        contButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//
//                
//
//            }
//        });
//
//        scoreButton.setFont(new Font("Courier New", Font.BOLD + Font.ITALIC, 80));
//        scoreButton.setForeground(Color.black);
//        scoreButton.setBackground(new Color(0, 250, 150));
//        scoreButton.setBounds(width / 2 - 430 / 2, 450, 430, 60);
//        launcher.add(scoreButton);
//
//        scoreButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//            }
//        });
//
//        exitButton.setFont(new Font("Courier New", Font.BOLD + Font.ITALIC, 80));
//        exitButton.setForeground(Color.black);
//        exitButton.setBackground(new Color(200, 50, 50));
//        exitButton.setBounds(width / 2 - 430 / 2, 550, 430, 60);
//        launcher.add(exitButton);
//
//        exitButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//            }
//        });
//
//    }
//
//}
