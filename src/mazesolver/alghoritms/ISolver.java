/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mazesolver.alghoritms;

import java.util.Map;
import mazesolver.grid.Grid;
import mazesolver.grid.Node;
import mazesolver.threads.IWorker;

/**
 *
 * @author DKrakowczyk & M. Kucharskov
 */
public interface ISolver {
    public void solve(IWorker worker, Grid grid) throws InterruptedException;
    public void showSolution(Map<Node, Node> mapaRozwiazan, Grid grid);
}
