/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mazesolver.alghoritms;

import java.util.Map;
import mazesolver.grid.Grid;
import mazesolver.grid.Node;
import mazesolver.threads.IConnectWorker;

/**
 *
 * @author DKrakowczyk & M. Kucharskov
 */
public interface IAlgorithm {

    public void solve(IConnectWorker worker, Grid grid) throws InterruptedException;

}
