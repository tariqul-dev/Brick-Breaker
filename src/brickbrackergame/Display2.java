//package brickbrackergame;
//
//import javax.swing.JFrame;
//
//public class Display2 extends JFrame {
//
//    private Game game;
//    private Launcher2 launcher;
//
//    private int width, height;
//    private String title;
//
//    public Display2(String title, int width, int height) {
//        super(title);
//        this.title = title;
//        this.width = width;
//        this.height = height;
//        init();
//        frameManager();
//    }
//    
//   
//
//    
//    
//    private void init() {
//
//        launcher = new Launcher2(width, height, this);
//
//    }
//    
//    
//
//    private void frameManager() {
//        setSize(width, height);
//        setLocationRelativeTo(null);
//        setResizable(false);
//        
//        add(launcher.lan);
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setVisible(true);
//    }
//    
//
//}
