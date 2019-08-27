package brickbrackergame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Ball {

    private double x, y, dx, dy;
    private int width, height, size;

    public Ball(double x, double y, double dx, double dy, int width, int height) {
        this.x = x;
        this.y = y;
        this.dx = dx;
        this.dy = dy;
        this.width = width;
        this.height = height;
        init();

    }

    private void init() {
        size = 30;
    }

    public void update() {
        position();
    }

    private void position() {

        x += dx;
        y += dy;

        if (x < 0) {
            dx = -dx;
        } else if (x > width - size) {
            dx = -dx;
        }

        if (y < 0) {
            dy = -dy;
        } else if (y > height - size) {
            dy = -dy;
        }
    }

    public void render(Graphics2D g) {
        g.setColor(Color.WHITE);
        g.fillOval((int)x, (int)y, size, size);
    }

    public Rectangle getRect() {
        return new Rectangle((int)x, (int)y, size, size);
    }

    public void setDx(double dx) {
        this.dx = dx;
    }

    public double getDx() {
        return dx;
    }

    public void setDy(double dy) {
        this.dy = dy;
    }

    public double getDy() {
        return dy;
    }
    
    public double getX(){
        return x;
    }
    
    public void setY(int y){
        this.y = y;
    }
    
    public int getSize(){
        return size;
    }
    
    public boolean isLose(){
        boolean lose = false;
        if (y > height - 100  + size){
            lose = true;
        }
        return lose;
    }

    
//    public void renderLose(Graphics2D g){
//        g.setColor(Color.RED);
//        g.setFont(new Font("Courier New", Font.BOLD + Font.ITALIC, 50));
//        g.drawString("Lose!", width/2 - 10, height / 2);
//    }
    
}
