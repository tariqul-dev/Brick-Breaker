package brickbrackergame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Paddle {

    private double x, targetX;
    private int y, size, width, height, padHeight, startSize, startHeight;
    private boolean isSet, ck;
    private long trackTime;
    private int remainTime, startingRemainTime, bonusTime;

    public Paddle(int size, int width, int height) {
        this.width = width;
        this.height = height;
        this.size = size;
        startSize = size;
        init();
    }

    private void init() {
        x = width / 2 - size;
        y = height - 100;
        startHeight = 20;
        padHeight = 20;
        remainTime = 30;
        startingRemainTime = remainTime;
        bonusTime = 10;
        ck = false;
    }

    public void update() {

        if (((System.nanoTime() - trackTime) / 1000000000 > remainTime) && isSet) {
            size = startSize;
            isSet = false;
        }
        
        if (ck){
            ck = false;
            isSet = false;
        }

        movePaddle();
        
        int dif = (int) Math.abs(targetX - x) / 5;
        padHeight = startHeight - dif;
        if (padHeight < 2) {
            padHeight = 2;
        }

    }

    public void render(Graphics2D g) {
        int yN = y + (startHeight - padHeight) / 2;

        g.setColor(Color.blue);
        g.fillRect((int) x, yN, size, padHeight);

        if (isSet) {
            g.setColor(Color.WHITE);
            g.setFont(new Font("Courier New", Font.BOLD + Font.ITALIC, 18));
            g.drawString("" + (remainTime - (System.nanoTime() - trackTime) / 1000000000), (int) x + size / 2, y + 17);
        }
    }

    public Rectangle getRect() {
        return new Rectangle((int) x, y, size, padHeight);
    }

    private void movePaddle() {
        x += (targetX - x) * .3;
        if (targetX > width - size) {
            targetX = width - size;
        } else if (targetX < 0) {
            targetX = 0;
        }
    }

    public void setX(int x) {
        targetX = x - size / 2;

    }

    public double getX() {
        return x;
    }

    public void setSize(int size) {
        if (size > startSize * 2) {
            this.size = startSize * 2;
            remainTime = startingRemainTime + bonusTime;
            trackTime = System.nanoTime();
        } else {
            this.size = size;
            trackTime = System.nanoTime();
        }
    }

    public void setDefaultSize() {
        this.size = startSize;
        ck = true;
    }

    public int getSize() {
        return size;
    }

    public int getPadHeight() {
        return padHeight;
    }

    public int getY() {
        return y;
    }

    public boolean getIsSet() {
        return isSet;
    }
    
    public void setIsSet(boolean isSet){
        this.isSet = isSet;
    }

}
