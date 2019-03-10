/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mazesolver.gui;

import mazesolver.grid.Grid;

/**
 *
 * @author DKrakowczyk & M. Kucharskov
 */
public class ComLayer implements IWorker, IGUI {

    Grid grid;
    int algorithm;
    boolean started;
    boolean stopAlgorithm;
    boolean stopWorker;
    boolean finished;

    @Override
    public synchronized boolean workerStopped() {
        return stopWorker;
    }

    @Override
    public synchronized void stopWorker(boolean stopWorker) {
        this.stopWorker = stopWorker;
        if (stopWorker) stopAlgorithm = true;

        notifyAll();
    }

    @Override
    public synchronized void start() throws InterruptedException {

        while (!(started || stopWorker)) {
            this.wait();
        }

        started = false;
        finished = false;
        stopAlgorithm = false;
    }
   
    @Override
    public synchronized boolean isStarted() {
        return started;
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
    public synchronized void setStarted(boolean started) {
        this.started = started;

        notifyAll();
    }

    @Override
    public synchronized boolean isStopAlgo() {
        return stopAlgorithm;
    }

    @Override
    public void stopAlgorithm(boolean stopAlgorithm) {
        this.stopAlgorithm = stopAlgorithm;
    }
    
       @Override
    public synchronized int getAlgorithm() {
        return algorithm;
    }

    @Override
    public synchronized void setAlgorithm(int algorithm) {
        this.algorithm = algorithm;
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
