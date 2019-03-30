/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mazesolver.threads;

import mazesolver.grid.Grid;
import mazesolver.alghoritms.AlgorithmFactory;

/**
 *
 * @author DKrakowczyk & M. Kucharskov
 */
public class RunAlgorithm {

    Grid grid;
    IConnectWorker worker;

    public void setGrid(Grid grid) {
        this.grid = grid;
    }

    public void setWorker(IConnectWorker worker) {
        this.worker = worker;
    }

    public void start() throws InterruptedException {
        AlgorithmFactory.getAlgorithm().solve(worker, grid);
    }
}
