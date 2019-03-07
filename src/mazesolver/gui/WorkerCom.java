/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mazesolver.gui;

import mazesolver.grid.Grid;

/**
 *
 * @author Marcin
 */
public interface WorkerCom {

    void finished();

    int getAlgo();

    Grid getGrid();

    boolean isStopAlgo();

    boolean isStopWorker();

    void start() throws InterruptedException;
    
}
