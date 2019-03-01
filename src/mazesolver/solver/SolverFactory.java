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

    public static int getID() {
        return id;
    }

    public static void setID(int id) {
        SolverFactory.id = id;
    }

    public static ISolver getSolver() {
        switch (SolverFactory.id) {
            default:
                return null;
        }

    }
}
