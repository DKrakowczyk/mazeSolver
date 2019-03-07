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
public class ComLayer implements WorkerCom, GUICom {

    Grid grid;
    int algo;
    boolean start;
    boolean stopAlgo;
    boolean stopWorker;
    boolean finished;

    @Override
    public synchronized boolean isStopWorker() {
        return stopWorker;
    }

    @Override
    public synchronized void setStopWorker(boolean stopWorker) {
        this.stopWorker = stopWorker;
        if (stopWorker) stopAlgo = true;
//============== 
        notifyAll();
    }

    @Override
    public synchronized void start() throws InterruptedException {
//============== TO JEST WAZNE
        while (!(start || stopWorker)) {
            this.wait();
        }
//============== 
        start = false;
        finished = false;
        stopAlgo = false;
    }
   
    @Override
    public synchronized boolean isStart() {
        return start;
    }

    @Override
    public synchronized void finished() {
        finished = true;
    }

    @Override
    public synchronized boolean isFinished() {
        return finished;
    }

    @Override
    public synchronized void setStart(boolean start) {
        this.start = start;
//============== 
        notifyAll();
    }

    @Override
    public synchronized boolean isStopAlgo() {
        return stopAlgo;
    }

    @Override
    public void setStopAlgo(boolean stopAlgo) {
        this.stopAlgo = stopAlgo;
    }
    
       @Override
    public synchronized int getAlgo() {
        return algo;
    }

    @Override
    public synchronized void setAlgo(int algo) {
        this.algo = algo;
    }

    @Override
    public synchronized void setGrid(Grid grid) {
        this.grid = grid;
    }

    @Override
    public synchronized Grid getGrid() {
        return grid;
    }

}
