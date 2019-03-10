/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mazesolver.solver;

import mazesolver.grid.Grid;
import mazesolver.grid.Node;
import mazesolver.grid.Node.Types;
import mazesolver.gui.GUI;
import mazesolver.gui.IWorker;

/**
 *
 * @author Marcin
 */
public class Algo {

    Grid grid;
    IWorker worker;

    public Grid getGrid() {
        return grid;
    }

    public void setGrid(Grid grid) {
        this.grid = grid;
    }

    public IWorker getWorker() {
        return worker;
    }

    public void setWorker(IWorker worker) {
        this.worker = worker;
    }
    
    public void start() throws InterruptedException {
        AlgorithmFactory.getGenerator().solve(worker, grid);
    }
}
