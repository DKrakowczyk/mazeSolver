package mazesolver;

import mazesolver.gui.GUI;
import java.awt.*;
import javax.swing.*;
import mazesolver.threads.BackgroundWorker;
import mazesolver.threads.Worker;

/**
 *
 * @author DKrakowczyk & M. Kucharskov
 */
public class MazeSolver extends JFrame {

    // Static dimension of maze
    public static final int height = 580;
    public static final int width = 900;
    public static final int nodeSize = 20;
    
    public MazeSolver() throws InterruptedException {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Maze Solver");
        setResizable(false);
        setLayout(new GridLayout(1, 1, 0, 0));

        // Add GUI to Frame
        BackgroundWorker bgWorker = new BackgroundWorker();
        GUI gui = new GUI(bgWorker);
        Worker w = new Worker(bgWorker);     
        
        add(gui);
        pack(); 
        setLocationRelativeTo(null);
        setVisible(true);
        
        w.start();
        w.join();
    }

    public static void main(String[] args) throws InterruptedException {
        new MazeSolver();
    }

}
