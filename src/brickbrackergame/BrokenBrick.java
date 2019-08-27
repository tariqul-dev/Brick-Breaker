package brickbrackergame;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;




public class BrokenBrick {
    
    private Color color;
    private int x, y, dy, type, brickWidth, brickHeight, width, height, preY;
    private boolean isScreen, wasUsed;
    
    public final static int GREEN_BRICK = 4;
    public final static int RED_BRICK = 5;
    public final static int YELLOW_BRICK = 6;
    
    public final static Color GREEN = Color.GREEN;
    public final static Color RED = Color.RED;
    public final static Color YELLOW = Color.YELLOW;
    
    public BrokenBrick(int x, int y, int type, int brickWidth, int brickHeight, int width, int height){
       this.x = x;
       this.y = y;
       preY = y;
       this.type = type;
       this.brickHeight = brickHeight;
       this.brickWidth = brickWidth;
       this.width = width;
       this.height = height;
       wasUsed = false;
       isScreen = true;
        init();
    }
    
    private void init(){
        switch (type) {
            case GREEN_BRICK:
                color = GREEN;
                break;
            case RED_BRICK:
                color = RED;
                break;
            case YELLOW_BRICK:
                color = YELLOW;
                break;
            default:
                break;
        }
        dy = (int)(Math.random() * 6 + 1);
    }
    
    public void render(Graphics2D g){
        g.setColor(color);
        g.fillRect(x, y, brickWidth, brickHeight);
    }
    
    public void update(){
        y += dy;
        
        if (y > height - brickHeight){
            isScreen = false;
        }
        
    }
    
    
    public Rectangle getRect(){
        return new Rectangle(x, y, brickWidth, brickHeight);
    }
    
    public void setX(int x){
        this.x = x;
    }
    
    public int getX(){
        return x;
    }
    
    public void setY(int y){
        this.y = y;
    }
    
    public int getY(){
        return y;
    }
    
    public int getDy(){
        return dy;
    }
    
    public void setIsOnScreen(boolean isScreen){
        this.isScreen = isScreen;
    }
    
    public boolean getIsOnScreen(){
        return isScreen;
    }
     
    public int getType(){
        return type;
    }
    
    
    public void setWasUsed(boolean wasUsed){
        this.wasUsed = wasUsed;
    }
    
    public boolean getWasUsed(){
        return wasUsed;
    }
    
}
