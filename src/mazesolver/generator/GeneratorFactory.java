/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mazesolver.generator;

/**
 *
 * @author DKrakowczyk & M. Kucharskov
 */
public class GeneratorFactory {

    public static IGenerator getGenerator(int id) {

        switch (id) {
            case 1:
                return new NoiseGenerator();
            default:
                return null;
        }

    }
}
