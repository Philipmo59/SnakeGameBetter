import javax.swing.JFrame;

public class Frame {
    private static final int HEIGHT = 500;
    private static final int WIDTH = 500;

    public static void main(String[] args){
        JFrame frame = new JFrame("SnakeGame");
        frame.setSize(HEIGHT,WIDTH);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 

        SnakeGame snakeGame = new SnakeGame(HEIGHT,WIDTH);
        frame.add(snakeGame);
        frame.pack();
        snakeGame.requestFocus(); 
 
    }
}