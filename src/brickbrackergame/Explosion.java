package brickbrackergame;

import java.awt.Graphics2D;
import java.util.ArrayList;

public class Explosion {

    private int x, y;
    private long startTime;
    private Bricks bricks;
    private ArrayList<BrickPiece> piece;
    private boolean isActive;

    public Explosion(int x, int y, Bricks bricks) {
        this.x = x;
        this.y = y;
        this.bricks = bricks;
        init();
    }

    private void init() {
        startTime = System.nanoTime();
        isActive = true;
        piece = new ArrayList<>();
        int rand = (int) (Math.random() * 100 + 60);

        for (int i = 0; i < rand; i++) {
            piece.add(new BrickPiece((double)x, (double)y, bricks));
        }

    }

    public void update() {
        for (BrickPiece bp : piece) {
            bp.update();
        }
        if (((System.nanoTime() - startTime) / 1000000000) > 100 && isActive) {
            isActive = false;
        }
    }

    public void render(Graphics2D g) {
        for (BrickPiece bp : piece) {
            bp.render(g);
        }
    }
    
    public boolean getIsActive(){
        return isActive;
    }

}
