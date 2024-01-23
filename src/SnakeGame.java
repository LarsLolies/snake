import java.awt.*;
import  java.awt.event.*;
import java.util.ArrayList;
import java.util.ArrayList.*;
import java.util.Random;
import javax.swing.*;

public class SnakeGame extends JPanel implements ActionListener, KeyListener{
    @Override
    public void actionPerformed(ActionEvent e) {
        move();
        repaint();
        if(gameOver){
            gameLoop.stop();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()== KeyEvent.VK_UP && velocityY !=1){
            velocityX = 0;
            velocityY = -1;
        }
        else if(e.getKeyCode()== KeyEvent.VK_DOWN && velocityY != -1){
            velocityX = 0;
            velocityY = 1;
        }
        else if(e.getKeyCode()== KeyEvent.VK_LEFT && velocityX != 1){
            velocityX = -1;
            velocityY = 0;
        }
        else if(e.getKeyCode()== KeyEvent.VK_RIGHT && velocityX != -1){
            velocityX = 1;
            velocityY = 0;
        }
    }

    private class Tile{
        int x;
        int y;
        Tile(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    int boardWidth;
    int boardHeight;
    int tileSize=25;

    //snake
    Tile snakeHead;
    ArrayList<Tile> snakeBody;

    Tile food;

    Random random;

    Timer gameLoop;
    int velocityX;
    int velocityY;

    boolean gameOver = false;


    SnakeGame(int boarsWidth, int boardHeight){
     this.boardWidth=boarsWidth;
     this.boardHeight=boardHeight;
     setPreferredSize(new Dimension(this.boardWidth, this.boardHeight));
     setBackground(Color.black);
     addKeyListener(this);
     setFocusable(true);


     this.snakeHead = new Tile(5,5);
     this.snakeBody = new ArrayList<Tile>();

     this.food = new Tile(10, 10);
     random = new Random();
     placeFood();

     this.velocityX = 0;
     this.velocityY = 1;


     gameLoop = new Timer(100,this);
     gameLoop.start();
    }


    public void paintComponent(Graphics g){
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g){

        for(int i = 0; i < boardWidth/tileSize; i++){
            g.drawLine(i*tileSize, 0,i*tileSize, boardHeight);
            g.drawLine(0, i*tileSize, boardWidth, i*tileSize);
        }
            //draw sanke Head
        g.setColor(Color.green);
        g.fillRect(snakeHead.x*tileSize, snakeHead.y*tileSize, tileSize, tileSize);

            //draw Snake Body
        for(int i =0; i< snakeBody.size(); i++){
            Tile snakePart= snakeBody.get(i);
            g.fillRect(snakePart.x*tileSize, snakePart.y *tileSize, tileSize, tileSize );
        }



        //draw food

        g.setColor(Color.red);
        g.fillRect(food.x*tileSize, food.y*tileSize, tileSize, tileSize);

    }

    public void placeFood(){
        food.x= random.nextInt(boardWidth/tileSize);
        food.y=random.nextInt(boardHeight/tileSize);
    }

    public boolean collision(Tile tile1, Tile tile2){
        return tile1.x == tile2.x && tile1.y == tile2.y;
    }

    public void move(){
        if(collision(snakeHead, food)){
            snakeBody.add(new Tile(food.x, food.y));
            placeFood();
        }

        //snake body
        for(int i = snakeBody.size()-1; i>= 0; i--){
            Tile snakePart= snakeBody.get(i);
            if(i==0){
                snakePart.x= snakeHead.x;
                snakePart.y=snakeHead.y;
            }
            else{
                Tile prevSnakePart = snakeBody.get(i-1);
                snakePart.x =prevSnakePart.x;
                snakePart.y =prevSnakePart.y;
            }
        }


        //snake HEad
        snakeHead.x += velocityX;
        snakeHead.y += velocityY;

        for(int i = 0; i< snakeBody.size(); i++){
            Tile snakePart = snakeBody.get(i);

            if(collision(snakeHead, snakePart)){
                gameOver=true;
            }
            if(snakeHead.x*tileSize<0 || snakeHead.x*tileSize > boardWidth ||
                snakeHead.y*tileSize<0 || snakeHead.y*tileSize >boardHeight){
                gameOver =true;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
    @Override
    public void keyTyped(KeyEvent e) {

    }
}
