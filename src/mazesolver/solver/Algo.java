/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mazesolver.solver;

import mazesolver.grid.Grid;
import mazesolver.grid.Node;
import mazesolver.grid.Node.Types;
import mazesolver.gui.IWorker;

/**
 *
 * @author Marcin
 */
public class Algo {

    Grid grid;
    IWorker cm;

    public Grid getGrid() {
        return grid;
    }

    public void setGrid(Grid gerid) {
        this.grid = gerid;
    }

    public IWorker getCm() {
        return cm;
    }

    public void setCm(IWorker cm) {
        this.cm = cm;
    }

    public void start() throws InterruptedException {
        grid.clear();
        System.out.println("Algorytm liczy");
        do {
            for (int y = 0; y < grid.getRows(); y++) {
                for (int x = 0; x < grid.getCols(); x++) {
                    if(cm.isStopAlgo()) break;
                    Node n = grid.getNode(x, y);
                    if(n.getType() != Types.WALL) n.setType(Types.VISITED);
                    else continue;
                    cm.getGrid().repaint();
                    Thread.sleep(100);
                }
            }

            System.out.println("Algo: Liczy");
        } while (!cm.isStopAlgo());
        System.out.println("Algo: Kończenie algorytmu");
    }
}
