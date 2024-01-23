import java.awt.*;
import  java.awt.event.*;
import java.util.ArrayList.*;
import java.util.Random;
import javax.swing.*;

public class SnakeGame extends JPanel implements ActionListener, KeyListener{
    @Override
    public void actionPerformed(ActionEvent e) {
        move();
        repaint();
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

    int boarsWidth;
    int boardHeight;
    int tileSize=25;

    Tile snakeHead;

    Tile food;

    Random random;

    Timer gameLoop;
    int velocityX;
    int velocityY;


    SnakeGame(int boarsWidth, int boardHeight){
     this.boarsWidth=boarsWidth;
     this.boardHeight=boardHeight;
     setPreferredSize(new Dimension(this.boarsWidth, this.boardHeight));
     setBackground(Color.black);
     addKeyListener(this);
     setFocusable(true);


     this.snakeHead = new Tile(5,5);
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

        for(int i = 0; i < boarsWidth/tileSize; i++){
            g.drawLine(i*tileSize, 0,i*tileSize, boardHeight);
            g.drawLine(0, i*tileSize, boarsWidth, i*tileSize);
        }
            //draw sanke
        g.setColor(Color.green);
        g.fillRect(snakeHead.x*tileSize, snakeHead.y*tileSize, tileSize, tileSize);

        //draw food

        g.setColor(Color.red);
        g.fillRect(food.x*tileSize, food.y*tileSize, tileSize, tileSize);

    }

    public void placeFood(){
        food.x= random.nextInt(boarsWidth/tileSize);
        food.y=random.nextInt(boardHeight/tileSize);
    }

    public void move(){
        snakeHead.x += velocityX;
        snakeHead.y += velocityY;
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
    @Override
    public void keyTyped(KeyEvent e) {

    }
}
