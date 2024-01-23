import javax.swing.*;

public class Main {
    static int boardWidth= 600;
    static int boardHight =600;
    public static void main(String[] args) {


        JFrame frame = new JFrame("Sanke");
        setFrame(frame);


    }



    static public void setFrame(JFrame frame){
        frame.setVisible(true);
        frame.setSize(boardWidth, boardHight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}