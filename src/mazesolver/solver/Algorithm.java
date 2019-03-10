/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mazesolver.solver;

import mazesolver.grid.Grid;
import mazesolver.grid.Node;
import mazesolver.gui.IWorker;



/**
 *
 * @author Dawid
 */
public class Algorithm implements ISolver {

    @Override
    public void solve(IWorker worker, Grid grid) throws InterruptedException {
    grid.clear();
        System.out.println("Algorytm liczy");
        do {
            for (int y = 0; y < grid.getRows(); y++) {
                for (int x = 0; x < grid.getCols(); x++) {
                    if(worker.algorithmStopped()) break;
                    Node n = grid.getNode(x, y);
                    if(n.getType() != Node.Types.WALL) n.setType(Node.Types.VISITED);
                    else continue;
                    worker.getGrid().repaint();
                    Thread.sleep(10);
                }
            }
            worker.stopRunning();
          
        } while (!worker.algorithmStopped());
    }
    
}
