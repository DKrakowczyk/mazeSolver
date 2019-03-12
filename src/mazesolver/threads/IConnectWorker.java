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
public interface IConnectWorker {

    void finished();

    int getAlgorithm();

    Grid getGrid();

    boolean algorithmStopped();

    boolean workerStopped();

    void start() throws InterruptedException;

    void stopRunning();
}
