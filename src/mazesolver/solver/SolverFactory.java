/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mazesolver.solver;

/**
 *
 * @author DKrakowczyk & M. Kucharskov
 */
public class SolverFactory {

    private static int id = 1;
    
    public static ISolver getSolver() {
        switch (SolverFactory.id) {
            case 1:
                return new SimpleSolver();
            default:
                return null;
        }

    }
}
