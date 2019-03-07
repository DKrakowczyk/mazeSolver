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
 * @author Marcin
 */
public class Worker extends Thread {

    WorkerCom cm;

    public Worker(WorkerCom cm) {
        this.cm = cm;
    }

    @Override
    public void run() {
        try {
            do {
                System.out.println("Worker: Czeka");
                cm.start();
                if (cm.isStopWorker()) break;
                System.out.println("Worker: Pobiera dane do algorytmu");
                Grid grid = cm.getGrid();
                Algo alg = new Algo();
                alg.setCm(cm);
                alg.setGerid(grid);
                System.out.println("Worker: Odpala algorytm");
                alg.start();
                System.out.println("Worker: Koniec algorytmu");
                cm.finished();
            } while (!cm.isStopWorker());
            System.out.println("Worker: Wyłączenie workera");
        } catch (InterruptedException ex) {
            Logger.getLogger(Worker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
