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

    //Snake
    Tile snakeHead;
    ArrayList<Tile> snakeBody;
    
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

        snakeHead = new Tile(5,5);
        snakeBody = new ArrayList<Tile>();
        food = new Tile (10,10);
        random = new Random();
        placeFood();

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

        //SnakeHead
        g.setColor(Color.green);
        g.fillRect(snakeHead.x * tileSize, snakeHead.y * tileSize, tileSize, tileSize);

        //snakeBody
        for(int i = 0; i < snakeBody.size();i++){
            Tile snakePart = snakeBody.get(i); 
            g.fillRect(snakePart.x * tileSize, snakePart.y * tileSize, tileSize, tileSize);
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
        return tile1.x == tile2.x && tile2.y == tile2.y;
    }

    public void move(){
        //eat food
        if(collision(snakeHead, food)){
            snakeBody.add(new Tile(food.x,food.y));
            placeFood();
        }
        //Snake Head
        snakeHead.x += velocityX;
        snakeHead.y += velocityY;
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
