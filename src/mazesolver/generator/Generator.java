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
public class Generator {

    public static void generate(Grid grid) {
        int rows = grid.getRows();
        int cols = grid.getCols();
        double fillness = 0.3;

        // Erase grid
        grid.erase();
        
        //Simple grid noise generator
        for (int y = 0;  y < rows; y++) {
            for (int x = 0; x < cols; x++) {
                if(Math.random() < fillness) {
                    Node n = grid.getNode(x, y);
                    n.setType(Node.Types.WALL);
                }
            }
        }

    }
}
