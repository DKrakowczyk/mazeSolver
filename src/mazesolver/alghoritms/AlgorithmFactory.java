/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mazesolver.alghoritms;

/**
 *
 * @author DKrakowczyk & M. Kucharskov
 */
public class AlgorithmFactory {

    private static int id = 1;

    public static void setID(int i) {
        id = i;
    }

    public static int getID() {
        return id;
    }

    public static IAlgorithm getAlgorithm() {
        switch (id) {
            case 1:
                return new BFSAlgorithm();
            case 2:
                return new DFSAlgorithm();
            default:
                return new DijkstraAlgorithm();
        }
    }
}
