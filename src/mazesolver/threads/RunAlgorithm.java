/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mazesolver.threads;

import mazesolver.grid.Grid;
import mazesolver.grid.Node;
import mazesolver.grid.Node.Types;
import mazesolver.gui.GUI;
import mazesolver.alghoritms.AlgorithmFactory;
import mazesolver.threads.IWorker;

/**
 *
 * @author Marcin
 */
public class RunAlgorithm {

    Grid grid;
    IWorker worker;

    public void setGrid(Grid grid) {
        this.grid = grid;
    }

    public void setWorker(IWorker worker) {
        this.worker = worker;
    }

    public void start() throws InterruptedException {
        AlgorithmFactory.getAlgorithm(GUI.algorithmIndex).solve(worker, grid);
    }
}
