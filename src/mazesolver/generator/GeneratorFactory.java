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

    public static IGenerator getGenerator(Grid grid, int id) {

        switch (id) {
            case 1:
                return new NoiseGenerator(grid);
            case 2:
                return new BacktrackGenerator(grid);
            default:
                return null;
        }

    }
}
