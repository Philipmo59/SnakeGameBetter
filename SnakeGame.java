import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;


public class SnakeGame extends JPanel implements ActionListener, KeyListener{
    //Snake Game Field;
    int boardHeight;
    int boardWidth;
    int tileSize = 25;
    
    //newSnake
    ArrayList<Tile> snake;
    
    //Food
    Tile food;
    //Random Food Location
    Random random;
    //Game Logic
    Timer gameLoop;
    int velocityX;
    int velocityY;


    //Snake Game Constructor;
    SnakeGame(int height, int width){
        this.boardHeight = height;
        this.boardWidth = width;
        setPreferredSize(new Dimension(this.boardWidth,this.boardHeight));
        setBackground(Color.black);
        addKeyListener(this);
        setFocusable(true);
        
        snake = new ArrayList<Tile>();
        snake.add(new Tile(5,5));
        food = new Tile (10,10);
        random = new Random();

        velocityX = 1;
        velocityY = 0;


        gameLoop = new Timer(100,this);
        gameLoop.start();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        draw(g);
    }    

    public void draw(Graphics g){
        //Horizontal and Vertical Lines
        for(int i = 0; i < boardWidth/tileSize; i++){
            g.drawLine(i*tileSize, 0, i*tileSize, boardHeight);
            g.drawLine(0, i*tileSize,boardWidth, i*tileSize);
            g.setColor(new Color(255,255,255));
        }

        //Food
        g.setColor(Color.pink);
        g.fillRect(food.x * tileSize, food.y *tileSize, tileSize, tileSize);

        //Snake
        g.setColor(Color.green);
        for(int i = 0; i < snake.size(); i++){
            System.out.println(snake.size());
            g.fillRect(snake.get(i).x * tileSize, snake.get(i).y * tileSize, tileSize, tileSize);
        }
        


     }

    public void placeFood(){
        food.x = random.nextInt(boardWidth/tileSize);
        food.y = random.nextInt(boardHeight/tileSize);
    }


    private class Tile{
        //Tile Field;    
            int x;
            int y;
    
            Tile(int x, int y){
                this.x = x;
                this.y = y;
            }
    
    }

    public boolean collision(Tile tile1, Tile tile2){
        return tile1.x == tile2.x && tile1.y == tile2.y;
    }
    public void move(){
        //eat food
        if(collision(snake.get(0), food)){
            Tile newSnakePart = new Tile(snake.get(snake.size()-1).x,snake.get(snake.size()-1).y);
            snake.add(newSnakePart);
            placeFood(); 
        }

        //Snake Growth
        for(int i = snake.size() - 1; i > 0 ; i--){
            if(i == 0) continue; 
            Tile lastBody = snake.get(i - 1);
            Tile nextBody = snake.get(i);
            nextBody.x = lastBody.x;
            nextBody.y = lastBody.y;                
            
        }
        
        //Snake movement
        snake.get(0).x += velocityX;
        snake.get(0).y += velocityY;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        move();
        repaint();
    }


    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_UP && velocityY != 1){
            velocityX = 0;
            velocityY = -1;
        }
        else if(e.getKeyCode() == KeyEvent.VK_DOWN && velocityY != -1){
            velocityX = 0;
            velocityY = 1;
        }
        else if(e.getKeyCode() == KeyEvent.VK_LEFT && velocityX != 1){
            velocityX = -1;
            velocityY = 0;
        }
        else if(e.getKeyCode() == KeyEvent.VK_RIGHT && velocityX != -1){
            velocityX = 1;
            velocityY = 0;
        }
    }

    // Dont need;
    @Override
    public void keyTyped(KeyEvent e) {
    }
    @Override
    public void keyReleased(KeyEvent e) {
    }
}
