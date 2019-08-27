package brickbrackergame;

import javax.swing.JFrame;

public class Display extends JFrame {

    
    private Launcher launcher;

    private int width, height;
    private String title;

    public Display(String title, int width, int height) {
        super(title);
        this.title = title;
        this.width = width;
        this.height = height;
        init();
        frameManager();
    }
    
   

    
    
    private void init() {

        launcher = new Launcher(width, height, this);

    }
    
    

    private void frameManager() {
        setSize(width, height);
        setLocationRelativeTo(null);
        setResizable(false);
        
        add(launcher.launcher);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    

}
