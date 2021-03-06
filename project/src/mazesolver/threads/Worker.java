/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mazesolver.threads;

import mazesolver.grid.Grid;

/**
 *
 * @author DKrakowczyk & M. Kucharskov
 */
public class Worker extends Thread {

    IConnectWorker worker;

    public Worker(IConnectWorker worker) {
        this.worker = worker;
    }

    @Override
    public void run() {
        try {
            do {
                worker.start();
                if (worker.workerStopped()) {
                    break;
                }
                Grid grid = worker.getGrid();
                RunAlgorithm algorithm = new RunAlgorithm();
                algorithm.setWorker(worker);
                algorithm.setGrid(grid);
                algorithm.start();
                worker.finished();
            } while (!worker.workerStopped());
        } catch (InterruptedException ex) {
        }
    }

}
