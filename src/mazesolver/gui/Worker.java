/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mazesolver.gui;

import java.util.logging.Level;
import java.util.logging.Logger;
import mazesolver.grid.Grid;
import mazesolver.solver.Algo;

/**
 *
 * @author DKrakowczyk & M. Kucharskov
 */
public class Worker extends Thread {

    WorkerCom worker;

    public Worker(WorkerCom worker) {
        this.worker = worker;
    }

    @Override
    public void run() {
        try {
            do {
                worker.start(); // Uruchomienie workera
                if (worker.isStopWorker()) {
                    break;
                }
                Grid grid = worker.getGrid(); // Pobranie danych do algorytmu
                Algo alg = new Algo();
                alg.setCm(worker);
                alg.setGrid(grid);
                alg.start();
                worker.finished(); // Zakonczenie dzialania algorytmu
            } while (!worker.isStopWorker());
        } catch (InterruptedException ex) {
            Logger.getLogger(Worker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
