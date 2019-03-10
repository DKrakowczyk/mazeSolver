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

    public static int getID() {
        return id;
    }

    public static void setID(int id) {
        AlgorithmFactory.id = id;
    }

    public static ISolver getGenerator() {
        return new DFSAlgorithm();
    }
}
