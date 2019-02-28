package mazesolver;

import mazesolver.gui.GUI;
import java.awt.*;
import javax.swing.*;

/**
 *
 * @author DKrakowczyk & M. Kucharskov
 */
public class MazeSolver extends JFrame {

    public static final int height = 600;
    public static final int width = 900;
    public static final int nodeSize = 20;
    
    public MazeSolver() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Maze Solver");
        setResizable(false);
        setLayout(new GridLayout(1, 1, 0, 0));

        GUI gui = new GUI();
        
        add(gui);
        pack(); 
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        new MazeSolver();
    }

}
