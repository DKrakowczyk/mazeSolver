/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mazesolver.generator;

import mazesolver.grid.Grid;

/**
 *
 * @author DKrakowczyk & M. Kucharskov
 */
public class GeneratorFactory {

    private static int id = 2;
    
    public static IGenerator getGenerator(Grid grid) {
        // Return a generator depending on the id
        switch (GeneratorFactory.id) {
            case 1:
                // Simple noise generator based on Math.random()
                return new NoiseGenerator(grid);
            case 2:
                // Amazing maze generator with backtrack algorithm
                return new BacktrackGenerator(grid);
            default:
                return null;
        }

    }
}
