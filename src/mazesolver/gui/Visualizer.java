/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mazesolver.gui;

import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import mazesolver.grid.Grid;
import mazesolver.grid.Node;
import mazesolver.solver.ISolver;
import mazesolver.solver.SolverFactory;

/**
 *
 * @author M.Kucharskov
 */
public class Visualizer implements Runnable {

    GUI gui;
    ISolver solver;

    public Visualizer(GUI gui) {
        this.gui = gui;
        this.solver = SolverFactory.getSolver();
    }

    @Override
    public void run() {
        if (!solver.setGrid(gui.grid)) {
            System.out.println("Solver twierdzi że grid jest niepoprawny");
        } else {
            while (!solver.isDone()) {
                try {
                    solver.doStep();
                    gui.repaint();
                    Thread.sleep(500);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Visualizer.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            showRozwiazanie(solver.getSolution());
            gui.repaint();
        }
    }

    public void showRozwiazanie(Map<Node, Node> mapaRozwiazan) {
        Node stan = gui.grid.getEnd();
        while (stan != gui.grid.getStart()) {
            if (stan != gui.grid.getEnd() && stan != gui.grid.getStart()) {
                stan.setType(Node.Types.SOLUTION);
            }
            Node rodzic = mapaRozwiazan.get(stan);
            stan = rodzic;
        }
    }

}
