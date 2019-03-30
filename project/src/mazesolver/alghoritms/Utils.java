/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mazesolver.alghoritms;

import java.util.Map;
import mazesolver.grid.Grid;
import mazesolver.grid.Node;
import mazesolver.gui.GUI;
import mazesolver.gui.Menu;

/**
 *
 * @author DKrakowczyk & M. Kucharskov
 */
public class Utils {

    public static void showSolution(Map<Node, Node> solutionMap, Grid grid) {
        Node state = grid.getEnd();
        while (state != grid.getStart()) {
            if (state != grid.getEnd() && state != grid.getStart()) {
                state.setType(Node.Types.SOLUTION);
            }
            Node parent = solutionMap.get(state);
            state = parent;
            grid.repaint();
        }
    }

    public static void checkForAlerts(boolean solved, Grid grid) {
        if (solved) {
            Menu.alertMessage = 3;
            Menu.showAlert = true;
            grid.repaint();
        } else {
            Menu.alertMessage = 2;
            Menu.showAlert = true;
            GUI.running = false;
            grid.repaint();
        }
    }
}
