import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;


public class Frame {
    private static final int HEIGHT = 500;
    private static final int WIDTH = 500;

    public static void main(String[] args){
        JFrame frame = new JFrame("SnakeGame");
        frame.setSize(HEIGHT,WIDTH);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 

        //Create a Label
        JLabel highscore = new JLabel("Score: ",JLabel.CENTER);
        highscore.setForeground(Color.black);
        highscore.setFont(new Font("Arial", Font.BOLD,36));
        highscore.setBackground(Color.white);
        highscore.setOpaque(true);

        // Create an instance of Snake Game
        SnakeGame snakeGame = new SnakeGame(HEIGHT,WIDTH);

        //Add components to JFrame
        frame.add(highscore,BorderLayout.NORTH);
        frame.add(snakeGame,BorderLayout.CENTER);
        frame.pack();


        frame.setVisible(true);
        snakeGame.requestFocus(); 
    }
}