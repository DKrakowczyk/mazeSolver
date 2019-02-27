package mazesolver;

import java.awt.*;
import javax.swing.*;

/**
 *
 * @author Dawid
 */
public class Frame extends JFrame{
    public Frame(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Maze Solver");
        setResizable(false);
        init();
    }
    
    public void init(){
        setLayout(new GridLayout(1,1,0,0));
        Maze m = new Maze();
        add(m);
        pack();
        setVisible(true);
    }
    
    public static void main(String[] args){
        new Frame();
    }
    
}
