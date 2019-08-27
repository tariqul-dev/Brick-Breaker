package brickbrackergame;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class Bricks {

    private int[][] brick;
    private final int row, col, brickWidth, brickHeight, width, height;
    
    public final int xPos = 80, yPos = 50;

    public Bricks(int row, int col, int width, int height) {
        this.row = row;
        this.col = col;
        this.width = width;
        this.height = height;
        init();

        brickWidth = (width - 3 * xPos) / col;
        brickHeight = (height / 2 - 4 * yPos) / row;

    }

    private void init() {
        brick = new int[row][col];

        int countGreen = 0;
        int countRed = 0;
        int countYellow = 0;
        
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                int rand = (int) (Math.random() * 6 + 1);
                
                if (rand == 4){
                    countGreen++;
                }
                if (rand == 5){
                    countRed++;
                }
                if (rand == 6){
                    countYellow++;
                }
                
                if (countYellow > (int) (row/2)){
                    rand = (int) (Math.random() * 5 + 1);
                }
                
                if (countRed > (int) (row / 2 + 2)){
                    rand = (int) (Math.random() * 4 + 1);
                }
                
                if ((countGreen > (int) (row / 2 + 2))){
                    rand = (int) (Math.random() * 3 + 1);
                }
                
                
                
                
                brick[i][j] = rand;
            }
        }

    }

    public void render(Graphics2D g) {

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (brick[i][j] > 0) {
                    switch (brick[i][j]) {
                        case 1:
                            g.setColor(new Color(0, 200, 200));
                            break;
                        case 2:
                            g.setColor(new Color(0, 150, 150));
                            break;
                        case 3:
                            g.setColor(new Color(0, 100, 100));
                            break;
                        case 4:
                            g.setColor(Color.GREEN);
                            break;
                        case 5:
                            g.setColor(Color.RED);
                            break;
                        case 6:
                            g.setColor(Color.YELLOW);
                            break;
                        default:
                            break;
                    }
                    
                    
                    
                    g.fillRect(j * brickWidth + xPos + 40, i * brickHeight + yPos + 100, brickWidth, brickHeight);
                    g.setStroke(new BasicStroke(10));
                    g.setColor(new Color(50));
                    g.drawRect(j * brickWidth + xPos + 40, i * brickHeight + yPos + 100, brickWidth, brickHeight);
                }
                
            }
            
        }
        
    }

    public int getBrickWidth() {
        return brickWidth;
    }

    public int getBrickHeight() {
        return brickHeight;
    }
    
    public int[][] getBricks(){
        return brick;
    }

    public void setBrickValue(int row, int col, int value){
        brick[row][col] = value;
    }
    
    public void hitBrick(int row, int col) {
        brick[row][col] -= 1;
        if(brick[row][col] < 0){
            brick[row][col] = 0;
        }
    }
    
    public boolean isWin(){
        boolean win = false;
        int brickRemain = 0;
        for (int i = 0; i < brick.length; i++){
            for (int j = 0; j < brick[0].length; j++){
                brickRemain += brick[i][j];
            }
        }
        if (brickRemain == 0){
            win = true;
        }
        return win;
    }
    
//    public void renderWin(Graphics2D g){
//        g.setColor(Color.ORANGE);
//        g.setFont(new Font("Courier New", Font.BOLD + Font.ITALIC, 50));
//        g.drawString("Win!", width / 2 - 10, height / 2);
//    }

}
