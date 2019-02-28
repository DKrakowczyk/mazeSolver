/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mazesolver.generator;

import mazesolver.grid.Grid;
import mazesolver.grid.Node;

/**
 *
 * @author DKrakowczyk & M. Kucharskov
 */
public class NoiseGenerator implements IGenerator {

    double noise = 0.3;

    public void setNoise(double noise) {
        if (noise < 0 || noise > 1) {
            return;
        }

        this.noise = noise;
    }

    @Override
    public void generate(Grid grid) {
        int rows = grid.getRows();
        int cols = grid.getCols();

        // Erase grid
        grid.erase();

        //Simple grid noise generator
        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < cols; x++) {
                if (Math.random() < noise) {
                    Node n = grid.getNode(x, y);
                    n.setType(Node.Types.WALL);
                }
            }
        }

    }
}
