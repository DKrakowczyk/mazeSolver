/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mazesolver.threads;

import java.util.logging.Level;
import java.util.logging.Logger;
import mazesolver.grid.Grid;

/**
 *
 * @author DKrakowczyk & M. Kucharskov
 */
public class Worker extends Thread {

    IWorker worker;

    public Worker(IWorker worker) {
        this.worker = worker;
    }

    @Override
    public void run() {
        try {
            do {
                worker.start(); // Uruchomienie workera
                if (worker.workerStopped()) {
                    break;
                }
                Grid grid = worker.getGrid(); // Pobranie danych do algorytmu
                RunAlgorithm alg = new RunAlgorithm();
                alg.setWorker(worker);
                alg.setGrid(grid);
                alg.start();
                worker.finished(); // Zakonczenie dzialania algorytmu
            } while (!worker.workerStopped());
        } catch (InterruptedException ex) {
            Logger.getLogger(Worker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
