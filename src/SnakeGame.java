import java.awt.*;
import  java.awt.event.*;
import java.util.ArrayList.*;
import java.util.Random;
import javax.swing.*;

public class SnakeGame extends JPanel{

    int boarsWidth;
    int boardHeight;

    SnakeGame(int boarsWidth, int boardHeight){
     this.boarsWidth=boarsWidth;
     this.boardHeight=boardHeight;
     setPreferredSize(new Dimension(this.boarsWidth, this.boardHeight));
     setBackground(Color.black);
    }


}
