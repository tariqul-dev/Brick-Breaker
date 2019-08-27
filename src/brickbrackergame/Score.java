package brickbrackergame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;





public class Score {
    
    private int score;
    
    private boolean isSet;
    
    public Score(){
        init();
    }
    
    private void init(){
        score = 0;
        isSet = false;
    }
    
    public void render(Graphics2D g){
        g.setColor(Color.WHITE);
        g.setFont(new Font("Courier New", Font.BOLD + Font.ITALIC, 40));
        g.drawString("Score: " + score, 20, 80);
    }
    
    
    public void setScore(int score){
        this.score = score;
    }
    
    public int getScore(){
        return score;
    }
    
    public void setIsSet(boolean isSet){
        this.isSet = isSet;
    }
    
    public boolean getIsSet(){
        return isSet;
    }
}
