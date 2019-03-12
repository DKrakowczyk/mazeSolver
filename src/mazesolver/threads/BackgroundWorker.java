/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mazesolver.threads;

import mazesolver.grid.Grid;
import mazesolver.gui.GUI;

/**
 *
 * @author DKrakowczyk & M. Kucharskov
 */
public class BackgroundWorker implements IConnectWorker, IConnectUI {

    Grid grid;
    int algorithm;
    boolean started;
    boolean stopAlgorithm;
    boolean workerStopped;
    boolean finished;

    @Override
    public synchronized boolean workerStopped() {
        return workerStopped;
    }

    @Override
    public synchronized void stopWorker(boolean stopWorker) {
        this.workerStopped = stopWorker;
        if (stopWorker) {
            stopAlgorithm = true;
        }

        notifyAll();
    }

    @Override
    public synchronized void start() throws InterruptedException {

        while (!(started || workerStopped)) {
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
    public synchronized boolean algorithmStopped() {
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

    @Override
    public void stopRunning() {
        GUI.running = false;
        stopAlgorithm(true);
    }
}
