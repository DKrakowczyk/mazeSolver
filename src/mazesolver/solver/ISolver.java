/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mazesolver.solver;

import mazesolver.grid.Grid;
import mazesolver.gui.IWorker;

/**
 *
 * @author Dawid
 */
public interface ISolver {
    public void solve(IWorker worker, Grid grid) throws InterruptedException;
}
