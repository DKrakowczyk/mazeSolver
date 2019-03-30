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
public interface IConnectUI {

    boolean isStarted();

    void setAlgorithm(int algo);

    void setGrid(Grid grid);

    void stopAlgorithm(boolean shouldStop);

    void setStarted(boolean start);

    void stopWorker(boolean stopWorker);

    boolean isFinished();
}
