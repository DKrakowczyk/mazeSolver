/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mazesolver.solver;

import mazesolver.grid.Grid;

/**
 *
 * @author DKrakowczyk & M. Kucharskov
 */
public class SolverFactory {

    public static ISolver getSolver(Grid grid, int id) {
        // Return a solver depending on the id
        switch (id) {
            case 1:
                return new AcrossSolver(grid);
            case 2:
                return new DeepSolver(grid);
            default:
                return null;
        }

    }
}
