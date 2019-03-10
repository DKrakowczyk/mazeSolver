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

    public static ISolver getAlgorithm(int i) {
        switch (i) {
            case 1:
                return new DFSAlgorithm();

            case 2:
                return new BFSAlgorithm();
        }
        return new DFSAlgorithm();
    }
}
