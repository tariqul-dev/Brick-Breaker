package brickbrackergame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Game extends JPanel implements Runnable {

    public static boolean gameOver;
    private JPanel launcher;

    private BufferedImage image;
    private Graphics2D g;

    private int width, height, mouseX, row, col, preScore;
    private boolean running, screenShakeActive;
    private long shakeTime;
    private static int level;
    private double speed;

    private Ball ball;
    private Paddle paddle;
    private Bricks bricks;
    private Score score;
    private ArrayList<BrokenBrick> broken;
    private ArrayList<Explosion> explosion;

    private MouseMotion mouseMotion;

    public Game(int row, int col, int width, int height, JPanel launcher) {
        this.width = width;
        this.height = height;
        this.level = 1;
        preScore = 0;
        this.launcher = launcher;

        init(row, col);
        Thread thread = new Thread(this);
        thread.start();

    }

    public Game(int row, int col, int width, int height, int level, int preScore, JPanel launcher) {
        this.width = width;
        this.height = height;
        this.level = level;
        this.preScore = preScore;
        this.launcher = launcher;
        
        
        init(row, col, preScore);
        Thread thread = new Thread(this);
        thread.start();
    }

    private void init(int row, int col) {

        this.row = row;
        this.col = col;
        speed = level * .1;
        running = true;
        screenShakeActive = false;
        gameOver = false;

        shakeTime = System.nanoTime();
        ball = new Ball(width / 2.7, height - 130, 1 + speed, 3 + speed, width, height);
        paddle = new Paddle(120, width, height);
        bricks = new Bricks(row, col, width, height);
        score = new Score();
        score.setScore(preScore);
        mouseMotion = new MouseMotion();
        addMouseMotionListener(mouseMotion);
        broken = new ArrayList<>();
        explosion = new ArrayList<>();

        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        g = (Graphics2D) image.getGraphics();

    }

    private void init(int row, int col, int preScore) {

        this.row = row;
        this.col = col;
        this.preScore = preScore;
        speed = level * .1;
        running = true;
        screenShakeActive = false;
        gameOver = false;

        shakeTime = System.nanoTime();
        ball = new Ball(width / 2.7, height - 130, 1 + speed, 3 + speed, width, height);
        paddle = new Paddle(120, width, height);
        bricks = new Bricks(row, col, width, height);
        score = new Score();
        score.setScore(preScore);
        mouseMotion = new MouseMotion();
        addMouseMotionListener(mouseMotion);
        broken = new ArrayList<>();
        explosion = new ArrayList<>();

        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        g = (Graphics2D) image.getGraphics();

    }

    @Override
    public void run() {

        while (running) {
            update();
            render();
            repaint();

            try {
                Thread.sleep(9);
            } catch (InterruptedException e) {
                System.out.println(e);
            }
            if (running == false) {

                if (bricks.isWin()) {

                    if (level < 10) {

                        int res = JOptionPane.showConfirmDialog(null, "Victory!!\n\nYour score is: " + score.getScore() + "\nDo you want to play next level?", "Message", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
                       
                        // Leveling
                        
                        if (res == JOptionPane.YES_OPTION) {
                            level++;
                            row += 1;
                            col = row * 2 + 1;
                            init(row, col, score.getScore());
                            
                        } 
                        else if (res == JOptionPane.NO_OPTION) {
                            g.setColor(Color.WHITE);
                            g.setFont(new Font("Courier New", Font.BOLD + Font.ITALIC, 100));
                            g.drawString("Game Over!!", width / 2 - 300, height / 2 + 50);
                            repaint();
                            gameOver = true;
                            
                        }

                        try {
                            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                            Connection connection = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=BrickBreaker;integratedSecurity=true");

                            Statement statement = connection.createStatement();
                            statement.execute("truncate table gameData;");
                            statement.execute("insert into gameData values ('" + score.getScore() + "', '" + level + "', '" + row + "', '" + col + "');");

                            statement.close();
                            connection.close();
                        } catch (Exception e) {
                            System.out.println(e);
                        }

                    } else {
                        try {
                            //                        JOptionPane.showMessageDialog(null, "Congratulatins!\n\nYou've Finish The Game\nYour Score is: " + score.getScore(), "Victory", JOptionPane.INFORMATION_MESSAGE);
                            g.setColor(Color.CYAN);
                            g.setFont(new Font("Courier New", Font.BOLD + Font.ITALIC, 100));
                            g.drawString("Congratulations!!", width / 2 - 500, height / 3 + 50);
                            g.setColor(Color.MAGENTA);
                            g.setFont(new Font("Courier New", Font.BOLD + Font.ITALIC, 48));
                            g.drawString("You've Played All Levels of This Game", width / 3 - 300, height / 2 + 50);
                            repaint();
                            Thread.sleep(10000);
                            gameOver = true;
                        } catch (InterruptedException ex) {
                            System.out.println(ex);
                        }

                    }
                }

                if (ball.isLose()) {
                    int res = JOptionPane.showConfirmDialog(null, "Your score is: " + score.getScore() + "\nDo you want to try again?", "Message", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);

                    if (res == JOptionPane.YES_OPTION) {

                        init(row, col, score.getScore());

                    } else if (res == JOptionPane.NO_OPTION) {
                        g.setColor(Color.WHITE);
                        g.setFont(new Font("Courier New", Font.BOLD + Font.ITALIC, 100));
                        g.drawString("Game Over!!", width / 2 - 300, height / 2 + 50);
                        repaint();
                        gameOver = true;
                    }
                }

            }
        }
        
        isOver();

    }

    
    private void isOver(){
        if (gameOver){
            setVisible(false);
            launcher.setVisible(true);
        }
    }
    
    private void update() {
        collisionCheck();
        ball.update();
        paddle.update();

        for (BrokenBrick b : broken) {
            b.update();
        }

        for (int i = 0; i < explosion.size(); i++) {
            explosion.get(i).update();
            if (!explosion.get(i).getIsActive()) {
                explosion.remove(i);
            }
        }
    }

    private void render() {

        g.clearRect(0, 0, width, height);
        g.setColor(new Color(50));
        g.fillRect(0, 0, width, height);

        ball.render(g);
        paddle.render(g);
        bricks.render(g);
        score.render(g);
        renderBrokenBrick();
        renderLevel();

        for (Explosion e : explosion) {
            e.render(g);
        }

        if (((System.nanoTime() - shakeTime) / 1000000) > 300 && screenShakeActive) {
            screenShakeActive = false;
        }

        if (bricks.isWin()) {
//            renderWin();
            running = false;
        }
        else if (ball.isLose()) {
//            renderLose();
            running = false;
        }
    }

    public void collisionCheck() {

        

        if (paddle.getRect().intersects(ball.getRect())) {

            ball.setY(paddle.getY() - ball.getSize());
            ball.setDy(-ball.getDy());

            if (ball.getX() + ball.getSize() < mouseX - paddle.getSize() / 3) {
                ball.setDx(ball.getDx() - .5);
            }
            if (ball.getX() >= (mouseX + paddle.getSize() / 3)) {
                ball.setDx(ball.getDx() + .5);
            }

        }

        for (int i = 0; i < bricks.getBricks().length; i++) {
            for (int j = 0; j < bricks.getBricks()[0].length; j++) {
                if (bricks.getBricks()[i][j] > 0) {
                    int brickWidth = bricks.getBrickWidth();
                    int brickHeight = bricks.getBrickHeight();
                    int brickX = j * brickWidth + bricks.xPos + 40;
                    int brickY = i * brickHeight + bricks.yPos + 100;

                    Rectangle brickRect = new Rectangle(brickX, brickY, brickWidth, brickHeight);

                    if (ball.getRect().intersects(brickRect)) {

                        if (bricks.getBricks()[i][j] == 1) {
                            explosion.add(new Explosion(brickX, brickY, bricks));
                            screenShakeActive = true;
                            shakeTime = System.nanoTime();
                            score.setScore(score.getScore() + 10);
                        }

                        if (bricks.getBricks()[i][j] > 3) {
                            broken.add(new BrokenBrick(brickX, brickY, bricks.getBricks()[i][j], brickWidth, brickHeight, WIDTH, HEIGHT));
                            bricks.setBrickValue(i, j, 0);
                            
                        }
                        else {
                            bricks.hitBrick(i, j);

                        }

                        ball.setDy(-ball.getDy());
//                        bricks.hitBrick(i, j);

                        break;
                    }
                }
            }
        }

        for (int i = 0; i < broken.size(); i++) {

            Rectangle brokenRect = broken.get(i).getRect();

            if (paddle.getRect().intersects(brokenRect)) {
                if ((broken.get(i).getType() == BrokenBrick.GREEN_BRICK) && !broken.get(i).getWasUsed()) {
                    paddle.setSize(paddle.getSize() * 2);
                    paddle.setIsSet(true);
                    score.setScore(score.getScore() + 15);
                    broken.get(i).setWasUsed(true);

                } else if ((broken.get(i).getType() == BrokenBrick.RED_BRICK) && !broken.get(i).getWasUsed()) {
                    paddle.setDefaultSize();
                    paddle.setIsSet(true);
                    score.setScore(score.getScore() - 5);
                    broken.get(i).setWasUsed(true);

                    if (score.getScore() < 0) {
                        score.setScore(0);
                    }

                } else if ((broken.get(i).getType() == BrokenBrick.YELLOW_BRICK) && !broken.get(i).getWasUsed()) {
                    score.setScore(score.getScore() + 50);
                    broken.get(i).setWasUsed(true);
                }

                if (broken.get(i).getIsOnScreen()) {
                    broken.get(i).setWasUsed(false);
                }

            }

        }

    }

    private void renderBrokenBrick() {
        for (BrokenBrick b : broken) {
            b.render(g);
        }
    }

    private void renderWin() {
        g.setColor(Color.ORANGE);
        g.setFont(new Font("Courier New", Font.BOLD + Font.ITALIC, 50));
        g.drawString("Win!", width / 2 - 100, height / 2);

        g.setFont(new Font("Courier New", Font.BOLD + Font.ITALIC, 30));
        g.drawString("Press Enter for next level", width / 2 - 100, height / 2 + 50);
    }

    private void renderLose() {
        g.setColor(Color.RED);
        g.setFont(new Font("Courier New", Font.BOLD + Font.ITALIC, 50));
        g.drawString("Lose!", width / 2 - 100, height / 2);

        g.setFont(new Font("Courier New", Font.BOLD + Font.ITALIC, 30));
        g.drawString("Press Enter to try again", width / 2 - 100, height / 2 + 50);
    }

    private void renderLevel() {
        g.setColor(Color.red);
        g.setFont(new Font("Courier New", Font.BOLD + Font.ITALIC, 40));
        g.drawString("Level: " + level, 20, 40);
    }

    private class MouseMotion implements MouseMotionListener {

        @Override
        public void mouseDragged(MouseEvent e) {

        }

        @Override
        public void mouseMoved(MouseEvent e) {
            mouseX = e.getX();
            paddle.setX(mouseX);
        }

    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        int x = 0;
        int y = 0;
        if (screenShakeActive == true) {
            x = (int) (Math.random() * 10 - 5);
            y = (int) (Math.random() * 10 - 5);
        }

        g2.drawImage(image, x, y, width, height, null);

        g2.dispose();
    }

}
