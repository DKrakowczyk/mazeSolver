/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mazesolver.solver;

import java.util.Map;
import mazesolver.grid.Grid;
import mazesolver.grid.Node;

/**
 *
 * @author DKrakowczyk & M. Kucharskov
 */
public interface ISolver {
    
    public boolean setGrid(Grid grid);
    
    public boolean isDone();

    public void doStep();

    public Map<Node, Node> getSolution();
}
