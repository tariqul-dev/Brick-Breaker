package brickbrackergame;

import java.awt.Color;
import java.awt.Graphics2D;



public class BrickPiece {
    
    private double x, y, dx, dy, gravity;
    private Bricks bricks;
    private Color color;
    private int size;
    
    
    public BrickPiece(double brickX, double brickY, Bricks bricks){
        this.bricks = bricks;
        this.x = brickX + bricks.getBrickWidth() / 2;
        this.y = brickY + bricks.getBrickHeight() / 2;
        dx = (Math.random() * 30 - 15);
        dy = (Math.random() * 30 - 15);
        color = new Color(0, 200, 200);
        size = (int) (Math.random() * 15 + 2);
        gravity = .07;
        
    }
    
    public void update(){
        x += dx;
        y += dy;
        dy += gravity;
    }
    
    public void render(Graphics2D g){
        g.setColor(color);
        g.fillRect((int) x, (int) y, size, size);
    }
    
}
