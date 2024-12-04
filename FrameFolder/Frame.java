package FrameFolder;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import SnakeFolder.SnakeGame;



public class Frame {
    private static final int HEIGHT = 500;
    private static final int WIDTH = 500;
    private static JLabel title;
    public static int score = 0;
    public static JButton button;
    public static JPanel panel;
    public static JFrame frame;
    public static SnakeGame snakeGame;

    public static void main(String[] args){
        frame = new JFrame("SnakeGame");
        frame.setSize(HEIGHT,WIDTH);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 

        //Create a Label
        title = new JLabel("Score: " + score, JLabel.CENTER);
        title.setForeground(Color.black);
        title.setFont(new Font("Arial", Font.BOLD,36));
        title.setBackground(Color.white);
        title.setOpaque(true);

        // Create an instance of Snake Game
        snakeGame = new SnakeGame(HEIGHT,WIDTH);

        //Add components to JFrame
        frame.add(title,BorderLayout.NORTH);
        frame.add(snakeGame,BorderLayout.CENTER);
        frame.pack();

        frame.setVisible(true);
        snakeGame.requestFocus(); 
    }

    public static void updateLabel(){
        Frame.title.setText("Score: " + score);
    }

    public static void addRestartButton(){
        // Instantiate button and panel
        Frame.title.setText("You Lose. Your score was: " + score);

        JButton button = new JButton("Play Again!");
        JPanel panel = new JPanel();
        panel.setBackground(Color.white);
        panel.setPreferredSize(new java.awt.Dimension(WIDTH, 50));
        panel.add(button);

        

        // Create an event listener for the button
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                //Reset the score, remove the button and previous SnakeGame and instantiate a new SnakeGame and repaint the UI.
                score = 0;
                updateLabel();
                frame.remove(panel);
                frame.remove(snakeGame);

                snakeGame = new SnakeGame(HEIGHT, WIDTH);
                frame.add(snakeGame, BorderLayout.CENTER);

   
                frame.revalidate();
                frame.repaint();
    
                snakeGame.requestFocus();



            }
        });

        frame.add(panel,BorderLayout.SOUTH);
        frame.pack();

        
    }
}